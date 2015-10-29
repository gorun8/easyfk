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

public abstract class GenericMapKeySet<K, V, M extends Map<K, V>> extends GenericMapSet<K, V, M, K> {
    public GenericMapKeySet(M source) {
        super(source);
    }

    public boolean remove(Object item) {
        return source.remove(item) != null;
    }
}

