package cn.gorun8.easyfk.install.dao.impl;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.install.dao.DelegatorHelper;
import cn.gorun8.easyfk.install.dao.InitDao;
 
 
@Repository
public class InitDaoImpl implements InitDao {
	public static final String module = InitDaoImpl.class.getName();
	private Connection connection;
	
	@Override
	public boolean initDB(String dbType,List<String> sqlFile)throws Exception{
		
		SqlSession delegator = DelegatorHelper.getDelegator("install-mybatis-configuration.xml");
		connection = delegator.getConnection();
		executeSQLFile(sqlFile);
		return true;
	}
	 
	@Override
	public String checkConnect() {
		SqlSession delegator = DelegatorHelper.getDelegator("install-mybatis-configuration.xml");
		Connection connection = delegator.getConnection();
		if(connection == null)
		{
			return "connectFail";
		}
		//保存数据库配置
		return "success";
	}
 
	
	/**
	 * 执行SQL文件，文件为目录则遍历执行
	 */
	public void executeSQLFile(List<String> sqlFile) throws Exception{
		Statement stmt = null;
		String sql = "";
		StringBuffer sqlbuf = new StringBuffer();
		String sqlStr ="";
		String sqlFileName ="";
		long lineIndex = 0;
		try {
			stmt = connection.createStatement();
			connection.setAutoCommit(false);
			// 执行sql文件
			
			for (String fileName : sqlFile)
			{
				File file = new File(fileName);
				if(!file.exists())
				{
					continue;
				}
				sqlFileName = fileName;
				lineIndex = 0;

				InputStreamReader isr = new InputStreamReader(
						new FileInputStream(file), "utf-8");
				BufferedReader br = new BufferedReader(isr);
				while ((sql = br.readLine()) != null) {
					sql = sql.trim();
					++lineIndex;
					boolean commented = sql.startsWith("--");
					boolean commented2 = sql.startsWith("/*");
					boolean commented3 = sql.startsWith("*/");
					boolean commented4 = sql.startsWith("*");
					if (UtilValidate.isEmpty(sql) || commented || commented2
							|| commented3|| commented4) {
						continue;
					}

					sqlbuf.append(sql);
					sqlbuf.append(" ");
					if (sql.endsWith(";")) {
						sqlStr = sqlbuf.toString().replaceAll("\r\n","\n");
				        if (Debug.verboseOn()) Debug.logVerbose(sqlStr + "\n", module);
						stmt.execute(sqlStr);
						sqlbuf = new StringBuffer();
					}

				}//end while
				connection.commit();
			}//end for
			connection.setAutoCommit(true);
		} catch (Exception e) {
			if (Debug.errorOn()){
				Debug.logError("error at " +sqlFileName+"\n"+sqlbuf.toString() + "\n", module);
				Debug.logError(e.toString() , module);
			}
			try {
				connection.rollback();
			} catch (SQLException e1) {
				if (Debug.errorOn()){
					Debug.logError(e.toString() , module);
				}
			}
			StringBuffer errorbuf = new StringBuffer();
			errorbuf.append(sqlFileName);
			errorbuf.append("(line:");
			errorbuf.append(lineIndex);
			errorbuf.append(")<br>");
			errorbuf.append(sqlStr);
			errorbuf.append("<br>");
			errorbuf.append(e.getMessage());
			throw  new Exception(errorbuf.toString());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					if (Debug.errorOn()){
						Debug.logError(e.toString() , module);
					}
				}
			}

		}
	}

	 
	  
}
