echo off
rem #####################################################################
rem Project: gounbiz ecommerce system

rem Description: This project base on so many open source project and thanks to all.
rem  
rem The Goal of this project is to architect a framework for developing web application easily.
rem  
rem 
rem
rem author:hezhiping   ,Email:hzpldx@163.com
rem
rem 
rem 
rem ================================================================================================================
rem 
rem 
rem 
rem #####################################################################

rem ###%~d0
rem ###set EASYFK_HOME=%~p0..\

rem ### Console log file
rem set EASYFK_LOG=runtime\logs\console.log

rem ### Delete the last log
rem del %EASYFK_LOG%

rem ###VM args block ####################################################
rem set MEMIF=-Xms128M -Xmx512M -XX:MaxPermSize=512m
rem # RMI settings
rem set DEBUG=-Dsun.rmi.server.exceptionTrace=true
rem # Automatic IP address for Windows
rem ipconfig | find "IP." | find /v "::" | find /v "0.0.0.0" > tmp.tmp
rem for /f "tokens=2* delims=:" %%a in (tmp.tmp)  do for %%b IN (%%a) do set IPADDR=%%b
rem del tmp.tmp
rem set RMIIF=-Djava.rmi.server.hostname=%IPADDR%
rem # Not needed anymore, for history
rem set MISC=-Duser.language=en
rem set VMARGS=%MEMIF% %MISC% %DEBUG% %RMIIF%
rem ####################################################################

rem ### Worldpay Config
rem set VMARGS=-Xbootclasspath/p:baseapps\accounting\lib\cryptix.jar %VMARGS%


rem ### Different ways of launching EasyFK ##############################
rem ### start easyfk with previous set VMARGS
rem "%JAVA_HOME%\bin\java" %VMARGS% -jar ./core/start/build/lib/easyfk.jar > %EASYFK_LOG%

rem ### This one is for more of a debugging mode
rem "%JAVA_HOME%\bin\java" -Xms128M -Xmx512M -XX:MaxPermSize=512m -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005 -jar ./core/start/build/lib/easyfk.jar > runtime\logs\console.log

rem ### Simple easy to read line
rem ###cd %EASYFK_HOME%
echo on
"%JAVA_HOME%\bin\java" -Dfile.encoding=UTF-8 -Xms128M -Xmx512M -XX:MaxPermSize=512m -jar core/start/build/lib/easyfk.jar
echo off
rem ### If you would prefer the console output to be logged rather than displayed switch out the above line for this one
rem "%JAVA_HOME%\bin\java" -Xms128M -Xmx512M -XX:MaxPermSize=512m -jar ./core/start/build/lib/easyfk.jar > runtime\logs\console.log
 
