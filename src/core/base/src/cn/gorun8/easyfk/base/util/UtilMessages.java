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

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javolution.util.FastList;
import javolution.util.FastMap;
import net.sf.json.JSONObject;

public class UtilMessages {
    public static final String module = UtilMessages.class.getName();
    public static final String resourceError = "CommonErrorUiLabels";

    /**
     * 响应类型 ,可以为RESPOND_SUCCESS ,RESPOND_ERROR,RESPOND_FAIL
     */
    public static final String RESPONSE_TYPE = "responseType";
    /**
     * 成功响应
     */
    public static final String RESPOND_SUCCESS = "success";
    /**
     * 错误响应
     */
    public static final String RESPOND_ERROR = "error";

    /**
     * 失败响应
     */
    public static final String RESPOND_FAIL = "fail";

    /**
     * 错误消息内容
     */
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String ERROR_MESSAGE_LIST = "errorMessageList";
    /**
     * 消息内容
     */
    public static final String EVENT_MESSAGE = "eventMessage";
    public static final String EVENT_MESSAGE_LIST = "eventMessageList";

    public static final String ERROR_MESSAGE_MAP = "errorMessageMap";
    /**
     * 响应数据
     */
    public static final String RESPONSE_DATA = "responseData";

    /**
     * 保存提示消息
     *
     * @param request
     * @param msg
     */
    public static void saveMessages(HttpServletRequest request, String msg) {
        List<String> msgList = UtilGenerics.toList(request.getAttribute(EVENT_MESSAGE_LIST));
        if (msgList == null) {
            msgList = FastList.newInstance();
            request.setAttribute(EVENT_MESSAGE_LIST, msgList);
        }
        msgList.add(msg);
        request.setAttribute(EVENT_MESSAGE_LIST, msgList);

    }

    /**
     * 保存错误消息
     * @param request
     * @param error
     */
    public static void saveErrors(HttpServletRequest request, String error) {
        List<String> errorList = UtilGenerics.toList(request.getAttribute(ERROR_MESSAGE_LIST));
        if (errorList == null) {
            errorList = FastList.newInstance();
            request.setAttribute(ERROR_MESSAGE_LIST, errorList);
        }
        errorList.add(error);
        request.setAttribute(ERROR_MESSAGE_LIST, errorList);
    }

    public static void saveErrors(HttpServletRequest request,  Map<String,Object> result){
        String error = UtilMessages.getErrorMessage(result);
        UtilMessages.saveErrors(request,error);
    }



    /**
     * JSON格式的成功响应
     * @param request
     * @return
     */
    public static String successResponse(HttpServletRequest request){
        return  successResponse(request,"");
    }

    /**
     * JSON格式的成功响应,同时将httpRequest的dataKeys属性转换为JSON
     * @param request
     * @return
     */
    public static String successResponse(String dataKeys,HttpServletRequest request){
        Map<String, Object> attrMap = UtilHttp.getJSONAttributeMap(request, "eventMessageList,"+dataKeys);
        JSONObject json = JSONObject.fromObject(attrMap);
        json.put(RESPONSE_TYPE, RESPOND_SUCCESS);
        return json.toString();
    }

    /**
     * JSON格式的成功响应
     * @param request
     * @param message
     * @return
     */
    public static String successResponse(HttpServletRequest request,String message){
        saveMessages(request,message);
        return successResponse("",request);
    }

    /**
     * JSON格式的出错响应
     * @param request
     * @return
     */
    public static String errorResponse(HttpServletRequest request){
        Map<String, Object> attrMap = UtilHttp.getJSONAttributeMap(request,ERROR_MESSAGE_LIST);
        JSONObject json = JSONObject.fromObject(attrMap);
        json.put(RESPONSE_TYPE,RESPOND_ERROR);
        return json.toString();
    }

    /**
     * JSON格式的出错响应
     * @param request
     * @return
     */
    public static String errorResponse(HttpServletRequest request,String error){
        saveErrors(request,error);
        return  errorResponse(request);
    }

    /**
     * JSON格式的出错响应
     * @param request
     * @return
     */
    public static String errorResponse(HttpServletRequest request,Map<String, ? extends Object> result){
        UtilMessages.getMessages(request, result, "");
        return  errorResponse(request);
    }

    /**
     * 将当前请求消息转换为JSON.
     * @param request
     * @return
     */
    public static String toJSonString(HttpServletRequest request,String allowKeys){
        Map<String, Object> attrMap = UtilHttp.getJSONAttributeMap(request,allowKeys);
        JSONObject json = JSONObject.fromObject(attrMap);
        return json.toString();
    }



