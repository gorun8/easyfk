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

import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.party.dao.PartyClsGroupDao;
import cn.gorun8.easyfk.party.service.PartyClsGroupService;
import javolution.util.FastList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

/**
 * 
 * demo 服务接口实现类
 *
 */

@Service("partyClsGroupService")
public class PartyClsGroupServiceImpl implements PartyClsGroupService {
	@Autowired
	protected PartyClsGroupDao partyClsGroupDao;


	public List<Map>  listRootNode(Map<String, ? extends Object> context){

		String parentId =(String)context.get("parentId");

		List<Map> parClsMapList = FastList.newInstance();
		if(UtilValidate.isEmpty(parentId))
		{
			return parClsMapList;
		}

		List<GenericValue> partyClsGroupList = partyClsGroupDao.findChildPartyClsGroupList(parentId);
		if(partyClsGroupList != null && partyClsGroupList.size()>0){
			GenericValue gv = partyClsGroupList.get(0);
			String id = gv.getString("partyClassificationGroupId");
			int count = partyClsGroupDao.findChildPartyClsGroupCount(id);
			Map map = gv.toMap();
			map.put("childCount", Integer.valueOf(count));
			parClsMapList.add(map);
		}
		return  parClsMapList;
	}

	public List<Map>  listChildNode(Map<String, ? extends Object> context)
	{
		String parentId =(String)context.get("parentId");
		List<Map> parClsMapList = FastList.newInstance();
		if(UtilValidate.isEmpty(parentId))
		{
			return parClsMapList;
		}

		List<GenericValue> partyClsGroupList = partyClsGroupDao.findChildPartyClsGroupList(parentId);
		for (GenericValue gv :partyClsGroupList){
			Map v = gv.toMap();
			String id = (String)v.get("partyClassificationGroupId");
			int count = partyClsGroupDao.findChildPartyClsGroupCount(id);
			v.put("childCount",Integer.valueOf(count));
			parClsMapList.add(v);
		}
		return  parClsMapList;
	}

	public boolean hasChildrenPartyClsGroup(String parentId){
		int count =  partyClsGroupDao.findChildPartyClsGroupCount(parentId);
		return (count > 0);
	}

	/**
	 * 获取指定组织机构
	 * @param partyClsGroupId
	 * @return
	 */
	public GenericValue findPartyClsGroupById(String partyClsGroupId){
		return partyClsGroupDao.findPartyClsGroupById(partyClsGroupId);
	}
	public boolean hasPartyClsGroupName(String parentId,String description){
		List<GenericValue> list = partyClsGroupDao.findChildPartyClsGroupByName(parentId,description);
		return (list != null) && (list.size() >0);
	}


	@Override
	public void createPartyClsGroup(GenericValue genericValue) {
		partyClsGroupDao.createPartyClsGroup(genericValue);
	}

	@Override
	public void savePartyClsGroup(GenericValue partyClsGroup) {
		partyClsGroupDao.savePartyClsGroup(partyClsGroup);
	}

	@Override
	public void removePartyClsGroupByPrimaryKey(String partyClsGroupId) {
		partyClsGroupDao.removePartyClsGroupByPrimaryKey(partyClsGroupId);
	}
}
