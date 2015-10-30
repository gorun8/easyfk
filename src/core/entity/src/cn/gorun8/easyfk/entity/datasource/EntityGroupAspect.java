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
package cn.gorun8.easyfk.entity.datasource;

import cn.gorun8.easyfk.annotation.EntityGroup;
import cn.gorun8.easyfk.base.util.Debug;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import java.lang.reflect.Method;

/**
 * 采用AOP技术，根据DAO方法的注解@EasyFKEntityGroup，动态的将该DAO方法注解中指明的
 * 数据源分组设置到线程中（利用EasyFKDataSourceHolder），以便EasyFKDataSource取得正确的数据源
 * 从而实现利用正确的数据源进行数据的读写操作。
 * 对于没有通过@EasyFKDataSourceHolder注解的方法，将会采用默认的数据源进行操作
 * 注：DAO的包路径必须为* cn.gorun8.easyfk.*.dao.*.*(..))，否则不能进行管理。
 */
public class EntityGroupAspect {
   private final static String  module =  EntityGroupAspect.class.getName();
   public void before(JoinPoint point) {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();
        Signature tmpSignature = point.getSignature();
        org.aspectj.lang.reflect.MethodSignature signature = (org.aspectj.lang.reflect.MethodSignature) tmpSignature;
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(EntityGroup.class)) {
                EntityGroup data = m.getAnnotation(EntityGroup.class);
                EntityGroupType entityGroupType = data.value();
                EntityGroupContext.putDataSource(entityGroupType.toString());
            }else{
                EntityGroupContext.putDataSource(EntityGroupType.MASTER.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Debug.logError(e.toString(),module);
        }
    }
}
