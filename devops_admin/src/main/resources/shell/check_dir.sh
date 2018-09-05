#!/bin/bash
APP_NAME=$1
APP_TYPE=$2

JDK_PATH="/usr/local/jdk1.7"
TOMCAT_PATH="/usr/local/tomcat_${APP_NAME}"
LOG_PATH="/data/logs/"
NGINX_PATH="/date/nginx/nginx_static/${APP_NAME}"

checkDirExist(){
    info="Test $1 exists: "
    result=1
    if [ ! -d $1 ]; then
        info="${info}  Not Found!"
        result=1
    else
        info="${info}  Success!"
        result=0
    fi
    echo ${info}
    return ${result}
}

# Micro Service Test JDK1.8 directory exist
if [ ${APP_TYPE} == 300 ]; then
    JDK_PATH=${JDK_PATH/7/8}
    checkDirExist ${JDK_PATH}
    exit $?
else
    checkDirExist ${JDK_PATH}
    result=$?
# WEB Test tomcat directory exist
    if [ ${APP_TYPE} == 200 ]; then
        if [ ${result} -eq 0 ]; then
            checkDirExist ${TOMCAT_PATH}
            result=$?
        fi
# JAVA Test tomcat directory exist
    elif [ ${APP_TYPE} == 100 ]; then
        if [ ${result} -eq 0 ]; then
           checkDirExist ${LOG_PATH}
           result=$?
        fi
        if [[ ${result} -eq 0 && ${APP_NAME} =~ ^task.* ]];then
		   checkDirExist /usr/local/app_${APP_NAME}
           result=$?
	    fi
    fi
fi

# STATIC Test nginx directory exist
if [ ${APP_TYPE} == 400 ]; then
    checkDirExist ${NGINX_PATH}
    result=$?
fi

exit ${result}