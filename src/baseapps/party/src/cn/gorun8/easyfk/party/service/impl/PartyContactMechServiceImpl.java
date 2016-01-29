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
 * Author:hezhiping   Email:110476592@qq.com   Date: 16-1-6
 */
package cn.gorun8.easyfk.party.service.impl;

import cn.gorun8.easyfk.base.util.*;
import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.entity.util.EntityUtil;
import cn.gorun8.easyfk.entity.util.UtilEntity;
import cn.gorun8.easyfk.party.dao.ContactMechReadDao;
import cn.gorun8.easyfk.party.dao.ContactMechWriteDao;
import cn.gorun8.easyfk.party.service.PartyContactMechService;
import javolution.util.FastMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service("partyContactMechService")
public class PartyContactMechServiceImpl implements PartyContactMechService {
    public static final String resource = "PartyUiLabels";
    public static final String resourceError = "PartyErrorUiLabels";

    @Autowired
    private ContactMechReadDao contactMechReadDao;

    @Autowired
    private ContactMechWriteDao contactMechWriteDao;

    /**
     *
     */
    public Map<String, Object> createContactMech(Map<String, ? extends Object> context) {
        Locale locale = (Locale) context.get("locale");
        GenericValue contactMech = GenericValue.fromMap(context, true);
        String contactMechId = contactMech.newPrimaryKey("ContactMech", "contactMechId");

        String contactMechTypeId = (String) context.get("contactMechTypeId");
        if (UtilValidate.isEmpty(contactMechTypeId)) {
            return UtilMessages.returnParamError(locale, "contactMechTypeId");
        }

        try {
            contactMechWriteDao.createContactMech(contactMech);
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "contactmechservices.could_not_create_contact_info_write", locale));
        }

        return UtilMessages.returnSuccessWithData(contactMechId);
    }


    public Map<String, Object> createPartyContactMech(Map<String, ? extends Object> context) {

        Locale locale = (Locale) context.get("locale");
        String partyId = (String) context.get("partyId");
        String infoString = (String) context.get("infoString");
        String contactMechTypeId = (String) context.get("contactMechTypeId");
        String contactMechId = (String) context.get("contactMechId");

        if (UtilValidate.isEmpty(partyId)) {
            return UtilMessages.returnParamError(locale, "partyId");
        }

        if (UtilValidate.isEmpty(contactMechTypeId)) {
            return UtilMessages.returnParamError(locale, "contactMechTypeId");
        }

        try {
            // check if the contact mech infostring is already existing if so, do not create a new one
            GenericValue param = GenericValue.fromMap(context,false);
            List<GenericValue> contactMechList = contactMechReadDao.findPartyAndContactMech(param);

            for (GenericValue partyAndContactMech : contactMechList) {
                GenericValue contactMechType = contactMechReadDao.findContactMechTypeById(contactMechTypeId);
                String hasTable = contactMechType.getString("hasTable");
                if ("N".equals(hasTable)) {
                    if (UtilValidate.isNotEmpty(infoString) &&
                            infoString.equals(partyAndContactMech.getString("infoString")) &&
                            contactMechTypeId.equals(partyAndContactMech.getString("contactMechTypeId"))) {
                        contactMechId = partyAndContactMech.getString("contactMechId");
                        return UtilMessages.returnSuccessWithData(contactMechId);
                    }
                }
            }

            if (UtilValidate.isEmpty(contactMechId)) {
                Map<String, Object> params = FastMap.newInstance();
                params.put("contactMechTypeId", contactMechTypeId);
                params.put("infoString", infoString);
                params.put("locale",locale);

                Map<String, Object> result = createContactMech(params);
                if (UtilMessages.isSuccess(result)) {
                    contactMechId = (String) result.get(UtilMessages.RESPONSE_DATA);
                }

            }//endif

            //创建与会员的关联
            Timestamp now = UtilDateTime.nowTimestamp();
            GenericValue contactMech = GenericValue.fromMap(context, true);
            contactMech.setString("contactMechId", contactMechId);
            contactMech.setString("fromDate", now);
            contactMech.setString("partyId", partyId);
            contactMech.setNonPKFields(context);

            contactMechWriteDao.createPartyContactMech(contactMech);

        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "contactmechservices.could_not_create_contact_info_write", locale));
        }

        return UtilMessages.returnSuccessWithData(contactMechId);

    }

    /*
     <service name="updateContactMech" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/ContactMechServices.xml" invoke="updateContactMech" auth="true">
     <description>Update a ContactMech</description>
     <attribute name="contactMechId" type="String" mode="INOUT" optional="false"/>
     <attribute name="contactMechTypeId" type="String" mode="IN" optional="false"/>
     <attribute name="infoString" type="String" mode="IN" optional="true"/>
     </service>
     */
    public Map<String, Object> updateContactMech(Map<String, ? extends Object> context) {

        /*
        <simple-method method-name="updateContactMech" short-description="Update Contact Mechanism">
        <set field="successMessageProperty" value="PartyContactMechanismSuccessfullyUpdated"/>
        <if-compare field="parameters.contactMechTypeId" operator="equals" value="EMAIL_ADDRESS">
            <set field="successMessageProperty" value="PartyEmailAddressSuccessfullyUpdated"/>
        </if-compare>
        <if-compare field="parameters.contactMechTypeId" operator="equals" value="WEB_ADDRESS">
            <set field="successMessageProperty" value="PartyWebAddressSuccessfullyUpdated"/>
        </if-compare>
        <if-compare field="parameters.contactMechTypeId" operator="equals" value="IP_ADDRESS">
            <set field="successMessageProperty" value="PartyIpAddressSuccessfullyUpdated"/>
        </if-compare>
        <if-compare field="parameters.contactMechTypeId" operator="equals" value="ELECTRONIC_ADDRESS">
            <set field="successMessageProperty" value="PartyElectronicAddressSuccessfullyUpdated"/>
        </if-compare>
        <if-compare field="parameters.contactMechTypeId" operator="equals" value="DOMAIN_NAME">
            <set field="successMessageProperty" value="PartyDomainNameSuccessfullyUpdated"/>
        </if-compare>

        <make-value entity-name="ContactMech" value-field="ContactMechMap"/>
        <set-pk-fields value-field="ContactMechMap" map="parameters"/>
        <find-by-primary-key entity-name="ContactMech" map="ContactMechMap" value-field="oldValue"/>

        <if-compare-field to-field="oldValue.infoString" field="parameters.infoString" operator="not-equals">
             <log level="info" message="Contact mech need updating"/>
            <clone-value value-field="oldValue" new-value-field="newValue"/>
            <set-nonpk-fields map="parameters" value-field="newValue" set-if-null="false"/>
            <set field="context.contactMechTypeId" from-field="parameters.contactMechTypeId"/>
            <set field="context.infoString" from-field="parameters.infoString"/>
            <call-service service-name="createContactMech" in-map-name="context">
                <default-message resource="PartyUiLabels" property="${successMessageProperty}"/>
                <result-to-field result-name="contactMechId" field="newValue.contactMechId"/>
            </call-service>
            <field-to-result field="newValue.contactMechId" result-name="contactMechId"/>
            <field-to-request field="newValue.contactMechId" request-name="contactMechId"/>
            <else>
                <log level="info" message="Contact mech not changed"/>
                <field-to-result field="oldValue.contactMechId" result-name="contactMechId"/>
                <field-to-request field="oldValue.contactMechId" request-name="contactMechId"/>
            </else>
        </if-compare-field>
    </simple-method>
        */
        Locale locale = (Locale) context.get("locale");
        String contactMechId = (String) context.get("contactMechId");
        String infoString = (String) context.get("infoString");

        if (UtilValidate.isEmpty(contactMechId)) {
            return UtilMessages.returnParamError(locale, "contactMechId");
        }

        String contactMechTypeId = (String) context.get("contactMechTypeId");
        if (UtilValidate.isEmpty(contactMechTypeId)) {
            return UtilMessages.returnParamError(locale, "contactMechTypeId");
        }

        try {
            GenericValue param = new GenericValue(false);
            param.setString("contactMechId",contactMechId);
            GenericValue oldValue = contactMechReadDao.findContactMechById(param);//ContactMech
            if (oldValue == null) {
                return UtilMessages.returnSuccessWithData(contactMechId);
            }

            String infoStringOld = oldValue.getString("infoString");
            if(infoStringOld.equals(infoString)){
                Map<String, Object> result = createContactMech(context);
                if (UtilMessages.isSuccess(result)) {
                    contactMechId = (String) result.get(UtilMessages.RESPONSE_DATA);
                }
            }
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "contactmechservices.could_not_create_contact_info_write", locale));
        }

        return UtilMessages.returnSuccessWithData(contactMechId);
    }

    /*
     <service name="updatePartyContactMech" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="updatePartyContactMech" auth="true">
     <description>Update a PartyContactMech</description>
     <permission-service service-name="partyContactMechPermissionCheck" main-action="UPDATE"/>
     <auto-attributes entity-name="PartyContactMech" include="all" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="INOUT" optional="false"/>
     <attribute name="contactMechTypeId" type="String" mode="IN" optional="false"/>
     <attribute name="infoString" type="String" mode="IN" optional="true"/>
     <attribute name="newContactMechId" type="String" mode="IN" optional="true"/>
     </service>
     */
    public Map<String, Object> updatePartyContactMech(Map<String, ? extends Object> context) {

        Locale locale = (Locale) context.get("locale");
        String partyId = (String) context.get("partyId");
        String infoString = (String) context.get("infoString");
        String contactMechTypeId = (String) context.get("contactMechTypeId");
        String contactMechId = (String) context.get("contactMechId");

        if (UtilValidate.isEmpty(partyId)) {
            return UtilMessages.returnParamError(locale, "partyId");
        }

        if (UtilValidate.isEmpty(contactMechTypeId)) {
            return UtilMessages.returnParamError(locale, "contactMechTypeId");
        }

        try {
            // check if the contact mech infostring is already existing if so, do not create a new one
            GenericValue param = GenericValue.fromMap(context,false);

            List<GenericValue> partyContactMechs = contactMechReadDao.findPartyContactMech(param);
            List<GenericValue> validPartyContactMechs = EntityUtil.filterByDate(partyContactMechs);
            GenericValue partyContactMech = validPartyContactMechs.get(0);
            if(null == partyContactMech){
                return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                        "PartyCannotUpdateContactBecauseNotWithSpecifiedParty", locale));
            }

            if (UtilValidate.isEmpty(contactMechId)) {
                Map<String, Object> params = FastMap.newInstance();
                params.put("contactMechTypeId", contactMechTypeId);
                params.put("infoString", infoString);

                Map<String, Object> result = updateContactMech(params);
                if (UtilMessages.isSuccess(result)) {
                    contactMechId = (String) result.get(UtilMessages.RESPONSE_DATA);
                }

            }//endif

            /*
        <if-compare-field to-field="newPartyContactMech.contactMechId" field="parameters.contactMechId" operator="not-equals">
            <set-nonpk-fields value-field="newPartyContactMech" map="parameters"/>
            <now-timestamp field="newPartyContactMech.fromDate"/>
            <now-timestamp field="partyContactMech.thruDate"/>
            <store-value value-field="partyContactMech"/>
            <create-value value-field="newPartyContactMech"/>
            <get-related value-field="partyContactMech" relation-name="PartyContactMechPurpose" list="partyContactMechPurposes"/>
            <filter-list-by-date list="partyContactMechPurposes"/>
            <iterate entry="partyContactMechPurposeOld" list="partyContactMechPurposes">
                <clone-value value-field="partyContactMechPurposeOld" new-value-field="partyContactMechPurpose"/>
                <now-timestamp field="partyContactMechPurposeOld.thruDate"/>
                <store-value value-field="partyContactMechPurposeOld"/>

                <set field="partyContactMechPurpose.contactMechId" from-field="newPartyContactMech.contactMechId"/>
                <set field="purposeMap.partyId" from-field="partyContactMechPurpose.partyId"/>
                <set field="purposeMap.contactMechPurposeTypeId" from-field="partyContactMechPurpose.contactMechPurposeTypeId"/>
                <set field="purposeMap.contactMechId" from-field="partyContactMechPurpose.contactMechId"/>
                <find-by-and entity-name="PartyContactMechPurpose" list="purposeResult" map="purposeMap"/>

                <if-empty field="purposeResult">
                    <create-value value-field="partyContactMechPurpose"/>
                </if-empty>
            </iterate>
            <log level="info" message="Setting id to result: ${newPartyContactMech.contactMechId}"/>
            <field-to-result field="newPartyContactMech.contactMechId" result-name="contactMechId"/>
            <field-to-request field="newPartyContactMech.contactMechId" request-name="contactMechId"/>
            <else>
                <set field="extension" from-field="partyContactMech.extension"/>
                <set-nonpk-fields value-field="partyContactMech" map="parameters"/>
                <if-compare-field field="parameters.extension" operator="not-equals" to-field="extension">
                    <set field="partyContactMech.thruDate" value=""/>
                </if-compare-field>
                <store-value value-field="partyContactMech"/>
                <log level="info" message="Setting id to result: ${partyContactMech.contactMechId}"/>
                <field-to-result field="partyContactMech.contactMechId" result-name="contactMechId"/>
                <field-to-request field="partyContactMech.contactMechId" request-name="contactMechId"/>
            </else>
        </if-compare-field>

    </simple-method>
*/
            //创建与会员的关联
            Timestamp now = UtilDateTime.nowTimestamp();
            GenericValue contactMech = GenericValue.fromMap(context, true);
            contactMech.setString("contactMechId", contactMechId);
            contactMech.setString("fromDate", now);
            contactMech.setString("partyId", partyId);
            contactMechWriteDao.createPartyContactMech(contactMech);

        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "contactmechservices.could_not_create_contact_info_write", locale));
        }

        return UtilMessages.returnSuccessWithData(contactMechId);
    }

    /*
     <service name="deletePartyContactMech" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="deletePartyContactMech" auth="true">
     <description>Delete a PartyContactMech</description>
     <permission-service service-name="partyContactMechPermissionCheck" main-action="DELETE"/>
     <attribute name="partyId" type="String" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="IN" optional="false"/>
     </service>
     */
    public Map<String, Object> deletePartyContactMech(Map<String, ? extends Object> context) {

        Locale locale = (Locale) context.get("locale");
        String partyId = (String) context.get("partyId");
        String infoString = (String) context.get("infoString");
        String contactMechId = (String) context.get("contactMechId");

        if (UtilValidate.isEmpty(partyId)) {
            return UtilMessages.returnParamError(locale, "partyId");
        }

        if (UtilValidate.isEmpty(contactMechId)) {
            return UtilMessages.returnParamError(locale, "contactMechId");
        }
        try {
            GenericValue param = GenericValue.fromMap(context,false);

            List<GenericValue> partyContactMechs = contactMechReadDao.findPartyContactMech(param);
            List<GenericValue>  validPartyContactMechs = EntityUtil.filterByDate(partyContactMechs);
            if(null == validPartyContactMechs && validPartyContactMechs.size() <=0){
                return UtilMessages.returnError(UtilProperties.getMessage(resource,
                        "PartyCannotUpdateContactBecauseNotWithSpecifiedParty", locale));
            }

            GenericValue partyContactMech = validPartyContactMechs.get(0);
            Timestamp now = UtilDateTime.nowTimestamp();
            partyContactMech.setString("thruDate",now);
            contactMechWriteDao.updatePartyContactMech(partyContactMech);
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "contactmechservices.could_not_create_contact_info_write", locale));
        }
        return UtilMessages.returnSuccess();
    }

    /*
     <service name="createPostalAddress" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/ContactMechServices.xml" invoke="createPostalAddress" auth="true">
     <description>Create a Postal Address</description>
     <auto-attributes entity-name="PostalAddress" include="all" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="OUT" optional="false"/>
     <override name="address1" optional="false"/>
     <override name="city" optional="false"/>
     <override name="postalCode" optional="false"/>
     </service>
    */

    public Map<String, Object> createPostalAddress(Map<String, ? extends Object> context) {
        Locale locale = (Locale) context.get("locale");
        Map <String ,Object > params = FastMap.newInstance();
        params.put("contactMechTypeId","POSTAL_ADDRESS");
        params.put("infoString", context.get("infoString"));

        Map<String,Object> result = createContactMech(params);
        if(!UtilMessages.isSuccess(result)){
            return result;
        }

        try {
            String contactMechId =(String)result.get(UtilMessages.RESPONSE_DATA);
            GenericValue contactMech = GenericValue.fromMap(context,true); // contactMechId
            contactMech.setString("contactMechId",contactMechId);
            contactMechWriteDao.createPostalAddress(contactMech);
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "contactmechservices.could_not_create_contact_info_write", locale));
        }
        return UtilMessages.returnSuccess();
    }

    /*
     <!-- Party ContachMech reverse find -->
     <service name="findPartyFromEmailAddress" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="findPartyFromEmailAddress" auth="true">
     <description>Find the partyId/contactMechId for a specific email address, if not found do not return a value</description>
     <attribute name="address" type="String" mode="IN" optional="false"/>
     <attribute name="caseInsensitive" type="String" mode="IN" optional="true"/>
     <attribute name="personal" type="String" mode="IN" optional="true"/><!-- field not used -->
     <attribute name="fromDate" type="Timestamp" mode="IN" optional="true"/>
     <attribute name="partyId" type="String" mode="OUT" optional="true"/>
     <attribute name="contactMechId" type="String" mode="OUT" optional="true"/>
     </service>
     */
    public Map<String, Object> findPartyFromEmailAddress(Map<String, ? extends Object> context) {
      /*
      <simple-method method-name="findPartyFromEmailAddress" short-description="Find partyId from email address">
        <set field="input.filterByDate" value="Y"/>
        <set field="input.inputFields.infoString" from-field="parameters.address"/>
        <set field="caseInsensitive" from-field="parameters.caseInsensitive"/>

        <if-empty field="caseInsensitive">
            <property-to-field resource="general.properties" property="mail.address.caseInsensitive" field="caseInsensitive" default="N"/>
        </if-empty>

        <set field="input.inputFields.infoString_ic" from-field="caseInsensitive"/>

        <if-empty field="parameters.fromDate">
            <set field="input.filterByDate" value="Y"/>
            <else>
                <set field="input.filterByDateValue" from-field="parameters.fromDate"/>
            </else>
        </if-empty>
        <!-- try primary email address -->
        <set field="input.inputFields.contactMechPurposeTypeId" value="PRIMARY_EMAIL"/>
        <set field="input.entityName" value="PartyContactDetailByPurpose"/>
        <call-service service-name="performFindItem" in-map-name="input">
            <results-to-map map-name="results"/>
        </call-service>
        <!-- any other email address -->
        <if-empty field="results.item">
            <set field="input.entityName" value="PartyAndContactMech"/>
            <clear-field field="input.inputFields.contactMechPurposeTypeId"/>
            <call-service service-name="performFindItem" in-map-name="input">
                <results-to-map map-name="results"/>
            </call-service>
        </if-empty>
        <if-not-empty field="results.item">
            <field-to-result field="results.item.partyId" result-name="partyId"/>
            <field-to-result field="results.item.contactMechId" result-name="contactMechId"/>
        </if-not-empty>
    </simple-method>
      */

        Locale locale = (Locale) context.get("locale");
        String filterByDate = "Y";
        String infoString = (String) context.get("address");
        String caseInsensitive =(String) context.get("caseInsensitive");
        String fromDate = (String) context.get("fromDate");
        String contactMechPurposeTypeId = "PRIMARY_EMAIL";

        if(UtilValidate.isEmpty(caseInsensitive)){
            caseInsensitive = UtilProperties.getMessage("general.properties", "mail.address.caseInsensitive",locale);
        }


        Map <String ,Object > params = FastMap.newInstance();
        params.put("contactMechTypeId","POSTAL_ADDRESS");
        params.put("infoString", context.get("infoString"));




/*
        <set field="input.filterByDate" value="Y"/>
        <set field="input.inputFields.infoString" from-field="parameters.address"/>
        <set field="caseInsensitive" from-field="parameters.caseInsensitive"/>

        <if-empty field="caseInsensitive">
        <property-to-field resource="general.properties" property="mail.address.caseInsensitive" field="caseInsensitive" default="N"/>
        </if-empty>

        <set field="input.inputFields.infoString_ic" from-field="caseInsensitive"/>

        <if-empty field="parameters.fromDate">
        <set field="input.filterByDate" value="Y"/>
        <else>
        <set field="input.filterByDateValue" from-field="parameters.fromDate"/>
        </else>
        </if-empty>
*/

        Map<String,Object> result = createContactMech(params);
        if(!UtilMessages.isSuccess(result)){
            return result;
        }

        try {
            String contactMechId =(String)result.get(UtilMessages.RESPONSE_DATA);
            GenericValue contactMech = GenericValue.fromMap(context,true); // contactMechId
            contactMech.setString("contactMechId",contactMechId);
            contactMechWriteDao.createPostalAddress(contactMech);
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "contactmechservices.could_not_create_contact_info_write", locale));
        }
        return UtilMessages.returnSuccess();

    }

    /*
     <service name="findPartyFromTelephone" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="findPartyFromTelephone" auth="true">
     <description>Find the partyId/contactMechId for a specific telephone number, if not found do not return a value</description>
     <attribute name="telno" type="String" mode="IN" optional="false"/>
     <attribute name="partyId" type="String" mode="OUT" optional="true"/>
     <attribute name="contactMechId" type="String" mode="OUT" optional="true"/>
     </service>
     */
    public Map<String, Object> findPartyFromTelephone(Map<String, ? extends Object> context) {
        return null;
    }

    /*
     <service name="findPartyFromTelephoneComplete" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="findPartyFromTelephoneComplete" auth="true">
     <description>
     Find the partyId/contactMechId for a specific telephone number, if not found do not return a value.
     Same than above but keep the number complete internally.
     </description>
     <attribute name="telno" type="String" mode="IN" optional="false"/>
     <attribute name="partyId" type="String" mode="OUT" optional="true"/>
     <attribute name="contactMechId" type="String" mode="OUT" optional="true"/>
     </service>
     */
    public Map<String, Object> findPartyFromTelephoneComplete(Map<String, ? extends Object> context) {
        return null;
    }

    /**
     * 根据partyId获取关联的联系方式
     * @param context
     * @return
     */
    public Map<String,Object> findPartyContactMech(Map<String, ? extends Object> context){

        Locale locale = (Locale) context.get("locale");
        GenericValue contactMech = GenericValue.fromMap(context, true);
        String partyId = (String) context.get("partyId");
        if (UtilValidate.isEmpty(partyId)) {
            return UtilMessages.returnParamError(locale, "partyId");
        }

        try {
            List<GenericValue>  listPartyContactMech = contactMechReadDao.findPartyContactMech(contactMech);
            for(GenericValue it:listPartyContactMech){
                Timestamp thruDateTamp = (Timestamp)it.get("THRU_DATE");
                if(UtilValidate.isNotEmpty(thruDateTamp)){
                    if(UtilValidate.isDateBeforeNow(thruDateTamp)){
                        it.setString("inValidate","Y");
                    }
                }
            }

            return  UtilMessages.returnSuccessWithData(UtilEntity.toMap(listPartyContactMech));
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "contactmechservices.could_not_create_contact_info_write", locale));
        }

    }

    /**
     * <service name="createPartyPostalAddress" engine="simple"
     * location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="createPartyPostalAddress" auth="true">
     * <description>Create a Postal Address</description>
     * <permission-service service-name="partyContactMechPermissionCheck" main-action="CREATE"/>
     * <auto-attributes entity-name="PartyContactMech" include="all" mode="IN" optional="true"/>
     * <auto-attributes entity-name="PostalAddress" include="nonpk" mode="IN" optional="true"/>
     * <attribute name="paymentMethodId" type="String" mode="IN" optional="true"/>
     * <attribute name="contactMechPurposeTypeId" type="String" mode="IN" optional="true"/>
     * <attribute name="contactMechId" type="String" mode="OUT" optional="false"/>
     * <override name="address1" optional="false"/>
     * <override name="city" optional="false"/>
     * <override name="postalCode" optional="false"/>
     * </service>
     */
    public Map<String, Object> createPartyPostalAddress(Map<String, ? extends Object> context) {
       /*
       <simple-method method-name="createPartyPostalAddress" short-description="Create a PostalAddress for party">
        <if-empty field="parameters.partyId">
            <set field="parameters.partyId" from-field="userLogin.partyId"/>
        </if-empty>

        <set-service-fields service-name="createPostalAddress" map="parameters" to-map="createPostalAddressMap"/>
        <call-service in-map-name="createPostalAddressMap" service-name="createPostalAddress">
            <default-message resource="PartyUiLabels" property="PartyPostalAddressSuccessfullyCreated"/>
            <result-to-field result-name="contactMechId" field="newPartyContactMech.contactMechId"/>
        </call-service>

        <check-errors/>
        <set-service-fields service-name="createPartyContactMech" map="parameters" to-map="createPartyContactMechMap"/>
        <set field="createPartyContactMechMap.contactMechId" from-field="newPartyContactMech.contactMechId"/>
        <set field="createPartyContactMechMap.contactMechTypeId" value="POSTAL_ADDRESS"/>

        <call-service service-name="createPartyContactMech" in-map-name="createPartyContactMechMap" break-on-error="true">
            <default-message resource="PartyUiLabels" property="PartyPostalAddressSuccessfullyCreated"/>
        </call-service>
        <field-to-request field="newPartyContactMech.contactMechId" request-name="contactMechId"/>
        <field-to-result field="newPartyContactMech.contactMechId" result-name="contactMechId"/>
    </simple-method>
       */

        Locale locale = (Locale) context.get("locale");
        String partyId = (String) context.get("partyId");

        Map<String,Object> result = createPostalAddress(context);
        if(!UtilMessages.isSuccess(result)){
            return result;
        }

        result = createPartyContactMech(context);
        if(!UtilMessages.isSuccess(result)){
            return result;
        }

        return UtilMessages.returnSuccess();
    }

    /**
     * <description>Update a Postal Address</description>
     * <auto-attributes entity-name="PostalAddress" include="nonpk" mode="IN" optional="true"/>
     * <attribute name="contactMechId" type="String" mode="INOUT" optional="false"/> <!-- the out paramater is the id of the new address -->
     * <attribute name="directions" type="String" mode="IN" optional="true"/> <!-- ?? -->
     * <attribute name="oldContactMechId" type="String" mode="OUT" optional="false"/> <!-- this is the id of the old address -->
     * <attribute name="partyId" type="String" mode="IN" optional="true"/>
     * <override name="address1" optional="false"/>
     * <override name="city" optional="false"/>
     * <override name="postalCode" optional="false"/>
     */
    public Map<String, Object> updatePostalAddress(Map<String, ? extends Object> context) {
        return null;
    }

    /**
     * <service name="updatePartyPostalAddress" engine="simple"
     * location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="updatePartyPostalAddress" auth="true">
     * <description>Update a Postal Address</description>
     * <permission-service service-name="partyContactMechPermissionCheck" main-action="UPDATE"/>
     * <auto-attributes entity-name="PartyContactMech" include="all" mode="IN" optional="true"/>
     * <auto-attributes entity-name="PostalAddress" include="nonpk" mode="IN" optional="true"/>
     * <attribute name="contactMechId" type="String" mode="INOUT" optional="false"/>
     * <attribute name="directions" type="String" mode="IN" optional="true"/> <!-- ?? -->
     * <override name="address1" optional="false"/>
     * <override name="city" optional="false"/>
     * <override name="postalCode" optional="false"/>
     * </service>
     */
    public Map<String, Object> updatePartyPostalAddress(Map<String, ? extends Object> context) {
        return null;
    }

    /*
     <service name="createTelecomNumber" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/ContactMechServices.xml" invoke="createTelecomNumber" auth="true">
     <description>Create a Telecommunications Number</description>
     <auto-attributes entity-name="TelecomNumber" include="all" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="OUT" optional="false"/>
     </service>
     */
    public Map<String, Object> createTelecomNumber(Map<String, ? extends Object> context) {
        /*
        <simple-method method-name="createTelecomNumber" short-description="Create Contact Mechanism with Telecom Number">
        <make-value entity-name="TelecomNumber" value-field="newValue"/>
        <set field="context.contactMechTypeId" value="TELECOM_NUMBER"/>
        <set field="context.contactMechId" from-field="parameters.contactMechId"/>
        <call-service service-name="createContactMech" in-map-name="context">
            <default-message resource="PartyUiLabels" property="PartyTelecomNumberSuccessfullyCreated"/>
            <result-to-field result-name="contactMechId" field="newValue.contactMechId"/>
        </call-service>
        <set-nonpk-fields map="parameters" value-field="newValue"/>
        <create-value value-field="newValue"/>
        <field-to-result field="newValue.contactMechId" result-name="contactMechId"/>
        <field-to-request field="newValue.contactMechId" request-name="contactMechId"/>
    </simple-method>
        */

        Locale locale = (Locale) context.get("locale");
        String contactMechTypeId = (String) context.get("contactMechTypeId");
        if (UtilValidate.isEmpty(contactMechTypeId)) {
            return UtilMessages.returnParamError(locale, "contactMechTypeId");
        }

        Map<String,Object> result = createContactMech(context);
        if(!UtilMessages.isSuccess(result)){
            return result;
        }

        String contactMechId =(String)result.get(UtilMessages.RESPONSE_DATA);
        try {
            GenericValue telecomParams = GenericValue.fromMap(context,true); // contactMechId
            telecomParams.setString("contactMechId",contactMechId);
            contactMechWriteDao.createTelecomNumber(telecomParams);
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "contactmechservices.could_not_create_contact_info_write", locale));
        }
        return UtilMessages.returnSuccessWithData(contactMechId);
    }

    /*
     <service name="createPartyTelecomNumber" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="createPartyTelecomNumber" auth="true">
     <description>Create a Telecommunications Number</description>
     <permission-service service-name="partyContactMechPermissionCheck" main-action="CREATE"/>
     <auto-attributes entity-name="PartyContactMech" include="all" mode="IN" optional="true"/>
     <auto-attributes entity-name="TelecomNumber" include="nonpk" mode="IN" optional="true"/>
     <attribute name="contactMechPurposeTypeId" type="String" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="OUT" optional="false"/>
     </service>
     */
    public Map<String, Object> createPartyTelecomNumber(Map<String, ? extends Object> context) {
        /*
        <simple-method method-name="createPartyTelecomNumber" short-description="Create a TelecomNumber for party">
        <if-empty field="parameters.partyId">
            <set field="parameters.partyId" from-field="userLogin.partyId"/>
        </if-empty>

        <log level="info" message="Creating telecom number"/>
        <set-service-fields service-name="createTelecomNumber" map="parameters" to-map="createTelecomNumberMap"/>
        <call-service in-map-name="createTelecomNumberMap" service-name="createTelecomNumber">
            <default-message resource="PartyUiLabels" property="PartyTelecomNumberSuccessfullyCreated"/>
            <result-to-field result-name="contactMechId" field="newPartyContactMech.contactMechId"/>
        </call-service>

        <set-service-fields service-name="createPartyContactMech" map="parameters" to-map="createPartyContactMechMap"/>
        <set field="createPartyContactMechMap.contactMechId" from-field="newPartyContactMech.contactMechId"/>
        <set field="createPartyContactMechMap.contactMechTypeId" value="TELECOM_NUMBER"/>
        <log level="info" message="Copied id to createPartyContactMechMap: ${createPartyContactMechMap.contactMechId}"/>

        <call-service service-name="createPartyContactMech" in-map-name="createPartyContactMechMap" break-on-error="true">
            <default-message resource="PartyUiLabels" property="PartyTelecomNumberSuccessfullyCreated"/>
        </call-service>
        <field-to-request field="newPartyContactMech.contactMechId" request-name="contactMechId"/>
        <field-to-result field="newPartyContactMech.contactMechId" result-name="contactMechId"/>
    </simple-method>
        */

        Locale locale = (Locale) context.get("locale");
        String partyId = (String) context.get("partyId");
        String contactMechTypeId = (String) context.get("contactMechTypeId");

        Map<String,String> param = UtilMisc.toMap(context);
        param.put("contactMechTypeId","TELECOM_NUMBER");

        if (UtilValidate.isEmpty(partyId)) {
            return UtilMessages.returnParamError(locale, "partyId");
        }

        Map<String,Object> result = createTelecomNumber(context);
        if(!UtilMessages.isSuccess(result)){
            return result;
        }

        String contactMechId =(String)result.get(UtilMessages.RESPONSE_DATA);
        param.put("contactMechId",contactMechId);

        result = createPartyContactMech(param);
        if(!UtilMessages.isSuccess(result)){
            return result;
        }

        return UtilMessages.returnSuccessWithData(contactMechId);
    }

    /*
     <service name="updateTelecomNumber" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/ContactMechServices.xml" invoke="updateTelecomNumber" auth="true">
     <description>Update a Telecommunications Number</description>
     <auto-attributes entity-name="TelecomNumber" include="nonpk" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="INOUT" optional="false"/>
     <attribute name="oldContactMechId" type="String" mode="OUT" optional="false"/>
     </service>
     */
    public Map<String, Object> updateTelecomNumber(Map<String, ? extends Object> context) {
        /*
        <simple-method method-name="updateTelecomNumber" short-description="Update Contact Mechanism with Telecom Number">
        <make-value entity-name="TelecomNumber" value-field="newValue"/>
        <set-pk-fields value-field="newValue" map="parameters"/>
        <find-by-primary-key entity-name="TelecomNumber" map="newValue" value-field="oldValue"/>
        <set-nonpk-fields map="parameters" value-field="newValue"/>
        <set field="context.contactMechTypeId" value="TELECOM_NUMBER"/>

        <if-compare-field field="oldValue" to-field="newValue" operator="not-equals" type="Object">
            <log level="info" message="Telecom number needs updating"/>
            <call-service service-name="createContactMech" in-map-name="context">
                <default-message resource="PartyUiLabels" property="PartyTelecomNumberSuccessfullyUpdated"/>
                <result-to-field result-name="contactMechId" field="newValue.contactMechId"/>
            </call-service>
            <create-value value-field="newValue"/>
            <else>
                <set field="context.contactMechId" from-field="parameters.contactMechId"/>
                <call-service service-name="updateContactMech" in-map-name="context">
                    <default-message resource="PartyUiLabels" property="PartyTelecomNumberSuccessfullyUpdated"/>
                    <result-to-field result-name="contactMechId" field="newValue.contactMechId"/>
                </call-service>
                <if-compare-field to-field="newValue.contactMechId" field="oldValue.contactMechId" operator="not-equals">
                    <log level="info" message="Telecom Number need updating, contact mech changed"/>
                    <create-value value-field="newValue"/>
                    <else>
                        <log level="info" message="Telecom Number unchanged"/>
                    </else>
                </if-compare-field>
            </else>
        </if-compare-field>
        <field-to-result field="newValue.contactMechId" result-name="contactMechId"/>
        <field-to-result field="parameters.contactMechId" result-name="oldContactMechId"/>
    </simple-method>
        */

        Locale locale = (Locale) context.get("locale");
        String partyId = (String) context.get("partyId");
        String contactMechId = (String) context.get("contactMechId");

        Map<String,String> param = UtilMisc.toMap(context);
        param.put("contactMechTypeId","TELECOM_NUMBER");

        GenericValue newValue = GenericValue.fromMap(context,false);


        try {
            GenericValue oldValue = contactMechReadDao.findTelecomNumber(contactMechId);
            oldValue.setString("contactMechId",contactMechId);
            contactMechWriteDao.createTelecomNumber(oldValue);
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "contactmechservices.could_not_create_contact_info_write", locale));
        }

        return UtilMessages.returnSuccessWithData(contactMechId);
    }

    /*
     <service name="updatePartyTelecomNumber" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="updatePartyTelecomNumber" auth="true">
     <description>Update a Telecommunications Number</description>
     <permission-service service-name="partyContactMechPermissionCheck" main-action="UPDATE"/>
     <auto-attributes entity-name="PartyContactMech" include="all" mode="IN" optional="true"/>
     <auto-attributes entity-name="TelecomNumber" include="nonpk" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="INOUT" optional="false"/>
     </service>
     */
    public Map<String, Object> updatePartyTelecomNumber(Map<String, ? extends Object> context) {
        /*
        <simple-method method-name="updatePartyTelecomNumber" short-description="Update a TelecomNumber for party">
        <make-value entity-name="PartyContactMech" value-field="newPartyContactMech"/>
        <if-empty field="parameters.partyId">
            <set field="parameters.partyId" from-field="userLogin.partyId"/>
        </if-empty>

        <set-service-fields service-name="updateTelecomNumber" map="parameters" to-map="updateTelecomNumberMap"/>
        <call-service service-name="updateTelecomNumber" in-map-name="updateTelecomNumberMap">
            <default-message resource="PartyUiLabels" property="PartyTelecomNumberSuccessfullyUpdated"/>
            <result-to-field result-name="contactMechId" field="newPartyContactMech.contactMechId"/>
        </call-service>

        <set-service-fields service-name="updatePartyContactMech" map="parameters" to-map="updatePartyContactMechMap"/>
        <set field="updatePartyContactMechMap.newContactMechId" from-field="newPartyContactMech.contactMechId"/>
        <set field="updatePartyContactMechMap.contactMechTypeId" value="TELECOM_NUMBER"/>
        <log level="info" message="Copied id to updatePartyContactMechMap: ${updatePartyContactMechMap.newContactMechId}"/>

        <call-service in-map-name="updatePartyContactMechMap" service-name="updatePartyContactMech">
            <default-message resource="PartyUiLabels" property="PartyTelecomNumberSuccessfullyUpdated"/>
        </call-service>
        <log level="info" message="Setting result id: ${newPartyContactMech.contactMechId}"/>
        <field-to-request field="newPartyContactMech.contactMechId" request-name="contactMechId"/>
        <field-to-result field="newPartyContactMech.contactMechId" result-name="contactMechId"/>
    </simple-method>
        */
        return null;
    }

    /*
     <service name="createEmailAddress" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/ContactMechServices.xml" invoke="createEmailAddress" auth="true">
     <description>Create an Email Address</description>
     <auto-attributes entity-name="ContactMech" include="nonpk" mode="IN" optional="true"/>
     <auto-attributes entity-name="ContactMech" include="pk" mode="OUT" optional="false"/>
     <attribute name="emailAddress" type="String" mode="IN" optional="false"/>
     <override name="infoString" optional="true"/>
     </service>
     */
    public Map<String, Object> createEmailAddress(Map<String, ? extends Object> context) {
        /*
        <simple-method method-name="createEmailAddress" short-description="Create an email address contact mechanism">
        <call-map-processor xml-resource="component://party/script/org/ofbiz/party/contact/ContactMechMapProcs.xml"
            processor-name="emailAddress" in-map-name="parameters" out-map-name="context"/>
        <check-errors/>
        <set field="context.contactMechTypeId" value="EMAIL_ADDRESS"/>
        <call-service service-name="createContactMech" in-map-name="context">
            <default-message resource="PartyUiLabels" property="PartyEmailAddressSuccessfullyCreated"/>
            <result-to-result result-name="contactMechId"/>
            <result-to-request result-name="contactMechId"/>
        </call-service>
    </simple-method>
        */

        Locale locale = (Locale) context.get("locale");
        String emailAddress = (String) context.get("emailAddress");
        Map<String,String> params = UtilMisc.toMap(context);
        params.put("contactMechTypeId","EMAIL_ADDRESS");
        params.put("infoString",emailAddress);

        String contactMechId ="";

        Map<String, Object> result = createContactMech(params);
        if (UtilMessages.isSuccess(result)) {
            contactMechId = (String) result.get(UtilMessages.RESPONSE_DATA);
        }else {
            return result;
        }

        return UtilMessages.returnSuccessWithData(contactMechId);
    }

    /*
     <service name="createPartyEmailAddress" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="createPartyEmailAddress" auth="true">
     <description>Create an Email Address</description>
     <permission-service service-name="partyContactMechPermissionCheck" main-action="CREATE"/>
     <auto-attributes entity-name="ContactMech" include="nonpk" mode="IN" optional="true"/>
     <auto-attributes entity-name="PartyContactMech" include="all" mode="IN" optional="true"/>
     <attribute name="contactMechPurposeTypeId" type="String" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="INOUT" optional="true"/>
     <attribute name="emailAddress" type="String" mode="IN" optional="false"/>
     </service>
     */
    public Map<String, Object> createPartyEmailAddress(Map<String, ? extends Object> context) {
        /*
         <simple-method method-name="createPartyEmailAddress" short-description="Create an email address for party">
        <if-empty field="parameters.partyId">
            <set field="parameters.partyId" from-field="userLogin.partyId"/>
        </if-empty>

        <if-validate-method field="parameters.emailAddress" method="isEmail">
            <else><add-error><fail-property resource="PartyUiLabels" property="PartyEmailAddressNotFormattedCorrectly"/></add-error></else>
        </if-validate-method>
        <check-errors/>

        <!-- if e-mail address already exists simply return -->
        <entity-condition list="partyAndContactMechs" entity-name="PartyAndContactMech">
            <condition-list combine="and">
                <condition-expr field-name="partyId" from-field="parameters.partyId"/>
                <condition-expr field-name="contactMechTypeId" from-field="EMAIL_ADDRESS"/>
                <condition-expr field-name="infoString" from-field="parameters.emailAddress" ignore-case="true"/>
            </condition-list>
        </entity-condition>
        <filter-list-by-date list="partyAndContactMechs"/>
        <if-not-empty field="partyAndContactMechs">
            <log level="info" message="E-mail address: ${parameters.emailAddress} already exists, did not add again.."/>
            <first-from-list entry="existsPartyAndContactMech" list="partyAndContactMechs"/>
            <field-to-result field="existsPartyAndContactMech.contactMechId" result-name="contactMechId"/>
            <field-to-request field="existsPartyAndContactMech.contactMechId" request-name="contactMechId"/>
            <return/>
        </if-not-empty>

        <set-service-fields service-name="createPartyContactMech" map="parameters" to-map="createPartyContactMechMap"/>
        <set field="createPartyContactMechMap.infoString" from-field="parameters.emailAddress"/>
        <set field="createPartyContactMechMap.contactMechTypeId" value="EMAIL_ADDRESS"/>
        <call-service service-name="createPartyContactMech" in-map-name="createPartyContactMechMap">
            <default-message resource="PartyUiLabels" property="PartyEmailAddressSuccessfullyCreated"/>
            <result-to-result result-name="contactMechId"/>
            <result-to-request result-name="contactMechId"/>
        </call-service>
    </simple-method>
        */

        Locale locale = (Locale) context.get("locale");
        String emailAddress = (String)context.get("emailAddress");
        String partyId = (String)context.get("partyId");

        if (UtilValidate.isEmpty(partyId)) {
            return UtilMessages.returnParamError(locale, "partyId");
        }

        if(!UtilValidate.isEmail(emailAddress)){
            return UtilMessages.returnError(UtilProperties.getMessage(resource ,
                    "PartyEmailAddressNotFormattedCorrectly", locale));
        }

        try {
            String contactMechId ="";
            GenericValue params = GenericValue.fromMap(context,false);
            params.setString("contactMechTypeId","EMAIL_ADDRESS");
            params.setString("partyId", partyId);
            params.setString("infoString", emailAddress);
            List<GenericValue> partyAndContactMechs = contactMechReadDao.findPartyAndContactMech(params);
            List<GenericValue> validPartyContactMechs = EntityUtil.filterByDate(partyAndContactMechs);
            if(validPartyContactMechs != null && validPartyContactMechs.size() > 0){
                GenericValue partyContactMech= validPartyContactMechs.get(0);
                contactMechId = partyContactMech.getString("contactMechId");
                return UtilMessages.returnSuccessWithData(contactMechId);
            }

            Map<String,String> newMap = params.toMap();
            Map<String, Object> result = createPartyContactMech(newMap);
            if (UtilMessages.isSuccess(result)) {
                contactMechId = (String) result.get(UtilMessages.RESPONSE_DATA);
            }else {
                return result;
            }

            return UtilMessages.returnSuccessWithData(contactMechId);
        } catch (GenericEntityException e) {
            e.printStackTrace();
            return UtilMessages.returnError(UtilProperties.getMessage(resourceError,
                    "contactmechservices.could_not_create_contact_info_write", locale));
        }
    }


    /*
     <service name="updateEmailAddress" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/ContactMechServices.xml" invoke="updateEmailAddress" auth="true">
     <description>Update an Email Address</description>
     <attribute name="contactMechId" type="String" mode="INOUT" optional="false"/>
     <attribute name="emailAddress" type="String" mode="IN" optional="false"/>
     </service>
     */
    public Map<String, Object> updateEmailAddress(Map<String, ? extends Object> context) {
        /*
        <simple-method method-name="updateEmailAddress" short-description="Update an email address contact mechanism">
        <call-map-processor xml-resource="component://party/script/org/ofbiz/party/contact/ContactMechMapProcs.xml"
            processor-name="emailAddress" in-map-name="parameters" out-map-name="context"/>
        <check-errors/>
        <set field="context.contactMechTypeId" value="EMAIL_ADDRESS"/>
        <call-service service-name="updateContactMech" in-map-name="context">
            <default-message resource="PartyUiLabels" property="PartyEmailAddressSuccessfullyUpdated"/>
            <result-to-result result-name="contactMechId"/>
            <result-to-request result-name="contactMechId"/>
        </call-service>
    </simple-method>
        */
        Locale locale = (Locale) context.get("locale");

        Map<String,String> params = UtilMisc.toMap(context);
        params.put("contactMechTypeId","EMAIL_ADDRESS");
        String contactMechId ="";

        Map<String, Object> result = updateContactMech(params);
        if (UtilMessages.isSuccess(result)) {
            contactMechId = (String) result.get(UtilMessages.RESPONSE_DATA);
        }else {
            return result;
        }

        return UtilMessages.returnSuccessWithData(contactMechId);

    }

    /*
     <service name="updatePartyEmailAddress" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="updatePartyEmailAddress" auth="true">
     <description>Update an Email Address</description>
     <permission-service service-name="partyContactMechPermissionCheck" main-action="UPDATE"/>
     <auto-attributes entity-name="PartyContactMech" include="all" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="INOUT" optional="false"/> <!-- the out paramater is the id of the new address -->
     <attribute name="emailAddress" type="String" mode="IN" optional="false"/>
     <attribute name="oldContactMechId" type="String" mode="OUT" optional="false"/> <!-- this is the id of the old address -->
     </service>
     */
    public Map<String, Object> updatePartyEmailAddress(Map<String, ? extends Object> context) {
        /*
        <simple-method method-name="updatePartyEmailAddress" short-description="Update an email address for party">
        <if-empty field="parameters.partyId">
            <set field="parameters.partyId" from-field="userLogin.partyId"/>
        </if-empty>

        <if-validate-method field="parameters.emailAddress" method="isEmail">
            <else><add-error><fail-property resource="PartyUiLabels" property="PartyEmailAddressNotFormattedCorrectly"/></add-error></else>
        </if-validate-method>
        <check-errors/>

        <set-service-fields service-name="updatePartyContactMech" map="parameters" to-map="updatePartyContactMechMap"/>
        <set field="updatePartyContactMechMap.infoString" from-field="parameters.emailAddress"/>
        <set field="updatePartyContactMechMap.contactMechTypeId" value="EMAIL_ADDRESS"/>

        <call-service service-name="updatePartyContactMech" in-map-name="updatePartyContactMechMap">
            <default-message resource="PartyUiLabels" property="PartyEmailAddressSuccessfullyUpdated"/>
            <result-to-result result-name="contactMechId"/>
            <result-to-request result-name="contactMechId"/>
        </call-service>
        <field-to-result field="parameters.contactMechId" result-name="oldContactMechId"/>
    </simple-method>
        */
        Locale locale = (Locale) context.get("locale");
        String emailAddress = (String)context.get("emailAddress");

        if(!UtilValidate.isEmail(emailAddress)){
            return UtilMessages.returnError(UtilProperties.getMessage(resource ,
                    "PartyEmailAddressNotFormattedCorrectly", locale));
        }

        Map<String,String> params = UtilMisc.toMap(context);
        params.put("contactMechTypeId","EMAIL_ADDRESS");
        params.put("infoString",emailAddress);
        String contactMechId ="";

        Map<String, Object> result = updatePartyContactMech(params);
        if (UtilMessages.isSuccess(result)) {
            contactMechId = (String) result.get(UtilMessages.RESPONSE_DATA);
        }else {
            return result;
        }

        return UtilMessages.returnSuccessWithData(contactMechId);
    }




}
