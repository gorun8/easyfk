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
 * Author:hezhiping   Email:110476592@qq.com   Date: 15-11-4
 */

package cn.gorun8.easyfk.party.service.impl;

import cn.gorun8.easyfk.base.util.*;
import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.common.dao.CommonReadDao;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.SequenceFactory;
import cn.gorun8.easyfk.entity.util.UtilEntity;
import cn.gorun8.easyfk.party.dao.PartyClsGroupReadDao;
import cn.gorun8.easyfk.party.dao.PartyClsGroupWriteDao;
import cn.gorun8.easyfk.party.dao.PartyReadDao;
import cn.gorun8.easyfk.party.dao.PartyWriteDao;
import cn.gorun8.easyfk.party.service.PartyService;
import cn.gorun8.easyfk.party.utils.PartyTypeUtil;
import cn.gorun8.easyfk.security.dao.UserLoginReadDao;
import cn.gorun8.easyfk.security.dao.UserLoginWriteDao;
import cn.gorun8.easyfk.security.service.UserLoginService;
import javolution.util.FastList;
import javolution.util.FastMap;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * 会员管理
 */
@Service
public class PartyServiceImpl implements PartyService {

    public static final String module = PartyServiceImpl.class.getName();
    public static final String resource = "PartyUiLabels";
    public static final String resourceError = "PartyErrorUiLabels";

    @Autowired
    private PartyReadDao partyReadDao;
    @Autowired
    private PartyWriteDao partyWriteDao;
    @Autowired
    private CommonReadDao commonReadDao;
    @Autowired
    private UserLoginReadDao userLoginReadDao;

    @Autowired
    private UserLoginWriteDao userLoginWriteDao;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private PartyClsGroupReadDao partyClsGroupReadDao;
    @Autowired
    private PartyClsGroupWriteDao partyClsGroupWriteDao;

    public Map<String,Object> listParty(Map<String, ? extends Object> context){
        Locale locale = (Locale) context.get("locale");
        String clsId = (String)context.get("clsId");
        if(UtilValidate.isEmpty(clsId)){
            return UtilMessages.returnParamError(locale, "clsId");
        }

        try {
             List<Map> list2 = FastList.newInstance();
             List<GenericValue> list = partyReadDao.findPartyList(clsId);
             for (GenericValue it: list){
                 list2.add(it.toMap());
             }
            return  UtilMessages.returnSuccessWithData(list2);
        }catch(Exception e){
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "party.find.err", locale));
        }

    }

    public Map<String,Object> findPartyById(Map<String, ? extends Object> context){

        Locale locale = (Locale) context.get("locale");
        String partyId = (String) context.get("partyId");
        if(UtilValidate.isEmpty(partyId)){
            return UtilMessages.returnParamError(locale, "partyId");
        }

        GenericValue data = null;
        try {
            data = partyReadDao.findPartyById(partyId);
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "party.found.error", locale));
        }

        if(data == null|| data.size() <=0){
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "party.not_found", locale));
        }

        return UtilMessages.returnSuccessWithData(data);
    }
    /**
     * get a party group
     * @param context Map containing the input parameters.
     * @return Map with the result of the service, the output parameters.
     */
    public Map<String,Object> findPartyGorup(Map<String,Object> context)
    {
        Locale locale = (Locale) context.get("locale");
        String partyId = (String) context.get("partyId");
        if(UtilValidate.isEmpty(partyId)){
            return UtilMessages.returnParamError(locale, "partyId");
        }

        GenericValue data = null;
        try {
            data = partyReadDao.findPartyGroupById(partyId);
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "party.cls.found.error", locale));
        }

        if(data == null|| data.size() <=0){
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                "party.not_found", locale));
        }

        return UtilMessages.returnSuccessWithData(data);
    }

    /**
     * Deletes a Party.
     * @param context Map containing the input parameters.
     * @return Map with the result of the service, the output parameters.
     */
    public Map<String, Object> deleteParty(Map<String, ? extends Object> context) {

        Locale locale = (Locale) context.get("locale");

        /*
         * pretty serious operation, would delete:
         * - Party
         * - PartyRole
         * - PartyRelationship: from and to
         * - PartyDataObject
         * - Person or PartyGroup
         * - PartyContactMech, but not ContactMech itself
         * - PartyContactMechPurpose
         * - Order?
         *
         * We may want to not allow this, but rather have some sort of delete flag for it if it's REALLY that big of a deal...
         */

        return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                "partyservices.cannot_delete_party_not_implemented", locale));
    }

    /**
     * Creates a Person.
     * If no partyId is specified a numeric partyId is retrieved from the Party sequence.
     * @param context Map containing the input parameters.
     * @return Map with the result of the service, the output parameters.
     */
    public Map<String, Object> createPerson(Map<String, ? extends Object> context) {
        Map<String, Object> result = FastMap.newInstance();
        Timestamp now = UtilDateTime.nowTimestamp();
        List<GenericValue> toBeStored = FastList.newInstance();
        Locale locale = (Locale) context.get("locale");
        // in most cases userLogin will be null, but get anyway so we can keep track of that info if it is available
        GenericValue userLogin = (GenericValue) context.get("userLogin");

        String partyId = (String) context.get("partyId");
        String description = (String) context.get("description");
        Boolean successIfExist =(Boolean)context.get("successIfExist");


        FastList<String> roleTypeIds = FastList.newInstance();
        Object tmpRoleTypeId =  context.get("roleTypeId");
        if(tmpRoleTypeId instanceof  List) {
            roleTypeIds = (FastList) context.get("roleTypeId");
        }else{
            roleTypeIds.add((String)tmpRoleTypeId);
        }

        if(UtilValidate.isEmpty(roleTypeIds)){
            return UtilMessages.returnParamError(locale, "roleTypeId");
        }

        // if specified partyId starts with a number, return an error
        if (UtilValidate.isNotEmpty(partyId) && partyId.matches("\\d+")) {
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "party.id_is_digit", locale));
        }

        // partyId might be empty, so check it and get next seq party id if empty
        if (UtilValidate.isEmpty(partyId)) {
            try {
                partyId = SequenceFactory.getInstance().getNextSeqId("Party");
            } catch (IllegalArgumentException e) {
                return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                        "party.id_generation_failure", locale));
            }
        }

        // check to see if party object exists, if so make sure it is PERSON type party
        try{
            GenericValue party =  partyReadDao.findPartyById(partyId);
            if (party != null) {
                if (!"PERSON".equals(party.getString("partyTypeId"))) {
                    return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                            "person.create.party_exists_not_person_type", locale));
                }
            } else {
                // create a party if one doesn't already exist with an initial status from the input
                String statusId = (String) context.get("statusId");
                if (statusId == null) {
                    statusId = "PARTY_ENABLED";
                }
                if (UtilValidate.isEmpty(description)) {
                    description = (String) context.get("firstName");
                }

                Map<String, Object> newPartyMap = UtilMisc.toMap("partyId", partyId, "partyTypeId", "PERSON", "description", description, "createdDate", now, "lastModifiedDate", now, "statusId", statusId);
                String preferredCurrencyUomId = (String) context.get("preferredCurrencyUomId");
                if (!UtilValidate.isEmpty(preferredCurrencyUomId)) {
                    newPartyMap.put("preferredCurrencyUomId", preferredCurrencyUomId);
                }
                String externalId = (String) context.get("externalId");
                if (!UtilValidate.isEmpty(externalId)) {
                    newPartyMap.put("externalId", externalId);
                }
                if (userLogin != null) {
                    newPartyMap.put("createdByUserLogin", userLogin.get("userLoginId"));
                    newPartyMap.put("lastModifiedByUserLogin", userLogin.get("userLoginId"));
                }
                party = GenericValue.fromMap(newPartyMap,true);
                partyWriteDao.createParty(party);
                // create the status history
                GenericValue partyStatus = GenericValue.fromMap(UtilMisc.toMap("partyId", partyId, "statusId", statusId, "statusDate", now),true);
                partyWriteDao.createPartyStatus(partyStatus);
            }

            GenericValue person = null;
            person = partyReadDao.findPersonById(partyId);
            if (person != null) {
                if(!successIfExist) {
                    return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                            "person.create.person_exists", locale));
                }else {
                    result.put("partyId", partyId);
                    result.put(UtilMessages.RESPONSE_TYPE, UtilMessages.RESPOND_SUCCESS);
                    return result;
                }
            }

            person = GenericValue.fromMap(UtilMisc.toMap("partyId", partyId),true);
