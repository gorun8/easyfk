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
package cn.gorun8.easyfk.base.component;

/**
 * Component Already Loaded Exception
 *
 */
@SuppressWarnings("serial")
public class AlreadyLoadedException extends ComponentException {

    public AlreadyLoadedException() {
        super();
    }

    public AlreadyLoadedException(String str) {
        super(str);
    }

    public AlreadyLoadedException(Throwable nested) {
        super(nested);
    }

    public AlreadyLoadedException(String str, Throwable nested) {
        super(str, nested);
    }
}

