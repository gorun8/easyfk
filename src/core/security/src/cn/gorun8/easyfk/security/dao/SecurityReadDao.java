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
package cn.gorun8.easyfk.security.dao;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilIOC;
import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.dao.ReadDaoBase;
import cn.gorun8.easyfk.entity.datasource.ReadSqlSessionTemplate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 访问用户数据
 * @author:hezhiping(110476592@qq.com)
 *
 */

public abstract class SecurityReadDao extends ReadDaoBase {
	private static final String module = SecurityReadDao.class.getName();

	public SecurityReadDao(){
		super("cn.gorun8.easyfk.security.dao.SecurityReadDao");
	}

	public ReadSqlSessionTemplate getSqlSessionTemplate(){
		ReadSqlSessionTemplate readSqlSessionTemplate = UtilIOC.getBean("partyReadSqlSession");
		if(readSqlSessionTemplate == null){
			Debug.logError("partyReadSqlSession not found", module);
			return null;
		}
		return readSqlSessionTemplate;
	}

	public abstract List<GenericValue> findSecurityGroup(@Param("params") GenericValue params)throws GenericEntityException;

	/**
	 * 获取权限资源
	 * @param params
	 * @return
	 * @throws GenericEntityException
	 */
	public abstract List<GenericValue> findSecurityPermission(@Param("params") GenericValue params)throws GenericEntityException;


	/**
	 * 获取组和组的权限资源
	 * @param params
	 * @return
	 * @throws GenericEntityException
	 */
	public abstract List<GenericValue> findGroupAndPermission(@Param("params") GenericValue params)throws GenericEntityException;

	/**
	 * 获取安全组关联的登录账号
	 * @param params
	 * @return
	 * @throws GenericEntityException
	 */
	public abstract List<GenericValue> findUserLoginSecurityGroup(@Param("params") GenericValue params)throws GenericEntityException;


	/**
	 * 获取角色类型列表
	 * @return
	 * @throws GenericEntityException
	 */
	public abstract List<GenericValue> findRoleTypes()throws GenericEntityException;

	/**
	 * 获取角色类型找出与它的上级角色类型列表
	 * @return
	 * @throws GenericEntityException
	 */
	public abstract List<GenericValue> findParentRoleTypes(@Param("params") GenericValue params)throws GenericEntityException;

	/**
	 * 读取登录账号的权限列表
	 * @return
	 * @throws GenericEntityException
	 */
	public abstract List<GenericValue> findUserLoginPermissions(@Param("params") GenericValue params)throws GenericEntityException;

	/**
	 * 获取会员的角色
	 * @return
	 * @throws GenericEntityException
	 */
	public abstract List<GenericValue> findPartyRoles(@Param("params") GenericValue params)throws GenericEntityException;

}
