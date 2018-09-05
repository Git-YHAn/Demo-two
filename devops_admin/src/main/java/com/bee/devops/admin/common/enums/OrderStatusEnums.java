package com.bee.devops.admin.common.enums;

/**
 * @author Roc created on 2018/8/13
 */
public enum OrderStatusEnums {
    /**
     * 工单状态枚举
     */
    UNDISPOSED("工单未处理", 0),
    DEPLOYING("工单发布中", 100),
    DEPLOYING_PART_FAILURE("工单发布中部分失败", -100),
    DEPLOY_COMPLETED_ALL_SUCCESS("工单发布结束全部成功", 200),
    DEPLOY_COMPLETED_ALL_FAILURE("工单发布结束全部失败", -200),;

    private int value;
    private String description;

    OrderStatusEnums(String description, int value) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatusEnums getDeployStatus(int value) {
        OrderStatusEnums result = UNDISPOSED;
        OrderStatusEnums[] enums = OrderStatusEnums.class.getEnumConstants();
        for (OrderStatusEnums status : enums) {
            if (status.value == value) {
                result = status;
            }
        }
        return result;
    }
}
