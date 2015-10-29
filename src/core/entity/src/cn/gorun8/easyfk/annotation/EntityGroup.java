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
package cn.gorun8.easyfk.annotation;

import cn.gorun8.easyfk.entity.datasource.EntityGroupType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *DAO方法的注解，指明该方法使用的数据源。
 * 其设值，应满足cn.gorun8.easyfk.entity.datasource.EntityGroupType中的定义。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EntityGroup {
    EntityGroupType value();
}

