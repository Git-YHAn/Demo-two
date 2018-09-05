package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.core.common.dao.ops.OpsBaseProfileTypeDao;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseProfileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OpsBaseProfileTypeService {

    @Autowired
    OpsBaseProfileTypeDao profileTypeDao;

    public int insertProfileType(OpsBaseProfileType profileType) {
        return profileTypeDao.insert(profileType);
    }

    public int deleteByPrimaryKey(Long profileTypeId) {
        return profileTypeDao.deleteByPrimaryKey(profileTypeId);
    }

    public int updateByPrimaryKey(OpsBaseProfileType profileType) {
        return profileTypeDao.updateByPrimaryKey(profileType);
    }

    public String selectProfileTypeNameById(Long profileTypeId) {
        return profileTypeDao.selectProfileTypeNameById(profileTypeId);
    }

    public List<OpsBaseProfileType> getAllProfileTypes() {
        return profileTypeDao.queryAllProfileTypes();
    }
}
