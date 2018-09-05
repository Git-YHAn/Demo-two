package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.ops.OpsVersionAppDepDao;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionAppDep;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionAppDepHis;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionAppImg;
import com.bee.devops.admin.core.vo.response.DeployAppVersionVo;
import com.bee.devops.admin.core.vo.response.PublishVersionResponse;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yangliang on 2018/03/19.
 */
@Service
@Transactional
public class OpsVersionAppDepService {
    @Autowired
    OpsVersionAppDepDao opsVersionAppDepDao;
    
    public PageBean<OpsVersionAppImg> queryAllImagesByPageData(int pageOn , int pageSize , Long envid, Long proid, Long appid){
		PageHelper.startPage(pageOn, pageSize);
		List<OpsVersionAppImg> lists = opsVersionAppDepDao.queryAllImagesByPageData(envid,proid,appid) ;
		return new PageBean<>(lists) ;
	}
    
    public PageBean<DeployAppVersionVo> searchAppVersion(int pageOn , int pageSize , Long envId, Long proId, String appName){
		PageHelper.startPage(pageOn, pageSize);
		List<DeployAppVersionVo> lists = opsVersionAppDepDao.searchAppVersion(envId,proId,appName) ;
		return new PageBean<>(lists) ;
	}
    
    public int insertSelective(OpsVersionAppDep record){
    	return opsVersionAppDepDao.insertSelective(record);
    }

	public String getMaxDeployVersion(Long appEnvId, String todayTime) {
		return opsVersionAppDepDao.getMaxDeployVersion(appEnvId, todayTime);
	}
	
	public String getMaxDeployVersionHis(Long appEnvId, String todayTime) {
		return opsVersionAppDepDao.getMaxDeployVersionHis(appEnvId, todayTime);
	}
    
    public DeployAppVersionVo queryDataByVersionCode(String versionCode) {
    	return opsVersionAppDepDao.getDataByVersionCode(versionCode);
    }

	/**
	 * 获取最新版本号
	 *
	 * @param appEnvId
	 * @return
	 */
	public String getNewestVersion(Long appEnvId) {
		return opsVersionAppDepDao.getNewestVersion(appEnvId);
	}

	public String getNewestVersionHis(Long appEnvId) {
		return opsVersionAppDepDao.getNewestVersionHis(appEnvId);
	}

	public Long getMaxDeployVersionId(Long appEnvId) {
		return opsVersionAppDepDao.getMaxVersionId(appEnvId);
	}
	
	public OpsVersionAppDep get(Long versionId) {
		return opsVersionAppDepDao.selectByPrimaryKey(versionId);
	}
	
	public List<PublishVersionResponse> queryWaitEnactApps(Long proId, Long envId) {
		return opsVersionAppDepDao.queryWaitEnactApps(proId, envId);
	}

	/**
	 * 根据项目id和环境id获取发布版本未使用数量
	 *
	 * @param proId 项目id
	 * @return
	 */
	public Integer getReleaseVersionNotUsedCount(Long envId, Long proId) {
		return opsVersionAppDepDao.getReleaseVersionNotUsedCount(envId, proId);
	}

	/**
	 * 将发布版本设置为已经使用
	 */
	public void updateReleaseVersionUsed(OpsVersionAppDep opsVersionAppDep) {
		opsVersionAppDepDao.updateByPrimaryKeySelective(opsVersionAppDep);
	}
	
	/**
	 * 制作发布版本
	 * @author Yang Chunhai	 
	 * @param opsVersionAppDep
	 */
	public Long insertDepAppVerHis(OpsVersionAppDep opsVersionAppDep, Long appId) {
		OpsVersionAppDepHis opsVersionAppDepHis = new OpsVersionAppDepHis();
		opsVersionAppDepHis.setAppVersionId(opsVersionAppDep.getAppVersionId());
		opsVersionAppDepHis.setAppEnvId(opsVersionAppDep.getAppEnvId());
		opsVersionAppDepHis.setConfigVersionId(opsVersionAppDep.getConfigVersionId());
		opsVersionAppDepHis.setVersionCode(opsVersionAppDep.getVersionCode());
		opsVersionAppDepHis.setDescription(opsVersionAppDep.getDescription());
		opsVersionAppDepHis.setOperateUserId(opsVersionAppDep.getOperateUserId());
		opsVersionAppDepHis.setProductionStatus(100);
		opsVersionAppDepHis.setStatusMessages("正在制作中");
		opsVersionAppDepHis.setAppId(appId);
		opsVersionAppDepDao.insertDeeAppVerHis(opsVersionAppDepHis);
		return opsVersionAppDepHis.getDepVerHisId();
	}
	
	/**
	 * 修改发布版本制作状态
	 */
	public void updateDeeAppVerHis(Long depVerHisId, boolean sign){
		OpsVersionAppDepHis opsVersionAppDepHis = new OpsVersionAppDepHis();
		if(sign) {
			opsVersionAppDepHis.setDepVerHisId(depVerHisId);
			opsVersionAppDepHis.setProductionStatus(200);
			opsVersionAppDepHis.setStatusMessages("制作成功");
			opsVersionAppDepDao.updateDeeAppVerHis(opsVersionAppDepHis);
		} else {
			opsVersionAppDepHis.setDepVerHisId(depVerHisId);
			opsVersionAppDepHis.setProductionStatus(300);
			opsVersionAppDepHis.setStatusMessages("制作失败");
			opsVersionAppDepDao.updateDeeAppVerHis(opsVersionAppDepHis);
		}
	}
	
	/**
	 * 查询制作中的发布版本
	 */
	public List<OpsVersionAppDepHis> queryMakingVersion(Long proId, Long envId) {
		return opsVersionAppDepDao.queryMakingVersion(proId, envId);
	}
	
	/**
	 * 更改版本制作状态为完成
	 */
	public Integer changeVersionStatus(Long appDepVerHisId) {
		return opsVersionAppDepDao.changeVersionStatus(appDepVerHisId);
	}
	
	/**
	 * 
	 */
	public OpsVersionAppDepHis getVerHisByStatusId(Long appEnvId) {
		return opsVersionAppDepDao.getVerHisByStatusId(appEnvId);
	}
}
