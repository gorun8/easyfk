#!/bin/sh
#####################################################################
# Project:Easy Web Framework
#
# Description: This project is based on much more open source projects than ever before,
#              and can be applied to mostly web development environment.
# Author:hezhiping   Email:110476592@qq.com
#
# 
#
# =================================================================================================== 
# 
# 
#####################################################################

# set the parent directory as EasyFK Home
EASYFK_HOME="$( cd -P "$( dirname "$0" )" && pwd )"

# console log file
EASYFK_LOG=runtime/logs/console.log

# delete the last log
rm -f $EASYFK_LOG

# VM args
#DEBUG="-Dsun.rmi.server.exceptionTrace=true"
DEBUG="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8091"
#automatic IP address for linux
#IPADDR=`/sbin/ifconfig eth0 | grep 'inet addr:' | cut -d: -f2 | awk '{ print $1}'`
#RMIIF="-Djava.rmi.server.hostname=$IPADDR"
MEMIF="-Xms128M -Xmx512M -XX:MaxPermSize=512m"
#JMX="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=33333 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"
#MISC="-Duser.language=en"
VMARGS="$MEMIF $MISC $JMX $DEBUG $RMIIF"

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
#$JAVA $VMARGS -jar ./core/start/build/lib/easyfk.jar $* >>$EASYFK_LOG 2>>$EASYFK_LOG&
(cd "$EASYFK_HOME" && exec "$JAVA"   $VMARGS  -jar ./core/start/build/lib/easyfk.jar   "$@")
