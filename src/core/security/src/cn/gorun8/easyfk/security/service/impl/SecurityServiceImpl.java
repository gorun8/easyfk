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
package cn.gorun8.easyfk.security.service.impl;

import cn.gorun8.easyfk.base.util.UtilMessages;
import cn.gorun8.easyfk.base.util.UtilProperties;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.util.UtilEntity;
import cn.gorun8.easyfk.security.dao.SecurityReadDao;
import cn.gorun8.easyfk.security.dao.SecurityWriteDao;
import cn.gorun8.easyfk.security.service.SecurityService;
import javolution.util.FastList;
import javolution.util.FastMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 授权相关的安全组的功能
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    public static final String module = SecurityServiceImpl.class.getName();
    public static final String resource = "SecurityUiLabels";
    public static final String resourceError = "SecurityErrorUiLabels";
    @Autowired
    SecurityWriteDao securityWriteDao;
    @Autowired
    SecurityReadDao securityReadDao;
    /**
     * 根据groupId查找安全组
     *
     * @param context
     * @return
     */
    public Map<String, Object> findSecurityGroup(Map<String, ? extends Object> context) {

        Locale locale = (Locale) context.get("locale");
        GenericValue param = new GenericValue();
        param.setNonPKFields(context);

        try {
            List<GenericValue> list = securityReadDao.findSecurityGroup(param);
            List<Map> mapList = UtilEntity.toMap(list);
            return  UtilMessages.returnSuccessWithData(mapList);
        }catch(Exception e){
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "securitygroup.find.error", locale));
        }
    }

    /**
     * 检测安全组是否存在
     *
     * @param context
     * @return
     */
    public Map<String, Object> checkSecurityGroupExist(Map<String, ? extends Object> context) {

        String parentId =(String)context.get("parentId");
        Locale locale = (Locale) context.get("locale");

        try {
            GenericValue param = GenericValue.fromMap(context,false);
            List<GenericValue> list = securityReadDao.findSecurityGroup(param);
            Boolean bb = Boolean.valueOf(list != null && list.size() >0 );
            return UtilMessages.returnSuccessWithData(bb);
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "securitygroup.find.error", locale));
        }


    }

    /**
     * 新建安全组
     *
     * @param context
     * @return
     */
    public Map<String, Object> createSecurityGroup(Map<String, ? extends Object> context) {
        Locale locale = (Locale) context.get("locale");
        String name = (String) context.get("name");

        if(UtilValidate.isEmpty(name)){
            return UtilMessages.returnParamError(locale, "name");
        }

        try {
            GenericValue param = new GenericValue();
            param.setString("name",name);
            List<GenericValue> list = securityReadDao.findSecurityGroup(param);
            if(list != null && list.size()> 0){
                return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                        "securitygroup.exist", locale));
            }

            GenericValue securityGroup = GenericValue.fromMap(context,true);
            String id = securityGroup.newPrimaryKey("SecurityGroup", "groupId");
            securityWriteDao.createSecurityGroup(securityGroup);
            return  UtilMessages.returnSuccessWithData(id);

        } catch (GenericEntityException e) {
            e.printStackTrace();
        }

        return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                "securitygroup.create.error", locale));
    }

    /**
     * 删除安全组
     *
     * @param context
     * @return
     */

    public Map<String, Object> removeSecurityGroup(Map<String, ? extends Object> context) {
        Locale locale = (Locale) context.get("locale");
        String groupId = (String)context.get("groupId");

        if(UtilValidate.isEmpty(groupId)){
            return UtilMessages.returnParamError(locale, "groupId");
        }

        try {
            GenericValue param = new GenericValue();
            param.setString("groupId",groupId);
            List<GenericValue> list = securityReadDao.findUserLoginSecurityGroup(param);
            if(list != null && list.size() > 0){
                return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                        "securitygroup.remove.existuserlogin", locale));
            }

            securityWriteDao.removeGroupPermission(param);
            securityWriteDao.removeSecurityGroup(param);
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "securitygroup.remove.error", locale));
        }

        return  UtilMessages.returnSuccess();
    }

    /**
     * 保存安全组
     *
     * @param context
     */
    public Map<String, Object> saveSecurityGroup(Map<String, ? extends Object> context) {
        Locale locale = (Locale) context.get("locale");
        String groupId = (String) context.get("groupId");

        if(UtilValidate.isEmpty(groupId)){
            return UtilMessages.returnParamError(locale, "groupId");
        }

        try {
            GenericValue securityGroup = GenericValue.fromMap(context,false);
            securityWriteDao.saveSecurityGroup(securityGroup);
            return  UtilMessages.returnSuccess();
        } catch (GenericEntityException e) {
            e.printStackTrace();
        }

        return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                "securitygroup.save.error", locale));
    }


    public Map<String,  Object> getPermissionList(Map<String, ? extends Object> context){
        Locale locale = (Locale) context.get("locale");

        String parentId = "_NA_";
        List<GenericValue> listRoot = FastList.newInstance();
        try {
            getPermissions(listRoot,parentId);
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "security.permission.find.error", locale));
        }

        return UtilMessages.returnSuccessWithData(UtilEntity.toMap(listRoot));
    }

    private boolean getPermissions(List<GenericValue> dataList,String parentId) throws  GenericEntityException{
        GenericValue param = new GenericValue();
        param.setString("parentId", parentId);

        List<GenericValue> permissionList = securityReadDao.findSecurityPermission(param);
        if(permissionList == null || permissionList.size() <=0){
            return false;
        }

        String pid ="";
        for (GenericValue it:permissionList){
            dataList.add(it);
            pid= it.getString("permissionId");
            boolean b =  getPermissions(dataList,pid);
            it.setString("hasSub",String.valueOf(b));
        }
        return true;
    }

    public Map<String,  Object> getGroupAndPermission(Map<String, ? extends Object> context){
        String groupId =(String)context.get("groupId");
        Locale locale = (Locale) context.get("locale");

        if(UtilValidate.isEmpty(groupId)){
            return UtilMessages.returnParamError(locale, "groupId");
        }

        try {
            GenericValue param = GenericValue.fromMap(context,false);
            List<GenericValue> list =  securityReadDao.findGroupAndPermission(param);
            List<Map> mapList = UtilEntity.toMap(list);
            return  UtilMessages.returnSuccessWithData(mapList);

        } catch (GenericEntityException e) {
            e.printStackTrace();
        }
        return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                "security.permission.find.error", locale));
    }

    @Override
    //@Transactional("partyWriteDataSourceTx")
    public Map<String, Object> setGroupPermission(Map<String, ? extends Object> context) {
        Locale locale = (Locale) context.get("locale");
        String groupId = (String) context.get("groupId");

        if(UtilValidate.isEmpty(groupId)){
            return UtilMessages.returnParamError(locale, "groupId");
        }

        try {
            GenericValue param = new GenericValue(false);
            param.setString("groupId",groupId);
            securityWriteDao.removeGroupPermission(param);

            FastList selectedPermissions = (FastList) context.get("selectedPermissions[]");
            if(UtilValidate.isEmpty(selectedPermissions)){
                selectedPermissions = (FastList) context.get("selectedPermissions");
                if(UtilValidate.isEmpty(selectedPermissions)){
                    return  UtilMessages.returnSuccess();
                }
            }

            for(Object id :selectedPermissions) {
                param.setString("permissionId",id);
                securityWriteDao.createGroupPermission(param);
            }

            return  UtilMessages.returnSuccess();
        } catch (GenericEntityException e) {
            e.printStackTrace();
        }

        return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                "security.permission.create.error", locale));
    }

    @Override
    public Map<String, Object> removeGroupPermission(Map<String, ? extends Object> context) {
        Locale locale = (Locale) context.get("locale");
        String groupId = (String)context.get("groupId");

        if(UtilValidate.isEmpty(groupId)){
            return UtilMessages.returnParamError(locale, "groupId");
        }

        try {
            GenericValue param = new GenericValue();
            param.setString("groupId",groupId);
            securityWriteDao.removeSecurityGroup(param);
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "securitygroup.remove.error", locale));
        }
        return  UtilMessages.returnSuccess();
    }


    /**
     * 获取角色类型列表
     * @return
     */
    public Map<String,  Object> findRoleTypes(Map<String, ? extends Object> context){
        Locale locale = (Locale) context.get("locale");
        try {
            List<GenericValue> list =  securityReadDao.findRoleTypes();
            List<Map> mapList = UtilEntity.toMap(list);
            return  UtilMessages.returnSuccessWithData(mapList);
        } catch (GenericEntityException e) {
            e.printStackTrace();
        }
        return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                "role.type.find.error", locale));
    }

    /**
     * 获取角色类型找出与它的上级角色类型列表
     * @return
     */
    public Map<String,  Object> findParentRoleTypes(Map<String, ? extends Object> context){
        String roleTypeId =(String)context.get("roleTypeId");
        Locale locale = (Locale) context.get("locale");

        if(UtilValidate.isEmpty(roleTypeId)){
            return UtilMessages.returnParamError(locale, "roleTypeId");
        }

        try {
            GenericValue param = GenericValue.fromMap(context,false);
            List<GenericValue> list =  securityReadDao.findParentRoleTypes(param);
            List<Map> mapList = UtilEntity.toMap(list);
            return  UtilMessages.returnSuccessWithData(mapList);

        } catch (GenericEntityException e) {
            e.printStackTrace();
        }
        return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                "role.type.find.error", locale));
    }

    @Override
    public Map<String,  Object> findUserLoginPermissions(Map<String, ? extends Object> context){
        Locale locale = (Locale) context.get("locale");
        String userLoginId =(String)context.get("userLoginId");

        if(UtilValidate.isEmpty(userLoginId)){
            GenericValue userLogin = (GenericValue) context.get("userLogin");
            userLoginId = userLogin.getString("userLoginId");
        }

        if(UtilValidate.isEmpty(userLoginId)){
            return UtilMessages.returnParamError(locale, "userLoginId");
        }

        try {
            GenericValue param = GenericValue.fromMap(context,false);
            param.setString("userLoginId",userLoginId);
            List<GenericValue> list =  securityReadDao.findUserLoginPermissions(param);
            List<Map> mapList = UtilEntity.toMap(list);
            return  UtilMessages.returnSuccessWithData(mapList);

        } catch (GenericEntityException e) {
            e.printStackTrace();
        }

        return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                "role.type.find.error", locale));
    }

    /**
     * 获取会员的角色
     */
    public Map<String,  Object> findPartyRoles(Map<String, ? extends Object> context){
        String partyId =(String)context.get("partyId");
        String tree =(String)context.get("tree");

        Locale locale = (Locale) context.get("locale");

        if(UtilValidate.isEmpty(partyId)){
            return UtilMessages.returnParamError(locale, "partyId");
        }

        try {
            GenericValue param = GenericValue.fromMap(context,false);
            List<GenericValue> roles =  securityReadDao.findPartyRoles(param);
            List<Map> rolesList = UtilEntity.toMap(roles);

            if(UtilValidate.isNotEmpty(tree)){
                findParentRoleTree(rolesList,roles);
            }

            return  UtilMessages.returnSuccessWithData(rolesList);

        } catch (GenericEntityException e) {
            e.printStackTrace();
        }
        return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                "role.type.find.error", locale));
    }

    /**
     * 读取继承而来的角色
     * @param rolesList
     * @param paramlist
     */
    private void findParentRoleTree(List<Map> rolesList,List<GenericValue> paramlist){
        for(GenericValue pm : paramlist){
            try {
                List<GenericValue>  gvList = securityReadDao.findParentRoleTypes(pm);
                if(gvList == null || gvList.size() <=0){
                    return;
                }

                for(GenericValue gv :gvList){
                    rolesList.add(gv.toMap());
                }

                findParentRoleTree(rolesList,gvList);
            } catch (GenericEntityException e) {
                e.printStackTrace();
            }
        }
    }

}
