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

import cn.gorun8.easyfk.base.util.UtilHttp;
import cn.gorun8.easyfk.base.util.UtilMessages;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.security.dao.UserLoginReadDao;
import cn.gorun8.easyfk.security.dao.UserLoginWriteDao;
import cn.gorun8.easyfk.security.service.UserLoginService;
import cn.gorun8.easyfk.security.shiro.AuthExpiredException;
import cn.gorun8.easyfk.security.shiro.passwd.UsernamePasswordCaptchaToken;
import cn.gorun8.easyfk.security.utils.UtilCaptcha;
import cn.gorun8.easyfk.security.utils.UtilSecurity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * 
 * 身份认证服务接口实现类
 *
 */

@Service
public class UserLoginServiceImpl implements UserLoginService {
	 public static final String module = UserLoginServiceImpl.class.getName();
	public static final String resource = "SecurityUiLabels";
	public static final String resourceError = "SecurityErrorUiLabels";
	@Autowired
	private UserLoginReadDao userLoginReadDao;

	@Autowired
	private UserLoginWriteDao userLoginWriteDao;
	
	@Override
	public Map<String,  Object> findUserLogin(Map<String, ? extends Object> context){
		String userLoginId =(String)context.get("userLoginId");
		Locale locale = (Locale) context.get("locale");

		if(UtilValidate.isEmpty(userLoginId)){
			return UtilMessages.returnParamError(locale, "userLoginId");
		}

		GenericValue gv = new GenericValue();
		gv.setString("userLoginId", userLoginId);
		try {
			gv = userLoginReadDao.findOne(gv);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"userlogin.find.error", locale));
		}

		if(UtilValidate.isEmpty(gv)){
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"userlogin.not_found", locale));
		}
		return  UtilMessages.returnSuccessWithData(gv);
	}

	public Map<String, Object> findPartyUserLogin(Map<String, ? extends Object> context){
		String currnetPartyId =(String)context.get("currnetPartyId");
		Locale locale = (Locale) context.get("locale");

		if(UtilValidate.isEmpty(currnetPartyId)){
			return UtilMessages.returnParamError(locale, "currnetPartyId");
		}

		GenericValue gv = new GenericValue();
		gv.setString("partyId", currnetPartyId);
		List<GenericValue> valueList = null;
		try {
			valueList = userLoginReadDao.findUserLoginByPartyId(gv);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"userlogin.find.error", locale));
		}

		return  UtilMessages.returnSuccessWithData(valueList);
	}
	/**
	 * 检测账号是否存在
	 * @param context
	 * @return
	 */
	public Map<String,  Object> checkUserLoginExist(Map<String, ? extends Object> context){
		Locale locale = (Locale) context.get("locale");
		GenericValue param =  new GenericValue();
		String userLoginId = (String) context.get("userLoginId");
		if(UtilValidate.isEmpty(userLoginId)){
			return UtilMessages.returnParamError(locale, "userLoginId");
		}

		param.setString("userLoginId",userLoginId);
		try {
			Long count = userLoginReadDao.findCount("checkUserLoginExist",param);
			return UtilMessages.returnSuccessWithData(count > 0);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"userlogin.find.error", locale));
		}
	}

	/**
	 * 新建账号
	 * @param context
	 * @return
	 */
	public Map<String,  Object> createUserLogin(Map<String, ? extends Object> context){
		Locale locale = (Locale) context.get("locale");
		String userLoginId = (String) context.get("userLoginId");
		Boolean successIfExist = (Boolean)context.get("successIfExist");

		if(UtilValidate.isEmpty(userLoginId)){
			return UtilMessages.returnParamError(locale, "userLoginId");
		}
		String currentPassword = (String ) context.get("currentPassword");
		if(UtilValidate.isEmpty(currentPassword)){
			return UtilMessages.returnParamError(locale, "currentPassword");
		}

		String partyId = (String) context.get("partyId");
		if(UtilValidate.isEmpty(partyId)){
			return UtilMessages.returnParamError(locale, "partyId");
		}

		Map<String,  Object>  result = checkUserLoginExist(context);
		if(!UtilMessages.isSuccess(result)){
			if(!successIfExist) {
				return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
						"userlogin.checkexist.error", locale));
			}else{
				return UtilMessages.returnSuccess();
			}
		}

		Boolean exist =(Boolean)result.get(UtilMessages.RESPONSE_DATA);
		if(exist){
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"userlogin.exist.error", locale));
		}

		GenericValue userLogin =  GenericValue.fromMap(context,true);
		currentPassword = UtilSecurity.cryptPassword(currentPassword);
		userLogin.setString("currentPassword",currentPassword);
		try {
			 userLoginWriteDao.create("createUserLogin",userLogin);
			return UtilMessages.returnSuccess();
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"userlogin.create.error", locale));
		}

	}

	/**
	 * 删除账号
	 * @param context
	 * @return
	 */
	public Map<String,  Object> removeUserLogin(Map<String, ? extends Object> context){
		Locale locale = (Locale) context.get("locale");
		GenericValue param =  GenericValue.fromMap(context,false);
		String userLoginId = (String) context.get("userLoginId");
		if(UtilValidate.isEmpty(userLoginId)){
			return UtilMessages.returnParamError(locale, "userLoginId");
		}

		String partyId = (String) context.get("partyId");
		if(UtilValidate.isEmpty(partyId)){
			return UtilMessages.returnParamError(locale, "partyId");
		}

		try {
			userLoginWriteDao.removeValue("removeUserLogin",param);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"userlogin.remove.error", locale));
		}
		return UtilMessages.returnSuccess();
	}

	@Override
	public Map<String,  Object> saveUserLogin(Map<String,? extends Object> context) {
		Locale locale = (Locale) context.get("locale");

		String userLoginId = (String) context.get("userLoginId");
		if(UtilValidate.isEmpty(userLoginId)){
			return UtilMessages.returnParamError(locale, "userLoginId");
		}
		String currentPassword = (String ) context.get("currentPassword");
		if(UtilValidate.isEmpty(currentPassword)){
			return UtilMessages.returnParamError(locale, "currentPassword");
		}

		GenericValue userLogin =  GenericValue.fromMap(context,false);
		currentPassword = UtilSecurity.cryptPassword(currentPassword);
		userLogin.setString("currentPassword",currentPassword);
		try {
			userLoginWriteDao.store("saveUserLogin", userLogin);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
						"userlogin.update.error", locale));
		}
		return UtilMessages.returnSuccess();
	}

	@Override
	public Map<String,  Object> changeUserLoginStatus(Map<String,? extends Object> context) {
		Locale locale = (Locale) context.get("locale");
		String userLoginId = (String) context.get("userLoginId");
		if(UtilValidate.isEmpty(userLoginId)){
			return UtilMessages.returnParamError(locale, "userLoginId");
		}

		String partyId = (String) context.get("partyId");
		if(UtilValidate.isEmpty(partyId)){
			return UtilMessages.returnParamError(locale, "partyId");
		}
		String enabled  = (String) context.get("enabled");
		if(UtilValidate.isEmpty(enabled)){
			return UtilMessages.returnParamError(locale, "enabled");
		}
		GenericValue userLogin =  GenericValue.fromMap(context,false);

		try {
			userLoginWriteDao.store("saveUserLogin", userLogin);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"userlogin.changestatus.error", locale));
		}
		return UtilMessages.returnSuccess();
	}


	/**
	 * 创建验证码
	 * @param context
	 * @return
	 */
	public Map<String,  Object> createCaptcha(Map<String,? extends Object> context) {
		Locale locale = (Locale) context.get("locale");
		String id = (String) context.get("id");
		HttpServletResponse response = (HttpServletResponse) context.get("response");

		if(UtilValidate.isEmpty(id)){
			return UtilMessages.returnParamError(locale, "id");
		}

		Session session = UtilSecurity.getSession();
		UtilCaptcha.createCaptcha(id, session, response);
		return UtilMessages.returnSuccess();
	}

	/**
	 * 验证验证码,
	 * @param context
	 * @return，成功返回true,失败false
	 */
	public Map<String,  Object> verifyCaptch(Map<String,? extends Object> context) {
		Locale locale = (Locale) context.get("locale");
		String id = (String) context.get("id");
		if(UtilValidate.isEmpty(id)){
			return UtilMessages.returnParamError(locale, "id");
		}
		String code = (String) context.get("code");
		if(UtilValidate.isEmpty(code)){
			return UtilMessages.returnParamError(locale, "code");
		}

		Session session = UtilSecurity.getSession();
		boolean rel = UtilCaptcha.verifyCaptch(id, session, code);
		return UtilMessages.returnSuccessWithData(Boolean.valueOf(rel));
	}


	/**
	 * 根据用户名和密码验证用户身份
	 * @param context
	 * @return  认证通过:{"responseMessage":"success","gbsid":"0112dff7-93a9-4d2f-a058-eee81c75b21c"}，
	 *                 gbsid作为票据，应在以后每次访问服务时作为http头传入，应用会根据gbsid找到其对应的会话。
	 *          认证失败：{"responseMessage":"error","errorMessage":"xxx"}
	 */
	public Map<String,  Object> doAuth(Map<String,? extends Object> context) {
		Locale locale = (Locale) context.get("locale");
 		String captchaValue =  (String)context.get("captcha");
		String captchaId =  (String)context.get("captchaid");
		String username = (String)context.get("username");
		String password =(String)context.get("password");
		if(UtilValidate.isEmpty(username)){
			return UtilMessages.returnParamError(locale, "username");
		}

		if(UtilValidate.isEmpty(password)){
			return UtilMessages.returnParamError(locale, "password");
		}

		AuthenticationToken token = new UsernamePasswordCaptchaToken(username, password.toCharArray(),true,"",captchaValue,captchaId);
		Subject subject = SecurityUtils.getSubject();

		try {
			try{
				subject.login(token);
			}catch(AuthenticationException e)
			{
				throw e.getCause();
			}

			if (subject.isAuthenticated()) {// 强制授权
				subject.isPermitted("-1");
				Session session = UtilSecurity.getSession();
				Object ticket = session.getId();
				return UtilMessages.returnSuccessWithData("gbsid",ticket);
			}

		}catch(ExcessiveAttemptsException e){
			subject.logout();
			return UtilMessages.returnError("登录失败:(账号已经被停用,请联系管理员解锁)");
		}catch(AuthExpiredException e){
			String msg = e.getMessage();
			subject.logout();
			return UtilMessages.returnError("登录失败:(" + msg + ")");
		}catch(IncorrectCredentialsException e){
			return UtilMessages.returnError("登录失败:(" + e.getMessage() + ")");
		}catch(CredentialsException e){
			return UtilMessages.returnError("登录失败:(" + e.getMessage() + ")");
		}catch(UnknownAccountException e){
			return UtilMessages.returnError("登录失败:(账号不正确)");
		}catch(LockedAccountException e){
			return UtilMessages.returnError("登录失败:(" + e.getMessage() + ")");
		}catch(Throwable e){
			subject.logout();
			return UtilMessages.returnError("登录失败:("+e.getMessage()+")");
		}
		return UtilMessages.returnError("登录失败");
	}
}
