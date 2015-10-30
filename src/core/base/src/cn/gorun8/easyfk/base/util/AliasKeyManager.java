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

import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.net.Socket;

import javax.net.ssl.X509KeyManager;

/**
 * AliasKeyManager - KeyManager used to specify a certificate alias
 *
 */
public class AliasKeyManager implements X509KeyManager {
    public static final String module = X509KeyManager.class.getName();

    protected X509KeyManager keyManager = null;
    protected String alias = null;

    protected AliasKeyManager() {}

    public AliasKeyManager(X509KeyManager keyManager, String alias) {
        this.keyManager = keyManager;
        this.alias = alias;
    }

    // this is where the customization comes in
    public String chooseClientAlias(String[] keyTypes, Principal[] issuers, Socket socket) {
        for (String keyType: keyTypes) {
            String[] aliases = keyManager.getClientAliases(keyType, null); // ignoring the issuers
            if (aliases != null && aliases.length > 0) {
                for (String alias: aliases) {
                    if (this.alias.equals(alias)) {
                        if (Debug.verboseOn()) Debug.logVerbose("chooseClientAlias for keyType [" + keyType + "] got alias " + this.alias, module);
                        //Debug.logInfo(new Exception(), "Location where chooseClientAlias is called", module);
                        return this.alias;
                    }
                }
            }
        }
        return null;
    }

    // these just pass through the keyManager
    public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
        return keyManager.chooseServerAlias(keyType, issuers, socket);
    }

    // these just pass through the keyManager
    public String chooseServerAlias(String keyType, Socket socket, Principal... issuers) {
        return keyManager.chooseServerAlias(keyType, issuers, socket);
    }

    public X509Certificate[] getCertificateChain(String alias) {
        X509Certificate[] certArray = keyManager.getCertificateChain(alias);
        if (Debug.verboseOn()) Debug.logVerbose("getCertificateChain for alias [" + alias + "] got " + certArray.length + " results", module);
        return certArray;
    }

    public String[] getClientAliases(String keyType, Principal[] issuers) {
        return keyManager.getClientAliases(keyType, issuers);
    }

    public PrivateKey getPrivateKey(String alias) {
        PrivateKey pk = keyManager.getPrivateKey(alias);
        if (Debug.verboseOn()) Debug.logVerbose("getPrivateKey for alias [" + alias + "] got " + (pk == null ? "[Not Found!]" : "[alg:" + pk.getAlgorithm() + ";format:" + pk.getFormat() + "]"), module);
        //Debug.logInfo(new Exception(), "Location where getPrivateKey is called", module);
        return pk;
    }

    public String[] getServerAliases(String keyType, Principal[] issuers) {
        return keyManager.getServerAliases(keyType, issuers);
    }
}
