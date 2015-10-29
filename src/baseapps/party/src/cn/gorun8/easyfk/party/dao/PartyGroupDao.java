package cn.gorun8.easyfk.party.dao;

import cn.gorun8.easyfk.entity.GenericValue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hezhiping(110476592@qq.com) on 15-10-19.
 */
@Repository
public interface PartyGroupDao {


    public List<GenericValue> findPartyGroupList(String name);

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
