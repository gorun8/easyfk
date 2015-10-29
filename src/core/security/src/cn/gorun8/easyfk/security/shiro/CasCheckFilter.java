/*
 *  Project:Easy Web Framework
 *
 *  Description: This project is based on much more open source projects than ever before,
 *             and can be applied to mostly web development environment.
 *  Author:hezhiping   Email:110476592@qq.com
 *  Date: 2015/10/6
 * ========================================================================================
 *
 */

package cn.gorun8.easyfk.security.shiro;

/**
 * Created by hzp on 2015/10/6.
 */

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.Generic;
import cn.gorun8.easyfk.base.util.UtilCookie;
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
            //如果用户已经登录了，则直接传回token即可
            String orgUrl = WebUtils.getCleanParam(request, Generic.ParamName.VISITURL.toString());
            if(UtilValidate.isNotEmpty(orgUrl))
            {
                //通过了身份认证，重定向到登录前的URL
                HttpServletRequest httpRequest  = WebUtils.toHttp(request);
                String rememberMe = UtilCookie.getCookieValue(httpRequest, "rememberMe");
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
