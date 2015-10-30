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
package cn.gorun8.easyfk.base.start;


import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * StartupException
 *
 */
@SuppressWarnings("serial")
public class StartupException extends Exception {

    Throwable nested = null;

    /**
     * Creates new <code>StartupException</code> without detail message.
     */
    public StartupException() {
        super();
    }

    /**
     * Constructs an <code>StartupException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public StartupException(String msg) {
        super(msg);
    }

    /**
     * Constructs an <code>StartupException</code> with the specified detail message and nested Exception.
     * @param msg the detail message.
     * @param nested the chained exception.
     */
    public StartupException(String msg, Throwable nested) {
        super(msg);
        this.nested = nested;
    }

    /**
     * Constructs an <code>StartupException</code> with the specified detail message and nested Exception.
     * @param nested the chained exception.
     */
    public StartupException(Throwable nested) {
        super();
        this.nested = nested;
    }

    /** Returns the detail message, including the message from the nested exception if there is one. */
    @Override
    public String getMessage() {
        if (nested != null) {
            return super.getMessage() + " (" + nested.getMessage() + ")";
        } else {
            return super.getMessage();
        }
    }

    /** Returns the detail message, NOT including the message from the nested exception. */
    public String getNonNestedMessage() {
        return super.getMessage();
    }

    /** Returns the nested exception if there is one, null if there is not. */
    public Throwable getNested() {
        if (nested == null) {
            return this;
        }
        return nested;
    }

    /** Prints the composite message to System.err. */
    @Override
    public void printStackTrace() {
        super.printStackTrace();
        if (nested != null) {
            nested.printStackTrace();
        }
    }

    /** Prints the composite message and the embedded stack trace to the specified stream ps. */
    @Override
    public void printStackTrace(PrintStream ps) {
        super.printStackTrace(ps);
        if (nested != null) {
            nested.printStackTrace(ps);
        }
    }

    /** Prints the composite message and the embedded stack trace to the specified print writer pw. */
    @Override
    public void printStackTrace(PrintWriter pw) {
        super.printStackTrace(pw);
        if (nested != null) {
            nested.printStackTrace(pw);
        }
    }
}
