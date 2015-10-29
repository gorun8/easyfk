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

import javolution.util.FastList;

public abstract class GenericMapCollection<K, V, M extends Map<K, V>, I> implements Collection<I> {
    protected final M source;

    public GenericMapCollection(M source) {
        this.source = source;
    }

    public boolean add(I item) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends I> collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        source.clear();
    }

    public boolean containsAll(Collection<?> collection) {
        for (Object item: collection) {
            if (!contains(item)) return false;
        }
        return true;
    }

    public boolean isEmpty() {
        return source.isEmpty();
    }

    public final Iterator<I> iterator() {
        return iterator(true);
    }

    protected abstract Iterator<I> iterator(boolean noteAccess);

    public boolean removeAll(Collection<?> collection) {
        int count = 0;
        for (Object item: collection) {
            if (remove(item)) count++;
        }
        return count > 0;
    }

    public boolean retainAll(Collection<?> collection) {
        int count = 0;
        Iterator<I> it = iterator(false);
        while (it.hasNext()) {
            I item = it.next();
            if (!collection.contains(item)) {
                it.remove();
                count++;
            }
        }
        return count > 0;
    }

    public int size() {
        return source.size();
    }

    public Object[] toArray() {
        List<I> list = FastList.newInstance();
        Iterator<I> it = iterator(false);
        while (it.hasNext()) {
            list.add(it.next());
        }
        return list.toArray();
    }

    public <T> T[] toArray(T[] array) {
        List<Object> list = FastList.newInstance();
        Iterator<I> it = iterator(false);
        while (it.hasNext()) {
            list.add(it.next());
        }
        return list.toArray(array);
    }

    @Override
    public String toString() {
        return appendTo(new StringBuilder()).toString();
    }

    public StringBuilder appendTo(StringBuilder sb) {
        sb.append("[");
        Iterator<I> it = iterator(false);
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) sb.append(", ");
        }
        return sb.append("]");
    }
}

