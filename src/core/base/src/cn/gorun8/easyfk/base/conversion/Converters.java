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

import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.ServiceLoader;

import javolution.util.FastMap;
import javolution.util.FastSet;

import cn.gorun8.easyfk.base.lang.SourceMonitored;
import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.ObjectType;
import cn.gorun8.easyfk.base.util.UtilGenerics;

/** A <code>Converter</code> factory and repository. */
@SourceMonitored
public class Converters {
    protected static final String module = Converters.class.getName();
    protected static final String DELIMITER = "->";
    protected static final FastMap<String, Converter<?, ?>> converterMap = FastMap.newInstance();
    protected static final FastSet<ConverterCreator> creators = FastSet.newInstance();
    protected static final FastSet<String> noConversions = FastSet.newInstance();

    static {
        converterMap.setShared(true);
        registerCreator(new PassThruConverterCreator());
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Iterator<ConverterLoader> converterLoaders = ServiceLoader.load(ConverterLoader.class, loader).iterator();
        while (converterLoaders.hasNext()) {
            try {
                ConverterLoader converterLoader = converterLoaders.next();
                converterLoader.loadConverters();
            } catch (Exception e) {
                Debug.logError(e, module);
            }
        }
    }

    private Converters() {}

    /** Returns an appropriate <code>Converter</code> instance for
     * <code>sourceClass</code> and <code>targetClass</code>. If no matching
     * <code>Converter</code> is found, the method throws
     * <code>ClassNotFoundException</code>.
     *
     * <p>This method is intended to be used when the source or
     * target <code>Object</code> types are unknown at compile time.
     * If the source and target <code>Object</code> types are known
     * at compile time, then one of the "ready made" converters should be used.</p>
     *
     * @param sourceClass The object class to convert from
     * @param targetClass The object class to convert to
     * @return A matching <code>Converter</code> instance
     * @throws ClassNotFoundException
     */
    public static <S, T> Converter<S, T> getConverter(Class<S> sourceClass, Class<T> targetClass) throws ClassNotFoundException {
        String key = sourceClass.getName().concat(DELIMITER).concat(targetClass.getName());
        if (Debug.verboseOn()) {
            Debug.logVerbose("Getting converter: " + key, module);
        }
OUTER:
        do {
            Converter<?, ?> result = converterMap.get(key);
            if (result != null) {
                return UtilGenerics.cast(result);
            }
            if (noConversions.contains(key)) {
                throw new ClassNotFoundException("No converter found for " + key);
            }
            for (Converter<?, ?> value : converterMap.values()) {
                if (value.canConvert(sourceClass, targetClass)) {
                    converterMap.putIfAbsent(key, value);
                    continue OUTER;
                }
            }
            for (ConverterCreator value : creators) {
                result = createConverter(value, sourceClass, targetClass);
                if (result != null) {
                    converterMap.putIfAbsent(key, result);
                    continue OUTER;
                }
            }
            if (noConversions.add(key)) {
                Debug.logWarning("*** No converter found, converting from " +
                        sourceClass.getName() + " to " + targetClass.getName() +
                        ". Please report this message to the developer community so " +
                        "a suitable converter can be created. ***", module);
            }
            throw new ClassNotFoundException("No converter found for " + key);
        } while (true);
    }

    private static <S, SS extends S, T, TT extends T> Converter<SS, TT> createConverter(ConverterCreator creater, Class<SS> sourceClass, Class<TT> targetClass) {
        return creater.createConverter(sourceClass, targetClass);
    }

    /** Load all classes that implement <code>Converter</code> and are
     * contained in <code>containerClass</code>.
     *
     * @param containerClass
     */
    public static void loadContainedConverters(Class<?> containerClass) {
        // This only returns -public- classes and interfaces
        for (Class<?> clz: containerClass.getClasses()) {
            try {
                // non-abstract, which means no interfaces or abstract classes
                if ((clz.getModifiers() & Modifier.ABSTRACT) == 0) {
                    Object value;
                    try {
                        value = clz.getConstructor().newInstance();
                    } catch (NoSuchMethodException e) {
                        // ignore this, as this class might be some other helper class,
                        // with a non-pubilc constructor
                        continue;
                    }
                    if (value instanceof ConverterLoader) {
                        ConverterLoader loader = (ConverterLoader) value;
                        loader.loadConverters();
                    }
                }
            } catch (Exception e) {
                Debug.logError(e, module);
            }
        }
    }

    /** Registers a <code>ConverterCreater</code> instance to be used by the
     * {@link cn.gorun8.easyfk.base.conversion.Converters#getConverter(Class, Class)}
     * method, when a converter can't be found.
     *
     * @param <S> The source object type
     * @param <T> The target object type
     * @param creator The <code>ConverterCreater</code> instance to register
     */
    public static <S, T> void registerCreator(ConverterCreator creator) {
        creators.add(creator);
    }

    /** Registers a <code>Converter</code> instance to be used by the
     * {@link cn.gorun8.easyfk.base.conversion.Converters#getConverter(Class, Class)}
     * method.
     *
     * @param <S> The source object type
     * @param <T> The target object type
     * @param converter The <code>Converter</code> instance to register
     */
    public static <S, T> void registerConverter(Converter<S, T> converter) {
        registerConverter(converter, converter.getSourceClass(), converter.getTargetClass());
    }

    public static <S, T> void registerConverter(Converter<S, T> converter, Class<?> sourceClass, Class<?> targetClass) {
        StringBuilder sb = new StringBuilder();
        if (sourceClass != null) {
            sb.append(sourceClass.getName());
        } else {
            sb.append("<null>");
        }
        sb.append(DELIMITER);
        sb.append(targetClass.getName());
        String key = sb.toString();
        if (converterMap.putIfAbsent(key, converter) == null) {
            Debug.logVerbose("Registered converter " + converter.getClass().getName(), module);
        }
    }

    protected static class PassThruConverterCreator implements ConverterCreator{
        protected PassThruConverterCreator() {
        }

        public <S, T> Converter<S, T> createConverter(Class<S> sourceClass, Class<T> targetClass) {
            if (ObjectType.instanceOf(sourceClass, targetClass)) {
                return new PassThruConverter<S, T>(sourceClass, targetClass);
            } else {
                return null;
            }
        }
    }

    /** Pass thru converter used when the source and target java object
     * types are the same. The <code>convert</code> method returns the
     * source object.
     *
     */
    protected static class PassThruConverter<S, T> implements Converter<S, T> {
        private final Class<S> sourceClass;
        private final Class<T> targetClass;

        public PassThruConverter(Class<S> sourceClass, Class<T> targetClass) {
            this.sourceClass = sourceClass;
            this.targetClass = targetClass;
        }

        public boolean canConvert(Class<?> sourceClass, Class<?> targetClass) {
            return this.sourceClass == sourceClass && this.targetClass == targetClass;
        }

        @SuppressWarnings("unchecked")
        public T convert(S obj) throws ConversionException {
            return (T) obj;
        }

        @SuppressWarnings("unchecked")
        public T convert(Class<? extends T> targetClass, S obj) throws ConversionException {
            return (T) obj;
        }

        public Class<?> getSourceClass() {
            return sourceClass;
        }

        public Class<?> getTargetClass() {
            return targetClass;
        }
    }
}
