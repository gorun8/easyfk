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
package cn.gorun8.easyfk.party.service;

import java.util.Map;

public interface PartyContactMechService {
    /**
     * <!-- ContactMech services -->
     <service name="createContactMech" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/ContactMechServices.xml" invoke="createContactMech" auth="true">
     <description>Create a ContactMech</description>
     <auto-attributes entity-name="ContactMech" include="nonpk" mode="IN" optional="false"/>
     <auto-attributes entity-name="ContactMech" include="pk" mode="INOUT" optional="true"/>
     <override name="infoString" optional="true"/>
     </service>
     */
     public Map<String,Object> createContactMech(Map<String, ? extends Object> context);

    /**
     <service name="createPartyContactMech" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="createPartyContactMech" auth="true">
     <description>Create a PartyContactMech</description>
     <permission-service service-name="partyContactMechPermissionCheck" main-action="CREATE"/>
     <auto-attributes entity-name="ContactMech" include="nonpk" mode="IN" optional="true"/>
     <auto-attributes entity-name="PartyContactMech" include="all" mode="IN" optional="true"/>
     <attribute name="contactMechPurposeTypeId" type="String" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="INOUT" optional="true"/>
     </service>
     */
     public Map<String,Object> createPartyContactMech(Map<String, ? extends Object> context);

    /*
     <service name="updateContactMech" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/ContactMechServices.xml" invoke="updateContactMech" auth="true">
     <description>Update a ContactMech</description>
     <attribute name="contactMechId" type="String" mode="INOUT" optional="false"/>
     <attribute name="contactMechTypeId" type="String" mode="IN" optional="false"/>
     <attribute name="infoString" type="String" mode="IN" optional="true"/>
     </service>
     */
    public Map<String,Object> updateContactMech(Map<String, ? extends Object> context);

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
     public Map<String,Object> updatePartyContactMech(Map<String, ? extends Object> context);

    /*
     <service name="deletePartyContactMech" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="deletePartyContactMech" auth="true">
     <description>Delete a PartyContactMech</description>
     <permission-service service-name="partyContactMechPermissionCheck" main-action="DELETE"/>
     <attribute name="partyId" type="String" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="IN" optional="false"/>
     </service>
     */
    public Map<String,Object> deletePartyContactMech(Map<String, ? extends Object> context);

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
    public Map<String,Object> createPostalAddress(Map<String, ? extends Object> context);

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
     public Map<String,Object> findPartyFromEmailAddress(Map<String, ? extends Object> context);

    /*
     <service name="findPartyFromTelephone" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="findPartyFromTelephone" auth="true">
     <description>Find the partyId/contactMechId for a specific telephone number, if not found do not return a value</description>
     <attribute name="telno" type="String" mode="IN" optional="false"/>
     <attribute name="partyId" type="String" mode="OUT" optional="true"/>
     <attribute name="contactMechId" type="String" mode="OUT" optional="true"/>
     </service>
     */
     public Map<String,Object> findPartyFromTelephone(Map<String, ? extends Object> context);

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
    public Map<String,Object> findPartyFromTelephoneComplete(Map<String, ? extends Object> context);


   /**
    * 根据partyId获取关联的联系方式
    * @param context
    * @return
    */
    public Map<String,Object> findPartyContactMech(Map<String, ? extends Object> context);

 /**
     <service name="createPartyPostalAddress" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="createPartyPostalAddress" auth="true">
     <description>Create a Postal Address</description>
     <permission-service service-name="partyContactMechPermissionCheck" main-action="CREATE"/>
     <auto-attributes entity-name="PartyContactMech" include="all" mode="IN" optional="true"/>
     <auto-attributes entity-name="PostalAddress" include="nonpk" mode="IN" optional="true"/>
     <attribute name="paymentMethodId" type="String" mode="IN" optional="true"/>
     <attribute name="contactMechPurposeTypeId" type="String" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="OUT" optional="false"/>
     <override name="address1" optional="false"/>
     <override name="city" optional="false"/>
     <override name="postalCode" optional="false"/>
     </service>
     */
    public Map<String,Object> createPartyPostalAddress(Map<String, ? extends Object> context);

    /**
     * <description>Update a Postal Address</description>
     <auto-attributes entity-name="PostalAddress" include="nonpk" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="INOUT" optional="false"/> <!-- the out paramater is the id of the new address -->
     <attribute name="directions" type="String" mode="IN" optional="true"/> <!-- ?? -->
     <attribute name="oldContactMechId" type="String" mode="OUT" optional="false"/> <!-- this is the id of the old address -->
     <attribute name="partyId" type="String" mode="IN" optional="true"/>
     <override name="address1" optional="false"/>
     <override name="city" optional="false"/>
     <override name="postalCode" optional="false"/>
     */
    public Map<String,Object> updatePostalAddress(Map<String, ? extends Object> context);

    /**
     * <service name="updatePartyPostalAddress" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/PartyContactMechServices.xml" invoke="updatePartyPostalAddress" auth="true">
     <description>Update a Postal Address</description>
     <permission-service service-name="partyContactMechPermissionCheck" main-action="UPDATE"/>
     <auto-attributes entity-name="PartyContactMech" include="all" mode="IN" optional="true"/>
     <auto-attributes entity-name="PostalAddress" include="nonpk" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="INOUT" optional="false"/>
     <attribute name="directions" type="String" mode="IN" optional="true"/> <!-- ?? -->
     <override name="address1" optional="false"/>
     <override name="city" optional="false"/>
     <override name="postalCode" optional="false"/>
     </service>
     */
    public Map<String,Object> updatePartyPostalAddress(Map<String, ? extends Object> context);

    /*
     <service name="createTelecomNumber" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/ContactMechServices.xml" invoke="createTelecomNumber" auth="true">
     <description>Create a Telecommunications Number</description>
     <auto-attributes entity-name="TelecomNumber" include="all" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="OUT" optional="false"/>
     </service>
     */
    public Map<String,Object> createTelecomNumber(Map<String, ? extends Object> context);

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
    public Map<String,Object> createPartyTelecomNumber(Map<String, ? extends Object> context);

    /*
     <service name="updateTelecomNumber" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/ContactMechServices.xml" invoke="updateTelecomNumber" auth="true">
     <description>Update a Telecommunications Number</description>
     <auto-attributes entity-name="TelecomNumber" include="nonpk" mode="IN" optional="true"/>
     <attribute name="contactMechId" type="String" mode="INOUT" optional="false"/>
     <attribute name="oldContactMechId" type="String" mode="OUT" optional="false"/>
     </service>
     */
    public Map<String,Object> updateTelecomNumber(Map<String, ? extends Object> context);

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
    public Map<String,Object> updatePartyTelecomNumber(Map<String, ? extends Object> context);

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
    public Map<String,Object> createEmailAddress(Map<String, ? extends Object> context);

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
    public Map<String,Object> createPartyEmailAddress(Map<String, ? extends Object> context);

    /*
     <service name="updateEmailAddress" engine="simple"
     location="component://party/script/org/ofbiz/party/contact/ContactMechServices.xml" invoke="updateEmailAddress" auth="true">
     <description>Update an Email Address</description>
     <attribute name="contactMechId" type="String" mode="INOUT" optional="false"/>
     <attribute name="emailAddress" type="String" mode="IN" optional="false"/>
     </service>
     */
    public Map<String,Object> updateEmailAddress(Map<String, ? extends Object> context);

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
    public Map<String,Object> updatePartyEmailAddress(Map<String, ? extends Object> context);


}
