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
import cn.gorun8.easyfk.entity.datasource.WriteSqlSessionTemplate;
import org.apache.ibatis.annotations.Param;

/**
 * Created by hezhiping(110476592@qq.com) on 15-10-19.
 */

public abstract class ContactMechWriteDao extends WriteDaoBase{
    private static final String module = ContactMechWriteDao.class.getName();

    public ContactMechWriteDao(){
        super("cn.gorun8.easyfk.party.dao.ContactMechWriteDao");
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
     * 创建
     * @param contactMech
     */
    public abstract void  createContactMech(@Param("contactMech") GenericValue contactMech) throws GenericEntityException ;

    /**
     * 创建
     * @param contactMech
     */
    public abstract  void  createPartyContactMech(@Param("contactMech") GenericValue contactMech) throws GenericEntityException;



    /**
     * 更新
     * @param contactMech
     */
    public abstract void  updateContactMech(@Param("contactMech") GenericValue contactMech) throws GenericEntityException ;

    /**
     * 更新会员
     * @param contactMech
     */
    public abstract void  updatePartyContactMech(@Param("contactMech") GenericValue contactMech) throws GenericEntityException ;


    /**
     * 创建邮件地址
     * @param contactMech
     */
    public abstract void  createPostalAddress(@Param("contactMech") GenericValue contactMech) throws GenericEntityException ;



    /**
     * 创建会员邮政地址
     * @param contactMech
     */
    public abstract void createPartyPostalAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException ;

    /**
     * 更新邮政地址
     * @param contactMech
     */
    public abstract void updatePostalAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException ;

    /**
     * 更新会员邮政地址
     * @param contactMech
     */
    public abstract void updatePartyPostalAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException ;


    /**
     * 创建电话号码
     * @param contactMech
     * @throws GenericEntityException
     */
    public abstract void  createTelecomNumber(@Param("contactMech") GenericValue contactMech)throws GenericEntityException ;

    /**
     * 创建会员电话号码
     * @param contactMech
     * @throws GenericEntityException
     */
    public abstract void  createPartyTelecomNumber(@Param("contactMech") GenericValue contactMech)throws GenericEntityException ;

    /**
     * 更新电话号码
     * @param contactMech
     * @throws GenericEntityException
     */
    public abstract void  updateTelecomNumber(@Param("contactMech") GenericValue contactMech)throws GenericEntityException ;

    /**
     * 更新会员电话号码
     * @param contactMech
     * @throws GenericEntityException
     */
    public abstract void  updatePartyTelecomNumber(@Param("contactMech") GenericValue contactMech)throws GenericEntityException ;

    /**
     * 创建电子邮件
     * @param contactMech
     * @throws GenericEntityException
     */
    public abstract void  createEmailAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException ;

    /**
     * 创建员会电子邮件
     * @param contactMech
     * @throws GenericEntityException
     */
    public abstract void  createPartyEmailAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException ;

    /**
     * 更新电子邮件
     * @param contactMech
     * @throws GenericEntityException
     */
    public abstract void  updateEmailAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException ;

    /**
     * 更新会员电子邮件
     * @param contactMech
     * @throws GenericEntityException
     */
    public abstract void  updatePartyEmailAddress(@Param("contactMech") GenericValue contactMech)throws GenericEntityException ;


}