//            List<String> fields = UtilMisc.toList("salutation","firstName","middleName","lastName");
//                fields.add("personalTitle");
//                fields.add("suffix");
//                fields.add("nickname");
//                fields.add("firstNameLocal");
//                fields.add("middleNameLocal");
//                fields.add("lastNameLocal");
//                fields.add("memberId");
//                fields.add("gender");
//                fields.add("birthDate");
//                fields.add("comments");
//                fields.add("cardId");
            person.setNonPKFields(context);
            person.setString("partyId", partyId);
            partyWriteDao.createPerson(person);
            // set party roles
            GenericValue param = new GenericValue(true);
            param.setString("partyId",partyId);
            partyWriteDao.removePartyRole(param);

            for(String roleTypeId : roleTypeIds) {
                param.setString("roleTypeId", roleTypeId);
                partyWriteDao.createPartyRole(param);
            }

            //set party to class
            String partyClassificationGroupId =(String) context.get("partyClassificationGroupId");
            GenericValue partyCls = GenericValue.fromMap(UtilMisc.toMap("partyId", partyId,"partyClassificationGroupId",partyClassificationGroupId,"fromDate",now),true);
            partyClsGroupWriteDao.setPartyClassification(partyCls);
        }catch(Exception e){
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "person.create.db_error", locale));
        }

        return UtilMessages.returnSuccessWithData("partyId",partyId);
    }

    /**
     * 导入电子表格文件，创建会员
     * @param context
     */
    //@Transactional
    public  Map<String,Object> importPersonFromFile(Map<String, ? extends Object> context){
        Locale locale = (Locale) context.get("locale");
        String filePath = (String)context.get("filePath");
        String categoryKey = (String)context.get("categoryKey");

        if(UtilValidate.isEmpty(filePath)){
            return UtilMessages.returnParamError(locale, "filePath");
        }

        if(UtilValidate.isEmpty(categoryKey)){
            return UtilMessages.returnParamError(locale, "categoryKey");
        }

        Workbook fromWorkbook = null;
        File fromFile = new File(filePath);
        UtilMessageCache.clearMessageCache(categoryKey);
        String percent ="";
        try {
            fromWorkbook = Workbook.getWorkbook(fromFile);
            Sheet fromSheet = fromWorkbook.getSheet(0);
            int rows = fromSheet.getRows();
            int succCount = 0 ;
            int dataRows = rows -1 ;
            // 从第二行开始读取
            for (int line = 1; line < rows; line++) {
                final Map<String, Object> params  = FastMap.newInstance();
                String partyId ="";
                percent = String.valueOf(((line-1)*100/dataRows));

                try {
                    String partyTypeId = "PERSON";
                    partyId = fromSheet.getCell(0, line).getContents();
                    String partyClassificationGroupId = fromSheet.getCell(1,line).getContents();//
                    String firstName = fromSheet.getCell(2, line).getContents();
                    String tel = fromSheet.getCell(3, line).getContents();
                    String title = fromSheet.getCell(4, line).getContents();
                    String description = fromSheet.getCell(5, line).getContents();


                    if(UtilValidate.isEmpty(partyId)){
                        UtilMessageCache.addErrorMessage(categoryKey, String.format("%s行出错：个人编号不能为空", line + 1),percent, true);
                        return UtilMessages.returnParamError(locale, "partyId");
                    }

                    if(UtilValidate.isEmpty(partyClassificationGroupId)){
                        UtilMessageCache.addErrorMessage(categoryKey, String.format("%s行出错：部门编号不能为空", line + 1),percent, true);
                        return UtilMessages.returnParamError(locale, "partyClassificationGroupId");
                    }

                    if(UtilValidate.isEmpty(firstName)){
                        UtilMessageCache.addErrorMessage(categoryKey, String.format("%s行出错：真实姓名不能为空", line + 1),percent, true);
                        return UtilMessages.returnParamError(locale, "firstName");
                    }

                    if(UtilValidate.isEmpty(tel)){
                        UtilMessageCache.addErrorMessage(categoryKey, String.format("%s行出错：联系电话不能为空", line + 1),percent, true);
                        return UtilMessages.returnParamError(locale, "tel");
                    }

                    if(UtilValidate.isEmpty(title)){
                        UtilMessageCache.addErrorMessage(categoryKey, String.format("%s行出错：职位不能为空", line + 1),percent, true);
                        return UtilMessages.returnParamError(locale, "tel");
                    }

                    if(UtilValidate.isEmpty(description)){
                        description = firstName;
                    }

                    params.put("partyTypeId",partyTypeId);
                    params.put("partyId",partyId);
                    params.put("partyClassificationGroupId",partyClassificationGroupId);
                    params.put("firstName",firstName);
                    params.put("tel",tel);
                    params.put("title",title);
                    params.put("description",description);
                    params.put("locale",locale);
                    params.put("successIfExist",true);

                    Map<String,Object>  result= createPerson(params);
                    if(UtilMessages.isError(result)){
                        String errorMessage = (String)result.get("errorMessage");
                        UtilMessageCache.addErrorMessage(categoryKey, String.format("%s行出错：%s", line + 1,errorMessage),percent, true);
                        continue;
                    }

                    params.clear();
                    String currentPassword = "gorun8.cn";
                    String userLoginId = partyId;

                    params.put("userLoginId",true);
                    params.put("currentPassword",currentPassword);
                    params.put("partyId",partyId);
                    params.put("successIfExist",true);
                    params.put("locale",locale);

                    Map<String,Object> result2 = userLoginService.createUserLogin(params);
                    if(UtilMessages.isError(result2)){
                        String errorMessage = (String)result.get("errorMessage");
                        UtilMessageCache.addErrorMessage(categoryKey, String.format("%s行出错：%s", line + 1,errorMessage),percent, true);
                        continue;
                    }

                } catch (Exception e) {
                    UtilMessageCache.addErrorMessage(categoryKey, String.format("%s行出错", line + 1),percent, true);
                    return UtilMessages.returnError("");
                }

                ++succCount ;
                UtilMessageCache.addSuccessMessage(categoryKey, String.format("%s行, %s 成功", line + 1, partyId),percent, false);
            }//end for

            UtilMessageCache.addSuccessMessage(categoryKey, "导入用户结束，成功记录数：" + succCount,percent, true);
        } catch (Exception e) {
            e.printStackTrace();
            UtilMessageCache.addErrorMessage(categoryKey, "导入用户失败",percent, true);
            return UtilMessages.returnError("");
        } finally {
            if(fromWorkbook != null) {
                fromWorkbook.close();
            }
        }

        return UtilMessages.returnSuccess();
    }

    /**
     * Sets a party status.
     * <b>security check</b>: the status change must be defined in StatusValidChange.
     */
    public  Map<String, Object> setPartyStatus(Map<String, ? extends Object> context) {
        Locale locale = (Locale) context.get("locale");
        String partyId = (String) context.get("partyId");
        String statusId = (String) context.get("statusId");
        Timestamp statusDate = (Timestamp) context.get("statusDate");
        if (statusDate == null) {
            statusDate = UtilDateTime.nowTimestamp();
        }

        try {
            GenericValue party = partyReadDao.findPartyById(partyId);

            if (party.getString("statusId") == null) { // old records
                party.setString("statusId", "PARTY_ENABLED");
            }

            String oldStatusId =  party.getString("statusId");
            if (!party.getString("statusId").equals(statusId)) {

                // check that status is defined as a valid change
                GenericValue status = GenericValue.fromMap(UtilMisc.toMap("statusId", party.getString("statusId"), "statusIdTo", statusId),true);
                GenericValue statusValidChange = commonReadDao.findStatusValidChange(status);
                if (statusValidChange == null) {
                    String errorMsg = "Cannot change party status from " + party.getString("statusId") + " to " + statusId;
                    Debug.logWarning(errorMsg, module);
                    return UtilMessages.returnError(UtilProperties.getMessage(resource, 
                            "PartyStatusCannotBeChanged", 
                            UtilMisc.toMap("partyFromStatusId", party.getString("statusId"),
                            "partyToStatusId", statusId), locale)); 
                }

                party.setString("statusId", statusId);
                partyWriteDao.saveParty(party);

                // record this status change in PartyStatus table
                GenericValue partyStatus = GenericValue.fromMap(UtilMisc.toMap("partyId", partyId, "statusId", statusId, "statusDate", statusDate),true);
                partyWriteDao.createPartyStatus(partyStatus);


                // disable all userlogins for this user when the new status is disabled
                if (("PARTY_DISABLED").equals(statusId)) {
                    GenericValue param = new GenericValue();
                    param.setString("partyId",partyId);
                    List <GenericValue> userLogins = userLoginReadDao.findUserLoginByPartyId(param);
                    for(GenericValue userLogin : userLogins) {
                        if (!"N".equals(userLogin.getString("ENABLED"))) {
                            userLogin.setString("ENABLED", "N");
                            userLoginWriteDao.saveUserLogin(userLogin);
                        }
                    }
                }
            }

            Map<String, Object> results = UtilMessages.returnSuccess();
            results.put("oldStatusId", oldStatusId);
            return results;
        } catch (Exception e) {
            Debug.logError(e, e.getMessage(), module);
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError, 
                    "person.update.write_failure", new Object[] { e.getMessage() }, locale));
        }
    }

    /**
     * Updates a Person.
     * @param context Map containing the input parameters.
     * @return Map with the result of the service, the output parameters.
     */
    public   Map<String, Object> updatePerson(Map<String, ? extends Object> context) {
        Map<String, Object> result = FastMap.newInstance();
        Locale locale = (Locale) context.get("locale");
        String partyId = (String) context.get("partyId");

        if (UtilValidate.isEmpty(partyId)) {
            return UtilMessages.returnParamError(locale, "partyId");
        }

        GenericValue person = null;
        GenericValue party = null;

        try {
            person = partyReadDao.findPersonById(partyId);
            party = partyReadDao.findPartyById(partyId);
        } catch (Exception e) {
            Debug.logWarning(e, module);
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError, 
                    "person.update.read_failure", new Object[] { e.getMessage() }, locale));
        }

        if (person == null || party == null) {
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError, 
                    "person.update.not_found", locale));
        }

        // update status by separate service
         String oldStatusId = party.getString("statusId");
