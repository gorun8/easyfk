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

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.List;

import javax.net.ssl.X509TrustManager;

import javolution.util.FastList;

/**
 * MultiTrustManager
 */
public class MultiTrustManager implements X509TrustManager {

    public static final String module = MultiTrustManager.class.getName();

    protected List<KeyStore> keystores;

    public MultiTrustManager(KeyStore ks) {
        this();
        keystores.add(ks);
    }

    public MultiTrustManager() {
        keystores = FastList.newInstance();
    }

    public void add(KeyStore ks) {
        if (ks != null) {
            keystores.add(ks);
        }
    }

    public int getNumberOfKeyStores() {
        return keystores.size();
    }

    public void checkClientTrusted(X509Certificate[] certs, String alg) throws CertificateException {
        if (!isTrusted(certs)) {
            throw new CertificateException("No trusted certificate found");
        }
    }

    public void checkServerTrusted(X509Certificate[] certs, String alg) throws CertificateException {
        if (!isTrusted(certs)) {
            throw new CertificateException("No trusted certificate found");
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        List<X509Certificate> issuers = FastList.newInstance();
        for (KeyStore store: keystores) {
            try {
                Enumeration<String> e = store.aliases();
                while (e.hasMoreElements()) {
                    String alias = e.nextElement();
                    Certificate[] chain = store.getCertificateChain(alias);
                    if (chain != null) {
                        for (Certificate cert: chain) {
                            if (cert instanceof X509Certificate) {
                                if (Debug.verboseOn())
                                    Debug.logInfo("Read certificate (chain) : " + ((X509Certificate) cert).getSubjectX500Principal().getName(), module);
                                issuers.add((X509Certificate) cert);
                            }
                        }
                    } else {
                        Certificate cert = store.getCertificate(alias);
                        if (cert != null && cert instanceof X509Certificate) {
                            if (Debug.verboseOn())
                                Debug.logInfo("Read certificate : " + ((X509Certificate) cert).getSubjectX500Principal().getName(), module);
                            issuers.add((X509Certificate) cert);
                        }
                    }
                }
            } catch (KeyStoreException e) {
                Debug.logError(e, module);
            }
        }

        return issuers.toArray(new X509Certificate[issuers.size()]);
    }

    protected boolean isTrusted(X509Certificate[] cert) {
        if (cert != null) {
            X509Certificate[] issuers = this.getAcceptedIssuers();
            if (issuers != null) {
                for (X509Certificate issuer: issuers) {
                    for (X509Certificate c: cert) {
                        if (Debug.verboseOn())
                            Debug.logInfo("--- Checking cert: " + issuer.getSubjectX500Principal() + " vs " + c.getSubjectX500Principal(), module);
                        if (issuer.equals(c)) {
                            if (Debug.verboseOn())
                                Debug.logInfo("--- Found trusted cert: " + issuer.getSerialNumber().toString(16) + " : " + issuer.getSubjectX500Principal(), module);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
