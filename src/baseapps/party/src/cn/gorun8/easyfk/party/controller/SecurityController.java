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
import cn.gorun8.easyfk.security.annotation.PermissionDefine;
import cn.gorun8.easyfk.security.service.SecurityService;
import cn.gorun8.easyfk.security.service.UserLoginService;
import javolution.util.FastList;
import javolution.util.FastMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@PermissionDefine(permissionId = "security_groups_mgr" ,resource = "SecurityUiLabels",key="security_groups_mgr" ,tag = "security:groups:*")

@Controller("securityController")
@RequestMapping("/dyn/security")
public class SecurityController {

	/**
	 * 该方法只用于配置权限
	 */
	@PermissionDefine(permissionId = "security_permission_mgr" ,resource = "SecurityUiLabels",key="security_permission_mgr" ,tag = "security:permisson:*")
	public void conf(){}

	@Autowired
	private SecurityService securityService;

	/**
	 * 系统安全组
	 * @param request
	 * @return
	 */
	@PermissionDefine(permissionId = "security_groups_list" ,parentId = "security_groups_mgr",resource = "SecurityUiLabels",key="security_groups_list" ,tag = "security:groups:list")
	//@RequiresPermissions("security:groups:list")
	@RequestMapping(value = "securitygroups")
	public String securitygroups(HttpServletRequest request,
								 @RequestParam(value="pageSize" ,defaultValue="10")int pageSize,
								 @RequestParam(value="pageIndex" ,defaultValue="1")int pageIndex){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		UtilPage.startPage(request, pageIndex, pageSize);

		context.put("isSystem","Y");
		securitygroups(request, context);
		return "page/securitygroups";
	}

	/**
	 * 业务安全组
	 * @param request
	 * @return
	 */
	@PermissionDefine(permissionId = "security_groups_bzlist" ,parentId = "security_groups_mgr",resource = "SecurityUiLabels",key="security_groups_bzlist" ,tag = "security:groups:bzlist")
	//@RequiresPermissions("security:groups:bzlist")
	@RequestMapping(value = "bzsecuritygroups")
	public String bzsecuritygroups(HttpServletRequest request, @RequestParam(value="pageSize" ,defaultValue="10")int pageSize,
	@RequestParam(value="pageIndex" ,defaultValue="1")int pageIndex){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		UtilPage.startPage(request, pageIndex, pageSize);
		context.put("isSystem", "N");
		securitygroups(request, context);
		return "page/bzsecuritygroups";
	}
	/**
	 * 查看安全组
	 * @param request
	 * @param context
	 */
	private void securitygroups(HttpServletRequest request,Map<String,Object> context){
		Locale locale = (Locale) context.get("locale");
		Map<String,Object> result =	securityService.findSecurityGroup(context);
		if(UtilMessages.isSuccess(result)) {
			List<Map> securityGroupList = (List<Map>) result.get(UtilMessages.RESPONSE_DATA);
			request.setAttribute("securityGroupList",securityGroupList);
		}else {
			String error = UtilMessages.getErrorMessage(result);
			UtilMessages.saveErrors(request,error);
		}
	}

	/**
	 * 创建系统安全组
	 * @param request
	 * @return
	 */
	@PermissionDefine(permissionId = "security_groups_create" ,parentId = "security_groups_mgr",resource = "SecurityUiLabels",key="security_groups_create" ,tag = "security:groups:create")
	//@RequiresPermissions("security:groups:create")
	@RequestMapping("createGroup")
	@ResponseBody
	public String createGroup( HttpServletRequest request  ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		context.put("isSystem", "Y");
		return 	createGroup(context, request);
	}

	/**
	 * 创建业务安全组
	 */
	@PermissionDefine(permissionId = "security_groups_bzcreate" ,parentId = "security_groups_mgr",resource = "SecurityUiLabels",key="security_groups_bzcreate" ,tag = "security:groups:bzcreate")
	//@RequiresPermissions("security:groups:bzcreate")
	@RequestMapping("bzcreateGroup")
	@ResponseBody
	public String bzcreateGroup( HttpServletRequest request  ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		context.put("isSystem", "N");
		return 	createGroup(context, request);
	}

