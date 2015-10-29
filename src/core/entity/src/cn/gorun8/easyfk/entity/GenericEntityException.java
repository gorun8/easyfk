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
package cn.gorun8.easyfk.entity;


import cn.gorun8.easyfk.base.util.GeneralException;

/**
 * GenericEntityException
 *
 */
@SuppressWarnings("serial")
public class GenericEntityException extends GeneralException {

    public GenericEntityException() {
        super();
    }

    public GenericEntityException(Throwable nested) {
        super(nested);
    }

    public GenericEntityException(String str) {
        super(str);
    }

    public GenericEntityException(String str, Throwable nested) {
        super(str, nested);
    }
}
