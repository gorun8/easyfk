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
package cn.gorun8.easyfk.webapp.control;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilGenerics;
import cn.gorun8.easyfk.base.util.UtilValidate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;



/**
 * ContextFilter - Restricts access to raw files and configures servlet objects.
 */
public class ContextFilter implements Filter {

    public static final String module = ContextFilter.class.getName();
    public static final String FORWARDED_FROM_SERVLET = "_FORWARDED_FROM_SERVLET_";

    protected FilterConfig config = null;
    protected boolean debug = false;


    /**
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig config) throws ServletException {
        this.config = config;


        // puts all init-parameters in ServletContext attributes for easier parameterization without code changes
        //this.putAllInitParametersInAttributes();


        // this will speed up the initial sessionId generation
        new java.security.SecureRandom().nextLong();
    }

    /**
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // we're done checking; continue on
        chain.doFilter(httpRequest, httpResponse);
    }

    /**
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {

        config = null;
    }


    protected void putAllInitParametersInAttributes() {
        Enumeration<String> initParamEnum = UtilGenerics.cast(config.getServletContext().getInitParameterNames());
        while (initParamEnum.hasMoreElements()) {
            String initParamName = initParamEnum.nextElement();
            String initParamValue = config.getServletContext().getInitParameter(initParamName);
            if (Debug.verboseOn()) Debug.logVerbose("Adding web.xml context-param to application attribute with name [" + initParamName + "] and value [" + initParamValue + "]", module);
            config.getServletContext().setAttribute(initParamName, initParamValue);
        }
        String GeronimoMultiGorun8Instances = (String) config.getServletContext().getAttribute("GeronimoMultiGorun8Instances");
        if (UtilValidate.isNotEmpty(GeronimoMultiGorun8Instances)) {
            String gorun8Home = System.getProperty("gorun8.home");
            if (GeronimoMultiGorun8Instances.equalsIgnoreCase("true") && UtilValidate.isEmpty(gorun8Home)) {
                gorun8Home = System.getProperty("gorun8.home"); // This is only used in case of Geronimo or WASCE using Gorun8 multi-instances. It allows to retrieve gorun8.home value set in JVM env
                System.out.println("Set GORUN8_HOME to - " + gorun8Home);
                System.setProperty("gorun8.home", gorun8Home);
            }
        }
    }

    protected String getServerId() {
        String serverId = (String) config.getServletContext().getAttribute("_serverId");
        if (serverId == null) {
            serverId = config.getServletContext().getInitParameter("gorun8ServerName");
            config.getServletContext().setAttribute("_serverId", serverId);
        }
        return serverId;
    }


    
    
}
