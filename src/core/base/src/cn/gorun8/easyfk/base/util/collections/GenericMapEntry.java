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

import cn.gorun8.easyfk.base.util.UtilObject;

public class GenericMapEntry<K, V> implements Map.Entry<K, V> {
    protected final GenericMap<K, V> map;
    protected final K key;
    protected final boolean noteAccess;

    public GenericMapEntry(GenericMap<K, V> map, K key, boolean noteAccess) {
        this.map = map;
        this.key = key;
        this.noteAccess = noteAccess;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return map.get(key, noteAccess);
    }

    public V setValue(V value) {
        return map.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Map.Entry<?, ?>)) return false;
        if (this == o) return true;
        Map.Entry<?, ?> other = (Map.Entry<?, ?>) o;
        return UtilObject.equalsHelper(getKey(), other.getKey()) && UtilObject.equalsHelper(getValue(), other.getValue());
    }

    @Override
    public int hashCode() {
        return UtilObject.doHashCode(getKey()) ^ UtilObject.doHashCode(getValue());
    }
}
