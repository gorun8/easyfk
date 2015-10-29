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

import cn.gorun8.easyfk.base.concurrent.ExecutionPool;

public abstract class CacheLine<V> extends ExecutionPool.Pulse {
    protected CacheLine(long loadTimeNanos, long expireTimeNanos) {
        super(loadTimeNanos, expireTimeNanos);
        // FIXME: this seems very odd to me (ARH)
        //if (loadTime <= 0) {
        //    hasExpired = true;
        //}
    }

    abstract CacheLine<V> changeLine(boolean useSoftReference, long expireTimeNanos);
    abstract void remove();
    boolean differentExpireTime(long expireTimeNanos) {
        return this.expireTimeNanos - loadTimeNanos - expireTimeNanos != 0;
    }
    public abstract V getValue();

    void cancel() {
    }

    @Override
    public void run() {
        remove();
    }
}

