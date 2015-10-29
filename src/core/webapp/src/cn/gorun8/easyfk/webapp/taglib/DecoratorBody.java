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
