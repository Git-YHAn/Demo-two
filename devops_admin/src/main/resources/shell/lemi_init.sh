#!/bin/bash
# $1 为服务名:[api,agent,admin,data,dist,login,mshop,pay,shop,web,message,taskserver]
APP_NAME=$1

#check and install wget
function checkWget(){
    which "wget" > /dev/null
    if [[ $? -ne 0 ]]; then
        cd /tmp/
        yum install -y wget
    fi
}

#check and install jdk1.7
function checkJdk(){
    if [ ! -d "/usr/local/jdk1.7" ]; then
        cd /tmp/
		if [ -f /tmp/jdk1.7.tar.gz ];then
				echo "Delete the old /tmp/jdk1.7.tar.gz"
				rm -rf /tmp/jdk1.7.tar.gz
			fi
        wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/jdk1.7.tar.gz
        tar zxvf /tmp/jdk1.7.tar.gz  -C /usr/local/

        con1="export JAVA_HOME=/usr/local/jdk1.7"
        con2="export CLASSPATH=\$JAVA_HOME/lib:\$JAVA_HOME/jre/lib"
        con3="export PATH=$PATH:\$JAVA_HOME/bin:\$JAVA_HOME/jre/bin"

        echo $con1 >> /etc/profile
        echo $con2 >> /etc/profile
        echo $con3 >> /etc/profile
        source  /etc/profile
    fi
}

function juadge(){
    # 应用名称为message 或名称以task开头的应用仅创建目录
	if [ ${APP_NAME} == "message" ]||[[ ${APP_NAME} =~ ^task.* ]];then
	    echo "mkdir -p /usr/local/app_${APP_NAME}"
	    mkdir -p /usr/local/app_${APP_NAME}
	# tomcat应用下载 tomcat_${APP_NAME}.tar.gz 并解压
	elif [ ! -d "/usr/local/tomcat_${APP_NAME}" ]; then
        echo "download /usr/local/tomcat_${APP_NAME}"
        cd /tmp/
	    if [ -f /tmp/tomcat_${APP_NAME}.tar.gz ];then
		    echo "Delete the old /tmp/tomcat_${APP_NAME}.tar.gz"
		    rm -rf /tmp/tomcat_${APP_NAME}.tar.gz
	    fi
        wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/tomcat_package/tomcat_${APP_NAME}.tar.gz
        wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/tomcat_exec_script/tomcat
        tar zxvf /tmp/tomcat_${APP_NAME}.tar.gz  -C /usr/local/
	fi
}

#check and download /usr/local/sbin/crononlog
check_cronolog(){
    file_name="cronolog"
    cronolog_path="/usr/local/sbin/${file_name}"

    if [ ! -f ${cronolog_path} ]; then
        echo "Download the file: ${cronolog_path}"
        cd /tmp/
        wget --ftp-user=virtualuser --ftp-password=12345678 ftp://192.168.100.2/init/cronolog
        mv /tmp/${file_name} ${cronolog_path}
        chmod 755 ${cronolog_path}
    fi
}

echo "=========================== Start  Initialing ${APP_NAME} ==========================="
checkWget
checkJdk
juadge
check_cronolog

mkdir -p /data/logs/
echo "=========================== Finish Initialing ${APP_NAME} ==========================="
exit 0