#!/bin/sh
#####################################################################
 # Project:Easy Web Framework
 # Description:
 # EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
 # was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
 # foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
 # and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
 # right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
 # Of course, you can customize it or use it as a framework to implement your most challenging business needs.
 # EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
 # http://www.apache.org/licenses/LICENSE-2.0
 # Author:hezhiping   Email:110476592@qq.com
#####################################################################

# set the parent directory as EasyFK Home
EASYFK_HOME="$( cd -P "$( dirname "$0" )" && pwd )"

# console log file
EASYFK_LOG=runtime/logs/console.log

# delete the last log
rm -f $EASYFK_LOG

# VM args
#DEBUG="-Dsun.rmi.server.exceptionTrace=true"
#DEBUG="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8091"
#automatic IP address for linux
#IPADDR=`/sbin/ifconfig eth0 | grep 'inet addr:' | cut -d: -f2 | awk '{ print $1}'`
#RMIIF="-Djava.rmi.server.hostname=$IPADDR"
MEMIF="-Xms128M -Xmx512M -XX:MaxPermSize=512m"
#JMX="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=33333 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"
#MISC="-Duser.language=en"
FENCODING="-Dfile.encoding=UTF-8"
VMARGS="$FENCODING $MEMIF $MISC $JMX $DEBUG $RMIIF"

# Worldpay Config
#VMARGS="-Xbootclasspath/p:baseapps/accounting/lib/cryptix.jar $VMARGS"

# location of java executable
if [ -f "$JAVA_HOME/bin/java" ]; then
  JAVA="$JAVA_HOME/bin/java"
else
  JAVA=java
fi

# Allows to run from Jenkins. See http://wiki.jenkins-ci.org/display/JENKINS/ProcessTreeKiller. Cons: the calling Jenkins job does not terminate if the log is not enabled, pros: this allows to monitor the log in Jenkins
#BUILD_ID=dontKillMe

# start easyfk
#$JAVA $VMARGS  -jar ./core/start/build/lib/easyfk.jar $* >>$EASYFK_LOG 2>>$EASYFK_LOG&
(cd "$EASYFK_HOME" && exec "$JAVA" $VMARGS -jar ./core/start/build/lib/easyfk.jar $* >>$EASYFK_LOG 2>>$EASYFK_LOG& "$@")
