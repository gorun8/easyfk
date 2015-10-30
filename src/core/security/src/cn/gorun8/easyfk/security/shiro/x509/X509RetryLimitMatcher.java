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
package cn.gorun8.easyfk.security.shiro.x509;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.PrincipalCollection;

import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.security.shiro.RetryLimitMatcher;

public class X509RetryLimitMatcher extends RetryLimitMatcher {

	 
	public X509RetryLimitMatcher(CacheManager cacheManager) {
		super(cacheManager);
	}
	
	public X509RetryLimitMatcher() {
	}
	

	@Override
	public boolean checkCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info,int remts) {
		X509CaptchaToken upToken = (X509CaptchaToken) token;
		String captcha = upToken.getCaptcha();
		String cert = upToken.getCert();
		String sign = upToken.getSign();
		String signSeed = upToken.getSeed();
		String certPath = upToken.getCertPath();
		String captchaId = upToken.getCaptchaId();
		verifyCaptcha(captcha,captchaId,remts);


		if(StringUtils.isBlank(sign)||StringUtils.isBlank(cert))
		{
			throw new IncorrectCredentialsException("证书不正确!再错"+remts+"次,账号将被锁定");
		}
		if (StringUtils.isBlank(certPath) ) {
			throw new IncorrectCredentialsException("用户没有绑定证书!再错"+remts+"次,账号将被锁定");
		}
	 	
		PrincipalCollection principalCollection = info.getPrincipals();
		GenericValue userLogin = (GenericValue)principalCollection.asList().get(0);
		if(userLogin == null){	
			throw new UnknownAccountException("没找到帐号!再错"+remts+"次,账号将被锁定");//没找到帐号
		}
		
		checkStatus(userLogin);
		throw new IncorrectCredentialsException("证书签证没有实现完整，暂时不可用");
		
	}
	
	 

	 
}
