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
