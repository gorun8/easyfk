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
package cn.gorun8.easyfk.base.config;

import javolution.util.FastMap;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilXml;
import org.w3c.dom.Element;

/**
 * JNDIConfigUtil
 *
 */
public class JNDIConfigUtil {

    public static final String module = JNDIConfigUtil.class.getName();
    public static final String JNDI_CONFIG_XML_FILENAME = "jndiservers.xml";
    private static final FastMap<String, JndiServerInfo> jndiServerInfos = FastMap.newInstance();

    private static Element getXmlRootElement() throws GenericConfigException {
        try {
            return ResourceLoader.getXmlRootElement(JNDIConfigUtil.JNDI_CONFIG_XML_FILENAME);
        } catch (GenericConfigException e) {
            throw new GenericConfigException("Could not get JNDI XML root element", e);
        }
    }

    static {
        try {
            initialize(getXmlRootElement());
        } catch (Exception e) {
            Debug.logError(e, "Error loading JNDI config XML file " + JNDI_CONFIG_XML_FILENAME, module);
        }
    }
    public static void initialize(Element rootElement) throws GenericConfigException {
        // jndi-server - jndiServerInfos
        for (Element curElement: UtilXml.childElementList(rootElement, "jndi-server")) {
            JndiServerInfo jndiServerInfo = new JndiServerInfo(curElement);

            jndiServerInfos.putIfAbsent(jndiServerInfo.name, jndiServerInfo);
        }
    }

    public static JndiServerInfo getJndiServerInfo(String name) {
        return jndiServerInfos.get(name);
    }

    public static final class JndiServerInfo {
        public final String name;
        public final String contextProviderUrl;
        public final String initialContextFactory;
        public final String urlPkgPrefixes;
        public final String securityPrincipal;
        public final String securityCredentials;

        public JndiServerInfo(Element element) {
            this.name = element.getAttribute("name");
            this.contextProviderUrl = element.getAttribute("context-provider-url");
            this.initialContextFactory = element.getAttribute("initial-context-factory");
            this.urlPkgPrefixes = element.getAttribute("url-pkg-prefixes");
            this.securityPrincipal = element.getAttribute("security-principal");
            this.securityCredentials = element.getAttribute("security-credentials");
        }
    }
}