	/**
	 * 创建安全组
	 * @param context
	 * @param request
	 * @return
	 */
	private String createGroup( Map<String,Object> context,HttpServletRequest request  ){
		Map<String,Object> result = securityService.createSecurityGroup(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}

	@PermissionDefine(permissionId = "security_groups_update" ,parentId = "security_groups_mgr",resource = "SecurityUiLabels",key="security_groups_update" ,tag = "security:groups:update")
	//@RequiresPermissions("security:groups:update")
	@RequestMapping("updateGroup")
	@ResponseBody
	public String updateGroup(HttpServletRequest request){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		context.put("isSystem", "Y");
		return updateGroup(context, request);
	}

	@PermissionDefine(permissionId = "security_groups_bzupdate" ,parentId = "security_groups_mgr",resource = "SecurityUiLabels",key="security_groups_bzupdate" ,tag = "security:groups:bzupdate")
	//@RequiresPermissions("security:groups:bzupdate")
	@RequestMapping("bzupdateGroup")
	@ResponseBody
	public String bzupdateGroup(HttpServletRequest request){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		context.put("isSystem", "N");
		return updateGroup(context, request);
	}

	private String updateGroup(Map<String,Object> context,HttpServletRequest request ){

		Map<String,Object> result = securityService.saveSecurityGroup(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}

	@PermissionDefine(permissionId = "security_groups_remove" ,parentId = "security_groups_mgr",resource = "SecurityUiLabels",key="security_groups_remove" ,tag = "security:groups:remove")
	//@RequiresPermissions("security:groups:remove")
	@RequestMapping("removeGroup")
	@ResponseBody
	public String removeGroup(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		context.put("isSystem", "Y");
		return 	removeGroup(request, context);
	}

	@PermissionDefine(permissionId = "security_groups_bzremove" ,parentId = "security_groups_mgr",resource = "SecurityUiLabels",key="security_groups_bzremove" ,tag = "security:groups:bzremove")
	//@RequiresPermissions("security:groups:bzremove")
	@RequestMapping("bzremoveGroup")
	@ResponseBody
	public String bzremoveGroup(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		context.put("isSystem", "N");
		return 	removeGroup(request, context);
	}


	private String removeGroup(HttpServletRequest request ,Map<String,Object> context){
		Map<String,Object> result = securityService.removeSecurityGroup(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}


	@RequestMapping(value = "getPermissionTree")
	//@RequiresPermissions("security:permisson:list")
	@ResponseBody
	public String getPermissionTree(HttpServletRequest request){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		context.put("isSystem", "Y");
		return getPermissionTree(request,context);
	}
	@RequestMapping(value = "getBzPermissionTree")
	//@RequiresPermissions("security:permisson:bzlist")
	@ResponseBody
	public String getBzPermissionTree(HttpServletRequest request){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		context.put("isSystem", "N");
		return getPermissionTree(request,context);
	}

	private String getPermissionTree(HttpServletRequest request,Map<String,Object> context){

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

	@PermissionDefine(permissionId = "security_groups_setpermisson" ,parentId = "security_groups_mgr",resource = "SecurityUiLabels",key="security_groups_setpermisson" ,tag = "security:groups:setpermisson")
	//@RequiresPermissions("security:groups:setpermisson")
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

	@PermissionDefine(permissionId = "security_permisson_list" ,parentId = "security_permission_mgr",resource = "SecurityUiLabels",key="security_permisson_list" ,tag = "security:permisson:list")
	//@RequiresPermissions("security:permisson:list")
	@RequestMapping(value = "resourcelist")
	public String resourceList(HttpServletRequest request,
						   @RequestParam(value="pageSize" ,defaultValue="10")int pageSize,
						   @RequestParam(value="pageIndex" ,defaultValue="1")int pageIndex){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		UtilPage.startPage(request, pageIndex, pageSize);
		Locale locale = (Locale) context.get("locale");
    	Map<String,Object> result =	securityService.getPermissionListByPage(context);
		if(UtilMessages.isSuccess(result)) {
			List<Map> permissionList = (List<Map>) result.get(UtilMessages.RESPONSE_DATA);
			request.setAttribute("permissionList",permissionList);
		}else {
			String error = UtilMessages.getErrorMessage(result);
			UtilMessages.saveErrors(request,error);
		}
		return "page/resourcelist";
	}

	/**
	 * 扫描配置安全权限
	 * @param request
	 * @return
	 */
	@PermissionDefine(permissionId = "security_permisson_scan" ,parentId = "security_permission_mgr",resource = "SecurityUiLabels",key="security_permisson_scan" ,tag = "security:permisson:scan")
	//@RequiresPermissions("security:permisson:scan")
	@RequestMapping(value = "/scanresource" ,method = RequestMethod.GET)
	@ResponseBody
	public String scanResource(HttpServletRequest request){
		Map<String ,Object > context = UtilHttp.getParameterMap(request);
		Map<String ,Object > result = securityService.scanSecurityPermission(context);
		if(!UtilMessages.isSuccess(result)){
			return UtilMessages.errorResponse(request);
		}
		return  UtilMessages.successResponse(request,"扫描安全权限配置成功");
	}

}
