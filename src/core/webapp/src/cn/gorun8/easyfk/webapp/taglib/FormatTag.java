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

import java.text.DateFormat;
import java.text.NumberFormat;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilJ2eeCompat;
import cn.gorun8.easyfk.base.util.UtilValidate;

/**
 * FormatTag - JSP Tag to format numbers and dates.
 */
@SuppressWarnings("serial")
public class FormatTag extends BodyTagSupport {

    public static final String module = FormatTag.class.getName();

    private String type = "N";
    private String defaultStr = "";

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getDefault() {
        return defaultStr;
    }

    public void setDefault(String defaultStr) {
        this.defaultStr = defaultStr;
    }

    @Override
    public int doAfterBody() throws JspException {
        NumberFormat nf = null;
        DateFormat df = null;
        BodyContent body = getBodyContent();
        String value = body.getString();
        body.clearBody();

        if (UtilValidate.isEmpty(value))
            return SKIP_BODY;

        if (type.charAt(0) == 'C' || type.charAt(0) == 'c')
            nf = NumberFormat.getCurrencyInstance();
        if (type.charAt(0) == 'N' || type.charAt(0) == 'n')
            nf = NumberFormat.getNumberInstance();
        if (type.charAt(0) == 'D' || type.charAt(0) == 'd')
            df = DateFormat.getDateInstance();

        try {
            if (nf != null) {
                // do the number formatting
                NumberFormat strFormat = NumberFormat.getInstance();

                getPreviousOut().print(nf.format(strFormat.parse(value.trim())));
            } else if (df != null) {
                // do the date formatting
                getPreviousOut().print(df.format(df.parse(value.trim())));
            } else {
                // just return the value
                getPreviousOut().print(value);
            }
        } catch (Exception e) {
            if (UtilJ2eeCompat.useNestedJspException(pageContext.getServletContext())) {
                throw new JspException(e.getMessage(), e);
            } else {
                Debug.logError(e, "Server does not support nested exceptions, here is the exception", module);
                throw new JspException(e.toString());
            }
        }

        return SKIP_BODY;
    }

}

