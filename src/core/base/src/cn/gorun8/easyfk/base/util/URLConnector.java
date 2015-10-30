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
package cn.gorun8.easyfk.base.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import cn.gorun8.easyfk.base.config.GenericConfigException;

/**
 * URLConnector.java
 *
 */
public class URLConnector {

    public static final String module = URLConnector.class.getName();

    private URLConnection connection = null;
    private URL url = null;
    private String clientCertAlias = null;
    private boolean timedOut = false;
    private boolean trustAnyCert = false;
    private int hostCertLevel = 2;

    protected URLConnector() {}
    protected URLConnector(URL url, String clientCertAlias, int hostCertLevel, boolean trustAnyCert) {
        this.clientCertAlias = clientCertAlias;
        this.url = url;
        this.trustAnyCert = trustAnyCert;
        this.hostCertLevel = hostCertLevel;
    }

    protected synchronized URLConnection openConnection(int timeout) throws IOException {
        Thread t = new Thread(new URLConnectorThread());
        t.start();

        try {
            this.wait(timeout);
        } catch (InterruptedException e) {
            if (connection == null) {
                timedOut = true;
            } else {
                close(connection);
            }
            throw new IOException("Connection never established");
        }

        if (connection != null) {
            return connection;
        } else {
            timedOut = true;
            throw new IOException("Connection timed out");
        }
    }

    // trusted certs only
    public static URLConnection openConnection(URL url) throws IOException {
        return openConnection(url, 30000);
    }

    public static URLConnection openConnection(URL url, int timeout) throws IOException {
        return openConnection(url, timeout, null, SSLUtil.HOSTCERT_NORMAL_CHECK);
    }

    public static URLConnection openConnection(URL url, String clientCertAlias) throws IOException {
        return openConnection(url, 30000, clientCertAlias, SSLUtil.HOSTCERT_NORMAL_CHECK);
    }

    public static URLConnection openConnection(URL url, int timeout, String clientCertAlias, int hostCertLevel) throws IOException {
        URLConnector uc = new URLConnector(url, clientCertAlias, hostCertLevel, false);
        return uc.openConnection(timeout);
    }

    // allow untrusted certs
    public static URLConnection openUntrustedConnection(URL url) throws IOException {
        return openConnection(url, 30000);
    }

    public static URLConnection openUntrustedConnection(URL url, int timeout) throws IOException {
        return openConnection(url, timeout, null, SSLUtil.HOSTCERT_NORMAL_CHECK);
    }

    public static URLConnection openUntrustedConnection(URL url, String clientCertAlias) throws IOException {
        return openConnection(url, 30000, clientCertAlias, SSLUtil.HOSTCERT_NORMAL_CHECK);
    }

    public static URLConnection openUntrustedConnection(URL url, int timeout, String clientCertAlias, int hostCertLevel) throws IOException {
        URLConnector uc = new URLConnector(url, clientCertAlias, hostCertLevel, true);
        return uc.openConnection(timeout);
    }

    // special thread to open the connection
    private class URLConnectorThread implements Runnable {
        public void run() {
            URLConnection con = null;
            try {
                con = url.openConnection();

                if ("HTTPS".equalsIgnoreCase(url.getProtocol())) {
                    HttpsURLConnection scon = (HttpsURLConnection) con;
                    try {
                        scon.setSSLSocketFactory(SSLUtil.getSSLSocketFactory(clientCertAlias, trustAnyCert));
                        HostnameVerifier hv = SSLUtil.getHostnameVerifier(hostCertLevel);
                        if (hv != null) {
                            scon.setHostnameVerifier(hv);
                        }
                    } catch (GeneralSecurityException e) {
                        Debug.logError(e, module);
                    } catch (GenericConfigException e) {
                        Debug.logError(e, module);
                    }
                }
            } catch (IOException e) {
                Debug.logError(e, module);
            }

            synchronized (URLConnector.this) {
                if (timedOut && con != null) {
                    close(con);
                } else {
                    connection = con;
                    URLConnector.this.notify();
                }
            }
        }
    }

    // closes the HttpURLConnection does nothing to others
    private static void close(URLConnection con) {
        if (con instanceof HttpURLConnection) {
            ((HttpURLConnection) con).disconnect();
        }
    }
}
