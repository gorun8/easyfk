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
package cn.gorun8.easyfk.install.service;

import java.util.Properties;

import cn.gorun8.easyfk.base.util.FileUtil;

/**
 * 本Service定义了安装的基本接口
 * @author:hezhiping(110476592@qq.com)
 *
 */
public interface InitService {
	public static final String DB_PROPERTIES_PATH_TMP = FileUtil.getFile("component://install/config/install-database-temp.properties").getAbsolutePath();
	public static final String DB_PROPERTIES_PATH = FileUtil.getFile("component://base/config/easyfk-database.properties").getAbsolutePath();
	
	/**
	 * 检测数据库配置是否正确
	 * @param properties 数据库的配置参数
	 * @return
	 * @throws Exception
	 */
	public String checkConnect(Properties properties) throws Exception;
	
	/**
	 * 执行安装
	 * @return
	 * @throws Exception
	 */
	public String install() throws Exception;
	
	/**
	 * 结束安装
	 * @return
	 */
	public String finish();

}
