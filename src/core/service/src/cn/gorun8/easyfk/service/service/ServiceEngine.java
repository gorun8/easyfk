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
 * Author:hezhiping   Email:110476592@qq.com   Date: 16-1-27
 */
package cn.gorun8.easyfk.service.service;

import cn.gorun8.easyfk.base.component.ComponentConfig;
import cn.gorun8.easyfk.base.util.*;
import cn.gorun8.easyfk.security.utils.UtilSecurity;
import freemarker.template.utility.StringUtil;
import javolution.util.FastList;
import javolution.util.FastMap;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class ServiceEngine {
    private final static String module = ServiceEngine.class.getName();
    private Map<String, ModelService> modelServices = FastMap.newInstance();
    private boolean inited = false;

    public ModelService getMethodClass(String methodName) {
        return modelServices.get(methodName);
    }


    // static java service methods should be: public Map<String, Object> serviceName(Map<String, Object> context)
    public Object invokerService(String serviceName, Map<String, Object> context) throws cn.gorun8.easyfk.service.service.GeneralServiceException {
        if (!inited) {
            loadModelService();
        }

        if (context == null) {
            Debug.logError("ERROR: Null Service Context.", module);
        }

        Object result = null;
        try {
            ModelService modelService = getMethodClass(serviceName);
            if(UtilValidate.isEmpty(modelService)){
                throw new cn.gorun8.easyfk.service.service.GeneralServiceException("Service [" + serviceName + "] does not exist");
            }

            if(modelService.auth){
                if(!UtilSecurity.hasAuthenticated() ){
                    throw new cn.gorun8.easyfk.service.service.GeneralServiceException("Service [" + serviceName + "] require auth");
                }
            }

            ClassLoader cl = this.getClass().getClassLoader();
            Class<?> cls = cl.loadClass(modelService.location);
            Method m = cls.getMethod(modelService.invoke, Map.class);
            if (Modifier.isStatic(m.getModifiers())) {
                result = m.invoke(null, context);
            } else {
                Object obj = UtilIOC.getBean(cls);
                result = m.invoke(obj, context);
            }

        } catch (NoSuchMethodException nsme) {
            throw new cn.gorun8.easyfk.service.service.GeneralServiceException("Service [" + serviceName + "] specified Java method (invoke attribute) does not exist", nsme);
        } catch (SecurityException se) {
            throw new cn.gorun8.easyfk.service.service.GeneralServiceException("Service [" + serviceName + "] Access denied", se);
        } catch (IllegalAccessException iae) {
            throw new cn.gorun8.easyfk.service.service.GeneralServiceException("Service [" + serviceName + "] Method not accessible", iae);
        } catch (IllegalArgumentException iarge) {
            throw new cn.gorun8.easyfk.service.service.GeneralServiceException("Service [" + serviceName + "] Invalid parameter match", iarge);
        } catch (InvocationTargetException ite) {
            throw new cn.gorun8.easyfk.service.service.GeneralServiceException("Service [" + serviceName + "] target threw an unexpected exception", ite.getTargetException());
        } catch (NullPointerException npe) {
            throw new cn.gorun8.easyfk.service.service.GeneralServiceException("Service [" + serviceName + "] ran into an unexpected null object", npe);
        } catch (ExceptionInInitializerError eie) {
            throw new cn.gorun8.easyfk.service.service.GeneralServiceException("Service [" + serviceName + "] Initialization failed", eie);
        } catch (Throwable th) {
            throw new cn.gorun8.easyfk.service.service.GeneralServiceException("Service [" + serviceName + "] Error or unknown exception", th);
        }
        return result;
    }


    private synchronized void loadModelService() {
        inited = true;
        Collection<ComponentConfig> allComponentList = ComponentConfig.getAllComponents();
        for (ComponentConfig cc: allComponentList) {
            List<ComponentConfig.ServiceResourceInfo>  entityList = cc.getServiceResourceInfos();
            String path = cc.getRootLocation();
            for(ComponentConfig.ServiceResourceInfo it : entityList){
                String loc = it.location;
                File f = new File(path,loc);
                if(f.isFile()){
                    loadModelService(f.getAbsolutePath());
                }else{
                    Debug.logError(f.getAbsolutePath()+"is not a sql file ,please check",module);
                }
            }
        }//end for

    }

    private Document getDocument(URL url) {
        if (url == null)
            return null;
        Document document = null;

        try {
            document = UtilXml.readXmlDocument(url, true, true);
        } catch (SAXException sxe) {
            // Error generated during parsing)
            Exception x = sxe;

            if (sxe.getException() != null)
                x = sxe.getException();
            x.printStackTrace();
        } catch (ParserConfigurationException pce) {
            // Parser with specified options can't be built
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return document;
    }

    private void  loadModelService(String resourceLocation) {
        URL confUrl = UtilURL.fromResource(resourceLocation);
        Document   document = getDocument(confUrl);
        if (document == null) {
            return  ;
        }

        Element docElement = document.getDocumentElement();
        if (docElement == null) {
            return  ;
        }

        docElement.normalize();

        int i = 0;
        Node curChild = docElement.getFirstChild();
        if (curChild != null) {
            do {
                if (curChild.getNodeType() == Node.ELEMENT_NODE && "service".equals(curChild.getNodeName())) {
                    i++;
                    Element curServiceElement = (Element) curChild;
                    String serviceName = UtilXml.checkEmpty(curServiceElement.getAttribute("name"));
                    // check to see if service with same name has already been read
                    if (modelServices.containsKey(serviceName)) {
                        Debug.logWarning("WARNING: Service " + serviceName + " is defined more than once, " +
                                "most recent will over-write previous definition(s)", module);
                    }

                    ModelService service = createModelService(curServiceElement, resourceLocation);
                    if (service != null) {
                        modelServices.put(serviceName, service);
                    } else {
                        Debug.logWarning(
                                "-- -- SERVICE ERROR:getModelService: Could not create service for serviceName: " +
                                        serviceName, module);
                    }
                }

            } while ((curChild = curChild.getNextSibling()) != null);
        } else {
            Debug.logWarning("No child nodes found.", module);
        }

    }

    private ModelService createModelService(Element serviceElement, String resourceLocation) {
        ModelService service = new ModelService();
        service.name = UtilXml.checkEmpty(serviceElement.getAttribute("name")).intern();
        service.definitionLocation = resourceLocation;
        service.description= UtilXml.checkEmpty(serviceElement.getAttribute("description")).intern();
        service.engineName = UtilXml.checkEmpty(serviceElement.getAttribute("engine")).intern();
        service.location = UtilXml.checkEmpty(serviceElement.getAttribute("location")).intern();
        service.invoke = UtilXml.checkEmpty(serviceElement.getAttribute("invoke")).intern();
        // these default to true; if anything but true, make false
        service.auth = "true".equalsIgnoreCase(serviceElement.getAttribute("auth"));
        service.export = "true".equalsIgnoreCase(serviceElement.getAttribute("export"));
        service.debug = "true".equalsIgnoreCase(serviceElement.getAttribute("debug"));
        // these defaults to false; if anything but false, make it true
        service.validate = !"false".equalsIgnoreCase(serviceElement.getAttribute("validate"));
        return service;
    }
}
