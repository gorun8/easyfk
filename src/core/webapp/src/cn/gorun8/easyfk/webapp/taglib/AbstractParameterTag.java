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
