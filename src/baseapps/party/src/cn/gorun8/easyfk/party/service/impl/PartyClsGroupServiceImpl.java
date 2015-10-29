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
package cn.gorun8.easyfk.party.service.impl;

import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.party.dao.PartyClsGroupDao;
import cn.gorun8.easyfk.party.service.PartyClsGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 
 * demo 服务接口实现类
 *
 */

@Service("partyClsGroupService")
public class PartyClsGroupServiceImpl implements PartyClsGroupService {
	@Autowired
	protected PartyClsGroupDao partyClsGroupDao;
	

	public List<GenericValue>  findPartyClsGroupList(){
		return partyClsGroupDao.findPartyClsGroupList("");
	}

	@Override
	public void createPartyClsGroup(GenericValue genericValue) {
		partyClsGroupDao.createPartyClsGroup(genericValue);
	}

	@Override
	public void savePartyClsGroup(GenericValue partyGroup) {
		partyClsGroupDao.savePartyClsGroup(partyGroup);
	}

	@Override
	public void removePartyClsGroupByPrimaryKey(String partyClsGroupId) {
		partyClsGroupDao.removePartyClsGroupByPrimaryKey(partyClsGroupId);
	}
}
