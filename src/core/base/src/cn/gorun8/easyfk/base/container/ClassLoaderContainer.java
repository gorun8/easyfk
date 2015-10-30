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
