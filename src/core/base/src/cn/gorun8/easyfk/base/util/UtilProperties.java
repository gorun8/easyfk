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

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import javolution.util.FastList;
import javolution.util.FastSet;

import cn.gorun8.easyfk.base.location.FlexibleLocation;
import cn.gorun8.easyfk.base.util.cache.UtilCache;
import cn.gorun8.easyfk.base.util.collections.ResourceBundleMapWrapper;
import cn.gorun8.easyfk.base.util.string.FlexibleStringExpander;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 缓存的类属性接口-进行属性文件操作的通用接口
 * UtilProperties类将属性文件分为两类：
 * 无语言化指定的属性文件：被应用于应用参数、配置设置等
 * 语言化指定的属性文件：被用于UI标签、系统信息等。每个属性文件的类都保存在自己的缓存中
 * 属性文件的语言化指定类可以被应用于以下三种格式的文件：
 * 标准的基于text的键值对格式（*.properties文件），java XML属性文件格式和框架应用指定的XML文件形式
 */

@SuppressWarnings("serial")
public class UtilProperties implements Serializable {

    public static final String module = UtilProperties.class.getName();
    
    /**
     * 存储无语言化指定的属性的缓存实例
     * 每个属性实例由资源字符串进行管理
     */
    protected static UtilCache<String, Properties> resourceCache = UtilCache.createUtilCache("properties.UtilPropertiesResourceCache");

    /**
     * 存储无语言化指定的属性的缓存实例
     * 每个属性实例由文件的路径进行管理
     */
    protected static UtilCache<String, Properties> urlCache = UtilCache.createUtilCache("properties.UtilPropertiesUrlCache");

    protected static Locale fallbackLocale = null;
    protected static Set<Locale> defaultCandidateLocales = null;
    protected static Set<String> propertiesNotFound = FastSet.newInstance();

    /**
     * 比较指定属性和比较字符串，相同返回真，否则返回假
     * @param resource 资源名，如果属性文件是“webevent.properties”，属性值则是“webevent”
     * @param name 属性文件的属性名
     * @param compareString 与属性值相比较的字符串
     * @return 相同返回真，否则返回假
     */
    public static boolean propertyValueEquals(String resource, String name, String compareString) {
        String value = getPropertyValue(resource, name);

        if (value == null) return false;
        return value.trim().equals(compareString);
    }

    /**
     * 比较指定属性和比较字符串，不考虑大小写。相同返回真，否则返回假
     * @param resource 资源名，如果属性文件是“webevent.properties”，属性值则是“webevent”
     * @param name 属性文件的属性名
     * @param compareString 与属性值相比较的字符串
     * @return 相同返回真，否则返回假
     */
    public static boolean propertyValueEqualsIgnoreCase(String resource, String name, String compareString) {
        String value = getPropertyValue(resource, name);

        if (value == null) return false;
        return value.trim().equalsIgnoreCase(compareString);
    }

