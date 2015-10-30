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
