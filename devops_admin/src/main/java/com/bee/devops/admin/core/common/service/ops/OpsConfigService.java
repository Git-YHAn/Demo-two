package com.bee.devops.admin.core.common.service.ops;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.ops.OpsConfigDao;
import com.bee.devops.admin.core.common.entity.admin.AdminUser;
import com.bee.devops.admin.core.common.entity.ops.OpsConfig;
import com.github.pagehelper.PageHelper;

/**
 * @description OpsConfigService
 * @author TurnerXi
 * @date 2018年6月12日
 */
@Service
@Transactional
public class OpsConfigService {

	@Autowired
	OpsConfigDao opsConfigDao;

	public void addConfigRecord(OpsConfig record) {
		opsConfigDao.addConfigRecord(record);
	}

	public void updateConfigRecordStatus(Long recordId, Integer oldStatus, Integer newStatus) {
		opsConfigDao.updateConfigRecordStatus(recordId, oldStatus, newStatus);
	}
	
	public void updateConfigCommitMessage(Long recordId, String commitMessage) {
		opsConfigDao.updateConfigCommitMessage(recordId, commitMessage);
	}

	public PageBean<OpsConfig> queryConfigRecordsByUser(Long userId, Long proId, Long envId, Long appId, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<OpsConfig> lists = opsConfigDao.queryConfigRecordsByUser(userId, proId, envId, appId);
		return new PageBean<>(lists);
	}

	public PageBean<OpsConfig> queryConfigRecords(Long proId, Long envId, Long appId, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<OpsConfig> lists = opsConfigDao.queryConfigRecords(proId, envId, appId);
		return new PageBean<>(lists);
	}
	
	public PageBean<OpsConfig> queryCommitConfigRecords(Long proId, Long envId, Long appId, Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<OpsConfig> lists = opsConfigDao.queryCommitConfigRecords(proId, envId, appId);
		return new PageBean<>(lists);
	}

	public OpsConfig getConfigRecord(Long recordId) {
		return opsConfigDao.getConfigRecord(recordId);
	}

	public OpsConfig getConfigRecordByAppBranch(Long appEnvId, String branchName) {
		return opsConfigDao.getConfigRecordByAppBranch(appEnvId, branchName);
	}

	public void updateConfigRecord(OpsConfig opsConfig) {
		opsConfigDao.updateConfigRecord(opsConfig);
	}

	public void updateAuditInfo(OpsConfig opsConfig) {
		opsConfigDao.updateAuditInfo(opsConfig);
	}

	public void addOrUpdateOpsConfig(String branchName, Long appEnvId, AdminUser adminUser, String commitId) {
		OpsConfig opsConfig = opsConfigDao.getConfigRecordByAppBranch(appEnvId, branchName);
		if (opsConfig == null) {
			opsConfig = new OpsConfig(appEnvId, branchName, adminUser.getAdminUserId(), new Date(), commitId, adminUser.getUsername());
			opsConfigDao.addConfigRecord(opsConfig);
		} else {
			opsConfig.setEndSha(commitId);
			opsConfig.setCommitDate(new Date());
			opsConfigDao.updateConfigRecord(opsConfig);
		}
	}

	public void deleteRecord(String branchName) {
		opsConfigDao.deleteRecord(branchName);
	}
}
