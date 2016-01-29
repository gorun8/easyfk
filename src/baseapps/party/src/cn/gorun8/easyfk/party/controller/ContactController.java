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
 * Author:hezhiping   Email:110476592@qq.com   Date: 16-1-12
 */
package cn.gorun8.easyfk.party.controller;


import cn.gorun8.easyfk.base.util.UtilHttp;
import cn.gorun8.easyfk.base.util.UtilMessages;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.page.UtilPage;
import cn.gorun8.easyfk.entity.util.UtilEntity;
import cn.gorun8.easyfk.party.service.PartyContactMechService;
import javolution.util.FastMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller("contactController")
@RequestMapping("/dyn/contact")
public class ContactController {

    @Autowired
    protected PartyContactMechService partyContactMechService;

    @RequestMapping("findPartyContactMech")
    public String findPartyContactMech(HttpServletRequest request){

        Map<String,Object> context = UtilHttp.getParameterMap(request);
        Locale locale = (Locale) context.get("locale");

        String partyId = (String) context.get("partyId");
        context.put("partyId",partyId);

        request.setAttribute("partyId",partyId);

        Map<String,Object> result = partyContactMechService.findPartyContactMech(context);
        if(UtilMessages.isSuccess(result)){
            List<GenericValue> partyContactMechList = (List<GenericValue>)result.get(UtilMessages.RESPONSE_DATA);
            if(partyContactMechList != null){
                request.setAttribute("partyContactMechList", UtilEntity.toMap(partyContactMechList));
            }
        }else {
            String error = UtilMessages.getErrorMessage(result);
            UtilMessages.saveErrors(request,error);
        }

        return "page/partycontactmech";
    }

    @RequestMapping("createEmail")
    @ResponseBody
    public String createEmailAddress( HttpServletRequest request  ){
        Map<String,Object> context = UtilHttp.getParameterMap(request);
        Map<String,Object> result = partyContactMechService.createPartyEmailAddress(context);
        if(UtilMessages.isSuccess(result)){
            return  UtilMessages.successResponse(request);
        }
        return 	UtilMessages.errorResponse(request, result);
    }


    @RequestMapping("createPartyTel")
    @ResponseBody
    public String createPartyTelecomNumber( HttpServletRequest request  ){
        Map<String,Object> context = UtilHttp.getParameterMap(request);
        Map<String,Object> result = partyContactMechService.createPartyTelecomNumber(context);
        if(UtilMessages.isSuccess(result)){
            return  UtilMessages.successResponse(request);
        }
        return 	UtilMessages.errorResponse(request, result);
    }

    @RequestMapping("createPartyPostal")
    @ResponseBody
    public String createPartyPostalAddress( HttpServletRequest request  ){
        Map<String,Object> context = UtilHttp.getParameterMap(request);
        Map<String,Object> result = partyContactMechService.createPartyPostalAddress(context);
        if(UtilMessages.isSuccess(result)){
            return  UtilMessages.successResponse(request);
        }
        return 	UtilMessages.errorResponse(request, result);
    }

}
