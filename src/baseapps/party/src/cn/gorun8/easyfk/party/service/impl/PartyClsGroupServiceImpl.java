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

import cn.gorun8.easyfk.base.util.*;
import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.util.UtilEntity;
import cn.gorun8.easyfk.party.dao.PartyClsGroupReadDao;
import cn.gorun8.easyfk.party.dao.PartyClsGroupWriteDao;
import cn.gorun8.easyfk.party.service.PartyClsGroupService;
import javolution.util.FastList;
import javolution.util.FastMap;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * 组织机构管理
 *
 */

@Service("partyClsGroupService")
public class PartyClsGroupServiceImpl implements PartyClsGroupService {
	public static final String resource = "PartyUiLabels";
	public static final String resourceError = "PartyErrorUiLabels";

	@Autowired
	protected PartyClsGroupReadDao partyClsGroupReadDao;

	@Autowired
	protected PartyClsGroupWriteDao partyClsGroupWriteDao;

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
	public Map<String,Object>  createPartyClassification(Map<String, ? extends Object> context){
		Locale locale = (Locale) context.get("locale");
		GenericValue partyCls = GenericValue.fromMap(context, true);
		String partyClassificationGroupId = (String) context.get("partyClassificationGroupId");
		if(UtilValidate.isEmpty(partyClassificationGroupId)){
			return UtilMessages.returnParamError(locale, "partyClassificationGroupId");
		}

		String partyId = (String) context.get("partyId");
		if(UtilValidate.isEmpty(partyId)){
			return UtilMessages.returnParamError(locale, "partyId");
		}

		Timestamp now = UtilDateTime.nowTimestamp();
		partyCls.setString("fromDate",now);


		try {
			partyClsGroupWriteDao.createPartyClassification(partyCls);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"partycls.create.error", locale));
		}

		return  UtilMessages.returnSuccess();
	}

	/*
    <service name="updatePartyClassification" engine="simple" default-entity-name="PartyClassification"
        location="component://party/script/org/ofbiz/party/party/PartyServices.xml" invoke="updatePartyClassification">
        <description>update PartyClassification</description>
        <permission-service service-name="partyBasePermissionCheck" main-action="UPDATE"/>
        <auto-attributes mode="IN" include="pk" optional="false"/>
        <auto-attributes mode="IN" include="nonpk" optional="true"/>
    </service>
    */
	public Map<String,Object>  updatePartyClassification(Map<String, ? extends Object> context){
		Locale locale = (Locale) context.get("locale");
		GenericValue partyCls = GenericValue.fromMap(context, true);
		String partyClassificationGroupId = (String) context.get("partyClassificationGroupId");
		if(UtilValidate.isEmpty(partyClassificationGroupId)){
			return UtilMessages.returnParamError(locale, "partyClassificationGroupId");
		}

		String partyId = (String) context.get("partyId");
		if(UtilValidate.isEmpty(partyId)){
			return UtilMessages.returnParamError(locale, "partyId");
		}

		Timestamp now = UtilDateTime.nowTimestamp();
		partyCls.setString("fromDate",now);


		try {
			partyClsGroupWriteDao.updatePartyClassification(partyCls);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"partycls.update.error", locale));
		}

		return  UtilMessages.returnSuccess();
	}

	/*
    <service name="deletePartyClassification" engine="simple" default-entity-name="PartyClassification"
            location="component://party/script/org/ofbiz/party/party/PartyServices.xml" invoke="deletePartyClassification">
        <description>delete PartyClassification</description>
        <permission-service service-name="partyBasePermissionCheck" main-action="DELETE"/>
        <auto-attributes mode="IN" include="pk" optional="false"/>
    </service>
	*/
	public Map<String,Object>  deletePartyClassification(Map<String, ? extends Object> context){
		Locale locale = (Locale) context.get("locale");
		GenericValue partyCls = GenericValue.fromMap(context, true);
		String partyClassificationGroupId = (String) context.get("partyClassificationGroupId");
		if(UtilValidate.isEmpty(partyClassificationGroupId)){
			return UtilMessages.returnParamError(locale, "partyClassificationGroupId");
		}

		String partyId = (String) context.get("partyId");
		if(UtilValidate.isEmpty(partyId)){
			return UtilMessages.returnParamError(locale, "partyId");
		}


		try {
			partyClsGroupWriteDao.deletePartyClassification(partyCls);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"partycls.delete.error", locale));
		}

		return  UtilMessages.returnSuccess();
	}

	/**
	 * 获取根组织机构
	 * @param context
	 * @return
	 */
