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
