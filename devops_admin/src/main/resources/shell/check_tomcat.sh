#!/bin/bash
WEB_PORT=$1
CURRENT_TIME=$(date +'%F %H:%M:%S')
function check_tomcat(){
  result=$(curl -I -m 10 --connect-timeout 10 -o /dev/null -s -w %{http_code} localhost:${WEB_PORT})
  if [ ${result} -ge 200 ]&&[ ${result} -lt 400 ]; then
      echo "${CURRENT_TIME} Tomcat启动成功, 页面返回码为$result"
      exit 0
  else
      echo "${CURRENT_TIME} Tomcat页面出错, 页面返回码为$result"
      exit 1
  fi
}

function check_nginx(){
    pid=$(pgrep -o nginx)
    if [ ! -z ${pid} ]; then
        echo "${CURRENT_TIME} nginx is running with pid $pid "
        exit 0
    else
        echo "${CURRENT_TIME} nginx is stopped!"
        exit 1
    fi
}

if [ ! -z $1 ];then
    check_tomcat
else
    check_nginx
fi
