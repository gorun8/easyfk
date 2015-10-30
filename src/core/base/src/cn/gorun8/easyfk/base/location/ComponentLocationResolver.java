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

import cn.gorun8.easyfk.base.component.ComponentConfig;
import cn.gorun8.easyfk.base.component.ComponentException;
import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilURL;

/**
 * A special location resolver that uses Strings like URLs, but with more options
 *
 */

public class ComponentLocationResolver implements LocationResolver {

    public static final String module = ComponentLocationResolver.class.getName();

    public URL resolveLocation(String location) throws MalformedURLException {
        StringBuffer baseLocation = ComponentLocationResolver.getBaseLocation(location);
        URL fileUrl = UtilURL.fromFilename(baseLocation.toString());

        if (fileUrl == null) {
            Debug.logWarning("Unable to get file URL for component location; expanded location was [" + baseLocation + "], original location was [" + location + "]", module);
        }
        return fileUrl;
    }

    public static StringBuffer getBaseLocation(String location) throws MalformedURLException {
        StringBuffer baseLocation = new StringBuffer(FlexibleLocation.stripLocationType(location));

        // componentName is between the first slash and the second
        int firstSlash = baseLocation.indexOf("/");
        int secondSlash = baseLocation.indexOf("/", firstSlash + 1);
        if (firstSlash != 0 || secondSlash == -1) {
            throw new MalformedURLException("Bad component location [" + location + "]: base location missing slashes [" + baseLocation + "], first=" + firstSlash + ", second=" + secondSlash + "; should be like: component://{component-name}/relative/path");
        }
        String componentName = baseLocation.substring(firstSlash + 1, secondSlash);

        // got the componentName, now remove it from the baseLocation, removing the second slash too (just in case the rootLocation has one)
        baseLocation.delete(0, secondSlash + 1);

        String rootLocation;
        try {
            rootLocation = ComponentConfig.getRootLocation(componentName);
        } catch (ComponentException e) {
            String errMsg = "Could not get root location for component with name [" + componentName + "], error was: " + e.toString();
            Debug.logError(e, errMsg, module);
            throw new MalformedURLException(errMsg);
        }

        // if there is not a forward slash between the two, add it
        if (baseLocation.charAt(0) != '/' && rootLocation.charAt(rootLocation.length() - 1) != '/') {
            baseLocation.insert(0, '/');
        }

        // insert the root location and we're done
        baseLocation.insert(0, rootLocation);

        return baseLocation;
    }
}
