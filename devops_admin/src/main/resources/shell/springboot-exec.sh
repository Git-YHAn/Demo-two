#!/bin/bash
# description: 框架启动脚本
# author: com.bee.msa Framework
# 注册到注册中心的Hostname
eureka_instance_hostname=${INNER_IP}
# 服务启动端口
server_port=${MS_PORT}
# 注册中心地址
eureka_client_service_url_default_zone=${MS_EUREKA_ADDRESS}
# 配置文件
spring_cloud_config_profile=${MS_PROFILE_CODE}
# 注册服务时指定当前服务所属的Zone
eureka_instance_metadata_map_zone=${MS_ZONE_CODE}
# 注册服务时指定当前服务所属的Region
eureka_client_region=${EUREKA_REGION_CODE}
# 配置文件GIT仓库地址，仅microservices-config-center项目时生效
spring_cloud_config_server_git_uri=${CONFIG_URI}
# 日志配置文件
logging_config=${LOGGING_CONFIG}
# 应用名称
app_name=${APP_NAME}
<#noparse>
CURRENT_PATH=$(cd "$(dirname "$0")"; pwd)
PATH_TO_JAR=`find ${CURRENT_PATH} -maxdepth 2 -name "*.jar" -a ! -name "*-sources.jar"`
#PATH_TO_JAR="$CURRENT_PATH/${PATH_TO_JAR:2}"
printf  "扫描Jar包所在位置: $PATH_TO_JAR \n"
#cd `dirname $0`/..
message=""
pid=0
start(){
  checkpid
  
  JAVA_OPTS="$JAVA_OPTS -XX:+UseParNewGC -XX:ParallelGCThreads=4 -XX:MaxTenuringThreshold=9 -XX:+UseConcMarkSweepGC -XX:+DisableExplicitGC -XX:+UseCMSInitiatingOccupancyOnly -XX:+ScavengeBeforeFullGC -XX:+UseCMSCompactAtFullCollection -XX:+CMSParallelRemarkEnabled -XX:CMSFullGCsBeforeCompaction=9 -XX:CMSInitiatingOccupancyFraction=60 -XX:+CMSClassUnloadingEnabled -XX:SoftRefLRUPolicyMSPerMB=0 -XX:+CMSPermGenSweepingEnabled -XX:CMSInitiatingPermOccupancyFraction=70 -XX:+ExplicitGCInvokesConcurrent -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationConcurrentTime -XX:+PrintHeapAtGC -XX:+HeapDumpOnOutOfMemoryError -XX:-OmitStackTraceInFastThrow -Duser.timezone=Asia/Shanghai -Dclient.encoding.override=UTF-8 -Dfile.encoding=UTF-8 -Djava.security.egd=file:/dev/./urandom"
  JAVA_OPTS="$JAVA_OPTS -Xloggc:./heap_trace.txt -XX:HeapDumpPath=./HeapDumpOnOutOfMemoryError/"

  if [ -n "$eureka_instance_hostname" ]; then
    JAVA_OPTS="$JAVA_OPTS -Deureka.instance.hostname=$eureka_instance_hostname"
  fi
  if [ -n "$server_port" ]; then
    JAVA_OPTS="$JAVA_OPTS -Dserver.port=$server_port"
  fi
  if [ -n "$eureka_client_service_url_default_zone" ]; then
    JAVA_OPTS="$JAVA_OPTS -Deureka.client.serviceUrl.defaultZone=$eureka_client_service_url_default_zone"
  fi
  if [ -n "$spring_cloud_config_profile" ]; then
    JAVA_OPTS="$JAVA_OPTS -Dspring.cloud.config.profile=$spring_cloud_config_profile"
  fi
  # 当传入了服务所在域时，将相关信息放到启动参数中
  if [ -n "$eureka_instance_metadata_map_zone" ] && [ -n "$eureka_client_region" ]; then
    JAVA_OPTS="$JAVA_OPTS -Deureka.instance.metadata_map.zone=$eureka_instance_metadata_map_zone"
    JAVA_OPTS="$JAVA_OPTS -Deureka.client.region=$eureka_client_region"
    JAVA_OPTS="$JAVA_OPTS -Deureka.client.availability_zones.$eureka_client_region=$eureka_instance_metadata_map_zone"
  fi
  # 当传入的仓库地址不为空，并且项目是microservices-config-center时，插入git的配置仓库信息到启动参数里面
  if [ -n "$spring_cloud_config_server_git_uri" ] && [[ $PATH_TO_JAR =~ "microservices-config-center" ]]; then
    JAVA_OPTS="$JAVA_OPTS -Dspring.cloud.config.server.git.uri=$spring_cloud_config_server_git_uri"
  fi
  if [ -n "$logging_config" ]; then
    JAVA_OPTS="$JAVA_OPTS -Dlogging.config=$logging_config"
  fi
  echo "##############启动参数#################"
  echo "$JAVA_OPTS"
  echo "##############启动参数#################"
  
  rm -rf /tmp/${app_name}.pid
  touch /tmp/${app_name}.pid
      
  if [ ! -n "$pid" ]; then
    if [[ -f $PATH_TO_JAR ]]; then
      source /etc/profile
      nohup $JAVA_HOME/bin/java -jar $JAVA_OPTS $PATH_TO_JAR > ${CURRENT_PATH}/lg_startup.log 2>&1 &
      rc=$?;
      if [[ $rc != 0 ]]; then
        echo "$(date) 启动 $PATH_TO_JAR 失败, return code: $rc"
        exit $rc;
      fi
      checkpid
      message="$(date) 服务启动命令已执行，请从日志确认启动结果"
      echo "$pid" >/tmp/${app_name}.pid
      echoGreen
    fi
  else
      echo "服务已运行 PID: $pid"   
  fi
}


status(){
  checkpid
  if [ ! -n "$pid" ]; then
    echo "服务未运行"
  else
    echo "服务运行中 PID: $pid"
  fi
}

checkpid(){
  echo "检查服务[$PATH_TO_JAR]状态......"
  pid=`ps -ef | grep $PATH_TO_JAR | grep -v grep | awk '{print $2}'`
  if [ ! -n "$pid" ]; then
    echo "服务[$PATH_TO_JAR]未运行"
  else
    echo "服务[$PATH_TO_JAR]运行中，PID为 $pid"
  fi
  echo "检查服务状态完成"
}

stop(){
  checkpid
  if [ ! -n "$pid" ]; then
    echo "服务未运行"
  else
    kill $pid &> /dev/null || { message="关闭服务失败 [$pid]，一分钟内将尝试继续关闭"; echoRed; return 1; }
    for i in $(seq 1 60); do
      isRunning "$pid" || { message="服务关闭成功 [$pid]"; echoGreen; return 0; }
      [[ $i -eq 30 ]] && kill "$pid" &> /dev/null
      sleep 1
    done
    rm -rf /tmp/${app_name}.pid
  fi 
}

restart(){
    stop 
    sleep 1s
    start
}

# ANSI Colors
echoRed() { echo $'\e[0;31m'"$message"$'\e[0m'; }
echoGreen() { echo $'\e[0;32m'"$message"$'\e[0m'; }
echoYellow() { echo $'\e[0;33m'"$message"$'\e[0m'; }

isRunning() {
  ps -p "$pid" &> /dev/null
}

case $1 in  
  start)   start;;  
  stop)    stop;; 
  restart) restart;;  
  status)  status;;   
  *)       echo "require start|stop|restart|status"  ;;  
esac

exit 0;
</#noparse>