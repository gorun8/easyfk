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

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

import javolution.util.FastList;
import javolution.util.FastSet;

import cn.gorun8.easyfk.base.location.ComponentLocationResolver;

import org.apache.commons.io.FileUtils;

/**
 * 文件配置模块
 * 主要是实现文件的创建、文件内容的转换和扩展、文件查找、文件过滤与文件路径转换等功能。
 */
public class FileUtil {

    public static final String module = FileUtil.class.getName();

    public static File getFile(String path) {
        return getFile(null, path);
    }
/**
 * 在指定文件的目录下创建文件
 * @param root：根目录
 * @param path：用户可识别的文件路径
 * @return：返回新建的文件
 */
    public static File getFile(File root, String path) {
        if (path.startsWith("component://")) {
            try {
                path = ComponentLocationResolver.getBaseLocation(path).toString();
            } catch (MalformedURLException e) {
                Debug.logError(e, module);
                return null;
            }
        }
        return new File(root, localizePath(path));
    }

    /**
     * 将文件路径转换成用户操作系统可以识别的形式
     * @param path：待转换的文件路径
     * @return：转换之后的文件路径
     */
    public static String localizePath(String path) {
        String fileNameSeparator = ("\\".equals(File.separator) ? "\\" + File.separator : File.separator);
        return path.replaceAll("/+|\\\\+", fileNameSeparator);
    }

    public static void writeString(String fileName, String s) throws IOException {
        writeString(null, fileName, s);
    }