//         if (party.getString("statusId") == null) { // old records
//             party.setString("statusId", "PARTY_ENABLED");
//         }

        List<String> fields = UtilMisc.toList("salutation","firstName","middleName","lastName");
        fields.add("personalTitle");
        fields.add("suffix");
        fields.add("nickname");
        fields.add("firstNameLocal");
        fields.add("middleNameLocal");
        fields.add("lastNameLocal");
        fields.add("memberId");
        fields.add("gender");
        fields.add("birthDate");
        fields.add("comments");
        fields.add("cardId");
        person.setNonPKFields(context,fields);

        fields =UtilMisc.toList("partyId","partyTypeId","externalId","preferredCurrencyUomId","description");
        fields.add("createdDate");
        fields.add("createdByUserLogin");
        fields.add("lastModifiedDate");
        fields.add("lastModifiedByUserLogin");
        fields.add("dataSourceId");
        fields.add("isUnread");
        party.setNonPKFields(context, fields);

        party.setString("statusId", oldStatusId);

        try {
            partyWriteDao.savePerson(person);
            partyWriteDao.saveParty(party);
        } catch (Exception e) {
            Debug.logWarning(e.getMessage(), module);
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError, 
                    "person.update.write_failure", new Object[] { e.getMessage() }, locale));
        }

        if (UtilValidate.isNotEmpty(context.get("statusId")) && !context.get("statusId").equals(oldStatusId)) {
            try {
                setPartyStatus(UtilMisc.toMap("partyId", partyId, "statusId", context.get("statusId"), "userLogin", context.get("userLogin")));
            } catch (Exception e) {
                Debug.logWarning(e.getMessage(), module);
                return UtilMessages.returnError(UtilProperties.getMessage(resourceError, 
                        "person.update.write_failure", new Object[] { e.getMessage() }, locale));
            }
        }

        return UtilMessages.returnSuccess(UtilProperties.getMessage(resourceError, "person.update.success", locale));
    }

    public Map<String, Object> updatePartyDesc(Map<String, ? extends Object> context){

        Map<String, Object> result = FastMap.newInstance();
        Locale locale = (Locale) context.get("locale");
        String partyId = (String) context.get("partyId");
        String description = (String) context.get("description");

        if (UtilValidate.isEmpty(partyId)) {
            return UtilMessages.returnParamError(locale, "partyId");
        }

        GenericValue party = null;
        try {
            party = partyReadDao.findPartyById(partyId);
        } catch (Exception e) {
            Debug.logWarning(e, module);
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "person.update.read_failure", new Object[] { e.getMessage() }, locale));
        }

        if (  party == null) {
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "person.update.not_found", locale));
        }

        party.setString("description",description);

        try {
            partyWriteDao.saveParty(party);
        } catch (Exception e) {
            Debug.logWarning(e.getMessage(), module);
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "person.update.write_failure", new Object[] { e.getMessage() }, locale));
        }

        return UtilMessages.returnSuccess(UtilProperties.getMessage(resourceError, "person.update.success", locale));
   }


    /**
     * Creates a PartyGroup.
     * If no partyId is specified a numeric partyId is retrieved from the Party sequence.
     * @param context Map containing the input parameters.
     * @return Map with the result of the service, the output parameters.
     */
    public   Map<String, Object> createPartyGroup(Map<String, ? extends Object> context) {
        Map<String, Object> result = FastMap.newInstance();
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        Timestamp now = UtilDateTime.nowTimestamp();

        String partyId = (String) context.get("partyId");
        Locale locale = (Locale) context.get("locale");

        // partyId might be empty, so check it and get next seq party id if empty
        if (UtilValidate.isEmpty(partyId)) {
            try {
                partyId = SequenceFactory.getInstance().getNextSeqId("Party");
            } catch (IllegalArgumentException e) {
                return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                        "partyservices.could_not_create_party_group_generation_failure", locale));
            }
        } else {
            // if specified partyId starts with a number, return an error
            if (partyId.matches("\\d+")) {
                return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                        "partyservices.could_not_create_party_ID_digit", locale));
            }
        }

        try {
            // check to see if party object exists, if so make sure it is PARTY_GROUP type party
            // delegator.findByPrimaryKey("Party", UtilMisc.toMap("partyId", partyId));
            GenericValue party = partyReadDao.findPartyById(partyId);
            GenericValue partyGroupPartyType = partyReadDao.findPartyTypeById("PARTY_GROUP");
            //delegator.findByPrimaryKeyCache("PartyType", UtilMisc.toMap("partyTypeId", "PARTY_GROUP"));

            if (partyGroupPartyType == null) {
                return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                        "partyservices.partyservices.party_type_not_found_in_database_cannot_create_party_group", locale));
            }

            if (party != null) {
                String myPartyId = party.getString("PartyTypeId");
                GenericValue partyType =partyReadDao.findPartyTypeById(myPartyId);
                //party.getRelatedOneCache("PartyType");

                if (!PartyTypeUtil.isType(partyReadDao,partyType, partyGroupPartyType)) {
                    return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                            "partyservices.partyservices.cannot_create_party_group_already_exists_not_PARTY_GROUP_type", locale));
                }
            } else {
                // create a party if one doesn't already exist
                String partyTypeId = "PARTY_GROUP";

                if (UtilValidate.isNotEmpty(context.get("partyTypeId"))) {
                    //GenericValue desiredPartyType = delegator.findByPrimaryKeyCache("PartyType", UtilMisc.toMap("partyTypeId", context.get("partyTypeId")));
                    GenericValue desiredPartyType =partyReadDao.findPartyTypeById((String)context.get("partyTypeId"));

                    if (desiredPartyType != null && PartyTypeUtil.isType(partyReadDao,desiredPartyType, partyGroupPartyType)) {
                        partyTypeId = desiredPartyType.getString("partyTypeId");
                    } else {
                        return UtilMessages.returnError(UtilProperties.getMessage(resource,
                                "PartyPartyTypeIdNotFound", UtilMisc.toMap("partyTypeId", context.get("partyTypeId")), locale));
                    }
                }

                Map<String, Object> newPartyMap = UtilMisc.toMap("partyId", partyId, "partyTypeId", partyTypeId, "createdDate", now, "lastModifiedDate", now);
                if (userLogin != null) {
                    newPartyMap.put("createdByUserLogin", userLogin.get("userLoginId"));
                    newPartyMap.put("lastModifiedByUserLogin", userLogin.get("userLoginId"));
                }

                party = GenericValue.fromMap(newPartyMap,true);
                //party = delegator.makeValue("Party", newPartyMap);
                List<String> fields =UtilMisc.toList("partyId","partyTypeId","externalId","preferredCurrencyUomId","description");
                fields.add("createdDate");
                fields.add("createdByUserLogin");
                fields.add("lastModifiedDate");
                fields.add("lastModifiedByUserLogin");
                fields.add("dataSourceId");
                fields.add("isUnread");
                party.setNonPKFields(context,fields);

                String statusId = (String) context.get("statusId");
                if (statusId == null) {
                    statusId = "PARTY_ENABLED";
                }
                party.setString("statusId", statusId);
                partyWriteDao.createParty(party);
                GenericValue partyStat = GenericValue.fromMap(UtilMisc.toMap("partyId", partyId, "statusId", statusId, "statusDate", now),true);
                // create the status history
                partyWriteDao.createPartyStatus(partyStat);
//                GenericValue partyStat =delegator.makeValue("PartyStatus",statusgv);
//                partyStat.create();
            }

            GenericValue partyGroup = partyReadDao.findPartyGroupById(partyId);
            //delegator.findByPrimaryKey("PartyGroup", UtilMisc.toMap("partyId", partyId));
            if (partyGroup != null) {
                return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                        "partyservices.cannot_create_party_group_already_exists", locale));
            }
           //delegator.makeValue("PartyGroup", UtilMisc.toMap("partyId", partyId));
            partyGroup = GenericValue.fromMap(UtilMisc.toMap("partyId", partyId),true);

            List<String> fields = UtilMisc.toList("groupName", "groupNameLocal", "officeSiteName", "annualRevenue", "numEmployees", "tickerSymbol");
            fields.add("comments");
            fields.add("logoImageUrl");
            partyGroup.setNonPKFields(context, fields);
            partyGroup.setString("lastUpdatedStamp", now);
            partyGroup.setString("lastUpdatedTxStamp",now);
            //partyGroup.create();
            partyWriteDao.createPartyGroup(partyGroup);

        } catch (Exception e) {
            Debug.logWarning(e, module);
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "partyservices.data_source_error_adding_party_group",
                    UtilMisc.toMap("errMessage", e.getMessage()), locale));
        }

        result.put("partyId", partyId);
        result.put(UtilMessages.RESPONSE_TYPE, UtilMessages.RESPOND_SUCCESS);
        return result;
    }

    /**
     * Updates a PartyGroup.
     * @param context Map containing the input parameters.
     * @return Map with the result of the service, the output parameters.
     */
    public   Map<String, Object> updatePartyGroup(Map<String, ? extends Object> context) {
        Map<String, Object> result = FastMap.newInstance();
        Locale locale = (Locale) context.get("locale");
        String partyId = (String) context.get("partyId");

        if (UtilValidate.isEmpty(partyId)) {
            return UtilMessages.returnParamError(locale, "partyId");
        }

        GenericValue partyGroup = null;
        GenericValue party = null;

        try {
            partyGroup = partyReadDao.findPartyGroupById(partyId) ;
            //delegator.findByPrimaryKey("PartyGroup", UtilMisc.toMap("partyId", partyId));
            party = partyReadDao.findPartyById(partyId);
            //party = delegator.findByPrimaryKey("Party", UtilMisc.toMap("partyId", partyId));
        } catch (Exception e) {
            Debug.logWarning(e, module);
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "partyservices.could_not_update_party_information_read",
                    UtilMisc.toMap("errMessage", e.getMessage()), locale));
        }

        if (partyGroup == null || party == null) {
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "partyservices.could_not_update_party_information_not_found", locale));
        }

        // update status by separate service
        String oldStatusId = party.getString("statusId");
        List<String> fields = UtilMisc.toList("groupName", "groupNameLocal", "officeSiteName", "annualRevenue", "numEmployees", "tickerSymbol");
        fields.add("comments");
        fields.add("logoImageUrl");
        partyGroup.setNonPKFields(context, fields);

        fields =UtilMisc.toList("partyId","partyTypeId","externalId","preferredCurrencyUomId","description");
        fields.add("createdDate");
        fields.add("createdByUserLogin");
        fields.add("lastModifiedDate");
        fields.add("lastModifiedByUserLogin");
        fields.add("dataSourceId");
        fields.add("isUnread");
        party.setNonPKFields(context, fields);
        party.setString("statusId", oldStatusId);

        try {
            partyWriteDao.savePartyGroup(partyGroup);
            partyWriteDao.saveParty(party);
        } catch (Exception e) {
            Debug.logWarning(e.getMessage(), module);
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "partyservices.could_not_update_party_information_write",
                    UtilMisc.toMap("errMessage", e.getMessage()), locale));
        }

        if (UtilValidate.isNotEmpty(context.get("statusId")) && !context.get("statusId").equals(oldStatusId)) {
            try {
                setPartyStatus(UtilMisc.toMap("partyId", partyId, "statusId", context.get("statusId"), "userLogin", context.get("userLogin")));
            } catch (Exception e) {
                Debug.logWarning(e.getMessage(), module);
                return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                        "person.update.write_failure", new Object[] { e.getMessage() }, locale));
            }
        }

        result.put(UtilMessages.RESPONSE_TYPE, UtilMessages.RESPOND_SUCCESS);
        return result;
    }
