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
import java.io.Serializable;
import java.net.URL;

import org.w3c.dom.Document;

/**
 * Contains resource information and provides for loading data
 *
 */
public interface ResourceHandler extends Serializable {

    public String getLoaderName();

    public String getLocation();

    public Document getDocument() throws GenericConfigException;

    public InputStream getStream() throws GenericConfigException;

    public URL getURL() throws GenericConfigException;

    public boolean isFileResource() throws GenericConfigException;

    public String getFullLocation() throws GenericConfigException;
}
