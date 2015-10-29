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

