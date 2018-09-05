package com.bee.devops.admin.core.vo.response;

import com.bee.devops.admin.core.common.entity.ops.OpsBaseProfileType;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseRegionType;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseZoneType;

import java.util.List;

public class MicroServiceTypeVo {
    private List<OpsBaseProfileType> profileTypes;

    private List<OpsBaseZoneType> zoneTypes;

    private List<OpsBaseRegionType> regionTypes;

    public List<OpsBaseProfileType> getProfileTypes() {
        return profileTypes;
    }

    public void setProfileTypes(List<OpsBaseProfileType> profileTypes) {
        this.profileTypes = profileTypes;
    }

    public List<OpsBaseZoneType> getZoneTypes() {
        return zoneTypes;
    }

    public void setZoneTypes(List<OpsBaseZoneType> zoneTypes) {
        this.zoneTypes = zoneTypes;
    }

    public List<OpsBaseRegionType> getRegionTypes() {
        return regionTypes;
    }

    public void setRegionTypes(List<OpsBaseRegionType> regionTypes) {
        this.regionTypes = regionTypes;
    }
}
