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
EASYFK_HOME="$( cd -P "$( dirname "$0" )" && pwd )"

# location of java executable
if [ -f "$JAVA_HOME/bin/java" ]; then
  JAVA=$JAVA_HOME/bin/java
else
  JAVA=java
fi


(cd "$EASYFK_HOME" && $JAVA -jar ./core/start/build/lib/easyfk.jar -shutdown)

