package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.ops.OpsAssembleAppDao;
import com.bee.devops.admin.core.common.dao.ops.OpsVersionAppDao;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionApp;
import com.bee.devops.admin.core.vo.response.AppEnvProCodeVo;
import com.bee.devops.admin.core.vo.response.ApplicationVersionResponse;
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
public class OpsVersionAppService {

    @Autowired
    OpsVersionAppDao appVersionDao;
    @Autowired
    OpsAssembleAppDao opsAssembleAppDao;

    public AppEnvProCodeVo getCodes(Long envId, Long appId, Long proId) {
        return appVersionDao.getCodes(envId, appId, proId);
    }

    public int addAppVersion(OpsVersionApp opsVersionApp) {
        return appVersionDao.insertSelective(opsVersionApp);
    }

    public List<OpsAssembleApp> selectAppByEnvId(Long envId) {
        return appVersionDao.selectByEnvId(envId);
    }

    public String getMaxVersionCode(Long appId, String todayTime) {
        return appVersionDao.getMaxVersionCode(appId, todayTime);
    }

    public ApplicationVersionResponse queryDataByVersionCode(String versionCode) {
        return appVersionDao.getDataByVersionCode(versionCode);
    }

    public OpsVersionApp queryDataByAppVerId(Long appVerId) {
        return appVersionDao.getDataByAppVerId(appVerId);
    }

    public PageBean<OpsVersionApp> searchAppVersion(Integer pageNum, Integer pageSize, Long envId, Long proId, String appName) {
        PageHelper.startPage(pageNum, pageSize);
        List<OpsVersionApp> lists = appVersionDao.searchAppVersion(envId, proId, appName);
        return new PageBean<>(lists);
    }

    public List<OpsVersionApp> searchAppVersionByid(Long envId, Long proId, Long appId) {
        return appVersionDao.searchAppVersionByid(envId, proId, appId);
    }

    /**
     * 根据应用id获取最新版本号
     *
     * @param appId
     * @return
     */
    public String getNewestVersion(Long appId) {
        return appVersionDao.getNewestVersion(appId);
    }

    /**
     * 将应用版本设置为已经使用
     */
    public void updateAppVersionUsed(OpsVersionApp opsVersionApp) {
        appVersionDao.updateByPrimaryKeySelective(opsVersionApp);
    }

    /**
     * 根据项目id获取应用版本未使用数量
     *
     * @param proId 项目id
     * @return
     */
    public Integer getAppVersionNotUsedCount(Long proId) {
        return appVersionDao.getAppVersionNotUsedCount(proId);
    }

    /**
     * 根据项目id和应用id获取对应的项目及应用编码
     *
     * @param appId
     * @param proId
     * @return
     */
    public AppEnvProCodeVo getProCodeAndAppCode(Long appId, Long proId) {
        return appVersionDao.getProCodeAndAppCode(appId, proId);
    }

    /**
     * 更新应用版本信息
     *
     * @param opsVersionApp
     */
    public void updateAppInfo(OpsVersionApp opsVersionApp) {
        appVersionDao.updateByPrimaryKeySelective(opsVersionApp);
    }

    /**
     * 通过提交记录ID查询应用版本
     * @param headCommitId
     * @return
     */
	public List<OpsVersionApp> queryVersionByCommitId(String headCommitId) {
		return appVersionDao.queryVersionByCommitId(headCommitId);
	}
}
