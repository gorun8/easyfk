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
package cn.gorun8.easyfk.entity.datasource;

/**
 * 从上下文环境中，取出取数据源的标识，传给EasyFKDataSource。以便其获取正确的数据源。
 * 采用ThreadLocal防止多用户环境下的竞争问题。
 */
public class EntityGroupContext {
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();
    public static void putDataSource(String name) {
        holder.set(name);
    }

    public static String getDataSouce() {
        return holder.get();
    }
}
