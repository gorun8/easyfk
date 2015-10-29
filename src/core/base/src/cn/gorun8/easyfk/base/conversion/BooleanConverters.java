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

/** Boolean Converter classes. */
public class BooleanConverters implements ConverterLoader {
    public static class BooleanToInteger extends AbstractConverter<Boolean, Integer> {
        public BooleanToInteger() {
            super(Boolean.class, Integer.class);
        }

        public Integer convert(Boolean obj) throws ConversionException {
             return obj.booleanValue() ? 1 : 0;
        }
    }

    public static class BooleanToList extends GenericSingletonToList<Boolean> {
        public BooleanToList() {
            super(Boolean.class);
        }
    }

    public static class BooleanToSet extends GenericSingletonToSet<Boolean> {
        public BooleanToSet() {
            super(Boolean.class);
        }
    }

    public static class BooleanToString extends AbstractConverter<Boolean, String> {
        public BooleanToString() {
            super(Boolean.class, String.class);
        }

        public String convert(Boolean obj) throws ConversionException {
            return obj.booleanValue() ? "true" : "false";
        }
    }

    public static class IntegerToBoolean extends AbstractConverter<Integer, Boolean> {
        public IntegerToBoolean() {
            super(Integer.class, Boolean.class);
        }

        public Boolean convert(Integer obj) throws ConversionException {
             return obj.intValue() == 0 ? false : true;
        }
    }

    public static class StringToBoolean extends AbstractConverter<String, Boolean> {
        public StringToBoolean() {
            super(String.class, Boolean.class);
        }

        public Boolean convert(String obj) throws ConversionException {
            return "TRUE".equals(obj.trim().toUpperCase());
        }
    }

    public void loadConverters() {
        Converters.loadContainedConverters(BooleanConverters.class);
    }
}
