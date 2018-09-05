package com.bee.devops.admin.core.common.service.ops;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.admin.AdminResourceDao;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseProDao;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseServerDao;
import com.bee.devops.admin.core.common.dao.ops.OpsDepAppOrderInfoDao;
import com.bee.devops.admin.core.common.entity.ops.OpsBasePro;
import com.bee.devops.admin.core.vo.response.AppReleaseInfo;
import com.bee.devops.admin.core.vo.response.DashBoardProDeployAppVo;
import com.github.pagehelper.PageHelper;

/**
 * Created by yangliang on 2018/03/28.
 */
@Service
@Transactional
public class OpsBaseProService {
    @Autowired
    OpsBaseProDao opsBaseProDao;
	@Autowired
	AdminResourceDao adminResourceDao;
	@Autowired
	OpsBaseServerDao opsBaseServerDao;
	@Autowired
	OpsDepAppOrderInfoDao opsDepAppOrderInfoDao;

    
    public OpsBasePro getProjectById(Long proId) {
        return opsBaseProDao.selectByPrimaryKey(proId);
    }
    
    public List<OpsBasePro> getAllProjects() {
        return opsBaseProDao.getAllProjects();
    }
     
    public int updateProject(OpsBasePro opsBasePro) {
        return opsBaseProDao.updateByPrimaryKeySelective(opsBasePro);
    }
   
    public int deleteProjectById(Long proId) {
        return opsBaseProDao.deleteByPrimaryKey(proId);
    }
    
    public int insertProject(OpsBasePro opsBasePro) {
        return opsBaseProDao.insertSelective(opsBasePro);
    }
    
    public int getProjectByCode(String proCode){
    	return opsBaseProDao.selectByProjectCode(proCode);
    }
    
    /**
	 * 分页查询所有的项目
	 * @return
	 */
	public PageBean<OpsBasePro> queryProjectsByPageData(int pageOn , int pageSize , String searchName){
		PageHelper.startPage(pageOn, pageSize);
		List<OpsBasePro> lists = opsBaseProDao.selectSelectiveProjects(searchName) ;
		return new PageBean<>(lists) ;
	}
	
	/**
	 * 通过环境ID查询应用版本中项目
	 * @param envId
	 * @return
	 */
	public List<OpsBasePro> selectProByEnvid(Long envId) {
		return opsBaseProDao.selectProByEnvid(envId);
	}
	
	
	public List<OpsBasePro> selectByEnvid(Long envId){
		return opsBaseProDao.selectByEnvid(envId);
	}

	public Integer queryProjectCount() {
		return opsBaseProDao.queryProjectCount();
	}

	public List<Map<String, Object>> getAppService(Long proId) {
		List<Map<String,Object>> list1 = opsBaseProDao.getEnvNameList(proId);
		for(Map<String,Object> res1 :list1){
			Map<String,Object> param = new HashMap<>();
			param.put("proId", proId);
			param.put("envId", res1.get("envId"));
			List<Map<String,Object>> res2 = opsBaseProDao.getAppService(param);
			res1.put("appServiceInfo", res2);
		}
		return list1;
	}

	public List<Map<String, Object>> getServiceInfo(Long appEvnId) {
		return opsBaseProDao.getServiceInfo(appEvnId);
	}
	
	/**
	 * 通过项目ID获取webHook
	 * @author Yang Chunhai	 
	 * @param proId
	 * @return
	 */
	public String getWebHookByProId(Long proId) {
		return opsBaseProDao.getWebHookByProId(proId);
	}

	/**
	 * 获取面板数据
	 */
	public DashBoardProDeployAppVo getDashboardDate(Long envId, Long proId) {
		DashBoardProDeployAppVo deployAppVo = new DashBoardProDeployAppVo();
		OpsBasePro pro = getProjectById(proId);
		int serverNum = opsBaseServerDao.getServerUseInfo(envId, proId);
		AppReleaseInfo releaseInfo = opsDepAppOrderInfoDao.getReleaseInfo(envId, proId);
		deployAppVo.setProId(pro.getProId());
		deployAppVo.setProName(pro.getProName());
		deployAppVo.setDeployAppInfo(releaseInfo);
		deployAppVo.setServerNum(serverNum);
		return deployAppVo;
	}
}
