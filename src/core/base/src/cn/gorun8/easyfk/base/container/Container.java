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

/**
 * An EasyFK container. A container can be thought of as a background process.
 * 
 * <p>
 * When EasyFK starts, the main thread will create the <code>Container</code> instance and
 * then call the container's <code>init</code> method. If the method returns without
 * throwing an exception the container will be added to a list of initialized containers.
 * After all instances have been created and initialized, the main thread will call the
 * <code>start</code> method of each container in the list. When EasyFK shuts down, a
 * separate shutdown thread will call the <code>stop</code> method of each container.
 * Implementations should anticipate asynchronous calls to the methods by different
 * threads.
 * </p>
 * 
 * <p>Containers might be loaded more than once (have more than one instance).<p>
 */
public interface Container {

    /** Initialize the container. This method must not block - implementations
     * should initialize internal structures and then return.
     *
     * @param args Command-line arguments.
     * @param configFile Location of the configuration file used to load this container.
     * @throws ContainerException If an error was encountered. Throwing this exception
     * will halt container loading, so it should be thrown only when other containers
     * might depend on this one.
     */
    public void init(String[] args, String configFile) throws ContainerException;

    /**
     * Start the container process. This method must not block - implementations
     * that require thread blocking must create a separate thread and then return.
     *
     * @return <code>true</code> if the process started.
     * @throws ContainerException If an error was encountered.
     */
    public boolean start() throws ContainerException;

    /**
     * Stop the container process. This method must not block.
     *
     * @throws ContainerException If an error was encountered.
     */
    public void stop() throws ContainerException;
}
