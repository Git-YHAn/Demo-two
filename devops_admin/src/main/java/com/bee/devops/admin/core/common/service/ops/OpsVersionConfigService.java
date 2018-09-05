package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.ops.OpsVersionConfigDao;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionConfig;
import com.bee.devops.admin.core.vo.response.CodeVo;
import com.bee.devops.admin.core.vo.response.ConfigVersionVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yangliang on 2018/04/08.
 */
@Service
@Transactional
public class OpsVersionConfigService {

    @Autowired
    OpsVersionConfigDao opsVersionConfigDao;

    /**
     * 用户查询的方法
     *
     * @param pageOn
     * @param pageSize
     * @param envid
     * @param proid
     * @param appName
     * @return
     */
    public PageBean<ConfigVersionVo> queryConfigVersionByPageData(int pageOn, int pageSize, Long envid, Long proid, String appName) {
        PageHelper.startPage(pageOn, pageSize);
        List<ConfigVersionVo> lists = opsVersionConfigDao.queryConfigVersionByPageData(envid, proid, appName);
        return new PageBean<>(lists);
    }

    public List<ConfigVersionVo> queryConfigVersionByid(Long envid, Long proid, Long appId) {
        List<ConfigVersionVo> lists = opsVersionConfigDao.queryConfigVersionByid(envid, proid, appId);
        return lists;
    }

    /**
     * 获取相关的Code编码
     *
     * @param envId
     * @param proId
     * @param appId
     * @return
     */
    public CodeVo getCode(Long envId, Long appId, Long proId) {
        return opsVersionConfigDao.getCode(envId, appId, proId);
    }

    public int addConfigVersion(OpsVersionConfig opsVersionConfig) {
        return opsVersionConfigDao.insertSelective(opsVersionConfig);
    }

    public String getMaxConfigVersion(Long appEnvId, String todayTime) {
        return opsVersionConfigDao.getMaxConfigVersion(appEnvId, todayTime);
    }

    public ConfigVersionVo queryDataByVersionCode(String versionCode) {
        return opsVersionConfigDao.getDataByVersionCode(versionCode);
    }

    public OpsVersionConfig queryDataByConfigVerId(Long configVerId) {
        return opsVersionConfigDao.getDataByConfigVerId(configVerId);
    }

    /**
     * 获取最新版本号
     *
     * @param appEnvId
     * @return
     */
    public String getNewestVersion(Long appEnvId) {
        return opsVersionConfigDao.getNewestVersion(appEnvId);
    }

    /**
     * 将配置版本设置为已经使用
     */
    public void updateConfigVersionUsed(OpsVersionConfig opsVersionConfig) {
        opsVersionConfigDao.updateByPrimaryKeySelective(opsVersionConfig);
    }

    /**
     * 根据环境id和项目id获取配置版本未使用数量
     *
     *
     * @param envId 环境id
     * @param proId 项目id
     * @return
     */
    public Integer getConfigVersionNotUsedCount(Long envId, Long proId) {
        return opsVersionConfigDao.getConfigVersionNotUsedCount(envId, proId);
    }
}

