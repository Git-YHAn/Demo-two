package com.bee.devops.admin.core.common.entity.ops;

public class OpsBaseServerTemp {
    private Long tplId;

    private String tplName;

    private String tplType;

    private String tplContent;

    private String tplDescription;

    public Long getTplId() {
        return tplId;
    }

    public void setTplId(Long tplId) {
        this.tplId = tplId;
    }

    public String getTplType() {
        return tplType;
    }

    public void setTplType(String tplType) {
        this.tplType = tplType;
    }

    public String getTplName() {
        return tplName;
    }

    public void setTplName(String tplName) {
        this.tplName = tplName == null ? null : tplName.trim();
    }

    public String getTplContent() {
        return tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent == null ? null : tplContent.trim();
    }

    public String getTplDescription() {
        return tplDescription;
    }

    public void setTplDescription(String tplDescription) {
        this.tplDescription = tplDescription == null ? null : tplDescription.trim();
    }

    @Override
    public String toString() {
        return "OpsBaseServerTemp [tplId=" + tplId + ", tplName=" + tplName + ", tplType=" + tplType
                + ", tplContent=" + tplContent + ", tplDescription=" + tplDescription + "]";
    }

}