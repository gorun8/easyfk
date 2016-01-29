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
import cn.gorun8.easyfk.party.dao.PartyWriteDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public class PartyWriteDaoImpl extends PartyWriteDao {

    @Override
    public void createParty(@Param("party") GenericValue party) throws GenericEntityException {
        this.create("createParty",party);
    }

    @Override
    public void saveParty(@Param("party") GenericValue party) throws GenericEntityException {
        this.store("saveParty",party);
    }

    @Override
    public void createPartyStatus(@Param("partyStatus") GenericValue partyStatus) throws GenericEntityException {
        this.create("createPartyStatus",partyStatus);
    }

    @Override
    public void savePartyStatus(@Param("partyStatus") GenericValue partyStatus) throws GenericEntityException {
        this.store("savePartyStatus",partyStatus);
    }

    @Override
    public void createPerson(@Param("person") GenericValue person) throws GenericEntityException {
        this.create("createPerson",person);
    }

    @Override
    public void savePerson(@Param("person") GenericValue person) throws GenericEntityException {
        this.store("savePerson",person);
    }

    @Override
    public void createPartyGroup(@Param("partyGroup") GenericValue partyGroup) throws GenericEntityException {
        this.create("createPartyGroup",partyGroup);
    }

    @Override
    public void savePartyGroup(@Param("partyGroup") GenericValue partyGroup) throws GenericEntityException {
        this.store("savePartyGroup",partyGroup);
    }

    @Override
    public void removePartyGroupByPrimaryKey(@Param("partyId") String partyId) throws GenericEntityException {
        GenericValue gv = new GenericValue();
        gv.setString("partyId",partyId);
        this.removeValue("removePartyGroupByPrimaryKey",gv);
    }

    @Override
    public void  createPartyRole(@Param("partyRole") GenericValue partyRole) throws GenericEntityException {
        this.store("createPartyRole",partyRole);
    }

    public void  removePartyRole(@Param("partyRole") GenericValue partyRole) throws GenericEntityException {
        this.removeValue("removePartyRole",partyRole);
    }
}
