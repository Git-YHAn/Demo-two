#!/usr/bin/env bash
GIT_REPO_PATH=$1
TARGET_GIT_TAG=$2

cd ${GIT_REPO_PATH}

result=`git log --oneline --decorate --graph -n 1`
if [ ${#result} = 0 ];then
    echo "Not Found git tag: ${TARGET_GIT_TAG} !"
    exit 1
fi

result=${result##*V_20}
result="V_20${result:0:9}"

if [ "${result}" == "${TARGET_GIT_TAG}" ]; then
    echo "Test Version ${TARGET_GIT_TAG} success!"
    exit 0
else
    echo "Test Version ${TARGET_GIT_TAG} failed and the current version is ${result}"
    exit 1
fi
