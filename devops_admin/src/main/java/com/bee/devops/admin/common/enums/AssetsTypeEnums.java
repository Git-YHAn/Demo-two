package com.bee.devops.admin.common.enums;

/**
 * @author Roc created on 2018/8/13
 */
public enum AssetsTypeEnums {
    /**
     * 服务器类型枚举
     */
    PHYSICS("物理机", 0),
    CLOUD("云服务器", 1),
    CONTAINER("容器", 2),;

    private int value;
    private String description;

    AssetsTypeEnums(String description, int value) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
