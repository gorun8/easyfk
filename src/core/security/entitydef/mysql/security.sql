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
 CREATE TABLE IF NOT EXISTS `security_group` (
  `GROUP_ID` varchar(20) NOT NULL,
  `NAME` varchar(100) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `IS_SYSTEM` char(1) default 'N',
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`GROUP_ID`),
  KEY `SCRT_GRP_TXSTMP` (`LAST_UPDATED_TX_STAMP`),
  KEY `SCRT_GRP_TXCRTS` (`CREATED_TX_STAMP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for security_group_permission
-- ----------------------------
CREATE TABLE IF NOT EXISTS `security_group_permission` (
  `GROUP_ID` varchar(20) NOT NULL,
  `PERMISSION_ID` varchar(60) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`GROUP_ID`,`PERMISSION_ID`),
  KEY `SEC_GRP_PERM_GRP` (`GROUP_ID`),
  KEY `SCT_GRP_PRMN_TXSTP` (`LAST_UPDATED_TX_STAMP`),
  KEY `SCT_GRP_PRMN_TXCRS` (`CREATED_TX_STAMP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for security_permission
-- add PERMISSION_TAG field for shiro permission
-- ----------------------------
CREATE TABLE IF NOT EXISTS `security_permission` (
  `PERMISSION_ID` varchar(60) NOT NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `DYNAMIC_ACCESS` varchar(255) default NULL,
  `PERMISSION_TAG` varchar(60) NOT NULL ,
  `PARENT_ID` varchar(60) NOT NULL,
  `IS_SYSTEM` char(1) default 'N',
  `RELATIVE_CLASS` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PERMISSION_ID`),
  KEY `SCRT_PRMSSN_TXSTMP` (`LAST_UPDATED_TX_STAMP`),
  KEY `SCRT_PRMSSN_TXCRTS` (`CREATED_TX_STAMP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for security_permission_auto_grant
-- ----------------------------
CREATE TABLE IF NOT EXISTS `security_permission_auto_grant` (
  `PERMISSION_ID` varchar(60) NOT NULL,
  `GRANT_PERMISSION` varchar(250) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PERMISSION_ID`,`GRANT_PERMISSION`),
  KEY `SEC_PERM_AUTO_GRNT` (`PERMISSION_ID`),
  KEY `SCT_PRN_AT_GRT_TXP` (`LAST_UPDATED_TX_STAMP`),
  KEY `SCT_PRN_AT_GRT_TXS` (`CREATED_TX_STAMP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_login_security_group
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user_login_security_group` (
  `USER_LOGIN_ID` varchar(250) NOT NULL,
  `GROUP_ID` varchar(20) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`USER_LOGIN_ID`,`GROUP_ID`,`FROM_DATE`),
  KEY `USER_SECGRP_USER` (`USER_LOGIN_ID`),
  KEY `USER_SECGRP_GRP` (`GROUP_ID`),
  KEY `USR_LGN_SCT_GRP_TP` (`LAST_UPDATED_TX_STAMP`),
  KEY `USR_LGN_SCT_GRP_TS` (`CREATED_TX_STAMP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS  `role_type` (
  `ROLE_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`ROLE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_type_attr
-- ----------------------------

CREATE TABLE  IF NOT EXISTS `role_type_attr` (
  `ROLE_TYPE_ID` varchar(20) NOT NULL,
  `ATTR_NAME` varchar(60) NOT NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`ROLE_TYPE_ID`,`ATTR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


