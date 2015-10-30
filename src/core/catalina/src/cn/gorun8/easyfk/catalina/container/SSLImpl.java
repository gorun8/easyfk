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

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.tomcat.util.net.AbstractEndpoint;
import org.apache.tomcat.util.net.ServerSocketFactory;
import org.apache.tomcat.util.net.jsse.JSSEImplementation;
import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.SSLUtil;
import cn.gorun8.easyfk.base.util.UtilValidate;

/**
 * SSLImpl
 */
public class SSLImpl extends JSSEImplementation {

    public static final String module = SSLImpl.class.getName();
    protected ServerSocketFactory ssFactory = null;
    protected TrustManager[] allow;

    public SSLImpl() throws ClassNotFoundException {
        super();
        this.allow =  new TrustManager[] { new AllowTrustManager() };
        Debug.logInfo("SSLImpl loaded; using custom ServerSocketFactory", module);
    }

    @Override
    public ServerSocketFactory getServerSocketFactory(AbstractEndpoint endpoint) {
        if (UtilValidate.isEmpty(this.ssFactory)) {
            this.ssFactory = (new JSSEImplementation()).getServerSocketFactory(endpoint);
        }
        return ssFactory;
    }

    class AllowTrustManager implements X509TrustManager {

        private TrustManager[] tm;

        public AllowTrustManager() throws ClassNotFoundException {
            try {
                tm = SSLUtil.getTrustManagers();
            } catch (Exception e) {
                Debug.logError(e, module);
                throw new ClassNotFoundException(e.getMessage());
            }
        }

        public void checkClientTrusted(X509Certificate[] x509Certificates, String string) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] x509Certificates, String string) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return ((X509TrustManager) tm[0]).getAcceptedIssuers();
        }
    }
}
