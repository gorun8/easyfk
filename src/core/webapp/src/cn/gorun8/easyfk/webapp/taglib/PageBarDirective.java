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


import com.github.pagehelper.Page;
import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilHttp;
import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * 分页模板
 * 使用
 * <@easyfkPageBar pageUrl=""></@easyfkPageBar>
 *
 */
public class PageBarDirective extends AbstractDirective {

	private  final  static  String module = PageBarDirective.class.getName();
	public PageBarDirective() {
		super(ThemePart.VT_PAGE_BAR_LOC);
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
			if( req == null) {
				Debug.logError("上下文件环境中找不到HttpServletRequest",module);
				return ;
			}

			Object wrappedObject = req.getWrappedObject();
			request = (HttpServletRequest)wrappedObject;

			Page page = (Page) request.getAttribute("PAGE_DATA");
			if(page == null)
			{
				Debug.logError("没有找到分页信息",module);
				return ;
			}

			String pageUrl  = "";


			SimpleScalar pageUrlObject = (SimpleScalar) params.get("pageUrl");
			if(pageUrlObject != null) {
				pageUrl = pageUrlObject.getAsString();
			}//endif

			Map<String, Object> httpRequestHashModel = UtilHttp.getAttributeMap(request);
			long listSize = page.getTotal();
			long viewSize = page.getPageSize();
			long viewIndex = page.getPageNum();
			long pages = page.getPages();
			long startShowIndex = pages - 5 ;
			long endShowIndex = pages + 5;

			if(startShowIndex <= 0){
				startShowIndex = 1L;
			}

			if(endShowIndex > pages){
				endShowIndex = pages;
			}

			if (viewIndex > 1)
			{
				httpRequestHashModel.put("hasPrev",Boolean.valueOf(true));
			}

			if(viewIndex < pages)
			{
				httpRequestHashModel.put("hasNext",Boolean.valueOf(true));
			}

			httpRequestHashModel.put("startShowIndex",Long.valueOf(startShowIndex));
			httpRequestHashModel.put("endShowIndex",Long.valueOf(endShowIndex));

			httpRequestHashModel.put("viewIndex",Long.valueOf(viewIndex));
			httpRequestHashModel.put("viewSize",Long.valueOf(viewSize));
			httpRequestHashModel.put("pageCount",Long.valueOf(pages));
			httpRequestHashModel.put("pageUrl",pageUrl);

			Configuration configuration = env.getConfiguration();
			Template template = configuration.getTemplate(file);
			out.write("<!-- "+ file + " begin-->\r\n");
			template.process(httpRequestHashModel, out);
			out.write("<!-- "+ file + " end-->\r\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
