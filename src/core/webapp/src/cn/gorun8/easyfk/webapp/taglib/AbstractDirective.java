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

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.RequestContext;

import cn.gorun8.easyfk.base.util.UtilHttp;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.webapp.ftl.EasyFKFreeMarkerConfigurer;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

 
public class AbstractDirective implements TemplateDirectiveModel {
    protected ThemePart themePart;
	public AbstractDirective(ThemePart themePart)
	{
		this.themePart = themePart ;
	}

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
			Writer out = env.getOut();
			String file = getThemePart(themePart.toString(),params);
			//读取freemark 文件内容并输出
			try {
				BeanModel req = (BeanModel)env.getVariable("httpServletRequest");
				HttpServletRequest request = null;
				if( req != null)
				{
					Object wrappedObject = req.getWrappedObject();
					request = (HttpServletRequest)wrappedObject;
				}

				Map<String, Object> httpRequestHashModel = UtilHttp.getAttributeMap(request);
				Configuration configuration = env.getConfiguration(); 
				Template template = configuration.getTemplate(file);
				out.write("<!-- "+ file + " begin-->\r\n");
				template.process(httpRequestHashModel, out);
				out.write("<!-- "+ file + " end-->\r\n");

			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public   String getThemePart(String templateName, Map params)
	{
		String theme = UtilProperties.getPropertyValue("easyfk-general","VISUAL_THEME");
		if(UtilValidate.isEmpty(theme))
		{
			theme ="default";
		}
	
		String themeconf = theme+"-theme"; 
		String themeItem = "easyfk.themes."+theme+"."+templateName; //+".VT_HDR_TMPLT_LOC";
		String file = UtilProperties.getPropertyValue(themeconf,themeItem);
		return file;
	}

}
