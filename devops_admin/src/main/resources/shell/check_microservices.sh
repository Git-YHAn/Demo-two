#!/bin/bash
MS_PORT=$1

function check(){
  result=$(curl -I -m 10 -o /dev/null -s -w %{http_code} localhost:${MS_PORT}/health)
  if [ ${result} -ge 200 ]&&[ ${result} -lt 400 ]; then
      echo "Micro service is running!"
      exit 0
  else
      echo "Micro service is stopped!"
      exit 1
  fi
}
check
