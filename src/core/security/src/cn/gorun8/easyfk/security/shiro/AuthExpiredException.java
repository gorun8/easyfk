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
package cn.gorun8.easyfk.security.shiro;

import org.apache.shiro.authc.ExpiredCredentialsException;

public class AuthExpiredException extends ExpiredCredentialsException {

	private String uid ;
	private boolean resetPassword;
	
	public AuthExpiredException(String uid,String msg,boolean resetPassword) {
		super(msg);
		this.uid = uid;
		this.resetPassword = resetPassword;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public boolean isResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(boolean resetPassword) {
		this.resetPassword = resetPassword;
	}
	
}
