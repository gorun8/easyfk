echo off
echo on
"%JAVA_HOME%\bin\java" -Dfile.encoding=UTF-8 -Xms128M -Xmx512M -XX:MaxPermSize=512m -jar core/start/build/lib/easyfk.jar -installwithxml -readers=seed,demo  
pause