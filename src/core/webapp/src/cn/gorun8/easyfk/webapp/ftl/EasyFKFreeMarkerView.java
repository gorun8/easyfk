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
package cn.gorun8.easyfk.webapp.ftl;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import cn.gorun8.easyfk.base.util.UtilHttp;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.jsp.TaglibFactory;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.ext.servlet.HttpSessionHashModel;
import freemarker.template.TemplateScalarModel;

public class EasyFKFreeMarkerView extends FreeMarkerView {

    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        prepEasyFKRoot(request, response);
        super.render(model, request, response);
    }

    public static void prepEasyFKRoot(HttpServletRequest request, HttpServletResponse response) {
        ServletContext servletContext = (ServletContext) request.getAttribute("servletContext");
        HttpSession session = request.getSession();
        //BeansWrapper wrapper = BeansWrapper.getDefaultInstance();

        // add in the  objects
//        request.setAttribute("delegator", request.getAttribute("delegator"));
//        request.setAttribute("dispatcher", request.getAttribute("dispatcher"));
//        request.setAttribute("authz", request.getAttribute("authz"));
//        request.setAttribute("security", request.getAttribute("security"));
//        if (null == request.getAttribute("userLogin")) {
//            request.setAttribute("userLogin", session.getAttribute("userLogin"));
//        }

        // add the response object (for transforms) to the context as a BeanModel
        //request.setAttribute("response", response);

        // add the application object (for transforms) to the context as a BeanModel
        if (null == request.getAttribute("application")) {
            request.setAttribute("application", servletContext);
        }
        // add the servlet context -- this has been deprecated, and now requires servlet, do we really need it?
        //request.setAttribute("applicationAttributes", new ServletContextHashModel(servletContext, BeansWrapper.getDefaultInstance()));

        // add the session object (for transforms) to the context as a BeanModel
        //if (null == request.getAttribute("session")) {
       //     request.setAttribute("session", session);
        //}
        // add the session
        //HttpSessionHashModel httpSessionHashModel = new HttpSessionHashModel(session, wrapper);
        //Map<String, Object> httpSessionHashModel = UtilHttp.getSessionMap(request);
        //request.setAttribute("sessionAttributes", httpSessionHashModel);


        // add the request object (for transforms) to the context as a BeanModel
        if (null == request.getAttribute("httpServletRequest")) {
                request.setAttribute("httpServletRequest", request);
        }
        // add the request
        //HttpRequestHashModel httpRequestHashModel = new HttpRequestHashModel(request, wrapper);
        Map<String, Object> httpRequestHashModel = UtilHttp.getAttributeMap(request);
        request.removeAttribute("requestAttributes");
        request.setAttribute("requestAttributes", httpRequestHashModel);

        // add the request parameters -- this now uses a Map from UtilHttp
        Map<String, Object> requestParameters = UtilHttp.getParameterMap(request);
        request.removeAttribute("requestParameters");
        request.setAttribute("requestParameters", requestParameters);

        // add the TabLibFactory
        TaglibFactory JspTaglibs = new TaglibFactory(servletContext);
        request.removeAttribute("JspTaglibs");
        request.setAttribute("JspTaglibs", JspTaglibs);

    }
}
