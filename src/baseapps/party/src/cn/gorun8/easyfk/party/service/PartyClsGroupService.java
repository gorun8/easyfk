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
package cn.gorun8.easyfk.party.service;

import cn.gorun8.easyfk.entity.GenericValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * demo 服务接口
 *
 */
public interface PartyClsGroupService {

	/**
	 * 获取根组织机构
	 * @param context
	 * @return
	 */
	public List<Map>  listRootNode(Map<String, ? extends Object> context);

	/**
	 * 获取下级组织机构
	 * @param context
	 * @return
	 */
	public List<Map>  listChildNode(Map<String, ? extends Object> context);


	/**
	 * 根据组织机构名查询
	 * @param context
	 * @return
	 */
	public List<Map>  searchNode(Map<String, ? extends Object> context);


	/**
	 * 是否存在获取下级组织机构
	 * @param parentId
	 * @return
	 */
	public boolean hasChildrenPartyClsGroup(String parentId);


	/**
	 * 获取指定组织机构
	 * @param partyClsGroupId
	 * @return
	 */
	public GenericValue findPartyClsGroupById(String partyClsGroupId);

	/**
	 * 相同组织机构下是否存在同名
	 * @param parentId
	 * @return
	 */
	public boolean hasPartyClsGroupName(String parentId,String description);




	/**
	 * 创建组织机构
	 * @param partyGroup
	 */
	public void createPartyClsGroup(  GenericValue partyGroup);

	/**
	 * 更新组织机构
	 * @param partyClsGroup
	 */
	public void savePartyClsGroup( GenericValue partyClsGroup);

	/**
	 * 删除组织机构
	 * @param partyClsGroupId
	 */
	public void removePartyClsGroupByPrimaryKey(String partyClsGroupId);

}
