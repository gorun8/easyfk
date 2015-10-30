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
package cn.gorun8.easyfk.base.start;

/**
 * An object that loads server startup classes.
 * <p>
 * When EasyFK starts, the main thread will create the <code>StartupLoader</code> instance and
 * then call the loader's <code>load</code> method. If the method returns without
 * throwing an exception the loader will be added to a list of initialized loaders.
 * After all instances have been created and initialized, the main thread will call the
 * <code>start</code> method of each loader in the list. When EasyFK shuts down, a
 * separate shutdown thread will call the <code>unload</code> method of each loader.
 * Implementations should anticipate asynchronous calls to the methods by different
 * threads.
 * </p>
 * 
 */
public interface StartupLoader {

    /**
     * Load a startup class.
     *
     * @param config Startup config.
     * @param args Command-line arguments.
     * @throws StartupException If an error was encountered. Throwing this exception
     * will halt loader loading, so it should be thrown only when EasyFK can't
     * operate without it.
     */
    public void load(Config config, String args[]) throws StartupException;

    /**
     * Start the startup class. This method must not block - implementations
     * that require thread blocking must create a separate thread and then return.
     * 
     * @throws StartupException If an error was encountered.
     */
    public void start() throws StartupException;

    /**
     * Stop the startup class. This method must not block.
     *
     * @throws StartupException If an error was encountered.
     */
    public void unload() throws StartupException;
}
