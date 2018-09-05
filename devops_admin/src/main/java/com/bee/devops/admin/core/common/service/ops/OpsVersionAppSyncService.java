package com.bee.devops.admin.core.common.service.ops;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.ops.OpsVersionAppSyncDao;
import com.bee.devops.admin.core.common.entity.ops.OpsVersionAppSync;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class OpsVersionAppSyncService {

	@Autowired
	OpsVersionAppSyncDao opsVersionAppSyncDao;

	public int addAppVersionSync(OpsVersionAppSync opsVersionAppSync){
		return opsVersionAppSyncDao.insertSelective(opsVersionAppSync);
	}

	public PageBean<OpsVersionAppSync> selectAppVersionSync(int pageOn,int pageSize,Long envId, Long proId, String appName){
		PageHelper.startPage(pageOn, pageSize); 
		List<OpsVersionAppSync> lists = opsVersionAppSyncDao.selectSelective(envId, proId, appName);
		return new PageBean<>(lists);
	}
	
	public OpsVersionAppSync selectByKeyId(Long syncId){
		return opsVersionAppSyncDao.selectByKeyId(syncId);
	}
}
