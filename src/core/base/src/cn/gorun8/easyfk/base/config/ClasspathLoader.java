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