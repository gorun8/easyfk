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
package cn.gorun8.easyfk.base.lang;

public interface ObjectWrapper<T> {
    T getObject() throws ObjectException;

    @SuppressWarnings("serial")
    public class ObjectException extends Exception {
        protected ObjectException(Throwable cause) {
            this(cause.getMessage(), cause);
        }

        protected ObjectException(String msg, Throwable cause) {
            super(msg, cause);
        }

        protected ObjectException(String msg) {
            super(msg);
        }

        public static final <T> T checkException(Throwable t) throws ObjectException {
            if (t instanceof ObjectException) throw (ObjectException) t;
            if (t instanceof RuntimeException) throw (RuntimeException) t;
            if (t instanceof Error) throw (Error) t;
            throw new NestedException(t);
        }
    }

    @SuppressWarnings("serial")
    public class NestedException extends ObjectException {
        public NestedException(Throwable cause) {
            super(cause);
        }
    }

    @SuppressWarnings("serial")
    public class ConfigurationException extends RuntimeException {
        public ConfigurationException(String msg) {
            super(msg);
        }
    }
}
