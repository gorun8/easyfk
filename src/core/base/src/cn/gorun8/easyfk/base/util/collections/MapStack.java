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

import java.util.Locale;
import java.util.Map;

import javolution.context.ObjectFactory;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilGenerics;


/**
 * Map Stack
 *
 */
public class MapStack<K> extends MapContext<K, Object> {

    public static final String module = MapStack.class.getName();

    protected static final ObjectFactory<MapStack<?>> mapStackFactory = new ObjectFactory<MapStack<?>>() {
        @Override
        protected MapStack<?> create() {
            return new MapStack<Object>();
        }
    };

    protected static final <K> MapStack<K> getMapStack() {
        return (MapStack<K>) UtilGenerics.<K, Object>checkMap(mapStackFactory.object());
    }

    public static <K> MapStack<K> create() {
        MapStack<K> newValue = MapStack.getMapStack();
        // initialize with a single entry
        newValue.push();
        return newValue;
    }

    @SuppressWarnings("unchecked")
    public static <K> MapStack<K> create(Map<K, Object> baseMap) {
        MapStack<K> newValue = MapStack.getMapStack();
        if (baseMap instanceof MapStack) {
            newValue.stackList.addAll(((MapStack) baseMap).stackList);
        } else {
            newValue.stackList.add(0, baseMap);
        }
        return newValue;
    }

    /** Does a shallow copy of the internal stack of the passed MapStack; enables simultaneous stacks that share common parent Maps */
    public static <K> MapStack<K> create(MapStack<K> source) {
        MapStack<K> newValue = MapStack.getMapStack();
        newValue.stackList.addAll(source.stackList);
        return newValue;
    }

    protected MapStack() {
        super();
    }

    /**
     * Creates a MapStack object that has the same Map objects on its stack;
     * meant to be used to enable a
     * situation where a parent and child context are operating simultaneously
     * using two different MapStack objects, but sharing the Maps in common
     */
    @Override
    public MapStack<K> standAloneStack() {
        MapStack<K> standAlone = MapStack.create(this);
        return standAlone;
    }

    /**
     * Creates a MapStack object that has the same Map objects on its stack,
     * but with a new Map pushed on the top; meant to be used to enable a
     * situation where a parent and child context are operating simultaneously
     * using two different MapStack objects, but sharing the Maps in common
     */
    @Override
    public MapStack<K> standAloneChildStack() {
        MapStack<K> standAloneChild = MapStack.create(this);
        standAloneChild.push();
        return standAloneChild;
    }

    /* (non-Javadoc)
     * @see java.util.Map#get(java.lang.Object)
     */
    @Override
    public Object get(Object key) {
        if ("context".equals(key)) {
            return this;
        }

        return super.get(key);
    }

    /* (non-Javadoc)
     * @see cn.gorun8.easyfk.base.util.collections.LocalizedMap#get(java.lang.String, java.util.Locale)
     */
    @Override
    public Object get(String name, Locale locale) {
        if ("context".equals(name)) {
            return this;
        }

        return super.get(name, locale);
    }

    /* (non-Javadoc)
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public Object put(K key, Object value) {
        if ("context".equals(key)) {
            if (value == null || this != value) {
                Debug.logWarning("WARNING: Putting a value in a MapStack with key [context] that is not this MapStack, will be hidden by the current MapStack self-reference: " + value, module);
            }
        }

        return super.put(key, value);
    }
}
