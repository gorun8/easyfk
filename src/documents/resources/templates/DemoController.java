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
