package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.common.enums.ApplicationStatusEnums;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseEnvServerDao;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseEnvServer;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 服务器操作相关服务
 *
 * @author Administrator
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OpsBaseEnvServerService {
    @Autowired
    private OpsBaseEnvServerDao opsBaseEnvServerDao;

    public List<OpsBaseEnvServer> getRunningAndStoppedApplications() {
        return opsBaseEnvServerDao.queryRunAndStopStatus(ApplicationStatusEnums.APPLICATION_DEPLOYING.getValue());
    }

    public void updateAppStatus(Long hostAppEnvId, int status) {
        opsBaseEnvServerDao.updateAppStatus(hostAppEnvId, status);
    }

    public List<OpsDepAppOrderInfoVo> getEnvServersByAppEnvId(Long appEnvId) {
        return opsBaseEnvServerDao.queryEnvServersByAppEnvId(appEnvId);
    }

    public Integer getApplicationRunningStatus(Long hostAppEnvId) {
        return opsBaseEnvServerDao.getApplicationRunningStatus(hostAppEnvId);
    }
}