    /** A little short-cut method to check to see if a service returned an error */
    public static boolean isError(Map<String, ? extends Object> results) {
        if (results == null || results.get(UtilMessages.RESPONSE_TYPE) == null) {
            return false;
        }
        return UtilMessages.RESPOND_ERROR.equals(results.get(UtilMessages.RESPONSE_TYPE));
    }

    public static boolean isFailure(Map<String, ? extends Object> results) {
        if (results == null || results.get(UtilMessages.RESPONSE_TYPE) == null) {
            return false;
        }
        return UtilMessages.RESPOND_FAIL.equals(results.get(UtilMessages.RESPONSE_TYPE));
    }

    /** A little short-cut method to check to see if a service was successful (neither error or failed) */
    public static boolean isSuccess(Map<String, ? extends Object> results) {
        if (isError(results) || isFailure(results)) {
            return false;
        }
        return true;
    }

    /** A small routine used all over to improve code efficiency, make a result map with the message and the error response code */
    public static Map<String, Object> returnError(String errorMessage) {
        return returnProblem(UtilMessages.RESPOND_ERROR, errorMessage, null, null, null);
    }

    public static Map<String, Object> returnParamError(Locale locale,String param) {
        List<String> params = FastList.newInstance();
        params.add(param);
        return returnParamError(locale,params);
    }

    public static Map<String, Object> returnParamError(Locale locale,List<String> params) {
        String errorMessage =  UtilProperties.getMessage(resourceError,
                "required_parameter_missing", locale);
        StringBuffer paraStr = new StringBuffer();
        paraStr.append(errorMessage);

        for(String p:params){
            paraStr.append(",");
            paraStr.append(p);
        }
        return returnProblem(UtilMessages.RESPOND_ERROR, paraStr.toString(), null, null, null);
    }

    /** A small routine used all over to improve code efficiency, make a result map with the message and the error response code */
    public static Map<String, Object> returnError(String errorMessage, List<? extends Object> errorMessageList) {
        return returnProblem(UtilMessages.RESPOND_ERROR, errorMessage, errorMessageList, null, null);
    }

    /** A small routine used all over to improve code efficiency, make a result map with the message and the error response code */
    public static Map<String, Object> returnError(List<? extends Object> errorMessageList) {
        return returnProblem(UtilMessages.RESPOND_ERROR, null, errorMessageList, null, null);
    }

    public static Map<String, Object> returnFailure(String errorMessage) {
        return returnProblem(UtilMessages.RESPOND_FAIL, errorMessage, null, null, null);
    }

    public static Map<String, Object> returnFailure(List<? extends Object> errorMessageList) {
        return returnProblem(UtilMessages.RESPOND_FAIL, null, errorMessageList, null, null);
    }

    public static Map<String, Object> returnFailure() {
        return returnProblem(UtilMessages.RESPOND_FAIL, null, null, null, null);
    }

    /** A small routine used all over to improve code efficiency, make a result map with the message and the error response code, also forwards any error messages from the nestedResult */
    public static Map<String, Object> returnError(String errorMessage, List<? extends Object> errorMessageList, Map<String, ? extends Object> errorMessageMap, Map<String, ? extends Object> nestedResult) {
        return returnProblem(UtilMessages.RESPOND_ERROR, errorMessage, errorMessageList, errorMessageMap, nestedResult);
    }



    public static Map<String, Object> returnProblem(String returnType, String errorMessage, List<? extends Object> errorMessageList, Map<String, ? extends Object> errorMessageMap, Map<String, ? extends Object> nestedResult) {
        Map<String, Object> result = FastMap.newInstance();
        result.put(UtilMessages.RESPONSE_TYPE, returnType);
        if (errorMessage != null) {
            result.put(UtilMessages.ERROR_MESSAGE, errorMessage);
        }

        List<Object> errorList = new LinkedList<Object>();
        if (errorMessageList != null) {
            errorList.addAll(errorMessageList);
        }

        Map<String, Object> errorMap = FastMap.newInstance();
        if (errorMessageMap != null) {
            errorMap.putAll(errorMessageMap);
        }

        if (nestedResult != null) {
            if (nestedResult.get(UtilMessages.ERROR_MESSAGE) != null) {
                errorList.add(nestedResult.get(UtilMessages.ERROR_MESSAGE));
            }
            if (nestedResult.get(UtilMessages.ERROR_MESSAGE_LIST) != null) {
                errorList.addAll(UtilGenerics.checkList(nestedResult.get(UtilMessages.ERROR_MESSAGE_LIST)));
            }
            if (nestedResult.get(UtilMessages.ERROR_MESSAGE_MAP) != null) {
                errorMap.putAll(UtilGenerics.<String, Object>checkMap(nestedResult.get(UtilMessages.ERROR_MESSAGE_MAP)));
            }
        }

        if (errorList.size() > 0) {
            result.put(UtilMessages.ERROR_MESSAGE_LIST, errorList);
        }
        if (errorMap.size() > 0) {
            result.put(UtilMessages.ERROR_MESSAGE_MAP, errorMap);
        }
        return result;
    }

