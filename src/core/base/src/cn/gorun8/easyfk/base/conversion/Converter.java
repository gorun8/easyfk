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

/** Converter interface. Classes implement this interface to convert one
 * Java object type to another.
 *
 * @param <S> The source object type
 * @param <T> The target object type
 */
public interface Converter<S, T> {
    /** Returns <code>true</code> if this object can convert
     * <code>sourceClass</code> to <code>targetClass</code>.
     * <p>Implementations can accomodate class hierarchy ranges
     * by converting super classes or interfaces.</p>
     *
     * @param sourceClass The source <code>Class</code>
     * @param targetClass The target <code>Class</code>
     * @return <code>true</code> if this object can convert
     * <code>sourceClass</code> to <code>targetClass</code>.
     */
    public boolean canConvert(Class<?> sourceClass, Class<?> targetClass);

    /** Converts <code>obj</code> to <code>T</code>.
     *
     * @param obj The source <code>Object</code> to convert
     * @return The converted <code>Object</code>
     * @throws ConversionException
     */
    public T convert(S obj) throws ConversionException;

    /** Converts <code>obj</code> to <code>T</code>.
     *
     * @param targetClass The <code>Class</code> to convert to
     * @param obj The source <code>Object</code> to convert
     * @return The converted <code>Object</code>
     * @throws ConversionException
     */
    public T convert(Class<? extends T> targetClass, S obj) throws ConversionException;

    /** Returns the source <code>Class</code> for this converter.
     *
     * @return The source <code>Class</code> for this converter
     */
    public Class<?> getSourceClass();

    /** Returns the target <code>Class</code> for this converter.
     *
     * @return The target <code>Class</code> for this converter
     */
    public Class<?> getTargetClass();
}
