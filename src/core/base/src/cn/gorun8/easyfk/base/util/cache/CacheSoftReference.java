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
package cn.gorun8.easyfk.base.util.cache;

import java.io.Serializable;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.ReferenceCleaner;

@SuppressWarnings("serial")
public abstract class CacheSoftReference<V> extends ReferenceCleaner.Soft<V> implements Serializable {

    public static final String module = CacheSoftReference.class.getName();

    public CacheSoftReference(V o) {
        super(o);
    }

    @Override
    public void clear() {
        if (Debug.verboseOn()) {
            Debug.logVerbose(new Exception("UtilCache.CacheSoftRef.clear()"), "Clearing UtilCache SoftReference - " + get(), module);
        }
        super.clear();
    }

    @Override
    public void finalize() throws Throwable {
        if (Debug.verboseOn()) {
            Debug.logVerbose(new Exception("UtilCache.CacheSoftRef.finalize()"), "Finalize UtilCache SoftReference - " + get(), module);
        }
        super.finalize();
    }
}
