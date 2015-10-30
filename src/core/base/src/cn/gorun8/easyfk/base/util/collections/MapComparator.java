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
package cn.gorun8.easyfk.base.util.collections;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilGenerics;

/**
 * MapComparator.java
 *
 */
public class MapComparator implements Comparator<Map<Object, Object>> {

    public static final String module = MapComparator.class.getName();

    private List<? extends Object> keys;

    /**
     * Method MapComparator.
     * @param keys List of Map keys to sort on
     */
    public MapComparator(List<? extends Object> keys) {
        this.keys = keys;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return obj.equals(this);
    }

    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Map<Object, Object> map1, Map<Object, Object> map2) {

        if (keys == null || keys.size() < 1) {
            throw new IllegalArgumentException("No sort fields defined");
        }

        for (Object key: keys) {
            // if false will be descending, ie reverse order
            boolean ascending = true;

            Object o1 = null;
            Object o2 = null;

            if (key instanceof FlexibleMapAccessor<?>) {
                FlexibleMapAccessor<Object> fmaKey = UtilGenerics.cast(key);
                ascending = fmaKey.getIsAscending();

                //Debug.logInfo("Doing compare with a FlexibleMapAccessor [" + fmaKey.getOriginalName() + "] ascending [" + ascending + "]", module);

                o1 = fmaKey.get(UtilGenerics.<String, Object>checkMap(map1));
                o2 = fmaKey.get(UtilGenerics.<String, Object>checkMap(map2));
            } else {
                if (key instanceof String) {
                    String keyStr = (String) key;
                    if (keyStr.charAt(0) == '-') {
                        ascending = false;
                        key = keyStr.substring(1);
                    } else if (keyStr.charAt(0) == '+') {
                        ascending = true;
                        key = keyStr.substring(1);
                    }
                }

                o1 = map1.get(key);
                o2 = map2.get(key);
            }

            if (o1 == null && o2 == null) {
                continue;
            }

            int compareResult = 0;
            if (o1 != null && o2 == null) {
                compareResult = -1;
            }
            if (o1 == null && o2 != null) {
                compareResult = 1;
            }

            if (compareResult == 0) {
                try {
                    // the map values in question MUST implement the Comparable interface, if not we'll throw an exception
                    Comparable<Object> comp1 = UtilGenerics.cast(o1);
                    compareResult = comp1.compareTo(o2);
                } catch (Exception e) {
                    String errorMessage = "Error sorting list of Maps: " + e.toString();
                    Debug.logError(e, errorMessage, module);
                    throw new RuntimeException(errorMessage);
                }
            }

            // if zero they are equal, so we carry on to the next key to try and find a difference
            if (compareResult != 0) {
                if (ascending) {
                    return compareResult;
                } else {
                    return -compareResult;
                }
            }
        }

        // none of them were different, so they are equal
        return 0;
    }
}
