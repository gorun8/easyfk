/*
 * Project:Easy Web Framework
 *
 * Description: This project is based on much more open source projects than ever before,
 *              and can be applied to mostly web development environment.
 * Author:hezhiping   Email:110476592@qq.com
 * 
 * 
 *==========================================================================================
 * 
 */
package cn.gorun8.easyfk.party.service;

import cn.gorun8.easyfk.entity.GenericValue;

import java.util.List;

/**
 * demo 服务接口
 *
 */
public interface PartyClsGroupService {

	public List<GenericValue> findPartyClsGroupList();
	/**
	 * 创建会员组
	 * @param partyGroup
	 */
	public void createPartyClsGroup(  GenericValue partyGroup);

	/**
	 * 更新会员组
	 * @param partyGroup
	 */
	public void savePartyClsGroup( GenericValue partyGroup);

	/**
	 * 删除会员组
	 * @param partyClsGroupId
	 */
	public void removePartyClsGroupByPrimaryKey(String partyClsGroupId);

}
