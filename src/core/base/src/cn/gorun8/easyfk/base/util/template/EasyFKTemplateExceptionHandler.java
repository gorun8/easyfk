package cn.gorun8.easyfk.base.util.template;
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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.StringUtil;
import cn.gorun8.easyfk.base.util.UtilGenerics;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * EasyFK specific TemplateExceptionHandler.  Sanitizes any error messages present in
 * the stack trace prior to printing to the output writer.
 */
public class EasyFKTemplateExceptionHandler implements TemplateExceptionHandler {
    public static final String module = EasyFKTemplateExceptionHandler.class.getName();

	public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
        StringWriter tempWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(tempWriter, true);
        te.printStackTrace(pw);
        String stackTrace = tempWriter.toString();

        StringUtil.SimpleEncoder simpleEncoder = getWrappedObject("simpleEncoder", env);
        if (simpleEncoder != null) {
            stackTrace = simpleEncoder.encode(stackTrace);
        }
        try {
            out.write(stackTrace);
        } catch (IOException e) {
            Debug.logError(e, module);
        }
    }
    
	/**
	    * Gets BeanModel from FreeMarker context and returns the object that it wraps.
	    * @param varName the name of the variable in the FreeMarker context.
	    * @param env the FreeMarker Environment
	    */
	    public static <T> T getWrappedObject(String varName, Environment env) {
	        Object obj = null;
	        try {
	            obj = env.getVariable(varName);
	            if (obj != null) {
	                if (obj == TemplateModel.NOTHING) {
	                    obj = null;
	                } else if (obj instanceof BeanModel) {
	                    BeanModel bean = (BeanModel) obj;
	                    obj = bean.getWrappedObject();
	                } else if (obj instanceof SimpleScalar) {
	                    obj = obj.toString();
	                }
	            }
	        } catch (TemplateModelException e) {
	            Debug.logInfo(e.getMessage(), module);
	        }
	        return UtilGenerics.<T>cast(obj);
	    }

    
}