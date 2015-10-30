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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javolution.util.FastList;
import javolution.util.FastSet;

import cn.gorun8.easyfk.base.util.StringUtil;
import cn.gorun8.easyfk.base.util.UtilGenerics;

/** Collection Converter classes. */
public class CollectionConverters implements ConverterLoader {
    public static class ArrayCreator implements ConverterCreator, ConverterLoader {
        public void loadConverters() {
            Converters.registerCreator(this);
        }

        public <S, T> Converter<S, T> createConverter(Class<S> sourceClass, Class<T> targetClass) {
            if (!sourceClass.isArray()) {
               return null;
            }
            if (targetClass != List.class) {
               return null;
            }
            if (!(sourceClass.getComponentType() instanceof Object)) {
                return null;
            }
            return UtilGenerics.cast(new ArrayClassToList<S, T>(sourceClass, targetClass));
        }
    }

    private static class ArrayClassToList<S, T> extends AbstractConverter<S, T> {
        public ArrayClassToList(Class<S> sourceClass, Class<T> targetClass) {
            super(sourceClass, targetClass);
        }

        @Override
        public boolean canConvert(Class<?> sourceClass, Class<?> targetClass) {
            return sourceClass == this.getSourceClass() && targetClass == this.getTargetClass();
        }

        public T convert(S obj) throws ConversionException {
            List<Object> list = FastList.newInstance();
            int len = Array.getLength(obj);
            for (int i = 0; i < len; i++) {
                list.add(Array.get(obj, i));
            }
            return UtilGenerics.<T>cast(list);
        }
    }

    public static class ArrayToList<T> extends AbstractConverter<T[], List<T>> {
        public ArrayToList() {
            super(Object[].class, List.class);
        }

        @Override
        public boolean canConvert(Class<?> sourceClass, Class<?> targetClass) {
            if (!sourceClass.isArray()) {
                return false;
            }
            if (!List.class.isAssignableFrom(targetClass)) {
                return false;
            }
            if (Object[].class.isAssignableFrom(sourceClass)) {
                return true;
            }
            return false;
        }

        public List<T> convert(T[] obj) throws ConversionException {
            return Arrays.asList(obj);
        }
    }

    public static class ListToString<T> extends AbstractConverter<List<T>, String> {
        public ListToString() {
            super(List.class, String.class);
        }

        public String convert(List<T> obj) throws ConversionException {
            return obj.toString();
        }
    }

    public static class MapToList<K, V> extends AbstractConverter<Map<K, V>, List<Map<K, V>>> {
        public MapToList() {
            super(Map.class, List.class);
        }

        public List<Map<K, V>> convert(Map<K, V> obj) throws ConversionException {
            List<Map<K, V>> tempList = FastList.newInstance();
            tempList.add(obj);
            return tempList;
        }
    }

    public static class MapToSet<K, V> extends AbstractConverter<Map<K, V>, Set<Map<K, V>>> {
        public MapToSet() {
            super(Map.class, Set.class);
        }

        public Set<Map<K, V>> convert(Map<K, V> obj) throws ConversionException {
            Set<Map<K, V>> tempSet = FastSet.newInstance();
            tempSet.add(obj);
            return tempSet;
        }
    }

    public static class MapToString<K, V> extends AbstractConverter<Map<K, V>, String> {
        public MapToString() {
            super(Map.class, String.class);
        }

        public String convert(Map<K, V> obj) throws ConversionException {
            return obj.toString();
        }
    }

    public static class StringToList extends GenericSingletonToList<String> {
        public StringToList() {
            super(String.class);
        }

        @Override
        public List<String> convert(String obj) throws ConversionException {
            if (obj.startsWith("[") && obj.endsWith("]")) {
                return StringUtil.toList(obj);
            } else {
                return super.convert(obj);
            }
        }
    }

    public static class StringToMap extends AbstractConverter<String, Map<String, String>> {
        public StringToMap() {
            super(String.class, Map.class);
        }

        public Map<String, String> convert(String obj) throws ConversionException {
            if (obj.startsWith("{") && obj.endsWith("}")) {
                return StringUtil.toMap(obj);
            }
            throw new ConversionException("Could not convert " + obj + " to Map: ");
        }
    }

    public static class StringToSet extends GenericSingletonToSet<String> {
        public StringToSet() {
            super(String.class);
        }

        @Override
        public Set<String> convert(String obj) throws ConversionException {
            if (obj.startsWith("[") && obj.endsWith("]")) {
                return StringUtil.toSet(obj);
            } else {
                return super.convert(obj);
            }
        }
    }

    public void loadConverters() {
        Converters.loadContainedConverters(CollectionConverters.class);
    }
}
