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
 * Author:hezhiping   Email:110476592@qq.com   Date: 15-11-4
 */
package cn.gorun8.easyfk.base.util.collections;

import cn.gorun8.easyfk.base.util.UtilProperties;
import org.springframework.util.Assert;
import java.util.Locale;

/**
 * 实现国际化资源文件管理。资源文件建议使用XML文件，文件名建议使有xxUiLabels.xml。
 * 每个组件有独立的资源管理器，配置在WEB-INF/classes/private-context.xml中，配置如下：
 *
 * <bean   class="cn.gorun8.easyfk.base.util.collections.ResourceBundleMessageSource"
 *init-method="init">
 *<property name="labelMapName" value="..."></property>
 *<property name="basenames">
 *<list>
 *<value>PartyUiLabels</value>
 *<value></value>
 *</list>
 *</property>
 *</bean>
 * labelMapName ：资源的映射名称，建议名为：uiLabelMap。
 * 在ftl中，可以根据该名称引用资源，${uiLabelMap.SecurityGroupList}
 * resources:资源的文件名，可以指定多个资源文件，并且这些资源文件可以在其它组件中。
 */
public class ResourceBundleMessageSource extends ResourceBundleMapWrapper {
    private String[] basenames = new String[0];
    private String labelMapName;

    public String getLabelMapName() {
        return labelMapName;
    }

    public void setLabelMapName(String labelMapName) {
        this.labelMapName = labelMapName;
    }

    public void setBasename(String basename) {
        this.setBasenames(new String[]{basename});
    }

    public void setBasenames(String... basenames) {
        if(basenames != null) {
            this.basenames = new String[basenames.length];

            for(int i = 0; i < basenames.length; ++i) {
                String basename = basenames[i];
                Assert.hasText(basename, "Basename must not be empty");
                this.basenames[i] = basename.trim();
            }
        } else {
            this.basenames = new String[0];
        }
    }

    public void init(){
        Locale locale = Locale.CHINA;
        for (String resource :basenames){
            if(getInitialResourceBundle() == null) {
                setInitialResourceBundle(UtilProperties.getResourceBundle(resource, locale));
            }else {
                addBottomResourceBundle(resource);
            }
        }
    }
}
