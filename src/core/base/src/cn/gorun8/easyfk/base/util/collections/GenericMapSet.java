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
package cn.gorun8.easyfk.base.util.collections;

import java.util.Map;
import java.util.Set;

public abstract class GenericMapSet<K, V, M extends Map<K, V>, I> extends GenericMapCollection<K, V, M, I> implements Set<I> {
    public GenericMapSet(M source) {
        super(source);
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Set<?>)) return false;
        Set<?> other = (Set<?>) o;
        if (source.size() != other.size()) return false;
        for (I item: this) {
            if (!other.contains(item)) return false;
        }
        return true;
    }

    @Override
    public final int hashCode() {
        int h = 0;
        for (I item: this) {
            if (item == null) continue;
            h += item.hashCode();
        }
        return h;
    }
}

