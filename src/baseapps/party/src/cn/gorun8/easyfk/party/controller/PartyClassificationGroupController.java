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

import cn.gorun8.easyfk.base.util.*;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.party.service.PartyClsGroupService;
import cn.gorun8.easyfk.security.annotation.PermissionDefine;
import cn.gorun8.easyfk.security.service.SecurityService;
import javolution.util.FastList;
import javolution.util.FastMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * 会员组控制器
 * 
 */
@PermissionDefine(permissionId = "party_clsgroup" ,resource = "PartyUiLabels",key="partyClsmgr" ,tag = "party:clsgroup:*")
@Controller("partyClassificationGroupController")
@RequestMapping("/dyn/partyclsgroup")
public class PartyClassificationGroupController {
	
	@Autowired
	private PartyClsGroupService partyClsGroupService;

	@Autowired
	private SecurityService securityService;
	/**
	 * 根据当前登录管理员的权限列出该管理员可以看到的所有目录
	 * @param request
	 * @return
	 */
	@PermissionDefine(permissionId = "party_clsgroup_list" ,parentId = "party_clsgroup",resource = "PartyUiLabels",key="partyClsgroupList" ,tag = "party:clsgroup:list")
	@RequiresPermissions("party:clsgroup:list")
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request){
		Map<String ,Object > context = UtilHttp.getParameterMap(request);
		Locale locale = (Locale) context.get("locale");
		Map<String,Object>  result = securityService.findRoleTypes(context);
		if(UtilMessages.isSuccess(result)){
			List<Map> partyRoleTypeList = (List<Map>)result.get(UtilMessages.RESPONSE_DATA);
			request.setAttribute("partyRoleTypeList", partyRoleTypeList);
		}
		return "page/partyclsgrouplist";
	}

	/**
	 * 根据当前登录管理员的权限列出该管理员可以看到的所有目录
	 * @param request
	 * @return
	 */
	@PermissionDefine(permissionId = "party_clsgroup_tree" ,parentId = "party_clsgroup",resource = "PartyUiLabels",key="partyClsgroupTree" ,tag = "party:clsgroup:tree")
	@RequiresPermissions("party:clsgroup:tree")
	@RequestMapping(value = "listtree")
	public String listGlsGroupTree(HttpServletRequest request){
		Map<String ,Object > context = UtilHttp.getParameterMap(request);
		Locale locale = (Locale) context.get("locale");
		String parentId = "_NA_";
		context.put("parentId", parentId);

		Map<String,Object>  result = partyClsGroupService.getPartyClassificationGroupTree(context);
		if(UtilMessages.isSuccess(result)){
			List<Map> parClsMapList = (List<Map>)result.get(UtilMessages.RESPONSE_DATA);
			request.setAttribute("partyClsGroupList", parClsMapList);
		}
		return "page/partyclsgrouplistajax";
	}

	/**
	 * 查找组织机构
	 * @param request
	 * @param partyClsName
	 * @return
	 */
	@PermissionDefine(permissionId = "party_clsgroup_search" ,parentId = "party_clsgroup",resource = "PartyUiLabels",key="partyClsgroupSearch",tag = "party:clsgroup:search")
	@RequiresPermissions("party:clsgroup:search")
	@RequestMapping(value = "searchnode")
	public String search(HttpServletRequest request,@RequestParam(value="partyClsName")String partyClsName){

		if(UtilValidate.isEmpty(partyClsName)){
			return listGlsGroupTree(request);
		}

		Map<String,Object>  result =  partyClsGroupService.searchPartyClassificationGroup(UtilMisc.toMap("partyClsName", partyClsName));
		if(UtilMessages.isSuccess(result)){
			List<Map> parClsMapList = (List<Map>)result.get(UtilMessages.RESPONSE_DATA);
			request.setAttribute("partyClsGroupList", parClsMapList);
		}

		return "page/partyclsgrouplistajax";
	}


	@PermissionDefine(permissionId = "party_clsgroup_create" ,parentId = "party_clsgroup",resource = "PartyUiLabels",key = "partyClsgroupCreate" ,tag = "party:clsgroup:create")
	@RequiresPermissions("party:clsgroup:create")
	@RequestMapping("create")
	@ResponseBody
	public String createPartyClsGroup( HttpServletRequest request,HttpServletResponse response ){
		Map<String ,Object > context = UtilHttp.getParameterMap(request);
		Locale locale = (Locale) context.get("locale");

		Map<String,Object>  result= partyClsGroupService.hasPartyClassificationGroupName(context);
		if(UtilMessages.isSuccess(result)){
			boolean exits = (Boolean)result.get(UtilMessages.RESPONSE_DATA);
			if(exits)
			{
				return  UtilMessages.errorResponse(request, "相同目录下已存在相同名称的机构");
			}
		}

		try {
			result = partyClsGroupService.createPartyClassificationGroup(context);
			if(!UtilMessages.isSuccess(result)){
				return UtilMessages.errorResponse(request);
			}
		}catch (Exception e){
			e.printStackTrace();
			return  UtilMessages.errorResponse(request, "新建组织机构出错");
		}
		return  UtilMessages.successResponse(request,"新建组织机构成功");
	}


	@PermissionDefine(permissionId = "party_clsgroup_update" ,parentId = "party_clsgroup",resource = "PartyUiLabels",key = "partyClsgroupUpdate" ,tag = "party:clsgroup:update")
	@RequiresPermissions("party:clsgroup:update")
	@RequestMapping("update")
	@ResponseBody
	public String savePartyClsGroup(HttpServletRequest request,HttpServletResponse response ){
		try {
			Map<String,Object> context = UtilHttp.getParameterMap(request);

			Map<String,Object> result = partyClsGroupService.hasPartyClassificationGroupName(context);
			if(UtilMessages.isSuccess(result)){
				boolean exits = (Boolean)result.get(UtilMessages.RESPONSE_DATA);
				if(exits)
				{
					return  UtilMessages.errorResponse(request, "相同目录下已存在相同名称的机构");
				}
			}

			result = partyClsGroupService.updatePartyClassificationGroup(context);
			if(!UtilMessages.isSuccess(result)){
				return UtilMessages.errorResponse(request);
			}
		}catch (Exception e){
			e.printStackTrace();
			return  UtilMessages.errorResponse(request, "更新组织机构出错");
		}
		return  UtilMessages.successResponse(request,"更新组织机构成功");
	}


	@PermissionDefine(permissionId = "party_clsgroup_remove" ,parentId = "party_clsgroup",resource = "PartyUiLabels",key = "partyClsgroupRemove" ,tag = "party:clsgroup:remove")
	@RequiresPermissions("party:clsgroup:remove")
	@RequestMapping("remove")
	@ResponseBody
	public String removePartyClsGroupByPrimaryKey(HttpServletRequest request){
		try {
			Map<String,Object> context = UtilHttp.getParameterMap(request);
			Locale locale = (Locale) context.get("locale");

			String partyClsGroupId = (String)context.get("partyClsGroupId");
			Map<String,Object> param = FastMap.newInstance();
			param.put("parentId",partyClsGroupId);

			Map<String,Object> result= partyClsGroupService.hasChildPartyClassificationGroup(param);
			if(UtilMessages.isSuccess(result)){
				boolean exits = (Boolean)result.get(UtilMessages.RESPONSE_DATA);
				if(exits)
				{
					return  UtilMessages.errorResponse(request, "存在下级组织机构，不能删除");
				}
			}else{
				return UtilMessages.errorResponse(request);
			}

			result= partyClsGroupService.deletePartyClassificationGroup(context);
			if(!UtilMessages.isSuccess(result)){
				return UtilMessages.errorResponse(request);
			}
		}catch (Exception e){
			e.printStackTrace();
			return  UtilMessages.errorResponse(request, "删除组织机构出错");
		}
		return  UtilMessages.successResponse(request,"删除组织机构成功");
	}

	@PermissionDefine(permissionId = "party_clsgroup_movenode" ,parentId = "party_clsgroup",resource = "PartyUiLabels",key = "partyClsgroupMoveNode",tag = "party:clsgroup:movenode")
	@RequiresPermissions("party:clsgroup:movenode")
	@RequestMapping(value = "movenode")
	@ResponseBody
	public String movePartyClsGroup(HttpServletRequest request){
		Map<String ,Object > context = UtilHttp.getParameterMap(request);
		Locale locale = (Locale) context.get("locale");

		Map<String,Object> result=  partyClsGroupService.movePartyClassificationGroup(context);
		if(!UtilMessages.isSuccess(result)){
			return UtilMessages.errorResponse(request,result);
		}
		return  UtilMessages.successResponse(request);
	}


}
