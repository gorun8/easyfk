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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LifoSet - Set interface wrapper around a LinkedList
 *
 */
@SuppressWarnings("serial")
public class LRUMap<K, V> extends LinkedHashMap<K, V> {
    private int maxSize;

    public LRUMap() {
        this(10);
    }

    public LRUMap(int size) {
        this(size, 16);
    }

    public LRUMap(int size, int initialCapacity) {
        this(size, initialCapacity, (float) .75);
    }

    public LRUMap(int size, int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor, true);
        maxSize = size;
    }

    /**
     * Sets the max capacity for this LRUMap
     * @param size Max Size (as integer)
     */
    public void setMaxSize(int size) {
        this.maxSize = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return size() > maxSize;
    }
}
