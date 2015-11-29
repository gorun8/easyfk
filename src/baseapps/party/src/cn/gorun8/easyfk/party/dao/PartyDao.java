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
import cn.gorun8.easyfk.entity.GenericValue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hezhiping(110476592@qq.com) on 15-10-19.
 */
@Repository
public interface PartyDao {

    /**
     * 获取会员列表
     * @return
     */
    public List<GenericValue> findPartyList(@Param("partyClsId") String partyClsId) throws Exception;

    /**
     * 根据partyId查询Party
     * @param partyId
     * @return
     */
    public  GenericValue  findPartyById(@Param("partyId")String partyId) throws Exception;

    /**
     * 创建Party
     * @param party
     */
    public  void  createParty(@Param("party")GenericValue party) throws Exception;

    /**
     * 更新Party
     * @param party
     */
    public  void  saveParty(@Param("party")GenericValue party) throws Exception;



    /**
     * 创建会员状态
     * @param partyStatus
     */
    public void  createPartyStatus(@Param("partyStatus")GenericValue partyStatus) throws Exception;

    /**
     * 更新会员状态
     * @param partyStatus
     */
    public void  savePartyStatus(@Param("partyStatus")GenericValue partyStatus) throws Exception;

    /**
     * 根据会员ID获取Person信息
     * @param partyId
     * @return
     */
    public  GenericValue  findPersonById(@Param("partyId")String partyId) throws Exception;

    /**
     * 创建Person
     * @param person
     */
    public  void  createPerson(@Param("person")GenericValue person) throws Exception;


    /**
     * 更新会员状态
     * @param person
     */
    public void  savePerson(@Param("person")GenericValue person) throws Exception;


    /**
     * 根据会员ID获取Person信息
     * @param partyTypeId
     * @return
     */
    public  GenericValue  findPartyTypeById(@Param("partyTypeId")String partyTypeId) throws Exception;
    public  List<GenericValue>  findDescendantTypes(@Param("partyTypeId")String partyTypeId) throws Exception;

    public List<GenericValue> findPartyGroupList(String name) throws Exception;

    /**
     * 读取组会员信息
     * @param partyGroupId
     * @return
     */
    public GenericValue findPartyGroupById(@Param("partyGroupId")String partyGroupId);

    /**
     * 创建会员组
     * @param partyGroup
     */
    public void createPartyGroup(@Param("partyGroup") GenericValue partyGroup);

    /**
     * 更新会员组
     * @param partyGroup
     */
    public void savePartyGroup(@Param("partyGroup") GenericValue partyGroup);

    /**
     * 删除会员组
     * @param partyId
     */
    public void removePartyGroupByPrimaryKey(@Param("partyId") String partyId);

}
