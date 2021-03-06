#!/bin/sh
#-------------------------------------------------------------------------------------------------------------
#该脚本的使用方式为-->[sh startup.sh $APP_NAME]
#该脚本可在服务器上的任意目录下执行,不会影响到日志的输出位置等
#$1表示应用名称
#------------------------------------------------------------------------------------------------------------
APP_NAME=$1

JAVA_HOME=`echo ${JAVA_HOME}`
if [ -z "$JAVA_HOME" ];then
    if [ -n "/usr/local/jdk1.7" ]; then
        JAVA_HOME="/usr/local/jdk1.7"
	fi
fi
echo "JAVA_HOME:$JAVA_HOME"

RUNNING_USER=root


APP_LOG=/data/logs/app_${APP_NAME}_log

APP_HOME=/usr/local/app_${APP_NAME}

APP_MAIN=org.lot.taskserver.ServerStart

#APP_LOG_NAME=tk_ticket_out.log
APP_LOG_NAME=tk_ticket_out%Y-%m-%d-%H.log

#JAVA_OPTS="-Duser.timezone=GMT+8 -server -Xms5120m -Xmx10240m -Xloggc:$APP_LOG/$APP_LOG_NAME"
#JAVA_OPTS="-Duser.timezone=GMT+8 -server -Xms64m -Xmx512m -Xloggc:$APP_LOG/$APP_LOG_NAME"
JAVA_OPTS="-Duser.timezone=GMT+8 -server -Xms512m -Xmx1024m -XX:PermSize=512M -XX:MaxPermSize=1024m -Xloggc:$APP_LOG/$APP_LOG_NAME"
CLASSPATH=.:$APP_HOME/classes
for tradePortalJar in "$APP_HOME"/lib/*.jar;
do
   CLASSPATH="$CLASSPATH":"$tradePortalJar"
done

tradePortalPID=0

getTradeProtalPID(){
    javaps=`$JAVA_HOME/bin/jps -v | grep $APP_LOG_NAME`
    if [ -n "$javaps" ]; then
        tradePortalPID=`echo $javaps | awk '{print $1}'`
    else
        tradePortalPID=0
    fi
}

startup(){
    getTradeProtalPID
    echo "===================="
    #echo $PWD
    cd $APP_HOME
    #echo $PWD
    #echo $CLASSPATH
    if [ $tradePortalPID -ne 0 ]; then
        echo "$APP_MAIN already started(PID=$tradePortalPID)"
        echo "===================="
    else
        echo -n "Starting $APP_MAIN"
        #/usr/java/jdk1.6.0_32/bin/java -Djava.ext.dirs=./lib -Xms64m -Xmx1024m -XX:PermSize=64M -XX:MaxPermSize=256m org.lot.message.main.ServerStart
        rm -rf $APP_LOG/$APP_LOG_NAME
		nohup $JAVA_HOME/bin/java $JAVA_OPTS -classpath $CLASSPATH $APP_MAIN  | /usr/local/sbin/cronolog $APP_LOG/$APP_LOG_NAME >/dev/null 2>&1 &
        getTradeProtalPID
        if [ $tradePortalPID -ne 0 ]; then
            echo "(PID=$tradePortalPID)...[Success]"
            echo "===================="
        else
            echo "[Failed]"
            echo "===================="
        fi
    fi
}

startup
exit 0
