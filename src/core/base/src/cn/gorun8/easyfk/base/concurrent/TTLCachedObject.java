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
package cn.gorun8.easyfk.base.concurrent;

import cn.gorun8.easyfk.base.lang.SourceMonitored;

@SourceMonitored
public abstract class TTLCachedObject<T> extends TTLObject<T> {
    public static final long NOT_EXISTANT_TIMESTAMP = Long.MIN_VALUE;
    public static final long FORCE_REGEN = Long.MIN_VALUE + 1;

    protected long lastModifiedTime = NOT_EXISTANT_TIMESTAMP;

    public long getTimestamp() throws ObjectException {
        getObject();
        return lastModifiedTime;
    }

    @Override
    protected T load(T old, int serial) throws Exception {
        long timestamp = getTimestamp(old);
        if (lastModifiedTime != timestamp) {
            if (timestamp != NOT_EXISTANT_TIMESTAMP) {
                GeneratedResult<T> result = generate(old, serial);
                lastModifiedTime = result.lastModifiedTime;
                return result.object;
            } else {
                lastModifiedTime = NOT_EXISTANT_TIMESTAMP;
                return getInitial();
            }
        }
        return old;
    }

    protected abstract GeneratedResult<T> generate(T old, int serial) throws Exception;
    protected abstract long getTimestamp(T old) throws Exception;
}
