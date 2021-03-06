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
 
package cn.gorun8.easyfk.base.component;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import cn.gorun8.easyfk.base.util.UtilGenerics;
import cn.gorun8.easyfk.base.util.UtilURL;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.base.util.UtilXml;
import cn.gorun8.easyfk.base.util.string.FlexibleStringExpander;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * ComponentLoaderConfig - Component Loader configuration
 *
 */
public class ComponentLoaderConfig {

    public static final String module = ComponentLoaderConfig.class.getName();
    public static final String COMPONENT_LOAD_XML_FILENAME = "component-load.xml";

    public static final int SINGLE_COMPONENT = 0;
    public static final int COMPONENT_DIRECTORY = 1;

    protected static List<ComponentDef> componentsToLoad = null;

    public static List<ComponentDef> getRootComponents(String configFile) throws ComponentException {
        if (componentsToLoad == null) {
            synchronized (ComponentLoaderConfig.class) {
                if (componentsToLoad == null) {
                    if (configFile == null) {
                        configFile = COMPONENT_LOAD_XML_FILENAME;
                    }
                    URL xmlUrl = UtilURL.fromResource(configFile);
                    ComponentLoaderConfig.componentsToLoad = ComponentLoaderConfig.getComponentsFromConfig(xmlUrl);
                }
            }
        }
        return componentsToLoad;
    }

    public static List<ComponentDef> getComponentsFromConfig(URL configUrl) throws ComponentException {
        if (configUrl == null) {
            throw new ComponentException("Component config file does not exist: " + configUrl);
        }

        List<ComponentDef> componentsFromConfig = null;
        Document document = null;
        try {
            document = UtilXml.readXmlDocument(configUrl, true);
        } catch (SAXException e) {
            throw new ComponentException("Error reading the component config file: " + configUrl, e);
        } catch (ParserConfigurationException e) {
            throw new ComponentException("Error reading the component config file: " + configUrl, e);
        } catch (IOException e) {
            throw new ComponentException("Error reading the component config file: " + configUrl, e);
        }

        Element root = document.getDocumentElement();
        List<? extends Element> toLoad = UtilXml.childElementList(root);
        if (UtilValidate.isNotEmpty(toLoad)) {
            componentsFromConfig = new LinkedList<ComponentDef>();
            for (Element element: toLoad) {
                componentsFromConfig.add(new ComponentDef(element));
            }
        }
        return componentsFromConfig;
    }

    public static class ComponentDef {
        public String name;
        public String location;
        public int type = -1;

        public ComponentDef(Element element) {
            Properties systemProps = System.getProperties();
            if ("load-component".equals(element.getNodeName())) {
                name = element.getAttribute("component-name");
                location = FlexibleStringExpander.expandString(element.getAttribute("component-location"), UtilGenerics.<String, Object>checkMap(systemProps));
                type = SINGLE_COMPONENT;
            } else if ("load-components".equals(element.getNodeName())) {
                name = null;
                location = FlexibleStringExpander.expandString(element.getAttribute("parent-directory"), UtilGenerics.<String, Object>checkMap(systemProps));
                type = COMPONENT_DIRECTORY;
            }
        }
    }
}
