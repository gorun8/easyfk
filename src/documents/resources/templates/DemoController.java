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
package cn.gorun8.easyfk.@component-name@.controller;

import org.springframework.web.bind.annotation.RequestParam;
import cn.gorun8.easyfk.entity.page.UtilPage;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.gorun8.easyfk.@component-name@.service.DemoService;

/**
 * demo默认首页控制器
 * 
 */
@Controller("@component-name@DemoController")
@RequestMapping("@component-name@demo")
public class DemoController {
	
	@Autowired
	private DemoService demoService; 
	
	@RequestMapping("")
	public String index(){
		demoService.getDemo();
		return "page/index";
	}

	/**
	 * 实现分页查询
	 * @param request
	 * @return
	 */
	@RequestMapping("listpage")
	public String listpage(HttpServletRequest request,
						   @RequestParam(value="pageSize" ,defaultValue="10")int pageSize,
						   @RequestParam(value="pageIndex" ,defaultValue="1")int pageIndex){

		UtilPage.startPage(request, pageIndex, pageSize);
		demoService.listByPage();
		return "page/index";
	}


}
