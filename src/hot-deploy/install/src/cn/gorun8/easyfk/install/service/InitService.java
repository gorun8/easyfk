/*
 * Project:Easy Web Framework
 * Description:
 * EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
 * was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
 * foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
 * and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
 * right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
 * Of course, you can customize it or use it as a framework to implement your most challenging business needs.
 * EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Author:hezhiping   Email:110476592@qq.com
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
