package com.bee.devops.admin.core.vo.response;

/**
 * @author Roc created on 2018/8/12
 */
public class DashBoardLastDeployAppVo {
    /**
     * 最近一次发布的应用名称
     */
    private String deployedAppName;
    /**
     * 最近一次发布的结果
     */
    private Integer deployedResult;
    /**
     * 最近一次发布的时间
     */
    private String deployedTime;
    /**
     * 最近一次发布的版本
     */
    private String deployVersion;

    public String getDeployedAppName() {
        return deployedAppName;
    }

    public void setDeployedAppName(String deployedAppName) {
        this.deployedAppName = deployedAppName;
    }

    public Integer getDeployedResult() {
        return deployedResult;
    }

    public void setDeployedResult(Integer deployedResult) {
        this.deployedResult = deployedResult;
    }

    public String getDeployedTime() {
        return deployedTime;
    }

    public void setDeployedTime(String deployedTime) {
        this.deployedTime = deployedTime;
    }

    public String getDeployVersion() {
        return deployVersion;
    }

    public void setDeployVersion(String deployVersion) {
        this.deployVersion = deployVersion;
    }
}