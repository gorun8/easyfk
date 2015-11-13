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
import cn.gorun8.easyfk.base.util.UtilMisc;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.party.service.PartyClsGroupService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 会员组控制器
 * 
 */
@Controller("partyClassificationGroupController")
@RequestMapping("partyclsgroup")
public class PartyClassificationGroupController {
	
	@Autowired
	private PartyClsGroupService partyClsGroupService;
	//ResourceBundleMessageSource t;
	@RequestMapping(value = "list")
	public String list(HttpServletRequest request){
		String parentId = "N/A";
		List<Map> parClsMapList = partyClsGroupService.listRootNode(UtilMisc.toMap("parentId",parentId));
		if(parClsMapList.size() >0) {
			request.setAttribute("partyClsGroupRoot", parClsMapList);
			Map<String, Object> attrMap = UtilHttp.getJSONAttributeMap(request, "partyClsGroupRoot");
			JSONObject json = JSONObject.fromObject(attrMap);
			request.setAttribute("partyClsGroupList", json);
			String id = (String)parClsMapList.get(0).get("partyClassificationGroupId");
			request.setAttribute("partyClsGroupRootId", id);
			return "page/partyclsgrouplist";
		}

		return "page/initpartyclsgroup";
	}

	@RequestMapping(value = "listchild")
	@ResponseBody
	public String listchild(HttpServletRequest request,@RequestParam(value="parentId")String parentId){
		if(UtilValidate.isEmpty(parentId))
		{
			return UtilMessages.errorResponse(request,"没有参数");
		}
		List<Map> parClsMapList =  partyClsGroupService.listChildNode(UtilMisc.toMap("parentId",parentId));
		request.setAttribute("partyClsGroupList", parClsMapList);
		return  UtilMessages.successResponse("partyClsGroupList",request);
	}

	@RequestMapping("createform")
	public String createPartyClsGroupForm( HttpServletRequest request,HttpServletResponse response ){
		createPartyClsGroup(request,response);
		return "redirect:list";
	}

	@RequestMapping("create")
	@ResponseBody
	public String createPartyClsGroup( HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> params = UtilHttp.getParameterMap(request);
		String parentGroupId = (String)params.get("parentGroupId");
		String description = (String)params.get("description");

		boolean exits = partyClsGroupService.hasPartyClsGroupName(parentGroupId, description);
		if(exits)
		{
			return  UtilMessages.errorResponse(request, "相同目录下已存在相同名称的机构");
		}

		try {
			GenericValue partyClsGroup = GenericValue.fromMap(params);
			String id = partyClsGroup.newPrimaryKey("partyClassificationGroup", "partyClassificationGroupId");
			partyClsGroupService.createPartyClsGroup(partyClsGroup);
		}catch (Exception e){
			e.printStackTrace();
			return  UtilMessages.errorResponse(request, "新建组织机构出错");
		}
		return  UtilMessages.successResponse(request,"新建组织机构成功");
	}

	@RequestMapping("update")
	@ResponseBody
	public String savePartyClsGroup(HttpServletRequest request,HttpServletResponse response ){
		try {
			Map<String,Object> params = UtilHttp.getParameterMap(request);
			String parentGroupId = (String)params.get("parentGroupId");
			String description = (String)params.get("description");

			boolean exits = partyClsGroupService.hasPartyClsGroupName(parentGroupId, description);
			if(exits)
			{
				return  UtilMessages.errorResponse(request, "相同目录下已存在相同名称的机构");
			}

			GenericValue partyClsGroup = GenericValue.fromMap(params);
			partyClsGroupService.savePartyClsGroup(partyClsGroup);
		}catch (Exception e){
			e.printStackTrace();
			return  UtilMessages.errorResponse(request, "更新组织机构出错");
		}
		return  UtilMessages.successResponse(request,"更新组织机构成功");
	}

	@RequestMapping("remove")
	@ResponseBody
	public String removePartyClsGroupByPrimaryKey(HttpServletRequest request,@RequestParam("partyClsGroupId") String partyClsGroupId){
		try {
			boolean rel = partyClsGroupService.hasChildrenPartyClsGroup(partyClsGroupId);
			if(rel)
			{
				return  UtilMessages.errorResponse(request, "存在下级组织机构，不能删除");
			}
			partyClsGroupService.removePartyClsGroupByPrimaryKey(partyClsGroupId);
		}catch (Exception e){
			e.printStackTrace();
			return  UtilMessages.errorResponse(request, "删除组织机构出错");
		}
		return  UtilMessages.successResponse(request,"删除组织机构成功");
	}

}
