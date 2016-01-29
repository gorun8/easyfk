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

 
-- ----------------------------
-- Table structure for portal_page
-- ----------------------------
 CREATE TABLE IF NOT EXISTS  `portal_page` (
  `PORTAL_PAGE_ID` varchar(20) NOT NULL,
  `PORTAL_PAGE_NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `OWNER_USER_LOGIN_ID` varchar(20) default NULL,
  `ORIGINAL_PORTAL_PAGE_ID` varchar(20) default NULL,
  `PARENT_PORTAL_PAGE_ID` varchar(20) default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `SECURITY_GROUP_ID` varchar(20) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  `HELP_CONTENT_ID` varchar(20) default NULL,
  PRIMARY KEY  (`PORTAL_PAGE_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portal_page_column
-- ----------------------------
 CREATE TABLE IF NOT EXISTS  `portal_page_column` (
  `PORTAL_PAGE_ID` varchar(20) NOT NULL,
  `COLUMN_SEQ_ID` varchar(20) NOT NULL,
  `COLUMN_WIDTH_PIXELS` decimal(20,0) default NULL,
  `COLUMN_WIDTH_PERCENTAGE` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PORTAL_PAGE_ID`,`COLUMN_SEQ_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portal_page_portlet
-- ----------------------------
 CREATE TABLE IF NOT EXISTS  `portal_page_portlet` (
  `PORTAL_PAGE_ID` varchar(20) NOT NULL,
  `PORTAL_PORTLET_ID` varchar(20) NOT NULL,
  `PORTLET_SEQ_ID` varchar(20) NOT NULL,
  `COLUMN_SEQ_ID` varchar(20) default NULL,
  `SEQUENCE_NUM` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PORTAL_PAGE_ID`,`PORTAL_PORTLET_ID`,`PORTLET_SEQ_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portal_portlet
-- ----------------------------
 CREATE TABLE IF NOT EXISTS  `portal_portlet` (
  `PORTAL_PORTLET_ID` varchar(20) NOT NULL,
  `PORTLET_NAME` varchar(100) default NULL,
  `SCREEN_NAME` varchar(255) default NULL,
  `SCREEN_LOCATION` varchar(255) default NULL,
  `EDIT_FORM_NAME` varchar(255) default NULL,
  `EDIT_FORM_LOCATION` varchar(255) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `SCREENSHOT` varchar(255) default NULL,
  `SECURITY_SERVICE_NAME` varchar(255) default NULL,
  `SECURITY_MAIN_ACTION` varchar(60) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PORTAL_PORTLET_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portlet_attribute
-- ----------------------------
 CREATE TABLE IF NOT EXISTS  `portlet_attribute` (
  `PORTAL_PAGE_ID` varchar(20) NOT NULL,
  `PORTAL_PORTLET_ID` varchar(20) NOT NULL,
  `PORTLET_SEQ_ID` varchar(20) NOT NULL,
  `ATTR_NAME` varchar(60) NOT NULL,
  `ATTR_VALUE` varchar(255) default NULL,
  `ATTR_TYPE` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PORTAL_PAGE_ID`,`PORTAL_PORTLET_ID`,`PORTLET_SEQ_ID`,`ATTR_NAME`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portlet_category
-- ----------------------------
 CREATE TABLE IF NOT EXISTS  `portlet_category` (
  `PORTLET_CATEGORY_ID` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PORTLET_CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for portlet_portlet_category
-- ----------------------------
 CREATE TABLE IF NOT EXISTS  `portlet_portlet_category` (
  `PORTAL_PORTLET_ID` varchar(20) NOT NULL,
  `PORTLET_CATEGORY_ID` varchar(20) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PORTAL_PORTLET_ID`,`PORTLET_CATEGORY_ID`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
