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
package cn.gorun8.easyfk.security.shiro.x509;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;

import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.security.shiro.AbstractAuthRealm;

/**
 * 实现证书登录登录及授权
 * 注：暂不可用
 */
public class X509Realm extends AbstractAuthRealm {

	@Override 
	public boolean supports(AuthenticationToken token) {
		Class cls = token.getClass();
		return (cls == X509CaptchaToken.class);
	}
	 
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo (AuthenticationToken arg0){
		X509CaptchaToken upToken = (X509CaptchaToken) arg0;
		String cert = upToken.getCert();
		String sign = upToken.getSign();
		
		List<GenericValue> li = new ArrayList<GenericValue>();
		GenericValue us = getUserByCert(cert,upToken);
		if(us != null){
			li.add(us);
		}else{//没有找到用户
			throw new UnknownAccountException("帐号不正确!");
		}
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(li,sign.toCharArray(), getName());
		
		return info;		
	}  
	
	 

	public GenericValue getUserByCert(String cert,X509CaptchaToken token){
	
//		byte[] certb = Base64.decode(cert.getBytes());
//		token.setUsername(userName);
// 		token.setCertPath(certPath);
//		return user;
		return null;
	}

	

	 
}
