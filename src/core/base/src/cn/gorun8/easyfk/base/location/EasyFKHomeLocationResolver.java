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
package cn.gorun8.easyfk.base.location;

import java.net.MalformedURLException;
import java.net.URL;

import cn.gorun8.easyfk.base.util.UtilURL;

/**
 * A special location resolver that uses Strings like URLs, but with more options
 *
 */

public class EasyFKHomeLocationResolver implements LocationResolver {

    public static final String envName = "easyfk.home";

    public URL resolveLocation(String location) throws MalformedURLException {
        String propValue = System.getProperty(envName);
        if (propValue == null) {
            String errMsg = "The Java environment (-Dxxx=yyy) variable with name " + envName + " is not set, cannot resolve location.";
            throw new MalformedURLException(errMsg);
        }

        StringBuilder baseLocation = new StringBuilder(FlexibleLocation.stripLocationType(location));

        // if there is not a forward slash between the two, add it
        if (baseLocation.charAt(0) != '/' && propValue.charAt(propValue.length() - 1) != '/') {
            baseLocation.insert(0, '/');
        }

        baseLocation.insert(0, propValue);

        return UtilURL.fromFilename(baseLocation.toString());
    }
}
