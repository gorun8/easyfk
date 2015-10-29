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
package cn.gorun8.easyfk.security.service;

import cn.gorun8.easyfk.entity.GenericValue;

/**
 * 与用户身份认证相关的功能
 *
 */
public interface LoginService {
	
	/**
	 * 根据userLoginId查找用户
	 * @param name
	 * @param password
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
