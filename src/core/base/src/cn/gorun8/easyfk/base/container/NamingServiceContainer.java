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

import java.net.UnknownHostException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NoSuchObjectException;
import java.rmi.server.UnicastRemoteObject;

import cn.gorun8.easyfk.base.util.RMIExtendedSocketFactory;

/**
 * NamingServiceContainer
 *
 */

public class NamingServiceContainer implements Container {

    public static final String module = NamingServiceContainer.class.getName();

    protected String configFileLocation = null;
    protected boolean isRunning = false;
    protected Registry registry = null;
    protected int namingPort = 1099;
    protected String namingHost = null;

    protected RMIExtendedSocketFactory rmiSocketFactory;

    public void init(String[] args, String configFile) throws ContainerException {
        this.configFileLocation = configFile;

        ContainerConfig.Container cfg = ContainerConfig.getContainer("naming-container", configFileLocation);

        // get the telnet-port
        ContainerConfig.Container.Property port = cfg.getProperty("port");
        if (port.value != null) {
            try {
                this.namingPort = Integer.parseInt(port.value);
            } catch (Exception e) {
                throw new ContainerException("Invalid port defined in container [naming-container] configuration; not a valid int");
            }
        }

        // get the telnet-host
        ContainerConfig.Container.Property host = cfg.getProperty("host");
        if (host != null && host.value != null) {
            this.namingHost =  host.value ;
        }

        try {
            rmiSocketFactory = new RMIExtendedSocketFactory( namingHost );
        } catch ( UnknownHostException uhEx ) {
            throw new ContainerException("Invalid host defined in container [naming-container] configuration; not a valid IP address", uhEx);
        }

    }

    public boolean start() throws ContainerException {
        try {
            registry = LocateRegistry.createRegistry(namingPort, rmiSocketFactory, rmiSocketFactory);
        } catch (RemoteException e) {
            throw new ContainerException("Unable to locate naming service", e);
        }

        isRunning = true;
        return isRunning;
    }

    public void stop() throws ContainerException {
        if (isRunning) {
            try {
                isRunning = !UnicastRemoteObject.unexportObject(registry, true);
            } catch (NoSuchObjectException e) {
                throw new ContainerException("Unable to shutdown naming registry");
            }
        }
    }
}
