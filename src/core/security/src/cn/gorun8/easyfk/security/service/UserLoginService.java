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

import cn.gorun8.easyfk.entity.GenericValue;

import java.util.Map;

/**
 * 与用户身份认证相关的功能
 *
 */
public interface UserLoginService {
	
	/**
	 * 根据userLoginId查找登录信息
	 * @param context
	 * @return
	 */
	public Map<String, Object> findUserLogin(Map<String, ? extends Object> context);

	/**
	 * 根据partyId查找登录信息
	 * @param context
	 * @return
	 */
	public Map<String, Object> findPartyUserLogin(Map<String, ? extends Object> context);
	

	/**
	 * 检测账号是否存在
	 * @param context
	 * @return
	 */
	public Map<String,  Object> checkUserLoginExist(Map<String, ? extends Object> context);

	/**
	 * 新建账号
	 * @param context
	 * @return
	 */
	public Map<String,  Object> createUserLogin(Map<String, ? extends Object> context);

	/**
	 * 删除账号
	 * @param context
	 * @return
	 */
	public Map<String,  Object> removeUserLogin(Map<String, ? extends Object> context);
	/**
	 * 保存用户
	 * @param context
	 */
	public Map<String,  Object> saveUserLogin(Map<String, ? extends Object> context);

	/**
	 * 更改使用状态
	 * @param context
	 * @return
	 */
	public Map<String,  Object> changeUserLoginStatus(Map<String,? extends Object> context) ;


	/**
	 * 创建验证码
	 * @param context
	 * @return
	 */
	public Map<String,  Object> createCaptcha(Map<String,? extends Object> context) ;

	/**
	 * 验证验证码
	 * @param context
	 * @return
	 */
	public Map<String,  Object> verifyCaptch(Map<String,? extends Object> context) ;


	/**
	 * 验证身份
	 * @param context
	 * @return
	 */
	public Map<String,  Object> doAuth(Map<String,? extends Object> context) ;




}
