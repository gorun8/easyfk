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
