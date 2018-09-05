package com.bee.devops.admin.common.enums;

/**
 * @author Roc created on 2018/8/13
 */
public enum DeployStatusEnums {
    /**
     * 发布状态枚举
     */
    DEPLOY_NOT_START("未发布", 0),
    DEPLOY_DIRECTORY_TESTING("目录检测中", 50),
    DEPLOY_DIRECTORY_TEST_FAILURE("目录检测失败", -50),
    DEPLOY_GIT_CHECKOUTING("代码检出中", 100),
    DEPLOY_GIT_CHECKOUT_FAILURE("代码检出失败", -100),
    DEPLOY_VERSION_TESTING("版本检测中", 150),
    DEPLOY_VERSION_TEST_FAILURE("版本检测失败", -150),
    DEPLOY_SERVICE_STARTING("服务启动中", 200),
    DEPLOY_SERVICE_START_FAILURE("服务启动失败", -200),
    DEPLOY_STATUS_TESTING("应用状态检测中", 250),
    DEPLOY_STATUS_TEST_FAILURE("应用状态检测失败", -250),
    DEPLOY_CODE_SUCCESS("代码部署完成", 151),
    DEPLOY_SUCCESS("发布成功", 300),;

    private int value;
    private String description;

    DeployStatusEnums(String description, int value) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static DeployStatusEnums getDeployStatus(int value) {
        DeployStatusEnums result = DEPLOY_NOT_START;
        DeployStatusEnums[] enums = DeployStatusEnums.class.getEnumConstants();
        for (DeployStatusEnums status : enums) {
            if (status.value == value) {
                result = status;
            }
        }
        return result;
    }


}