    /** A small routine used all over to improve code efficiency, make a result map with the message and the success response code */
    public static Map<String, Object> returnSuccess(String successMessage) {
        return returnMessage(UtilMessages.RESPOND_SUCCESS, successMessage);
    }

    /** A small routine used all over to improve code efficiency, make a result map with the message and the success response code */
    public static Map<String, Object> returnSuccessWithData(Object data) {
        return returnSuccessWithData(UtilMessages.RESPONSE_DATA,data);
    }

    public static Map<String, Object> returnSuccessWithData(String key,Object data) {
        Map<String, Object>  relmap =  returnMessage(UtilMessages.RESPOND_SUCCESS, null);
        relmap.put(key,data);
        return relmap;
    }

    /** A small routine used all over to improve code efficiency, make a result map with the message and the success response code */
    public static Map<String, Object> returnSuccess() {
        return returnMessage(UtilMessages.RESPOND_SUCCESS, null);
    }

    /** A small routine used all over to improve code efficiency, make a result map with the message and the success response code */
    public static Map<String, Object> returnSuccess(List<String> successMessageList) {
        Map<String, Object> result = returnMessage(UtilMessages.RESPOND_SUCCESS, null);
        result.put(UtilMessages.EVENT_MESSAGE_LIST, successMessageList);
        return result;
    }

    /** A small routine to make a result map with the message and the response code
     * NOTE: This brings out some bad points to our message convention: we should be using a single message or message list
     *  and what type of message that is should be determined by the RESPONSE_MESSAGE (and there's another annoyance, it should be RESPONSE_CODE)
     */
    public static Map<String, Object> returnMessage(String code, String message) {
        Map<String, Object> result = FastMap.newInstance();
        if (code != null) result.put(UtilMessages.RESPONSE_TYPE, code);
        if (message != null) result.put(UtilMessages.EVENT_MESSAGE, message);
        return result;
    }


    public static void setMessages(HttpServletRequest request, String errorMessage, String eventMessage, String defaultMessage) {
        if (UtilValidate.isNotEmpty(errorMessage))
            request.setAttribute(ERROR_MESSAGE_LIST, errorMessage);

        if (UtilValidate.isNotEmpty(eventMessage))
            request.setAttribute(EVENT_MESSAGE_LIST, eventMessage);

        if (UtilValidate.isEmpty(errorMessage) && UtilValidate.isEmpty(eventMessage) && UtilValidate.isNotEmpty(defaultMessage))
            request.setAttribute(EVENT_MESSAGE_LIST, defaultMessage);

    }

    public static void getMessages(HttpServletRequest request, Map<String, ? extends Object> result, String defaultMessage) {
        getMessages(request, result, defaultMessage, null, null, null, null, null, null);
    }

    public static void getMessages(HttpServletRequest request, Map<String, ? extends Object> result, String defaultMessage,
                                   String msgPrefix, String msgSuffix, String errorPrefix, String errorSuffix, String successPrefix, String successSuffix) {
        String errorMessage = makeErrorMessage(result, msgPrefix, msgSuffix, errorPrefix, errorSuffix);
        String successMessage = makeSuccessMessage(result, msgPrefix, msgSuffix, successPrefix, successSuffix);
        setMessages(request, errorMessage, successMessage, defaultMessage);
    }

    public static String getErrorMessage(Map<String, ? extends Object> result) {
        StringBuilder errorMessage = new StringBuilder();

        if (result.get(UtilMessages.ERROR_MESSAGE) != null) errorMessage.append((String) result.get(UtilMessages.ERROR_MESSAGE));

        if (result.get(UtilMessages.ERROR_MESSAGE_LIST) != null) {
            List<? extends Object> errors = UtilGenerics.checkList(result.get(UtilMessages.ERROR_MESSAGE_LIST));
            for (Object message: errors) {
                // NOTE: this MUST use toString and not cast to String because it may be a MessageString object
                String curMessage = message.toString();
                if (errorMessage.length() > 0) {
                    errorMessage.append(", ");
                }
                errorMessage.append(curMessage);
            }
        }

        return errorMessage.toString();
    }

