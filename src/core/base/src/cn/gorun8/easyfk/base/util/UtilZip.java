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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/*******************************************************************************
 * Project:easy web framework
 *
 * Description: This project base on so many open source project and thanks to all.
 *  
 *  
 * The Goal of this project is to architect a framework for developing web application easily.  
 * 
 *
 * author:hezhiping   ,Email:hzpldx@163.com
 *
 * 
 * 
 * ================================================================================================================
 * 
 * 
 * 
 *******************************************************************************/


 

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
