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

/**
 * A simple interface to facilitate the retreival of values based on a Locale.
 *
 */
public interface LocalizedMap<V> {
    public V get(String name, Locale locale);
}
