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
import cn.gorun8.easyfk.base.config.GenericConfigException;

/**
 * ComponentException
 *
 */
@SuppressWarnings("serial")
public class ComponentException extends GenericConfigException {

    public ComponentException() {
        super();
    }

    public ComponentException(String str) {
        super(str);
    }

    public ComponentException(Throwable nested) {
        super(nested);
    }

    public ComponentException(String str, Throwable nested) {
        super(str, nested);
    }
}
