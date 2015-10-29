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

import cn.gorun8.easyfk.base.util.UtilValidate;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.PrincipalCollection;

import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.security.shiro.RetryLimitMatcher;
import cn.gorun8.easyfk.security.utils.UtilSecurity;

public class PasswordRetryLimitMatcher extends RetryLimitMatcher {

	public PasswordRetryLimitMatcher() {
		
	}
	
	public PasswordRetryLimitMatcher(CacheManager cacheManager) {
		super(cacheManager);
	}

	@Override
	public boolean checkCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info,int remts) {
	
		UsernamePasswordCaptchaToken upToken = (UsernamePasswordCaptchaToken)token;
		String password = String.valueOf(upToken.getPassword());
		String captcha = upToken.getCaptcha();
		String captchaId = upToken.getCaptchaId();
		verifyCaptcha(captcha,captchaId,remts);
		
		if(UtilValidate.isEmpty(password))
		{
			throw new IncorrectCredentialsException("请填写密码!再错"+remts+"次,账号将被锁定");
		}
		
		PrincipalCollection principalCollection = info.getPrincipals();
		GenericValue userLogin = (GenericValue)principalCollection.asList().get(0);
		if(userLogin == null){	
			throw new UnknownAccountException("没找到帐号!再错"+remts+"次,账号将被锁定");//没找到帐号
		}
		
		checkStatus(userLogin);
		//验证密码
		boolean useEncryption = "true".equals(UtilProperties.getPropertyValue("security.properties", "password.encrypt"));
		String oldPassword = userLogin.getString("CURRENT_PASSWORD");
		if(!UtilSecurity.checkPassword(oldPassword,useEncryption,password)){
			throw new IncorrectCredentialsException("密码不正确!再错"+remts+"次,账号将被锁定");//密码错误
			
		}

		return true;
	}

}
