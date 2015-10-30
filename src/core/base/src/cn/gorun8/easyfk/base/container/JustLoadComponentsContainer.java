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
