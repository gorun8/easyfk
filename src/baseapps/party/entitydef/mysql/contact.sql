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

  CREATE TABLE IF NOT EXISTS `contact_list` (
  `CONTACT_LIST_ID` varchar(20) NOT NULL,
  `CONTACT_LIST_TYPE_ID` varchar(20) default NULL,
  `CONTACT_MECH_TYPE_ID` varchar(20) default NULL,
  `MARKETING_CAMPAIGN_ID` varchar(20) default NULL,
  `CONTACT_LIST_NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `COMMENTS` varchar(255) default NULL,
  `IS_PUBLIC` char(1) default NULL,
  `SINGLE_USE` char(1) default NULL,
  `OWNER_PARTY_ID` varchar(20) default NULL,
  `VERIFY_EMAIL_FROM` varchar(255) default NULL,
  `VERIFY_EMAIL_SCREEN` varchar(255) default NULL,
  `VERIFY_EMAIL_SUBJECT` varchar(255) default NULL,
  `VERIFY_EMAIL_WEB_SITE_ID` varchar(20) default NULL,
  `OPT_OUT_SCREEN` varchar(255) default NULL,
  `CREATED_BY_USER_LOGIN` varchar(250) default NULL,
  `LAST_MODIFIED_BY_USER_LOGIN` varchar(250) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_LIST_ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact_list_comm_status
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `contact_list_comm_status` (
  `CONTACT_LIST_ID` varchar(20) NOT NULL,
  `COMMUNICATION_EVENT_ID` varchar(20) NOT NULL,
  `CONTACT_MECH_ID` varchar(20) NOT NULL,
  `PARTY_ID` varchar(20) default NULL,
  `MESSAGE_ID` varchar(255) default NULL,
  `STATUS_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_LIST_ID`,`COMMUNICATION_EVENT_ID`,`CONTACT_MECH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact_list_party
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `contact_list_party` (
  `CONTACT_LIST_ID` varchar(20) NOT NULL,
  `PARTY_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `STATUS_ID` varchar(20) default NULL,
  `PREFERRED_CONTACT_MECH_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_LIST_ID`,`PARTY_ID`,`FROM_DATE`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact_list_party_status
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `contact_list_party_status` (
  `CONTACT_LIST_ID` varchar(20) NOT NULL,
  `PARTY_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `STATUS_DATE` datetime NOT NULL,
  `STATUS_ID` varchar(20) default NULL,
  `SET_BY_USER_LOGIN_ID` varchar(255) default NULL,
  `OPT_IN_VERIFY_CODE` varchar(60) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_LIST_ID`,`PARTY_ID`,`FROM_DATE`,`STATUS_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact_list_type
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `contact_list_type` (
  `CONTACT_LIST_TYPE_ID` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_LIST_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact_mech
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `contact_mech` (
  `CONTACT_MECH_ID` varchar(20) NOT NULL,
  `CONTACT_MECH_TYPE_ID` varchar(20) default NULL,
  `INFO_STRING` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_MECH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact_mech_attribute
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `contact_mech_attribute` (
  `CONTACT_MECH_ID` varchar(20) NOT NULL,
  `ATTR_NAME` varchar(60) NOT NULL,
  `ATTR_VALUE` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_MECH_ID`,`ATTR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact_mech_link
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `contact_mech_link` (
  `CONTACT_MECH_ID_FROM` varchar(20) NOT NULL,
  `CONTACT_MECH_ID_TO` varchar(20) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_MECH_ID_FROM`,`CONTACT_MECH_ID_TO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact_mech_purpose_type
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `contact_mech_purpose_type` (
  `CONTACT_MECH_PURPOSE_TYPE_ID` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_MECH_PURPOSE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact_mech_type
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `contact_mech_type` (
  `CONTACT_MECH_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_MECH_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact_mech_type_attr
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `contact_mech_type_attr` (
  `CONTACT_MECH_TYPE_ID` varchar(20) NOT NULL,
  `ATTR_NAME` varchar(60) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_MECH_TYPE_ID`,`ATTR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact_mech_type_purpose
-- ----------------------------
 CREATE TABLE IF NOT EXISTS `contact_mech_type_purpose` (
  `CONTACT_MECH_TYPE_ID` varchar(20) NOT NULL,
  `CONTACT_MECH_PURPOSE_TYPE_ID` varchar(20) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_MECH_TYPE_ID`,`CONTACT_MECH_PURPOSE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS  `postal_address` (
  `CONTACT_MECH_ID` varchar(20) NOT NULL,
  `TO_NAME` varchar(100) default NULL,
  `ATTN_NAME` varchar(100) default NULL,
  `ADDRESS1` varchar(255) default NULL,
  `ADDRESS2` varchar(255) default NULL,
  `DIRECTIONS` varchar(255) default NULL,
  `CITY` varchar(100) default NULL,
  `POSTAL_CODE` varchar(60) default NULL,
  `POSTAL_CODE_EXT` varchar(60) default NULL,
  `COUNTRY_GEO_ID` varchar(20) default NULL,
  `STATE_PROVINCE_GEO_ID` varchar(20) default NULL,
  `COUNTY_GEO_ID` varchar(20) default NULL,
  `POSTAL_CODE_GEO_ID` varchar(20) default NULL,
  `GEO_POINT_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_MECH_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for postal_address_boundary
-- ----------------------------
CREATE TABLE  IF NOT EXISTS  `postal_address_boundary` (
  `CONTACT_MECH_ID` varchar(20) NOT NULL,
  `GEO_ID` varchar(20) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_MECH_ID`,`GEO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE   IF NOT EXISTS  `telecom_number` (
  `CONTACT_MECH_ID` varchar(20) NOT NULL,
  `COUNTRY_CODE` varchar(10) default NULL,
  `AREA_CODE` varchar(10) default NULL,
  `CONTACT_NUMBER` varchar(60) default NULL,
  `ASK_FOR_NAME` varchar(100) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`CONTACT_MECH_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;