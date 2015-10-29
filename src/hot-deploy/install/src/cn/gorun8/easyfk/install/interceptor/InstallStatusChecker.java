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
package cn.gorun8.easyfk.install.interceptor;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import cn.gorun8.easyfk.base.util.FileUtil;

/**
 * 初始化拦截器
 */
public class InstallStatusChecker extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws IOException {

		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {

			File file = FileUtil
					.getFile("component://base/config/install.lock");
			if (file.exists()) {
				String uri = request.getRequestURI();
				if(uri.indexOf("finished2") >= 0 ){
					return true;
				}
				response.setCharacterEncoding("UTF-8");
				response.sendRedirect("finished2");
				return false;
			} else {
				return true;
			}
		}
		return true;
	}

}
