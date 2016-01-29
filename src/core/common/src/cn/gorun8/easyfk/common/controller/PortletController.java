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
 * Author:hezhiping   Email:110476592@qq.com   Date: 15-12-8
 */
package cn.gorun8.easyfk.common.controller;

import cn.gorun8.easyfk.base.util.UtilHttp;
import cn.gorun8.easyfk.base.util.UtilMessages;
import cn.gorun8.easyfk.common.service.PortalService;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.util.UtilEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller("portletController")
@RequestMapping("/dyn/portlet")
public class PortletController {

    @Autowired
    private PortalService portalService;

    @RequestMapping("getPagePortal")
    @ResponseBody
    public String getPagePortal(HttpServletRequest request){
        Map<String,Object> context = UtilHttp.getParameterMap(request);

        Map<String,Object> result = portalService.findPagePortal(context);
        if(UtilMessages.isSuccess(result)){
            List<GenericValue> pagePortalList = (List<GenericValue>) result.get(UtilMessages.RESPONSE_DATA);
            List<GenericValue> pageColumnList = (List<GenericValue>) result.get("pageColumnList");
            request.setAttribute("pagePortalList", UtilEntity.toMap(pagePortalList));
            request.setAttribute("pageColumnList",  UtilEntity.toMap(pageColumnList));
            return UtilMessages.successResponse("pagePortalList,pageColumnList",request);
        }else {
            return UtilMessages.errorResponse(request,result);
        }
    }
}
