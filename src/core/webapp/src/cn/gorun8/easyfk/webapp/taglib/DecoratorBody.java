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
import cn.gorun8.easyfk.base.util.UtilValidate;
import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.*;

import javax.servlet.http.HttpServletRequest;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * 指定页面修改模板的锚点位置
 * <@easyfkDecoratorBody name="..."/>
 * name:必须与<@easyfkDecoratorScreen>中的name对应
 */
public class DecoratorBody implements TemplateDirectiveModel {
    public static final String module = DecoratorBody.class.getName();

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {
        Writer out = env.getOut();

        BeanModel req = (BeanModel) env.getVariable("httpServletRequest");
        Object wrappedObject = req.getWrappedObject();
        HttpServletRequest request = (HttpServletRequest) wrappedObject;
        if (UtilValidate.isEmpty(params)) {
            Debug.logError("请指定锚点名称", module);
            return;
        }

        Object tmpObject = params.get("name");
        if (UtilValidate.isEmpty(tmpObject)) {
            Debug.logError("请指定锚点名称", module);
            return;
        }

        SimpleScalar nameObject = (SimpleScalar) tmpObject;
        String name = nameObject.getAsString();

        tmpObject = request.getAttribute(name);

        if (tmpObject != null) {
            CharArrayWriter bufferedWriter = (CharArrayWriter) tmpObject;
            char[] buf = bufferedWriter.toCharArray();
            out.write(buf);
            request.removeAttribute(name);
        }//endif

    }
}
