package cn.gorun8.easyfk.security.annotation;/*
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
 * Author:hezhiping   Email:110476592@qq.com   Date: 2016-01-29
 */

import java.lang.annotation.*;

/**
 * 实现通过注释定义权限。
 * 在注解中定义的权限会自动加载到表security_permission中。
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionDefine {
    /**
     * 权限ID, 必须唯一PERMISSION_ID,应按照规则命名，以防重复
     * 规则：资源标识符_对象实例ID_操作 ，如party_clsgroup_search
     */
    String permissionId();
    /**
     *上级权限ID,PARENT_ID
     */
    String parentId() default "_NA_";
    /**
     *资源文件名
     */
    String resource();
    /**
     *资源KEY
     */
    String key();

    /**
     *是否为系统资源,IS_SYSTEM
     */
    String isSystem() default "N";

    /**
     * 权限标识码，PERMISSION_TAG，应按照规则命名
     *规则：“资源标识符：对象实例ID：操作” 即对哪个资源的哪个实例可以进行什么操作。
     *其默认支持通配符权限字符串，“:”表示资源/操作/实例的分割；“,”表示操作的分割；
     *“*”表示任意资源/操作/实例。
     * 例如：system:user:update,system:user:delete
     * @return
     */
    String tag();
    //DYNAMIC_ACCESS
    String dynamicAccess() default "";
}

