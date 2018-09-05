#!/bin/bash

APP_MAINCLASS=org.lot.message.main.ServerStart
APP_LOG_NAME=lmout.log
psid=0
checkpid() {
   javaps=`$JAVA_HOME/bin/jps -v | grep $APP_LOG_NAME`
   
   if [ -n "$javaps" ]; then      
      psid=`echo $javaps | awk '{print $1}'`    
   else      
      psid=0    
   fi
}   

stop() {    
  checkpid      
  
  if [ $psid -ne 0 ]; then      
      echo -n "Stopping $APP_MAINCLASS ...(pid=$psid) "      
      su - $RUNNING_USER -c "kill $psid"      
      if [ $? -eq 0 ]; then         
         echo "[OK]"      
      else         
         echo "[Failed]"      
	 fi        
	 
	 checkpid       
	 if [ $psid -ne 0 ]; then         
	    stop       
	 fi   
  else      
    echo "===================="      
    echo "warn: $APP_MAINCLASS is not running"      
    echo "===================="   
  fi
} 

stop

