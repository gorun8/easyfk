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

import java.util.Set;

import javolution.util.FastSet;

public class GenericSingletonToSet<T> extends AbstractConverter<T, Set<T>> {
    public GenericSingletonToSet(Class<T> sourceClass) {
        super(sourceClass, Set.class);
    }

    public Set<T> convert(T obj) throws ConversionException {
        Set<T> tempSet = FastSet.newInstance();
        tempSet.add(obj);
        return tempSet;
    }
}
