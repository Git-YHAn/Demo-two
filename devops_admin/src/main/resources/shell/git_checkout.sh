#!/bin/bash

GIT_LINK=${GIT_LINK}    #GITLAB仓库地址 http://username:password@192.168.100.110:31000/E00/P00/E00P00M00.git

GIT_DEFAULT_BRANCH="master"   #GITLAB默认拉取得分支

PROJECT_DEPLOY_DIR=${PROJECT_DEPLOY_DIR}     #项目的发布路径

GIT_TAG=${GIT_TAG}    #本次发布的版本 
<#noparse>
if [[ -z "$GIT_LINK" ]]; then
    echo "仓库地址为空"
    exit 1
fi

if [[ -z "$PROJECT_DEPLOY_DIR" ]]; then
    echo "发布路径为空"
    exit 1
fi

if [[ -z "$GIT_TAG" ]]; then
    echo "发布版本为空"
    exit 1
fi

which "git" > /dev/null

if [[ $? -ne 0 ]]; then
	echo command not exist
	yum install -y git	
fi

if [[ ! -d "${PROJECT_DEPLOY_DIR}" ]]; then
    mkdir -pv ${PROJECT_DEPLOY_DIR}
fi
   
cd ${PROJECT_DEPLOY_DIR}	
if [[ ! -d ".git" ]]; then
	git clone ${GIT_LINK} .
else
	git checkout ${GIT_DEFAULT_BRANCH}
    git pull && git fetch --prune origin
fi

git checkout ${GIT_TAG}
echo "Git checkout completed!" > /dev/null 2>&1
exit 0
</#noparse>