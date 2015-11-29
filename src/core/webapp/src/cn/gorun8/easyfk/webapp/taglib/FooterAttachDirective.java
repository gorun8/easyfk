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
 * 头部附加内容
 * 该指令用于设置需要加入到</html>之前的内容，一般用于指定
 * js脚本，。
 * 可以同时使用引用外部文件和本地内容，如
 * <@easyfkFooterAttach location="component://sss/sss.js">
 *    <script >
 *    ....
 *    </script>
 * </@easyfkFooterAttach>
 * 注意：本地内容会被加载到外部文件的后面。
 */

public class FooterAttachDirective implements TemplateDirectiveModel {
    public static final String module = FooterAttachDirective.class.getName();
    public static final String EASYFK_FOOTER_ATTACH = "EASYFK_FOOTER_ATTACH";

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {
        Writer out = env.getOut();
        BeanModel req = (BeanModel) env.getVariable("httpServletRequest");
        Object wrappedObject = req.getWrappedObject();

        HttpServletRequest request = (HttpServletRequest) wrappedObject;
        String location = "";

        SimpleScalar locationObject = (SimpleScalar) params.get("location");
        if (locationObject != null) {
            location = locationObject.getAsString();
        }//endif

        StringBuffer buffer = new StringBuffer();
        if (UtilValidate.isNotEmpty(location)) {
            CharArrayWriter bufferedWriter = new CharArrayWriter();
            Map<String, Object> httpRequestHashModel = UtilHttp.getAttributeMap(request);
            Configuration configuration = env.getConfiguration();
            Template template = configuration.getTemplate(location);
            template.process(httpRequestHashModel, bufferedWriter);
            buffer.append(bufferedWriter);
        }

        if (body != null) {
            CharArrayWriter bufferedWriter = new CharArrayWriter();
            body.render(bufferedWriter);
            buffer.append(bufferedWriter);
        }//if

        request.setAttribute(EASYFK_FOOTER_ATTACH, buffer.toString());
    }
}
