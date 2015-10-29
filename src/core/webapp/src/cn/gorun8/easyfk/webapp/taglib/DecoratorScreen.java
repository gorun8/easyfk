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
import org.apache.commons.io.output.ByteArrayOutputStream;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * 使用页面修饰模板指令
 * <p/>
 * <@easyfkDecoratorScreen name="..." location="component://....">
 * ....
 * </@easyfkDecoratorScreen>
 * <p/>
 * name :可以为任意字符
 * location：指定模板文件
 */
public class DecoratorScreen implements TemplateDirectiveModel {
    public static final String module = DecoratorScreen.class.getName();

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {

        Writer out = env.getOut();
        BeanModel req = (BeanModel) env.getVariable("httpServletRequest");
        Object wrappedObject = req.getWrappedObject();
        HttpServletRequest request = (HttpServletRequest) wrappedObject;

        String location = "";
        String name = "";

        SimpleScalar nameObject = (SimpleScalar) params.get("name");
        SimpleScalar locationObject = (SimpleScalar) params.get("location");
        if (nameObject != null && locationObject != null) {
            location = locationObject.getAsString();
            name = nameObject.getAsString();
        }//endif

        if (UtilValidate.isEmpty(location) || UtilValidate.isEmpty(name)) {
            Debug.logError("必须指定location和name", module);
            throw new TemplateException("必须指定location和name",env);
        }

        if (body != null) {
            CharArrayWriter bufferedWriter = new CharArrayWriter();
            body.render(bufferedWriter);
            String buf = bufferedWriter.toString();
            request.setAttribute(name, bufferedWriter);
        }//if

        Map<String, Object> httpRequestHashModel = UtilHttp.getAttributeMap(request);
        Configuration configuration = env.getConfiguration();
        Template template = configuration.getTemplate(location);

        Boolean verbose = UtilProperties.getPropertyAsBoolean("easyfk-general", "widget.verbose", true);

        if(verbose) {
            out.write("<!-- " + location + " begin-->\r\n");
        }
        template.process(httpRequestHashModel, out);
        if(verbose) {
            out.write("<!-- " + location + " end-->\r\n");
        }

    }
}
