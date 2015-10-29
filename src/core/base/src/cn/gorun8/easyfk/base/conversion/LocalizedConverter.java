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

import java.util.Locale;
import java.util.TimeZone;

/** Localized converter interface. Classes implement this interface
 * to convert one object type to another. Methods are provided to
 * localize the conversion.
 */
public interface LocalizedConverter<S, T> extends Converter<S, T> {
    /** Converts <code>obj</code> to <code>T</code>.
     *
     * @param obj The source <code>Object</code> to convert
     * @param locale The locale used for conversion - must not be <code>null</code>
     * @param timeZone The time zone used for conversion - must not be <code>null</code>
     * @return The converted <code>Object</code>
     * @throws ConversionException
     */
    public T convert(S obj, Locale locale, TimeZone timeZone) throws ConversionException;

    /** Converts <code>obj</code> to <code>T</code>.
     *
     * @param targetClass The <code>Class</code> to convert to
     * @param obj The source <code>Object</code> to convert
     * @param locale The locale used for conversion - must not be <code>null</code>
     * @param timeZone The time zone used for conversion - must not be <code>null</code>
     * @return The converted <code>Object</code>
     * @throws ConversionException
     */
    public T convert(Class<? extends T> targetClass, S obj, Locale locale, TimeZone timeZone) throws ConversionException;

    /** Converts <code>obj</code> to <code>T</code>.
     *
     * @param obj The source <code>Object</code> to convert
     * @param locale The locale used for conversion - must not be <code>null</code>
     * @param timeZone The time zone used for conversion - must not be <code>null</code>
     * @param formatString Optional formatting string
     * @return The converted <code>Object</code>
     * @throws ConversionException
     */
    public T convert(S obj, Locale locale, TimeZone timeZone, String formatString) throws ConversionException;

    /** Converts <code>obj</code> to <code>T</code>.
     *
     * @param targetClass The <code>Class</code> to convert to
     * @param obj The source <code>Object</code> to convert
     * @param locale The locale used for conversion - must not be <code>null</code>
     * @param timeZone The time zone used for conversion - must not be <code>null</code>
     * @param formatString Optional formatting string
     * @return The converted <code>Object</code>
     * @throws ConversionException
     */
    public T convert(Class<? extends T> targetClass, S obj, Locale locale, TimeZone timeZone, String formatString) throws ConversionException;
}
