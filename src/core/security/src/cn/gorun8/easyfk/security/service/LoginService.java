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

/**
 * 与用户身份认证相关的功能
 *
 */
public interface LoginService {
	
	/**
	 * 根据userLoginId查找用户
	 * @param userLoginId
	 * @return
	 */
	public GenericValue findUserLogin(String userLoginId);
	
	/**
	 * 保存用户
	 * @param user
	 */
	public void saveUserLogin(GenericValue userLogin);
	
	
	/**
	 * 保存标据
	 * @param user
	 */
	public void saveTicket(String userLoginId,String ticket);
	
	
	/**
	 * 验证票据是否合法
	 * @param ticket
	 * @return
	 */
	public boolean isTicketLegal(String ticket);
	
	
	/**
	 * 根据ticket查找user
	 * @param ticket
	 * @return
	 */
	public GenericValue findUserByTicket(String ticket);
	
	 

}
