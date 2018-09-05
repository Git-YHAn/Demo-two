package com.bee.devops.admin.core.common.dao.ops;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bee.devops.admin.core.common.entity.DeplyHostAppenvRel;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServer;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseServerType;
import com.bee.devops.admin.core.vo.request.DeployHostRequest;
import com.bee.devops.admin.core.vo.response.DeployAssetsHostVo;
import com.bee.devops.admin.core.vo.response.HostAppEnvVo;
import com.bee.devops.admin.core.vo.response.ServerDepAppStatusVo;
import com.bee.devops.admin.core.vo.response.ServerStatusVo;
import com.bee.devops.admin.core.vo.response.ServerUseStatusResponse;
@Mapper
public interface OpsBaseServerDao {
	
	/**
	 * 根据id数组查询
	 * @param assetsId
	 * @return
	 */
	public List<DeployHostRequest> selectHostById(Integer[] assetsId);
	
	/**
	 * 根据id查询数量
	 * @param name
	 * @return
	 */
	public int repetition(String name);
	
	/**
	 * 查询DEPLOY_HOST_APPENV_REL表中是否存在此数据
	 * @param map
	 * @return
	 */
	public int selectCountAppEnv(DeplyHostAppenvRel deplyHostAppenvRel);
	
	/**
	 * 查询总信息数
	 * @return
	 */
	public int selectCount();
	
	/**
	 * 通过名称查询服务器信息
	 * @param assetsHost
	 * @author Yang bin
	 * @return
	 */
	List<DeployAssetsHostVo> selectAllByName(@Param("assetsName")String assetsName);

	/**
	 * 通过SSH地址查询服务器信息
	 * @param sshaddress
	 * @return
	 */
	OpsBaseServer selectByAddress(String sshAddress);
	
	/**
	 * 通过ID查询服务器信息
	 * @param hostId
	 * @return
	 */
	 DeployAssetsHostVo selectById(@Param("assetsId")Long hostId);
	
	/**
	 * 添加服务器信息
	 * @param deployAssetsHost
	 */
	int insertAssetsHost(DeployHostRequest deployHostRequest);
	
	/**
	 * 根据ID删除服务器信息
	 * @param hostId
	 */
	int deleteById(Long hostId);
	
	/**
	 * 根据ID修改服务器信息
	 * @param host
	 */
	int updateById(DeployHostRequest deployHostRequest);
	
	/**
	 * 根据ID查询当前SSH地址
	 * @param hostId
	 * @return
	 */
	String selectSSH(Long hostId);
	
	/**
	 * 查询所有类型
	 * @return
	 */
	List<OpsBaseServerType> showType();
	
	/**
	 * 根据环境ID和应用ID查询服务器
	 * @param envId
	 * @param appId
	 * @return
	 */
	List<DeployAssetsHostVo> selectByAppEnv(@Param("envId")Long envId, @Param("appId")Long appId);
	
	/**
	 * 保存服务器配置信息
	 * @param envId
	 * @param appId
	 * @param hostId
	 * @return
	 */
	int insertAssetsConfig(@Param("appEnvId")Long appEnvId,@Param("hostId")Long hostId);
	
	List<OpsBaseServer> queryServerByType(@Param("type")Integer type);
	/**
	 * 查询当前服务器是否使用
	 * @param hostId
	 * @return
	 */
	int selectAssetsRel(Long hostId);

	/**
	 * 查询未使用的服务器
	 * @return
	 */
	List<OpsBaseServer> selectUnused(Long appEnvId);

	/**
	 * 查询已配置的服务器
	 * @param appEnvId
	 * @return
	 */
	List<Long> selectEmploy(Long appEnvId);
	
	List<OpsBaseServer> selectHostByaddress(String sshAddress);
	
	int selectByAppEnvCount(Long appEnvId);

	public List<OpsBaseServer> selectAll();

	public int queryHostEnv(String envName);

	public int deleteByAppEnv(Long appEnvId);
	
	HostAppEnvVo selectRelation(@Param("hostId")Long hostId,@Param("appEnvId") Long appEnvId);
	
	int updateCurrentVersion(@Param("hostAppEnvId")Long hostAppEnvId,@Param("operateUserId")Long operateUserId, @Param("versionCode")String versionCode);

	OpsBaseServer getById(@Param("hostId")Long hostId);
	
	OpsBaseServer getAccountById(@Param("hostId")Long hostId);
	
	OpsBaseServer selectHostsByAppEnvId(@Param("appEnvId") Long appEnvId);

    /**
     * 获取可初始化的服务器列表
     *
     * @param assetsName
     * @return
     */
    List<DeployAssetsHostVo> initialableHosts(@Param("assetsName") String assetsName, @Param("envName") String envName, @Param("appName") String appName, @Param("proName") String proName);

    /**
     * 更新服务器的初始化状态
     *
     * @param host
     */
    void updateHostInitStatus(OpsBaseServer host);

    int getServerUseInfo(@Param("envId")Long envId, @Param("proId")Long proId);

    ServerStatusVo getServerStatus(@Param("proId")Long proId, @Param("envId")Long envId);

	List<ServerDepAppStatusVo> getServerDepAppStatus(@Param("proId")Long proId, @Param("envId")Long envId);

	ServerUseStatusResponse getServerUseStatus();
}