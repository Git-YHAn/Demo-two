package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.common.utils.GlobalContainer;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseServerExecHisDao;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServerExecHis;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OpsBaseServerExecHisService {
    @Autowired
    private OpsBaseServerExecHisDao execHisDao;

    @Async
    public Integer insertExecHis(String order, Long assetsId, Long currentTime, Long operateUserId, Long execTplId) {
        String key = assetsId + "_" + currentTime;
        String result = GlobalContainer.MAP.get(key);
        while (StringUtils.isEmpty(result)) {
            result = GlobalContainer.MAP.get(key);
        }
        if (StringUtils.startsWithIgnoreCase(result, "exception")) {
            result = result.replaceFirst("exception:", "失败【") + "】";
        } else {
            result = "成功";
        }

        OpsBaseServerExecHis execHis = new OpsBaseServerExecHis();
        execHis.setAssetsId(assetsId);
        execHis.setExecResult(result);
        execHis.setExecTime(new Date(currentTime));
        execHis.setOperateUserId(operateUserId);
        execHis.setExecTplId(execTplId);
        //使用模板时只记录模板ID
        if (execTplId == null) {
            execHis.setExecContent(order);
        }
        return execHisDao.insertExecHis(execHis);
    }

    public int deleteById(Long execHisId) {
        return execHisDao.deleteExecHisById(execHisId);
    }

    public PageBean<OpsBaseServerExecHis> queryExecHisBySshAndExecTime(Integer pageNum, Integer pageSize, String sshAddress, String tplName, String execTime) {
        PageHelper.startPage(pageNum, pageSize);
        List<OpsBaseServerExecHis> list = execHisDao.selectAll(sshAddress, tplName, execTime);
        return new PageBean<>(list);
    }

}
