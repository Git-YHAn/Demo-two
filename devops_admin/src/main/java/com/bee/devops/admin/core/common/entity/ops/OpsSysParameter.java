package com.bee.devops.admin.core.common.entity.ops;

import java.util.Date;

/**
 * @author Roc created on 2018/8/2
 */
public class OpsSysParameter {
    private Long sysParameterId;

    private String sysParameterName;

    private String sysParameterValue;

    private Date createTime;

    private Date updateTime;

    public Long getSysParameterId() {
        return sysParameterId;
    }

    public void setSysParameterId(Long sysParameterId) {
        this.sysParameterId = sysParameterId;
    }

    public String getSysParameterName() {
        return sysParameterName;
    }

    public void setSysParameterName(String sysParameterName) {
        this.sysParameterName = sysParameterName;
    }

    public String getSysParameterValue() {
        return sysParameterValue;
    }

    public void setSysParameterValue(String sysParameterValue) {
        this.sysParameterValue = sysParameterValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
