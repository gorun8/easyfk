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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;



 

public class UtilZip {

	/**
	 * 压缩文件或目录
	 * @param srcFile 要压缩的文件
	 * @param zipFile 输出的文件
	 * @return
	 */
	public static boolean zip(String srcFile,String zipFile)
	{
		try {
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			zip(zos, new File(srcFile), "");
			zos.close();
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 解压文件
	 * @param zipFile 要解压的文件
	 * @param dir 解压后的目录
	 * @return
	 */
	public static boolean unzip(String zipFile,String dir)
	{

		try {
			File ff = new File(dir);
			ZipInputStream zis;
			zis = new ZipInputStream(new FileInputStream(zipFile));
			unzip(zis, ff);
			zis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private static void unzip(ZipInputStream zis, File workdir) throws Exception {
		 ZipEntry ze = zis.getNextEntry();
		 byte[] buffer = new byte[1024 * 100];
		 
		 File file = null;
		 while( ze != null) {
			 file = new File(workdir, ze.getName()); 
			 if(!file.getParentFile().exists()){
				 file.getParentFile().mkdir();
			 }
			 FileOutputStream fos = new FileOutputStream(file);
			int len = 0;
			while ((len = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			ze = zis.getNextEntry();
		 }
	}
 

	private static void zip(ZipOutputStream zos, File file, String base) throws Exception {
		if (file.isDirectory()) {
			zipDir(zos, file, base);
		} else {
			zipFile(zos, file, base);
		}
	}

	private static void zipDir(ZipOutputStream zos, File file, String base) throws Exception {
		File[] files = file.listFiles();
		String newBase = base;
		for (File subFile : files) {
			if(base.length() > 0) {
				newBase =  base + "/" + subFile.getName();
			}
			else {
				newBase = subFile.getName();
			}
			zip(zos, subFile, newBase);
		}
	}

	private static void zipFile(ZipOutputStream zos, File file, String base) throws Exception {
		byte[] buffer = new byte[1024 * 100];
		
		zos.putNextEntry(new ZipEntry(base));
		FileInputStream fis = new FileInputStream(file);
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			zos.write(buffer, 0, len);
		}
		fis.close();
	}
	
	

}
