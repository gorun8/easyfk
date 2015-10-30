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

/** Converter loader interface. Applications implement this
 * interface to load their Java object converters.
 *
 */
public interface ConverterLoader {
    /** Create and register converters with the Java object type
     * conversion framework. If the converter extends one of the
     * converter abstract classes, then the converter will register
     * itself when an instance is created. Otherwise, call
     * {@link cn.gorun8.easyfk.base.conversion.Converters#registerConverter(Converter)}
     * with the <code>Converter</code> instance.
     *
     */
    public void loadConverters();
}
