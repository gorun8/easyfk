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
package cn.gorun8.easyfk.base.conversion;

/** ConverterCreator interface. Classes implement this interface to create a
 * converter that can convert one Java object type to another.
 */
public interface ConverterCreator {
    /** Creates a Converter that can convert the <code>sourceClass</code> to
     * the <code>targetClass</code>. Returns <code>null</code> if this creater
     * doesn't support the class pair.
     *
     * @param sourceClass The source <code>Class</code> to convert
     * @param targetClass The target <code>Class</code> to convert to
     * @return a converter that can convert <code>Object</code>s
     */
    public <S, T> Converter<S, T> createConverter(Class<S> sourceClass, Class<T> targetClass);
}
