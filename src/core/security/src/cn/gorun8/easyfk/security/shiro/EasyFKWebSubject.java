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
 * Author:hezhiping   Email:110476592@qq.com   Date: 15-11-12
 */
package cn.gorun8.easyfk.security.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EasyFKWebSubject extends WebDelegatingSubject {

    public EasyFKWebSubject(PrincipalCollection principals, boolean authenticated,
                            String host, Session session,
                            ServletRequest request, ServletResponse response,
                            org.apache.shiro.mgt.SecurityManager securityManager) {
        super(principals, authenticated, host, session, true, request, response, securityManager);
    }

    //since 1.2
    public EasyFKWebSubject(PrincipalCollection principals, boolean authenticated,
                            String host, Session session, boolean sessionEnabled,
                            ServletRequest request, ServletResponse response,
                            SecurityManager securityManager) {
        super(principals, authenticated, host, session, sessionEnabled,request,response, securityManager);
    }


    /**
     * 表示用户进行了身份验证登录的，即使有Subject.login进行了登录；
     * @return
     */
    public boolean isAuthenticated(){
        boolean rel =  super.isAuthenticated();
//        if(!rel ){
//            HttpServletRequest request = (HttpServletRequest)this.getServletRequest();
//            Cookie ck = UtilSecurity.getCookie(request, "uid");
//            if(ck != null) {
//                String userLoginId = ck.getValue();
//                if(UtilValidate.isNotEmpty(userLoginId)) {
//                    List<GenericValue> li = new ArrayList<GenericValue>();
//                    LoginService loginService = UtilIOC.getBean(cn.gorun8.easyfk.security.service.impl.LoginServiceImpl.class);
//                    GenericValue userLogin = loginService.findUserLogin(userLoginId);
//                    if(UtilValidate.isNotEmpty(userLogin)) {
//                        li.add(userLogin);
//                        PrincipalCollection principals = new SimplePrincipalCollection(li, "reaml");
//                        this.principals = principals;
//                        this.authenticated = true;
//                        rel = true;
//                    }
//                }
//            }
//        }

        return rel ;
    }

    /**
     * 表示用户是通过记住我登录的，此时可能并不是真正的你（如你
     *的朋友使用你的电脑，或者你的cookie 被窃取）在访问的；
     *isAuthenticated 和isRemembered且两者二选一，即
     * subject.isAuthenticated()==true，则subject.isRemembered()==false；
     * 反之一样。
     * @return
     */
    public boolean isRemembered(){
        return super.isRemembered();
    }

    public  void logout(){
        super.logout();
        //UtilSecurity.setCookie("uid","","/");
    }

}
