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
package cn.gorun8.easyfk.security.shiro.passwd;

import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.security.shiro.RetryLimitMatcher;
import cn.gorun8.easyfk.security.utils.UtilSecurity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

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
		String oldPassword = userLogin.getString("currentPassword");
		if(!UtilSecurity.checkPassword(oldPassword,useEncryption,password)){
			throw new IncorrectCredentialsException("密码不正确!再错"+remts+"次,账号将被锁定");//密码错误
		}
		//save current userlogin to session .
		UtilSecurity.setSession("userLogin",userLogin);
		return true;
	}

}
