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

INSERT INTO `contact_mech_type` VALUES ('DOMAIN_NAME', 'ELECTRONIC_ADDRESS', 'N', '网络域名', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type` VALUES ('ELECTRONIC_ADDRESS', null, 'N', '电子地址', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type` VALUES ('EMAIL_ADDRESS', 'ELECTRONIC_ADDRESS', 'N', '电子邮件', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type` VALUES ('IP_ADDRESS', 'ELECTRONIC_ADDRESS', 'N', '网络IP地址', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type` VALUES ('POSTAL_ADDRESS', null, 'Y', '邮政地址', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type` VALUES ('TELECOM_NUMBER', null, 'Y', '电话号码', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type` VALUES ('INTERNAL_NUMBER', null, 'N', '网络号码', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type` VALUES ('WEB_ADDRESS', 'ELECTRONIC_ADDRESS', 'N', '网址', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_purpose_type` VALUES ('PHONE_HOME', '家庭电话号码', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_purpose_type` VALUES ('PHONE_WORK', '办公电话', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_purpose_type` VALUES ('PHONE_MOBILE', '手机号码', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_purpose_type` VALUES ('PRIMARY_EMAIL', '电子邮件', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_purpose_type` VALUES ('LOCATION_HOME', '家庭地址', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_purpose_type` VALUES ('LOCATION_WORK', '办公地址', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_purpose_type` VALUES ('WEIXIN_NUMBER', '微信号', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_purpose_type` VALUES ('QQ_NUMBER', 'QQ号', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type_purpose` VALUES ('TELECOM_NUMBER', 'PHONE_HOME', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type_purpose` VALUES ('TELECOM_NUMBER', 'PHONE_WORK', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type_purpose` VALUES ('TELECOM_NUMBER', 'PHONE_MOBILE', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type_purpose` VALUES ('EMAIL_ADDRESS', 'PRIMARY_EMAIL', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type_purpose` VALUES ('POSTAL_ADDRESS', 'LOCATION_HOME', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type_purpose` VALUES ('POSTAL_ADDRESS', 'LOCATION_WORK', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type_purpose` VALUES ('INTERNAL_NUMBER', 'WEIXIN_NUMBER', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');
INSERT INTO `contact_mech_type_purpose` VALUES ('INTERNAL_NUMBER', 'QQ_NUMBER', '2015-01-29 16:09:29', '2015-01-29 16:09:28', '2015-01-29 16:09:29', '2015-01-29 16:09:28');