    public static String makeErrorMessage(Map<String, ? extends Object> result, String msgPrefix, String msgSuffix, String errorPrefix, String errorSuffix) {
        if (result == null) {
            Debug.logWarning("A null result map was passed", module);
            return null;
        }
        String errorMsg = (String) result.get(UtilMessages.ERROR_MESSAGE);
        List<? extends Object> errorMsgList = UtilGenerics.checkList(result.get(UtilMessages.ERROR_MESSAGE_LIST));
        Map<String, ? extends Object> errorMsgMap = UtilGenerics.checkMap(result.get(UtilMessages.ERROR_MESSAGE_MAP));
        StringBuilder outMsg = new StringBuilder();

        if (errorMsg != null) {
            if (msgPrefix != null) outMsg.append(msgPrefix);
            outMsg.append(errorMsg);
            if (msgSuffix != null) outMsg.append(msgSuffix);
        }

        outMsg.append(makeMessageList(errorMsgList, msgPrefix, msgSuffix));

        if (errorMsgMap != null) {
            for (Map.Entry<String, ? extends Object> entry: errorMsgMap.entrySet()) {
                outMsg.append(msgPrefix);
                outMsg.append(entry.getKey());
                outMsg.append(": ");
                outMsg.append(entry.getValue());
                outMsg.append(msgSuffix);
            }
        }

        if (outMsg.length() > 0) {
            StringBuilder strBuf = new StringBuilder();

            if (errorPrefix != null) strBuf.append(errorPrefix);
            strBuf.append(outMsg.toString());
            if (errorSuffix != null) strBuf.append(errorSuffix);
            return strBuf.toString();
        } else {
            return null;
        }
    }

    public static String makeSuccessMessage(Map<String, ? extends Object> result, String msgPrefix, String msgSuffix, String successPrefix, String successSuffix) {
        if (result == null) {
            return "";
        }
        String successMsg = (String) result.get(UtilMessages.EVENT_MESSAGE);
        List<? extends Object> successMsgList = UtilGenerics.checkList(result.get(UtilMessages.EVENT_MESSAGE_LIST));
        StringBuilder outMsg = new StringBuilder();

        outMsg.append(makeMessageList(successMsgList, msgPrefix, msgSuffix));

        if (successMsg != null) {
            if (msgPrefix != null) outMsg.append(msgPrefix);
            outMsg.append(successMsg);
            if (msgSuffix != null) outMsg.append(msgSuffix);
        }

        if (outMsg.length() > 0) {
            StringBuilder strBuf = new StringBuilder();
            if (successPrefix != null) strBuf.append(successPrefix);
            strBuf.append(outMsg.toString());
            if (successSuffix != null) strBuf.append(successSuffix);
            return strBuf.toString();
        } else {
            return null;
        }
    }

    public static String makeMessageList(List<? extends Object> msgList, String msgPrefix, String msgSuffix) {
        StringBuilder outMsg = new StringBuilder();
        if (UtilValidate.isNotEmpty(msgList)) {
            for (Object msg: msgList) {
                if (msg == null) continue;
                String curMsg = msg.toString();
                if (msgPrefix != null) outMsg.append(msgPrefix);
                outMsg.append(curMsg);
                if (msgSuffix != null) outMsg.append(msgSuffix);
            }
        }
        return outMsg.toString();
    }

    /**
     * Takes the result of an invocation and extracts any error messages
     * and adds them to the targetList or targetMap. This will handle both List and String
     * error messags.
     *
     * @param targetList    The List to add the error messages to
     * @param targetMap The Map to add any Map error messages to
     * @param callResult The result from an invocation
     */
    public static void addErrors(List<String> targetList, Map<String, Object> targetMap, Map<String, ? extends Object> callResult) {
        List<String> newList;
        Map<String, Object> errorMsgMap;

        //See if there is a single message
        if (callResult.containsKey(UtilMessages.ERROR_MESSAGE)) {
            targetList.add((String) callResult.get(UtilMessages.ERROR_MESSAGE));
        }

        //See if there is a message list
        if (callResult.containsKey(UtilMessages.ERROR_MESSAGE_LIST)) {
            newList = UtilGenerics.checkList(callResult.get(UtilMessages.ERROR_MESSAGE_LIST));
            targetList.addAll(newList);
        }

        //See if there are an error message map
        if (callResult.containsKey(UtilMessages.ERROR_MESSAGE_MAP)) {
            errorMsgMap = UtilGenerics.checkMap(callResult.get(UtilMessages.ERROR_MESSAGE_MAP));
            targetMap.putAll(errorMsgMap);
        }
    }

    private static Locale getLocale(Map<String, ? extends Object> context) {
        Locale locale = (Locale) context.get("locale");
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return locale;
    }

    public static <T extends Object> Map<String, Object> makeContext(T... args) {
        if (args != null) {
            for (int i = 0; i < args.length; i += 2) {
                if (!(args[i] instanceof String)) throw new IllegalArgumentException("Arg(" + i + "), value(" + args[i] + ") is not a string.");
            }
        }
        return UtilGenerics.checkMap(UtilMisc.toMap(args));
    }

}
