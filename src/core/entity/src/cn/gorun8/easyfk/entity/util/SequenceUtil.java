/*
 * Project:Easy Web Framework
 *
 * Description: This project is based on much more open source projects than ever before,
 *              and can be applied to mostly web development environment.
 * Author:hezhiping   Email:110476592@qq.com
 *
 *
 *==========================================================================================
 *
 */
package cn.gorun8.easyfk.entity.util;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilIOC;
import cn.gorun8.easyfk.entity.GenericEntityException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Sequence Utility to get unique sequences from named sequence banks
 * Uses a collision detection approach to safely get unique sequenced ids in banks from the database
 */
public class SequenceUtil {
    public static final String module = SequenceUtil.class.getName();

    private final ConcurrentMap<String, SequenceBank> sequences = new ConcurrentHashMap<String, SequenceBank>();
    private final String tableName;
    private final String nameColName;
    private final String idColName;

	 public SequenceUtil(String tableName,String nameColName, String idColName) {
        this.tableName = tableName;
        this.nameColName = nameColName;
        this.idColName = idColName;
    }

    public Long getNextSeqId(String seqName, long staggerMax) {
        SequenceBank bank = this.getBank(seqName);
        return bank.getNextSeqId(staggerMax);
    }

    public void forceBankRefresh(String seqName, long staggerMax) {
        // don't use the get method because we don't want to create if it fails
        SequenceBank bank = sequences.get(seqName);
        if (bank == null) {
            return;
        }

        bank.refresh(staggerMax);
    }

	private SequenceBank getBank(String seqName) {
        SequenceBank bank = sequences.get(seqName);

        if (bank == null) {
            long bankSize = SequenceBank.defaultBankSize;
            bank = new SequenceBank(seqName, bankSize);
            SequenceBank bankFromCache = sequences.putIfAbsent(seqName, bank);
            bank = bankFromCache != null ? bankFromCache : bank;
        }

        return bank;
    }

    private class SequenceBank {
        public static final long defaultBankSize = 10;
        public static final long maxBankSize = 5000;
        public static final long startSeqId = 10000;

        private final String seqName;
        private final long bankSize;
        private final String updateForLockStatement;
        private final String selectSequenceStatement;

        private long curSeqId;
        private long maxSeqId;

        private SequenceBank(String seqName, long bankSize) {
            this.seqName = seqName;
            this.curSeqId = 0;
            this.maxSeqId = 0;
            this.bankSize = bankSize;
            this.updateForLockStatement = "UPDATE " + SequenceUtil.this.tableName + " SET " + SequenceUtil.this.idColName + "=" + SequenceUtil.this.idColName + " WHERE " + SequenceUtil.this.nameColName + "='" + this.seqName + "'";
            this.selectSequenceStatement = "SELECT " + SequenceUtil.this.idColName + " FROM " + SequenceUtil.this.tableName + " WHERE " + SequenceUtil.this.nameColName + "='" + this.seqName + "'";
        }

        private Long getNextSeqId(long staggerMax) {
            long stagger = 1;
            if (staggerMax > 1) {
                stagger = (long)Math.ceil(Math.random() * staggerMax);
                if (stagger == 0) stagger = 1;
            }
            synchronized (this) {
                if ((curSeqId + stagger) <= maxSeqId) {
                    long retSeqId = curSeqId;
                    curSeqId += stagger;
                    return retSeqId;
                } else {
                    fillBank(stagger);
                    if ((curSeqId + stagger) <= maxSeqId) {
                        long retSeqId = curSeqId;
                        curSeqId += stagger;
                        return retSeqId;
                    } else {
                        Debug.logError("Fill bank failed, returning null", module);
                        return null;
                    }
                }
            }
        }

        private synchronized void refresh(long staggerMax) {
            this.curSeqId = this.maxSeqId;
            this.fillBank(staggerMax);
        }

