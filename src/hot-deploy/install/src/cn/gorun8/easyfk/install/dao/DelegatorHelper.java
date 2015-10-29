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
package cn.gorun8.easyfk.install.dao;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.gorun8.easyfk.base.util.Debug;

/**
 * 数据库访问工具类，采用Mybatis实现与数据库的访问
 * @author:hezhiping(110476592@qq.com)
 *
 */
public class DelegatorHelper {
	
	private static String module = DelegatorHelper.class.toString();
	private static SqlSessionFactory sqlSessionFactory ;
	private static Configuration configuration;
 	private DelegatorHelper(){}
 	
	
	private static SqlSessionFactory getSessionFactory(String mybatisConfigFile){
		buildSessionFactory(mybatisConfigFile);
		return sqlSessionFactory;
	}
	
	/**
	 * 根据配置，构建Mybatis的Session	
	 * @param mybatisConfigFile
	 */
	private static void buildSessionFactory(String mybatisConfigFile){
		try {
			sqlSessionFactory = new SqlSessionFactoryBuilder()			
					.build(Resources.getResourceAsReader(mybatisConfigFile));
			configuration = sqlSessionFactory.getConfiguration();
		} catch (IOException e) {
			if(Debug.errorOn())
			{
				Debug.logError(e.toString(), module);
			}
		}
		 
	}
	
	/**
	 * 根据配置文件获取数据库连接 
	 * @param configFile
	 * @return
	 */
	public static SqlSession getDelegator(String configFile){
		SqlSession sqlSession = getSessionFactory(configFile).openSession();
		return sqlSession;
	}
	
	public static Configuration getConfiguration(){
		return  configuration;
	}
	
	
	 

}
