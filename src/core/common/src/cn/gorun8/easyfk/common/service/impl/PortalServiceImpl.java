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
package cn.gorun8.easyfk.common.service.impl;


import cn.gorun8.easyfk.base.util.UtilMessages;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.common.dao.PortletReadDao;
import cn.gorun8.easyfk.common.service.PortalService;
import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;
import javolution.util.FastMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service("portalServiceImpl")
public class PortalServiceImpl implements PortalService {
    public static final String module = PortalServiceImpl.class.getName();
    public static final String resource = "CommonUiLabels";
    public static final String resourceError = "CommonErrorUiLabels";

    @Autowired
    protected PortletReadDao portletReadDao;


    @Override
    public Map<String,Object> findPagePortal(Map<String,Object> context){
        Locale locale = (Locale) context.get("locale");
        //GenericValue userLogin = (GenericValue) context.get("userLogin");
        String portalPageId =   (String) context.get("portalPageId");

        if(UtilValidate.isEmpty(portalPageId)){
            return UtilMessages.returnParamError(locale, "portalPageId");
        }

//        if(UtilValidate.isEmpty(userLogin)){
//            return UtilMessages.returnParamError(locale, "userLogin");
//        }
//
//        String ownerUserLoginId =(String)userLogin.getString("userLogin");

        try {

            List<GenericValue>  pageColumnList = portletReadDao.findPortalPageColumn(portalPageId);
            List<GenericValue>  pageList = portletReadDao.findPortalByPageId(portalPageId);
            Map<String, Object> result = UtilMessages.returnSuccessWithData(pageList);
            result.put("pageColumnList",pageColumnList);
            return result;
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "find.portal.page.err", locale));
        }
    }
}
