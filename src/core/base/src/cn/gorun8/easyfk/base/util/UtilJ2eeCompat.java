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

import javax.servlet.ServletContext;

/**
 * Misc J2EE Compatibility Utility Functions
 *
 */
public class UtilJ2eeCompat {

    public static final String module = UtilJ2eeCompat.class.getName();

    public static final String TOMCAT = "apache tomcat";
    public static final String ORION = "orion";
    public static final String RESIN = "resin";
    public static final String REX_IP = "tradecity";
    public static final String OC4J = "oracle";
    public static final String JRUN = "jrun";
    public static final String JETTY = "jetty";
    public static final String WEBSPHERE = "websphere";

    protected static Boolean doFlushOnRenderValue = null;
    protected static Boolean useOutputStreamNotWriterValue = null;
    protected static Boolean useNestedJspException = null;

    public static boolean doFlushOnRender(ServletContext context) {
        initCompatibilityOptions(context);
        return doFlushOnRenderValue.booleanValue();
    }

    public static boolean useOutputStreamNotWriter(ServletContext context) {
        initCompatibilityOptions(context);
        return useOutputStreamNotWriterValue.booleanValue();
    }

    public static boolean useNestedJspException(ServletContext context) {
        initCompatibilityOptions(context);
        return useNestedJspException.booleanValue();
    }

    protected static void initCompatibilityOptions(ServletContext context) {
        // this check to see if we should flush is done because on most servers this
        // will just slow things down and not solve any problems, but on Tomcat, Orion, etc it is necessary
        if (useOutputStreamNotWriterValue == null || doFlushOnRenderValue == null) {
            boolean doflush = true;
            boolean usestream = true;
            boolean nestjspexception = true;
            // if context is null use an empty string here which will cause the defaults to be used
            String serverInfo = context == null ? "" : context.getServerInfo().toLowerCase();

            Debug.logInfo("serverInfo: " + serverInfo, module);

            if (serverInfo.indexOf(RESIN) >= 0) {
                Debug.logImportant("Resin detected, disabling the flush on the region render from PageContext for better performance", module);
                doflush = false;
            } else if (serverInfo.indexOf(REX_IP) >= 0) {
                Debug.logImportant("Trade City RexIP detected, using response.getWriter to write text out instead of response.getOutputStream", module);
                usestream = false;
            } else if (serverInfo.indexOf(TOMCAT) >= 0) {
                Debug.logImportant("Apache Tomcat detected, using response.getWriter to write text out instead of response.getOutputStream", module);
                usestream = false;
            } else if (serverInfo.indexOf(JRUN) >= 0) {
                Debug.logImportant("JRun detected, using response.getWriter to write text out instead of response.getOutputStream", module);
                usestream = false;
            } else if (serverInfo.indexOf(JETTY) >= 0) {
                Debug.logImportant("Jetty detected, using response.getWriter to write text out instead of response.getOutputStream", module);
                usestream = false;
            } else if (serverInfo.indexOf(ORION) >= 0) {
                Debug.logImportant("Orion detected, using response.getWriter to write text out instead of response.getOutputStream", module);
                usestream = false;
                Debug.logImportant("Orion detected, using non-nested JspException", module);
                nestjspexception = false;
            } else if (serverInfo.indexOf(WEBSPHERE) >= 0) {
                Debug.logImportant("IBM Websphere Application Server detected, using response.getWriter to write text out instead of response.getOutputStream", module);
                usestream = false;
            }

            doFlushOnRenderValue = Boolean.valueOf(doflush);
            useOutputStreamNotWriterValue = Boolean.valueOf(usestream);
            useNestedJspException = Boolean.valueOf(nestjspexception);
        }
    }
}
