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

INSERT INTO `security_group` VALUES ('FULLADMIN', '超级管理员', '系统最高管理权限，具有系统所有权限', 'Y', '2015-01-29 16:09:23', '2015-01-29 16:09:23', '2015-01-29 16:09:23', '2015-01-29 16:09:23');
INSERT INTO `security_group` VALUES ('FULLADMINREADONLY', '只读超级管理员', '可以查看系统的所有内容，但不能修改', null, '2015-01-29 16:09:23', '2015-01-29 16:09:23', '2015-01-29 16:09:23', '2015-01-29 16:09:23');
INSERT INTO `user_login_security_group` VALUES ('easyfkadmin', 'FULLADMIN', '2012-01-01 12:00:00', null, '2015-01-29 16:09:30', '2015-01-29 16:09:30', '2015-01-29 16:09:30', '2015-01-29 16:09:30');
