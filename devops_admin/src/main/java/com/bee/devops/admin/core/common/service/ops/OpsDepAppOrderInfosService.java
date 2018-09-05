package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.core.common.dao.ops.OpsDepAppOrderInfoDao;
import com.bee.devops.admin.core.common.entity.ops.OpsDepAppOrderInfo;
import com.bee.devops.admin.core.vo.response.AppReleaseInfo;
import com.bee.devops.admin.core.vo.response.OpsDepAppOrderInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OpsDepAppOrderInfosService {
    @Autowired
    OpsDepAppOrderInfoDao opsDepAppOrderInfoDao;

    public long insertDepAppOrderInfo(OpsDepAppOrderInfo opsdepapporderinfo) {
        return opsDepAppOrderInfoDao.insertDepAppOrderInfo(opsdepapporderinfo);
    }

    public OpsDepAppOrderInfoResponse getOrderInfo(Long orderInfoId) {
        return opsDepAppOrderInfoDao.queryByOrderInfoId(orderInfoId);
    }

    public List<OpsDepAppOrderInfoResponse> queryOrderInfos(Long[] orderinfos) {
        return opsDepAppOrderInfoDao.queryOrderInfos(orderinfos);
    }

    public AppReleaseInfo getReleaseInfo(Long envId, Long proId) {
        return opsDepAppOrderInfoDao.getReleaseInfo(envId, proId);
    }
}
