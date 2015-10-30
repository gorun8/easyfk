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

/*
*本文件存放与party相关的表结构
*
*/


-- ----------------------------
-- Table structure for party
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party` (
  `PARTY_ID` varchar(20) NOT NULL,
  `PARTY_TYPE_ID` varchar(20) default NULL,
  `EXTERNAL_ID` varchar(20) default NULL,
  `PREFERRED_CURRENCY_UOM_ID` varchar(20) default NULL,
  `DESCRIPTION` longtext,
  `STATUS_ID` varchar(20) default NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY_USER_LOGIN` varchar(250) default NULL,
  `LAST_MODIFIED_DATE` datetime default NULL,
  `LAST_MODIFIED_BY_USER_LOGIN` varchar(250) default NULL,
  `DATA_SOURCE_ID` varchar(20) default NULL,
  `IS_UNREAD` char(1) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_acctg_preference
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_acctg_preference` (
  `PARTY_ID` varchar(20) NOT NULL,
  `FISCAL_YEAR_START_MONTH` decimal(20,0) default NULL,
  `FISCAL_YEAR_START_DAY` decimal(20,0) default NULL,
  `TAX_FORM_ID` varchar(20) default NULL,
  `COGS_METHOD_ID` varchar(20) default NULL,
  `BASE_CURRENCY_UOM_ID` varchar(20) default NULL,
  `INVOICE_SEQ_CUST_METH_ID` varchar(20) default NULL,
  `INVOICE_ID_PREFIX` varchar(10) default NULL,
  `LAST_INVOICE_NUMBER` decimal(20,0) default NULL,
  `LAST_INVOICE_RESTART_DATE` datetime default NULL,
  `USE_INVOICE_ID_FOR_RETURNS` char(1) default NULL,
  `QUOTE_SEQ_CUST_METH_ID` varchar(20) default NULL,
  `QUOTE_ID_PREFIX` varchar(10) default NULL,
  `LAST_QUOTE_NUMBER` decimal(20,0) default NULL,
  `ORDER_SEQ_CUST_METH_ID` varchar(20) default NULL,
  `ORDER_ID_PREFIX` varchar(10) default NULL,
  `LAST_ORDER_NUMBER` decimal(20,0) default NULL,
  `REFUND_PAYMENT_METHOD_ID` varchar(20) default NULL,
  `ERROR_GL_JOURNAL_ID` varchar(20) default NULL,
  `INVOICE_SEQUENCE_ENUM_ID` varchar(20) default NULL,
  `ORDER_SEQUENCE_ENUM_ID` varchar(20) default NULL,
  `QUOTE_SEQUENCE_ENUM_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_attribute
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_attribute` (
  `PARTY_ID` varchar(20) NOT NULL,
  `ATTR_NAME` varchar(60) NOT NULL,
  `ATTR_VALUE` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`ATTR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_benefit
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_benefit` (
  `ROLE_TYPE_ID_FROM` varchar(20) NOT NULL,
  `ROLE_TYPE_ID_TO` varchar(20) NOT NULL,
  `PARTY_ID_FROM` varchar(20) NOT NULL,
  `PARTY_ID_TO` varchar(20) NOT NULL,
  `BENEFIT_TYPE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `PERIOD_TYPE_ID` varchar(20) default NULL,
  `COST` decimal(18,2) default NULL,
  `ACTUAL_EMPLOYER_PAID_PERCENT` double default NULL,
  `AVAILABLE_TIME` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`ROLE_TYPE_ID_FROM`,`ROLE_TYPE_ID_TO`,`PARTY_ID_FROM`,`PARTY_ID_TO`,`BENEFIT_TYPE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_carrier_account
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_carrier_account` (
  `PARTY_ID` varchar(20) NOT NULL,
  `CARRIER_PARTY_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `ACCOUNT_NUMBER` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`CARRIER_PARTY_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_classification
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_classification` (
  `PARTY_ID` varchar(20) NOT NULL,
  `PARTY_CLASSIFICATION_GROUP_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`PARTY_CLASSIFICATION_GROUP_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_classification_group
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_classification_group` (
  `PARTY_CLASSIFICATION_GROUP_ID` varchar(20) NOT NULL,
  `PARTY_CLASSIFICATION_TYPE_ID` varchar(20) default NULL,
  `PARENT_GROUP_ID` varchar(20) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_CLASSIFICATION_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_classification_type
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_classification_type` (
  `PARTY_CLASSIFICATION_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_CLASSIFICATION_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_contact_mech
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_contact_mech` (
  `PARTY_ID` varchar(20) NOT NULL,
  `CONTACT_MECH_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `ROLE_TYPE_ID` varchar(20) default NULL,
  `ALLOW_SOLICITATION` char(1) default NULL,
  `EXTENSION` varchar(255) default NULL,
  `VERIFIED` char(1) default NULL,
  `COMMENTS` varchar(255) default NULL,
  `YEARS_WITH_CONTACT_MECH` decimal(20,0) default NULL,
  `MONTHS_WITH_CONTACT_MECH` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`CONTACT_MECH_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_contact_mech_purpose
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_contact_mech_purpose` (
  `PARTY_ID` varchar(20) NOT NULL,
  `CONTACT_MECH_ID` varchar(20) NOT NULL,
  `CONTACT_MECH_PURPOSE_TYPE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`CONTACT_MECH_ID`,`CONTACT_MECH_PURPOSE_TYPE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_content
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_content` (
  `PARTY_ID` varchar(20) NOT NULL,
  `CONTENT_ID` varchar(20) NOT NULL,
  `PARTY_CONTENT_TYPE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`CONTENT_ID`,`PARTY_CONTENT_TYPE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_content_type
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_content_type` (
  `PARTY_CONTENT_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_CONTENT_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_data_source
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_data_source` (
  `PARTY_ID` varchar(20) NOT NULL,
  `DATA_SOURCE_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `VISIT_ID` varchar(20) default NULL,
  `COMMENTS` varchar(255) default NULL,
  `IS_CREATE` char(1) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`DATA_SOURCE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_fixed_asset_assignment
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_fixed_asset_assignment` (
  `PARTY_ID` varchar(20) NOT NULL,
  `ROLE_TYPE_ID` varchar(20) NOT NULL,
  `FIXED_ASSET_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `ALLOCATED_DATE` datetime default NULL,
  `STATUS_ID` varchar(20) default NULL,
  `COMMENTS` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`ROLE_TYPE_ID`,`FIXED_ASSET_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_geo_point
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_geo_point` (
  `PARTY_ID` varchar(20) NOT NULL,
  `GEO_POINT_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`GEO_POINT_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_gl_account
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_gl_account` (
  `ORGANIZATION_PARTY_ID` varchar(20) NOT NULL,
  `PARTY_ID` varchar(20) NOT NULL,
  `ROLE_TYPE_ID` varchar(20) NOT NULL,
  `GL_ACCOUNT_TYPE_ID` varchar(20) NOT NULL,
  `GL_ACCOUNT_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`ORGANIZATION_PARTY_ID`,`PARTY_ID`,`ROLE_TYPE_ID`,`GL_ACCOUNT_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_group
-- ----------------------------
CREATE TABLE IF NOT EXISTS `party_group` (
  `PARTY_ID` varchar(20) NOT NULL,
  `GROUP_NAME` varchar(100) default NULL,
  `GROUP_NAME_LOCAL` varchar(100) default NULL,
  `OFFICE_SITE_NAME` varchar(100) default NULL,
  `ANNUAL_REVENUE` decimal(18,2) default NULL,
  `NUM_EMPLOYEES` decimal(20,0) default NULL,
  `TICKER_SYMBOL` varchar(10) default NULL,
  `COMMENTS` varchar(255) default NULL,
  `LOGO_IMAGE_URL` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_ics_avs_override
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_ics_avs_override` (
  `PARTY_ID` varchar(20) NOT NULL,
  `AVS_DECLINE_STRING` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_identification
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_identification` (
  `PARTY_ID` varchar(20) NOT NULL,
  `PARTY_IDENTIFICATION_TYPE_ID` varchar(20) NOT NULL,
  `ID_VALUE` varchar(60) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`PARTY_IDENTIFICATION_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_identification_type
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_identification_type` (
  `PARTY_IDENTIFICATION_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_IDENTIFICATION_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_invitation
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_invitation` (
  `PARTY_INVITATION_ID` varchar(20) NOT NULL,
  `PARTY_ID_FROM` varchar(20) default NULL,
  `PARTY_ID` varchar(20) default NULL,
  `TO_NAME` varchar(100) default NULL,
  `EMAIL_ADDRESS` varchar(255) default NULL,
  `STATUS_ID` varchar(20) default NULL,
  `LAST_INVITE_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_INVITATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_invitation_group_assoc
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_invitation_group_assoc` (
  `PARTY_INVITATION_ID` varchar(20) NOT NULL,
  `PARTY_ID_TO` varchar(20) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_INVITATION_ID`,`PARTY_ID_TO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_invitation_role_assoc
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_invitation_role_assoc` (
  `PARTY_INVITATION_ID` varchar(20) NOT NULL,
  `ROLE_TYPE_ID` varchar(20) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_INVITATION_ID`,`ROLE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_name_history
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_name_history` (
  `PARTY_ID` varchar(20) NOT NULL,
  `CHANGE_DATE` datetime NOT NULL,
  `GROUP_NAME` varchar(100) default NULL,
  `FIRST_NAME` varchar(100) default NULL,
  `MIDDLE_NAME` varchar(100) default NULL,
  `LAST_NAME` varchar(100) default NULL,
  `PERSONAL_TITLE` varchar(100) default NULL,
  `SUFFIX` varchar(100) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`CHANGE_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_need
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_need` (
  `PARTY_NEED_ID` varchar(20) NOT NULL,
  `PARTY_ID` varchar(20) NOT NULL,
  `ROLE_TYPE_ID` varchar(20) NOT NULL,
  `PARTY_TYPE_ID` varchar(20) default NULL,
  `NEED_TYPE_ID` varchar(20) default NULL,
  `COMMUNICATION_EVENT_ID` varchar(20) default NULL,
  `PRODUCT_ID` varchar(20) default NULL,
  `PRODUCT_CATEGORY_ID` varchar(20) default NULL,
  `VISIT_ID` varchar(20) default NULL,
  `DATETIME_RECORDED` datetime default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_NEED_ID`,`PARTY_ID`,`ROLE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_note
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_note` (
  `PARTY_ID` varchar(20) NOT NULL,
  `NOTE_ID` varchar(20) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`NOTE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_profile_default
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_profile_default` (
  `PARTY_ID` varchar(20) NOT NULL,
  `PRODUCT_STORE_ID` varchar(20) NOT NULL,
  `DEFAULT_SHIP_ADDR` varchar(20) default NULL,
  `DEFAULT_BILL_ADDR` varchar(20) default NULL,
  `DEFAULT_PAY_METH` varchar(20) default NULL,
  `DEFAULT_SHIP_METH` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`PRODUCT_STORE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_qual
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_qual` (
  `PARTY_ID` varchar(20) NOT NULL,
  `PARTY_QUAL_TYPE_ID` varchar(20) NOT NULL,
  `QUALIFICATION_DESC` varchar(60) default NULL,
  `TITLE` varchar(60) default NULL,
  `STATUS_ID` varchar(20) default NULL,
  `VERIF_STATUS_ID` varchar(20) default NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`PARTY_QUAL_TYPE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_qual_type
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_qual_type` (
  `PARTY_QUAL_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_QUAL_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_rate
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_rate` (
  `PARTY_ID` varchar(20) NOT NULL,
  `RATE_TYPE_ID` varchar(20) NOT NULL,
  `CURRENCY_UOM_ID` varchar(20) NOT NULL,
  `DEFAULT_RATE` char(1) default NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `RATE` decimal(18,2) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`RATE_TYPE_ID`,`CURRENCY_UOM_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_rate_new
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_rate_new` (
  `PARTY_ID` varchar(20) NOT NULL,
  `RATE_TYPE_ID` varchar(20) NOT NULL,
  `DEFAULT_RATE` char(1) default NULL,
  `PERCENTAGE_USED` double default NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`RATE_TYPE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_relationship
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_relationship` (
  `PARTY_ID_FROM` varchar(20) NOT NULL,
  `PARTY_ID_TO` varchar(20) NOT NULL,
  `ROLE_TYPE_ID_FROM` varchar(20) NOT NULL,
  `ROLE_TYPE_ID_TO` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `STATUS_ID` varchar(20) default NULL,
  `RELATIONSHIP_NAME` varchar(100) default NULL,
  `SECURITY_GROUP_ID` varchar(20) default NULL,
  `PRIORITY_TYPE_ID` varchar(20) default NULL,
  `PARTY_RELATIONSHIP_TYPE_ID` varchar(20) default NULL,
  `PERMISSIONS_ENUM_ID` varchar(20) default NULL,
  `POSITION_TITLE` varchar(100) default NULL,
  `COMMENTS` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID_FROM`,`PARTY_ID_TO`,`ROLE_TYPE_ID_FROM`,`ROLE_TYPE_ID_TO`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_relationship_type
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_relationship_type` (
  `PARTY_RELATIONSHIP_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `PARTY_RELATIONSHIP_NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `ROLE_TYPE_ID_VALID_FROM` varchar(20) default NULL,
  `ROLE_TYPE_ID_VALID_TO` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_RELATIONSHIP_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_resume
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_resume` (
  `RESUME_ID` varchar(20) NOT NULL,
  `PARTY_ID` varchar(20) default NULL,
  `CONTENT_ID` varchar(20) default NULL,
  `RESUME_DATE` datetime default NULL,
  `RESUME_TEXT` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`RESUME_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_role
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_role` (
  `PARTY_ID` varchar(20) NOT NULL,
  `ROLE_TYPE_ID` varchar(20) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`ROLE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_skill
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_skill` (
  `PARTY_ID` varchar(20) NOT NULL,
  `SKILL_TYPE_ID` varchar(20) NOT NULL,
  `YEARS_EXPERIENCE` decimal(20,0) default NULL,
  `RATING` decimal(20,0) default NULL,
  `SKILL_LEVEL` decimal(20,0) default NULL,
  `STARTED_USING_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`SKILL_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_status
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_status` (
  `STATUS_ID` varchar(20) NOT NULL,
  `PARTY_ID` varchar(20) NOT NULL,
  `STATUS_DATE` datetime NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`STATUS_ID`,`PARTY_ID`,`STATUS_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_tax_auth_info
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_tax_auth_info` (
  `PARTY_ID` varchar(20) NOT NULL,
  `TAX_AUTH_GEO_ID` varchar(20) NOT NULL,
  `TAX_AUTH_PARTY_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `PARTY_TAX_ID` varchar(60) default NULL,
  `IS_EXEMPT` char(1) default NULL,
  `IS_NEXUS` char(1) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`TAX_AUTH_GEO_ID`,`TAX_AUTH_PARTY_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_tax_info
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_tax_info` (
  `PARTY_ID` varchar(20) NOT NULL,
  `GEO_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `PARTY_TAX_ID` varchar(60) default NULL,
  `IS_EXEMPT` char(1) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`GEO_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_type
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_type` (
  `PARTY_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for party_type_attr
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `party_type_attr` (
  `PARTY_TYPE_ID` varchar(20) NOT NULL,
  `ATTR_NAME` varchar(60) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_TYPE_ID`,`ATTR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;