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

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilCaptcha {
	private final static Logger logger = LoggerFactory.getLogger(UtilCaptcha.class);
	public final static String CAPTCHA_CODE = "_CAPTCHA_CODE_";
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
			String availableString = UtilProperties.getPropertyValue("captcha", "captcha.characters", "abcdefhjkmnpstuvwxy2345678");
			int charsToPrint = UtilProperties.getPropertyAsInteger("captcha", "captcha.code_length", 6);
			int fontSize = UtilProperties.getPropertyAsInteger("captcha", "captcha.font_size", 20);
			int width = UtilProperties.getPropertyAsInteger("captcha", "captcha.width", 40);
			int height = UtilProperties.getPropertyAsInteger("captcha", "captcha.height", 140);
			final char[] availableChars =availableString.toCharArray();

			Color backgroundColor = Color.gray;
			Color borderColor = Color.DARK_GRAY;
			Color textColor = Color.ORANGE;
			Color circleColor = new Color(160, 160, 160);
			Font textFont = new Font("Arial", Font.PLAIN, fontSize);
			int circlesToDraw = charsToPrint;
			float horizMargin = 20.0f;
			double rotationRange = 0.7; // in radians
			BufferedImage bufferedImage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);

			Graphics2D g = (Graphics2D) bufferedImage.getGraphics();

			g.setColor(backgroundColor);
			g.fillRect(0, 0, width, height);

			// Generating some circles for background noise
			g.setColor(circleColor);
			for (int i = 0; i < circlesToDraw; i++) {
				int circleRadius = (int) (Math.random() * height / 2.0);
				int circleX = (int) (Math.random() * width - circleRadius);
				int circleY = (int) (Math.random() * height - circleRadius);
				g.drawOval(circleX, circleY, circleRadius * 2, circleRadius * 2);
			}
			g.setColor(textColor);
			g.setFont(textFont);

			FontMetrics fontMetrics = g.getFontMetrics();
			int maxAdvance = fontMetrics.getMaxAdvance();
			int fontHeight = fontMetrics.getHeight();

			String captchaCode = RandomStringUtils.random(charsToPrint,
					availableChars);

			float spaceForLetters = -horizMargin * 2 + width;
			float spacePerChar = spaceForLetters / (charsToPrint - 1.0f);

			for (int i = 0; i < captchaCode.length(); i++) {

				// this is a separate canvas used for the character so that
				// we can rotate it independently
				int charWidth = fontMetrics.charWidth(captchaCode.charAt(i));
				int charDim = Math.max(maxAdvance, fontHeight);
				int halfCharDim = (charDim / 2);

				BufferedImage charImage = new BufferedImage(charDim, charDim,
						BufferedImage.TYPE_INT_ARGB);
				Graphics2D charGraphics = charImage.createGraphics();
				charGraphics.translate(halfCharDim, halfCharDim);
				double angle = (Math.random() - 0.5) * rotationRange;
				charGraphics
						.transform(AffineTransform.getRotateInstance(angle));
				charGraphics.translate(-halfCharDim, -halfCharDim);
				charGraphics.setColor(textColor);
				charGraphics.setFont(textFont);

				int charX = (int) (0.5 * charDim - 0.5 * charWidth);
				charGraphics.drawString("" + captchaCode.charAt(i), charX,
						((charDim - fontMetrics.getAscent()) / 2 + fontMetrics
								.getAscent()));

				float x = horizMargin + spacePerChar * (i) - charDim / 2.0f;
				int y = ((height - charDim) / 2);

				g.drawImage(charImage, (int) x, y, charDim, charDim, null, null);

				charGraphics.dispose();
			}
			// Drawing the image border
			g.setColor(borderColor);
			g.drawRect(0, 0, width - 1, height - 1);
			g.dispose();

			ServletOutputStream servletOutputStream = response.getOutputStream();
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");

			ImageIO.write(bufferedImage, "jpg", servletOutputStream);
			servletOutputStream.flush();
			servletOutputStream.close();

			//启用redis等外部缓存后，一旦session中的数据发生了变化都应该调用
			//session.setAttribute，这样才会将变化同步到session中。因些即使
			//从session中取到了captchaCodeMap，也应该再session一次。
			Map captchaCodeMap = (Map) session.getAttribute(CAPTCHA_CODE);
			if (captchaCodeMap == null) {
				captchaCodeMap = new HashMap();
			}

			captchaCodeMap.put(id, captchaCode);
			session.setAttribute(CAPTCHA_CODE, captchaCodeMap);


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

		Map captchMap = (Map)session.getAttribute(CAPTCHA_CODE);

		if(UtilValidate.isEmpty(captchMap)){
			return false;
		}

		String codetmp = (String)captchMap.get(id);
		if(UtilValidate.isEmpty(codetmp)){
			return false;
		}

		return  codetmp.equals(code);
	}

	/**
	 * 验证验证码
	 * @param id
	 * @param session
	 * @param code
	 * @return
	 */
	public static boolean verifyCaptch(String id,Session session,String code){

		Map captchMap = (Map)session.getAttribute(CAPTCHA_CODE);

		if(UtilValidate.isEmpty(captchMap)){
			return false;
		}

		String codetmp = (String)captchMap.get(id);
		if(UtilValidate.isEmpty(codetmp)){
			return false;
		}

		return  codetmp.equals(code);
	}
}
