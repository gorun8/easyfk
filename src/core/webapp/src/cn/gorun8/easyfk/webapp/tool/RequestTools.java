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

package cn.gorun8.easyfk.webapp.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;

import cn.gorun8.easyfk.base.util.Generic;

public class RequestTools {
	
	/**
	 * 获取请求完整路径，包括参数
	 * @param request
	 * @return
	 */
	public static String getRequestCompleteUrl(HttpServletRequest request){
		//获取访问的完整url
		StringBuilder sb  = new StringBuilder();
		String scheme = request.getScheme();
		String serverName = request.getServerName();
		int port = request.getServerPort();
		String uri = request.getRequestURI();
		sb.append(scheme).append("://").append(serverName).append(":").append(port).append(uri);
		
		Map<String,String[]> params = request.getParameterMap();
		Set<String> names = params.keySet();
		if(CollectionUtils.isNotEmpty(names)){
			List<String> namelist = new ArrayList<String>();
			for(String name:names){
				namelist.add(name);
			}
			for(int i = 0 ; i<namelist.size();i++){
				String paramvalue=request.getParameter(namelist.get(i));
				if(i==0){
					sb.append("?");
				}else{
					sb.append("&");
				}
				sb.append(namelist.get(i)).append("=").append(paramvalue);
			}
			
		}
		
		return sb.toString();
	}

}
