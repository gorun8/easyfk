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
package cn.gorun8.easyfk.security.shiro.x509;

import cn.gorun8.easyfk.security.shiro.passwd.UsernamePasswordCaptchaToken;


public class X509CaptchaToken extends UsernamePasswordCaptchaToken {

	/**
	 * PEM证书
	 */
	private String cert;
	
	/**
	 * Base64签名
	 */
	private String sign;
	/**
	 * 加密参数
	 */
	private String seed;
	
	/**
	 * 证书文件
	 */
	private String certPath;
	
	/**
	 * 证书DN
	 */
	private String subject ;
	
	public X509CaptchaToken() {
	}

	public X509CaptchaToken(String cert, String sign,
			boolean rememberMe, String captcha,String seed,String captchaId) {
		super("", "".toCharArray(), rememberMe, "",captcha, captchaId);
		this.cert = cert;
		this.sign = sign;
		this.seed = seed;
	}
	
	 


	public String getCert() {
		return cert;
	}


	public void setCert(String cert) {
		this.cert = cert;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}
 

}