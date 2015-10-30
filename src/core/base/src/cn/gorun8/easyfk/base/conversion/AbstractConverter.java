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

import cn.gorun8.easyfk.base.util.ObjectType;

/** Abstract Converter class. This class handles converter registration
 * and it implements the <code>canConvert</code>, <code>getSourceClass</code>,
 * and <code>getTargetClass</code> methods.
 */
public abstract class AbstractConverter<S, T> implements Converter<S, T>, ConverterLoader {
    private final Class<? super S> sourceClass;
    private final Class<? super T> targetClass;

    protected AbstractConverter(Class<? super S> sourceClass, Class<? super T> targetClass) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    public void loadConverters() {
        Converters.registerConverter(this);
    }

    public T convert(Class<? extends T> targetClass, S obj) throws ConversionException {
        return convert(obj);
    }

    public boolean canConvert(Class<?> sourceClass, Class<?> targetClass) {
        return ObjectType.instanceOf(sourceClass, this.getSourceClass()) && ObjectType.instanceOf(targetClass, this.getTargetClass());
    }

    public Class<? super S> getSourceClass() {
        return sourceClass;
    }

    public Class<? super T> getTargetClass() {
        return targetClass;
    }
}
