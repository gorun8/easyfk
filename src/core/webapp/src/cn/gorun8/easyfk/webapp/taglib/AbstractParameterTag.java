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
package cn.gorun8.easyfk.webapp.taglib;

import java.util.Map;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import javolution.util.FastMap;

/**
 * AbstractParameterTag - Tag which support child parameter tags.
 */
@SuppressWarnings("serial")
public abstract class AbstractParameterTag extends TagSupport {

    private Map<String, Object> inParameters = null;
    private Map<String, String> outParameters = null;

    public void addInParameter(String name, Object value) {
        if (this.inParameters == null)
            this.inParameters = FastMap.newInstance();
        inParameters.put(name, value);
    }

    public Map<String, Object> getInParameters() {
        if (this.inParameters == null)
            return FastMap.newInstance();
        else
            return this.inParameters;
    }

    public void addOutParameter(Object name, Object alias) {
        if (this.outParameters == null)
            this.outParameters = FastMap.newInstance();
        outParameters.put((String) name, (String) alias);
    }

    public Map<String, String> getOutParameters() {
        if (this.outParameters == null)
            return FastMap.newInstance();
        else
            return this.outParameters;
    }

    @Override
    public int doStartTag() throws JspTagException {
        inParameters = FastMap.newInstance();
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public abstract int doEndTag() throws JspTagException;

}
