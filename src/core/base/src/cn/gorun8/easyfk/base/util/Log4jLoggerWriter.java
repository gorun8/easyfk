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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;

/**
 * Writer implementation for writing to a log4j logger.
 *
 */
public class Log4jLoggerWriter extends PrintWriter {

    public Log4jLoggerWriter(Logger logger) {
        this(logger, Level.INFO);
    }

    public Log4jLoggerWriter(Logger logger, Priority priority) {
        super(new Log4jPrintWriter(logger, priority), true);
    }

    static class Log4jPrintWriter extends Writer {

        private Logger logger = null;
        private Priority priority = null;
        private boolean closed = false;

        public Log4jPrintWriter(Logger logger, Priority priority) {
            lock = logger;
            this.logger = logger;
            this.priority = priority;
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            if (closed) {
                throw new IOException("Writer is closed");
            }

            // Remove the eol
            while (len > 0 && (cbuf[len - 1] == '\n' || cbuf[len - 1] == '\r')) {
                len--;
            }

            // send to log4j
            if (len > 0) {
                logger.log(priority, String.copyValueOf(cbuf, off, len));
            }
        }

        @Override
        public void flush() throws IOException {
            if (closed) {
                throw new IOException("Writer is closed");
            }
        }

        @Override
        public void close() {
            closed = true;
        }
    }
}

