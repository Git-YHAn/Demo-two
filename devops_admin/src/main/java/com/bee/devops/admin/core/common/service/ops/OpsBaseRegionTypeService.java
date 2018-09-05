package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.core.common.dao.ops.OpsBaseRegionTypeDao;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseRegionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OpsBaseRegionTypeService {

    @Autowired
    OpsBaseRegionTypeDao regionTypeDao;

    public int deleteByPrimaryKey(Long regionTypeId) {
        return regionTypeDao.deleteByPrimaryKey(regionTypeId);
    }

    public int insert(OpsBaseRegionType regionType) {
        return regionTypeDao.insert(regionType);
    }

    public int updateByPrimaryKey(OpsBaseRegionType regionType) {
        return regionTypeDao.updateByPrimaryKey(regionType);
    }

    public String selectRegionTypeNameById(Long regionTypeId) {
        return regionTypeDao.selectRegionTypeNameById(regionTypeId);
    }

    public List<OpsBaseRegionType> getAllRegionTypes() {
        return regionTypeDao.queryAllRegionTypes();
    }
}
