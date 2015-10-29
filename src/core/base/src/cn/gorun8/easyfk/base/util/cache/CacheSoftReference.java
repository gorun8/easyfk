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