//
//    /**
//     * Create an Affiliate entity.
//     * @param ctx The DispatchContext that this service is operating in.
//     * @param context Map containing the input parameters.
//     * @return Map with the result of the service, the output parameters.
//     */
//    public static Map<String, Object> createAffiliate(DispatchContext ctx, Map<String, ? extends Object> context) {
//        Map<String, Object> result = FastMap.newInstance();
//        Delegator delegator = ctx.getDelegator();
//        Locale locale = (Locale) context.get("locale");
//        Timestamp now = UtilDateTime.nowTimestamp();
//
//        String partyId = getPartyId(context);
//
//        // if specified partyId starts with a number, return an error
//        if (UtilValidate.isNotEmpty(partyId) && partyId.matches("\\d+")) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.cannot_create_affiliate_digit", locale));
//        }
//
//        // partyId might be empty, so check it and get next seq party id if empty
//        if (UtilValidate.isEmpty(partyId)) {
//            try {
//                partyId = delegator.getNextSeqId("Party");
//            } catch (IllegalArgumentException e) {
//                return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                        "partyservices.cannot_create_affiliate_generation_failure", locale));
//            }
//        }
//
//        // check to see if party object exists, if so make sure it is AFFILIATE type party
//        GenericValue party = null;
//
//        try {
//            party = delegator.findByPrimaryKey("Party", UtilMisc.toMap("partyId", partyId));
//        } catch (GenericEntityException e) {
//            Debug.logWarning(e.getMessage(), module);
//        }
//
//        if (party == null) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.cannot_create_affiliate_no_party_entity", locale));
//        }
//
//        GenericValue affiliate = null;
//
//        try {
//            affiliate = delegator.findByPrimaryKey("Affiliate", UtilMisc.toMap("partyId", partyId));
//        } catch (GenericEntityException e) {
//            Debug.logWarning(e.getMessage(), module);
//        }
//
//        if (affiliate != null) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.cannot_create_affiliate_ID_already_exists", locale));
//        }
//
//        affiliate = delegator.makeValue("Affiliate", UtilMisc.toMap("partyId", partyId));
//        affiliate.setNonPKFields(context);
//        affiliate.setString("dateTimeCreated", now, false);
//
//        try {
//            delegator.create(affiliate);
//        } catch (GenericEntityException e) {
//            Debug.logWarning(e.getMessage(), module);
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.could_not_add_affiliate_info_write",
//                    UtilMisc.toMap("errMessage", e.getMessage()), locale));
//        }
//
//        result.put("partyId", partyId);
//        result.put(UtilMessages.RESPONSE_MESSAGE, UtilMessages.RESPOND_SUCCESS);
//        return result;
//    }
//
//    /**
//     * Updates an Affiliate.
//     * @param ctx The DispatchContext that this service is operating in.
//     * @param context Map containing the input parameters.
//     * @return Map with the result of the service, the output parameters.
//     */
//    public static Map<String, Object> updateAffiliate(DispatchContext ctx, Map<String, ? extends Object> context) {
//        Delegator delegator = ctx.getDelegator();
//        Locale locale = (Locale) context.get("locale");
//
//        String partyId = getPartyId(context);
//        if (UtilValidate.isEmpty(partyId)) {
//            return UtilMessages.returnError(UtilProperties.getMessage(UtilMessages.resource,
//                    "UtilMessages.party_id_missing", locale));
//        }
//
//        GenericValue affiliate = null;
//
//        try {
//            affiliate = delegator.findByPrimaryKey("Affiliate", UtilMisc.toMap("partyId", partyId));
//        } catch (GenericEntityException e) {
//            Debug.logWarning(e, module);
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.could_not_update_affiliate_information_read",
//                    UtilMisc.toMap("errMessage", e.getMessage()), locale));
//        }
//
//        if (affiliate == null) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.could_not_update_affiliate_information_not_found", locale));
//        }
//
//        affiliate.setNonPKFields(context);
//
//        try {
//            affiliate.store();
//        } catch (GenericEntityException e) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.could_not_update_affiliate_information_write",
//                    UtilMisc.toMap("errMessage", e.getMessage()), locale));
//        }
//        return UtilMessages.returnSuccess();
//    }
//
//    /**
//     * Add a PartyNote.
//     * @param dctx The DispatchContext that this service is operating in.
//     * @param context Map containing the input parameters.
//     * @return Map with the result of the service, the output parameters.
//     */
//    public static Map<String, Object> createPartyNote(DispatchContext dctx, Map<String, ? extends Object> context) {
//        Map<String, Object> result = FastMap.newInstance();
//        Delegator delegator = dctx.getDelegator();
//        LocalDispatcher dispatcher = dctx.getDispatcher();
//        GenericValue userLogin = (GenericValue) context.get("userLogin");
//        String noteString = (String) context.get("note");
//        String partyId = (String) context.get("partyId");
//        String noteId = (String) context.get("noteId");
//        String noteName = (String) context.get("noteName");
//        Locale locale = (Locale) context.get("locale");
//        //Map noteCtx = UtilMisc.toMap("note", noteString, "userLogin", userLogin);
//
//        //Make sure the note Id actually exists if one is passed to avoid a foreign key error below
//        if (noteId != null) {
//            try {
//                GenericValue value = delegator.findByPrimaryKey("NoteData", UtilMisc.toMap("noteId", noteId));
//                if (value == null) {
//                    Debug.logError("ERROR: Note id does not exist for : " + noteId + ", autogenerating." , module);
//                    noteId = null;
//                }
//            } catch (GenericEntityException e) {
//                Debug.logError(e, "ERROR: Note id does not exist for : " + noteId + ", autogenerating." , module);
//                noteId = null;
//            }
//        }
//
//        // if no noteId is specified, then create and associate the note with the userLogin
//        if (noteId == null) {
//            Map<String, Object> noteRes = null;
//            try {
//                noteRes = dispatcher.runSync("createNote", UtilMisc.toMap("partyId", userLogin.getString("partyId"),
//                         "note", noteString, "userLogin", userLogin, "locale", locale, "noteName", noteName));
//            } catch (GenericServiceException e) {
//                Debug.logError(e, e.getMessage(), module);
//                return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                        "PartyNoteCreationError", UtilMisc.toMap("errorString", e.getMessage()), locale));
//            }
//
//            if (noteRes.get(UtilMessages.RESPONSE_MESSAGE).equals(UtilMessages.RESPOND_ERROR))
//                return noteRes;
//
//            noteId = (String) noteRes.get("noteId");
//
//            if (UtilValidate.isEmpty(noteId)) {
//                return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                        "partyservices.problem_creating_note_no_noteId_returned", locale));
//            }
//        }
//        result.put("noteId", noteId);
//
//        // Set the party info
//        try {
//            Map<String, String> fields = UtilMisc.toMap("partyId", partyId, "noteId", noteId);
//            GenericValue v = delegator.makeValue("PartyNote", fields);
//
//            delegator.create(v);
//        } catch (GenericEntityException ee) {
//            Debug.logError(ee, module);
//            result.put(UtilMessages.RESPONSE_MESSAGE, UtilMessages.RESPOND_ERROR);
//            result.put(UtilMessages.ERROR_MESSAGE, UtilProperties.getMessage(resourceError,
//                    "partyservices.problem_associating_note_with_party",
//                    UtilMisc.toMap("errMessage", ee.getMessage()), locale));
//        }
//        return result;
//    }
//
//    /**
//     * Get the party object(s) from an e-mail address
//     * @param dctx The DispatchContext that this service is operating in.
//     * @param context Map containing the input parameters.
//     * @return Map with the result of the service, the output parameters.
//     */
//    public static Map<String, Object> getPartyFromExactEmail(DispatchContext dctx, Map<String, ? extends Object> context) {
//        Map<String, Object> result = FastMap.newInstance();
//        Delegator delegator = dctx.getDelegator();
//        Collection<Map<String, GenericValue>> parties = FastList.newInstance();
//        String email = (String) context.get("email");
//        Locale locale = (Locale) context.get("locale");
//
//        if (email.length() == 0) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.required_parameter_email_cannot_be_empty", locale));
//        }
//
//        try {
//            EntityExpr ee = EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("infoString"), EntityOperator.EQUALS, EntityFunction.UPPER(email.toUpperCase()));
//            List<GenericValue> c = EntityUtil.filterByDate(delegator.findList("PartyAndContactMech", ee, null, UtilMisc.toList("infoString"), null, false), true);
//
//            if (Debug.verboseOn()) Debug.logVerbose("List: " + c, module);
//            if (Debug.infoOn()) Debug.logInfo("PartyFromEmail number found: " + c.size(), module);
//            if (c != null) {
//                for (GenericValue pacm: c) {
//                    GenericValue party = delegator.makeValue("Party", UtilMisc.toMap("partyId", pacm.get("partyId"), "partyTypeId", pacm.get("partyTypeId")));
//
//                    parties.add(UtilMisc.<String, GenericValue>toMap("party", party));
//                }
//            }
//        } catch (GenericEntityException e) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.cannot_get_party_entities_read",
//                    UtilMisc.toMap("errMessage", e.getMessage()), locale));
//        }
//        if (parties.size() > 0)
//            result.put("parties", parties);
//        return result;
//    }
//
//    public static Map<String, Object> getPartyFromEmail(DispatchContext dctx, Map<String, ? extends Object> context) {
//        Map<String, Object> result = FastMap.newInstance();
//        Delegator delegator = dctx.getDelegator();
//        Collection<Map<String, GenericValue>> parties = FastList.newInstance();
//        String email = (String) context.get("email");
//        Locale locale = (Locale) context.get("locale");
//
//        if (email.length() == 0) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.required_parameter_email_cannot_be_empty", locale));
//        }
//
//        try {
//            EntityExpr ee = EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("infoString"), EntityOperator.LIKE, EntityFunction.UPPER(("%" + email.toUpperCase()) + "%"));
//            List<GenericValue> c = EntityUtil.filterByDate(delegator.findList("PartyAndContactMech", ee, null, UtilMisc.toList("infoString"), null, false), true);
//
//            if (Debug.verboseOn()) Debug.logVerbose("List: " + c, module);
//            if (Debug.infoOn()) Debug.logInfo("PartyFromEmail number found: " + c.size(), module);
//            if (c != null) {
//                for (GenericValue pacm: c) {
//                    GenericValue party = delegator.makeValue("Party", UtilMisc.toMap("partyId", pacm.get("partyId"), "partyTypeId", pacm.get("partyTypeId")));
//
//                    parties.add(UtilMisc.<String, GenericValue>toMap("party", party));
//                }
//            }
//        } catch (GenericEntityException e) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.cannot_get_party_entities_read",
//                    UtilMisc.toMap("errMessage", e.getMessage()), locale));
//        }
//        if (parties.size() > 0)
//            result.put("parties", parties);
//        return result;
//    }
//
//    /**
//     * Get the party object(s) from a user login ID
//     * @param dctx The DispatchContext that this service is operating in.
//     * @param context Map containing the input parameters.
//     * @return Map with the result of the service, the output parameters.
//     */
//    public static Map<String, Object> getPartyFromUserLogin(DispatchContext dctx, Map<String, ? extends Object> context) {
//        Debug.logWarning("Running the getPartyFromUserLogin Service...", module);
//        Map<String, Object> result = FastMap.newInstance();
//        Delegator delegator = dctx.getDelegator();
//        Collection<Map<String, GenericValue>> parties = FastList.newInstance();
//        String userLoginId = (String) context.get("userLoginId");
//        Locale locale = (Locale) context.get("locale");
//
//        if (userLoginId.length() == 0)
//            return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                    "PartyCannotGetUserLoginFromParty", locale));
//
//        try {
//            EntityExpr ee = EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("userLoginId"), EntityOperator.LIKE, EntityFunction.UPPER("%" + userLoginId.toUpperCase() + "%"));
//            Collection<GenericValue> ulc = delegator.findList("PartyAndUserLogin", ee, null, UtilMisc.toList("userLoginId"), null, false);
//
//            if (Debug.verboseOn()) Debug.logVerbose("Collection: " + ulc, module);
//            if (Debug.infoOn()) Debug.logInfo("PartyFromUserLogin number found: " + ulc.size(), module);
//            if (ulc != null) {
//                for (GenericValue ul: ulc) {
//                    GenericValue party = delegator.makeValue("Party", UtilMisc.toMap("partyId", ul.get("partyId"), "partyTypeId", ul.get("partyTypeId")));
//
//                    parties.add(UtilMisc.<String, GenericValue>toMap("party", party));
//                }
//            }
//        } catch (GenericEntityException e) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.cannot_get_party_entities_read",
//                    UtilMisc.toMap("errMessage", e.getMessage()), locale));
//        }
//        if (parties.size() > 0) {
//            result.put("parties", parties);
//        }
//        return result;
//    }
//
//    /**
//     * Get the party object(s) from person information
//     * @param dctx The DispatchContext that this service is operating in.
//     * @param context Map containing the input parameters.
//     * @return Map with the result of the service, the output parameters.
//     */
//    public static Map<String, Object> getPartyFromPerson(DispatchContext dctx, Map<String, ? extends Object> context) {
//        Map<String, Object> result = FastMap.newInstance();
//        Delegator delegator = dctx.getDelegator();
//        Collection<Map<String, GenericValue>> parties = FastList.newInstance();
//        String firstName = (String) context.get("firstName");
//        String lastName = (String) context.get("lastName");
//        Locale locale = (Locale) context.get("locale");
//
//        if (firstName == null) {
//            firstName = "";
//        }
//        if (lastName == null) {
//            lastName = "";
//        }
//        if (firstName.length() == 0 && lastName.length() == 0) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.both_names_cannot_be_empty", locale));
//        }
//
//        try {
//            EntityConditionList<EntityExpr> ecl = EntityCondition.makeCondition(EntityOperator.AND,
//                    EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("firstName"), EntityOperator.LIKE, EntityFunction.UPPER("%" + firstName.toUpperCase() + "%")),
//                    EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("lastName"), EntityOperator.LIKE, EntityFunction.UPPER("%" + lastName.toUpperCase() + "%")));
//            Collection<GenericValue> pc = delegator.findList("Person", ecl, null, UtilMisc.toList("lastName", "firstName", "partyId"), null, false);
//
//            if (Debug.infoOn()) Debug.logInfo("PartyFromPerson number found: " + pc.size(), module);
//            if (pc != null) {
//                for (GenericValue person: pc) {
//                    GenericValue party = delegator.makeValue("Party", UtilMisc.toMap("partyId", person.get("partyId"), "partyTypeId", "PERSON"));
//
//                    parties.add(UtilMisc.<String, GenericValue>toMap("person", person, "party", party));
//                }
//            }
//        } catch (GenericEntityException e) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.cannot_get_party_entities_read",
//                    UtilMisc.toMap("errMessage", e.getMessage()), locale));
//        }
//        if (parties.size() > 0) {
//            result.put("parties", parties);
//        }
//        return result;
//    }
//
//    /**
//     * Get the party object(s) from party group name.
//     * @param dctx The DispatchContext that this service is operating in.
//     * @param context Map containing the input parameters.
//     * @return Map with the result of the service, the output parameters.
//     */
//    public static Map<String, Object> getPartyFromPartyGroup(DispatchContext dctx, Map<String, ? extends Object> context) {
//        Map<String, Object> result = FastMap.newInstance();
//        Delegator delegator = dctx.getDelegator();
//        Collection<Map<String, GenericValue>> parties = FastList.newInstance();
//        String groupName = (String) context.get("groupName");
//        Locale locale = (Locale) context.get("locale");
//
//        if (groupName.length() == 0) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                    "PartyCannotGetPartyFromPartyGroup", locale));
//        }
//
//        try {
//            EntityExpr ee = EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("groupName"), EntityOperator.LIKE, EntityFunction.UPPER("%" + groupName.toUpperCase() + "%"));
//            Collection<GenericValue> pc = delegator.findList("PartyGroup", ee, null, UtilMisc.toList("groupName", "partyId"), null, false);
//
//            if (Debug.infoOn()) Debug.logInfo("PartyFromGroup number found: " + pc.size(), module);
//            if (pc != null) {
//                for (GenericValue group: pc) {
//                    GenericValue party = delegator.makeValue("Party", UtilMisc.toMap("partyId", group.get("partyId"), "partyTypeId", "PARTY_GROUP"));
//
//                    parties.add(UtilMisc.<String, GenericValue>toMap("partyGroup", group, "party", party));
//                }
//            }
//        } catch (GenericEntityException e) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.cannot_get_party_entities_read",
//                    UtilMisc.toMap("errMessage", e.getMessage()), locale));
//        }
//        if (parties.size() > 0) {
//            result.put("parties", parties);
//        }
//        return result;
//    }
//
//    public static Map<String, Object> getPerson(DispatchContext dctx, Map<String, ? extends Object> context) {
//        Map<String, Object> result = FastMap.newInstance();
//        Delegator delegator = dctx.getDelegator();
//        String partyId = (String) context.get("partyId");
//        Locale locale = (Locale) context.get("locale");
//        GenericValue person = null;
//
//        try {
//            person = delegator.findByPrimaryKeyCache("Person", UtilMisc.toMap("partyId", partyId));
//        } catch (GenericEntityException e) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
//                    "partyservices.cannot_get_party_entities_read",
//                    UtilMisc.toMap("errMessage", e.getMessage()), locale));
//        }
//        if (person != null) {
//            result.put("lookupPerson", person);
//        }
//        return result;
//    }
//
//    public static Map<String, Object> createRoleType(DispatchContext dctx, Map<String, ? extends Object> context) {
//        Map<String, Object> result = FastMap.newInstance();
//        Delegator delegator = dctx.getDelegator();
//        Locale locale = (Locale) context.get("locale");
//        GenericValue roleType = null;
//
//        try {
//            roleType = delegator.makeValue("RoleType");
//            roleType.setPKFields(context);
//            roleType.setNonPKFields(context);
//            roleType = delegator.create(roleType);
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                    "PartyCannotCreateRoleTypeEntity",
//                    UtilMisc.toMap("errMessage", e.getMessage()), locale));
//        }
//        if (roleType != null) {
//            result.put("roleType", roleType);
//        }
//        return result;
//    }
//
//    public static Map<String, Object> createPartyDataSource(DispatchContext ctx, Map<String, ? extends Object> context) {
//        Delegator delegator = ctx.getDelegator();
//        Locale locale = (Locale) context.get("locale");
//
//        // input data
//        String partyId = (String) context.get("partyId");
//        String dataSourceId = (String) context.get("dataSourceId");
//        Timestamp fromDate = (Timestamp) context.get("fromDate");
//        if (fromDate == null) fromDate = UtilDateTime.nowTimestamp();
//
//        try {
//            // validate the existance of party and dataSource
//            GenericValue party = delegator.findByPrimaryKey("Party", UtilMisc.toMap("partyId", partyId));
//            GenericValue dataSource = delegator.findByPrimaryKey("DataSource", UtilMisc.toMap("dataSourceId", dataSourceId));
//            if (party == null || dataSource == null) {
//                List<String> errorList = UtilMisc.toList(UtilProperties.getMessage(resource,
//                        "PartyCannotCreatePartyDataSource", locale));
//                if (party == null) {
//                    errorList.add(UtilProperties.getMessage(resource,
//                            "PartyNoPartyFoundWithPartyId", locale) + partyId);
//                }
//                if (dataSource == null) {
//                    errorList.add(UtilProperties.getMessage(resource,
//                            "PartyNoPartyWithDataSourceId",
//                            UtilMisc.toMap("dataSourceId", dataSourceId), locale));
//                }
//                return UtilMessages.returnError(errorList);
//            }
//
//            // create the PartyDataSource
//            GenericValue partyDataSource = delegator.makeValue("PartyDataSource", UtilMisc.toMap("partyId", partyId, "dataSourceId", dataSourceId, "fromDate", fromDate));
//            partyDataSource.create();
//
//        } catch (GenericEntityException e) {
//            Debug.logError(e, e.getMessage(), module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//        return UtilMessages.returnSuccess();
//    }
//
//    public static Map<String, Object> findParty(DispatchContext dctx, Map<String, ? extends Object> context) {
//        Map<String, Object> result = UtilMessages.returnSuccess();
//        Delegator delegator = dctx.getDelegator();
//        GenericValue userLogin = (GenericValue) context.get("userLogin");
//        Locale locale = (Locale) context.get("locale");
//
//        String extInfo = (String) context.get("extInfo");
//
//        // get the role types
//        try {
//            List<GenericValue> roleTypes = delegator.findList("RoleType", null, null, UtilMisc.toList("description"), null, false);
//            result.put("roleTypes", roleTypes);
//        } catch (GenericEntityException e) {
//            String errMsg = "Error looking up RoleTypes: " + e.toString();
//            Debug.logError(e, errMsg, module);
//            return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                    "PartyLookupRoleTypeError",
//                    UtilMisc.toMap("errMessage", e.toString()), locale));
//        }
//
//        // current role type
//        String roleTypeId;
//        try {
//            roleTypeId = (String) context.get("roleTypeId");
//            if (UtilValidate.isNotEmpty(roleTypeId)) {
//                GenericValue currentRole = delegator.findByPrimaryKeyCache("RoleType", UtilMisc.toMap("roleTypeId", roleTypeId));
//                result.put("currentRole", currentRole);
//            }
//        } catch (GenericEntityException e) {
//            String errMsg = "Error looking up current RoleType: " + e.toString();
//            Debug.logError(e, errMsg, module);
//            return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                    "PartyLookupRoleTypeError",
//                    UtilMisc.toMap("errMessage", e.toString()), locale));
//        }
//
//        //get party types
//        try {
//            List<GenericValue> partyTypes = delegator.findList("PartyType", null, null, UtilMisc.toList("description"), null, false);
//            result.put("partyTypes", partyTypes);
//        } catch (GenericEntityException e) {
//            String errMsg = "Error looking up PartyTypes: " + e.toString();
//            Debug.logError(e, errMsg, module);
//            return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                    "PartyLookupPartyTypeError",
//                    UtilMisc.toMap("errMessage", e.toString()), locale));
//        }
//
//        // current party type
//        String partyTypeId;
//        try {
//            partyTypeId = (String) context.get("partyTypeId");
//            if (UtilValidate.isNotEmpty(partyTypeId)) {
//                GenericValue currentPartyType = delegator.findByPrimaryKeyCache("PartyType", UtilMisc.toMap("partyTypeId", partyTypeId));
//                result.put("currentPartyType", currentPartyType);
//            }
//        } catch (GenericEntityException e) {
//            String errMsg = "Error looking up current PartyType: " + e.toString();
//            Debug.logError(e, errMsg, module);
//            return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                    "PartyLookupPartyTypeError",
//                    UtilMisc.toMap("errMessage", e.toString()), locale));
//        }
//
//        // current state
//        String stateProvinceGeoId;
//        try {
//            stateProvinceGeoId = (String) context.get("stateProvinceGeoId");
//            if (UtilValidate.isNotEmpty(stateProvinceGeoId)) {
//                GenericValue currentStateGeo = delegator.findByPrimaryKeyCache("Geo", UtilMisc.toMap("geoId", stateProvinceGeoId));
//                result.put("currentStateGeo", currentStateGeo);
//            }
//        } catch (GenericEntityException e) {
//            String errMsg = "Error looking up current stateProvinceGeo: " + e.toString();
//            Debug.logError(e, errMsg, module);
//            return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                    "PartyLookupStateProvinceGeoError",
//                    UtilMisc.toMap("errMessage", e.toString()), locale));
//        }
//
//        // set the page parameters
//        int viewIndex = 0;
//        try {
//            viewIndex = Integer.parseInt((String) context.get("VIEW_INDEX"));
//        } catch (Exception e) {
//            viewIndex = 0;
//        }
//        result.put("viewIndex", Integer.valueOf(viewIndex));
//
//        int viewSize = 20;
//        try {
//            viewSize = Integer.parseInt((String) context.get("VIEW_SIZE"));
//        } catch (Exception e) {
//            viewSize = 20;
//        }
//        result.put("viewSize", Integer.valueOf(viewSize));
//
//        // get the lookup flag
//        String lookupFlag = (String) context.get("lookupFlag");
//
//        // blank param list
//        String paramList = "";
//
//        List<GenericValue> partyList = null;
//        int partyListSize = 0;
//        int lowIndex = 0;
//        int highIndex = 0;
//
//        if ("Y".equals(lookupFlag)) {
//            String showAll = (context.get("showAll") != null ? (String) context.get("showAll") : "N");
//            paramList = paramList + "&lookupFlag=" + lookupFlag + "&showAll=" + showAll + "&extInfo=" + extInfo;
//
//            // create the dynamic view entity
//            DynamicViewEntity dynamicView = new DynamicViewEntity();
//
//            // default view settings
//            dynamicView.addMemberEntity("PT", "Party");
//            dynamicView.addAlias("PT", "partyId");
//            dynamicView.addAlias("PT", "statusId");
//            dynamicView.addAlias("PT", "partyTypeId");
//            dynamicView.addAlias("PT", "createdDate");
//            dynamicView.addAlias("PT", "lastModifiedDate");
//            dynamicView.addRelation("one-nofk", "", "PartyType", ModelKeyMap.makeKeyMapList("partyTypeId"));
//            dynamicView.addRelation("many", "", "UserLogin", ModelKeyMap.makeKeyMapList("partyId"));
//
//            // define the main condition & expression list
//            List<EntityCondition> andExprs = FastList.newInstance();
//            EntityCondition mainCond = null;
//
//            List<String> orderBy = FastList.newInstance();
//            List<String> fieldsToSelect = FastList.newInstance();
//            // fields we need to select; will be used to set distinct
//            fieldsToSelect.add("partyId");
//            fieldsToSelect.add("statusId");
//            fieldsToSelect.add("partyTypeId");
//            fieldsToSelect.add("createdDate");
//            fieldsToSelect.add("lastModifiedDate");
//
//            // filter on parties that have relationship with logged in user
//            String partyRelationshipTypeId = (String) context.get("partyRelationshipTypeId");
//            if (UtilValidate.isNotEmpty(partyRelationshipTypeId)) {
//                // add relation to view
//                dynamicView.addMemberEntity("PRSHP", "PartyRelationship");
//                dynamicView.addAlias("PRSHP", "partyIdTo");
//                dynamicView.addAlias("PRSHP", "partyRelationshipTypeId");
//                dynamicView.addViewLink("PT", "PRSHP", Boolean.FALSE, ModelKeyMap.makeKeyMapList("partyId", "partyIdTo"));
//                List<String> ownerPartyIds = UtilGenerics.cast(context.get("ownerPartyIds"));
//                EntityCondition relationshipCond = null;
//                if (UtilValidate.isEmpty(ownerPartyIds)) {
//                    String partyIdFrom = userLogin.getString("partyId");
//                    paramList = paramList + "&partyIdFrom=" + partyIdFrom;
//                    relationshipCond = EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("partyIdFrom"), EntityOperator.EQUALS, EntityFunction.UPPER(partyIdFrom));
//                } else {
//                    relationshipCond = EntityCondition.makeCondition("partyIdFrom", EntityOperator.IN, ownerPartyIds);
//                }
//                dynamicView.addAlias("PRSHP", "partyIdFrom");
//                // add the expr
//                andExprs.add(EntityCondition.makeCondition(
//                        relationshipCond, EntityOperator.AND,
//                        EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("partyRelationshipTypeId"), EntityOperator.EQUALS, EntityFunction.UPPER(partyRelationshipTypeId))));
//                fieldsToSelect.add("partyIdTo");
//            }
//
//            // get the params
//            String partyId = (String) context.get("partyId");
//            String statusId = (String) context.get("statusId");
//            String userLoginId = (String) context.get("userLoginId");
//            String firstName = (String) context.get("firstName");
//            String lastName = (String) context.get("lastName");
//            String groupName = (String) context.get("groupName");
//
//            if (!"Y".equals(showAll)) {
//                // check for a partyId
//                if (UtilValidate.isNotEmpty(partyId)) {
//                    paramList = paramList + "&partyId=" + partyId;
//                    andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("partyId"), EntityOperator.LIKE, EntityFunction.UPPER("%"+partyId+"%")));
//                }
//
//                // now the statusId - send ANY for all statuses; leave null for just enabled; or pass a specific status
//                if (statusId != null) {
//                    paramList = paramList + "&statusId=" + statusId;
//                    if (!"ANY".equalsIgnoreCase(statusId)) {
//                        andExprs.add(EntityCondition.makeCondition("statusId", EntityOperator.EQUALS, statusId));
//                    }
//                } else {
//                    // NOTE: _must_ explicitly allow null as it is not included in a not equal in many databases... odd but true
//                    andExprs.add(EntityCondition.makeCondition(EntityCondition.makeCondition("statusId", EntityOperator.EQUALS, null), EntityOperator.OR, EntityCondition.makeCondition("statusId", EntityOperator.NOT_EQUAL, "PARTY_DISABLED")));
//                }
//                // check for partyTypeId
//                if (partyTypeId != null && !"ANY".equals(partyTypeId)) {
//                    paramList = paramList + "&partyTypeId=" + partyTypeId;
//                    andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("partyTypeId"), EntityOperator.LIKE, EntityFunction.UPPER("%"+partyTypeId+"%")));
//                }
//
//                // ----
//                // UserLogin Fields
//                // ----
//
//                // filter on user login
//                if (UtilValidate.isNotEmpty(userLoginId)) {
//                    paramList = paramList + "&userLoginId=" + userLoginId;
//
//                    // modify the dynamic view
//                    dynamicView.addMemberEntity("UL", "UserLogin");
//                    dynamicView.addAlias("UL", "userLoginId");
//                    dynamicView.addViewLink("PT", "UL", Boolean.FALSE, ModelKeyMap.makeKeyMapList("partyId"));
//
//                    // add the expr
//                    andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("userLoginId"), EntityOperator.LIKE, EntityFunction.UPPER("%"+userLoginId+"%")));
//
//                    fieldsToSelect.add("userLoginId");
//                }
//
//                // ----
//                // PartyGroup Fields
//                // ----
//
//                // filter on groupName
//                if (UtilValidate.isNotEmpty(groupName)) {
//                    paramList = paramList + "&groupName=" + groupName;
//
//                    // modify the dynamic view
//                    dynamicView.addMemberEntity("PG", "PartyGroup");
//                    dynamicView.addAlias("PG", "groupName");
//                    dynamicView.addViewLink("PT", "PG", Boolean.FALSE, ModelKeyMap.makeKeyMapList("partyId"));
//
//                    // add the expr
//                    andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("groupName"), EntityOperator.LIKE, EntityFunction.UPPER("%"+groupName+"%")));
//
//                    fieldsToSelect.add("groupName");
//                }
//
//                // ----
//                // Person Fields
//                // ----
//
//                // modify the dynamic view
//                if (UtilValidate.isNotEmpty(firstName) || UtilValidate.isNotEmpty(lastName)) {
//                    dynamicView.addMemberEntity("PE", "Person");
//                    dynamicView.addAlias("PE", "firstName");
//                    dynamicView.addAlias("PE", "lastName");
//                    dynamicView.addViewLink("PT", "PE", Boolean.FALSE, ModelKeyMap.makeKeyMapList("partyId"));
//
//                    fieldsToSelect.add("firstName");
//                    fieldsToSelect.add("lastName");
//                    orderBy.add("lastName");
//                    orderBy.add("firstName");
//                }
//
//                // filter on firstName
//                if (UtilValidate.isNotEmpty(firstName)) {
//                    paramList = paramList + "&firstName=" + firstName;
//                    andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("firstName"), EntityOperator.LIKE, EntityFunction.UPPER("%"+firstName+"%")));
//                }
//
//                // filter on lastName
//                if (UtilValidate.isNotEmpty(lastName)) {
//                    paramList = paramList + "&lastName=" + lastName;
//                    andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("lastName"), EntityOperator.LIKE, EntityFunction.UPPER("%"+lastName+"%")));
//                }
//
//                // ----
//                // RoleType Fields
//                // ----
//
//                // filter on role member
//                if (roleTypeId != null && !"ANY".equals(roleTypeId)) {
//                    paramList = paramList + "&roleTypeId=" + roleTypeId;
//
//                    // add role to view
//                    dynamicView.addMemberEntity("PR", "PartyRole");
//                    dynamicView.addAlias("PR", "roleTypeId");
//                    dynamicView.addViewLink("PT", "PR", Boolean.FALSE, ModelKeyMap.makeKeyMapList("partyId"));
//
//                    // add the expr
//                    andExprs.add(EntityCondition.makeCondition("roleTypeId", EntityOperator.EQUALS, roleTypeId));
//
//                    fieldsToSelect.add("roleTypeId");
//                }
//
//                // ----
//                // InventoryItem Fields
//                // ----
//
//                // filter on inventory item's fields
//                String inventoryItemId = (String) context.get("inventoryItemId");
//                String serialNumber = (String) context.get("serialNumber");
//                String softIdentifier = (String) context.get("softIdentifier");
//                if (UtilValidate.isNotEmpty(inventoryItemId) ||
//                    UtilValidate.isNotEmpty(serialNumber) ||
//                    UtilValidate.isNotEmpty(softIdentifier)) {
//
//                    // add role to view
//                    dynamicView.addMemberEntity("II", "InventoryItem");
//                    dynamicView.addAlias("II", "ownerPartyId");
//                    dynamicView.addViewLink("PT", "II", Boolean.FALSE, ModelKeyMap.makeKeyMapList("partyId", "ownerPartyId"));
//                }
//                if (UtilValidate.isNotEmpty(inventoryItemId)) {
//                    paramList = paramList + "&inventoryItemId=" + inventoryItemId;
//                    dynamicView.addAlias("II", "inventoryItemId");
//                    // add the expr
//                    andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("inventoryItemId"), EntityOperator.LIKE, EntityFunction.UPPER("%" + inventoryItemId + "%")));
//                    fieldsToSelect.add("inventoryItemId");
//                }
//                if (UtilValidate.isNotEmpty(serialNumber)) {
//                    paramList = paramList + "&serialNumber=" + serialNumber;
//                    dynamicView.addAlias("II", "serialNumber");
//                    // add the expr
//                    andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("serialNumber"), EntityOperator.LIKE, EntityFunction.UPPER("%" + serialNumber + "%")));
//                    fieldsToSelect.add("serialNumber");
//                }
//                if (UtilValidate.isNotEmpty(softIdentifier)) {
//                    paramList = paramList + "&softIdentifier=" + softIdentifier;
//                    dynamicView.addAlias("II", "softIdentifier");
//                    // add the expr
//                    andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("softIdentifier"), EntityOperator.LIKE, EntityFunction.UPPER("%" + softIdentifier + "%")));
//                    fieldsToSelect.add("softIdentifier");
//                }
//
//                // ----
//                // PostalAddress fields
//                // ----
//                if ("P".equals(extInfo)) {
//                    // add address to dynamic view
//                    dynamicView.addMemberEntity("PC", "PartyContactMech");
//                    dynamicView.addMemberEntity("PA", "PostalAddress");
//                    dynamicView.addAlias("PC", "contactMechId");
//                    dynamicView.addAlias("PA", "address1");
//                    dynamicView.addAlias("PA", "address2");
//                    dynamicView.addAlias("PA", "city");
//                    dynamicView.addAlias("PA", "stateProvinceGeoId");
//                    dynamicView.addAlias("PA", "countryGeoId");
//                    dynamicView.addAlias("PA", "postalCode");
//                    dynamicView.addViewLink("PT", "PC", Boolean.FALSE, ModelKeyMap.makeKeyMapList("partyId"));
//                    dynamicView.addViewLink("PC", "PA", Boolean.FALSE, ModelKeyMap.makeKeyMapList("contactMechId"));
//
//                    // filter on address1
//                    String address1 = (String) context.get("address1");
//                    if (UtilValidate.isNotEmpty(address1)) {
//                        paramList = paramList + "&address1=" + address1;
//                        andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("address1"), EntityOperator.LIKE, EntityFunction.UPPER("%" + address1 + "%")));
//                    }
//
//                    // filter on address2
//                    String address2 = (String) context.get("address2");
//                    if (UtilValidate.isNotEmpty(address2)) {
//                        paramList = paramList + "&address2=" + address2;
//                        andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("address2"), EntityOperator.LIKE, EntityFunction.UPPER("%" + address2 + "%")));
//                    }
//
//                    // filter on city
//                    String city = (String) context.get("city");
//                    if (UtilValidate.isNotEmpty(city)) {
//                        paramList = paramList + "&city=" + city;
//                        andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("city"), EntityOperator.LIKE, EntityFunction.UPPER("%" + city + "%")));
//                    }
//
//                    // filter on state geo
//                    if (stateProvinceGeoId != null && !"ANY".equals(stateProvinceGeoId)) {
//                        paramList = paramList + "&stateProvinceGeoId=" + stateProvinceGeoId;
//                        andExprs.add(EntityCondition.makeCondition("stateProvinceGeoId", EntityOperator.EQUALS, stateProvinceGeoId));
//                    }
//
//                    // filter on postal code
//                    String postalCode = (String) context.get("postalCode");
//                    if (UtilValidate.isNotEmpty(postalCode)) {
//                        paramList = paramList + "&postalCode=" + postalCode;
//                        andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("postalCode"), EntityOperator.LIKE, EntityFunction.UPPER("%" + postalCode + "%")));
//                    }
//
//                    fieldsToSelect.add("postalCode");
//                    fieldsToSelect.add("city");
//                    fieldsToSelect.add("stateProvinceGeoId");
//                }
//
//                // ----
//                // Generic CM Fields
//                // ----
//                if ("O".equals(extInfo)) {
//                    // add info to dynamic view
//                    dynamicView.addMemberEntity("PC", "PartyContactMech");
//                    dynamicView.addMemberEntity("CM", "ContactMech");
//                    dynamicView.addAlias("PC", "contactMechId");
//                    dynamicView.addAlias("CM", "infoString");
//                    dynamicView.addViewLink("PT", "PC", Boolean.FALSE, ModelKeyMap.makeKeyMapList("partyId"));
//                    dynamicView.addViewLink("PC", "CM", Boolean.FALSE, ModelKeyMap.makeKeyMapList("contactMechId"));
//
//                    // filter on infoString
//                    String infoString = (String) context.get("infoString");
//                    if (UtilValidate.isNotEmpty(infoString)) {
//                        paramList = paramList + "&infoString=" + infoString;
//                        andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("infoString"), EntityOperator.LIKE, EntityFunction.UPPER("%"+infoString+"%")));
//                        fieldsToSelect.add("infoString");
//                    }
//
//                }
//
//                // ----
//                // TelecomNumber Fields
//                // ----
//                if ("T".equals(extInfo)) {
//                    // add telecom to dynamic view
//                    dynamicView.addMemberEntity("PC", "PartyContactMech");
//                    dynamicView.addMemberEntity("TM", "TelecomNumber");
//                    dynamicView.addAlias("PC", "contactMechId");
//                    dynamicView.addAlias("TM", "countryCode");
//                    dynamicView.addAlias("TM", "areaCode");
//                    dynamicView.addAlias("TM", "contactNumber");
//                    dynamicView.addViewLink("PT", "PC", Boolean.FALSE, ModelKeyMap.makeKeyMapList("partyId"));
//                    dynamicView.addViewLink("PC", "TM", Boolean.FALSE, ModelKeyMap.makeKeyMapList("contactMechId"));
//
//                    // filter on countryCode
//                    String countryCode = (String) context.get("countryCode");
//                    if (UtilValidate.isNotEmpty(countryCode)) {
//                        paramList = paramList + "&countryCode=" + countryCode;
//                        andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("countryCode"), EntityOperator.EQUALS, EntityFunction.UPPER(countryCode)));
//                    }
//
//                    // filter on areaCode
//                    String areaCode = (String) context.get("areaCode");
//                    if (UtilValidate.isNotEmpty(areaCode)) {
//                        paramList = paramList + "&areaCode=" + areaCode;
//                        andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("areaCode"), EntityOperator.EQUALS, EntityFunction.UPPER(areaCode)));
//                    }
//
//                    // filter on contact number
//                    String contactNumber = (String) context.get("contactNumber");
//                    if (UtilValidate.isNotEmpty(contactNumber)) {
//                        paramList = paramList + "&contactNumber=" + contactNumber;
//                        andExprs.add(EntityCondition.makeCondition(EntityFunction.UPPER_FIELD("contactNumber"), EntityOperator.EQUALS, EntityFunction.UPPER(contactNumber)));
//                    }
//
//                    fieldsToSelect.add("contactNumber");
//                    fieldsToSelect.add("areaCode");
//                }
//
//                // ---- End of Dynamic View Creation
//
//                // build the main condition
//                if (andExprs.size() > 0) mainCond = EntityCondition.makeCondition(andExprs, EntityOperator.AND);
//            }
//
//            Debug.logInfo("In findParty mainCond=" + mainCond, module);
//
//            String sortField = (String) context.get("sortField");
//            if(UtilValidate.isNotEmpty(sortField)){
//                orderBy.add(sortField);
//            }
//
//            // do the lookup
//            if (mainCond != null || "Y".equals(showAll)) {
//                try {
//                    // get the indexes for the partial list
//                    lowIndex = viewIndex * viewSize + 1;
//                    highIndex = (viewIndex + 1) * viewSize;
//
//                    // set distinct on so we only get one row per order
//                    EntityFindOptions findOpts = new EntityFindOptions(true, EntityFindOptions.TYPE_SCROLL_INSENSITIVE, EntityFindOptions.CONCUR_READ_ONLY, -1, highIndex, true);
//                    // using list iterator
//                    EntityListIterator pli = delegator.findListIteratorByCondition(dynamicView, mainCond, null, fieldsToSelect, orderBy, findOpts);
//
//                    // get the partial list for this page
//                    partyList = pli.getPartialList(lowIndex, viewSize);
//
//                    // attempt to get the full size
//                    partyListSize = pli.getResultsSizeAfterPartialList();
//                    if (highIndex > partyListSize) {
//                        highIndex = partyListSize;
//                    }
//
//                    // close the list iterator
//                    pli.close();
//                } catch (GenericEntityException e) {
//                    String errMsg = "Failure in party find operation, rolling back transaction: " + e.toString();
//                    Debug.logError(e, errMsg, module);
//                    return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                            "PartyLookupPartyError",
//                            UtilMisc.toMap("errMessage", e.toString()), locale));
//                }
//            } else {
//                partyListSize = 0;
//            }
//        }
//
//        if (partyList == null) partyList = FastList.newInstance();
//        result.put("partyList", partyList);
//        result.put("partyListSize", Integer.valueOf(partyListSize));
//        result.put("paramList", paramList);
//        result.put("highIndex", Integer.valueOf(highIndex));
//        result.put("lowIndex", Integer.valueOf(lowIndex));
//
//        return result;
//    }
//
//    /**
//     * Changes the association of contact mechs, purposes, notes, orders and attributes from
//     * one party to another for the purpose of merging records together. Flags the from party
//     * as disabled so it no longer appears in a search.
//     *
//     * @param dctx the dispatch context
//     * @param context the context
//     * @return the result of the service execution
//     */
//    public static Map<String, Object> linkParty(DispatchContext dctx, Map<String, ? extends Object> context) {
//        Delegator _delegator = dctx.getDelegator();
//        Delegator delegator = _delegator.cloneDelegator();
//        Locale locale = (Locale) context.get("locale");
//        delegator.setEntityEcaHandler(null);
//
//        String partyIdTo = (String) context.get("partyIdTo");
//        String partyId = (String) context.get("partyId");
//        Timestamp now = UtilDateTime.nowTimestamp();
//
//        if (partyIdTo.equals(partyId)) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                    "PartyCannotLinkPartyToItSelf", locale));
//        }
//
//        // get the from/to party records
//        GenericValue partyTo;
//        try {
//            partyTo = delegator.findByPrimaryKey("Party", UtilMisc.toMap("partyId", partyIdTo));
//        } catch (GenericEntityException e) {
//            Debug.logInfo(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//        if (partyTo == null) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                    "PartyPartyToDoesNotExists", locale));
//        }
//        if ("PARTY_DISABLED".equals(partyTo.get("statusId"))) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                    "PartyCannotMergeDisabledParty", locale));
//        }
//
//        GenericValue party;
//        try {
//            party = delegator.findByPrimaryKey("Party", UtilMisc.toMap("partyId", partyId));
//        } catch (GenericEntityException e) {
//            Debug.logInfo(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//        if (party == null) {
//            return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                    "PartyPartyFromDoesNotExists", locale));
//        }
//
//        // update the contact mech records
//        try {
//            delegator.storeByCondition("PartyContactMech", UtilMisc.<String, Object>toMap("partyId", partyIdTo, "thruDate", now),
//                    EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // update the contact mech purpose records
//        try {
//            delegator.storeByCondition("PartyContactMechPurpose", UtilMisc.<String, Object>toMap("partyId", partyIdTo, "thruDate", now),
//                    EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // update the party notes
//        try {
//            delegator.storeByCondition("PartyNote", UtilMisc.toMap("partyId", partyIdTo),
//                    EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // update the inventory item(s)
//        try {
//            delegator.storeByCondition("InventoryItem", UtilMisc.toMap("ownerPartyId", partyIdTo),
//                    EntityCondition.makeCondition("ownerPartyId", EntityOperator.EQUALS, partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // update the subscription
//        try {
//            delegator.storeByCondition("Subscription", UtilMisc.toMap("partyId", partyIdTo),
//                    EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // update the userLogin records
//        try {
//            delegator.storeByCondition("UserLogin", UtilMisc.toMap("partyId", partyIdTo),
//                    EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // update the non-existing party roles
//        List<GenericValue> rolesToMove;
//        try {
//            rolesToMove = delegator.findByAnd("PartyRole", UtilMisc.toMap("partyId", partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        for (GenericValue attr: rolesToMove) {
//            attr.setString("partyId", partyIdTo);
//            try {
//                if (delegator.findByPrimaryKey("PartyRole", attr.getPrimaryKey()) == null) {
//                    attr.create();
//                }
//            } catch (GenericEntityException e) {
//                Debug.logError(e, module);
//                return UtilMessages.returnError(e.getMessage());
//            }
//        }
//
//        // update the order role records
//        try {
//            delegator.storeByCondition("OrderRole", UtilMisc.toMap("partyId", partyIdTo),
//                    EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // invoice role
//        try {
//            delegator.storeByCondition("InvoiceRole", UtilMisc.toMap("partyId", partyIdTo),
//                    EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // data resource role
//        try {
//            delegator.storeByCondition("DataResourceRole", UtilMisc.toMap("partyId", partyIdTo),
//                    EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // content role
//        try {
//            delegator.storeByCondition("ContentRole", UtilMisc.toMap("partyId", partyIdTo),
//                    EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // update the fin account
//        try {
//            delegator.storeByCondition("FinAccountRole", UtilMisc.toMap("partyId", partyIdTo),
//                    EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // update the Product Store Role records
//        try {
//            delegator.storeByCondition("ProductStoreRole", UtilMisc.<String, Object>toMap("partyId", partyIdTo, "thruDate", now),
//                    EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        //  update the Communication Event Role records
//        try {
//            delegator.storeByCondition("CommunicationEventRole", UtilMisc.toMap("partyId", partyIdTo),
//                    EntityCondition.makeCondition("partyId", EntityOperator.EQUALS, partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // remove all previous party roles
//        try {
//            delegator.removeByAnd("PartyRole", UtilMisc.toMap("partyId", partyId));
//        } catch (GenericEntityException e) {
//            Debug.logWarning(e, module);
//            // if this fails no problem
//        }
//
//        // update the non-existing attributes
//        List<GenericValue> attrsToMove;
//        try {
//            attrsToMove = delegator.findByAnd("PartyAttribute", UtilMisc.toMap("partyId", partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        for (GenericValue attr: attrsToMove) {
//            attr.setString("partyId", partyIdTo);
//            try {
//                if (delegator.findByPrimaryKey("PartyAttribute", attr.getPrimaryKey()) == null) {
//                    attr.create();
//                }
//            } catch (GenericEntityException e) {
//                Debug.logError(e, module);
//                return UtilMessages.returnError(e.getMessage());
//            }
//        }
//        try {
//            delegator.removeByAnd("PartyAttribute", UtilMisc.toMap("partyId", partyId));
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // create a party link attribute
//        GenericValue linkAttr = delegator.makeValue("PartyAttribute");
//        linkAttr.setString("partyId", partyId);
//        linkAttr.setString("attrName", "LINKED_TO");
//        linkAttr.setString("attrValue", partyIdTo);
//        try {
//            delegator.create(linkAttr);
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        // disable the party
//        String currentStatus = party.getString("statusId");
//        if (currentStatus == null || !"PARTY_DISABLED".equals(currentStatus)) {
//            party.setString("statusId", "PARTY_DISABLED");
//
//            try {
//                party.store();
//            } catch (GenericEntityException e) {
//                Debug.logError(e, "Error setting disable mode on partyId: " + partyId, module);
//                return UtilMessages.returnError(e.getMessage());
//            }
//        }
//
//        Map<String, Object> resp = UtilMessages.returnSuccess();
//        resp.put("partyId", partyIdTo);
//        return resp;
//    }
//
//    public static Map<String, Object> importAddressMatchMapCsv(DispatchContext dctx, Map<String, ? extends Object> context) {
//        Delegator delegator = dctx.getDelegator();
//        Locale locale = (Locale) context.get("locale");
//        ByteBuffer fileBytes = (ByteBuffer) context.get("uploadedFile");
//        String encoding = System.getProperty("file.encoding");
//        String csvFile = Charset.forName(encoding).decode(fileBytes).toString();
//        csvFile = csvFile.replaceAll("\\r", "");
//        String[] records = csvFile.split("\\n");
//
//        for (int i = 0; i < records.length; i++) {
//            if (records[i] != null) {
//                String str = records[i].trim();
//                String[] map = str.split(",");
//                if (map.length != 2 && map.length != 3) {
//                    return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                            "PartyImportInvalidCsvFile", locale));
//                } else {
//                    GenericValue addrMap = delegator.makeValue("AddressMatchMap");
//                    addrMap.put("mapKey", map[0].trim().toUpperCase());
//                    addrMap.put("mapValue", map[1].trim().toUpperCase());
//                    int seq = i + 1;
//                    if (map.length == 3) {
//                        char[] chars = map[2].toCharArray();
//                        boolean isNumber = true;
//                        for (char c: chars) {
//                            if (!Character.isDigit(c)) {
//                                isNumber = false;
//                            }
//                        }
//                        if (isNumber) {
//                            try {
//                                seq = Integer.parseInt(map[2]);
//                            } catch (Throwable t) {
//                                Debug.logWarning(t, "Unable to parse number", module);
//                            }
//                        }
//                    }
//
//                    addrMap.put("sequenceNum", Long.valueOf(seq));
//                    Debug.logInfo("Creating map entry: " + addrMap, module);
//                    try {
//                        delegator.create(addrMap);
//                    } catch (GenericEntityException e) {
//                        Debug.logError(e, module);
//                        return UtilMessages.returnError(e.getMessage());
//                    }
//                }
//            } else {
//                return UtilMessages.returnError(UtilProperties.getMessage(resource,
//                        "PartyImportNoRecordsFoundInFile", locale));
//            }
//        }
//
//        return UtilMessages.returnSuccess();
//    }
//

