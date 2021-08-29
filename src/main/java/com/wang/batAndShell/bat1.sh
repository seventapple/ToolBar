#!/bin/sh 
#remeber encode set UNIX
EXEC_DIR=$(cd `dirname $0`;pwd)
JAVA_HOME=/home/wang/java/jre1.8.0_301

CLASS_PATH=$EXEC_DIR/lib/bat1.jar

$JAVA_HOME/bin/java -classpath ${CLASS_PATH} com.wang.StartUp "$@"