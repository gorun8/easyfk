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

public abstract class HardRefCacheLine<V> extends CacheLine<V> {
    public final V value;

    public HardRefCacheLine(V value, long loadTimeNanos, long expireTimeNanos) {
        super(loadTimeNanos, expireTimeNanos);
        this.value = value;
    }

    @Override
    public V getValue() {
        return value;
    }
}
