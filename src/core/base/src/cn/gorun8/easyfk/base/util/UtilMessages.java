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
package cn.gorun8.easyfk.base.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javolution.util.FastList;
import net.sf.json.JSONObject;

public class UtilMessages {
    /**
     * 响应类型，一般用于ajax请求中作判断
     */
    public static final String RESPONSE_TYPE = "responseType";
    /**
     * 成功响应
     */
    public static final String RESPOND_SUCCESS = "success";
    /**
     * 失败响应
     */
    public static final String RESPOND_ERROR = "error";

    /**
     * 错误消息
     */
    public static final String ERROR_MESSAGE_LIST = "errorMessageList";
    /**
     * 提示消息
     */
    public static final String EVENT_MESSAGE_LIST = "eventMessageList";

    /**
     * 保存提示消息
     *
     * @param request
     * @param msg
     */
    public static void saveMessages(HttpServletRequest request, String msg) {
//        List<String> msgList = UtilGenerics.toList(request.getAttribute(EVENT_MESSAGE_LIST));
//        if (msgList == null) {
//            msgList = FastList.newInstance();
//            request.setAttribute(EVENT_MESSAGE_LIST, msgList);
//        }
//
//        msgList.add(msg);
        request.setAttribute(EVENT_MESSAGE_LIST, msg);
    }

    /**
     * 保存错误消息
     * @param request
     * @param error
     */
    public static void saveErrors(HttpServletRequest request, String error) {
//        List<String> errorList = UtilGenerics.toList(request.getAttribute(ERROR_MESSAGE_LIST));
//        if (errorList == null) {
//            errorList = FastList.newInstance();
//            request.setAttribute(ERROR_MESSAGE_LIST, errorList);
//        }
//        errorList.add(error);
        request.setAttribute(ERROR_MESSAGE_LIST, error);
    }

    /**
     * JSON格式的成功响应
     * @param request
     * @return
     */
    public static String successResponse(HttpServletRequest request){
        Map<String, Object> attrMap = UtilHttp.getJSONAttributeMap(request, "eventMessageList");
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
        return successResponse(request);
    }

    /**
     * JSON格式的出错响应
     * @param request
     * @return
     */
    public static String errorResponse(HttpServletRequest request){
        Map<String, Object> attrMap = UtilHttp.getJSONAttributeMap(request,"errorMessageList");
        JSONObject json = JSONObject.fromObject(attrMap);
        json.put(RESPONSE_TYPE,RESPOND_SUCCESS);
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
     * 将当前请求中的成功消息和错误消息转换为JSON.
     * @param request
     * @return
     */
    public static String toJSonString(HttpServletRequest request){
        Map<String, Object> attrMap = UtilHttp.getJSONAttributeMap(request,"errorMessageList,eventMessageList");
        JSONObject json = JSONObject.fromObject(attrMap);
        return json.toString();
    }

}
