package cn.gorun8.easyfk.webapp.ftl;
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
 
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.StringUtil;
import cn.gorun8.easyfk.base.util.UtilMisc;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.base.util.template.EasyFKFileTemplateLoader;
import cn.gorun8.easyfk.base.util.template.EasyFKTemplateExceptionHandler;

import freemarker.cache.TemplateLoader;
import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
 
public class EasyFKFreeMarkerConfigurer extends FreeMarkerConfigurer {
	public static final String module = EasyFKFreeMarkerConfigurer.class.getName();
	protected static BeansWrapper defaultEasyFKWrapper = BeansWrapper.getDefaultInstance();
    
	/**
	 * To be overridden by subclasses that want to to perform custom
	 * post-processing of the Configuration object after this factory
	 * performed its default initialization.
	 * <p>Called by {@code createConfiguration()}.
	 * @param config the current Configuration object
	 * @throws IOException if a config file wasn't found
	 * @throws TemplateException on FreeMarker initialization failure
	 * @see #createConfiguration()
	 */
	protected void postProcessConfiguration(Configuration newConfig) throws IOException, TemplateException {
		
	 
		newConfig.setObjectWrapper(defaultEasyFKWrapper);
        newConfig.setSharedVariable("Static", defaultEasyFKWrapper.getStaticModels());
        newConfig.setLocalizedLookup(false);
        newConfig.setSharedVariable("StringUtil", new BeanModel(StringUtil.INSTANCE, defaultEasyFKWrapper));
        newConfig.setAutoImports(UtilProperties.getProperties("freemarkerImports"));
        newConfig.setTemplateExceptionHandler(new EasyFKTemplateExceptionHandler());
         
         
        // Transforms properties file set up as key=transform name, property=transform class name
        //从配置文件中加载自定义标签
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources;
        try {
            resources = loader.getResources("freemarkerTransforms.properties");
        } catch (IOException e) {
            Debug.logError(e, "Could not load list of freemarkerTransforms.properties", module);
            throw UtilMisc.initCause(new InternalError(e.getMessage()), e);
        }
        while (resources.hasMoreElements()) {
            URL propertyURL = resources.nextElement();
            Debug.logInfo("loading properties: " + propertyURL, module);
            Properties props = UtilProperties.getProperties(propertyURL);
            if (UtilValidate.isEmpty(props)) {
                Debug.logError("Unable to locate properties file " + propertyURL, module);
            } else {
                loadTransforms(loader, props, newConfig);
            }
        }
	}
	 
	protected Configuration newConfiguration() throws IOException, TemplateException {
		return new Configuration();
	}
	/**
	 * Determine a FreeMarker TemplateLoader for the given path.
	 * <p>Default implementation creates either a FileTemplateLoader or
	 * a SpringTemplateLoader.
	 * @param templateLoaderPath the path to load templates from
	 * @return an appropriate TemplateLoader
	 * @see freemarker.cache.FileTemplateLoader
	 * @see SpringTemplateLoader
	 */
	protected TemplateLoader getTemplateLoaderForPath(String templateLoaderPath) {
		if (isPreferFileSystemAccess()) {
			// Try to load via the file system, fall back to SpringTemplateLoader
			// (for hot detection of template changes, if possible).
			try {
				Resource path = getResourceLoader().getResource(templateLoaderPath);
				File file = path.getFile();  // will fail if not resolvable in the file system
				if (logger.isDebugEnabled()) {
					logger.debug(
							"Template loader path [" + path + "] resolved to file path [" + file.getAbsolutePath() + "]");
				}
				return new EasyFKFileTemplateLoader(file);
			}
			catch (IOException ex) {
				if (logger.isDebugEnabled()) {
					logger.debug("Cannot resolve template loader path [" + templateLoaderPath +
							"] to [java.io.File]: using SpringTemplateLoader as fallback", ex);
				}
				return new SpringTemplateLoader(getResourceLoader(), templateLoaderPath);
			}
		}
		else {
			// Always load via SpringTemplateLoader (without hot detection of template changes).
			logger.debug("File system access not preferred: using SpringTemplateLoader");
			return new SpringTemplateLoader(getResourceLoader(), templateLoaderPath);
		}
	}
	
	/**
     * Protected helper method.
     */
    protected static void loadTransforms(ClassLoader loader, Properties props, Configuration config) {
        for (Iterator<Object> i = props.keySet().iterator(); i.hasNext();) {
            String key = (String) i.next();
            String className = props.getProperty(key);
            if (Debug.verboseOn()) {
                Debug.logVerbose("Adding FTL Transform " + key + " with class " + className, module);
            }
            try {
                config.setSharedVariable(key, loader.loadClass(className).newInstance());
            } catch (Exception e) {
                Debug.logError(e, "Could not pre-initialize dynamically loaded class: " + className + ": " + e, module);
            }
        }
    }
	

}
