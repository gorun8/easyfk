echo off
rem #####################################################################
rem  # Project:Easy Web Framework
rem  # Description:
rem  # EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
rem  # was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
rem  # foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
rem  # and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
rem  # right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
rem  # Of course, you can customize it or use it as a framework to implement your most challenging business needs.
rem  # EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
rem  # http://www.apache.org/licenses/LICENSE-2.0
rem  # Author:hezhiping   Email:110476592@qq.com
rem #####################################################################
 
 
echo on
"%JAVA_HOME%\bin\java"    -jar core/start/build/lib/easyfk.jar -shutdown
echo off
 
