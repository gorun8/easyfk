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
import cn.gorun8.easyfk.entity.dao.ReadDaoBase;
import cn.gorun8.easyfk.entity.datasource.ReadSqlSessionTemplate;
import cn.gorun8.easyfk.security.dao.SecurityReadDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 访问用户数据
 * @author:hezhiping(110476592@qq.com)
 *
 */
@Repository
public   class SecurityReadDaoImpl extends SecurityReadDao {
	private static final String module = SecurityReadDaoImpl.class.getName();

	@Override
	public List<GenericValue> findSecurityGroup(@Param("params") GenericValue params) throws GenericEntityException {
		return this.findList("findSecurityGroup",params);
	}

	@Override
	public  List<GenericValue> findSecurityPermission(@Param("params") GenericValue params)throws GenericEntityException{
		return this.findList("findSecurityPermission",params);
	}

	@Override
	public  List<GenericValue> findGroupAndPermission(@Param("params") GenericValue params)throws GenericEntityException{
		return this.findList("findGroupAndPermission",params);
	}

	@Override
	public  List<GenericValue> findUserLoginSecurityGroup(@Param("params") GenericValue params)throws GenericEntityException{
		return this.findList("findUserLoginSecurityGroup",params);
	}

	@Override
	public  List<GenericValue> findRoleTypes()throws GenericEntityException{
		GenericValue params = new GenericValue();
		return this.findList("findRoleTypes",params);
	}

	@Override
	public  List<GenericValue> findParentRoleTypes(@Param("params") GenericValue params)throws GenericEntityException{
		return this.findList("findParentRoleTypes",params);
	}

	@Override
	public  List<GenericValue> findUserLoginPermissions(@Param("params") GenericValue params)throws GenericEntityException{
		return this.findList("findUserLoginPermissions",params);
	}

	@Override
	public  List<GenericValue> findPartyRoles(@Param("params") GenericValue params)throws GenericEntityException{
		return this.findList("findPartyRoles",params);
	}

}
