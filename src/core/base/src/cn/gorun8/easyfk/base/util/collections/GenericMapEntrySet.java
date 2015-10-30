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

public abstract class GenericMapEntrySet<K, V, M extends GenericMap<K, V>> extends GenericMapSet<K, V, M, Map.Entry<K, V>> {
    public GenericMapEntrySet(M source) {
        super(source);
    }

    public boolean contains(Object item) {
        if (item == null) return false;
        if (!(item instanceof Map.Entry<?, ?>)) return false;
        Map.Entry<?, ?> other = (Map.Entry<?, ?>) item;
        return contains(other.getKey(), other.getValue());
    }

    protected abstract boolean contains(Object key, Object value);

    public boolean remove(Object item) {
        if (item == null) return false;
        if (!(item instanceof Map.Entry<?, ?>)) return false;
        Map.Entry<?, ?> other = (Map.Entry<?, ?>) item;
        Object key = other.getKey();
        if (!source.containsKey(key)) return false;
        source.remove(key);
        return true;
    }
}

