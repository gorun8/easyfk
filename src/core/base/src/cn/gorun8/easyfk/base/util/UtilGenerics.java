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
package cn.gorun8.easyfk.base.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javolution.util.FastMap;

public class UtilGenerics {

    public static final String module = UtilMisc.class.getName();

    @SuppressWarnings("unchecked")
    public static <V> V cast(Object object) {
        return (V) object;
    }

    private static <C extends Collection<?>> C checkCollectionCast(Object object, Class<C> clz) {
        return clz.cast(object);
    }

    public static <C extends Collection<?>> void checkCollectionContainment(Object object, Class<C> clz, Class<?> type) {
        if (object != null) {
            if (!(clz.isInstance(object))) throw new ClassCastException("Not a " + clz.getName());
            int i = 0;
            for (Object value: (Collection<?>) object) {
                if (value != null && !type.isInstance(value)) {
                    throw new IllegalArgumentException("Value(" + i + "), with value(" + value + ") is not a " + type.getName());
                }
                i++;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Collection<T> checkCollection(Object object) {
        return (Collection<T>) checkCollectionCast(object, Collection.class);
    }

    public static <T> Collection<T> checkCollection(Object object, Class<T> type) {
        checkCollectionContainment(object, Collection.class, type);
        return checkCollection(object);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> checkList(Object object) {
        return (List<T>) checkCollectionCast(object, List.class);
    }

    public static <T> List<T> checkList(Object object, Class<T> type) {
        checkCollectionContainment(object, List.class, type);
        return checkList(object);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> checkMap(Object object) {
        if (object != null && !(object instanceof Map)) throw new ClassCastException("Not a map");
        return (Map<K, V>) object;
    }

    public static <K, V> Map<K, V> checkMap(Object object, Class<K> keyType, Class<V> valueType) {
        if (object != null) {
            if (!(object instanceof Map<?, ?>)) throw new ClassCastException("Not a map");
            Map<?, ?> map = (Map<?,?>) object;
            int i = 0;
            for (Map.Entry<?, ?> entry: map.entrySet()) {
                if (entry.getKey() != null && !keyType.isInstance(entry.getKey())) {
                    throw new IllegalArgumentException("Key(" + i + "), with value(" + entry.getKey() + ") is not a " + keyType);
                }
                if (entry.getValue() != null && !valueType.isInstance(entry.getValue())) {
                    throw new IllegalArgumentException("Value(" + i + "), with value(" + entry.getValue() + ") is not a " + valueType);
                }
                i++;
            }
        }
        return checkMap(object);
    }

    @SuppressWarnings("unchecked")
    public static <T> Stack<T> checkStack(Object object) {
        return (Stack<T>) checkCollectionCast(object, Stack.class);
    }

    public static <T> Stack<T> checkStack(Object object, Class<T> type) {
        checkCollectionContainment(object, Stack.class, type);
        return checkStack(object);
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> checkSet(Object object) {
        return (Set<T>) checkCollectionCast(object, Set.class);
    }

    public static <T> Set<T> checkSet(Object object, Class<T> type) {
        checkCollectionContainment(object, Set.class, type);
        return checkSet(object);
    }

    /** Returns the Object argument as a parameterized List if the Object argument
     * is an instance of List. Otherwise returns null.
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> toList(Object object) {
        if (object != null && !(object instanceof List)) return null;
        return (List<T>) object;
    }

    /** Returns the Object argument as a parameterized Map if the Object argument
     * is an instance of Map. Otherwise returns null.
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> toMap(Object object) {
        if (object != null && !(object instanceof Map)) return null;
        return (Map<K, V>) object;
    }

    public static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... data) {
        if (data == null) {
            return null;
        }
        if (data.length % 2 == 1) {
            throw new IllegalArgumentException("You must pass an even sized array to the toMap method");
        }
        Map<K, V> map = FastMap.newInstance();
        for (int i = 0; i < data.length;) {
            Object key = data[i];
            if (key != null && !(keyType.isInstance(key))) throw new IllegalArgumentException("Key(" + i + ") is not a " + keyType.getName() + ", was(" + key.getClass().getName() + ")");
            i++;
            Object value = data[i];
            if (value != null && !(valueType.isInstance(value))) throw new IllegalArgumentException("Value(" + i + ") is not a " + keyType.getName() + ", was(" + key.getClass().getName() + ")");
            i++;
            map.put(keyType.cast(key), valueType.cast(value));
        }
        return map;
    }

    @SuppressWarnings("hiding")
    public static <K, Object> Map<K, Object> toMap(Class<K> keyType, Object... data) {
        if (data == null) {
            return null;
        }
        if (data.length % 2 == 1) {
            throw new IllegalArgumentException("You must pass an even sized array to the toMap method");
        }
        Map<K, Object> map = FastMap.newInstance();
        for (int i = 0; i < data.length;) {
            Object key = data[i];
            if (key != null && !(keyType.isInstance(key))) throw new IllegalArgumentException("Key(" + i + ") is not a " + keyType.getName() + ", was(" + key.getClass().getName() + ")");
            i++;
            Object value = data[i];
            map.put(keyType.cast(key), value);
        }
        return map;
    }
}
