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
