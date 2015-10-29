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
package cn.gorun8.easyfk.entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.gorun8.easyfk.entity.util.ModelUtil;
import cn.gorun8.easyfk.entity.util.SequenceUtil;
import javolution.util.FastMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;

import com.google.common.collect.Lists;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;

/**
 * 实现数据库ID值的生产管理。
 * ID由SequenceUtil负责产生。系统用数据表sequence_value_item统一记录所有其它数据表的ID当前状态。
 * 根据表名可以生成下一个ID。
 *
 * 注：为了提高效率，SequenceUtil每次从sequence_value_item表中取出10个ID。用完了再取。
 */
public class SequenceFactory {
    private static SequenceFactory instance = null;
    protected SequenceUtil sequencer = null;

    private SequenceFactory() {
    }

    public static synchronized SequenceFactory getInstance() {
        if (instance == null) {
            instance = new SequenceFactory();
        }

        return instance;
    }

    /* (non-Javadoc)
     * @see org.ofbiz.entity.Delegator#getNextSeqId(java.lang.String)
     */
    public String getNextSeqId(String seqName) {
        return this.getNextSeqId(seqName, 1);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.entity.Delegator#getNextSeqId(java.lang.String, long)
     */
    public String getNextSeqId(String seqName, long staggerMax) {
        Long nextSeqLong = this.getNextSeqIdLong(seqName, staggerMax);

        if (nextSeqLong == null) {
            // NOTE: the getNextSeqIdLong method SHOULD throw a runtime exception when no sequence value is found, which means we should never see it get here
            throw new IllegalArgumentException("Could not get next sequenced ID for sequence name: " + seqName);
        }

        return nextSeqLong.toString();
    }

    /* (non-Javadoc)
     * @see org.ofbiz.entity.Delegator#getNextSeqIdLong(java.lang.String)
     */
    public Long getNextSeqIdLong(String seqName) {
        return this.getNextSeqIdLong(seqName, 1);
    }

    /* (non-Javadoc)
     * @see org.ofbiz.entity.Delegator#getNextSeqIdLong(java.lang.String, long)
     */
    public Long getNextSeqIdLong(String seqName, long staggerMax) {
        String tmpSeqName = ModelUtil.javaNameToDbName(seqName);
        if (sequencer == null) {
            synchronized (this) {
                if (sequencer == null) {
                    sequencer = new SequenceUtil("SEQUENCE_VALUE_ITEM", "SEQ_NAME", "SEQ_ID");
                }
            }
        }

        Long newSeqId = sequencer == null ? null : sequencer.getNextSeqId(tmpSeqName, staggerMax);
        return newSeqId;

    }

    /* (non-Javadoc)
     * @see org.ofbiz.entity.Delegator#setSequencer(org.ofbiz.entity.util.SequenceUtil)
     */
    public void setSequencer(SequenceUtil sequencer) {
        this.sequencer = sequencer;
    }

    /* (non-Javadoc)
     * @see org.ofbiz.entity.Delegator#refreshSequencer()
     */
    public void refreshSequencer() {
        this.sequencer = null;
    }


}
