/*
 * Project:Easy Web Framework
 *
 * Description: This project is based on much more open source projects than ever before,
 *              and can be applied to mostly web development environment.
 * Author:hezhiping   Email:110476592@qq.com
 * 
 * 
 *==========================================================================================
 * 
 */
package cn.gorun8.easyfk.install.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gorun8.easyfk.base.util.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gorun8.easyfk.install.service.InitService;

/**
 * 系统初始化安装配置
 * @author:hezhiping(110476592@qq.com)
 *
 */
@Controller("initController")
@RequestMapping("")
public class InitController {
	public static final String module = InitController.class.getName();

	@Autowired
	private InitService initService;

	@RequestMapping("index")
	public String index(Model model, HttpServletRequest request)
	{
		return "page/index";
	}

	@RequestMapping("dbconfig")
	public String dbconfig(Model model, HttpServletRequest request) {
		model.addAttribute("currentstep", "2");
		Properties properties = UtilProperties.getProperties(InitService.DB_PROPERTIES_PATH_TMP);
		Enumeration<Object> keys = properties.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			model.addAttribute(key.replace("db.", ""),
					properties.getProperty(key));
		}
		return "page/dbconfig";
	}

	@RequestMapping("dbinit")
	public String dbsetup(Model model, HttpServletRequest request) {
		model.addAttribute("currentstep", "3");
		model.addAttribute("readOnlyField", "true");

		Properties properties = UtilProperties.getProperties(InitService.DB_PROPERTIES_PATH_TMP);
		Enumeration<Object> keys = properties.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			model.addAttribute(key.replace("db.", ""),
					properties.getProperty(key));
		}
		return "page/dbinstall";
	}

	@ResponseBody
	@RequestMapping("checkSetting")
	public String checkSetting(@RequestBody Properties properties) throws Exception {
		return initService.checkConnect(properties);
	}


	@ResponseBody
	@RequestMapping("installdb")
	public String installDB(HttpServletRequest request
			,HttpServletResponse response) {
		try {
			initService.install();
		}catch (Exception e){
			Debug.logError(e.toString(),module);
			String msg = e.getMessage();
			UtilMessages.saveErrors(request,msg);
		}
		return jsonResponseFromRequestAttributes(request,response);
	}
	
	@RequestMapping("finished")
	public String finished(Model model, HttpServletRequest request) {
		model.addAttribute("currentstep", "4");
		model.addAttribute("readOnlyField", "true");
		initService.finish();
		return "page/finished";
	}

	/**
	 * 如果已经完成，再次执行安装，直接到这个页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("finished2")
	public String finished2(Model model, HttpServletRequest request) {
		model.addAttribute("currentstep", "4");
		model.addAttribute("readOnlyField", "true");
		return "page/finished";
	}


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
}

	
	
	
	
	 