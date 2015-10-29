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
