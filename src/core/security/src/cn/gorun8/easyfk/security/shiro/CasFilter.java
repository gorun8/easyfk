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
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import cn.gorun8.easyfk.base.util.*;
import cn.gorun8.easyfk.security.utils.UtilSecurity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;

/**用于跨域共享session,哪个应用需要与登录应用共享session，
 * 就开放此filter，并且应该是拦截所有请求，
 * 至少是拦截所有需要保护的资源
 */
public class CasFilter extends AdviceFilter {
    public static final String module = CasFilter.class.getName();
    private String loginURL;  //登录的URL
    private String domain;   //域名

    public  CasFilter(){

    }
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response)throws Exception {
        Session session  = UtilSecurity.getSession();
        if(UtilSecurity.hasAuthenticated() ){
            //当用户已经登录或者从session中取得的hasSyn为true，说明已经同步session，不需要再重定向
            return true;
        }

        String gbsid = WebUtils.getCleanParam(request, "gbsid");
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String url = httpRequest.getRequestURL().toString();

        //如果gbsid不为空，说明是通过了认证,重定向回来的，将从认证服务器拿到的sessionId写回到自己域名下。
        //这样就可以拿到在认证服务器中产生的session了
        if(UtilValidate.isNotEmpty(gbsid)){

            UtilCookie.setCookie(httpRequest ,WebUtils.toHttp(response), "gbsid", gbsid,domain);
            String rememberMe  = WebUtils.getCleanParam(request, "rememberMe");
            UtilCookie.setCookie(httpRequest ,WebUtils.toHttp(response), "rememberMe", rememberMe,domain);

            WebUtils.issueRedirect(request, response, url);
            Debug.logInfo("redirect : " + url, module);
            return false;
        }

        //重写向到认证服务器进行认证
        String uri = loginURL + "?"+ Generic.ParamName.VISITURL.toString()+"=" + url;
        WebUtils.issueRedirect(request, response, uri);
        Debug.logInfo("redirect : " + uri,module);
        return false;
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response)throws Exception {
        super.postHandle(request, response);
    }


    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setLoginURL(String loginURL) {
        this.loginURL = loginURL;
    }
}
