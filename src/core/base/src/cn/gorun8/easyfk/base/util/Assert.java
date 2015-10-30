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

package cn.gorun8.easyfk.base.util;

import java.util.Collection;
import java.util.Map;

import cn.gorun8.easyfk.base.lang.ThreadSafe;

/** Basic assertions. The static methods in this class provide a convenient way
 * to test method arguments. All of the methods in this class throw <code>
 * IllegalArgumentException</code> if the method arguments are invalid.
 * 
 */
@ThreadSafe
public class Assert {

    /**
     * Tests if an argument is not null and can be cast to a specified class.
     * <p><code>Assert.isAssignableTo("foo", foo, Foo.class);</code></p>
     * 
     * @param argumentName
     * @param argumentObject
     * @param targetClass
     * @throws IllegalArgumentException
     */
    public static void isAssignableTo(String argumentName, Object argumentObject, Class<?> targetClass) {
        notNull(argumentName, argumentObject);
        if (!targetClass.isAssignableFrom(argumentObject.getClass())) {
            throw new IllegalArgumentException(argumentName + " cannot be assigned to " + targetClass.getName());
        }
    }

    /**
     * Tests if an argument is not null and is an instance of a specified class.
     * <p><code>Assert.isInstanceOf("foo", foo, Foo.class);</code></p>
     * 
     * @param argumentName
     * @param argumentObject
     * @param targetClass
     * @throws IllegalArgumentException
     */
    public static void isInstanceOf(String argumentName, Object argumentObject, Class<?> targetClass) {
        notNull(argumentName, argumentObject);
        if (!targetClass.isInstance(argumentObject)) {
            throw new IllegalArgumentException(argumentName + " is not an instance of " + targetClass.getName());
        }
    }

    /**
     * Tests if an argument is not null and is an instance of one of the specified classes.
     * <p><code>Assert.isInstanceOf("foo", foo, Foo.class, Bar.class, ...);</code></p>
     * 
     * @param argumentName
     * @param argumentObject
     * @param targetClasses
     * @throws IllegalArgumentException
     */
    public static void isInstanceOf(String argumentName, Object argumentObject, Class<?>... targetClasses) {
        notNull(argumentName, argumentObject);
        for (int i = 0; i < targetClasses.length;) {
            if (targetClasses[i++].isInstance(argumentObject)) {
                return;
            }
        }
        StringBuilder sb = new StringBuilder(argumentName);
        sb.append(" must be an instance of");
        for (int i = 0; i < targetClasses.length;) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(" ").append(targetClasses[i++].getName());
        }
        throw new IllegalArgumentException(sb.toString());
    }

    /**
     * Tests if an argument is not null and is not an instance of a specified class.
     * <p><code>Assert.isNotInstanceOf("foo", foo, Foo.class);</code></p>
     * 
     * @param argumentName
     * @param argumentObject
     * @param targetClass
     * @throws IllegalArgumentException
     */
    public static void isNotInstanceOf(String argumentName, Object argumentObject, Class<?> targetClass) {
        notNull(argumentName, argumentObject);
        if (targetClass.isInstance(argumentObject)) {
            throw new IllegalArgumentException(argumentName + " cannot be an instance of " + targetClass.getName());
        }
    }

    /**
     * Tests if an argument is not null and is not an instance of one of the specified classes.
     * <p><code>Assert.isNotInstanceOf("foo", foo, Foo.class, Bar.class, ...);</code></p>
     * 
     * @param argumentName
     * @param argumentObject
     * @param targetClasses
     * @throws IllegalArgumentException
     */
    public static void isNotInstanceOf(String argumentName, Object argumentObject, Class<?>... targetClasses) {
        notNull(argumentName, argumentObject);
        for (int i = 0; i < targetClasses.length;) {
            Class<?> targetClass = targetClasses[i++];
            if (targetClass.isInstance(argumentObject)) {
                throw new IllegalArgumentException(argumentName + " cannot be an instance of " + targetClass.getName());
            }
        }
    }

    /**
     * Tests if an argument is not null and is not empty.
     * <p><code>Assert.notEmpty("foo", foo);</code></p>
     * 
     * @param argumentName
     * @param argumentObject
     * @throws IllegalArgumentException
     */
    public static void notEmpty(String argumentName, String argumentObject) {
        notNull(argumentName, argumentObject);
        if (argumentObject.length() == 0) {
            throw new IllegalArgumentException(argumentName + " cannot be empty");
        }
    }

    /**
     * Tests if an argument is not null and is not empty.
     * <p><code>Assert.notEmpty("foo", foo);</code></p>
     * 
     * @param argumentName
     * @param argumentObject
     * @throws IllegalArgumentException
     */
    public static <T extends Map<?, ?>> void notEmpty(String argumentName, T argumentObject) {
        notNull(argumentName, argumentObject);
        if (argumentObject.size() == 0) {
            throw new IllegalArgumentException(argumentName + " cannot be empty");
        }
    }

    /**
     * Tests if an argument is not null and is not empty.
     * <p><code>Assert.notEmpty("foo", foo);</code></p>
     * 
     * @param argumentName
     * @param argumentObject
     * @throws IllegalArgumentException
     */
    public static <T extends Collection<?>> void notEmpty(String argumentName, T argumentObject) {
        notNull(argumentName, argumentObject);
        if (argumentObject.size() == 0) {
            throw new IllegalArgumentException(argumentName + " cannot be empty");
        }
    }

    /**
     * Tests if an argument is not null and is not empty.
     * <p><code>Assert.notEmpty("foo", foo);</code></p>
     * 
     * @param argumentName
     * @param argumentObject
     * @throws IllegalArgumentException
     */
    public static <T> void notEmpty(String argumentName, T[] argumentObject) {
        notNull(argumentName, argumentObject);
        if (argumentObject.length == 0) {
            throw new IllegalArgumentException(argumentName + " cannot be empty");
        }
    }

    /**
     * Tests a list of arguments for <code>null</code>.
     * <p><code>Assert.notNull("foo", foo, "bar", bar, ...);</code></p>
     * 
     * @param arguments
     * @throws IllegalArgumentException
     */
    public static void notNull(Object... arguments) {
        for (int i = 0; i < arguments.length;) {
            notNull((String) arguments[i++], arguments[i++]);
        }
    }

    /**
     * Tests an argument for <code>null</code>.
     * <p><code>Assert.notNull("foo", foo);</code></p>
     * 
     * @param argumentName
     * @param objectToTest
     * @throws IllegalArgumentException
     */
    public static void notNull(String argumentName, Object objectToTest) {
        if (objectToTest == null) {
            throw new IllegalArgumentException(argumentName + " cannot be null");
        }
    }

    private Assert() {}
}
