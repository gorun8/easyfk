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

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.gorun8.easyfk.base.util.*;
import cn.gorun8.easyfk.security.utils.UtilCaptcha;
import cn.gorun8.easyfk.security.utils.UtilJCaptcha;
import cn.gorun8.easyfk.security.utils.UtilSecurity;
import net.sf.json.JSONObject;
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


import cn.gorun8.easyfk.security.service.LoginService;
import cn.gorun8.easyfk.security.shiro.AuthExpiredException;
import cn.gorun8.easyfk.security.shiro.passwd.UsernamePasswordCaptchaToken;
 


/**
 * 登录验证、登录页面、登录、退出
 */
@Controller("userLoginController")
@RequestMapping("")
public class UserLoginController {
	//源URL
	private static final  String ORG_URL = "orgUrl";
	@Autowired
	private LoginService loginService;
	 
	/**
	 * 登录页面,不还转向URL，禁止访问
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login" ,method = RequestMethod.GET)
	public String loginPage(Model model,HttpServletRequest request,HttpSession session){
		String orgUrl = WebUtils.getCleanParam(request, "url");
		if(UtilValidate.isEmpty(orgUrl))
		{
			return "redirect:common/err404";
		}
		session.setAttribute( "url", orgUrl);
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
		String ajax = request.getParameter("AJAX");
		boolean rel = doLogin(request, response);
		if(!rel){
			return "Y".equals(ajax)? "redirect:common/json":"page/login";
		}

		//认证通过
		if("Y".equals(ajax))
		{
			return "redirect:common/json";
		}

		String orgUrl =(String)request.getAttribute(ORG_URL);
		if(UtilValidate.isEmpty(orgUrl))
		{
			return "redirect:common/err404";
		}

		try {
			WebUtils.issueRedirect(request, response, orgUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;


	}

	private boolean doLogin(HttpServletRequest request
			,HttpServletResponse response){
		AuthenticationToken token = getAuthenticationToken(request);
		Subject subject = SecurityUtils.getSubject();

		try {
			try{
				subject.login(token);
			}catch(AuthenticationException e)
			{
				throw e.getCause();
			}
			
			if (subject.isAuthenticated()) {
				//um =(SysUserMember)currentUser.getPrincipals().asList().get(0);
				// 正常状态
				subject.isPermitted("-1");// 强制授权

				//通过了身份认证，重定向到登录前的URL
				Session session = UtilSecurity.getSession();
				String rememberMe = UtilCookie.getCookieValue(request, "rememberMe");
				String orgUrl =(String) session.getAttribute( "url");

				Object commonSessionId = session.getId();
				orgUrl = orgUrl+"?gbsid="+commonSessionId+"&rememberMe="+rememberMe;
				request.setAttribute(ORG_URL,orgUrl);
				return true;

			}//end if (currentUser.isAuthenticated())
			 
		}catch(ExcessiveAttemptsException e){
			subject.logout();
			UtilMessages.saveErrors(request, "登录失败:(账号已经被停用,请联系管理员解锁)");
		}catch(AuthExpiredException e){
			String msg = e.getMessage();
			subject.logout();
			if(e.isResetPassword())
			{
				String uid = e.getUid();
				request.setAttribute("changePassword", uid);
			}
			UtilMessages.saveErrors(request, "登录失败:(" + msg + ")");
		}catch(IncorrectCredentialsException e){
			UtilMessages.saveErrors(request, "登录失败:(" + e.getMessage() + ")");
		}catch(CredentialsException e){
			UtilMessages.saveErrors(request, "登录失败:(" + e.getMessage() + ")");
		}catch(UnknownAccountException e){
			UtilMessages.saveErrors(request, "登录失败:(账号不正确)");
		}catch(LockedAccountException e){
			UtilMessages.saveErrors(request, "登录失败:(" + e.getMessage() + ")");
		}catch(Throwable e){
			subject.logout();
			UtilMessages.saveErrors(request, "登录失败:("+e.getMessage()+")");
		}

		return false;
	}
	
	private static  AuthenticationToken getAuthenticationToken(HttpServletRequest request)
	{
		String authType = request.getParameter("AUTHTYPE"); 
		String rememberMe = request.getParameter("REMEMBERME");
		String captchaValue = request.getParameter("CAPTCHA");
		String captchaId = request.getParameter("CAPTCHAID");
		
		AuthenticationToken token = null;
		boolean remember = "0".equals(rememberMe) ? false : true;
		
		if("PASSWD".equals(authType))
		{
			String username = request.getParameter("USERNAME");
	        String password = request.getParameter("PASSWORD");
			token = new UsernamePasswordCaptchaToken(username, password.toCharArray(),remember,"",captchaValue,captchaId);
		}
		/*暂时不支持基于证书的验证
		else if("X509".equals(authType)){
			String cert = user.getUserName();
			String sign = user.getUserPassword();
			token = new X509CaptchaToken(cert,sign,remember,captchaValue,signSeed);
		}*/
			
		return token;
	}
	
	/**
	 * 退出
	 * @param request
	 * @param response
	 */
	@RequestMapping("/logout")
	public String ssoLogout(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated())
		{
			subject.logout();
			subject.getSession().removeAttribute("userLogin");
		}

		return "redirect:login";
	}
 
	/**
	 * 产生验证码
	 * @param id
	 * @param session
	 * @param response
	 */
	@RequestMapping(value="captcha.jpg/{id}")
	@ResponseBody
	public void captcha(
			@PathVariable(value = "id") String id 
			,HttpSession session,
			HttpServletResponse response)
	{
		UtilCaptcha.createCaptcha(id, session, response);
	}
	
	/**
	 * 验证验证码
	 * @param id
	 * @param code
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value="checkcaptcha/{id}")
	@ResponseBody
	public String checkcaptcha(
			@PathVariable(value = "id") String id
			,@RequestParam(value="code") String code
			,HttpSession session,
			HttpServletResponse response)
	{
		boolean rel = UtilCaptcha.verifyCaptch(id, session, code);
		return String.valueOf(rel);
	}


}
