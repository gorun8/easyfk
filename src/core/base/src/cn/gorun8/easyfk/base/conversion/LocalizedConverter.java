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
