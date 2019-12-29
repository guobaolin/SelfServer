#!/bin/bash

java_bin=$JAVA_HOME/bin/java
server_home=`dirname $0`/..
class_lib=$server_home/lib
log_home=$server_home/logs/example/
log_out=$log_home/stdout.log
log_err=$log_home/stderr.log

if [ ! -d $log_home ]; then
    mkdir -p $log_home
fi

java_opts="-Xmx512m -Dfile.encoding=utf8 -cp :${class_lib}/*"
$java_bin $java_opts com.test.example 1>>$log_out 2>>$log_err &
echo $! > $server_home/example.pid