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
package cn.gorun8.easyfk.service.controller;

import cn.gorun8.easyfk.base.util.*;
import cn.gorun8.easyfk.service.service.GeneralServiceException;
import cn.gorun8.easyfk.service.service.ServiceEngine;
import javolution.util.FastMap;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;


/**
 * demo默认首页控制器
 * 
 */
@Controller("serviceController")
@RequestMapping("/dyn/api")
public class ServiceController {
	private final static String module = ServiceController.class.getName();

	@Autowired
	private ServiceEngine serviceEngine;

	@RequestMapping("")
	@ResponseBody
	public String execute(HttpServletRequest request){
		Map<String ,Object > context = UtilHttp.getParameterMap(request);
		String serviceName = (String) context.get("serviceName");

		if(UtilValidate.isEmpty(serviceName)){
			return  UtilMessages.errorResponse(request,"没有指定服务名称");
		}
		try {
			Object result = serviceEngine.invokerService(serviceName,context);
			JSONObject json = JSONObject.fromObject(result);
			return json.toString();
		} catch (GeneralServiceException e) {
			e.printStackTrace();
			Debug.logError(e, module);
			return  UtilMessages.errorResponse(request, e.getMessage());
		}
	}
}