        /*
           The algorithm to get the new sequence id in a thread safe way is the following:
           1 - run an update with no changes to get a lock on the record
               1bis - if no record is found, try to create and update it to get the lock
           2 - select the record (now locked) to get the curSeqId
           3 - increment the sequence
           The three steps are executed in one dedicated database transaction.
         */
        private void fillBank(long stagger) {
            // no need to get a new bank, SeqIds available
            if ((curSeqId + stagger) <= maxSeqId) {
                return;
            }

            long bankSize = this.bankSize;
            if (stagger > 1) {
                // NOTE: could use staggerMax for this, but if that is done it would be easier to guess a valid next id without a brute force attack
                bankSize = stagger * defaultBankSize;
            }

            if (bankSize > maxBankSize) {
                bankSize = maxBankSize;
            }

            Connection connection = null;
            try {
                Statement stmt = null;
                ResultSet rs = null;

                try {
                    SqlSessionFactoryBean sqlSessionFactoryBean = UtilIOC.getBean(org.mybatis.spring.SqlSessionFactoryBean.class);
                    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
                    connection = sqlSessionFactory.openSession().getConnection();
                } catch (SQLException sqle) {
                    Debug.logWarning("Unable to esablish a connection with the database. Error was:" + sqle.toString(), module);
                    throw sqle;
                } catch (GenericEntityException e) {
                    Debug.logWarning("Unable to esablish a connection with the database. Error was: " + e.toString(), module);
                    throw e;
                }
                if (connection == null) {
                    throw new GenericEntityException("Unable to esablish a connection with the database, connection was null...");
                }

                try {
                    stmt = connection.createStatement();
                    String sql = null;
                    // 1 - run an update with no changes to get a lock on the record
                    if (stmt.executeUpdate(updateForLockStatement) <= 0) {
                        Debug.logWarning("Lock failed; no sequence row was found, will try to add a new one for sequence: " + seqName, module);
                        sql = "INSERT INTO " + SequenceUtil.this.tableName + " (" + SequenceUtil.this.nameColName + ", " + SequenceUtil.this.idColName + ") VALUES ('" + this.seqName + "', " + startSeqId + ")";
                        try {
                            stmt.executeUpdate(sql);
                        } catch (SQLException sqle) {
                            // insert failed: this means that another thread inserted the record; then retry to run an update with no changes to get a lock on the record
                            if (stmt.executeUpdate(updateForLockStatement) <= 0) {
                                // This should never happen
                                throw new GenericEntityException("No rows changed when trying insert new sequence: " + seqName);
                            }
                       }
                    }
                    // 2 - select the record (now locked) to get the curSeqId
                    rs = stmt.executeQuery(selectSequenceStatement);
                    boolean sequenceFound = rs.next();
                    if (sequenceFound) {
                        curSeqId = rs.getLong(SequenceUtil.this.idColName);
                    }
                    rs.close();
                    if (!sequenceFound) {
                        throw new GenericEntityException("Failed to find the sequence record for sequence: " + seqName);
                    }
                    // 3 - increment the sequence
                    sql = "UPDATE " + SequenceUtil.this.tableName + " SET " + SequenceUtil.this.idColName + "=" + SequenceUtil.this.idColName + "+" + bankSize + " WHERE " + SequenceUtil.this.nameColName + "='" + this.seqName + "'";
                    if (stmt.executeUpdate(sql) <= 0) {
                        throw new GenericEntityException("Update failed, no rows changes for seqName: " + seqName);
                    }
                     connection.commit();

                } catch (SQLException sqle) {
                    Debug.logWarning(sqle, "SQL Exception:" + sqle.getMessage(), module);
                    throw sqle;
                } finally {
                    try {
                        if (stmt != null) stmt.close();
                    } catch (SQLException sqle) {
                        Debug.logWarning(sqle, "Error closing statement in sequence util", module);
                    }
                    try {
                        if (connection != null) connection.close();
                    } catch (SQLException sqle) {
                        Debug.logWarning(sqle, "Error closing connection in sequence util", module);
                    }
                }
            } catch (Exception e) {
                // reset the sequence fields and return (note: it would be better to throw an exception)
                curSeqId = 0;
                maxSeqId = 0;
                String errMsg = "General error in getting a sequenced ID";
                Debug.logError(e, errMsg, module);
                try {
                    connection.rollback();
                } catch (Exception gte2) {
                    Debug.logError(gte2, "Unable to rollback transaction", module);
                }
                return;
            }


            maxSeqId = curSeqId + bankSize;
            if (Debug.infoOn()) Debug.logInfo("Got bank of sequenced IDs for [" + this.seqName + "]; curSeqId=" + curSeqId + ", maxSeqId=" + maxSeqId + ", bankSize=" + bankSize, module);
        }
    }
}
