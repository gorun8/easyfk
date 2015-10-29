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
package cn.gorun8.easyfk.base.container;

import cn.gorun8.easyfk.base.util.CachedClassLoader;
import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.start.Classpath;

import java.net.URL;

/**
 * ClassLoader Container; Created a CachedClassLoader for use by all following containers
 *
 */
public class ClassLoaderContainer implements Container {

    public static final String module = ClassLoaderContainer.class.getName();
    protected static CachedClassLoader cl = null;

    /**
     * @see cn.gorun8.easyfk.base.container.Container#init(java.lang.String[], java.lang.String)
     */
    public void init(String[] args, String configFile) throws ContainerException {
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        if (parent == null) {
            parent = Classpath.class.getClassLoader();
        }
        if (parent == null) {
            parent = ClassLoader.getSystemClassLoader();
        }

        cl = new CachedClassLoader(new URL[0], parent);
        Thread.currentThread().setContextClassLoader(cl);
        Debug.logInfo("CachedClassLoader created", module);
    }

    /**
     * @see cn.gorun8.easyfk.base.container.Container#start()
     */
    public boolean start() throws ContainerException {
        return true;
    }

    /**
     * @see cn.gorun8.easyfk.base.container.Container#stop()
     */
    public void stop() throws ContainerException {
    }

    public static ClassLoader getClassLoader() {
        if (cl != null) {
            return cl;
        } else {
            return ClassLoader.getSystemClassLoader();
        }
    }
}
