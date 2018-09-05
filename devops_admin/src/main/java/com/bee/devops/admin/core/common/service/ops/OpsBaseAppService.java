package com.bee.devops.admin.core.common.service.ops;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseAppDao;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseAppType;
import com.github.pagehelper.PageHelper;

/**
 * Created by yangliang on 2018/03/28.
 */
@Service
@Transactional
public class OpsBaseAppService {
   @Autowired
   OpsBaseAppDao deployApplicationDao;
   @Autowired
   OpsConfigService configVersionService;

   /**
    * 通过id查询应用
    * @author Yang Chunhai	 
    * @param appId
    * @return
    */
    public OpsBaseApp getAppById(Long appId) {
        return deployApplicationDao.selectByPrimaryKey(appId);
    }
    
    /**
     * 查询所有应用
     * @author Yang Chunhai	 
     * @return
     */
    public List<OpsBaseApp> getAllApps()
    {
    	return deployApplicationDao.getAllApps();
    }
    
    /**
     * 通过项目id查询应用（查询项目的子应用）
     * @author Yang Chunhai	 
     * @param proId
     * @return
     */
    public List<OpsBaseApp> queryAllAppsByPro(Long proId) {
        return deployApplicationDao.searchAppsByPro(proId, null);
    }
	
    /**
     * 修改应用
     * @author Yang Chunhai	 
     * @param deployApplication
     * @return
     */
    public int updateApp(OpsBaseApp deployApplication) {
        return deployApplicationDao.updateByPrimaryKeySelective(deployApplication);
    }
  
    /**
     * 删除应用
     * @author Yang Chunhai	 
     * @param appId
     * @return
     */
    public int deleteAppById(Long appId) {
        return deployApplicationDao.deleteByPrimaryKey(appId);
    }
    
    /**
     * 添加应用
     * @author Yang Chunhai	 
     * @param deployApplication
     * @return
     */
    public int insertApp(OpsBaseApp deployApplication) {
        return deployApplicationDao.insertSelective(deployApplication);
    }
    
    /**
     * 通过应用编码查应用
     * @author Yang Chunhai	 
     * @param appCode
     * @return
     */
    public int getAppByCode(String appCode){
    	return deployApplicationDao.selectAppByCode(appCode);
    }
    
    public List<OpsBaseApp> selectNotSetApp(Long[] appIds,Long proId){
    	return deployApplicationDao.selectNotSetApp(appIds,proId);
    }
    
    /**
	 * 分页查询所有的应用
	 * @return
	 */
	public PageBean<OpsBaseApp> queryAppsByPageData(int pageOn , int pageSize , String searchName){
		PageHelper.startPage(pageOn, pageSize);
		List<OpsBaseApp> lists = deployApplicationDao.selectSelectiveApps(searchName) ;
		return new PageBean<>(lists) ;
	}
	
	/**
	 * 分页查询某项目的所有应用
	 * @param keyWord 
	 * @return
	 */
	public PageBean<OpsBaseApp> searchAppsByPro(int pageOn , int pageSize , Long proId, String keyWord){
		PageHelper.startPage(pageOn, pageSize);
		List<OpsBaseApp> lists = deployApplicationDao.searchAppsByPro(proId, keyWord) ;
		return new PageBean<>(lists) ;
	}
	
	/**
	 * 查询环境项目下的应用
	 * @author Yang Chunhai	 
	 * @param proId
	 * @return
	 */
	public List<OpsBaseApp> selectEnvInProApp(Long proId,Long envId){
		return deployApplicationDao.selectEnvInProApp(proId,envId);
	}
	
	/**
	 * 查询应用版本下项目中的应用
	 * @author Yang Chunhai	 
	 * @param proId
	 * @return
	 */
	public List<OpsBaseApp> selectAppVerInApp(Long proId){
		return deployApplicationDao.selectAppVerInApp(proId);
	}
	
	public PageBean<OpsBaseApp> queryProAppsByName(int pageOn , int pageSize , OpsBaseApp deployApplication){
		PageHelper.startPage(pageOn, pageSize);
		List<OpsBaseApp> lists = deployApplicationDao.getProAllAppsByName(deployApplication) ;
		return new PageBean<>(lists) ;
	}
	
	public Integer queryAppCount() {
		return deployApplicationDao.queryAppCount();
	}

	public List<OpsBaseAppType> queryAppTypes() {
		return deployApplicationDao.queryAppTypes();
	}

	/**
	 * 检测数据库中是否存在当前的gitUrl路径，若存在返回true，不存在返回false
	 */
	public boolean checkCurrentGitURLExisted(String gitUrl, Long appId) {
		return deployApplicationDao.getExistedGitUrlCount(gitUrl, appId) > 0;
	}

	public OpsBaseApp getAppByGitUrl(String gitUrl) {
		return deployApplicationDao.getAppByGitUrl(gitUrl);
	}
}
