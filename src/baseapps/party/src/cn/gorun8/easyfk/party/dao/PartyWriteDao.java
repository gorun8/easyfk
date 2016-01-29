package cn.gorun8.easyfk.party.dao;
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
import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilIOC;
import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.dao.WriteDaoBase;
import cn.gorun8.easyfk.entity.datasource.ReadSqlSessionTemplate;
import cn.gorun8.easyfk.entity.datasource.WriteSqlSessionTemplate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hezhiping(110476592@qq.com) on 15-10-19.
 */

public abstract class PartyWriteDao  extends WriteDaoBase{
    private static final String module = PartyWriteDao.class.getName();

    public  PartyWriteDao(){
        super("cn.gorun8.easyfk.party.dao.PartyWriteDao");
    }

    public WriteSqlSessionTemplate getSqlSessionTemplate(){
        WriteSqlSessionTemplate writeSqlSessionTemplate = UtilIOC.getBean("partyWriteSqlSession");
        if(writeSqlSessionTemplate == null){
            Debug.logError("partyWriteSqlSession not found", module);
            return null;
        }
        return writeSqlSessionTemplate;

    }

    /**
     * 创建Party
     * @param party
     */
    public abstract void  createParty(@Param("party") GenericValue party) throws GenericEntityException ;

    /**
     * 更新Party
     * @param party
     */
    public abstract  void  saveParty(@Param("party") GenericValue party) throws GenericEntityException;



    /**
     * 创建会员状态
     * @param partyStatus
     */
    public abstract void  createPartyStatus(@Param("partyStatus") GenericValue partyStatus) throws GenericEntityException ;

    /**
     * 更新会员状态
     * @param partyStatus
     */
    public abstract void  savePartyStatus(@Param("partyStatus") GenericValue partyStatus) throws GenericEntityException ;


    /**
     * 创建Person
     * @param person
     */
    public abstract void  createPerson(@Param("person") GenericValue person) throws GenericEntityException ;


    /**
     * 更新会员状态
     * @param person
     */
    public abstract void  savePerson(@Param("person") GenericValue person) throws GenericEntityException ;



    /**
     * 创建会员组
     * @param partyGroup
     */
    public abstract void createPartyGroup(@Param("partyGroup") GenericValue partyGroup)throws GenericEntityException ;

    /**
     * 更新会员组
     * @param partyGroup
     */
    public abstract void savePartyGroup(@Param("partyGroup") GenericValue partyGroup)throws GenericEntityException ;

    /**
     * 删除会员组
     * @param partyId
     */
    public abstract void removePartyGroupByPrimaryKey(@Param("partyId") String partyId)throws GenericEntityException ;


    /**
     * 创建会员与角色关联
     * @param partyRole
     */
    public abstract void  createPartyRole(@Param("partyRole") GenericValue partyRole) throws GenericEntityException ;


    /**
     * 根据partyId和roleTypeId删除会员与角色关联
     * @param partyRole
     */
    public abstract void  removePartyRole(@Param("partyRole") GenericValue partyRole) throws GenericEntityException ;

}
