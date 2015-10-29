///*
// * Project:Easy Web Framework
// *
// * Description: This project is based on much more open source projects than ever before,
// *              and can be applied to mostly web development environment.
// * Author:hezhiping   Email:110476592@qq.com
// *
// *
// *==========================================================================================
// *
// */
//package cn.gorun8.easyfk.party.controller;
//
//import cn.gorun8.easyfk.entity.GenericValue;
//import cn.gorun8.easyfk.party.service.PartyClsGroupService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import cn.gorun8.easyfk.party.service.PartyGroupService;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * demo默认首页控制器
// *
// */
//@Controller("partyGroupController")
//@RequestMapping("partygroup")
//public class PartyGroupController {
//
//	@Autowired
//	private PartyClsGroupService partyGroupService;
//
//	@RequestMapping(value = "list")
//	public String list( ModelAndView mv ,HttpServletRequest request,HttpServletResponse response ){
//		List<GenericValue> partyGroupList = partyGroupService.findPartyGroupList();
//		mv.addObject("partyGroupList",partyGroupList);
//
//		return  "page/partygrouplist";
//	}
//
//	@RequestMapping("create")
//	public String createPartyGroup( HttpServletRequest request,HttpServletResponse response ){
//		GenericValue partyGroup = new GenericValue();
//		partyGroup.put("PARTY_ID","1");
//		partyGroup.put("GROUP_NAME","1");
//		partyGroup.put("GROUP_NAME_LOCAL","zh");
//		partyGroupService.createPartyGroup(partyGroup);
//		return  "page/partygrouplist";
//	}
//
//	@RequestMapping("update")
//	@ResponseBody
//	public String savePartyGroup(HttpServletRequest request,HttpServletResponse response ){
//		GenericValue partyGroup = null;
//		partyGroupService.savePartyGroup(partyGroup);
//		return  "";
//	}
//
//	@RequestMapping("remove")
//	@ResponseBody
//	public String removePartyGroupByPrimaryKey(@RequestParam("partyId") String partyId){
//		partyGroupService.removePartyGroupByPrimaryKey(partyId);
//		return "";
//	}
//
//}
