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

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilIOC;

import cn.gorun8.easyfk.base.util.collections.ResourceBundleMessageSource;
import org.apache.shiro.web.servlet.OncePerRequestFilter;

public class RequestInitFilter extends OncePerRequestFilter {
    private final  static String module = RequestInitFilter.class.getName();
    private final  static String FILTERED_FLG = "FILTERED_FLG";

    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        if(request.getAttribute(FILTERED_FLG) != null)
        {
            chain.doFilter(request, response);
            return ;
        }
        request.setAttribute(FILTERED_FLG, true);

        try {
            ResourceBundleMessageSource uiLabelMap = UtilIOC.getBean(ResourceBundleMessageSource.class);
            String labelMapName = uiLabelMap.getLabelMapName();
            request.setAttribute(labelMapName, uiLabelMap);
        }catch (Exception e){
            e.printStackTrace();
            Debug.logWarning("i18n resource not found",module);
        }

		String ctx = req.getContextPath();
		request.setAttribute("ctx", ctx);

        chain.doFilter(request, response);
    }
}
