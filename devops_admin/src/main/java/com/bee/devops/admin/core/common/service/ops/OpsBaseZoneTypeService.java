package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.core.common.dao.ops.OpsBaseZoneTypeDao;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseZoneType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OpsBaseZoneTypeService {

    @Autowired
    OpsBaseZoneTypeDao zoneTypeDao;

    public int deleteByPrimaryKey(Long zoneTypeId) {
        return zoneTypeDao.deleteByPrimaryKey(zoneTypeId);
    }

    public int insert(OpsBaseZoneType zoneType) {
        return zoneTypeDao.insert(zoneType);
    }

    public String selectZoneTypeNameById(Long zoneTypeId) {
        return zoneTypeDao.selectZoneTypeNameById(zoneTypeId);
    }

    public int updateByPrimaryKey(OpsBaseZoneType zoneType) {
        return zoneTypeDao.updateByPrimaryKey(zoneType);
    }

    public List<OpsBaseZoneType> getAllZoneTypes() {
        return zoneTypeDao.queryAllZoneTypes();
    }
}
