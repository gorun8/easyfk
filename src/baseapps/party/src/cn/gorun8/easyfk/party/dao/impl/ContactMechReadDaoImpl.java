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
import cn.gorun8.easyfk.entity.dao.ReadDaoBase;
import cn.gorun8.easyfk.entity.datasource.ReadSqlSessionTemplate;
import cn.gorun8.easyfk.party.dao.ContactMechReadDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hezhiping(110476592@qq.com) on 15-10-19.
 */
@Repository
public    class ContactMechReadDaoImpl extends ContactMechReadDao {
    private static final String module = ContactMechReadDaoImpl.class.getName();

    @Override
    public GenericValue  findContactMechById(@Param("contactMech") GenericValue contactMech) throws GenericEntityException{
        return this.findOne("findContactMechById",contactMech);
    }

    @Override
    public List<GenericValue> findPartyFromEmailAddress(@Param("contactMech") GenericValue contactMech) throws GenericEntityException {
       return this.findList("findPartyFromEmailAddress",contactMech);

    }

    @Override
    public List<GenericValue> findPartyFromTelephone(@Param("contactMech") GenericValue contactMech) throws GenericEntityException {
        return this.findList("findPartyFromTelephone",contactMech);

    }

    @Override
    public List<GenericValue> findPartyFromTelephoneComplete(@Param("contactMech") GenericValue contactMech) throws GenericEntityException {
        return this.findList("findPartyFromTelephoneComplete",contactMech);
    }

    @Override
    public List<GenericValue> findPartyContactMech(@Param("contactMech") GenericValue contactMech) throws GenericEntityException {
        return this.findList("findPartyContactMech",contactMech);
    }

    @Override
    public List<GenericValue> findPartyAndContactMech(@Param("contactMech") GenericValue contactMech) throws GenericEntityException {
        return this.findList("findPartyAndContactMech",contactMech);
    }

    @Override
    public  GenericValue   findContactMechTypeById(@Param("contactMechTypeId") String contactMechTypeId) throws GenericEntityException{
        GenericValue param = new GenericValue();
        param.setString("contactMechTypeId", contactMechTypeId);
        return this.findOne("findContactMechTypeById",param);
    }


    public GenericValue   findTelecomNumber(@Param("contactMechId") String contactMechId) throws GenericEntityException{
        GenericValue param = new GenericValue();
        param.setString("contactMechId", contactMechId);
        return this.findOne("findTelecomNumber",param);
    }

}
