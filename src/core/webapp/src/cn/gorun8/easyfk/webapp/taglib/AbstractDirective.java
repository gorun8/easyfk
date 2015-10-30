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
