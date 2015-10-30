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

package cn.gorun8.easyfk.common.controller;

import cn.gorun8.easyfk.base.util.UtilHttp;
import cn.gorun8.easyfk.base.util.UtilMessages;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 通用的Controller,该Controller中的方法将自动映射到其它组件，通过应用组件的上下文件即可访问。
 */
@Controller("commonController")
@RequestMapping("/common")
public class CommonController {
    /**
     *  从request中获取对象，产生Json响应
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "json")
    @ResponseBody
    public  String jsonResponseFromRequestAttributes(HttpServletRequest request, HttpServletResponse response) {
        // pull out the service response from the request attribute
        Map<String, Object> attrMap = UtilHttp.getJSONAttributeMap(request);
        //create a JSON Object for return
        JSONObject json = JSONObject.fromObject(attrMap);
        return json.toString();
    }


    /**
     *  注销登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "logoutajax")
    @ResponseBody
    public  String logoutajax(HttpServletRequest request, HttpServletResponse response) {
        logout(request,response);
        return  jsonResponseFromRequestAttributes(request, response);
    }

    /**
     *  注销登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "logout")
    public  String logout(HttpServletRequest request, HttpServletResponse response) {

        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();
        }

        UtilMessages.saveMessages(request, "您已经退出了系统");
        return  "redirect:/";
    }

    /**
     *  404错误
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "err404")
    public  String err404(HttpServletRequest request, HttpServletResponse response) {
        return "component://common/webcommon/ftl/err404";
    }
    }
