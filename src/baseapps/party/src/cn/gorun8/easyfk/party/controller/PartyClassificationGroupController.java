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
package cn.gorun8.easyfk.party.controller;

import cn.gorun8.easyfk.base.util.UtilHttp;
import cn.gorun8.easyfk.base.util.UtilMessages;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.SequenceFactory;
import cn.gorun8.easyfk.entity.page.UtilPage;
import cn.gorun8.easyfk.party.service.PartyClsGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value = "list")
	public String list( ModelAndView mv ,HttpServletRequest request,@RequestParam(value="pageSize" ,defaultValue="10")int pageSize,
						@RequestParam(value="pageIndex" ,defaultValue="1")int pageIndex ){
		UtilPage.startPage(request, pageIndex, pageSize);
		List<GenericValue> partyClsGroupList = partyClsGroupService.findPartyClsGroupList();
		mv.addObject("partyClsGroupList",partyClsGroupList);
		return  "page/partyclsgrouplist";
	}

	@RequestMapping("create")
	@ResponseBody
	public String createPartyClsGroup( HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> params = UtilHttp.getParameterMap(request);
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
			partyClsGroupService.removePartyClsGroupByPrimaryKey(partyClsGroupId);
		}catch (Exception e){
			e.printStackTrace();
			return  UtilMessages.errorResponse(request, "更新组织机构出错");
		}
		return  UtilMessages.successResponse(request,"更新组织机构成功");
	}

}
