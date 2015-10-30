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
package cn.gorun8.easyfk.security.utils;

import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * 利用JCaptcha生成验证码和验证
 */
public class UtilJCaptcha {
	private final static Logger logger = LoggerFactory.getLogger(UtilJCaptcha.class);
	public  final static ImageCaptchaService imageCaptchaService = new DefaultManageableImageCaptchaService();

	/**
	 * 产生验证码图片
	 * @param id
	 * @param session
	 * @param response
	 */
	public static void createCaptcha(String id,
			HttpSession session,
			HttpServletResponse response) {
		try {
			String sessionId = session.getId();
			BufferedImage bufferedImage = imageCaptchaService.getImageChallengeForID(sessionId);
			ServletOutputStream servletOutputStream = response.getOutputStream();

			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");

			ImageIO.write(bufferedImage, "jpg", servletOutputStream);
			servletOutputStream.flush();
			servletOutputStream.close();

		} catch (Exception ioe) {
			logger.debug(ioe.getMessage());
		}

	}



	/**
	 * 验证验证码
	 * @param id
	 * @param session
	 * @param code
	 * @return
	 */
	public static boolean verifyCaptch(String id,HttpSession session,String code){
		String sessionId = session.getId();
		return imageCaptchaService.validateResponseForID(sessionId,code).booleanValue();
	}

	/**
	 * 验证验证码
	 * @param id
	 * @param session
	 * @param code
	 * @return
	 */
	public static boolean verifyCaptch(String id,Session session,String code){

		String sessionId = session.getId().toString();
		return imageCaptchaService.validateResponseForID(sessionId,code).booleanValue();
	}
}
