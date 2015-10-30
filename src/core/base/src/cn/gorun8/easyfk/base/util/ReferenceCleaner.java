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

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public final class ReferenceCleaner {
    public static final String module = ReferenceCleaner.class.getName();

    private static final class CleanerThread extends Thread {
        private boolean keepRunning = true;

        protected CleanerThread() {
            setDaemon(true);
            setName("ReferenceCleaner");
        }

        protected void stopRunning() {
            keepRunning = false;
        }

        @Override
        public void run() {
            while (keepRunning) {
                try {
                    ((Removable) QUEUE.remove()).remove();
                } catch (Throwable t) {
                    // ignore
                }
                if (interrupted()) {
                    stopRunning();
                    cleanerThread = new CleanerThread();
                    cleanerThread.start();
                }
            }
        }
    }
    private static CleanerThread cleanerThread = new CleanerThread();

    static {
        cleanerThread.start();
    }

    private ReferenceCleaner() {
    }

    private static final ReferenceQueue<Object> QUEUE = new ReferenceQueue<Object>();

    public interface Removable {
        void remove() throws Exception;
    }

    public abstract static class Soft<V> extends SoftReference<V> implements Removable {
        public Soft(V value) {
            super(value, QUEUE);
        }
    }

    public abstract static class Phantom<V> extends PhantomReference<V> implements Removable {
        public Phantom(V value) {
            super(value, QUEUE);
        }
    }

    public abstract static class Weak<V> extends WeakReference<V> implements Removable {
        public Weak(V value) {
            super(value, QUEUE);
        }
    }
}
