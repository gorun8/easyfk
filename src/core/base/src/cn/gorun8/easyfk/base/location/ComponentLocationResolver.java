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
