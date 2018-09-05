package com.bee.devops.admin.core.common.service.ops;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.ops.OpsBaseEnvDao;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseEnv;
import com.github.pagehelper.PageHelper;


@Service
@Transactional
public class OpsBaseEnvService {
   @Autowired
   OpsBaseEnvDao opsBaseEnvDao;
   
   /**
    * 根据ID进行查找
    * @param id
    * @return
    */
    public OpsBaseEnv getEnvById(Long id) {
        return opsBaseEnvDao.selectByPrimaryKey(id);
    }
    
    /**
     * 查询所有环境信息
     * @return
     */
    public List<OpsBaseEnv> getAllEnvs() {
        return opsBaseEnvDao.selectAll();
    }
    
    /**
     * 修改环境信息
     * @param opsBaseEnv
     * @return
     */
    public int updateEnv(OpsBaseEnv opsBaseEnv) {
        return opsBaseEnvDao.updateByPrimaryKey(opsBaseEnv);
    }
    
    /**
     * 根据ID进行删除
     * @return
     */
    public int deleteEnvById(Long appHisId) {
        return opsBaseEnvDao.deleteByPrimaryKey(appHisId);
    }
    
    /**
     * 添加所有环境信息
     * @return
     */
    public int insertEnv(OpsBaseEnv opsBaseEnv) {
        return opsBaseEnvDao.insert(opsBaseEnv);
    }
    
    /**
     * 通过code查询
     * @return
     */
    public int getEnvByCodeCount(String code) {
        return opsBaseEnvDao.selectByCode(code);
    }
    
    /**
     * 通过priority查询
     * @return
     */
    public int getEnvByPriorityCount(long priority) {
        return opsBaseEnvDao.selectByPriority(priority);
    }

    /**
	 * 分页查询所有的环境
	 * @return
	 */
	public PageBean<OpsBaseEnv> queryEnvsByPageData(int pageOn , int pageSize){
		PageHelper.startPage(pageOn, pageSize);
		List<OpsBaseEnv> lists = opsBaseEnvDao.selectAll();
		return new PageBean<>(lists) ;
	}
	
	/**
	 * 根据名称分页查询
	 * @param pageOn
	 * @param pageSize
	 * @return
	 */
	public PageBean<OpsBaseEnv> queryEnvsByName(int pageOn, int pageSize, String envName) {
		PageHelper.startPage(pageOn, pageSize);
		if(envName != null){
			envName = envName.trim();
		}
		List<OpsBaseEnv> lists = opsBaseEnvDao.selectAllByName(envName);
		return new PageBean<>(lists);
	}

	/**
	 * 查询小于当前优先级的环境
	 * @author Yang Chunhai	 
	 * @param priority
	 * @return
	 */
	public List<OpsBaseEnv> selectEnvByPriority(Integer priority){
		return opsBaseEnvDao.selectEnvByPriority(priority);
	}
	
	/**
	 * 查询应用版本中存在的环境
	 * @author Yang Chunhai	 
	 * @return
	 */
	public List<OpsBaseEnv> selectByAppVer(){
		return opsBaseEnvDao.selectByAppVer();
	}
	
	public int queryPriorityById(Long envId) {
		return opsBaseEnvDao.selectPriorityById(envId);
	}
	
}
