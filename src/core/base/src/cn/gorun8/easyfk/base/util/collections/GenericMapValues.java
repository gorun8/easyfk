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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.gorun8.easyfk.base.util.UtilObject;

public abstract class GenericMapValues<K, V, M extends Map<K, V>> extends GenericMapCollection<K, V, M, V> {
    public GenericMapValues(M source) {
        super(source);
    }

    public boolean contains(Object item) {
        Iterator<V> it = iterator(false);
        while (it.hasNext()) {
            if (UtilObject.equalsHelper(item, it.next())) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Collection<?>)) return false;
        if (o instanceof List<?> || o instanceof Set<?>) return false;
        Collection<?> other = (Collection<?>) o;
        if (source.size() != other.size()) return false;
        Iterator<V> it = iterator(false);
        while (it.hasNext()) {
            V item = it.next();
            if (!other.contains(item)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int h = 0;
        Iterator<V> it = iterator(false);
        while (it.hasNext()) {
            V item = it.next();
            if (item == null) continue;
            h += item.hashCode();
        }
        return h;
    }

    public boolean remove(Object item) {
        Iterator<V> it = iterator(false);
        while (it.hasNext()) {
            if (UtilObject.equalsHelper(item, it.next())) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}

