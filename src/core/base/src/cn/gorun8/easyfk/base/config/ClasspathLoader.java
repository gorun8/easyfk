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

import java.net.URL;
import java.io.InputStream;
import cn.gorun8.easyfk.base.util.UtilURL;

/**
 * Loads resources from the classpath
 *
 */
@SuppressWarnings("serial")
public class ClasspathLoader extends ResourceLoader implements java.io.Serializable {

    @Override
    public URL getURL(String location) throws GenericConfigException {
        String fullLocation = fullLocation(location);
        URL url = UtilURL.fromResource(fullLocation);
        if (url == null) {
            throw new GenericConfigException("Classpath Resource not found: " + fullLocation);
        }
        return url;
    }

    @Override
    public InputStream loadResource(String location) throws GenericConfigException {
        URL url = getURL(location);
        try {
            return url.openStream();
        } catch (java.io.IOException e) {
            throw new GenericConfigException("Error opening classpath resource at location [" + url.toExternalForm() + "]", e);
        }
    }
}
