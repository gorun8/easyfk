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
package cn.gorun8.easyfk.webapp.ftl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.servlet.support.RequestContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cn.gorun8.easyfk.base.component.ComponentConfig;
import cn.gorun8.easyfk.base.component.ComponentConfig.WebappInfo;
import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.base.util.UtilXml;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateTransformModel;

/**
 * EasyFKUrlTransform - Freemarker Transform for URLs (links)
 */
public class EasyFKUrlTransform implements TemplateTransformModel {

    public final static String module = EasyFKUrlTransform.class.getName();

    @SuppressWarnings("unchecked")
    public boolean checkArg(Map args, String key, boolean defaultValue) {
        if (!args.containsKey(key)) {
            return defaultValue;
        } else {
            Object o = args.get(key);
            if (o instanceof SimpleScalar) {
                SimpleScalar s = (SimpleScalar) o;
                return "true".equalsIgnoreCase(s.getAsString());
            }
            return defaultValue;
        }
    }

    @SuppressWarnings("unchecked")
    public Writer getWriter(final Writer out, Map args) {
        final StringBuilder buf = new StringBuilder();
        final boolean fullPath = checkArg(args, "fullPath", false);
        final boolean secure = checkArg(args, "secure", false);
        final boolean encode = checkArg(args, "encode", true);
        final String webSiteId = getArg(args, "webSiteId");

        return new Writer(out) {
            @Override
            public void write(char cbuf[], int off, int len) {
                buf.append(cbuf, off, len);
            }

            @Override
            public void flush() throws IOException {
                out.flush();
            }

            @Override
            public void close() throws IOException {
                try {
                    Environment env = Environment.getCurrentEnvironment();
                    BeanModel req = (BeanModel) env.getVariable("request");
                    BeanModel res = (BeanModel) env.getVariable("response");
                    Object prefix = env.getVariable("urlPrefix");
                    if (req != null) {
                    	RequestContext request = (RequestContext) req.getWrappedObject();
                    	String requestUrl = buf.toString();
                    	if(UtilValidate.isEmpty(requestUrl) || !requestUrl.startsWith("/"))
                    	{
                    		requestUrl = "/" + requestUrl ;  
                    	}
                    	
                    	String newUrl = request.getContextUrl(requestUrl);
                    	out.write(newUrl);
                    }else {
                        out.write(buf.toString());
                    }
                } catch (TemplateModelException e) {
                    throw new IOException(e.getMessage());
                }
            }
        };
    }
    private static String getArg(Map args, String key) {
        String  result = "";
        Object o = args.get(key);
        if (o != null) {
            if (Debug.verboseOn()) Debug.logVerbose("Arg Object : " + o.getClass().getName(), module);
            if (o instanceof TemplateScalarModel) {
                TemplateScalarModel s = (TemplateScalarModel) o;
                try {
                    result = s.getAsString();
                } catch (TemplateModelException e) {
                    Debug.logError(e, "Template Exception", module);
                }
            } else {
              result = o.toString();
            }
        }
        return result;
    }
    private static String getTagValue(String sTag, Element eElement){
    String value = "";
        try{
            NodeList nlList= eElement.getElementsByTagName(sTag).item(0).getChildNodes();
            Node nValue = (Node) nlList.item(0);
            return value = nValue.getNodeValue();
        } catch (Exception e) {
            return value;
        }
    }
}