    /**
     * 从指定的资源/属性文件中返回指定属性的值
     * @param resource 资源名，如果属性文件是“webevent.properties”，属性值则是“webevent”
     * @param name 资源文件的资源名
     * @param defaultValue 找不到资源返回该值
     * @return 属性文件中属性的值，找不到属性返回默认值
     */
    public static String getPropertyValue(String resource, String name, String defaultValue) {
        String value = getPropertyValue(resource, name);

        if (UtilValidate.isEmpty(value))
            return defaultValue;
        else
            return value;
    }
    public static double getPropertyNumber(String resource, String name, double defaultValue) {
        String str = getPropertyValue(resource, name);
        if (str == null) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static double getPropertyNumber(String resource, String name) {
        return getPropertyNumber(resource, name, 0.00000);
    }

    /**
     * 从指定资源/属性文件中返回指定的属性值，该值的类型是Number类型
     * 如果找不到指定文件或者指定属性名，返回默认的属性值
     * @param resource 资源名，如果属性文件是“webevent.properties”，属性值则是“webevent”
     * @param name 属性文件的属性名
     * @param defaultNumber 找不到属性名时，返回该值
     * @param type 属性值转换成的类型的字符串表示，比如说Integer
     * @return Number类型的属性值，找不到属性值则返回默认值
     */
    private static Number getPropertyNumber(String resource, String name, Number defaultNumber, String type) {
        String str = getPropertyValue(resource, name);
        if (UtilValidate.isEmpty(str)) {
            Debug.logWarning("Error converting String \"" + str + "\" to " + type + "; using defaultNumber " + defaultNumber + ".", module);
            return defaultNumber;
        } else
            try {
                return (Number)(ObjectType.simpleTypeConvert(str, type, null, null));
            } catch (GeneralException e) {
                Debug.logWarning("Error converting String \"" + str + "\" to " + type + "; using defaultNumber " + defaultNumber + ".", module);
            }
            return defaultNumber;
    }

    /**
     * 从指定资源/属性文件中返回指定的属性值，该值的类型是Boolean（布尔）类型
     * 如果找不到指定文件或者指定属性名，返回默认的属性值
     * @param resource 资源名，如果属性文件是“webevent.properties”，属性值则是“webevent”
     * @param name 属性文件的属性名
     * @param defaultNumber 找不到属性名时，返回该值
     * @return 布尔类型的属性值，找不到属性值则返回默认值
     */
    public static Boolean getPropertyAsBoolean(String resource, String name, boolean defaultValue) {
        String str = getPropertyValue(resource, name);
        if ("true".equalsIgnoreCase(str)) return Boolean.TRUE;
        else if ("false".equalsIgnoreCase(str)) return Boolean.FALSE;
        else return defaultValue;
    }

    /**
     * 从指定资源/属性文件中返回指定的属性值，该值的类型是Integer（整形）类型
     * 如果找不到指定文件或者指定属性名，返回默认的属性值
     * @param resource 资源名，如果属性文件是“webevent.properties”，属性值则是“webevent”
     * @param name 属性文件的属性名
     * @param defaultNumber 找不到属性名时，返回该值
     * @return 整形类型的属性值，找不到属性值则返回默认值
     */
    public static Integer getPropertyAsInteger(String resource, String name, int defaultNumber) {
        return (Integer)getPropertyNumber(resource, name, defaultNumber, "Integer");
    }

    /**
     * 从指定资源/属性文件中返回指定的属性值，该值的类型是Long（长整形）类型
     * 如果找不到指定文件或者指定属性名，返回默认的属性值
     * @param resource 资源名，如果属性文件是“webevent.properties”，属性值则是“webevent”
     * @param name 属性文件的属性名
     * @param defaultNumber 找不到属性名时，返回该值
     * @return 长整形型的属性值，找不到属性值则返回默认值
     */
    public static Long getPropertyAsLong(String resource, String name, long defaultNumber) {
        return (Long)getPropertyNumber(resource, name, defaultNumber, "Long");
    }

    /**
     * 从指定资源/属性文件中返回指定的属性值，该值的类型是Float（单精度）类型
     * 如果找不到指定文件或者指定属性名，返回默认的属性值
     * @param resource 资源名，如果属性文件是“webevent.properties”，属性值则是“webevent”
     * @param name 属性文件的属性名
     * @param defaultNumber 找不到属性名时，返回该值
     * @return 单精度型的属性值，找不到属性值则返回默认值
     */
    public static Float getPropertyAsFloat(String resource, String name, float defaultNumber) {
        return (Float)getPropertyNumber(resource, name, defaultNumber, "Float");
    }

    /**
     * 从指定资源/属性文件中返回指定的属性值，该值的类型是Double（双精度）类型
     * 如果找不到指定文件或者指定属性名，返回默认的属性值
     * @param resource 资源名，如果属性文件是“webevent.properties”，属性值则是“webevent”
     * @param name 属性文件的属性名
     * @param defaultNumber 找不到属性名时，返回该值
     * @return 双精度型的属性值，找不到属性值则返回默认值
     */
    public static Double getPropertyAsDouble(String resource, String name, double defaultNumber) {
        return (Double)getPropertyNumber(resource, name, defaultNumber, "Double");
    }

    /**
     * 从指定资源/属性文件中返回指定属性的值，该值的类型是BigInteger类型
     * 如果找不到指定文件或者指定属性名，返回默认的属性值
     * @param resource 资源名，如果属性文件是“webevent.properties”，属性值则是“webevent”
     * @param name 属性文件的属性名
     * @param defaultNumber 找不到属性名时，返回该值
     * @return BigInteger类型的属性值，找不到属性值则返回默认值
     */
    public static BigInteger getPropertyAsBigInteger(String resource, String name, BigInteger defaultNumber) {
        String strValue = getPropertyValue(resource, name);
        BigInteger result = defaultNumber;
        try {
            result = new BigInteger(strValue);
        } catch (NumberFormatException nfe) {
            Debug.logWarning("Couldnt convert String \"" + strValue + "\" to BigInteger; using defaultNumber " + defaultNumber.toString() + ".", module);
        }
        return result;
    }

    /**
     * 从指定资源/属性文件中返回指定属性的值，该值的类型是BigDecimal类型
     * 如果找不到指定文件或者指定属性名，返回默认的属性值
     * @param resource 资源名，如果属性文件是“webevent.properties”，属性值则是“webevent”
     * @param name 属性文件的属性名
     * @param defaultNumber 找不到属性名时，返回该值
     * @return BigDecimal型的属性值，找不到属性值则返回默认值
     */
    public static BigDecimal getPropertyAsBigDecimal(String resource, String name, BigDecimal defaultNumber) {
        String strValue = getPropertyValue(resource, name);
        BigDecimal result = defaultNumber;
        try {
            result = new BigDecimal(strValue);
        } catch (NumberFormatException nfe) {
            Debug.logWarning("Couldnt convert String \"" + strValue + "\" to BigDecimal; using defaultNumber " + defaultNumber.toString() + ".", module);
        }
        return result;
    }

    /**
     * 从指定的资源/属性文件中返回指定文件的值
     * @param resource 资源的类型，可以是文件、类或者URL
     * @param name 属性文件的属性名
     * @return 返回指定文件的属性值
     */
    public static String getPropertyValue(String resource, String name) {
        if (resource == null || resource.length() <= 0) return "";
        if (name == null || name.length() <= 0) return "";

        Properties properties = getProperties(resource);
        if (properties == null) {
            return "";
        }

        String value = null;

        try {
            value = properties.getProperty(name);
        } catch (Exception e) {
            Debug.logInfo(e, module);
        }
        return value == null ? "" : value.trim();
    }

    /**
     * 返回指定的资源/属性文件
     * @param resource 资源的类型可以是文件、类或者URL
     * @return 返回资源文件
     */
    public static Properties getProperties(String resource) {
        if (resource == null || resource.length() <= 0) {
            return null;
        }
        String cacheKey = resource.replace(".properties", "");
        Properties properties = resourceCache.get(cacheKey);
        if (properties == null) {
            try {
                URL url = UtilURL.fromResource(resource);

                if (url == null)
                    return null;
                properties = getProperties(url);
                resourceCache.put(cacheKey, properties);
            } catch (MissingResourceException e) {
                Debug.logInfo(e, module);
            }
        }
        if (properties == null) {
            Debug.logInfo("[UtilProperties.getProperties] could not find resource: " + resource, module);
            return null;
        }
        return properties;
    }

    /**
     * 返回指定的资源/属性文件
     * @param url 资源的路径
     * @return 返回属性文件
     */
    public static Properties getProperties(URL url) {
        if (url == null) {
            return null;
        }
        Properties properties = urlCache.get(url.toString());
        if (properties == null) {
            try {
                properties = new Properties();
                properties.load(url.openStream());
                urlCache.put(url.toString(), properties);
            } catch (Exception e) {
                Debug.logInfo(e, module);
            }
        }
        if (properties == null) {
            Debug.logInfo("[UtilProperties.getProperties] could not find resource: " + url, module);
            return null;
        }
        return properties;
    }


    // ========= 基于URL的方法 ==========

    /**
     * 比较指定属性和比较字符串。相同返回真，否则返回假
     * @param url URL类指向资源的存放路径
     * @param name 属性文件中的属性值
     * @param compareString 与属性值相比较的字符串
     * @return 相同返回真，否则返回假
     */
    public static boolean propertyValueEquals(URL url, String name, String compareString) {
        String value = getPropertyValue(url, name);

        if (value == null) return false;
        return value.trim().equals(compareString);
    }

    /**
     * 比较类型无关的指定属性值和比较字符串，相同返回真，否则返回假
     * @param url URL类指向资源的存放路径
     * @param name 属性文件中的属性值
     * @param compareString 与属性值相比较的字符串
     * @return 相同返回真，否则返回假
     */
    public static boolean propertyValueEqualsIgnoreCase(URL url, String name, String compareString) {
        String value = getPropertyValue(url, name);

        if (value == null) return false;
        return value.trim().equalsIgnoreCase(compareString);
    }

    /**
     * 从指定资源/属性文件返回指定属性的属性值
     * 如果找不到指定的属性名或者属性文件，返回默认值
     * @param url URL类指向资源的路径
     * @param name 属性文件中的属性名
     * @param defaultValue 如果找不到指定属性，返回该值
     * @return 指定文件的指定属性值，找不到属性的话返回默认值
     */
    public static String getPropertyValue(URL url, String name, String defaultValue) {
        String value = getPropertyValue(url, name);

        if (value == null || value.length() <= 0)
            return defaultValue;
        else
            return value;
    }

    public static double getPropertyNumber(URL url, String name, double defaultValue) {
        String str = getPropertyValue(url, name);
        if (str == null) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static double getPropertyNumber(URL url, String name) {
        return getPropertyNumber(url, name, 0.00000);
    }

    /**
     * 从指定资源/属性文件返回指定属性的属性值
     * @param url URL类指向资源的路径
     * @param name 属性文件中的属性名
     * @return 返回属性文件的属性值
     */
    public static String getPropertyValue(URL url, String name) {
        if (url == null) return "";
        if (name == null || name.length() <= 0) return "";
        Properties properties = getProperties(url);

        if (properties == null) {
            return null;
        }

        String value = null;

        try {
            value = properties.getProperty(name);
        } catch (Exception e) {
            Debug.logInfo(e, module);
        }
        return value == null ? "" : value.trim();
    }

    /**
     * 从指定资源/属性文件返回一个子属性的值，而不是指定属性名
     * @param url 指向资源位置的URL
     * @param name 属性文件中的子属性的名称
     * @return 属性文件中子属性的值
     */
    public static String getSplitPropertyValue(URL url, String name) {
        if (url == null) return "";
        if (name == null || name.length() <= 0) return "";

        Properties properties = getProperties(url);

        if (properties == null) {
            return null;
        }

        String value = null;

        try {
            int curIdx = 1;
            String curName = null;

            while ((curName = properties.getProperty("name." + curIdx)) != null) {
                if (name.equals(curName)) {
                    value = properties.getProperty("value." + curIdx);
                    break;
                }
                curIdx++;
            }
        } catch (Exception e) {
            Debug.logInfo(e, module);
        }
        return value == null ? "" : value.trim();
    }

    /**
     * 为指定的资源/属性文件中的指定属性设置指定的值。
     * @param resource 资源的类型，必须是file。
     * @param name 属性文件的属性名
     * @param value 属性文件中的属性值
     */
     public static void setPropertyValue(String resource, String name, String value) {
         if (resource == null || resource.length() <= 0) return;
         if (name == null || name.length() <= 0) return;

         Properties properties = getProperties(resource);
         if (properties == null) {
             return;
         }

         try {
             properties.setProperty(name, value);
             FileOutputStream propFile = new FileOutputStream(resource);
             if ("XuiLabels".equals(name)) {
                 properties.store(propFile,
                     "##############################################################################\n"
                     +"# Project:Easy Web Framework                   \n"
                     +"#                   \n"
                     +"# Description: This project is based on much more open source projects than ever before,         \n"
                     +"# and can be applied to mostly web development environment.                        \n"
                     +"#                     \n"
                     +"#                              \n"
                     +"# \"License\"); you may not use this file except in compliance                 \n"
                     +"#                    \n"
                     +"#                                                                              \n"
                     +"# Author:hezhiping   Email:110476592@qq.com                                     \n"
                     +"#                                                                              \n"
                     +"#                    \n"
                     +"#                   \n"
                     +"# \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY                     \n"
                     +"#                     \n"
                     +"#                       \n"
                     +"#                                                            \n"
                     +"###############################################################################\n"
                     +"#                                                                              \n"
                     +"# Dynamically modified by EasyFK Framework (cn.gorun8.easyfk.base.util : UtilProperties.setPropertyValue)\n"
                     +"#                                                                              \n"
                     +"# By default the screen is 1024x768 wide. If you want to use another screen size,\n"
                     +"# you must create a new directory under baseapps/pos/screens, like the 800x600.\n"
                     +"# You must also set the 3 related parameters (StartClass, ClientWidth, ClientHeight) accordingly.\n"
                     +"#");
             } else {
                 properties.store(propFile,
                     "##############################################################################\n"
                     +"# Project:Easy Web Framework                   \n"
                     +"#                   \n"
                     +"# Description: This project is based on much more open source projects than ever before,         \n"
                     +"# and can be applied to mostly web development environment.                        \n"
                     +"#                     \n"
                     +"#                              \n"
                     +"# \"License\"); you may not use this file except in compliance                 \n"
                     +"#                    \n"
                     +"#                                                                              \n"
                     +"# Author:hezhiping   Email:110476592@qq.com                                  \n"
                     +"#                                                                              \n"
                     +"#                    \n"
                     +"#                   \n"
                     +"# \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY                     \n"
                     +"#                     \n"
                     +"#                       \n"
                     +"#                                                            \n"
                     +"###############################################################################\n"
                     +"#                                                                              \n"
                     +"# Dynamically modified by EasyFK Framework (cn.gorun8.easyfk.base.util : UtilProperties.setPropertyValue)\n"
                     +"# The comments have been removed, you may still find them on the EasyFK repository... \n"
                     +"#");
             }

             propFile.close();
         } catch (FileNotFoundException e) {
             Debug.logInfo(e, "Unable to located the resource file.", module);
         } catch (IOException e) {
             Debug.logError(e, module);
         }
     }

     /**
      * 为内存中指定资源/属性文件中的指定属性设置指定的值。
      * @param resource 资源的名称。
      * @param name 资源中的属性名。
      * @param value 需要在内存中设置的属性值。
      */
      public static void setPropertyValueInMemory(String resource, String name, String value) {
          if (resource == null || resource.length() <= 0) return;
          if (name == null || name.length() <= 0) return;

          Properties properties = getProperties(resource);
          if (properties == null) {
              return;
          }
          properties.setProperty(name, value);
      }

    // ========= 基于语言化类和资源的方法 ==========

      /**
       * 从指定的属性/资源文件返回指定属性名对应的值，该文件相当于给定的Locale对象
       * @param resource 资源的类型可以是文件、类或者URL
       * @param name 属性文件中的属性名
       * @param locale 给定资源文件的语言化信息
       * @return 返回属性文件的属性值
       */
    public static String getMessage(String resource, String name, Locale locale) {
        if (resource == null || resource.length() <= 0) return "";
        if (name == null || name.length() <= 0) return "";

        ResourceBundle bundle = getResourceBundle(resource, locale);

        if (bundle == null) return name;

        String value = null;
        try {
            value = bundle.getString(name);
        } catch (Exception e) {
            //Debug.logInfo(e, module);
        }
        return value == null ? name : value.trim();
    }

    /**
     * 从指定的资源/属性文件返回指定属性名称的值。这个指定的资源/属性文件相当于给定的语言化信息，使用MessageFormat类，用给定的参数代替参数占位符
     * @param resource 资源的类型可以是文件、类或者URL
     * @param name 属性文件中的属性名
     * @param arguments 插入到信息参数占位符中的参数列表
     * @param locale 给定资源文件的语言化信息
     * @return 属性文件的属性值
     */
    public static String getMessage(String resource, String name, Object[] arguments, Locale locale) {
        String value = getMessage(resource, name, locale);

        if (UtilValidate.isEmpty(value)) {
            return "";
        } else {
            if (arguments != null && arguments.length > 0) {
                value = MessageFormat.format(value, arguments);
            }
            return value;
        }
    }

    /**
     * 从指定的资源/属性文件返回指定属性名称的值。这个指定的资源/属性文件相当于给定的语言化信息，使用MessageFormat类，用给定的参数代替参数占位符
     * @param resource 资源的类型可以是文件、类或者ＵＲＬ
     * @param name 属性的名称在属性文件中
     * @param arguments 插入到信息参数占位符中的参数数组
     * @param locale 给定资源文件的语言化信息
     * @return 属性文件的属性值
     */
    public static <E> String getMessage(String resource, String name, List<E> arguments, Locale locale) {
        String value = getMessage(resource, name, locale);

        if (UtilValidate.isEmpty(value)) {
            return "";
        } else {
            if (UtilValidate.isNotEmpty(arguments)) {
                value = MessageFormat.format(value, arguments.toArray());
            }
            return value;
        }
    }

    public static String getMessageList(String resource, String name, Locale locale, Object... arguments) {
        return getMessage(resource, name, arguments, locale);
    }

    /**
     * 从指定的资源/属性文件返回指定属性名称的值。这个指定的资源/属性文件相当于给定的语言语言化信息，使用FlexibleStringExpander类，用给定的参数代替参数占位符
     * @param resource 资源的类型可以是文件、类或者URL
     * @param name 属性的名称在属性文件中
     * @param context 插入到信息参数占位符中的参数键值对，该方法使用了FlexibleStringExpander类的$()语法
     * @param locale 给定资源文件的语言化信息
     * @return 返回属性文件中的属性值
     */
    public static String getMessage(String resource, String name, Map<String, ? extends Object> context, Locale locale) {
        String value = getMessage(resource, name, locale);

        if (UtilValidate.isEmpty(value)) {
            return "";
        } else {
            if (UtilValidate.isNotEmpty(context)) {
                value = FlexibleStringExpander.expandString(value, context, locale);
            }
            return value;
        }
    }

    public static String getMessageMap(String resource, String name, Locale locale, Object... context) {
        return getMessage(resource, name, UtilGenerics.toMap(String.class, context), locale);
    }

    protected static Set<String> resourceNotFoundMessagesShown = FastSet.newInstance();
    
    /**
     * 以ResourceBundle资源的形式返回指定的资源或属性文件。
     * @param resource 资源的名称可以是文件、类或者URL。
     * @param locale 给定资源文件的语言化信息
     * @return ResourceBundle类的对象
     */
    public static ResourceBundle getResourceBundle(String resource, Locale locale) {
        if (UtilValidate.isEmpty(resource)) {
            throw new IllegalArgumentException("resource cannot be null or empty");
        }
        if (locale == null) {
            throw new IllegalArgumentException("locale cannot be null");
        }
        ResourceBundle bundle = null;
        try {
            bundle = UtilResourceBundle.getBundle(resource, locale, (ClassLoader) null);
        } catch (MissingResourceException e) {
            String resourceCacheKey = createResourceName(resource, locale, false);
            if (!resourceNotFoundMessagesShown.contains(resourceCacheKey)) {
                resourceNotFoundMessagesShown.add(resourceCacheKey);
                Debug.logInfo("[UtilProperties.getPropertyValue] could not find resource: " + resource + " for locale " + locale, module);
            }
            throw new IllegalArgumentException("Could not find resource bundle [" + resource + "] in the locale [" + locale + "]");
        }
        return bundle;
    }

    /**
     * 以Map的形式返回指定的资源或属性文件。
     * @param resource 资源的名称可以是文件、类或者URL。
     * @param locale 给定资源文件的语言化信息
     * @return 包含所有的ResourBundle的记录
     */
    public static ResourceBundleMapWrapper getResourceBundleMap(String resource, Locale locale) {
        return new ResourceBundleMapWrapper(getResourceBundle(resource, locale));
    }

    /**
     * 以Map的形式返回指定的资源或属性文件。
     * @param resource 资源的类型。可以是文件、类或URL
     * @param locale 给定资源文件的语言化信息
     * @param context 屏幕内容上下文
     * @return 包含所有的ResourBundle的记录
     */
    public static ResourceBundleMapWrapper getResourceBundleMap(String resource, Locale locale, Map<String, Object> context) {
        return new ResourceBundleMapWrapper(getResourceBundle(resource, locale), context);
    }

    /**
     * 返回执行的资源和属性文件。注意：这个方法会为指定的本地化（语言化）信息返回一个属性实例。
     * 主要被UtilProperties类调用
     * @param resource 资源的类型。可以是文件、类或URL
     * @param locale 需要的语言化信息
     * @return 返回属性实例，当未找到匹配的属性时返回null
     */
    public static Properties getProperties(String resource, Locale locale) {
        if (UtilValidate.isEmpty(resource)) {
            throw new IllegalArgumentException("resource cannot be null or empty");
        }
        if (locale == null) {
            throw new IllegalArgumentException("locale cannot be null");
        }
        Properties properties = null;
        URL url = resolvePropertiesUrl(resource, locale);
        if (url != null) {
            try {
                properties = new ExtendedProperties(url, locale);
            } catch (Exception e) {
                if (UtilValidate.isNotEmpty(e.getMessage())) {
                    Debug.logInfo(e.getMessage(), module);
                } else {
                    Debug.logInfo("Exception thrown: " + e.getClass().getName(), module);
                }
                properties = null;
            }
        }
        if (UtilValidate.isNotEmpty(properties)) {
            if (Debug.verboseOn()) Debug.logVerbose("Loaded " + properties.size() + " properties for: " + resource + " (" + locale + ")", module);
        }
        return properties;
    }

    // ========= 支持扩展属性文件的类和方法 ========== //
    
    /**
     * 返回配置的回滚语言化信息，UtilProperties使用这个信息解析指定的XML属性文件
     * 这个回滚信息可以在general.properties中使用“locale.properties.fallback”
     * @return 配置好的回滚语言化信息
     */
    @Deprecated
    public static Locale getFallbackLocale() {
        if (fallbackLocale == null) {
            synchronized (UtilProperties.class) {
                if (fallbackLocale == null) {
                    String locale = getPropertyValue("general", "locale.properties.fallback");
                    if (UtilValidate.isNotEmpty(locale)) {
                        fallbackLocale = UtilMisc.parseLocale(locale);
                    }
                    if (fallbackLocale == null) {
                        fallbackLocale = UtilMisc.parseLocale("en");
                    }
                }
            }
        }
        return fallbackLocale;
    }

    /**
     * 将语言化的实例转换成候选语言化信息集合，这个集合不仅包含具体的，也包含泛指
     * 比如说：localeToCandidateList(Locale.US)就会返回一个包含en_US和en
     * @param locale 语言化信息实例
     * @return 候选语言化信息的集合
     */
    public static List<Locale> localeToCandidateList(Locale locale) {
        List<Locale> localeList = FastList.newInstance();
        localeList.add(locale);
        String localeString = locale.toString();
        int pos = localeString.lastIndexOf("_", localeString.length());
        while (pos != -1) {
            localeString = localeString.substring(0, pos);
            localeList.add(new Locale(localeString));
            pos = localeString.lastIndexOf("_", localeString.length());
        }
        return localeList;
    }

    /**
     * 返回默认的候选语言化信息集合，这个集合可以添加JVM的默认语言化信息、框架应用的回滚语言化信息和根语言化信息
     * @return 默认的候选语言化信息集合
     */
    public static Set<Locale> getDefaultCandidateLocales() {
        if (defaultCandidateLocales == null) {
            synchronized (UtilProperties.class) {
                if (defaultCandidateLocales == null) {
                    defaultCandidateLocales = FastSet.newInstance();
                    defaultCandidateLocales.addAll(localeToCandidateList(Locale.getDefault()));
                    defaultCandidateLocales.addAll(localeToCandidateList(getFallbackLocale()));
                    defaultCandidateLocales.add(Locale.ROOT);
                }
            }
        }
        return defaultCandidateLocales;
    }

    /**
     * 根据提供的语言化信息返回候选语言化信息集合。返回的路径集合包括提供的和默认候选的语言化信息
     * @param localez 需要的语言化信息
     * @return 候选语言化信息集合
     */
    @Deprecated
    public static List<Locale> getCandidateLocales(Locale locale) {
        if (Locale.ROOT.equals(locale)) {
            return UtilMisc.toList(locale);
        }
        Set<Locale> localeSet = FastSet.newInstance();
        localeSet.addAll(localeToCandidateList(locale));
        localeSet.addAll(getDefaultCandidateLocales());
        List<Locale> localeList = FastList.newInstance();
        localeList.addAll(localeSet);
        return localeList;
    }

    /**
     * 根据资源的名称和语言化信息，创建一个语言化的资源
     * @param resource 需要的资源
     * @param locale 需要的语言化信息
     * @param removeExtension 从资源字符串中删除文件扩展名
     * @return 语言化的资源名
     */
    public static String createResourceName(String resource, Locale locale, boolean removeExtension) {
        String resourceName = resource;
        if (removeExtension) {
            if (resourceName.endsWith(".xml")) {
                resourceName = resourceName.replace(".xml", "");
            } else if (resourceName.endsWith(".properties")) {
                resourceName = resourceName.replace(".properties", "");
            }
        }
        if (locale != null) {
            if (UtilValidate.isNotEmpty(locale.toString())) {
                resourceName = resourceName + "_" + locale;
            }
        }
        return resourceName;
    }

    public static boolean isPropertiesResourceNotFound(String resource, Locale locale, boolean removeExtension) {
        return propertiesNotFound.contains(UtilProperties.createResourceName(resource, locale, removeExtension));
    }
    
    /**
     * 解析属性文件的Url
     * 该方法使用以下的策略：
     * （1）在MyProps.xml资源中精确定位XML文件
     * （2）在MyProp_en.xml资源中精确定位以指定名称开头和以语言化字符串或.XML结束的文件
     * （3）在MyProp_en.xml资源中精确定位以指定名称开头和以语言化字符串或.properties结束的文件
     * （4）在MyProps_en资源中精确定位以指定名称开头和以语言化字符串结束的文件
     * @param resource 需要解析的资源
     * @param locale 需要的语言化信息
     * @return 返回URL实例。如果找不到返回null
     */
    public static URL resolvePropertiesUrl(String resource, Locale locale) {
        if (UtilValidate.isEmpty(resource)) {
            throw new IllegalArgumentException("resource cannot be null or empty");
        }
        String resourceName = createResourceName(resource, locale, false);
        if (propertiesNotFound.contains(resourceName)) {
            return null;
        }
        URL url = null;
        try {
            if (resource.endsWith(".xml") || resource.endsWith(".properties")) {
                url = FlexibleLocation.resolveLocation(resource);
                if (url != null) {
                    return url;
                }
            }
            url = FlexibleLocation.resolveLocation(resourceName + ".properties");
            if (url != null) {
                return url;
            }
            url = FlexibleLocation.resolveLocation(resourceName + ".xml");
            if (url != null) {
                return url;
            }
            url = FlexibleLocation.resolveLocation(resource + ".xml");
            if (url != null) {
                return url;
            }
            url = FlexibleLocation.resolveLocation(resourceName);
            if (url != null) {
                return url;
            }
        } catch (Exception e) {
            Debug.logInfo("Properties resolver: invalid URL - " + e.getMessage(), module);
        }
        if (propertiesNotFound.size() <= 300) {
            propertiesNotFound.add(resourceName);
        }
        return null;
    }

    /**
     * 将XML属性文件转换成Properties实例，这种方法不仅可以转换Java的XML属性文件，还可以转换框架应用的通用XML文件
     * @param in XML文件输入流
     * @param locale 需要的语言化信息
     * @param properties 添加的可选择属性
     * @return 返回属性实例。如果找不到返回null
     * @throws IOException
     * @throws InvalidPropertiesFormatException
     */
    public static Properties xmlToProperties(InputStream in, Locale locale, Properties properties) throws IOException, InvalidPropertiesFormatException {
        if (in == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }
        Document doc = null;
        try {
            doc = UtilXml.readXmlDocument(in, false, "XML Properties file");
            in.close();
        } catch (Exception e) {
            Debug.logWarning(e, "XML Locale file for locale " + locale + " could not be loaded.", module);
            in.close();
            return null;
        }
        Element resourceElement = doc.getDocumentElement();
        List<? extends Element> propertyList = UtilXml.childElementList(resourceElement, "property");
        if (UtilValidate.isNotEmpty(propertyList)) {
            if (locale == null) {
                throw new IllegalArgumentException("locale cannot be null");
            }
            String localeString = locale.toString();
            String correctedLocaleString = localeString.replace('_','-');
            for (Element property : propertyList) {
                Element value = UtilXml.firstChildElement(property, "value", "xml:lang", correctedLocaleString);
                if( value == null ) {
                    value = UtilXml.firstChildElement(property, "value", "xml:lang", localeString);
                }
                if (value != null) {
                    if (properties == null) {
                        properties = new Properties();
                    }
                    String valueString = UtilXml.elementValue(value);
                    if (valueString != null) {
                        properties.put(property.getAttribute("key"), valueString);
                    }
                }
            }
            return properties;
        }
        propertyList = UtilXml.childElementList(resourceElement, "entry");
        if (UtilValidate.isEmpty(propertyList)) {
            throw new InvalidPropertiesFormatException("XML properties file invalid or empty");
        }
        for (Element property : propertyList) {
            String value = UtilXml.elementValue(property);
            if (value != null) {
                if (properties == null) {
                    properties = new Properties();
                }
                properties.put(property.getAttribute("key"), value);
            }
        }
        return properties;
    }

    /**
     * 通用资源包类，继承ResourceBundle类，为框架应用的通用XML属性文件添加通用包缓存编码和支持
     */
    public static class UtilResourceBundle extends ResourceBundle {
        protected static UtilCache<String, UtilResourceBundle> bundleCache = UtilCache.createUtilCache("properties.UtilPropertiesBundleCache");
        protected Properties properties = null;
        protected Locale locale = null;
        protected int hashCode = hashCode();

        protected UtilResourceBundle() {}

        public UtilResourceBundle(Properties properties, Locale locale, UtilResourceBundle parent) {
            this.properties = properties;
            this.locale = locale;
            setParent(parent);
            String hashString = properties.toString();
            if (parent != null) {
                hashString += parent.properties;
            }
            this.hashCode = hashString.hashCode();
        }

        public static ResourceBundle getBundle(String resource, Locale locale, ClassLoader loader) throws MissingResourceException {
            String resourceName = createResourceName(resource, locale, true);
            UtilResourceBundle bundle = bundleCache.get(resourceName);
            if (bundle == null) {
                synchronized (bundleCache) {
                    double startTime = System.currentTimeMillis();
                    FastList<Locale> candidateLocales = (FastList<Locale>) getCandidateLocales(locale);
                    UtilResourceBundle parentBundle = null;
                    int numProperties = 0;
                    while (candidateLocales.size() > 0) {
                        Locale candidateLocale = candidateLocales.removeLast();
                        // ResourceBundles are connected together as a singly-linked list
                        String lookupName = createResourceName(resource, candidateLocale, true);
                        UtilResourceBundle lookupBundle = bundleCache.get(lookupName);
                        if (lookupBundle == null) {
                            Properties newProps = getProperties(resource, candidateLocale);
                            if (UtilValidate.isNotEmpty(newProps)) {
                                // The last bundle we found becomes the parent of the new bundle
                                parentBundle = bundle;
                                bundle = new UtilResourceBundle(newProps, candidateLocale, parentBundle);
                                bundleCache.put(lookupName, bundle);
                                numProperties = newProps.size();
                            }
                        } else {
                            parentBundle = bundle;
                            bundle = lookupBundle;
                        }
                    }
                    if (bundle == null) {
                        throw new MissingResourceException("Resource " + resource + ", locale " + locale + " not found", null, null);
                    } else if (!bundle.getLocale().equals(locale)) {
                        // Create a "dummy" bundle for the requested locale
                        bundle = new UtilResourceBundle(bundle.properties, locale, parentBundle);
                    }
                    double totalTime = System.currentTimeMillis() - startTime;
                    if (Debug.infoOn()) {
                        Debug.logInfo("ResourceBundle " + resource + " (" + locale + ") created in " + totalTime/1000.0 + "s with " + numProperties + " properties", module);
                    }
                    bundleCache.put(resourceName, bundle);
                }
            }
            return bundle;
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == null ? false : obj.hashCode() == this.hashCode;
        }

        @Override
        public Locale getLocale() {
            return this.locale;
        }

        @Override
        protected Object handleGetObject(String key) {
            return properties.get(key);
        }

        @Override
        public Enumeration<String> getKeys() {
            return new Enumeration<String>() {
                Iterator<String> i = UtilGenerics.cast(properties.keySet().iterator());
                public boolean hasMoreElements() {
                    return (i.hasNext());
                }
                public String nextElement() {
                    return i.next();
                }
            };
        }

    }

    /**
     * 通用属性类，继承Properties类，为框架应用的通用XML文件扩展支持
     */
    public static class ExtendedProperties extends Properties {
        public ExtendedProperties() {
            super();
        }
        public ExtendedProperties(Properties defaults) {
            super(defaults);
        }
        public ExtendedProperties(URL url, Locale locale) throws IOException, InvalidPropertiesFormatException {
            InputStream in = new BufferedInputStream(url.openStream());
            if (url.getFile().endsWith(".xml")) {
                xmlToProperties(in, locale, this);
            } else {
                load(in);
            }
            in.close();
        }
        @Override
        public void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException {
            xmlToProperties(in, null, this);
            in.close();
        }
    }
}
