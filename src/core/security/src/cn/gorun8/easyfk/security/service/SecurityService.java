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
package cn.gorun8.easyfk.security.service;

import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;

import java.util.List;
import java.util.Map;

/**
 * 授权相关的安全组的功能
 *
 */
public interface SecurityService {
	
	/**
	 * 根据groupId查找安全组
	 * @param context
	 * @return
	 */
	public Map<String, Object> findSecurityGroup(Map<String, ? extends Object> context);

	/**
	 * 检测安全组是否存在
	 * @param context
	 * @return
	 */
	public Map<String,  Object> checkSecurityGroupExist(Map<String, ? extends Object> context);

	/**
	 * 新建安全组
	 * @param context
	 * @return
	 */
	public Map<String,  Object> createSecurityGroup(Map<String, ? extends Object> context);

	/**
	 * 删除安全组
	 * @param context
	 * @return
	 */
	public Map<String,  Object> removeSecurityGroup(Map<String, ? extends Object> context);
	/**
	 * 保存安全组
	 * @param context
	 */
	public Map<String,  Object> saveSecurityGroup(Map<String, ? extends Object> context);

	/**
	 * 获取资源树
	 * @param context
	 */
	public Map<String,  Object> getPermissionList(Map<String, ? extends Object> context);


	/**
	 * 分页获取资源树
	 * @param context
	 */
	public Map<String,  Object> getPermissionListByPage(Map<String, ? extends Object> context);


	/**
	 * 获取组和组资源
	 * @param context
	 */
	public Map<String,  Object> getGroupAndPermission(Map<String, ? extends Object> context);


	/**
	 * 设置安全组权限
	 * @param context
	 * @return
	 */
	public Map<String,  Object> setGroupPermission(Map<String, ? extends Object> context);

	/**
	 * 删除安全组权限
	 * @param context
	 * @return
	 */
	public Map<String,  Object> removeGroupPermission(Map<String, ? extends Object> context);



	/**
	 * 获取角色类型列表
	 * @return
	 */
	public Map<String,  Object> findRoleTypes(Map<String, ? extends Object> context);

	/**
	 * 读取角色类型找出与它的上级角色类型列表
	 * @return
	 */
	public Map<String,  Object> findParentRoleTypes(Map<String, ? extends Object> context);


	/**
	 * 读取登录账号关联的权限
	 * @return
	 */
	public Map<String,  Object> findUserLoginPermissions(Map<String, ? extends Object> context);


	/**
	 * 获取会员的角色
	 */
	public Map<String,  Object> findPartyRoles(Map<String, ? extends Object> context);


	/**
	 * 描扫程序包中的PermissionDefine标签，并保存到security_permission表中。
	 */
	public Map<String,  Object> scanSecurityPermission(Map<String, ? extends Object> context);


}
