/*
 * Project:Easy Web Framework
 *
 * Description: This project is based on much more open source projects than ever before,
 *              and can be applied to mostly web development environment.
 * Author:hezhiping   Email:110476592@qq.com
 *
 *
 *==========================================================================================
 *
 */
/*
*本文件定义与PERSON相关的表结构
*
*/

-- ----------------------------
-- Table structure for person
-- ----------------------------
CREATE TABLE  IF NOT EXISTS `person` (
  `PARTY_ID` varchar(20) NOT NULL,
  `SALUTATION` varchar(100) default NULL,
  `FIRST_NAME` varchar(100) default NULL,
  `MIDDLE_NAME` varchar(100) default NULL,
  `LAST_NAME` varchar(100) default NULL,
  `PERSONAL_TITLE` varchar(100) default NULL,
  `SUFFIX` varchar(100) default NULL,
  `NICKNAME` varchar(100) default NULL,
  `FIRST_NAME_LOCAL` varchar(100) default NULL,
  `MIDDLE_NAME_LOCAL` varchar(100) default NULL,
  `LAST_NAME_LOCAL` varchar(100) default NULL,
  `OTHER_LOCAL` varchar(100) default NULL,
  `MEMBER_ID` varchar(20) default NULL,
  `GENDER` char(1) default NULL,
  `BIRTH_DATE` date default NULL,
  `DECEASED_DATE` date default NULL,
  `HEIGHT` double default NULL,
  `WEIGHT` double default NULL,
  `MOTHERS_MAIDEN_NAME` varchar(255) default NULL,
  `MARITAL_STATUS` char(1) default NULL,
  `SOCIAL_SECURITY_NUMBER` varchar(255) default NULL,
  `PASSPORT_NUMBER` varchar(255) default NULL,
  `PASSPORT_EXPIRE_DATE` date default NULL,
  `TOTAL_YEARS_WORK_EXPERIENCE` double default NULL,
  `COMMENTS` varchar(255) default NULL,
  `EMPLOYMENT_STATUS_ENUM_ID` varchar(20) default NULL,
  `RESIDENCE_STATUS_ENUM_ID` varchar(20) default NULL,
  `OCCUPATION` varchar(100) default NULL,
  `YEARS_WITH_EMPLOYER` decimal(20,0) default NULL,
  `MONTHS_WITH_EMPLOYER` decimal(20,0) default NULL,
  `EXISTING_CUSTOMER` char(1) default NULL,
  `CARD_ID` varchar(60) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for person_training
-- ----------------------------
CREATE TABLE  IF NOT EXISTS `person_training` (
  `PARTY_ID` varchar(20) NOT NULL,
  `TRAINING_REQUEST_ID` varchar(20) default NULL,
  `TRAINING_CLASS_TYPE_ID` varchar(20) NOT NULL,
  `WORK_EFFORT_ID` varchar(20) default NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `APPROVER_ID` varchar(20) default NULL,
  `APPROVAL_STATUS` varchar(60) default NULL,
  `REASON` varchar(255) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`PARTY_ID`,`TRAINING_CLASS_TYPE_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

