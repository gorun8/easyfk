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
