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

@SuppressWarnings("serial")
public abstract class SoftRefCacheLine<V> extends CacheLine<V> {
    public final CacheSoftReference<V> ref;

    public SoftRefCacheLine(V value, long loadTimeNanos, long expireTimeNanos) {
        super(loadTimeNanos, expireTimeNanos);
        this.ref = new CacheSoftReference<V>(value) {
            public void remove() {
                SoftRefCacheLine.this.remove();
            }
        };
    }

    @Override
    void cancel() {
        ref.clear();
    }

    @Override
    public V getValue() {
        return ref.get();
    }
}
