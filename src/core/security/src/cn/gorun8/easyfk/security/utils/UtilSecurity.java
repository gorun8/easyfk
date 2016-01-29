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

package cn.gorun8.easyfk.security.utils;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.security.shiro.EasyFKWebSubject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 文件描述：安全工具
 */
public class UtilSecurity {
 
	public final static String module = UtilSecurity.class.getName();

	/**
	 * 获取Session
	 */
	public  static Session getSession(){
		return getSession(false);
	}

	/**
	 * 获取Session
	 */
	public  static Session getSession(boolean create){
		Subject currentUser = getSubject();
		return currentUser.getSession(create);
	}

	/**
	 * 保存到session中
	 * @param key
	 * @param value
	 */
	public static void setSession(String key, Object value){
		String ids = UtilProperties.getPropertyValue("security","security.sessionId.allows","");
		if(ids.indexOf(key)>=0){
			Session session = getSession();
			session.setAttribute("userLogin",value);
		}else{
			Debug.logWarning("the key ["+key+"] is not allowed for session ,please check security.sessionId.allows in security.properties",module);
		}
	}


	/**
	 * 取值Cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || name == null || name.length() == 0) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (name.equals(cookies[i].getName()))
			//    && request.getServerName().equals(cookies[i].getDomain()))
			{
				return cookies[i];
			}
		}
		return null;
	}

	/**
	 * 从Cookie中读取值
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || name == null || name.length() == 0) {
			return null;
		}
		String svrName = request.getServerName();
		for (int i = 0; i < cookies.length; i++) {
			String dn = cookies[i].getDomain();
			if (name.equals(cookies[i].getName())
					&& svrName.equals(dn))
			{
				return cookies[i].getValue();
			}
		}
		return null;
	}

	/**
	 * 从Cookie中删除值
	 * @param request
	 * @param response
	 * @param cookie
	 */
	public static void deleteCookie(HttpServletRequest request,
									HttpServletResponse response, Cookie cookie) {
		if (cookie != null) {
			cookie.setPath(getPath(request));
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	/**
	 * 将值保存到Cookie中,统一设置过期时间
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletRequest request,
								 HttpServletResponse response, String name, String value,String domain) {
		String ids = UtilProperties.getPropertyValue("security","security.cookieId.allows","");
		if(ids.indexOf(name) >= 0){
			int maxAge = UtilProperties.getPropertyAsInteger("security","security.cookieId.allows",0x278d00);
			setCookie(request, response, name, value, maxAge,domain);
		}else{
			Debug.logWarning("the name ["+name+"] is not allowed for cookie ,please check security.cookieId.allows in security.properties",module);
		}
	}

	/**
	 * 将值保存到Cookie中
	 * @param name
	 * @param value
	 * @param domain
	 */
	public static void setCookie( String name, String value,String domain){
		EasyFKWebSubject subject = (EasyFKWebSubject) SecurityUtils.getSubject();
		HttpServletRequest request = (HttpServletRequest)subject.getServletRequest();
		HttpServletResponse response = (HttpServletResponse)subject.getServletResponse();
		setCookie(request, response,name,value,domain);
	}

	/**
	 * 将值保存到Cookie中
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	private static void setCookie(HttpServletRequest request,
								  HttpServletResponse response, String name, String value, int maxAge,String domain) {
		Cookie cookie =  getCookie(request, name);
		if(cookie == null) {
			cookie =  new Cookie(name, value == null ? "" : value);
		} else {
			cookie.setValue(value);
		}

		cookie.setMaxAge(maxAge);
		//cookie.setDomain(domain);
		cookie.setPath(getPath(request));
		response.addCookie(cookie);

	}

	private static String getPath(HttpServletRequest request) {
		// String path = request.getContextPath();
		//return (path == null || path.length()==0) ? "/" : path;
		return "/";
	}

	/**
	*是否登录,可以是自动登录
	*/
	public static boolean hasAuthenticated(){
		EasyFKWebSubject currentUser = getSubject();
		return currentUser.isAuthenticated();
	}

	/**
	 *是否登录
	 */
	public static boolean hasLiveAuthenticated(){
		EasyFKWebSubject currentUser = getSubject();
		return currentUser.isAuthenticated();
	}

	/**
	 * 功能描述：获取session
	 *
	 * @return
	 */
	public static String[] getUserSession() {
		Subject currentUser = getSubject();
		String pString = currentUser.getPrincipals().toString();
		String[] strArr = pString.split(",");
		return strArr.length > 0 ? strArr : null;
	}

	/**
	 * 功能描述：获取shiro的subject对象
	 *
	 * @return
	 */
	public static EasyFKWebSubject  getSubject() {
		return (EasyFKWebSubject)SecurityUtils.getSubject();
	}

	 
	/**
	 * 功能描述：获取放在subject中的用户对象
	 *
	 * @return
	 */
	public static GenericValue getUser() {
		if (SecurityUtils.getSubject().getPrincipals() != null) {
			return (GenericValue) SecurityUtils.getSubject().getPrincipals().asList().get(0);
		}
		return null;
	}

	/**
	 * 功能描述：获取当前登录用户的ID
	 *
	 * @return
	 */
	public static String getUserLoginId() {
		return (String)getUser().get("USER_LOGIN_ID");
	}

	/**
	 * 功能描述：获取当前登录用户的用户名
	 *
	 * @return
	 */
	public static String getUserLoginName() {
		return (String)getUser().get("USER_NAME");
	}

	 
	 
	@SuppressWarnings("unchecked")
//	public static GenericValue getSessionUserMember(){
//		List<String>  list = null;
//		GenericValue userLogin = new GenericValue();
//		if (SecurityUtils.getSubject() != null && getSubject().getPrincipals() != null) {
//			list = getSubject().getPrincipals().asList();
//		}
//		if (list != null && list.size() > 0) {
//			userLogin.put("USER_LOGIN_ID", list.get(0));
//			userLogin.put("USER_NAME",list.get(1));
//		}
//		return userLogin;
//	}

	
	public static String getHashType() {
        String hashType = UtilProperties.getPropertyValue("security.properties", "password.encrypt.hash.type");

        if (UtilValidate.isEmpty(hashType)) {
            Debug.logWarning("Password encrypt hash type is not specified in security.properties, use SHA", module);
            hashType = "SHA";
        }

        return hashType;
    }

    public  static boolean checkPassword(String oldPassword, boolean useEncryption, String currentPassword) {
        boolean passwordMatches = false;
        if (oldPassword != null) {
            if (useEncryption) {
                passwordMatches = cn.gorun8.easyfk.base.crypto.HashCrypt.comparePassword(oldPassword, getHashType(), currentPassword);
            } else {
                passwordMatches = oldPassword.equals(currentPassword);
            }
        }
        if (!passwordMatches && "true".equals(UtilProperties.getPropertyValue("security.properties", "password.accept.encrypted.and.plain"))) {
            passwordMatches = currentPassword.equals(oldPassword);
        }
        return passwordMatches;
    }

	public static String cryptPassword(String password){
		return  cn.gorun8.easyfk.base.crypto.HashCrypt.cryptPassword(password);
	}

}