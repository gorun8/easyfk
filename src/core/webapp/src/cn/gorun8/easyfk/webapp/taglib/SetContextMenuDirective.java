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
 * 设备菜单数据
 * 菜单数据会被放入当前请求的上下文，在风格模板中，可以根据名字(menuName)找到并
 * 使用这些菜单数据，以便展现出菜单。
 * 用法一
 * <p/>
 * <@easyfkSetAppMenu menuName="..."  focusItemIds= "..." location="component://...."/>
 * <p/>
 * menuName :可选的，可以为任意字符，但必须与模板中引用该菜单数据时的名称相同。如果没有设置，默认为EASYFK_CONTEXT_MENU.
 * focusItemIds:可选的，当前焦点的菜单ID列表，多个ID用JSON数组管理。
 * 如：focusItemIds= "{[id1,id2,id3]}"
 * 如果设置了，其值将设置于EASYFK_CONTEXT_MENU_FOCUS_IDS中。
 * location：可选的，指定模板文件
 * 用法二：
 * <@easyfkSetAppMenu menuName="..." focusItemIds= "..." >
 * 菜单数据
 * </@easyfkSetAppMenu>
 * 注意：方法一和方法二不能混合使用，如果使用了，优先使用方法一的方式处理，而在这种情况下，方法二的数据会被丢弃。
 *建议：菜单数据必须用JSON描述。具体是在服务器端渲染，还是放到客户端进行这项工作，由风格模板决定。
 * 并且所有数据都存在MENU_DATA的项目中。
 * 菜单的JSON数据结构如下：
 * {[{ id:'菜单ID', title:'菜单标题', desc:'菜单描述', style='css  class 名称',  href='', target='', sub:[子菜单对象]}]}
 *  id:  必须项，菜单ID，在整个菜单中必须唯一。
 *  title:必须项，菜单标题
 *  desc:必须项，菜单描述
 *  style:可选项，CSS class
 *  style2:可选项，CSS class
 *  href: 可选项，菜单URL，可以为javascript。如： href="javascript:alert();"
 *  target:可选项，超链接的target，如_bank,_self等
 *  toggle:可选项，关联数据data-toggle
 * 如：
 * {MENU_DATA:[
 *    {
 *    id:'mid1',
 *    title:'菜单1',
 *        desc:'菜单1',
 *        style:'css  class 名称',
 *        style2:'css  class 名称',
 *       href:'ddd',
 *       toggle:'modal'
 *       target:"_self",
 *},
 * {id:'contextNewMenuId',title:'新建',desc:'新建会员组',toggle:'modal',href:'#newPartyGroupFormPanel',style:'btn btn-primary tip-bottom',style2:' icon-plus-sign icon-white'},
 ]}
 * 注：虽然菜单是可以限制级扩展的，但是不建议使用超过两级的菜单。
 * 权限：通过freemarker控制数据生成即可以实现。
 */
public class SetContextMenuDirective implements TemplateDirectiveModel {
    public static final String module = SetContextMenuDirective.class.getName();
    public static final String DEFAULT_CONTEXT_EASYFK_MENU_NAME = "EASYFK_CONTEXT_MENU";
    public static final String EASYFK_CONTEXT_MENU_FOCUS_IDS = "EASYFK_CONTEXT_MENU_FOCUS_IDS";

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {
        Writer out = env.getOut();
        BeanModel req = (BeanModel) env.getVariable("httpServletRequest");
        Object wrappedObject = req.getWrappedObject();

        HttpServletRequest request = (HttpServletRequest) wrappedObject;
        String location = "";
        String menuName = DEFAULT_CONTEXT_EASYFK_MENU_NAME;
        String focusItemIds = "";

        SimpleScalar nameObject = (SimpleScalar) params.get("menuName");
        SimpleScalar locationObject = (SimpleScalar) params.get("location");
        SimpleScalar focusIdsObject = (SimpleScalar) params.get("focusItemIds");


        if (nameObject != null ){
            menuName = nameObject.getAsString();
        }
        if(locationObject != null) {
            location = locationObject.getAsString();
        }//endif

        if(focusIdsObject != null) {
            focusItemIds = focusIdsObject.getAsString();
        }//endif

        if (UtilValidate.isNotEmpty(location)){
            CharArrayWriter bufferedWriter = new CharArrayWriter();
            Map<String, Object> httpRequestHashModel = UtilHttp.getAttributeMap(request);
            Configuration configuration = env.getConfiguration();
            Template template = configuration.getTemplate(location);
            template.process(httpRequestHashModel, bufferedWriter);
            try {
                JSONObject jsonObject = JSON.parseObject(bufferedWriter.toString());
                request.setAttribute(menuName, jsonObject);
                request.setAttribute(EASYFK_CONTEXT_MENU_FOCUS_IDS, focusItemIds);
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
                    request.setAttribute(menuName, jsonObject);
                    request.setAttribute(EASYFK_CONTEXT_MENU_FOCUS_IDS, focusItemIds);
                }catch (Exception e){
                    Debug.logError(e.toString(),module);
                }

            }//if
        }
    }
}
