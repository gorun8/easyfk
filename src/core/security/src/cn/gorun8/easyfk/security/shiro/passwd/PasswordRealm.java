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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.gorun8.easyfk.base.util.UtilMessages;
import cn.gorun8.easyfk.entity.util.UtilEntity;
import cn.gorun8.easyfk.security.service.SecurityService;
import javolution.util.FastMap;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.security.service.UserLoginService;
import cn.gorun8.easyfk.security.shiro.AbstractAuthRealm;


/**
 * 实现基于口令的身份认证
 * @author:hezhiping(110476592@qq.com)
 *
 */
public class PasswordRealm extends AbstractAuthRealm{
	


	public UserLoginService getUserLoginService() {
		return userLoginService;
	}

	public void setUserLoginService(UserLoginService userLoginService) {
		this.userLoginService = userLoginService;
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
		Map<String,Object> context = FastMap.newInstance();
		context.put("userLoginId",userLoginId);
		Map<String,Object> result = userLoginService.findUserLogin(context);
		GenericValue userLogin =(GenericValue)result.get(UtilMessages.RESPONSE_DATA);

		if(!UtilMessages.isSuccess(result)||UtilValidate.isEmpty(userLogin))
		{
			throw new UnknownAccountException("没有找到用户账号");
		}
	 	
		List<GenericValue> li = new ArrayList<GenericValue>();
		li.add(userLogin);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(li, password.toCharArray(),getName());
	
		return info;		
	}

}
