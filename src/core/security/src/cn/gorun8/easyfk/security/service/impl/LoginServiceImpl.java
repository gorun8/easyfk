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
package cn.gorun8.easyfk.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gorun8.easyfk.base.crypto.HashCrypt;
import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.security.dao.UserLoginDao;
import cn.gorun8.easyfk.security.service.LoginService;
 

/**
 * 
 * 服务接口实现类
 *
 */

@Service
public class LoginServiceImpl implements LoginService{
	 public static final String module = LoginServiceImpl.class.getName();

	@Autowired
	private UserLoginDao userLoginDao;
	
	@Override
	public GenericValue findUserLogin(String userLoginId){
		return userLoginDao.findUserLogin(userLoginId);
	}

	@Override
	public void saveUserLogin(GenericValue userLogin) {
		userLoginDao.saveUserLogin(userLogin);
	}

	@Override
	public boolean isTicketLegal(String ticket) {
		GenericValue user = userLoginDao.findUserByTicket(ticket);
		if(UtilValidate.isNotEmpty(user)){
			return true;
		}
		return false;
	}

	@Override
	public GenericValue findUserByTicket(String ticket) {
		return userLoginDao.findUserByTicket(ticket);
	}

	@Override
	public void saveTicket(String userLoginId,String ticket) {
		userLoginDao.saveTicket(userLoginId, ticket);
	}
	
	
	 
}