//	public Map<String,Object> listRootNode(Map<String, ? extends Object> context){
//		String parentId =(String)context.get("parentId");
//		Locale locale = (Locale) context.get("locale");
//
//		//获取根结点
//		try {
//			GenericValue rootgv =  partyClsGroupReadDao.findPartyClsGroupById(parentId);
//			if (UtilValidate.isEmpty(rootgv)){
//				return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//						"party.cls.not_found", locale));
//			}
//			String id = (String)rootgv.get("PARTY_CLASSIFICATION_GROUP_ID");
//			long count = partyClsGroupReadDao.findChildPartyClsGroupCount(id);
//
//			Map<String,Object>  result = listChildNode(context);
//			Map root = rootgv.toMap();
//			root.put("rootChildCount",count);
//			result.put("rootNode",root);
//			return result;
//
//		} catch (GenericEntityException e) {
//			e.printStackTrace();
//			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//					"party.cls.found.error", locale));
//		}
//	}


	public Map<String,  Object>  getChildPartyClassificationGroupCount(Map<String, ? extends Object> context){
		String parentId =(String)context.get("parentId");
		Locale locale = (Locale) context.get("locale");
		List<Map> parClsMapList = FastList.newInstance();

		if(UtilValidate.isEmpty(parentId)){
			return UtilMessages.returnParamError(locale,"parentId");
		}

		try {
			Long count = partyClsGroupReadDao.findChildPartyClsGroupCount(parentId);
			return  UtilMessages.returnSuccessWithData(count);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"party.cls.found.error", locale));
		}
	}

	public Map<String,  Object>   listChildPartyClassificationGroup(Map<String, ? extends Object> context){
		Locale locale = (Locale) context.get("locale");
		String parentId =(String)context.get("parentId");

		if(UtilValidate.isEmpty(parentId)){
			return UtilMessages.returnParamError(locale, "parentId");
		}

		try {
			List<Map> parClsMapList = FastList.newInstance();
			List<GenericValue> partyClsGroupList = partyClsGroupReadDao.findChildPartyClsGroupList(parentId);
			for (GenericValue gv :partyClsGroupList){
				Map v = gv.toMap();
				String id = (String)v.get("partyClassificationGroupId");
				long count = partyClsGroupReadDao.findChildPartyClsGroupCount(id);
				v.put("childCount",Long.valueOf(count));
				parClsMapList.add(v);
			}
			return UtilMessages.returnSuccessWithData(parClsMapList);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"party.cls.found.error", locale));
		}
 	}


	public Map<String,  Object> getPartyClassificationGroupTree(Map<String, ? extends Object> context){
		Locale locale = (Locale) context.get("locale");

		String partyRootId = (String) context.get("parentId");
		List<GenericValue> listRoot = FastList.newInstance();

		try {

			//用户当前根结点 ，如果指定了根节点就读取根节点以下的节点，如果没有指定，就以_NA_为根节点读取
			GenericValue myRootGv = null;
			if(UtilValidate.isNotEmpty(partyRootId)) {
				myRootGv = partyClsGroupReadDao.findPartyClsGroupById(partyRootId);
				if (UtilValidate.isEmpty(myRootGv)) {
					return UtilMessages.returnSuccessWithData(UtilEntity.toMap(listRoot));
				}
				listRoot.add(myRootGv);
			}else{
				partyRootId = "_NA_";
				myRootGv = partyClsGroupReadDao.findPartyClsGroupById("_NA_");
				if (UtilValidate.isNotEmpty(myRootGv)) {
					String id = (String) myRootGv.get("PARTY_CLASSIFICATION_GROUP_ID");
					long count = partyClsGroupReadDao.findChildPartyClsGroupCount(id);
					myRootGv.setString("childCount", String.valueOf(count));
					listRoot.add(myRootGv);
				}
			}

			int count = getClsGroups(listRoot, partyRootId);
			myRootGv.setString("childCount",String.valueOf(count));

		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"security.permission.find.error", locale));
		}

		return UtilMessages.returnSuccessWithData(UtilEntity.toMap(listRoot));
	}



	private int getClsGroups(List<GenericValue> dataList,String parentId) throws  GenericEntityException{

		List<GenericValue> clsGroupsList = partyClsGroupReadDao.findChildPartyClsGroupList(parentId);
		if(clsGroupsList == null || clsGroupsList.size() <=0){
			return 0;
		}

		String pid ="";
		for (GenericValue it:clsGroupsList){
			dataList.add(it);
			pid= it.getString("partyClassificationGroupId");
			int count =  getClsGroups(dataList, pid);
			it.setString("childCount",String.valueOf(count));
		}
		return clsGroupsList.size();
	}


	public Map<String,  Object>  searchPartyClassificationGroup(Map<String, ? extends Object> context){
		String partyClsName =(String)context.get("partyClsName");
		Locale locale = (Locale) context.get("locale");


		List<GenericValue> listRoot = FastList.newInstance();

		try {//始终要显示
			GenericValue rootgv =  partyClsGroupReadDao.findPartyClsGroupById("_NA_");
			if (UtilValidate.isNotEmpty(rootgv)){
				String id = (String)rootgv.get("PARTY_CLASSIFICATION_GROUP_ID");
				long count = partyClsGroupReadDao.findChildPartyClsGroupCount(id);
				rootgv.setString("childCount", String.valueOf(count));
				listRoot.add(rootgv);
			}

			if(UtilValidate.isEmpty(partyClsName)){
				//return UtilMessages.returnParamError(locale, "partyClsName");
				return UtilMessages.returnSuccessWithData(UtilEntity.toMap(listRoot));
			}

			List<GenericValue> partyClsGroupList = partyClsGroupReadDao.findPartyClsGroupByName(partyClsName);
			for (GenericValue gv :partyClsGroupList){
				listRoot.add(gv);

				String id = (String)gv.get("PARTY_CLASSIFICATION_GROUP_ID");
				int count = getClsGroups(listRoot, id);
				gv.setString("childCount", String.valueOf(count));
			}

			return UtilMessages.returnSuccessWithData(UtilEntity.toMap(listRoot));
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"party.cls.found.error", locale));
		}

	}



	public Map<String,  Object>  hasChildPartyClassificationGroup(Map<String, ? extends Object> context){
		String parentId =(String)context.get("parentId");
		Locale locale = (Locale) context.get("locale");

		if(UtilValidate.isEmpty(parentId)){
			return UtilMessages.returnParamError(locale, "parentId");
		}

		long count = 0;
		try {
			count = partyClsGroupReadDao.findChildPartyClsGroupCount(parentId);
			Boolean bb = Boolean.valueOf(count > 0);
			return UtilMessages.returnSuccessWithData(bb);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"party.cls.found.error", locale));
		}
	}

	/**
	 * 获取指定组织机构
	 * @param context
	 * @return
	 */
	public Map<String,  Object>  findPartyClassificationGroupById(Map<String, ? extends Object> context){
		String partyClsGroupId =(String)context.get("partyClsGroupId");
		Locale locale = (Locale) context.get("locale");

		if(UtilValidate.isEmpty(partyClsGroupId)){
			return UtilMessages.returnParamError(locale, "partyClsGroupId");
		}

		try {
			GenericValue gv =  partyClsGroupReadDao.findPartyClsGroupById(partyClsGroupId);
			if (UtilValidate.isNotEmpty(gv)){
				return UtilMessages.returnSuccessWithData(gv.toMap());
			}
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
				"party.cls.not_found", locale));
	}

	public Map<String,  Object>  hasPartyClassificationGroupName(Map<String, ? extends Object> context){
		String parentGroupId =(String)context.get("parentGroupId");
		String description =(String)context.get("description");
		Locale locale = (Locale) context.get("locale");

		if(UtilValidate.isEmpty(parentGroupId)){
			return UtilMessages.returnParamError(locale, "parentGroupId");
		}
		if(UtilValidate.isEmpty(description)){
			return UtilMessages.returnParamError(locale, "description");
		}

		List<GenericValue> list = null;
		try {
			list = partyClsGroupReadDao.findChildPartyClsGroupByName(parentGroupId,description);
			Boolean bb = Boolean.valueOf((list != null) && (list.size() >0));
			return UtilMessages.returnSuccessWithData(bb);
		} catch (GenericEntityException e) {
			e.printStackTrace();
		}
		return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
				"party.cls.found.error", locale));
	}

	public Map<String,Object>  movePartyClassificationGroup(Map<String, ? extends Object> context){
		Locale locale = (Locale) context.get("locale");
		GenericValue partyClsGroup = GenericValue.fromMap(context,false);
		String partyClassificationGroupId = (String) context.get("partyClassificationGroupId");
		if(UtilValidate.isEmpty(partyClassificationGroupId)){
			return UtilMessages.returnParamError(locale, "partyClassificationGroupId");
		}
		String parentGroupId = (String) context.get("parentGroupId");
		if(UtilValidate.isEmpty(parentGroupId)){
			return UtilMessages.returnParamError(locale, "parentGroupId");
		}

		try {
			partyClsGroupWriteDao.savePartyClsGroup(partyClsGroup);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"party.cls.update.error", locale));
		}

		return  UtilMessages.returnSuccess();
	}

	@Override
	public Map<String,  Object>  createPartyClassificationGroup(Map<String, ? extends Object> context) {
		Locale locale = (Locale) context.get("locale");
		GenericValue partyClsGroup = GenericValue.fromMap(context,true);
		String id = partyClsGroup.newPrimaryKey("PartyClassificationGroup", "partyClassificationGroupId");
		String description = (String) context.get("description");
		if(UtilValidate.isEmpty(description)){
			return UtilMessages.returnParamError(locale, "description");
		}

		try {
			partyClsGroupWriteDao.createPartyClsGroup(partyClsGroup);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"party.cls.create.error", locale));
		}

		return  UtilMessages.returnSuccessWithData(id);
	}

	@Override
	public Map<String,  Object>  updatePartyClassificationGroup(Map<String, ? extends Object> context) {
		Locale locale = (Locale) context.get("locale");
		GenericValue partyClsGroup = GenericValue.fromMap(context,false);
		String partyClassificationGroupId = (String) context.get("partyClassificationGroupId");
		if(UtilValidate.isEmpty(partyClassificationGroupId)){
			return UtilMessages.returnParamError(locale, "partyClassificationGroupId");
		}
		String parentGroupId = (String) context.get("parentGroupId");
		if(UtilValidate.isEmpty(parentGroupId)){
			return UtilMessages.returnParamError(locale, "parentGroupId");
		}
		String description = (String) context.get("description");
		if(UtilValidate.isEmpty(description)){
			return UtilMessages.returnParamError(locale, "description");
		}

		try {
			partyClsGroupWriteDao.savePartyClsGroup(partyClsGroup);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"party.cls.update.error", locale));
		}

		return  UtilMessages.returnSuccess();
	}

	@Override
	public Map<String,  Object>   deletePartyClassificationGroup(Map<String, ? extends Object> context) {
		Locale locale = (Locale) context.get("locale");
		String partyClsGroupId = (String)context.get("partyClsGroupId");

		if(UtilValidate.isEmpty(partyClsGroupId)){
			return UtilMessages.returnParamError(locale, "partyClsGroupId");
		}

		try {
			partyClsGroupWriteDao.removePartyClsGroupByPrimaryKey(partyClsGroupId);
		} catch (GenericEntityException e) {
			e.printStackTrace();
			return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
					"party.cls.remove.error", locale));
		}

		return  UtilMessages.returnSuccess();
	}





}
