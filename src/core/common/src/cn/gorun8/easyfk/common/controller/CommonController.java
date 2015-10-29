/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
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
