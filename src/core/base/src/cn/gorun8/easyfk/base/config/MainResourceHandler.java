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

import cn.gorun8.easyfk.base.util.UtilXml;
import cn.gorun8.easyfk.base.util.Debug;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Contains resource information and provides for loading data
 *
 */
@SuppressWarnings("serial")
public final class MainResourceHandler implements ResourceHandler {

    public static final String module = MainResourceHandler.class.getName();
    protected final String xmlFilename;
    protected final String loaderName;
    protected final String location;

    public MainResourceHandler(String xmlFilename, Element element) {
        this.xmlFilename = xmlFilename;
        this.loaderName = element.getAttribute("loader");
        this.location = element.getAttribute("location");
        if (Debug.verboseOn()) Debug.logVerbose("Created " + this.toString(), module);
    }

    public MainResourceHandler(String xmlFilename, String loaderName, String location) {
        this.xmlFilename = xmlFilename;
        this.loaderName = loaderName;
        this.location = location;
    }

    public String getLoaderName() {
        return this.loaderName;
    }

    public String getLocation() {
        return this.location;
    }

    public Document getDocument() throws GenericConfigException {
        try {
            return UtilXml.readXmlDocument(this.getStream(), this.xmlFilename, true);
        } catch (org.xml.sax.SAXException e) {
            throw new GenericConfigException("Error reading " + this.toString(), e);
        } catch (javax.xml.parsers.ParserConfigurationException e) {
            throw new GenericConfigException("Error reading " + this.toString(), e);
        } catch (java.io.IOException e) {
            throw new GenericConfigException("Error reading " + this.toString(), e);
        }
    }

    public InputStream getStream() throws GenericConfigException {
        return ResourceLoader.loadResource(this.xmlFilename, this.location, this.loaderName);
    }

    public URL getURL() throws GenericConfigException {
        return ResourceLoader.getURL(this.xmlFilename, this.location, this.loaderName);
    }

    public boolean isFileResource() throws GenericConfigException {
        ResourceLoader loader = ResourceLoader.getLoader(this.xmlFilename, this.loaderName);

        if (loader instanceof FileLoader) {
            return true;
        } else {
            return false;
        }
    }

    public String getFullLocation() throws GenericConfigException {
        ResourceLoader loader = ResourceLoader.getLoader(this.xmlFilename, this.loaderName);

        return loader.fullLocation(location);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MainResourceHandler) {
            MainResourceHandler other = (MainResourceHandler) obj;

            if (this.loaderName.equals(other.loaderName) &&
                this.xmlFilename.equals(other.xmlFilename) &&
                this.location.equals(other.location)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        // the hashCode will weight by a combination xmlFilename and the combination of loaderName and location
        return (this.xmlFilename.hashCode() + ((this.loaderName.hashCode() + this.location.hashCode()) >> 1)) >> 1;
    }

    @Override
    public String toString() {
        return "ResourceHandler from XML file [" + this.xmlFilename + "] with loaderName [" + loaderName + "] and location [" + location + "]";
    }
}
