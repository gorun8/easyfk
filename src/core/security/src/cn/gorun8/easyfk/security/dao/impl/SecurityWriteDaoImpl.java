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
package cn.gorun8.easyfk.security.dao.impl;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilIOC;
import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.dao.WriteDaoBase;
import cn.gorun8.easyfk.entity.datasource.WriteSqlSessionTemplate;
import cn.gorun8.easyfk.security.dao.SecurityWriteDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 访问用户数据
 * @author:hezhiping(110476592@qq.com)
 *
 */
@Repository
public  class SecurityWriteDaoImpl extends SecurityWriteDao {
	private static final String module = SecurityWriteDaoImpl.class.getName();

	@Override
	public void createSecurityGroup(@Param("securityGroup") GenericValue securityGroup) throws GenericEntityException {
		this.create("createSecurityGroup",securityGroup);
	}

	@Override
	public void saveSecurityGroup(@Param("securityGroup") GenericValue securityGroup) throws GenericEntityException {
		this.store("saveSecurityGroup",securityGroup);
	}

	@Override
	public void removeSecurityGroup(@Param("securityGroup") GenericValue securityGroup) throws GenericEntityException {
		this.removeValue("removeSecurityGroup",securityGroup);
	}

	public   void createGroupPermission(@Param("groupPermission") GenericValue groupPermission)throws GenericEntityException{
		this.create("createGroupPermission",groupPermission);
	}

	public   void removeGroupPermission(@Param("groupPermission") GenericValue groupPermission)throws GenericEntityException {
		this.removeValue("removeGroupPermission",groupPermission);
	}


	/**
	 * 创建会员组登录账号
	 * @param userLoginGroup
	 */
	public   void createUserLoginSecurityGroup(@Param("userLoginGroup") GenericValue userLoginGroup)throws GenericEntityException{
		this.create("createUserLoginSecurityGroup",userLoginGroup);
	}

	/**
	 * 停用会员组登录账号
	 * @param userLoginGroup
	 */
	public   void stopUserLoginSecurityGroup(@Param("userLoginGroup") GenericValue userLoginGroup)throws GenericEntityException {
		this.store("stopUserLoginSecurityGroup",userLoginGroup);
	}


	/**
	 * 创建权限
	 * @param securityPermission
	 */
	public  void createSecurityPermission(@Param("securityPermission") GenericValue securityPermission)throws GenericEntityException{
		this.create("createSecurityPermission",securityPermission);
	}

	/**
	 * 更新权限
	 * @param securityPermission
	 */
	public  void saveSecurityPermission(@Param("securityPermission") GenericValue securityPermission)throws GenericEntityException {
		this.store("saveSecurityPermission", securityPermission);
	}

	/**
	 * 删除权限
	 * @param securityPermission
	 */
	public  void removeSecurityPermission(@Param("securityPermission") GenericValue securityPermission)throws GenericEntityException {
		this.removeValue("removeSecurityPermission", securityPermission);
	}
}
