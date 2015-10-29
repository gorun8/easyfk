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
package cn.gorun8.easyfk.base.container;

import cn.gorun8.easyfk.base.config.GenericConfigException;

/**
 * ContainerException
 */
@SuppressWarnings("serial")
public class ContainerException extends GenericConfigException {

    public ContainerException() {
        super();
    }

    public ContainerException(String str) {
        super(str);
    }

    public ContainerException(Throwable t) {
        super(t);
    }

    public ContainerException(String str, Throwable nested) {
        super(str, nested);
    }

}
