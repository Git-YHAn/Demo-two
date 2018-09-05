package com.bee.devops.admin.core.common.service.ops;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.asynctask.AsyncRestartTask;
import com.bee.devops.admin.core.asynctask.AsyncStopTask;
import com.bee.devops.admin.core.common.dao.ops.OpsAssembleAppDao;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.vo.response.AppEnvProCodeVo;
import com.bee.devops.admin.core.vo.response.AppServiceManagerVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@Transactional
public class OpsAssembleAppService {

	@Autowired
	OpsAssembleAppDao opsAssembleAppDao;
	@Autowired
	OpsBaseEnvService opsBaseEnvService;
	@Autowired
	OpsAssembleAppService opsAssembleAppService;
	@Autowired
	OpsBaseServerService opsBaseServerService;
	@Autowired
	AsyncRestartTask asyncRestartTask;
	@Autowired
	AsyncStopTask asyncStopTask;
	/**
	 * 通过appEnvId查询
	 *
	 * @return
	 */
	public OpsAssembleApp getByappEnvId(Long appEnvId) {
		return opsAssembleAppDao.getByappEnvId(appEnvId);
	}

	public OpsAssembleApp getBygiturl(String giturl) {
		return opsAssembleAppDao.getBygiturl(giturl);
	}

	/**
	 * 通过应用ID查询
	 *
	 * @author Yang Chunhai
	 * @param appId
	 * @return
	 */
	public int selectByAppidCount(Long appId) {
		return opsAssembleAppDao.selectByAppid(appId);
	}

	/**
	 * 通过应用ID查询
	 *
	 * @author Yang Chunhai
	 * @param appId
	 * @return
	 */
	public OpsAssembleApp getAppEnvByAppid(Long appId) {
		return opsAssembleAppDao.selectAppEnvByAppid(appId);
	}

	/**
	 * 通过envId和appId查询
	 *
	 * @param envId
	 * @param appId
	 * @return
	 */
	public int getByEnvidAppidCount(Long envId, Long appId) {
		return opsAssembleAppDao.selectByEnvidAppid(envId, appId);
	}

	/**
	 * 通过envId和appId查询id
	 *
	 * @param envId
	 * @param appId
	 * @return
	 */
	public OpsAssembleApp getByEnvIdAppId(Long envId, Long appId) {
		return opsAssembleAppDao.getAppEnv(envId, appId);
	}

	/**
	 * 保存应用环境信息
	 *
	 * @param record
	 * @return
	 */
	public int saveEnvironmentConfig(OpsAssembleApp record) {
		return opsAssembleAppDao.insertSelective(record);
	}

	/**
	 * 查询应用环境中环境id
	 *
	 * @param envId
	 * @return
	 */
	public int selectByEnvid(Long envId) {
		return opsAssembleAppDao.selectByEnvid(envId);
	}

	/**
	 * 根据ID查询
	 *
	 * @author Yang Chunhai
	 * @return
	 */
	public AppServiceManagerVo selectAppEnvById(Long appEnvId) {
		return opsAssembleAppDao.selectByPrimaryKey(appEnvId);
	}

	/**
	 * 条件查询所有应用环境
	 *
	 * @author Yang Chunhai
	 * @param pageOn
	 * @param pageSize
	 * @return
	 */
	public PageBean<AppServiceManagerVo> searchAppEnvs(int pageOn, int pageSize, Long envId, Long proId, String appName) {
		PageHelper.startPage(pageOn, pageSize);
		List<AppServiceManagerVo> lists = opsAssembleAppDao.searchAppEnvs(envId, proId, appName);
		return new PageBean<>(lists);
	}

	/**
	 * 查询所有应用环境
	 * 
	 * @author Yang Chunhai
	 * @param pageOn
	 * @param pageSize
	 * @return
	 */
	public PageBean<OpsAssembleApp> queryAppEnvByPageData(int pageOn, int pageSize) {
		PageHelper.startPage(pageOn, pageSize);
		List<OpsAssembleApp> lists = opsAssembleAppDao.selectAllAppEnvs();
		return new PageBean<>(lists);
	}

	/**
	 * 修改应用环境
	 *
	 * @author Yang Chunhai
	 * @param opsAssembleApp
	 * @return
	 */
	public int updateAppEnv(OpsAssembleApp opsAssembleApp) {
		return opsAssembleAppDao.updateByPrimaryKeySelective(opsAssembleApp);
	}

	/**
	 * 根据发布方式修改应用当前版本
	 *
	 */
	public int updateAppEnvByType(OpsAssembleApp opsAssembleApp, String deployType) {
		return opsAssembleAppDao.updateAppEnvByType(opsAssembleApp.getCurrentVersion(), deployType, opsAssembleApp.getAppEnvId());
	}

	/**
	 * 通过环境id找到所有应用id
	 *
	 * @author Yang Chunhai
	 * @param envId
	 * @return
	 */
	public Long[] selectAppidByEnvid(Long envId) {
		return opsAssembleAppDao.selectAppidByEnvid(envId);
	}

	public List<AppServiceManagerVo> queryAppEnvs(Long envId, Long proId, String appName) {
		return opsAssembleAppDao.searchAppEnvs(envId, proId, appName);
	}

	public AppEnvProCodeVo getCodes(Long appEnvId) {
		return opsAssembleAppDao.getCodes(appEnvId);
	}
	
	public void restartApplication(Long[] serverIds, Long appEnvId, Integer deployType) throws UnsupportedEncodingException{
		if(deployType == 0){
			for (Long serverId : serverIds) {
				boolean result = asyncRestartTask.restartApp(appEnvId, serverId);
				if(!result){
					break;
				}
			}
		}else{
			for (Long serverId : serverIds) {
				asyncRestartTask.asyncRestartApp(appEnvId, serverId);
			}
		}
	}
	public void stopApplication(Long[] serverIds, Long appEnvId, Integer deployType) throws UnsupportedEncodingException{
		if(deployType == 0){
			for (Long serverId : serverIds) {
				asyncStopTask.stopApp(appEnvId, serverId);
			}
		}else{
			for (Long serverId : serverIds) {
				asyncStopTask.asyncStopApp(appEnvId, serverId);
			}
		}
	}
}
