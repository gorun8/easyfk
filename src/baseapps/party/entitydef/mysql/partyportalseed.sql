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

INSERT INTO `portlet_category` VALUES ('PARTY_PROFILE', 'Profiles', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');
INSERT INTO `portal_portlet` VALUES ('CALENDAR_PORTLET', 'Calendar', 'Calendar', '/party/dyn/portlet/calendar', null, null, 'My Calendar', '', null, null, '2015-11-23 11:42:26', '2015-11-23 11:42:26', '2015-11-23 11:42:26', '2015-11-23 11:42:26');
INSERT INTO `portal_portlet` VALUES ('CONTACT_PORTLET', 'Contact', 'Contact', '/party/dyn/portal/partycontact', null, null, 'contact methods of a party', '', null, null, '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');
INSERT INTO `portal_portlet` VALUES ('PARTY_PORTLET', 'Party Info', 'Party', '/party/dyn/portal/partyinfo', null, null, 'General information about a person or party group', '', null, null, '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');
INSERT INTO `portal_portlet` VALUES ('USERLOGIN_PORTLET', 'User Login', 'UserLogin', '/party/dyn/portal/partyuserlogin', null, null, 'User Logins of a party', '', null, null, '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');

INSERT INTO `portlet_portlet_category` VALUES ('CALENDAR_PORTLET', 'PARTY_PROFILE', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');
INSERT INTO `portlet_portlet_category` VALUES ('CONTACT_PORTLET', 'PARTY_PROFILE', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');
INSERT INTO `portlet_portlet_category` VALUES ('PARTY_PORTLET', 'PARTY_PROFILE', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');
INSERT INTO `portlet_portlet_category` VALUES ('USERLOGIN_PORTLET', 'PARTY_PROFILE', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');


INSERT INTO `portal_page` VALUES ('PARTYPROFILE_PAGE', 'Parties', '会员详情', '_NA_', null, null, '0', null, '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', null);
INSERT INTO `portal_page_column` VALUES ('PARTYPROFILE_PAGE', '00001', null, '6', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');
INSERT INTO `portal_page_column` VALUES ('PARTYPROFILE_PAGE', '00002', null, '6', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');

INSERT INTO `portal_page_portlet` VALUES ('PARTYPROFILE_PAGE', 'CALENDAR_PORTLET', '00001', '00001', '3', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');
INSERT INTO `portal_page_portlet` VALUES ('PARTYPROFILE_PAGE', 'CONTACT_PORTLET', '00001', '00001', '3', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');
INSERT INTO `portal_page_portlet` VALUES ('PARTYPROFILE_PAGE', 'PARTY_PORTLET', '00001', '00001', '0', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');
INSERT INTO `portal_page_portlet` VALUES ('PARTYPROFILE_PAGE', 'USERLOGIN_PORTLET', '00001', '00002', '1', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15', '2015-11-23 11:42:15');
