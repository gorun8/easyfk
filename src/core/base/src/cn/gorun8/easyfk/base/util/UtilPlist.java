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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static cn.gorun8.easyfk.base.util.UtilGenerics.checkList;
import static cn.gorun8.easyfk.base.util.UtilGenerics.checkMap;

/**
 * File Utilities
 *
 */
public class UtilPlist {

    public static final String module = UtilPlist.class.getName();

    /** simple 4 char indentation */
    public static final String indentFourString = "    ";

    public static void writePlistProperty(String name, Object value, int indentLevel, PrintWriter writer) {
        for (int i = 0; i < indentLevel; i++) writer.print(indentFourString);
        writer.print(name);
        writer.print(" = ");
        if (value instanceof Map<?, ?>) {
            writer.println();
            Map<String, Object> map = checkMap(value);
            writePlistPropertyMap(map, indentLevel, writer, false);
        } else if (value instanceof List<?>) {
            List<Object> list = checkList(value);
            writePlistPropertyValueList(list, indentLevel, writer);
        } else {
            writer.print(value);
            writer.println(";");
        }
    }
    public static void writePlistPropertyMap(Map<String, Object> propertyMap, int indentLevel, PrintWriter writer, boolean appendComma) {
        for (int i = 0; i < indentLevel; i++) writer.print(indentFourString);
        writer.println("{");
        for (Map.Entry<String, Object> property: propertyMap.entrySet()) {
            writePlistProperty(property.getKey(), property.getValue(), indentLevel + 1, writer);
        }
        for (int i = 0; i < indentLevel; i++) writer.print(indentFourString);
        if (appendComma) {
            writer.println("},");
        } else {
            writer.println("}");
        }
    }
    public static void writePlistPropertyValueList(List<Object> propertyValueList, int indentLevel, PrintWriter writer) {
        for (int i = 0; i < indentLevel; i++) writer.print(indentFourString);
        writer.print("(");

        Iterator<Object> propertyValueIter = propertyValueList.iterator();
        while (propertyValueIter.hasNext()) {
            Object propertyValue = propertyValueIter.next();
            if (propertyValue instanceof Map<?, ?>) {
                Map<String, Object> propertyMap = checkMap(propertyValue);
                writePlistPropertyMap(propertyMap, indentLevel + 1, writer, propertyValueIter.hasNext());
            } else {
                writer.print(propertyValue);
                if (propertyValueIter.hasNext()) writer.print(", ");
            }
        }

        for (int i = 0; i < indentLevel; i++) writer.print(indentFourString);
        writer.println(");");
    }

    public static void writePlistPropertyXml(String name, Object value, int indentLevel, PrintWriter writer) {
        for (int i = 0; i < indentLevel; i++) writer.print(indentFourString);
        writer.print("<key>");
        writer.print(name);
        writer.println("</key>");
        if (value instanceof Map<?, ?>) {
            Map<String, Object> map = checkMap(value);
            writePlistPropertyMapXml(map, indentLevel, writer);
        } else if (value instanceof List<?>) {
            List<Object> list = checkList(value);
            writePlistPropertyValueListXml(list, indentLevel, writer);
        } else {
            for (int i = 0; i < indentLevel; i++) writer.print(indentFourString);
            writer.print("<string>");
            writer.print(value);
            writer.println("</string>");
        }
    }
    public static void writePlistPropertyMapXml(Map<String, Object> propertyMap, int indentLevel, PrintWriter writer) {
        for (int i = 0; i < indentLevel; i++) writer.print(indentFourString);
        writer.println("<dict>");
        for (Map.Entry<String, Object> property: propertyMap.entrySet()) {
            writePlistPropertyXml(property.getKey(), property.getValue(), indentLevel + 1, writer);
        }
        for (int i = 0; i < indentLevel; i++) writer.print(indentFourString);
        writer.println("</dict>");
    }
    public static void writePlistPropertyValueListXml(List<Object> propertyValueList, int indentLevel, PrintWriter writer) {
        for (int i = 0; i < indentLevel; i++) writer.print(indentFourString);
        writer.println("<array>");

        indentLevel++;
        Iterator<Object> propertyValueIter = propertyValueList.iterator();
        while (propertyValueIter.hasNext()) {
            Object propertyValue = propertyValueIter.next();
            if (propertyValue instanceof Map<?, ?>) {
                Map<String, Object> propertyMap = checkMap(propertyValue);
                writePlistPropertyMapXml(propertyMap, indentLevel, writer);
            } else {
                for (int i = 0; i < indentLevel; i++) writer.print(indentFourString);
                writer.print("<string>");
                writer.print(propertyValue);
                writer.println("</string>");
            }
        }
        indentLevel--;

        for (int i = 0; i < indentLevel; i++) writer.print(indentFourString);
        writer.println("</array>");
    }

    /**
     * Writes model information in the Apple EOModelBundle format.
     *
     * For document structure and definition see: http://developer.apple.com/documentation/InternetWeb/Reference/WO_BundleReference/Articles/EOModelBundle.html
     *
     * @param eoModelMap
     * @param eomodeldFullPath
     * @param filename
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void writePlistFile(Map<String, Object> eoModelMap, String eomodeldFullPath, String filename, boolean useXml) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter plistWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(eomodeldFullPath, filename)), "UTF-8")));
        if (useXml) {
            plistWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            plistWriter.println("<!DOCTYPE plist PUBLIC \"-//Apple Computer//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">");
            plistWriter.println("<plist version=\"1.0\">");
            writePlistPropertyMapXml(eoModelMap, 0, plistWriter);
            plistWriter.println("</plist>");
        } else {
            writePlistPropertyMap(eoModelMap, 0, plistWriter, false);
        }
        plistWriter.close();
    }
}
