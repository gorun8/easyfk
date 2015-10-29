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
package cn.gorun8.easyfk.security.shiro.passwd;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.security.service.LoginService;
import cn.gorun8.easyfk.security.shiro.AbstractAuthRealm;


/**
 * 实现基于口令的身份认证
 * @author:hezhiping(110476592@qq.com)
 *
 */
public class PasswordRealm extends AbstractAuthRealm{
	
	@Autowired
	private LoginService loginService;

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		Class cls = token.getClass();
		return (cls == UsernamePasswordCaptchaToken.class);
	}
	    
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo (AuthenticationToken arg0){
		UsernamePasswordCaptchaToken upToken = (UsernamePasswordCaptchaToken)arg0;
		String password = String.valueOf(upToken.getPassword());
		String userLoginId = upToken.getUsername();
			
		GenericValue userLogin = loginService.findUserLogin(userLoginId);
	 	if(UtilValidate.isEmpty(userLogin))
		{
			throw new UnknownAccountException("错误的用户账号");
		}
	 	
		List<GenericValue> li = new ArrayList<GenericValue>();
		li.add(userLogin);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(li, password.toCharArray(),getName());
	
		return info;		
	}  
	
	 
}  
