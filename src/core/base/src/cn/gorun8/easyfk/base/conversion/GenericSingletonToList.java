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

import java.util.List;

import javolution.util.FastList;

public class GenericSingletonToList<T> extends AbstractConverter<T, List<T>> {
    public GenericSingletonToList(Class<T> sourceClass) {
        super(sourceClass, List.class);
    }

    public List<T> convert(T obj) throws ConversionException {
        List<T> tempList = FastList.newInstance();
        tempList.add(obj);
        return tempList;
    }
}
