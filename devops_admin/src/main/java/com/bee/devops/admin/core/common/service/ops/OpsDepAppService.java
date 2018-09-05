package com.bee.devops.admin.core.common.service.ops;

import java.util.Date;
import java.util.List;

import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bee.devops.admin.core.common.dao.ops.OpsDepAppDao;
import com.bee.devops.admin.core.common.dao.ops.OpsDepAppHisDao;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsDepApp;
import com.bee.devops.admin.core.vo.response.AppEnvProCodeVo;
import com.bee.devops.admin.core.vo.response.OpsDepAppResponse;

@Service
public class OpsDepAppService {

	// private final static Logger log = Logger.getLogger(OpsDepAppService.class);
	@Autowired
	OpsDepAppDao opsDepAppDao;
	@Autowired
	OpsDepAppHisDao opsDepAppHisDao;

	public List<OpsDepAppResponse> queryPublishApps(Long proId, Long envId) {
		return opsDepAppDao.queryPublishApps(proId, envId);
	}

	public List<OpsDepApp> queryWaitPublishAppsByCon(Long proId, Long envId) {
		return opsDepAppDao.queryWaitPublishAppsByCon(proId, envId);
	}
	
	public void saveApp(OpsDepApp opsDepApp) {
		opsDepAppDao.save(opsDepApp);
	}

	public OpsDepApp getAppByAppEnv(Long appEnvId, Long serverId) {
		return opsDepAppDao.getAppByAppEnv(appEnvId, serverId);
	}

	public void updateStatus(Integer oldStatus, Integer newStatus, Long publishId) {
		opsDepAppDao.updateStatus(oldStatus, newStatus, publishId);
	}

	public void updateDeployAppVersion(Long versionId, String versionCode, Long publishId) {
		opsDepAppDao.updateDeployAppVersion(versionId, versionCode, publishId);
	}

	public void setPublishDate(Long publishId) {
		opsDepAppDao.updatePublishDate(new Date(), publishId);
	}
	
	public List<OpsDepApp> queryPublishingApps(Long proId, Long envId) {
		return opsDepAppDao.queryPublishingApps(proId, envId);
	}
	
	public List<OpsDepApp> queryPhyPublishingApps(Long proId, Long envId) {
		return opsDepAppDao.queryPhyPublishingApps(proId, envId);
	}
	
	public List<OpsDepApp> queryConPublishingApps(Long proId, Long envId) {
		return opsDepAppDao.queryConPublishingApps(proId, envId);
	}

	public OpsDepApp get(long publishId) {
		return opsDepAppDao.getByPrimaryKey(publishId);
	}

	public String getDeployLogPath(long serverId, AppEnvProCodeVo appEnvProCodeVo, OpsAssembleApp opsAssembleApp) {
		String deployLogPath = OpsSysParameterUtil.getStringProperty(OpsSysParameterUtil.DEPLOY_LOG_PATH);
		return deployLogPath + "/" + appEnvProCodeVo.getEnvCode() + "/" + appEnvProCodeVo.getAppCode() + "/"
				+ opsAssembleApp.getAppName() + "_" + serverId + ".log";
	}

	@Transactional
	public void moveToHistory(Long publishId) {
		OpsDepApp opsDepApp = opsDepAppDao.getByPrimaryKey(publishId);
		opsDepAppDao.delete(publishId);
		if(opsDepApp.getDeployType() == 2) {
			opsDepAppHisDao.save(opsDepApp);
		}
	}

	@Transactional
	public void cancelPublish(Long publishId) {
		OpsDepApp opsDepApp = opsDepAppDao.getByPrimaryKey(publishId);
		if (opsDepApp.getPublishStatus() <= OpsDepApp.PUBLISH_STATUS_DEFAULT) {
			opsDepAppDao.delete(publishId);
			opsDepApp.setPublishStatus(OpsDepApp.PUBLISH_STATUS_PUBLISH_STOP);
			opsDepAppHisDao.save(opsDepApp);
		} else {
			throw new ApplicationContextException("当前状态【" + opsDepApp.getPublishStatus() + "】无法取消发布");
		}
	}

}
