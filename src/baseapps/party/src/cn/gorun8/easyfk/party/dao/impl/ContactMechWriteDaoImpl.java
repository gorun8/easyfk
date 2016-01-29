package cn.gorun8.easyfk.party.dao.impl;
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
import cn.gorun8.easyfk.entity.datasource.WriteSqlSessionTemplate;
import cn.gorun8.easyfk.party.dao.ContactMechWriteDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by hezhiping(110476592@qq.com) on 15-10-19.
 */
@Repository
public   class ContactMechWriteDaoImpl extends ContactMechWriteDao {
    private static final String module = ContactMechWriteDaoImpl.class.getName();
 
    /**
     * 创建
     * @param contactMech
     */
    public  void  createContactMech(@Param("contactMech") GenericValue contactMech) throws GenericEntityException {

        this.create("createContactMech",contactMech);
    }

    /**
     * 创建
     * @param contactMech
     */
    public   void  createPartyContactMech(@Param("contactMech") GenericValue contactMech) throws GenericEntityException{
        this.create("createPartyContactMech",contactMech);
    }



    /**
     * 更新
     * @param contactMech
     */
    public  void  updateContactMech(@Param("contactMech") GenericValue contactMech) throws GenericEntityException {
        this.store("updateContactMech", contactMech);
    }

    /**
     * 更新会员
     * @param contactMech
     */
    public  void  updatePartyContactMech(@Param("contactMech") GenericValue contactMech) throws GenericEntityException {
        this.store("updatePartyContactMech", contactMech);
    }

    /**
     * 创建邮件地址
     * @param contactMech
     */
    public  void  createPostalAddress(@Param("contactMech") GenericValue contactMech) throws GenericEntityException {
        this.create("createPostalAddress",contactMech);
    }

    /**
     * 创建会员邮政地址
     * @param contactMech
     */
    public  void createPartyPostalAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException {
        this.create("createPartyPostalAddress",contactMech);
    }

    /**
     * 更新邮政地址
     * @param contactMech
     */
    public  void updatePostalAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException {
        this.store("updatePostalAddress", contactMech);
    }

    /**
     * 更新会员邮政地址
     * @param contactMech
     */
    public  void updatePartyPostalAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException {
        this.store("updatePartyPostalAddress", contactMech);
    }


    /**
     * 创建电话号码
     * @param contactMech
     * @throws cn.gorun8.easyfk.entity.GenericEntityException
     */
    public  void  createTelecomNumber(@Param("contactMech") GenericValue contactMech)throws GenericEntityException {
        this.create("createTelecomNumber",contactMech);
    }

    /**
     * 创建会员电话号码
     * @param contactMech
     * @throws cn.gorun8.easyfk.entity.GenericEntityException
     */
    public  void  createPartyTelecomNumber(@Param("contactMech") GenericValue contactMech)throws GenericEntityException {
        this.create("createPartyTelecomNumber",contactMech);
    }

    /**
     * 更新电话号码
     * @param contactMech
     * @throws cn.gorun8.easyfk.entity.GenericEntityException
     */
    public  void  updateTelecomNumber(@Param("contactMech") GenericValue contactMech)throws GenericEntityException {
        this.store("updateTelecomNumber", contactMech);
    }

    /**
     * 更新会员电话号码
     * @param contactMech
     * @throws cn.gorun8.easyfk.entity.GenericEntityException
     */
    public  void  updatePartyTelecomNumber(@Param("contactMech") GenericValue contactMech)throws GenericEntityException {
        this.store("updatePartyTelecomNumber", contactMech);
    }

    /**
     * 创建电子邮件
     * @param contactMech
     * @throws cn.gorun8.easyfk.entity.GenericEntityException
     */
    public  void  createEmailAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException {
        this.create("createEmailAddress", contactMech);
    }

    /**
     * 创建员会电子邮件
     * @param contactMech
     * @throws cn.gorun8.easyfk.entity.GenericEntityException
     */
    public  void  createPartyEmailAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException {
        this.create("createPartyEmailAddress", contactMech);
    }

    /**
     * 更新电子邮件
     * @param contactMech
     * @throws cn.gorun8.easyfk.entity.GenericEntityException
     */
    public  void  updateEmailAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException {
        this.store("updateEmailAddress", contactMech);
    }

    /**
     * 更新会员电子邮件
     * @param contactMech
     * @throws cn.gorun8.easyfk.entity.GenericEntityException
     */
    public  void  updatePartyEmailAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException {
        this.store("updatePartyEmailAddress", contactMech);
    }

}
