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
