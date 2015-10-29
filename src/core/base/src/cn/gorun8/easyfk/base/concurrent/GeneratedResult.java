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
package cn.gorun8.easyfk.base.concurrent;

public final class GeneratedResult<T> {
    public final long lastModifiedTime;
    public final T object;

    public GeneratedResult(long lastModifiedTime, T object) {
        this.lastModifiedTime = lastModifiedTime;
        this.object = object;
    }
}
