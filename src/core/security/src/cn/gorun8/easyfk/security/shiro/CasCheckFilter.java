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

/**
 * Created by hzp on 2015/10/6.
 */

import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.security.utils.UtilSecurity;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**用于跨域共享session,那个应用需要与登录应用共享session，
 * 就开放此filter，并且应该是拦截所有请求，
 * 至少是拦截所有需要保护的资源
 */
public class CasCheckFilter extends AdviceFilter {
    public static final String module = CasCheckFilter.class.getName();

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response)throws Exception {
        if(UtilSecurity.hasAuthenticated() ){
            HttpServletRequest httpRequest  = WebUtils.toHttp(request);
            //如果用户已经登录了，则直接传回token即可
            String orgUrl = WebUtils.getCleanParam(request,  "url");
            if(UtilValidate.isEmpty(orgUrl))
            {
                orgUrl = UtilSecurity.getCookieValue(httpRequest,"url");
            }

            if(UtilValidate.isEmpty(orgUrl)){
                //通过了身份认证，重定向到登录前的URL
                String rememberMe = UtilSecurity.getCookieValue(httpRequest, "rememberMe");
                Session session  = UtilSecurity.getSession();
                Object commonSessionId = session.getId();
                orgUrl = orgUrl+"?gbsid="+commonSessionId+"&rememberMe="+rememberMe;

                try {
                    WebUtils.issueRedirect(request, response, orgUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }


        }
        return true;
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response)throws Exception {
        super.postHandle(request, response);
    }
}
