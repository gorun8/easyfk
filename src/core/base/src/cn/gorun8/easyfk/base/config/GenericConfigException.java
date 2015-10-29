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
package cn.gorun8.easyfk.base.config;

import cn.gorun8.easyfk.base.util.GeneralException;

/**
 * GenericConfigException
 *
 */
@SuppressWarnings("serial")
public class GenericConfigException extends GeneralException {

    public GenericConfigException() {
        super();
    }

    public GenericConfigException(String str) {
        super(str);
    }

    public GenericConfigException(Throwable nested) {
        super(nested);
    }

    public GenericConfigException(String str, Throwable nested) {
        super(str, nested);
    }
}
