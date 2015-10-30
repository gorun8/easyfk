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

