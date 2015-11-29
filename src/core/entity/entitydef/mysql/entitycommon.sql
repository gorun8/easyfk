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
-- Table structure for sequence_value_item
-- ----------------------------
 CREATE TABLE IF NOT EXISTS  `sequence_value_item` (
  `SEQ_NAME` varchar(60) NOT NULL,
  `SEQ_ID` decimal(20,0) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 /*
 *版本号
 */
CREATE TABLE IF NOT EXISTS `version`(
  `ID` int(64) NOT NULL auto_increment,
  `VERSION_CODE` int(11) default NULL,
  `VERSION` varchar(255) default NULL,
  `LAST_UPDATE` varchar(255) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `version`(`VERSION_CODE`,`VERSION`,`LAST_UPDATE`) VALUES ('1', '1.0', now());


-- ----------------------------
-- Table structure for status_item
-- ----------------------------

CREATE TABLE  IF NOT EXISTS   `status_item` (
  `STATUS_ID` varchar(20) NOT NULL,
  `STATUS_TYPE_ID` varchar(20) default NULL,
  `STATUS_CODE` varchar(60) default NULL,
  `SEQUENCE_ID` varchar(20) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for status_type
-- ----------------------------

CREATE TABLE  IF NOT EXISTS  `status_type` (
  `STATUS_TYPE_ID` varchar(20) NOT NULL,
  `PARENT_TYPE_ID` varchar(20) default NULL,
  `HAS_TABLE` char(1) default NULL,
  `DESCRIPTION` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`STATUS_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for status_valid_change
-- ----------------------------

CREATE TABLE  IF NOT EXISTS  `status_valid_change` (
  `STATUS_ID` varchar(20) NOT NULL,
  `STATUS_ID_TO` varchar(20) NOT NULL,
  `CONDITION_EXPRESSION` varchar(255) default NULL,
  `TRANSITION_NAME` varchar(100) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`STATUS_ID`,`STATUS_ID_TO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



