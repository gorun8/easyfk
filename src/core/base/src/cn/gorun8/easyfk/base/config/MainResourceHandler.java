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
