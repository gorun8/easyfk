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
package cn.gorun8.easyfk.service.service;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.GeneralException;
import cn.gorun8.easyfk.base.util.UtilMessages;
import cn.gorun8.easyfk.base.util.UtilMisc;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * General Service Exception - base Exception for in-Service Errors
 */

public class GeneralServiceException extends GeneralException {

    protected List<Object> errorMsgList = null;
    protected Map<String, ? extends Object> errorMsgMap = null;
    protected Map<String, ? extends Object> nestedServiceResult = null;

    public GeneralServiceException() {
        super();
    }

    public GeneralServiceException(String str) {
        super(str);
    }

    public GeneralServiceException(String str, Throwable nested) {
        super(str, nested);
    }

    public GeneralServiceException(Throwable nested) {
        super(nested);
    }

    public GeneralServiceException(String str, List<? extends Object> errorMsgList, Map<String, ? extends Object> errorMsgMap, Map<String, ? extends Object> nestedServiceResult, Throwable nested) {
        super(str, nested);
        this.errorMsgList = UtilMisc.makeListWritable(errorMsgList);
        this.errorMsgMap = errorMsgMap;
        this.nestedServiceResult = nestedServiceResult;
    }

    public Map<String, Object> returnError(String module) {
        String errMsg = this.getMessage() == null ? "Error in Service" : this.getMessage();
        if (this.getNested() != null) {
            Debug.logError(this.getNested(), errMsg, module);
        }
        return UtilMessages.returnError(errMsg, this.errorMsgList, this.errorMsgMap, this.nestedServiceResult);
    }

    public void addErrorMessages(List<? extends Object> errMsgs) {
        if (this.errorMsgList == null) {
            this.errorMsgList = new LinkedList<Object>();
        }
        this.errorMsgList.addAll(errMsgs);
    }
}
