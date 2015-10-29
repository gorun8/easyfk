package cn.gorun8.easyfk.base.util;

/**
 * 
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Xia Tian
 *
 * @date 2015年4月28日
 *
 */
public class DateUtil {
	
	public static String FULL_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String FULL_DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 将日期转化为字符串格式
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String convertDateToString(Date date,String pattern){
		if(date == null || pattern == null){
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String result = sdf.format(date);
		return result;
		
	}
	

}
