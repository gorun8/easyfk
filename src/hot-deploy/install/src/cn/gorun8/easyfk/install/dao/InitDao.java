package cn.gorun8.easyfk.install.dao;

import java.util.List;
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

/**
  * 收集easyfk-application.xml中<entity-resource>配置的资源，实始化数据库。
  * <entity-resource>中可以配置多条资源。
  * type:指明location指定的文件的数据用途类型，可以为model:表结构， seed:初始化数据，demo:示例数据。
  * <entity-resource type="model" reader-name="main" loader="main" location="entitydef/install.sql"/>
  * 与数据库相关的应在配置中加入${db.type}区分, 例如：
  * <entity-resource type="model" reader-name="main" loader="main" location="entitydef/${db.type}/install.sql"/>
  * 在运行时，系统会解${db.type}解析为正确的类型，例如使用mysql数据时，以上语句与下面这一句等同
  * <entity-resource type="model" reader-name="main" loader="main" location="entitydef/mysql/install.sql"/>
  * @author:hezhiping(110476592@qq.com)
  *
  */
public interface InitDao {
	
	/**数据库
	 * @param dbType
	 * @param sqlFile 配置文件列表
	 * @return
	 * @throws Exception
	 */
	public boolean initDB(String dbType,List<String> sqlFile) throws Exception;

	/**
	 * 检测数据库连接配置
	 * @return 正确返回success。
	 */
	public String checkConnect();
	
	 
}
