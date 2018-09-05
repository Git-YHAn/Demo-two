package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.ops.OpsAppConfigDao;
import com.bee.devops.admin.core.common.entity.ops.OpsAppConfig;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OpsAppConfigService {

	@Autowired
	OpsAppConfigDao opsAppConfigDao;

	public void addAppConfigRecord(OpsAppConfig record) {
		opsAppConfigDao.addAppConfigRecord(record);
	}

	public void updateAppConfigRecordStatus(Long recordId, Integer oldStatus, Integer newStatus) {
		opsAppConfigDao.updateAppConfigRecordStatus(recordId, oldStatus, newStatus);
	}
	

	/**
	 * 查询当前登陆用户的应用审核记录
	 */
	public PageBean<OpsAppConfig> queryAppConfigRecordsByUser(Long userId, Long proId, Long appId, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<OpsAppConfig> lists = opsAppConfigDao.queryAppConfigRecordsByUser(userId, proId, appId);
		return new PageBean<>(lists);
	}

	/**
	 * 查询待审应用,即为已经提交审核的应用
	 */
	public PageBean<OpsAppConfig> queryCommitAppConfigRecords(Long proId, Long appId, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<OpsAppConfig> lists = opsAppConfigDao.queryCommitAppConfigRecords(proId, appId);
		return new PageBean<>(lists);
	}

	public OpsAppConfig getAppConfigRecord(Long recordId) {
		return opsAppConfigDao.getAppConfigRecord(recordId);
	}

	public void updateAppConfigRecord(OpsAppConfig opsConfig) {
		opsAppConfigDao.updateAppConfigRecord(opsConfig);
	}

	public void updateAuditInfo(OpsAppConfig opsConfig) {
		opsAppConfigDao.updateAuditInfo(opsConfig);
	}

	public List<OpsAppConfig> getRecord(Long proId, Long appId, String branch) {
		return opsAppConfigDao.getRecord(proId, appId, branch);
	}
}
