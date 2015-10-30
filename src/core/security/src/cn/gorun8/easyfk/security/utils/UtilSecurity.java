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

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericValue;

 

/**
 * 文件描述：安全工具
 */
public class UtilSecurity {
 
	public final static String module = UtilSecurity.class.getName();

	/**
	 * 获取Session
	 */
	public  static Session getSession(){
		Subject currentUser = getSubject();
		return currentUser.getSession();
	}


	/**
	 *
	 */
	public static boolean hasAuthenticated(){
		Subject currentUser = getSubject();
		return  currentUser.isAuthenticated();
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
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
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
	public static GenericValue getSessionUserMember(){
		List<String>  list = null;
		GenericValue userLogin = new GenericValue();
		if (SecurityUtils.getSubject() != null && getSubject().getPrincipals() != null) {
			list = getSubject().getPrincipals().asList();
		}
		if (list != null && list.size() > 0) {
			userLogin.put("USER_LOGIN_ID", list.get(0));
			userLogin.put("USER_NAME",list.get(1));
		}
		return userLogin;
	}

	
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
 
	
}