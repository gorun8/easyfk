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

/**
 * A special location resolver that uses Strings like URLs, but with more options
 *
 */

public class StandardUrlLocationResolver implements LocationResolver {
    public URL resolveLocation(String location) throws MalformedURLException {
        return new URL(location);
    }
}
