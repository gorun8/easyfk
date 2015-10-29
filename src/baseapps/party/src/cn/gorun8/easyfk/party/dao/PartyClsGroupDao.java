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
