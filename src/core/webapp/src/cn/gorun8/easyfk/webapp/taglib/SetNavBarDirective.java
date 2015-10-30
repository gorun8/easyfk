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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
 * 设置导航栏数据
 * 导航数据会被放入当前请求的上下文，在风格模板中，可以在上下文件的EASYFK_NAV_BAR找到并
 * 使用这些数据，以便展现。
 * 用法一
 * <p/>
 * <@easyfkSetNavBar  location="component://...."  subtitle=""/>
 * <p/>
 *  subtitle:导航子标题,其值放设置于上下文件的EASYFK_SUB_TITLE中。
 * location：可选的，指定模板文件
 * 用法二：
 * <@easyfkSetNavBar   subtitle="">
 * 导航数据
 * </@easyfkSetNavBar>
 * 注意：方法一和方法二不能混合使用，如果使用了，优先使用方法一的方式处理，而在这种情况下，方法二的数据会被丢弃。
 *建议：导航数据必须用JSON描述。具体是在服务器端渲染，还是放到客户端进行这项工作，由风格模板决定。
 * 并且所有数据都存在NAV_BAR_DATA的项目中。
 * 导航的JSON数据结构如下：
 * {[{ id:'菜单ID', title:'菜单标题', desc:'菜单描述', style='css  class 名称',  href='', target=''}]}
 *  id:  可选项，菜单ID，在整个菜单中必须唯一。
 *  title:必须项，菜单标题
 *  tip:可选项项，菜单描述
 *  style:可选项，CSS class
 *  href: 可选项，菜单URL，可以为javascript。如： href="javascript:alert();"
 * 如：
 * {NAV_BAR_DATA:[{id:'navid1',title:'首页',tip:'返回首页',style:'icon-home',href:'${ctx}'},
 *{id:'navid2',title:'会员组'}
 *]}
 * 权限：通过freemarker控制数据生成即可以实现。
 */
public class SetNavBarDirective implements TemplateDirectiveModel {
    public static final String module = SetAppMenuDirective.class.getName();
    public static final String EASYFK_NAV_BAR = "EASYFK_NAV_BAR";
    public static final String EASYFK_SUB_TITLE = "EASYFK_SUB_TITLE";

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {
        Writer out = env.getOut();
        BeanModel req = (BeanModel) env.getVariable("httpServletRequest");
        Object wrappedObject = req.getWrappedObject();

        HttpServletRequest request = (HttpServletRequest) wrappedObject;
        String location = "";
        String subTitle = "";


        SimpleScalar locationObject = (SimpleScalar) params.get("location");
        SimpleScalar subTitleObject = (SimpleScalar) params.get("subTitle");


        if(locationObject != null) {
            location = locationObject.getAsString();
        }//endif

        if(subTitleObject != null) {
            subTitle = subTitleObject.getAsString();
        }//endif
        request.setAttribute(EASYFK_SUB_TITLE, subTitle);

        if (UtilValidate.isNotEmpty(location)){
            CharArrayWriter bufferedWriter = new CharArrayWriter();
            Map<String, Object> httpRequestHashModel = UtilHttp.getAttributeMap(request);
            Configuration configuration = env.getConfiguration();
            Template template = configuration.getTemplate(location);
            template.process(httpRequestHashModel, bufferedWriter);
            try {
                JSONObject jsonObject = JSON.parseObject(bufferedWriter.toString());
                request.setAttribute(EASYFK_NAV_BAR, jsonObject);
            }catch (Exception e){
                Debug.logError(e.toString(),module);
            }
        }else{
            if (body != null) {
                CharArrayWriter bufferedWriter = new CharArrayWriter();
                body.render(bufferedWriter);
                String buf = bufferedWriter.toString();
                try {
                    JSONObject jsonObject = JSON.parseObject(bufferedWriter.toString());
                    request.setAttribute(EASYFK_NAV_BAR, jsonObject);
                }catch (Exception e){
                    Debug.logError(e.toString(),module);
                }

            }//if
        }
    }
}
