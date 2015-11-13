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
import javolution.util.FastList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
* party
*
*/
@Controller("partyController")
@RequestMapping("party")
public class PartyController {

	@Autowired
	private PartyService partyService;

	@Autowired
	private PartyClsGroupService partyClsGroupService;

	@RequestMapping(value = "list")
	public String listparty(HttpServletRequest request){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		String navids = (String) context.get("navids");
		List<Map> parentClsGroupList = FastList.newInstance();
		if(UtilValidate.isNotEmpty(navids))
		{
			String  []ids = navids.split(",");
			for (String id : ids){
				if(UtilValidate.isEmpty(id)){
					continue;
				}
				GenericValue gv = partyClsGroupService.findPartyClsGroupById(id);
				if(gv != null){
					parentClsGroupList.add(gv.toMap());
				}
			}
			request.setAttribute("navids",navids);
		}
		request.setAttribute("parentClsGroupList",parentClsGroupList);
		return "page/partylist";
	}

	@RequestMapping(value = "listpartydata")
	public String listpartydata(HttpServletRequest request,
							@RequestParam(value="pageSize" ,defaultValue="10")int pageSize,
							@RequestParam(value="pageIndex" ,defaultValue="1")int pageIndex){

		UtilPage.startPage(request, pageIndex, pageSize);
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		String navids = (String) context.get("navids");
		List<Map> partyGroupList = partyService.listParty(context);
		request.setAttribute("navids",navids);
		request.setAttribute("partyGroupList",partyGroupList);
		return "page/partylistdata";
	}

	@RequestMapping("createperson")
	@ResponseBody
	public String createPerson( HttpServletRequest request  ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = partyService.createPerson(context);
		return  UtilMessages.successResponse(request);
	}

	@RequestMapping("partydetial")
	public String partydetial(HttpServletRequest request){

		return "page/partydetial";
	}

	@RequestMapping("updateperson")
	@ResponseBody
	public String savePerson(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = partyService.updatePerson(context);
		return  UtilMessages.successResponse(request);
	}

	@RequestMapping(value = "creategroup",method = RequestMethod.POST)
	@ResponseBody
	public String createGroup( HttpServletRequest request  ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = partyService.createPartyGroup(context);
		return  UtilMessages.successResponse(request);
	}

	@RequestMapping("updategroup")
	@ResponseBody
	public String saveGroup(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = partyService.updatePartyGroup(context);
		return  UtilMessages.successResponse(request);
	}
}
