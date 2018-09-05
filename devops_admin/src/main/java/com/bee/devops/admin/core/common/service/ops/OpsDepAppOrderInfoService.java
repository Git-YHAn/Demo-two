package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.core.common.dao.ops.OpsDepAppOrderInfoDao;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderInfoAppEnvVo;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Roc created on 2018/8/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OpsDepAppOrderInfoService {

    @Autowired
    private OpsDepAppOrderInfoDao opsDepAppOrderInfoDao;

    public OpsDepAppOrderInfoVo searchOrderInfoByAppEnvId(Long appEnvId, Long serverId) {
        return opsDepAppOrderInfoDao.queryOrderInfoByAppEnvAndServerId(appEnvId, serverId);
    }

    public OpsDepAppOrderInfoAppEnvVo getAppEnvAndDepAppVerAndServer(Long orderInfoId) {
        return opsDepAppOrderInfoDao.queryAppEnvAndDepAppVerAndServer(orderInfoId);
    }
}