    public static void writeString(String path, String name, String s) throws IOException {
        Writer out = getBufferedWriter(path, name);

        try {
            out.write(s + System.getProperty("line.separator"));
        } catch (IOException e) {
            Debug.logError(e, module);
            throw e;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    Debug.logError(e, module);
                }
            }
        }
    }

    /**
     * 使用指定的编码方式将字符串写到文件中
     *
     * @param path：文件路径
     * @param name：文件名
     * @param encoding：编解码方式
     * @param s：待写入的字符串
     */
    public static void writeString(String path, String name, String encoding, String s) throws IOException {
        String fileName = getPatchedFileName(path, name);
        if (UtilValidate.isEmpty(fileName)) {
            throw new IOException("Cannot obtain buffered writer for an empty filename!");
        }

        try {
            FileUtils.writeStringToFile(new File(fileName), s, encoding);
        } catch (IOException e) {
            Debug.logError(e, module);
            throw e;
        }
    }

    public static void writeString(String encoding, String s, File outFile) throws IOException {
        try {
            FileUtils.writeStringToFile(outFile, s, encoding);
        } catch (IOException e) {
            Debug.logError(e, module);
            throw e;
        }
    }
    /**
     * 将文件内容转化成字符输出流
     * @param path：文件路径
     * @param name：文件名
     * @return：返回转化后的字符输出流
     */
    public static Writer getBufferedWriter(String path, String name) throws IOException {
        String fileName = getPatchedFileName(path, name);
        if (UtilValidate.isEmpty(fileName)) {
            throw new IOException("Cannot obtain buffered writer for an empty filename!");
        }

        return new BufferedWriter(new FileWriter(fileName));
    }
    /**
     * 将文件内容转化成字节输出流
     * @param path：文件路径
     * @param name：文件名
     * @return：返回转化后的字节输出流
     */
    public static OutputStream getBufferedOutputStream(String path, String name) throws IOException {
        String fileName = getPatchedFileName(path, name);
        if (UtilValidate.isEmpty(fileName)) {
            throw new IOException("Cannot obtain buffered writer for an empty filename!");
        }

        return new BufferedOutputStream(new FileOutputStream(fileName));
    }
    /**
     * 获得特定形式的文件名
     * @param path：文件路径
     * @param fileName：文件名
     * @return：返回修改后的文件名
     */
    public static String getPatchedFileName(String path, String fileName) throws IOException {
        // make sure the export directory exists
        if (UtilValidate.isNotEmpty(path)) {
            path = path.replaceAll("\\\\", "/");
            File parentDir = new File(path);
            if (!parentDir.exists()) {
                if (!parentDir.mkdir()) {
                    throw new IOException("Cannot create directory for path: " + path);
                }
            }

            // build the filename with the path
            if (!path.endsWith("/")) {
                path = path + "/";
            }
            if (fileName.startsWith("/")) {
                fileName = fileName.substring(1);
            }
            fileName = path + fileName;
        }

        return fileName;
    }
    /**
     * 将指定的系统属性添加到文件中
     * @param file：待添加内容的文件
     * @param newline：是否需要扩展系统信息的判断值
     * @return：返回包含文件内容和系统属性的字符输入流
     */
    public static StringBuffer readTextFile(File file, boolean newline) throws FileNotFoundException, IOException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        StringBuffer buf = new StringBuffer();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));

            String str;
            while ((str = in.readLine()) != null) {
                buf.append(str);
                if (newline) {
                    buf.append(System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            Debug.logError(e, module);
            throw e;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Debug.logError(e, module);
                }
            }
        }

        return buf;
    }
    /**
     * 将指定的系统属性添加到文件中
     * @param fileName:文件名或文件路径
     * @param newline:判断是否需要添加系统属性到文件中
     * @return：返回返回包含文件内容和系统属性的字符输入流
     */
    public static StringBuffer readTextFile(String fileName, boolean newline) throws FileNotFoundException, IOException {
        File file = new File(fileName);
        return readTextFile(file, newline);
    }

    public static String readString(String encoding, File inFile) throws IOException {
        String readString = "";
        try {
            readString = FileUtils.readFileToString(inFile, encoding);
        } catch (IOException e) {
            Debug.logError(e, module);
            throw e;
        }
        return readString;
    }
    /**
     * 在指定文件目录下查找符合规格的文件
     * @param fileList：用来存放文件的集合
     * @param path：指定路径下新建的文件
     * @param filter：文件过滤设置
     * @param includeSubfolders：是否包含子文件夹
     */
    public static void searchFiles(List<File> fileList, File path, FilenameFilter filter, boolean includeSubfolders) throws IOException {
        // Get filtered files in the current path
        File[] files = path.listFiles(filter);
        if (files == null) {
            return;
        }

        // Process each filtered entry
        for (int i = 0; i < files.length; i++) {
            // recurse if the entry is a directory
            if (files[i].isDirectory() && includeSubfolders && !files[i].getName().startsWith(".")) {
                searchFiles(fileList, files[i], filter, true);
            } else {
                // add the filtered file to the list
                fileList.add(files[i]);
            }
        }
    }
    /**
     * 查找指定目录下的文件
     * @param fileExt：文件的扩展名
     * @param basePath：添加到stringsToFindInPath中的路径名称
     * @param partialPath：添加到stringsToFindInFile中的目录名
     * @param stringToFind：添加到stringsToFindInFile的文件名
     * @return：返回fileExt格式的文件集合
     */
    public static List<File> findFiles(String fileExt, String basePath, String partialPath, String stringToFind) throws IOException {
        if (basePath == null) {
            basePath = System.getProperty("easyfk.home");
        }

        Set<String> stringsToFindInPath = FastSet.newInstance();
        Set<String> stringsToFindInFile = FastSet.newInstance();

        if (partialPath != null) {
           stringsToFindInPath.add(partialPath);
        }
        if (stringToFind != null) {
           stringsToFindInFile.add(stringToFind);
        }

        List<File> fileList = FastList.newInstance();
        FileUtil.searchFiles(fileList, new File(basePath), new SearchTextFilesFilter(fileExt, stringsToFindInPath, stringsToFindInFile), true);

        return fileList;
    }
    /**
     * 查找指定目录下的xml文件
     * @param basePath：EasyFK指定的路径
     * @param partialPath：添加到stringsToFindInPath中的路径名称
     * @param rootElementName：添加到stringsToFindInFile中的目录名
     * @param xsdOrDtdName：添加到stringsToFindInFile的文件名
     * @return：返回XML文件的集合
     */
    public static List<File> findXmlFiles(String basePath, String partialPath, String rootElementName, String xsdOrDtdName) throws IOException {
        if (basePath == null) {
            basePath = System.getProperty("easyfk.home");
        }

        Set<String> stringsToFindInPath = FastSet.newInstance();
        Set<String> stringsToFindInFile = FastSet.newInstance();

        if (partialPath != null) stringsToFindInPath.add(partialPath);
        if (rootElementName != null) stringsToFindInFile.add("<" + rootElementName + " ");
        if (xsdOrDtdName != null) stringsToFindInFile.add(xsdOrDtdName);

        List<File> fileList = FastList.newInstance();
        FileUtil.searchFiles(fileList, new File(basePath), new SearchTextFilesFilter("xml", stringsToFindInPath, stringsToFindInFile), true);
        return fileList;
    }
    /**
     *过滤不符合规格的文件名的实现类，该类继承了FileNameFilter接口，并实现了接口功能和接口的accept（）方法
     */
    public static class SearchTextFilesFilter implements FilenameFilter {
        String fileExtension;
        Set<String> stringsToFindInFile = FastSet.newInstance();
        Set<String> stringsToFindInPath = FastSet.newInstance();
        /**
         * 判断stringsToFindInFile与stringsToFindInPath是否为空
         * @param fileExtension：文件扩展名
         * @param stringsToFindInPath：文件路径集合
         * @param stringsToFindInFile：文件集合
         */
        public SearchTextFilesFilter(String fileExtension, Set<String> stringsToFindInPath, Set<String> stringsToFindInFile) {
            this.fileExtension = fileExtension;
            if (stringsToFindInPath != null) {
                this.stringsToFindInPath.addAll(stringsToFindInPath);
            }
            if (stringsToFindInFile != null) {
                this.stringsToFindInFile.addAll(stringsToFindInFile);
            }
        }
        /**
         * 过滤不符合规格的文件名
         * @param dir：文件路径
         * @param name：文件名
         * @return：符合规格返回真，否则返回假
         */
        public boolean accept(File dir, String name) {
            File file = new File(dir, name);
            if (file.getName().startsWith(".")) {
                return false;
            }
            if (file.isDirectory()) {
                return true;
            }

            boolean hasAllPathStrings = true;
            String fullPath = dir.getPath().replace('\\', '/');
            for (String pathString: stringsToFindInPath) {
                if (fullPath.indexOf(pathString) < 0) {
                    hasAllPathStrings = false;
                    break;
                }
            }

            if (hasAllPathStrings && name.endsWith("." + fileExtension)) {
                if (stringsToFindInFile.size() == 0) {
                    return true;
                }
                StringBuffer xmlFileBuffer = null;
                try {
                    xmlFileBuffer = FileUtil.readTextFile(file, true);
                } catch (FileNotFoundException e) {
                    Debug.logWarning("Error reading xml file [" + file + "] for file search: " + e.toString(), module);
                    return false;
                } catch (IOException e) {
                    Debug.logWarning("Error reading xml file [" + file + "] for file search: " + e.toString(), module);
                    return false;
                }
                if (UtilValidate.isNotEmpty(xmlFileBuffer)) {
                    boolean hasAllStrings = true;
                    for (String stringToFile: stringsToFindInFile) {
                        if (xmlFileBuffer.indexOf(stringToFile) < 0) {
                            hasAllStrings = false;
                            break;
                        }
                    }
                    return hasAllStrings;
                }
            } else {
                return false;
            }
            return false;
        }
    }
    /**
     * 在给定的字符流文件中查找指定的字段
     * @param reader：用来查找字段的字符流文件
     * @param searchString：待查找的字段
     * @return：如果文件中存在待查找字段，返回真；否则返回假
     * @throws IOException：异常发生时抛出的输入输出异常
     */
   public static boolean containsString(Reader reader, final String searchString) throws IOException {
       char[] buffer = new char[1024];
       int numCharsRead;
       int count = 0;
       while((numCharsRead = reader.read(buffer)) > 0) {
           for (int c = 0; c < numCharsRead; ++c) {
               if (buffer[c] == searchString.charAt(count)) count++;
               else count = 0;
               if (count == searchString.length()) return true;
           }
       }
       return false;
   }
   /**
    * 在给定文件名的文件中查找指定的字段，如果文件不存在，返回FALSE
    * @param fileName：待查找字段的文件的访问详细路径
    * @param searchString：待查找的字段
    * @return：如果文件中存在待查找字段，返回真；否则返回假
    * @throws IOException：异常发生时抛出的输入输出异常
    */
   
   public static boolean containsString(final String fileName, final String searchString) throws IOException {
       File inFile = new File(fileName);
       if (inFile.exists()) {
           BufferedReader in = new BufferedReader(new FileReader(inFile));
           try {
               return containsString(in, searchString);
           } finally {
               if (in != null)in.close();
           }
       } else {
           return false;
       }
   }
   /**
    * 删除目录或文件
    * @param file：待删除的文件
    */
   public static void deleteFile(String file)
   {
	   deleteFile(new File(file));
   }
   /**
    * 删除目录或文件
    * @param file：待删除的文件或者文件目录
    */
   public static void deleteFile(File file){
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File subFile : files) {
				deleteFile(subFile);
			}
		}
		file.delete();
  }
  
   /**
    * 从目录下删除指定文件
    * @param dir:待删除文件的存储路径
    * @param filename：待删除文件的文件名
    */
   public static void deleteFile(String dir,String filename)
   {
	   File fp = new File(dir);
	   if(!fp.exists())
	   {
		   return ;
	   }	   
	   
	   File [] fplist = fp.listFiles();
	   for(File item: fplist)
		{
			if(item.getName().equals(filename))
			{
				item.delete();
				break;
			}
			
		}
   }
}
