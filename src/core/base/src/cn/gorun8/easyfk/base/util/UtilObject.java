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

import java.io.*;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.ServiceLoader;

import cn.gorun8.easyfk.base.lang.Factory;
import cn.gorun8.easyfk.base.lang.SourceMonitored;

/**
 * UtilObject
 *
 */
@SourceMonitored
public final class UtilObject {
    private UtilObject() {
    }

    public static final String module = UtilObject.class.getName();

    public static byte[] getBytes(InputStream is) {
        byte[] buffer = new byte[4 * 1024];
        byte[] data = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {

                int numBytesRead;
                while ((numBytesRead = is.read(buffer)) != -1) {
                    bos.write(buffer, 0, numBytesRead);
                }
                data = bos.toByteArray();
            } finally {
                bos.close();
            }
        } catch (IOException e) {
            Debug.logError(e, module);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                Debug.logError(e, module);
            }
        }

        return data;
    }

    /** Serialize an object to a byte array */
    public static byte[] getBytes(Object obj) {
        byte[] data = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                try {
                    oos.writeObject(obj);
                    data = bos.toByteArray();
                } catch (IOException e) {
                    Debug.logError(e, module);
                } finally {
                    oos.flush();
                    oos.close();
                }
            } catch (IOException e) {
                // I don't know how to force an error during flush or
                // close of ObjectOutputStream; since OOS is wrapping
                // BAOS, and BAOS does not throw IOException during
                // write, I don't think this can happen.
                Debug.logError(e, module);
            } finally {
                bos.close();
            }
        } catch (IOException e) {
            // How could this ever happen?  BAOS.close() is listed as
            // throwing the exception, but I don't understand why this
            // is.
            Debug.logError(e, module);
        }

        return data;
    }

    /** Returns the size of a serializable object. Non-serializable objects
     * will throw an <code>IOException</code>.<p>It is important to note
     * that the returned value is length of the byte stream after the object has
     * been serialized. The returned value does not represent the amount of memory
     * the object uses. There is no accurate way to determine the size of an
     * object in memory.</p>
     * @param obj
     * @return the number of bytes in the serialized object
     * @throws IOException
     */
    public static long getByteCount(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.flush();
        long size = bos.size();
        bos.close();
        return size;
    }

    /** Deserialize a byte array back to an object */
    public static Object getObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            try {
                ObjectInputStream ois = new ObjectInputStream(bis, Thread.currentThread().getContextClassLoader());
                try {
                    obj = ois.readObject();
                } catch (ClassNotFoundException e) {
                    Debug.logError(e, module);
                } catch (IOException e) {
                    Debug.logError(e, module);
                } finally {
                    ois.close();
                }
            } catch (IOException e) {
                Debug.logError(e, module);
            } finally {
                bis.close();
            }
        } catch (IOException e) {
            // How could this ever happen?  BAIS.close() is listed as
            // throwing the exception, but I don't understand why this
            // is.
            Debug.logError(e, module);
        }
        return obj;
    }

    public static boolean equalsHelper(Object o1, Object o2) {
        if (o1 == o2) {
            // handles same-reference, or null
            return true;
        } else if (o1 == null || o2 == null) {
            // either o1 or o2 is null, but not both
            return false;
        } else {
            return o1.equals(o2);
        }
    }

    public static <T> int compareToHelper(Comparable<T> o1, T o2) {
        if (o1 == o2) {
            // handles same-reference, or null
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            // either o1 or o2 is null, but not both
            return 1;
        } else {
            return o1.compareTo(o2);
        }
    }

    public static int doHashCode(Object o1) {
        if (o1 == null) return 0;
        if (o1.getClass().isArray()) {
            int length = Array.getLength(o1);
            int result = 0;
            for (int i = 0; i < length; i++) {
                result += doHashCode(Array.get(o1, i));
            }
            return result;
        }
        return o1.hashCode();
    }

    public static <A, R> R getObjectFromFactory(Class<? extends Factory<R, A>> factoryInterface, A obj) throws ClassNotFoundException {
        Iterator<? extends Factory<R, A>> it = ServiceLoader.load(factoryInterface).iterator();
        while (it.hasNext()) {
            Factory<R, A> factory = it.next();
            R instance = factory.getInstance(obj);
            if (instance != null) {
                return instance;
            }
        }
        throw new ClassNotFoundException(factoryInterface.getClass().getName());
    }


}
