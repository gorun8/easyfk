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
package cn.gorun8.easyfk.catalina.container;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

/**
 * To use add (or uncomment) the following line to the Tomcat/Catalina configuarion (ie in easyfk-containers.xml under the <property name="default-server" value="engine"> element)
 *    <property name="ssl-accelerator-port" value="8443"/>
 *
 * Once that is done just setup a connector just like the example http-connector and have it listen on the port you set in the ssl-accelerator-port value.
 */
public class SslAcceleratorValve extends ValveBase {

    protected Integer sslAcceleratorPort = null;

    public void setSslAcceleratorPort(Integer sslAcceleratorPort) {
        this.sslAcceleratorPort = sslAcceleratorPort;
    }

    public Integer getSslAcceleratorPort() {
        return sslAcceleratorPort;
    }

    public @Override void invoke(Request req, Response resp) throws IOException, ServletException {
        if (sslAcceleratorPort != null && req.getLocalPort() == sslAcceleratorPort.intValue()) {
            req.setSecure(true);
        }

        if (getNext() != null) {
            getNext().invoke(req, resp);
        }
    }
}
