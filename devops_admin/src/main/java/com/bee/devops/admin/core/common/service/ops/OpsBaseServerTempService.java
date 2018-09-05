package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseServerTempDao;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServerTemp;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OpsBaseServerTempService {
    @Autowired
    private OpsBaseServerTempDao tempDao;

    public OpsBaseServerTemp getById(long tempId) {
        return tempDao.getById(tempId);
    }

    public void insetServerTemp(OpsBaseServerTemp serverTemp) {
        tempDao.inserTpl(serverTemp);
    }

    public void updateServerTemp(OpsBaseServerTemp serverTemp) {
        tempDao.upDate(serverTemp);
    }

    public void deleteServerTemp(long... ids) {
        for (long id : ids) {
            tempDao.deleteTplById(id);
        }
    }

    public PageBean<OpsBaseServerTemp> selectAll(Integer pageNum, Integer pageSize, String tplName, String tplType) {
        PageHelper.startPage(pageNum, pageSize);
        List<OpsBaseServerTemp> list = tempDao.selectAll(tplName, tplType);
        return new PageBean<>(list);
    }

}
