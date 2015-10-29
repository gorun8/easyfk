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
 * Loads resources from the file system
 *
 */
@SuppressWarnings("serial")
public class FileLoader extends ResourceLoader implements java.io.Serializable {

    @Override
    public URL getURL(String location) throws GenericConfigException {
        String fullLocation = fullLocation(location);
        URL fileUrl = null;

        fileUrl = UtilURL.fromFilename(fullLocation);
        if (fileUrl == null) {
            throw new GenericConfigException("File Resource not found: " + fullLocation);
        }
        return fileUrl;
    }

    @Override
    public InputStream loadResource(String location) throws GenericConfigException {
        URL fileUrl = getURL(location);
        try {
            return fileUrl.openStream();
        } catch (java.io.IOException e) {
            throw new GenericConfigException("Error opening file at location [" + fileUrl.toExternalForm() + "]", e);
        }
    }
}
