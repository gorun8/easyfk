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
