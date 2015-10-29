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

