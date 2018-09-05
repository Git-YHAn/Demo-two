#!/usr/bin/env bash

LAST_SUCCESS_VERSION=$1
GIT_REPO_PATH=$2

cd ${GIT_REPO_PATH} && echo "cd ${GIT_REPO_PATH}"

checkDirExist(){
    info="Test $1/.git exists: "
    result=1
    if [ ! -d $1 ]; then
        info="${info}  Not Git Repo!"
        result=1
    else
        info="${info}  Success!"
        result=0
    fi
    echo "${info}"
    return ${result}
}

checkDirExist
result=$?

if [ ${result} -eq 0 ]; then
    git checkout master
    git pull && git fetch --prune origin

    echo "git checkout ${LAST_SUCCESS_VERSION}"
    git checkout ${LAST_SUCCESS_VERSION}

    echo "git log --graph --decorate --oneline -n 1"
    git log --graph --decorate --oneline -n 1
fi
