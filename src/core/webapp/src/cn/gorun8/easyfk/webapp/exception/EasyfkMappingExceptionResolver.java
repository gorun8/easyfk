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
package cn.gorun8.easyfk.webapp.exception;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilIO;
import cn.gorun8.easyfk.base.util.UtilMessages;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class EasyfkMappingExceptionResolver extends  org.springframework.web.servlet.handler.SimpleMappingExceptionResolver{

    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Debug.logError(ex,"");

        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            StringBuilder exStackBuffer = new StringBuilder();
            exStackBuffer.append(ex.toString());

            StackTraceElement [] ste =  ex.getStackTrace();
            int i =0 ;
            for (StackTraceElement e : ste){
                if(i >1){
                    break;
                }
                ++i;
                exStackBuffer.append(", class:");
                exStackBuffer.append(e.getClassName());
                exStackBuffer.append(", line:");
                exStackBuffer.append(e.getLineNumber());
                exStackBuffer.append(", method:");
                exStackBuffer.append(e.getMethodName());
            }

            JSONObject json = new JSONObject();
            json.put(UtilMessages.ERROR_MESSAGE, exStackBuffer.toString());
            json.put(UtilMessages.RESPONSE_TYPE,UtilMessages.RESPOND_ERROR);
            String jst =json.toString();
            try {
                OutputStream out = response.getOutputStream();
                UtilIO.writeString(out,jst);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        return super.doResolveException(request, response, handler, ex);

    }
}
