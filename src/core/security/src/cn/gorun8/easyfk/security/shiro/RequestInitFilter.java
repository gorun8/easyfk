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
