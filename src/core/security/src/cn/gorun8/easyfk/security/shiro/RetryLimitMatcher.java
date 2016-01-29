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
package cn.gorun8.easyfk.security.shiro;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.security.utils.UtilCaptcha;
import javolution.util.FastMap;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MapCache;
import org.apache.shiro.session.Session;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.entity.GenericValue;

 
public abstract class RetryLimitMatcher extends HashedCredentialsMatcher {

	public static final String module = RetryLimitMatcher.class.getName();
    
	/**
	 * 默认最大失败次数
	 */
	protected final int DEFAULT_MAX_FAIL_TIMES = 3;
    private Cache<String, AtomicInteger> loginRetryCache;

    public RetryLimitMatcher(CacheManager cacheManager) {
    	loginRetryCache = cacheManager.getCache("loginRetryCache");
    }
    
    public RetryLimitMatcher() {
    	Map <String, AtomicInteger> map = FastMap.newInstance();
    	loginRetryCache =  new MapCache("loginRetryCache",map);
    }
    
    
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    	
    	String username = (String)token.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount = loginRetryCache.get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            loginRetryCache.put(username, retryCount);
        }
        int max = getMaxFailTimes();
        int cur = retryCount.incrementAndGet();
        if(cur > max) {
            //if retry count >= max throw
            throw new ExcessiveAttemptsException(username);
        }
        
        boolean matches = false;
        try{
        	matches = checkCredentialsMatch(token, info,max - cur);
        }finally{
	        if(matches) {
	            //clear retry count
	        	loginRetryCache.remove(username);
	        }else{
	        	if(cur >= max) {
	                throw new ExcessiveAttemptsException(username);
	            }
	        }
        }
        
        
        return matches;
    }
    
    /**
     * 获取口令方式登录系统失败最大次数
     * @return
     */
    private int getMaxFailTimes()
    {
    	 // if failed logins over amount in properties file, disable account
        String mflStr = UtilProperties.getPropertyValue("security.properties", "max.failed.logins");
        int maxFailedLogins = DEFAULT_MAX_FAIL_TIMES;
        try {
            maxFailedLogins = Integer.parseInt(mflStr);
        } catch (Exception e) {
            Debug.logWarning("Could not parse max.failed.logins from security.properties, using default of 3", module);
        }
		
		 return maxFailedLogins;
    }
    
    public abstract boolean checkCredentialsMatch(AuthenticationToken token, AuthenticationInfo info,int remts);
    
    /**
	 * 判断验证码
	 * @param captcha
	 */
	protected void verifyCaptcha(String captcha,String captchaId,int remts){
		// 判断验证码
        if(UtilValidate.isEmpty(captchaId)){
            return ;
        }

		Session session =SecurityUtils.getSubject().getSession();
        if(!UtilCaptcha.verifyCaptch(captchaId,session,captcha))
        {
            throw new IncorrectCredentialsException("验证码错误！再错"+remts+"次,账号将被锁定");
        }
	}
	
	protected void checkStatus(GenericValue user)
	{
        String enabled = user.getString("enabled");
        if("N".equals(enabled)){
            throw new LockedAccountException("账号已经被停用,请联系管理员解锁");
        }
 	}
}
