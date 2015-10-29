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
