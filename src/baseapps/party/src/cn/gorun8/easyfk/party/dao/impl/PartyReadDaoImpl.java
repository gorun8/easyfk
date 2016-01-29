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
 * Author:hezhiping   Email:110476592@qq.com   Date: 15-12-2
 */
package cn.gorun8.easyfk.party.dao.impl;

import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.party.dao.PartyReadDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartyReadDaoImpl extends PartyReadDao {

    @Override
    public List<GenericValue> findPartyList(@Param("partyClassificationGroupId") String partyClassificationGroupId) throws GenericEntityException {
        GenericValue param = new GenericValue();
        param.setString("partyClassificationGroupId",partyClassificationGroupId);
        return this.findList("findPartyList",param);
    }

    @Override
    public GenericValue findPartyById(@Param("partyId") String partyId) throws GenericEntityException {
        GenericValue param = new GenericValue();
        param.setString("partyId",partyId);
        return this.findOne("findPartyById",param);
    }

    @Override
    public GenericValue findPersonById(@Param("partyId") String partyId) throws GenericEntityException {
        GenericValue param = new GenericValue();
        param.setString("partyId",partyId);
        return this.findOne("findPersonById",param);
    }

    @Override
    public GenericValue findPartyTypeById(@Param("partyTypeId") String partyTypeId) throws GenericEntityException {
        GenericValue param = new GenericValue();
        param.setString("partyTypeId",partyTypeId);
        return this.findOne("findPartyTypeById",param);
    }

    @Override
    public List<GenericValue> findDescendantTypes(@Param("partyTypeId") String partyTypeId) throws GenericEntityException {
        GenericValue param = new GenericValue();
        param.setString("partyTypeId",partyTypeId);
        return this.findList("findDescendantTypes", param);
    }

    @Override
    public List<GenericValue> findPartyGroupList(String name) throws GenericEntityException {
        GenericValue param = new GenericValue();
        param.setString("name",name);
        return this.findList("findPartyGroupList", param);
    }

    @Override
    public GenericValue findPartyGroupById(@Param("partyId") String partyId)  throws GenericEntityException {
        GenericValue param = new GenericValue();
        param.setString("partyId",partyId);
        return this.findOne("findPartyGroupById", param);
    }


}
