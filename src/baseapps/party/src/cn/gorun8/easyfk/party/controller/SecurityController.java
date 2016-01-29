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
package cn.gorun8.easyfk.party.controller;

import cn.gorun8.easyfk.base.util.UtilHttp;
import cn.gorun8.easyfk.base.util.UtilMessages;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.page.UtilPage;
import cn.gorun8.easyfk.party.service.PartyClsGroupService;
import cn.gorun8.easyfk.party.service.PartyService;
import cn.gorun8.easyfk.security.service.SecurityService;
import cn.gorun8.easyfk.security.service.UserLoginService;
import javolution.util.FastList;
import javolution.util.FastMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
* party
*
*/
@Controller("securityController")
@RequestMapping("/dyn/security")
public class SecurityController {

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "securitygroups")
	public String securitygroups(HttpServletRequest request){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Locale locale = (Locale) context.get("locale");
		Map<String,Object> result =	securityService.findSecurityGroup(context);
		if(UtilMessages.isSuccess(result)) {
			List<Map> securityGroupList = (List<Map>) result.get(UtilMessages.RESPONSE_DATA);
			request.setAttribute("securityGroupList",securityGroupList);
		}else {
			String error = UtilMessages.getErrorMessage(result);
			UtilMessages.saveErrors(request,error);
		}
		return "page/securitygroups";
	}

	@RequestMapping("createGroup")
	@ResponseBody
	public String createGroup( HttpServletRequest request  ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = securityService.createSecurityGroup(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}

	@RequestMapping("updateGroup")
	@ResponseBody
	public String updateGroup(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = securityService.saveSecurityGroup(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}

	@RequestMapping("removeGroup")
	@ResponseBody
	public String removeGroup(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = securityService.removeSecurityGroup(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}


	@RequestMapping(value = "getPermissionTree")
	@ResponseBody
	public String getPermissionTree(HttpServletRequest request){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Locale locale = (Locale) context.get("locale");
		List<Map>  permissionList = null;
		List<Map>  permissionSelected = null;

		Map<String,Object> result =	securityService.getPermissionList(context);
		if(UtilMessages.isSuccess(result)){
			 permissionList =(List<Map>)result.get(UtilMessages.RESPONSE_DATA);
		}else {
			return 	UtilMessages.errorResponse(request, result);
		}

 		if(permissionList != null){
			result = securityService.getGroupAndPermission(context);
			if(UtilMessages.isSuccess(result)){
				permissionSelected =(List<Map>)result.get(UtilMessages.RESPONSE_DATA);
				if(permissionSelected != null){
					StringBuffer buf = new StringBuffer();
					for(Map it: permissionSelected){
						buf.append(it.get("permissionId"));
						buf.append(",");
					}

					List<Map>  permissionJsonList = FastList.newInstance();
					String selectedIds = buf.toString();
					for(Map it:permissionList){
						Map<String ,Object> tmp = FastMap.newInstance();
						tmp.put("id",it.get("permissionId"));
						tmp.put("pId",it.get("parentId"));
						tmp.put("name",it.get("description"));
						tmp.put("open",it.get("hasSub"));
						if(selectedIds.indexOf((String)it.get("permissionId")+",") >=0){
							tmp.put("checked","true");
						}
						permissionJsonList.add(tmp);
					}

					request.setAttribute("permissionJsonList",permissionJsonList);
					return 	UtilMessages.successResponse("permissionJsonList",request);
				}
			}else {
				return 	UtilMessages.errorResponse(request, result);
			}
		}
		return 	UtilMessages.errorResponse(request);
 	}


	@RequestMapping(value = "setGroupPermission")
	@ResponseBody
	public String setGroupPermission(HttpServletRequest request){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Locale locale = (Locale) context.get("locale");
		Map<String,Object> result =	securityService.setGroupPermission(context);
		if(UtilMessages.isSuccess(result)){
			return UtilMessages.successResponse(request);
		}
		return UtilMessages.errorResponse(request,result);
	}
}
