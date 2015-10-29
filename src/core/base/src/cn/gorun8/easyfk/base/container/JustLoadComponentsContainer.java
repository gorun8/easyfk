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

import cn.gorun8.easyfk.base.component.AlreadyLoadedException;
import cn.gorun8.easyfk.base.component.ComponentException;
import cn.gorun8.easyfk.base.util.Debug;

/**
 * A Container implementation to run the tests configured through this testtools stuff.
 */
public class JustLoadComponentsContainer implements Container {

    public static final String module = JustLoadComponentsContainer.class.getName();

    /**
     * @see cn.gorun8.easyfk.base.container.Container#init(java.lang.String[], java.lang.String)
     */
    public void init(String[] args, String configFile) {
        try {
            ComponentContainer.loadComponents(true);
        } catch (AlreadyLoadedException e) {
            Debug.logError(e, module);
        } catch (ComponentException e) {
            Debug.logError(e, module);
            //throw UtilMisc.initCause(new ContainerException(e.getMessage()), e);
        }
    }

    public boolean start() throws ContainerException {
        return true;
    }

    public void stop() throws ContainerException {
    }
}
