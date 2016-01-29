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
*本文件定义与PARTY相关的初始数据
*
*/

-- ----------------------------
-- Table structure for person
-- ----------------------------
INSERT INTO `party_classification_type` VALUES ('ORGANIZATION_CLASSIF', null, 'N', '组织', '2014-06-11 15:21:30', '2014-06-11 15:21:30', '2014-06-11 15:21:30', '2014-06-11 15:21:30');
INSERT INTO `party_classification_type` VALUES ('PERSON_CLASSIFICATIO', null, 'N', '人员', '2014-06-11 15:21:30', '2014-06-11 15:21:30', '2014-06-11 15:21:30', '2014-06-11 15:21:30');
INSERT INTO `party_classification_group` VALUES ('_NA_', 'ORGANIZATION_CLASSIF', '', '组织机构目录', '2014-06-11 15:21:30', '2014-06-11 15:21:30', '2014-06-11 15:21:30', '2014-06-11 15:21:30');
-- ----------------------------
--  party status change defined
-- ----------------------------
INSERT INTO `status_type` VALUES ('PARTY_STATUS', null, 'N', '会员', '2015-11-23 11:42:15', '2015-11-23 11:42:14', '2015-11-23 11:42:15', '2015-11-23 11:42:14');
INSERT INTO `status_item` VALUES ('PARTY_DISABLED', 'PARTY_STATUS', 'DISABLED', '99', '停用', '2015-11-23 11:42:15', '2015-11-23 11:42:14', '2015-11-23 11:42:15', '2015-11-23 11:42:14');
INSERT INTO `status_item` VALUES ('PARTY_ENABLED', 'PARTY_STATUS', 'ENABLED', '01', '启用', '2015-11-23 11:42:15', '2015-11-23 11:42:14', '2015-11-23 11:42:15', '2015-11-23 11:42:14');
INSERT INTO `status_valid_change` VALUES ('PARTY_DISABLED', 'PARTY_ENABLED', null, '重新启用', '2015-11-23 11:42:15', '2015-11-23 11:42:14', '2015-11-23 11:42:15', '2015-11-23 11:42:14');
INSERT INTO `status_valid_change` VALUES ('PARTY_ENABLED', 'PARTY_DISABLED', null, '停用', '2015-11-23 11:42:15', '2015-11-23 11:42:14', '2015-11-23 11:42:15', '2015-11-23 11:42:14');



