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

public class ClasspathLocationResolver implements LocationResolver {
    public URL resolveLocation(String location) throws MalformedURLException {
        return resolveLocation(location, null);
    }

    public URL resolveLocation(String location, ClassLoader loader) throws MalformedURLException {
        String baseLocation = FlexibleLocation.stripLocationType(location);
        // if there is a leading forward slash, remove it
        if (baseLocation.charAt(0) == '/') {
            baseLocation = baseLocation.substring(1);
        }
        return UtilURL.fromResource(baseLocation, loader);
    }
}
