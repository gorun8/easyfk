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
