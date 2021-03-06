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
package cn.gorun8.easyfk.base.config;

import java.io.InputStream;
import java.net.URL;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilURL;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.base.util.UtilXml;
import cn.gorun8.easyfk.base.util.cache.UtilCache;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Loads resources using dynamically specified resource loader classes.
 */
public abstract class ResourceLoader {

    public static final String module = ResourceLoader.class.getName();
    protected static UtilCache<String, Object> loaderCache = UtilCache.createUtilCache("resource.ResourceLoaders", 0, 0);

    protected String name;
    protected String prefix;
    protected String envName;

    public static InputStream loadResource(String xmlFilename, String location, String loaderName) throws GenericConfigException {
        ResourceLoader loader = getLoader(xmlFilename, loaderName);
        if (loader == null) {
            throw new IllegalArgumentException("ResourceLoader not found with name [" + loaderName + "] in " + xmlFilename);
        }
        return loader.loadResource(location);
    }

    public static URL getURL(String xmlFilename, String location, String loaderName) throws GenericConfigException {
        ResourceLoader loader = getLoader(xmlFilename, loaderName);
        if (loader == null) {
            throw new IllegalArgumentException("ResourceLoader not found with name [" + loaderName + "] in " + xmlFilename);
        }
        return loader.getURL(location);
    }

    public static ResourceLoader getLoader(String xmlFilename, String loaderName) throws GenericConfigException {
        ResourceLoader loader = (ResourceLoader) loaderCache.get(xmlFilename + "::" + loaderName);

        if (loader == null) {
            synchronized (ResourceLoader.class) {
                loader = (ResourceLoader) loaderCache.get(xmlFilename + "::" + loaderName);
                if (loader == null) {
                    Element rootElement = getXmlRootElement(xmlFilename);

                    Element loaderElement = UtilXml.firstChildElement(rootElement, "resource-loader", "name", loaderName);

                    loader = makeLoader(loaderElement);

                    if (loader != null) {
                        loaderCache.put(xmlFilename + "::" + loaderName, loader);
                    }
                }
            }
        }

        return loader;
    }

    public static Element getXmlRootElement(String xmlFilename) throws GenericConfigException {
        Document document = ResourceLoader.getXmlDocument(xmlFilename);

        if (document != null) {
            return document.getDocumentElement();
        } else {
            return null;
        }
    }

    public static void invalidateDocument(String xmlFilename) throws GenericConfigException {
        UtilCache.clearCachesThatStartWith(xmlFilename);
    }

    public static Document getXmlDocument(String xmlFilename) throws GenericConfigException {
        Document document = (Document) loaderCache.get(xmlFilename);

        if (document == null) {
            synchronized (ResourceLoader.class) {
                document = (Document) loaderCache.get(xmlFilename);
                if (document == null) {
                    URL confUrl = UtilURL.fromResource(xmlFilename);

                    if (confUrl == null) {
                        throw new GenericConfigException("ERROR: could not find the [" + xmlFilename + "] XML file on the classpath");
                    }

                    try {
                        document = UtilXml.readXmlDocument(confUrl);
                    } catch (org.xml.sax.SAXException e) {
                        throw new GenericConfigException("Error reading " + xmlFilename + "", e);
                    } catch (javax.xml.parsers.ParserConfigurationException e) {
                        throw new GenericConfigException("Error reading " + xmlFilename + "", e);
                    } catch (java.io.IOException e) {
                        throw new GenericConfigException("Error reading " + xmlFilename + "", e);
                    }

                    if (document != null) {
                        loaderCache.put(xmlFilename, document);
                    }
                }
            }
        }
        return document;
    }

    public static ResourceLoader makeLoader(Element loaderElement) throws GenericConfigException {
        if (loaderElement == null)
            return null;

        String loaderName = loaderElement.getAttribute("name");
        String className = loaderElement.getAttribute("class");
        ResourceLoader loader = null;

        try {
            Class<?> lClass = null;

            if (UtilValidate.isNotEmpty(className)) {
                try {
                    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                    lClass = classLoader.loadClass(className);
                } catch (ClassNotFoundException e) {
                    throw new GenericConfigException("Error loading Resource Loader class \"" + className + "\"", e);
                }
            }

            try {
                loader = (ResourceLoader) lClass.newInstance();
            } catch (IllegalAccessException e) {
                throw new GenericConfigException("Error loading Resource Loader class \"" + className + "\"", e);
            } catch (InstantiationException e) {
                throw new GenericConfigException("Error loading Resource Loader class \"" + className + "\"", e);
            }
        } catch (SecurityException e) {
            throw new GenericConfigException("Error loading Resource Loader class \"" + className + "\"", e);
        }

        if (loader != null) {
            loader.init(loaderName, loaderElement.getAttribute("prefix"), loaderElement.getAttribute("prepend-env"));
        }

        return loader;
    }

    protected ResourceLoader() {}

    public void init(String name, String prefix, String envName) {
        this.name = name;
        this.prefix = prefix;
        this.envName = envName;
    }

    /**
     * Just a utility method to be used in loadResource by the implementing class.
     * @param location
     * @return the built-up full location
     */
    public String fullLocation(String location) {
        StringBuilder buf = new StringBuilder();

        if (UtilValidate.isNotEmpty(envName)) {
            String propValue = System.getProperty(envName);
            if (propValue == null) {
                String errMsg = "The Java environment (-Dxxx=yyy) variable with name " + envName + " is not set, cannot load resource.";
                Debug.logError(errMsg, module);
                throw new IllegalArgumentException(errMsg);
            }
            buf.append(propValue);
        }
        if (UtilValidate.isNotEmpty(prefix)) {
            buf.append(prefix);
        }
        buf.append(location);
        return buf.toString();
    }

    public abstract InputStream loadResource(String location) throws GenericConfigException;
    public abstract URL getURL(String location) throws GenericConfigException;
}
