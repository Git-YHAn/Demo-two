package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.ops.OpsDepAppHisDao;
import com.bee.devops.admin.core.vo.response.HistoryVersionVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OpsDepAppHisService {
    @Autowired
    private OpsDepAppHisDao opsDepAppHisDao;

    public PageBean<HistoryVersionVo> queryPublishingAppsByPage(int pageOn, int pageSize, Long envId, Long proId, String appName, Integer publishStatus) {
        PageHelper.startPage(pageOn, pageSize);
        List<HistoryVersionVo> lists = opsDepAppHisDao.queryPublishAppHistory(envId, proId, appName, publishStatus);
        return new PageBean<>(lists);
    }
}