//
//
//    /**
//     * Finds partyId(s) corresponding to a party reference, partyId or a GoodIdentification idValue
//     * @param ctx the dispatch context
//     * @param context use to search with partyId or goodIdentification.idValue
//     * @return a GenericValue with a partyId and a List of complementary partyId found
//     */
//    public static Map<String, Object> findPartyById(DispatchContext ctx, Map<String, Object> context) {
//        Delegator delegator = ctx.getDelegator();
//        String idToFind = (String) context.get("idToFind");
//        String partyIdentificationTypeId = (String) context.get("partyIdentificationTypeId");
//        String searchPartyFirstContext = (String) context.get("searchPartyFirst");
//        String searchAllIdContext = (String) context.get("searchAllId");
//
//        boolean searchPartyFirst = UtilValidate.isNotEmpty(searchPartyFirstContext) && "N".equals(searchPartyFirstContext) ? false : true;
//        boolean searchAllId = UtilValidate.isNotEmpty(searchAllIdContext)&& "Y".equals(searchAllIdContext) ? true : false;
//
//        GenericValue party = null;
//        List<GenericValue> partiesFound = null;
//        try {
//            partiesFound = PartyWorker.findPartiesById(delegator, idToFind, partyIdentificationTypeId, searchPartyFirst, searchAllId);
//        } catch (GenericEntityException e) {
//            Debug.logError(e, module);
//            return UtilMessages.returnError(e.getMessage());
//        }
//
//        if (UtilValidate.isNotEmpty(partiesFound)) {
//            // gets the first partyId of the List
//            party = EntityUtil.getFirst(partiesFound);
//            // remove this partyId
//            partiesFound.remove(0);
//        }
//
//        Map<String, Object> result = UtilMessages.returnSuccess();
//        result.put("party", party);
//        result.put("partiesFound", partiesFound);
//
//        return result;
//    }



}
