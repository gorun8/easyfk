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
package cn.gorun8.easyfk.party.dao;

import cn.gorun8.easyfk.entity.GenericValue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hezhiping(110476592@qq.com) on 15-10-19.
 */
@Repository
public interface PartyClsGroupDao {


    public List<GenericValue> findPartyClsGroupList(String name);

    /**
     * 创建会员组
     * @param partyClsGroup
     */
    public void createPartyClsGroup(@Param("partyClsGroup") GenericValue partyClsGroup);

    /**
     * 更新会员组
     * @param partyClsGroup
     */
    public void savePartyClsGroup(@Param("partyClsGroup") GenericValue partyClsGroup);

    /**
     * 删除会员组
     * @param partyClsGroupId
     */
    public void removePartyClsGroupByPrimaryKey(@Param("partyClsGroupId") String partyClsGroupId);
}
