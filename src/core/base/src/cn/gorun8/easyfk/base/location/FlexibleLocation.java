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
import java.util.Map;

import javolution.util.FastMap;

import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;

/**
 * A special location resolver that uses Strings like URLs, but with more options
 *
 */

public class FlexibleLocation {

    protected static Map<String, LocationResolver> locationResolvers = FastMap.newInstance();

    protected static Map<String, String> defaultResolvers = FastMap.newInstance();

    protected static String standardUrlResolverName = StandardUrlLocationResolver.class.getName();
    protected static String classpathResolverName = ClasspathLocationResolver.class.getName();
    protected static String easyfkHomeResolverName = EasyFKHomeLocationResolver.class.getName();
    protected static String componentResolverName = ComponentLocationResolver.class.getName();

    static {
        defaultResolvers.put("http", standardUrlResolverName);
        defaultResolvers.put("https", standardUrlResolverName);
        defaultResolvers.put("ftp", standardUrlResolverName);
        defaultResolvers.put("jar", standardUrlResolverName);
        defaultResolvers.put("file", standardUrlResolverName);

        defaultResolvers.put("classpath", classpathResolverName);
        defaultResolvers.put("easyfkhome", easyfkHomeResolverName);
        defaultResolvers.put("component", componentResolverName);
    }

    /**
     * Resolves the gives location into a URL object for use in various ways.
     *
     * The general format of the location is like a URL: {locationType}://location/path/file.ext
     *
     * Supports standard locationTypes like http, https, ftp, jar, & file
     * Supports a classpath location type for when desired to be used like any other URL
     * Supports EasyFK specific location types like easyfkhome and component
     * Supports additional locationTypes specified in the locationresolvers.properties file
     *
     * @param location The location String to parse and create a URL from
     * @return URL object corresponding to the location String passed in
     * @throws MalformedURLException
     */
    public static URL resolveLocation(String location) throws MalformedURLException {
        return resolveLocation(location, null);
    }

    public static URL resolveLocation(String location, ClassLoader loader) throws MalformedURLException {
        if (UtilValidate.isEmpty(location)) {
            return null;
        }
        String locationType = getLocationType(location);

        LocationResolver resolver = locationResolvers.get(locationType);
        if (resolver == null) {
            synchronized (FlexibleLocation.class) {
                resolver = locationResolvers.get(locationType);
                if (resolver == null) {
                    String locationResolverName = UtilProperties.getPropertyValue("locationresolvers", locationType);
                    if (UtilValidate.isEmpty(locationResolverName)) {
                        // try one of the defaults
                        locationResolverName = defaultResolvers.get(locationType);
                    }

                    if (UtilValidate.isEmpty(locationResolverName)) {
                        // still nothing, give up
                        throw new MalformedURLException("Could not find a LocationResolver class name for the location type: " + locationType);
                    }

                    // now create a new instance of the class...
                    try {
                        Class<?> lClass = null;

                        try {
                            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                            lClass = classLoader.loadClass(locationResolverName);
                        } catch (ClassNotFoundException e) {
                            throw new MalformedURLException("Error loading Location Resolver class \"" + locationResolverName + "\": " + e.toString());
                        }

                        try {
                            resolver = (LocationResolver) lClass.newInstance();
                        } catch (IllegalAccessException e) {
                            throw new MalformedURLException("Error loading Location Resolver class \"" + locationResolverName + "\": " + e.toString());
                        } catch (InstantiationException e) {
                            throw new MalformedURLException("Error loading Location Resolver class \"" + locationResolverName + "\": " + e.toString());
                        }
                    } catch (SecurityException e) {
                        throw new MalformedURLException("Error loading Location Resolver class \"" + locationResolverName + "\": " + e.toString());
                    }

                    if (resolver != null) {
                        locationResolvers.put(locationType, resolver);
                    }
                }
            }
        }

        if (resolver != null) {
            if (loader != null && resolver instanceof ClasspathLocationResolver) {
                ClasspathLocationResolver cplResolver = (ClasspathLocationResolver) resolver;
                return cplResolver.resolveLocation(location, loader);
            } else {
                return resolver.resolveLocation(location);
            }
        } else {
            throw new MalformedURLException("Could not find a LocationResolver for the location type: " + locationType);
        }
    }

    /**
     * Find the location type descriptor for the passed location String;
     *   generally is all text before the first ":" character.
     *   If no type descriptor is found, defaults to "classpath".
     */
    public static String getLocationType(String location) {
        if (UtilValidate.isEmpty(location)) {
            return null;
        }

        int colonIndex = location.indexOf(":");
        if (colonIndex > 0) {
            return location.substring(0, colonIndex);
        } else {
            return "classpath";
        }
    }

    public static String stripLocationType(String location) {
        if (UtilValidate.isEmpty(location)) {
            return "";
        }

        StringBuilder strippedSoFar = new StringBuilder(location);

        // first take care of the colon and everything before it
        int colonIndex = strippedSoFar.indexOf(":");
        if (colonIndex == 0) {
            strippedSoFar.deleteCharAt(0);
        } else if (colonIndex > 0) {
            strippedSoFar.delete(0, colonIndex + 1);
        }

        // now remove any extra forward slashes, ie as long as the first two are forward slashes remove the first one
        while (strippedSoFar.charAt(0) == '/' && strippedSoFar.charAt(1) == '/') {
            strippedSoFar.deleteCharAt(0);
        }

        return strippedSoFar.toString();
    }
}
