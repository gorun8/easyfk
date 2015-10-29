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
package cn.gorun8.easyfk.security.shiro;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.OncePerRequestFilter;

import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericValue;

public class RequestInitFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
    	
//    	Subject subject = SecurityUtils.getSubject();
//		if (!subject.isAuthenticated()){
//			System.out.print("no login");
//		}
//    	Session session =subject.getSession();
//    	Object userLogin = session.getAttribute("userLogin");
//    	if(UtilValidate.isEmpty(userLogin)){
//    		PrincipalCollection principalCollection = subject.getPrincipals();
//    		if(UtilValidate.isNotEmpty(principalCollection)){
//    			userLogin= (GenericValue)principalCollection.asList().get(0);
//    		}
//        	if(UtilValidate.isNotEmpty(userLogin)){
//        		session.setAttribute("userLogin", userLogin);
//        	}
//        }
//
//		if(UtilValidate.isNotEmpty(userLogin)){
//			request.setAttribute("userLogin", userLogin);
//		}

		HttpServletRequest req = (HttpServletRequest)request;
		String ctx = req.getContextPath();
		request.setAttribute("ctx", ctx);
        chain.doFilter(request, response);
    }
}
