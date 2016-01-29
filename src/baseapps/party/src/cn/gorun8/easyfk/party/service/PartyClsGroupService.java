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
	/*
	<service name="createPartyClassification" engine="simple" default-entity-name="PartyClassification"
            location="component://party/script/org/ofbiz/party/party/PartyServices.xml" invoke="createPartyClassification">
        <description>create PartyClassification</description>
        <permission-service service-name="partyBasePermissionCheck" main-action="CREATE"/>
        <auto-attributes mode="IN" include="pk" optional="false"/>
        <auto-attributes mode="IN" include="nonpk" optional="true"/>
        <override name="fromDate" optional="true"/>
    </service>
    */
	public Map<String,Object>  createPartyClassification(Map<String, ? extends Object> context);

	/*
    <service name="updatePartyClassification" engine="simple" default-entity-name="PartyClassification"
        location="component://party/script/org/ofbiz/party/party/PartyServices.xml" invoke="updatePartyClassification">
        <description>update PartyClassification</description>
        <permission-service service-name="partyBasePermissionCheck" main-action="UPDATE"/>
        <auto-attributes mode="IN" include="pk" optional="false"/>
        <auto-attributes mode="IN" include="nonpk" optional="true"/>
    </service>
    */
	public Map<String,Object>  updatePartyClassification(Map<String, ? extends Object> context);

	/*
    <service name="deletePartyClassification" engine="simple" default-entity-name="PartyClassification"
            location="component://party/script/org/ofbiz/party/party/PartyServices.xml" invoke="deletePartyClassification">
        <description>delete PartyClassification</description>
        <permission-service service-name="partyBasePermissionCheck" main-action="DELETE"/>
        <auto-attributes mode="IN" include="pk" optional="false"/>
    </service>
	*/
	public Map<String,Object>  deletePartyClassification(Map<String, ? extends Object> context);

	/**
	 * 获取根组织机构
	 * @param context
	 * @return
	 */
	//public Map<String,Object> listRootNode(Map<String, ? extends Object> context);

	/**
	 * 获取下级组织机构的数目
	 * @param context
	 * @return
	 */
	public Map<String,Object>  getChildPartyClassificationGroupCount(Map<String, ? extends Object> context);

	/**
	 * 获取下级组织机构
	 * @param context
	 * @return
	 */
	public Map<String,Object> listChildPartyClassificationGroup(Map<String, ? extends Object> context);

	/**
	 * 获取所有的组织机构
	 * @param context
	 * @return
	 */
	public Map<String,  Object> getPartyClassificationGroupTree(Map<String, ? extends Object> context);


	/**
	 * 根据组织机构名查询
	 * @param context
	 * @return
	 */
	public Map<String,Object>  searchPartyClassificationGroup(Map<String, ? extends Object> context);


	/**
	 * 是否存在获取下级组织机构
	 * @param context
	 * @return
	 */
	public Map<String,Object> hasChildPartyClassificationGroup(Map<String, ? extends Object> context);


	/**
	 * 获取指定组织机构
	 * @param context
	 * @return
	 */
	public Map<String,Object> findPartyClassificationGroupById(Map<String, ? extends Object> context);

	/**
	 * 相同组织机构下是否存在同名
	 * @param context
	 * @return
	 */
	public Map<String,Object>  hasPartyClassificationGroupName(Map<String, ? extends Object> context);


	/**
	 * 移动组织机构
	 * @param context
	 * @return
	 */
	public Map<String,Object>  movePartyClassificationGroup(Map<String, ? extends Object> context);


	/**
	 * 创建组织机构
	 * <service name="createPartyClassificationGroup" engine="simple" default-entity-name="PartyClassificationGroup"
	 location="component://party/script/org/ofbiz/party/party/PartyServices.xml" invoke="createPartyClassificationGroup">
	 <description>create PartyClassificationGroup</description>
	 <permission-service service-name="partyBasePermissionCheck" main-action="CREATE"/>
	 <auto-attributes mode="OUT" include="pk" optional="false"/>
	 <auto-attributes mode="IN" include="nonpk" optional="true"/>
	 </service>
	 * @param context
	 */
	public  Map<String,Object> createPartyClassificationGroup(Map<String, ? extends Object> context);


	/**
	 * 更新组织机构
	 * <service name="updatePartyClassificationGroup" engine="simple" default-entity-name="PartyClassificationGroup"
	 location="component://party/script/org/ofbiz/party/party/PartyServices.xml" invoke="updatePartyClassificationGroup">
	 <description>update PartyClassificationGroup</description>
	 <permission-service service-name="partyBasePermissionCheck" main-action="UPDATE"/>
	 <auto-attributes mode="IN" include="pk" optional="false"/>
	 <auto-attributes mode="IN" include="nonpk" optional="true"/>
	 </service>
	 * @param context
	 */
	public Map<String,Object>  updatePartyClassificationGroup( Map<String, ? extends Object> context);

	/**
	 * 删除组织机构
	 * <service name="deletePartyClassificationGroup" engine="simple" default-entity-name="PartyClassificationGroup"
	 location="component://party/script/org/ofbiz/party/party/PartyServices.xml" invoke="deletePartyClassificationGroup">
	 <description>delete PartyClassificationGroup</description>
	 <permission-service service-name="partyBasePermissionCheck" main-action="DELETE"/>
	 <auto-attributes mode="IN" include="pk" optional="false"/>
	 </service>
	 * @param context
	 */
	public Map<String,Object>  deletePartyClassificationGroup(Map<String, ? extends Object> context);

}
