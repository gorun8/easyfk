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
 * Author:hezhiping   Email:110476592@qq.com   Date: 15-12-11
 */
package cn.gorun8.easyfk.party.controller;

import cn.gorun8.easyfk.base.util.UtilHttp;
import cn.gorun8.easyfk.base.util.UtilMessages;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.util.UtilEntity;
import cn.gorun8.easyfk.party.service.PartyContactMechService;
import cn.gorun8.easyfk.party.service.PartyService;
import cn.gorun8.easyfk.security.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller("partyPortalController")
@RequestMapping("/dyn/portal")
public class PartyPortalController {

    @Autowired
    protected UserLoginService userLoginService;
    @Autowired
    protected PartyService partyService;

    @Autowired
    protected PartyContactMechService partyContactMechService;

    @RequestMapping("partyinfo")
    public String partyinfo(HttpServletRequest request){
        Map<String,Object> context = UtilHttp.getParameterMap(request);
        String partyId = (String) context.get("currnetPartyId");
        context.put("partyId",partyId);
        request.setAttribute("currnetPartyId",partyId);


        Map<String,Object> result = partyService.findPartyById(context);
        if(UtilMessages.isSuccess(result)){
            GenericValue party = (GenericValue)result.get(UtilMessages.RESPONSE_DATA);
            if(party != null){
                request.setAttribute("party", party.toMap());
            }
        }
        return "page/portal/partyinfo";
    }

    @RequestMapping("partycontact")
    public String partycontact(HttpServletRequest request){
        Map<String,Object> context = UtilHttp.getParameterMap(request);
        String partyId = (String) context.get("currnetPartyId");
        context.put("partyId",partyId);
        request.setAttribute("currnetPartyId",partyId);

        Map<String,Object> result = partyContactMechService.findPartyContactMech(context);
        if(UtilMessages.isSuccess(result)){
            List<Map> partyContactMechList = (List<Map>)result.get(UtilMessages.RESPONSE_DATA);
            if(partyContactMechList != null){
                request.setAttribute("partyContactMechList", partyContactMechList);
            }
        }

        return "page/portal/partycontact";
    }

    @RequestMapping("partyuserlogin")
    public String partyuserlogin(HttpServletRequest request){
        Map<String,Object> context = UtilHttp.getParameterMap(request);
        String currnetPartyId = (String) context.get("currnetPartyId");
        request.setAttribute("currnetPartyId",currnetPartyId);

        Map<String,Object> result = userLoginService.findPartyUserLogin(context);
        if(UtilMessages.isSuccess(result)){
            List<GenericValue> userLoginList =(List<GenericValue>) result.get(UtilMessages.RESPONSE_DATA);
            if(userLoginList != null){
                request.setAttribute("userLoginList", UtilEntity.toMap(userLoginList));
            }
        }
        return "page/portal/partyuserlogin";
    }

    @RequestMapping("partycalendar")
    public String partycalendar(HttpServletRequest request){
        return "page/portal/partycalendar";
    }


}
