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
package cn.gorun8.easyfk.webapp.taglib;


import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilHttp;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;
import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * 登录页面模板
 *
 */
public class ErrorDirective extends AbstractDirective {

	public ErrorDirective() {
		super(ThemePart.VT_ERR_PAGE_LOC);
	}
	@Override
	public   String getThemePart(String templateName, Map params)
	{
		String theme = UtilProperties.getPropertyValue("easyfk-general", "VISUAL_THEME");
		if(UtilValidate.isEmpty(theme))
		{
			theme ="default";
		}
		String themeconf = theme+"-theme";
		String themeItem = "easyfk.themes."+theme+"."+templateName;

		if (UtilValidate.isNotEmpty(params)) {
			Object tmpObject = params.get("errorCode");
			if (UtilValidate.isNotEmpty(tmpObject)){
				SimpleScalar errorCode = (SimpleScalar) tmpObject;
				String code = errorCode.getAsString();
				themeItem =themeItem + "." + code;
			}
		}

		String file = UtilProperties.getPropertyValue(themeconf,themeItem);
		return file;
	}
	 
}
