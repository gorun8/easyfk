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
import cn.gorun8.easyfk.common.service.PortalService;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.page.UtilPage;
import cn.gorun8.easyfk.entity.util.UtilEntity;
import cn.gorun8.easyfk.party.service.PartyClsGroupService;
import cn.gorun8.easyfk.party.service.PartyService;
import cn.gorun8.easyfk.security.service.UserLoginService;
import javolution.util.FastList;
import javolution.util.FastMap;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
* party
*
*/
@Controller("partyController")
@RequestMapping("/dyn/party")
public class PartyController {

	@Autowired
	private PartyService partyService;

	@Autowired
	private PartyClsGroupService partyClsGroupService;

	@Autowired
	protected UserLoginService userLoginService;

	@RequestMapping(value = "list")
	public String listPartyClsGroup(HttpServletRequest request){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		String navids = (String) context.get("navids");
		List<Map> parentClsGroupList = FastList.newInstance();
		Locale locale = (Locale) context.get("locale");

		Map<String,Object> params = FastMap.newInstance();
		params.put("locale",locale);

		if(UtilValidate.isNotEmpty(navids)){
			String  []ids = navids.split(",");
			for (String id : ids){
				if(UtilValidate.isEmpty(id)){
					continue;
				}
				params.put("partyClsGroupId",id);
				Map<String,Object> result =	partyClsGroupService.findPartyClassificationGroupById(params);
				if(UtilMessages.isSuccess(result)){
					GenericValue gv =(GenericValue)result.get(UtilMessages.RESPONSE_DATA);
					if(gv != null){
						parentClsGroupList.add(gv.toMap());
					}
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
		Map<String,Object> context = UtilHttp.getParameterMap(request);

		Locale locale = (Locale) context.get("locale");
		Map<String,Object> params = FastMap.newInstance();
		params.put("locale",locale);
		String clsId = (String) context.get("clsId");

		request.setAttribute("clsId",clsId);
		UtilPage.startPage(request, pageIndex, pageSize);
		Map<String,Object> result = partyService.listParty(context);
		if(UtilMessages.isSuccess(result)) {
			List<Map> partyGroupList = (List<Map>) result.get(UtilMessages.RESPONSE_DATA);
			request.setAttribute("partyGroupList",partyGroupList);
		}else {
			String error = UtilMessages.getErrorMessage(result);
			UtilMessages.saveErrors(request,error);
		}

		return "page/partylistdata";
	}

	@RequestMapping("disableParty")
	@ResponseBody
	public String disableParty(HttpServletRequest request ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		context.put("statusId","PARTY_DISABLED");
		Map<String,Object> result = partyService.setPartyStatus(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request,result);
	}

	@RequestMapping("createperson")
	@ResponseBody
	public String createPerson( HttpServletRequest request  ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = partyService.createPerson(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}


	@RequestMapping("partydetial")
	public String partydetial(HttpServletRequest request){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Locale locale = (Locale) context.get("locale");

		String partyId = (String)context.get("partyId");
		if(UtilValidate.isEmpty(partyId)){
			UtilMessages.saveErrors(request,"参数不存在");
		}
		request.setAttribute("currnetPartyId",partyId);
		return "page/partydetial";
	}

	@RequestMapping("updateperson")
	@ResponseBody
	public String savePerson(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = partyService.updatePerson(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}

	@RequestMapping(value = "creategroup",method = RequestMethod.POST)
	@ResponseBody
	public String createGroup( HttpServletRequest request  ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = partyService.createPartyGroup(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}

	@RequestMapping("updategroup")
	@ResponseBody
	public String saveGroup(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = partyService.updatePartyGroup(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}

	@RequestMapping("createUserLogin")
	@ResponseBody
	public String createUserLogin(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = userLoginService.createUserLogin(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}

	@RequestMapping("saveUserLogin")
	@ResponseBody
	public String saveUserLogin(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = userLoginService.saveUserLogin(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}

	@RequestMapping("savePartyDesc")
	@ResponseBody
	public String savePartyDesc(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = partyService.updatePartyDesc(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}

		return 	UtilMessages.errorResponse(request, result);
	}





	@RequestMapping("removeUserLogin")
	@ResponseBody
	public String removeUserLogin(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		Map<String,Object> result = userLoginService.removeUserLogin(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}

	@RequestMapping("enableUserLogin")
	@ResponseBody
	public String enableUserLogin(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		context.put("enabled","Y");

		Map<String,Object> result = userLoginService.changeUserLoginStatus(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}

	@RequestMapping("disableUserLogin")
	@ResponseBody
	public String disableUserLogin(HttpServletRequest request,HttpServletResponse response ){
		Map<String,Object> context = UtilHttp.getParameterMap(request);
		context.put("enabled","N");
		Map<String,Object> result = userLoginService.changeUserLoginStatus(context);
		if(UtilMessages.isSuccess(result)){
			return  UtilMessages.successResponse(request);
		}
		return 	UtilMessages.errorResponse(request, result);
	}


	@RequestMapping(value = "beforeimport")
	public String beforeImportParty(HttpServletRequest request){
		return "page/importparty";
	}


	/**
	 * 功能描述：导入状态
	 */
	@RequestMapping(value = "importStatus")
	@ResponseBody
	public String  importStatus(HttpServletRequest request, String categoryKey) {
		List<UtilMessageCache.MessageItem> messageList = UtilMessageCache.getMessage(categoryKey);
		request.setAttribute("messageList",messageList);
		Map<String, Object> attrMap = UtilHttp.getJSONAttributeMap(request, "messageList");
		JSONObject json = JSONObject.fromObject(attrMap);
		return json.toString();
	}

	/**
	 * 功能描述：导入用户
	 */
	@RequestMapping(value = "importfromfile" ,method = RequestMethod.POST)
	public String importPartyUser( HttpServletRequest request, @RequestParam(value = "theFile", required = false) MultipartFile theFile)
	{
		final Map<String ,Object > context = UtilHttp.getParameterMap(request);
		Locale locale = (Locale) context.get("locale");
		String categoryKey =  UUID.randomUUID().toString();

		try {
			final File tmp = File.createTempFile(categoryKey, ".xls");
			InputStream in = theFile.getInputStream();
			FileOutputStream out = new FileOutputStream(tmp);
			UtilIO.copy(in, true, out, true);
			context.put("categoryKey",categoryKey);
			context.put("filePath",tmp.getAbsolutePath());
			Thread t = new Thread(){
				public void run(){
					partyService.importPersonFromFile(context);
					tmp.delete();
				}
			};
			t.start();
			UtilMessages.saveMessages(request,"上传用户文件成功，开始导入数据");
			request.setAttribute("categoryKey",categoryKey);
		}catch (Exception e){
			UtilMessages.saveErrors(request, "上传用户文件出错");
		}

		return "page/importparty";
	}
}
