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
import cn.gorun8.easyfk.party.dao.PartyClsGroupWriteDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public class PartyClsGroupWriteDaoImpl extends PartyClsGroupWriteDao {
    @Override
    public void createPartyClassification(@Param("partyClass") GenericValue partyClass) throws GenericEntityException {
        this.create("createPartyClassification",partyClass);
    }

    @Override
    public void updatePartyClassification(@Param("partyClass") GenericValue partyClass) throws GenericEntityException {
        this.store("updatePartyClassification",partyClass);
    }

    @Override
    public void deletePartyClassification(@Param("partyClass") GenericValue partyClass) throws GenericEntityException {
        this.removeValue("deletePartyClassification",partyClass);
    }

    @Override
    public void createPartyClsGroup(@Param("partyClsGroup") GenericValue partyClsGroup) throws GenericEntityException {
        this.create("createPartyClsGroup",partyClsGroup);
    }

    @Override
    public void savePartyClsGroup(@Param("partyClsGroup") GenericValue partyClsGroup) throws GenericEntityException {
        this.store("savePartyClsGroup",partyClsGroup);
    }

    @Override
    public void removePartyClsGroupByPrimaryKey(@Param("partyClassificationGroupId") String partyClassificationGroupId) throws GenericEntityException {
        GenericValue gv = new GenericValue();
        gv.setString("partyClassificationGroupId",partyClassificationGroupId);
        this.removeValue("removePartyClsGroupByPrimaryKey",gv);
    }

    @Override
    public void setPartyClassification(@Param("partyCls") GenericValue partyCls) throws GenericEntityException {
        this.create("setPartyClassification",partyCls);
    }
}
