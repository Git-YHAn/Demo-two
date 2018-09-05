package com.bee.devops.admin.common.enums;

/**
 * @author Roc created on 2018/8/13
 */
public enum ApplicationStatusEnums {
    /**
     * 应用状态枚举
     */
    APPLICATION_STOPPED("应用已停止", -1),
    APPLICATION_RUNNING("应用运行中", 0),
    APPLICATION_DEPLOYING("应用发布中", 1);

    private int value;
    private String description;

    ApplicationStatusEnums(String description, int value) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static ApplicationStatusEnums getDeployStatus(int value) {
        ApplicationStatusEnums result = APPLICATION_STOPPED;
        ApplicationStatusEnums[] enums = ApplicationStatusEnums.class.getEnumConstants();
        for (ApplicationStatusEnums status : enums) {
            if (status.value == value) {
                result = status;
            }
        }
        return result;
    }
}
