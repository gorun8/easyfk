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
package cn.gorun8.easyfk.base.container;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import cn.gorun8.easyfk.base.lang.LockedBy;
import cn.gorun8.easyfk.base.util.UtilURL;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.base.util.UtilXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * ContainerConfig - Container configuration for easyfk.xml
 *
 */
public class ContainerConfig {

    public static final String module = ContainerConfig.class.getName();

    @LockedBy("ContainerConfig.class")
    private static Map<String, Container> containers = new LinkedHashMap<String, Container>();

    public static Container getContainer(String containerName, String configFile) throws ContainerException {
        Container container = containers.get(containerName);
        if (container == null) {
            getContainers(configFile);
            container = containers.get(containerName);
        }
        if (container == null) {
            throw new ContainerException("No container found with the name : " + containerName);
        }
        return container;
    }

    public static Collection<Container> getContainers(String configFile) throws ContainerException {
        if (UtilValidate.isEmpty(configFile)) {
            throw new ContainerException("configFile argument cannot be null or empty");
        }
        URL xmlUrl = UtilURL.fromResource(configFile);
        if (xmlUrl == null) {
            throw new ContainerException("Could not find container config file " + configFile);
        }
        return getContainers(xmlUrl);
    }

    public static Collection<Container> getContainers(URL xmlUrl) throws ContainerException {
        if (xmlUrl == null) {
            throw new ContainerException("xmlUrl argument cannot be null");
        }
        Collection<Container> result = getContainerPropsFromXml(xmlUrl);
        synchronized (ContainerConfig.class) {
            for (Container container : result) {
                containers.put(container.name, container);
            }
        }
        return result;
    }

    public static String getPropertyValue(ContainerConfig.Container parentProp, String name, String defaultValue) {
        ContainerConfig.Container.Property prop = parentProp.getProperty(name);
        if (prop == null || UtilValidate.isEmpty(prop.value)) {
            return defaultValue;
        } else {
            return prop.value;
        }
    }

    public static int getPropertyValue(ContainerConfig.Container parentProp, String name, int defaultValue) {
        ContainerConfig.Container.Property prop = parentProp.getProperty(name);
        if (prop == null || UtilValidate.isEmpty(prop.value)) {
            return defaultValue;
        } else {
            int num = defaultValue;
            try {
                num = Integer.parseInt(prop.value);
            } catch (Exception e) {
                return defaultValue;
            }
            return num;
        }
    }

    public static boolean getPropertyValue(ContainerConfig.Container parentProp, String name, boolean defaultValue) {
        ContainerConfig.Container.Property prop = parentProp.getProperty(name);
        if (prop == null || UtilValidate.isEmpty(prop.value)) {
            return defaultValue;
        } else {
            return "true".equalsIgnoreCase(prop.value);
        }
    }

    public static String getPropertyValue(ContainerConfig.Container.Property parentProp, String name, String defaultValue) {
        ContainerConfig.Container.Property prop = parentProp.getProperty(name);
        if (prop == null || UtilValidate.isEmpty(prop.value)) {
            return defaultValue;
        } else {
            return prop.value;
        }
    }

    public static int getPropertyValue(ContainerConfig.Container.Property parentProp, String name, int defaultValue) {
        ContainerConfig.Container.Property prop = parentProp.getProperty(name);
        if (prop == null || UtilValidate.isEmpty(prop.value)) {
            return defaultValue;
        } else {
            int num = defaultValue;
            try {
                num = Integer.parseInt(prop.value);
            } catch (Exception e) {
                return defaultValue;
            }
            return num;
        }
    }

    public static boolean getPropertyValue(ContainerConfig.Container.Property parentProp, String name, boolean defaultValue) {
        ContainerConfig.Container.Property prop = parentProp.getProperty(name);
        if (prop == null || UtilValidate.isEmpty(prop.value)) {
            return defaultValue;
        } else {
            return "true".equalsIgnoreCase(prop.value);
        }
    }

    private ContainerConfig() {}

    private static Collection<Container> getContainerPropsFromXml(URL xmlUrl) throws ContainerException {
        Document containerDocument = null;
        try {
            containerDocument = UtilXml.readXmlDocument(xmlUrl, true);
        } catch (SAXException e) {
            throw new ContainerException("Error reading the container config file: " + xmlUrl, e);
        } catch (ParserConfigurationException e) {
            throw new ContainerException("Error reading the container config file: " + xmlUrl, e);
        } catch (IOException e) {
            throw new ContainerException("Error reading the container config file: " + xmlUrl, e);
        }
        Element root = containerDocument.getDocumentElement();
        List<Container> result = new ArrayList<Container>();
        for (Element curElement: UtilXml.childElementList(root, "container")) {
            result.add(new Container(curElement));
        }
        return result;
    }

    public static class Container {
        public String name;
        public String className;
        public Map<String, Property> properties;

        public Container(Element element) {
            this.name = element.getAttribute("name");
            this.className = element.getAttribute("class");

            properties = new LinkedHashMap<String, Property>();
            for (Element curElement: UtilXml.childElementList(element, "property")) {
                Property property = new Property(curElement);
                properties.put(property.name, property);
            }
        }

        public Property getProperty(String name) {
            return properties.get(name);
        }

        public List<Property> getPropertiesWithValue(String value) {
            List<Property> props = new LinkedList<Property>();
            if (UtilValidate.isNotEmpty(properties)) {
                for (Property p: properties.values()) {
                    if (p != null && value.equals(p.value)) {
                        props.add(p);
                    }
                }
            }
            return props;
        }

        public static class Property {
            public String name;
            public String value;
            public Map<String, Property> properties;

            public Property(Element element) {
                this.name = element.getAttribute("name");
                this.value = element.getAttribute("value");
                if (UtilValidate.isEmpty(this.value)) {
                    this.value = UtilXml.childElementValue(element, "property-value");
                }

                properties = new LinkedHashMap<String, Property>();
                for (Element curElement: UtilXml.childElementList(element, "property")) {
                    Property property = new Property(curElement);
                    properties.put(property.name, property);
                }
            }

            public Property getProperty(String name) {
                return properties.get(name);
            }

            public List<Property> getPropertiesWithValue(String value) {
                List<Property> props = new LinkedList<Property>();
                if (UtilValidate.isNotEmpty(properties)) {
                    for (Property p: properties.values()) {
                        if (p != null && value.equals(p.value)) {
                            props.add(p);
                        }
                    }
                }
                return props;
            }
        }
    }
}
