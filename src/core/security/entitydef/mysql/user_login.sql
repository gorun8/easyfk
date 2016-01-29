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
-- Table structure for user_login
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user_login` (
  `USER_LOGIN_ID` varchar(250) NOT NULL,
  `CURRENT_PASSWORD` varchar(60) default NULL,
  `PLAIN_PASSWORD` varchar(60) default NULL,
  `PASSWORD_HINT` varchar(255) default NULL,
  `IS_SYSTEM` char(1) default NULL,
  `ENABLED` char(1) default NULL,
  `HAS_LOGGED_OUT` char(1) default NULL,
  `REQUIRE_PASSWORD_CHANGE` char(1) default NULL,
  `LAST_CURRENCY_UOM` varchar(20) default NULL,
  `LAST_LOCALE` varchar(10) default NULL,
  `LAST_TIME_ZONE` varchar(60) default NULL,
  `DISABLED_DATE_TIME` datetime default NULL,
  `SUCCESSIVE_FAILED_LOGINS` decimal(20,0) default NULL,
  `EXTERNAL_AUTH_ID` varchar(250) default NULL,
  `USER_LDAP_DN` varchar(250) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  `PARTY_ID` varchar(20) default NULL,
  PRIMARY KEY  (`USER_LOGIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_login_history
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user_login_history` (
  `USER_LOGIN_ID` varchar(250) NOT NULL,
  `VISIT_ID` varchar(20) default NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `PASSWORD_USED` varchar(60) default NULL,
  `SUCCESSFUL_LOGIN` char(1) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  `PARTY_ID` varchar(20) default NULL,
  PRIMARY KEY  (`USER_LOGIN_ID`,`FROM_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_login_password_history
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user_login_password_history` (
  `USER_LOGIN_ID` varchar(250) NOT NULL,
  `FROM_DATE` datetime NOT NULL,
  `THRU_DATE` datetime default NULL,
  `CURRENT_PASSWORD` varchar(60) default NULL,
  `LAST_UPDATED_STAMP` datetime default NULL,
  `LAST_UPDATED_TX_STAMP` datetime default NULL,
  `CREATED_STAMP` datetime default NULL,
  `CREATED_TX_STAMP` datetime default NULL,
  PRIMARY KEY  (`USER_LOGIN_ID`,`FROM_DATE`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for user_login_session
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user_login_session` (
  `SESSION_ID` varchar(250) NOT NULL,
  `SESSION_DATA` longtext,
  `SAVED_DATE` datetime default NULL,
  PRIMARY KEY  (`SESSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

