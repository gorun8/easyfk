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

package cn.gorun8.easyfk.base.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 操作Cookie的小工具
 */
public class UtilCookie {

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
     * 将值保存到Cookie中
     * @param request
     * @param response
     * @param name
     * @param value
     */
    public static void setCookie(HttpServletRequest request,
                                 HttpServletResponse response, String name, String value,String domain) {
        setCookie(request, response, name, value, 0x278d00,domain);
    }

    /**
     * 将值保存到Cookie中
     * @param request
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletRequest request,
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
}
