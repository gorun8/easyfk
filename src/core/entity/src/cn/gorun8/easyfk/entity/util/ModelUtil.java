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
package cn.gorun8.easyfk.entity.util;

import cn.gorun8.easyfk.base.util.StringUtil;

import java.io.File;

/**
 * Generic Entity - General Utilities
 *
 */
public class ModelUtil {

    public static final String module = ModelUtil.class.getName();

    /**
     * Changes the first letter of the passed String to upper case.
     * @param string The passed String
     * @return A String with an upper case first letter
     */
    public static String upperFirstChar(String string) {
        if (string == null) return null;
        if (string.length() <= 1) return string.toLowerCase();
        StringBuilder sb = new StringBuilder(string);

        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    /**
     * Changes the first letter of the passed String to lower case.
     *
     * @param string The passed String
     * @return A String with a lower case first letter
     */
    public static String lowerFirstChar(String string) {
        if (string == null) return null;
        if (string.length() <= 1) return string.toLowerCase();
        StringBuilder sb = new StringBuilder(string);

        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return sb.toString();
    }

    /** Converts a database name to a Java class name.
     * The naming conventions used to allow for this are as follows: a database name (table or
     * column) is in all capital letters, and the words are separated by an underscore
     * (for example: NEAT_ENTITY_NAME or RANDOM_FIELD_NAME); a Java name (ejb or field) is in all
     * lower case letters, except the letter at the beginning of each word (for example:
     * NeatEntityName or RandomFieldName). The convention of using a capital letter at
     * the beginning of a class name in Java, or a lower-case letter for the beginning of a
     * variable name in Java is also used along with the Java name convention above.
     * @param columnName The database name
     * @return The Java class name
     */
    public static String dbNameToClassName(String columnName) {
        return upperFirstChar(dbNameToVarName(columnName));
    }

    /** Converts a database name to a Java variable name.
     * The naming conventions used to allow for this are as follows: a database name (table or
     * column) is in all capital letters, and the words are separated by an underscore
     * (for example: NEAT_ENTITY_NAME or RANDOM_FIELD_NAME); a Java name (ejb or field) is in all
     * lower case letters, except the letter at the beginning of each word (for example:
     * NeatEntityName or RandomFieldName). The convention of using a capital letter at
     * the beginning of a class name in Java, or a lower-case letter for the beginning of a
     * variable name in Java is also used along with the Java name convention above.
     * @param columnName The database name
     * @return The Java variable name
     */
    public static String dbNameToVarName(String columnName) {
        if (columnName == null) return null;

        StringBuilder fieldName = new StringBuilder(columnName.length());

        boolean toUpper = false;
        for (int i=0; i < columnName.length(); i++) {
            char ch = columnName.charAt(i);
            if (ch == '_') {
                toUpper = true;
            } else if (toUpper) {
                fieldName.append(Character.toUpperCase(ch));
                toUpper = false;
            } else {
                fieldName.append(Character.toLowerCase(ch));
            }
        }

        return fieldName.toString();
    }

    /**
     * Converts a Java variable name to a database name.
     * The naming conventions used to allow for this are as follows: a database name (table or
     * column) is in all capital letters, and the words are separated by an underscore
     * (for example: NEAT_ENTITY_NAME or RANDOM_FIELD_NAME); a Java name (ejb or field) is in all
     * lower case letters, except the letter at the beginning of each word (for example:
     * NeatEntityName or RandomFieldName). The convention of using a capital letter at
     * the beginning of a class name in Java, or a lower-case letter for the beginning of a
     * variable name in Java is also used along with the Java name convention above.
     * @param javaName The Java variable name
     * @return The database name
     */
    public static String javaNameToDbName(String javaName) {
        if (javaName == null) return null;
        if (javaName.length() <= 0) return "";
        StringBuilder dbName = new StringBuilder();

        dbName.append(Character.toUpperCase(javaName.charAt(0)));
        int namePos = 1;

        while (namePos < javaName.length()) {
            char curChar = javaName.charAt(namePos);

            if (Character.isUpperCase(curChar)) dbName.append('_');
            dbName.append(Character.toUpperCase(curChar));
            namePos++;
        }

        return dbName.toString();
    }

    public static String vowelBag = "aeiouyAEIOUY";
    /**  Start by removing all vowels, then pull 1 letter at a time off the end of each _ separated segment, go until it is less than or equal to the desired length
     *
     * @param dbName
     * @param desiredLength
     * @return shortened String
     */
    public static String shortenDbName(String dbName, int desiredLength) {
        StringBuilder dbBuf = new StringBuilder(dbName);
        if (dbBuf.length() > desiredLength) {
            // remove one vowel at a time, starting at beginning
            for (int i = dbBuf.length() - 1; i > 0; i--) {
                // don't remove vowels that are at the beginning of the string (taken care of by the i > 0) or right after an underscore
                if (dbBuf.charAt(i - 1) == '_') {
                    continue;
                }

                char curChar = dbBuf.charAt(i);
                if (vowelBag.indexOf(curChar) > 0) {
                    dbBuf.deleteCharAt(i);
                }
            }
        }

        // remove all double underscores
        while (dbBuf.indexOf("__") > 0) {
            dbBuf.deleteCharAt(dbBuf.indexOf("__"));
        }

        while (dbBuf.length() > desiredLength) {
            boolean removedChars = false;

            int usIndex = dbBuf.lastIndexOf("_");
            while (usIndex > 0 && dbBuf.length() > desiredLength) {
                // if this is the first word in the group, don't pull letters off unless it is 4 letters or more
                int prevUsIndex = dbBuf.lastIndexOf("_", usIndex - 1);
                if (prevUsIndex < 0 && usIndex < 4) {
                    break;
                }

                // don't remove characters to reduce the size two less than three characters between underscores
                if (prevUsIndex >= 0 && (usIndex - prevUsIndex) <= 4) {
                    usIndex = prevUsIndex;
                    continue;
                }

                // delete the second to last character instead of the last, better chance of being unique
                dbBuf.deleteCharAt(usIndex - 2);
                removedChars = true;
                if (usIndex > 2) {
                    usIndex = dbBuf.lastIndexOf("_", usIndex - 2);
                } else {
                    break;
                }
            }

            // now delete the char at the end of the string if necessary
            if (dbBuf.length() > desiredLength) {
                int removeIndex = dbBuf.length() - 1;
                int prevRemoveIndex = dbBuf.lastIndexOf("_", removeIndex - 1);
                // don't remove characters to reduce the size two less than two characters between underscores
                if (prevRemoveIndex < 0 || (removeIndex - prevRemoveIndex) >= 3) {
                    // delete the second to last character instead of the last, better chance of being unique
                    dbBuf.deleteCharAt(removeIndex - 1);
                    removedChars = true;
                }
            }

            // remove all double underscores
            while (dbBuf.indexOf("__") > 0) {
                dbBuf.deleteCharAt(dbBuf.indexOf("__"));
                removedChars = true;
            }

            // if we didn't remove anything break out to avoid an infinite loop
            if (!removedChars) {
                break;
            }
        }

        // remove all double underscores
        while (dbBuf.indexOf("__") > 0) {
            dbBuf.deleteCharAt(dbBuf.indexOf("__"));
        }

        while (dbBuf.length() > desiredLength) {
            // still not short enough, get more aggressive
            // don't remove the first segment, just remove the second over and over until we are short enough
            int firstUs = dbBuf.indexOf("_");
            if (firstUs > 0) {
                int nextUs = dbBuf.indexOf("_", firstUs + 1);
                if (nextUs > 0) {
                    //Debug.logInfo("couldn't shorten enough normally, removing second segment from " + dbBuf, module);
                    dbBuf.delete(firstUs, nextUs);
                }
            }
        }

        //Debug.logInfo("Shortened " + dbName + " to " + dbBuf.toString(), module);
        return dbBuf.toString();
    }

    /**
     * Converts a package name to a path by replacing all '.' characters with the File.separatorChar character.
     *  Is therefore platform independent.
     * @param packageName The package name.
     * @return The path name corresponding to the specified package name.
     */
    public static String packageToPath(String packageName) {
        // just replace all of the '.' characters with the folder separater character
        return packageName.replace('.', File.separatorChar);
    }

    /**
     * Replaces all occurances of oldString in mainString with newString
     * @param mainString The original string
     * @param oldString The string to replace
     * @param newString The string to insert in place of the old
     * @return mainString with all occurances of oldString replaced by newString
     */
    public static String replaceString(String mainString, String oldString, String newString) {
        return StringUtil.replaceString(mainString, oldString, newString);
    }

}
