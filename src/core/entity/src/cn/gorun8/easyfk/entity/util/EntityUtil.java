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
 * Author:hezhiping   Email:110476592@qq.com   Date: 16-1-6
 */

package cn.gorun8.easyfk.entity.util;


import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilDateTime;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericValue;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * Helper methods when dealing with Entities, especially ones that follow certain conventions
 */
public class EntityUtil {

    public static final String module = EntityUtil.class.getName();

    public static <V> Map<String, V> makeFields(V... args) {
        Map<String, V> fields = new HashMap<String, V>();
        if (args != null) {
            for (int i = 0; i < args.length;) {
                if (!(args[i] instanceof String)) throw new IllegalArgumentException("Key(" + i + "), with value(" + args[i] + ") is not a String.");
                String key = (String) args[i];
                i++;
                if (!(args[i] instanceof Comparable<?>)) throw new IllegalArgumentException("Value(" + i + "), with value(" + args[i] + ") does not implement Comparable.");
                if (!(args[i] instanceof Serializable)) throw new IllegalArgumentException("Value(" + i + "), with value(" + args[i] + ") does not implement Serializable.");
                fields.put(key, args[i]);
                i++;
            }
        }
        return fields;
    }


    public static GenericValue getFirst(Collection<GenericValue> values) {
        if (UtilValidate.isNotEmpty(values)) {
            return values.iterator().next();
        } else {
            return null;
        }
    }

    public static GenericValue getFirst(List<GenericValue> values) {
        if (UtilValidate.isNotEmpty(values)) {
            return values.get(0);
        } else {
            return null;
        }
    }

    public static GenericValue getOnly(Collection<GenericValue> values) {
        if (UtilValidate.isNotEmpty(values)) {
            Iterator<GenericValue> it = values.iterator();
            GenericValue result = it.next();
            if (it.hasNext()) {
                throw new IllegalArgumentException("Passed List had more than one value.");
            }
            return result;
        } else {
            return null;
        }
    }

    public static GenericValue getOnly(List<GenericValue> values) {
        if (UtilValidate.isNotEmpty(values)) {
            if (values.size() == 1) {
                return values.get(0);
            } else {
                throw new IllegalArgumentException("Passed List had more than one value.");
            }
        } else {
            return null;
        }
    }

     
    /**
     *returns the values that are currently active.
     *
     *@param datedValues GenericValue's that have "fromDate" and "thruDate" fields
     *@return List of GenericValue's that are currently active
     */
    public static List<GenericValue> filterByDate(List<GenericValue> datedValues) {
        return filterByDate(datedValues, UtilDateTime.nowTimestamp(), null, null, true);
    }

    /**
     *returns the values that are currently active.
     *
     *@param datedValues GenericValue's that have "fromDate" and "thruDate" fields
     *@param allAreSame Specifies whether all values in the List are of the same entity; this can help speed things up a fair amount since we only have to see if the from and thru date fields are valid once
     *@return List of GenericValue's that are currently active
     */
    public static   List<GenericValue> filterByDate(List<GenericValue> datedValues, boolean allAreSame) {
        return filterByDate(datedValues, UtilDateTime.nowTimestamp(), null, null, allAreSame);
    }

    /**
     *returns the values that are active at the moment.
     *
     *@param datedValues GenericValue's that have "fromDate" and "thruDate" fields
     *@param moment the moment in question
     *@return List of GenericValue's that are active at the moment
     */
    public static  List<GenericValue> filterByDate(List<GenericValue> datedValues, java.util.Date moment) {
        return filterByDate(datedValues, new Timestamp(moment.getTime()), null, null, true);
    }

    /**
     *returns the values that are active at the moment.
     *
     *@param datedValues GenericValue's that have "fromDate" and "thruDate" fields
     *@param moment the moment in question
     *@return List of GenericValue's that are active at the moment
     */
    public static  List<GenericValue> filterByDate(List<GenericValue> datedValues, Timestamp moment) {
        return filterByDate(datedValues, moment, null, null, true);
    }

    /**
     *returns the values that are active at the moment.
     *
     *@param datedValues GenericValue's that have "fromDate" and "thruDate" fields
     *@param moment the moment in question
     *@param allAreSame Specifies whether all values in the List are of the same entity; this can help speed things up a fair amount since we only have to see if the from and thru date fields are valid once
     *@return List of GenericValue's that are active at the moment
     */
    public static List<GenericValue> filterByDate(List<GenericValue> datedValues, Timestamp moment, String fromDateName, String thruDateName, boolean allAreSame) {
        if (datedValues == null) return null;
        if (moment == null) return datedValues;
        if (fromDateName == null) fromDateName = "fromDate";
        if (thruDateName == null) thruDateName = "thruDate";

        List<GenericValue> result = new LinkedList<GenericValue>();
        Iterator<GenericValue> iter = datedValues.iterator();

        if (allAreSame) {
            if (iter.hasNext()) {
                GenericValue datedValue = iter.next();
                Timestamp fromDate = datedValue.getTimestamp(fromDateName);
                Timestamp thruDate = datedValue.getTimestamp(thruDateName);

                if ((thruDate == null || thruDate.after(moment)) && (fromDate == null || fromDate.before(moment) || fromDate.equals(moment))) {
                    result.add(datedValue);
                }// else not active at moment
            }

            while (iter.hasNext()) {
                GenericValue datedValue = iter.next();
                Timestamp fromDate = datedValue.getTimestamp(fromDateName);
                Timestamp thruDate = datedValue.getTimestamp(thruDateName);

                if ((thruDate == null || thruDate.after(moment)) && (fromDate == null || fromDate.before(moment) || fromDate.equals(moment))) {
                    result.add(datedValue);
                }// else not active at moment
            }
        } else {
            // if not all values are known to be of the same entity, must check each one...
            while (iter.hasNext()) {
                GenericValue datedValue = iter.next();
                Timestamp fromDate = datedValue.getTimestamp(fromDateName);
                Timestamp thruDate = datedValue.getTimestamp(thruDateName);

                if ((thruDate == null || thruDate.after(moment)) && (fromDate == null || fromDate.before(moment) || fromDate.equals(moment))) {
                    result.add(datedValue);
                }// else not active at moment
            }
        }

        return result;
    }

    public static boolean isValueActive(GenericValue datedValue, Timestamp moment) {
        return isValueActive(datedValue, moment, "fromDate", "thruDate");
    }

    public static boolean isValueActive(GenericValue datedValue, Timestamp moment, String fromDateName, String thruDateName) {
        Timestamp fromDate = datedValue.getTimestamp(fromDateName);
        Timestamp thruDate = datedValue.getTimestamp(thruDateName);

        if ((thruDate == null || thruDate.after(moment)) && (fromDate == null || fromDate.before(moment) || fromDate.equals(moment))) {
            return true;
        } else {
            // else not active at moment
            return false;
        }
    }

    /**
     *returns the values that match the values in fields
     *
     *@param values List of GenericValues
     *@param fields the field-name/value pairs that must match
     *@return List of GenericValue's that match the values in fields
     */
    public static   List<GenericValue> filterByAnd(List<GenericValue> values, Map<String, ? extends Object> fields) {
        if (values == null) return null;

        List<GenericValue> result = null;
        if (UtilValidate.isEmpty(fields)) {
            result = new LinkedList<GenericValue>();
            result.addAll(values);
        } else {
            result = new LinkedList<GenericValue>();
            for (GenericValue value: values) {
                if (value.matchesFields(fields)) {
                    result.add(value);
                }// else did not match
            }
        }
        return result;
    }

}
