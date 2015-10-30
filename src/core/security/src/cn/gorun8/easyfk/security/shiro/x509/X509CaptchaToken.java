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