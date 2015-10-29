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

/**
 * HttpClient Exception.
 *
 */
@SuppressWarnings("serial")
public class HttpClientException extends GeneralException {

    Throwable nested = null;

    public HttpClientException() {
        super();
    }

    public HttpClientException(String str) {
        super(str);
    }

    public HttpClientException(String str, Throwable nested) {
        super(str, nested);
    }
}

