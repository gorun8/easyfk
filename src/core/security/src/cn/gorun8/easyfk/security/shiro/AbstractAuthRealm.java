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

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import cn.gorun8.easyfk.entity.GenericValue;


/**
 * 实现身份认证的基础功能
 * @author:hezhiping(110476592@qq.com)
 *
 */
public abstract class AbstractAuthRealm extends AuthorizingRealm{
	/**
	 * 默认最大失败次数
	 */
	protected final int DEFAULT_MAX_FAIL_TIMES = 3;
 
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
//	   String	loginId =((SysUserMember) arg0.asList().get(0)).getId();
//       SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
//       List<String> idLi = new ArrayList<String>();
//    
//       for(Map<String, String> map :sysUserDao.queryRoleFromAuthorizationInfo(loginId)){
//    	   if( sazi.getRoles()==null || (!map.get("roleName").toString().equals("") && !sazi.getRoles().contains(map.get("roleName")))){
//    		   sazi.addRole(map.get("roleName"));
//    	   }
//    	   if(!idLi.contains(map.get("roleId"))){
//    		   idLi.add(map.get("roleId"));
//    	   }
//       }
//       
//       sazi.addStringPermissions(sysResourceRepository.findByUserId(loginId));
//       if(idLi!=null && idLi.size()>0){
//    	   sazi.addStringPermissions(sysResourceRepository.findByRoleIds(idLi));
//    	   sazi.addStringPermissions(this.queryParentResOfRoles(new ArrayList<String>(), idLi));
//       }
       return null;//sazi;
	}
	public List<String> queryParentResOfRoles(List<String> strngli,List<String> idLi){
//		List<String>  parentRoleIdLi =	sysRoleRelationRepository.findParentIdOfRoleId(idLi);
//		if(parentRoleIdLi!=null && parentRoleIdLi.size()>0){
//			strngli.addAll(sysResourceRepository.findByRoleIds(parentRoleIdLi));
//			strngli=this.queryParentResOfRoles(strngli,parentRoleIdLi);
//		}
		return null;//strngli;
	}
	
	 
	 
	/**
	 * 清楚授权信息
	 */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }
    /**
     * 清楚认证信息
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }
    /**
     * 清楚登陆缓存
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
    /**
     * 清楚所有授权信息
     */
    public void clearAllCachedAuthorizationInfo() {
        if(getAuthorizationCache() != null){
        	getAuthorizationCache().clear();
        	
        }
    }
    /**
     * 清楚所有认证信息
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }
    /**
     * 清楚所有缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
   
}  
