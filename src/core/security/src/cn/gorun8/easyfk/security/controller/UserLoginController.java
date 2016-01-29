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
package cn.gorun8.easyfk.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.gorun8.easyfk.base.util.*;
import cn.gorun8.easyfk.security.utils.UtilCaptcha;
import cn.gorun8.easyfk.security.utils.UtilSecurity;
import javolution.util.FastMap;
import jxl.write.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.gorun8.easyfk.security.service.UserLoginService;
import cn.gorun8.easyfk.security.shiro.AuthExpiredException;
import cn.gorun8.easyfk.security.shiro.passwd.UsernamePasswordCaptchaToken;

import java.lang.Boolean;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * 登录验证、登录页面、登录、退出
 */
@Controller("userLoginController")
@RequestMapping("/dyn")
public class UserLoginController {
	//源URL
	private static final  String ORG_URL = "orgUrl";
	@Autowired
	private UserLoginService loginService;
	 
	/**
	 * 登录页面,转向URL，禁止访问
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login" ,method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request){
		Session session = UtilSecurity.getSession();
		String orgUrl = WebUtils.getCleanParam(request, "url");
		if(UtilValidate.isEmpty(orgUrl)){
			orgUrl =(String) session.getAttribute(ORG_URL);
			if(UtilValidate.isEmpty(orgUrl)) {
				return "redirect:common/err404";
			}
		}
		if(session != null) {
			session.setAttribute(ORG_URL, orgUrl);
		}
		return "page/login";
	} 
	
	/**
	 * 登录,支持表单和ajax。如果参数中包含AJAX=Y，表示AJAX登录
	 * @param request
	 * @param response
	 */
	@RequestMapping( value = "/login" ,method = RequestMethod.POST)
	public  String login(HttpServletRequest request
			,HttpServletResponse response){

		Map<String ,Object > context = UtilHttp.getParameterMap(request);
		Locale locale = (Locale) context.get("locale");
		Map<String ,Object > result = loginService.doAuth(context);
		if(!UtilMessages.isSuccess(result)){
			UtilMessages.errorResponse(request,result);
			return loginPage(request);
		}

		Object gbsid = (Object)result.get("gbsid");

		//通过了身份认证，重定向到登录前的URL
		Session session = UtilSecurity.getSession();
		String rememberMe = UtilSecurity.getCookieValue(request, "rememberMe");
		String orgUrl =(String) session.getAttribute(ORG_URL);

		orgUrl = orgUrl+"?gbsid="+gbsid+"&rememberMe="+rememberMe;
		request.setAttribute(ORG_URL,orgUrl);

		try {
			WebUtils.issueRedirect(request, response, orgUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}



	/**
	 * 产生验证码
	 * @param id
	 * @param response
	 */
	@RequestMapping(value="captcha.jpg/{id}")
	@ResponseBody
	public void captcha(
			@PathVariable(value = "id") String id
			,HttpServletRequest request,HttpServletResponse response)
	{
		Map<String ,Object > context = UtilHttp.getParameterMap(request);
		Locale locale = (Locale) context.get("locale");
		context.put("id",id);
		context.put("response",response);
		loginService.createCaptcha(context);

	}
	
	/**
	 * 验证验证码
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value="checkcaptcha/{id}")
	@ResponseBody
	public String checkcaptcha(
			@PathVariable(value = "id") String id
			,HttpServletRequest request,HttpServletResponse response)
	{
		Map<String ,Object > context = UtilHttp.getParameterMap(request);
		Locale locale = (Locale) context.get("locale");
		context.put("id",id);

		Map<String ,Object > result = loginService.verifyCaptch(context);
		if(UtilMessages.isSuccess(result)){
			Boolean rel = (Boolean)result.get(UtilMessages.RESPONSE_DATA);
			return  UtilMessages.successResponse(request);

		}
		return 	UtilMessages.errorResponse(request, result);
	}
}
