package com.bee.devops.admin.core.common.service.admin;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bee.devops.admin.core.common.dao.ops.*;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseEnv;
import com.bee.devops.admin.core.common.entity.ops.OpsBasePro;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bee.devops.admin.common.page.PageBean;
import com.bee.devops.admin.core.common.dao.admin.AdminResourceDao;
import com.bee.devops.admin.core.common.dao.admin.AdminRoleDao;
import com.bee.devops.admin.core.common.entity.admin.AdminResource;
import com.bee.devops.admin.core.common.entity.common.VueTree;
import com.bee.devops.admin.core.vo.request.VersionNotUsedVo;
import com.github.pagehelper.PageHelper;

@Service
public class AdminResourceService {
	final static Logger log = Logger.getLogger(AdminResourceService.class);
	// 常亮定义
	private static final String APPLICATION = "application";
	private static final String CONFIGURE = "configure";
	private static final String RELEASE = "release";
    private static final String TEMPLATE_PRO_RESOURCE_NAME = "彩票项目管理";//项目模板
    private static final String TEMPLATE_APP_MANAGER_NAME = "应用管理";//应用管理模板
    private static final String TEMPLATE_ENV_MANAGER_NAME = "DEV环境";//环境模板

	@Autowired
	AdminResourceDao adminResourceDao;
	@Autowired
	AdminRoleDao adminRoleDao;
	@Autowired
	OpsVersionAppDao opsVersionAppDao;
	@Autowired
	OpsVersionConfigDao opsVersionConfigDao;
	@Autowired
	OpsVersionAppDepDao opsVersionAppDepDao;
    @Autowired
    OpsBaseProDao opsBaseProDao;
	
	public PageBean<AdminResource> querySysResourceList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<AdminResource> res = adminResourceDao.querySysResourceList();
		return new PageBean<>(res) ;
	}

	public AdminResource getDetail(Long resourceId){
		return adminResourceDao.getDetailByResourceId(resourceId);
	}
	
	public List<AdminResource> getListByParentId(Long parentId){
		return adminResourceDao.getListByParentId(parentId);
	}
	
	public List<VueTree> getTree(Long parentId){
		List<VueTree> lt= new ArrayList<>();
		List<AdminResource> temp = getListByParentId(parentId);
		for(AdminResource res:temp){
			VueTree tree = new VueTree();
			tree.setResourceId(res.getResourceId());
			tree.setResourceName(res.getResourceName());
			tree.setParentId(res.getParentId());
			tree.setResourceOrder(res.getResourceOrder());
			tree.setIsEnable(res.getIsEnable());
            tree.setIsShow(res.getIsShow());
			tree.setResourceUrl(res.getResourceUrl());
			tree.setResourceType(res.getResourceType());
			tree.setResourceIcon(res.getResourceIcon());
			if(getListByParentId(res.getResourceId())!=null){	
				tree.setChildren(getTree(res.getResourceId()));
			}
			lt.add(tree);	
		}
		return lt;
	}
	
	public List<VueTree> getResource(Long adminUserId,Long parentId) {
        // 获取当前用户所拥有的所有可用资源
		List<Long> resourceIdList = getResourceId(adminUserId);
		// 获取当前用户的父节点
        List<Long> parentIds = recursiveQueryParentIds(resourceIdList);
        resourceIdList.addAll(parentIds);

		List<VueTree> lt= new ArrayList<>();
		List<AdminResource> temp = getListByParentId(parentId);
		for(AdminResource res:temp){
			if(resourceIdList.contains(res.getResourceId())){
				VueTree tree = new VueTree();
                tree.setResourceId(res.getResourceId());
                tree.setResourceName(res.getResourceName());
                tree.setParentId(res.getParentId());
                tree.setResourceOrder(res.getResourceOrder());
                tree.setResourceCode(res.getResourceCode());
                tree.setIsEnable(res.getIsEnable());
                tree.setIsShow(res.getIsShow());
                tree.setResourceUrl(res.getResourceUrl());
                tree.setResourceType(res.getResourceType());
                tree.setResourceIcon(res.getResourceIcon());

				String url = res.getResourceUrl();
				if (StringUtils.contains(url, "/publish/version/")) {
					String query = StringUtils.substringAfter(url, "?");
					String[] arr = StringUtils.split(query, "&");
					long proId = 0L, envId = 0L;
					for (String param : arr) {
						String[] keyValue = StringUtils.split(param, "=");
						for (String token : keyValue) {
							if (StringUtils.contains(token, "pro")) {
								proId = Long.parseLong(keyValue[1]);
							} else if (StringUtils.contains(token, "env")) {
								envId = Long.parseLong(keyValue[1]);
							}
							break;
						}
					}

					// 截取版本类型  /publish/version/release?pro_id=49&env_id=135
					VersionNotUsedVo versionNotUsedVo = new VersionNotUsedVo();
					String type = StringUtils.substring(url, url.lastIndexOf("/") + 1, url.indexOf("?"));
					if (StringUtils.equals(type, APPLICATION)) {
						Integer appVersionNotUsedCount = opsVersionAppDao.getAppVersionNotUsedCount(proId);
						versionNotUsedVo.setNotUsed(appVersionNotUsedCount);
					} else if (StringUtils.equals(type, CONFIGURE)) {
						Integer configVersionNotUsedCount = opsVersionConfigDao.getConfigVersionNotUsedCount(envId, proId);
						versionNotUsedVo.setNotUsed(configVersionNotUsedCount);
					} else if (StringUtils.equals(type, RELEASE)) {
						Integer releaseVersionNotUsedCount = opsVersionAppDepDao.getReleaseVersionNotUsedCount(envId, proId);
						versionNotUsedVo.setNotUsed(releaseVersionNotUsedCount);
					}
					versionNotUsedVo.setUrl(url);
				}

				if(getListByParentId(res.getResourceId())!=null){
					tree.setChildren(getResource(adminUserId,res.getResourceId()));
				}
				lt.add(tree);
			}	
		}
		return lt;
	}
	public List<Long> getResourceId(Long adminUserId){
		return adminResourceDao.getResourceIdList(adminUserId);
	}
	public Integer addResource(AdminResource sysRes) {
		return adminResourceDao.addResource(sysRes);
	}
	
	@Transactional
	public Integer deleteResource(Long resourceId) {
        // 删除资源时同步删除 角色关联信息
		adminRoleDao.deleteRoleResourceByResourceId(resourceId);
		// 删除资源时同步删除 接口关联信息
        adminResourceDao.deleteResourceUri(resourceId);
		return adminResourceDao.deleteResource(resourceId);
	}
	public Integer editResource(AdminResource sysRes) {
		return adminResourceDao.editResource(sysRes);
	}

    public List<Long> getResourceByRole(Long roleId) {
		return adminResourceDao.queryResourceIdsByRole(roleId);
    }

    /**
     * 根据资源列表进行递归查询所有父节点Id，不包含根节点（resourceId=0）
     *
     * @param resourceIds 资源列表
     * @return 所有父节点ID
     */
    private List<Long> recursiveQueryParentIds(List<Long> resourceIds) {
        if (resourceIds == null || resourceIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> allParentIds = new ArrayList<>();
        List<Long> parentIdList = adminResourceDao.queryParentIdList(resourceIds);
        if (parentIdList != null && parentIdList.size() > 0) {
            Iterator<Long> iterator = parentIdList.iterator();
            while (iterator.hasNext()) {
                Long parentId = iterator.next();
                //resourceIds中不包含父节点，判断parentId是否为根节点0，若是则移除该parentId，否则添加parentId到结果中
                if (resourceIds.indexOf(parentId) == -1) {
                    //父节点为根节点0时，移除该父节点或终止递归
                    if (parentId == 0) {
                        if (parentIdList.size() == 1) {
                            return Collections.emptyList();
                        }
                        iterator.remove();
                    } else {
                        //非根节点0 时添加父节点集合中
                        allParentIds.add(parentId);
                    }
                }
            }
            //递归
            List<Long> rootParentIds = recursiveQueryParentIds(parentIdList);

            for (Long parentId : rootParentIds) {
                if (allParentIds.indexOf(parentId) == -1) {
                    allParentIds.add(parentId);
                }
            }
        }
        return allParentIds;
    }

    /**
     * 新增项目资源、应用管理资源
     *
     * @param proName 项目名称
     */
    @Async
    public void addProjectResources(String proName, Long proId) {
        //获取模板 项目资源
        AdminResource proResource = getTemplateByResourceName(TEMPLATE_PRO_RESOURCE_NAME);
        if (proResource == null) {
            log.error("No template resource for the project " + proName);
            return;
        }
        String proResName = generateNewResourceName(proName, "项目管理");
        proResource.setResourceName(proResName);
        proResource.setResourceId(null);

        if (adminResourceDao.addResource(proResource) == 1) {
            Long proResourceId = proResource.getResourceId();
            //获取模板 应用管理资源
            AdminResource appResource = getTemplateByResourceName(TEMPLATE_APP_MANAGER_NAME);
            Long templateAppResourceId = appResource.getResourceId();

            //重置应用管理url(proId替换),parentId,parentName
            String newUrl = generateProNewUrl(appResource.getResourceUrl(), proId);
            appResource.setResourceId(null);
            appResource.setResourceName(proResName);
            appResource.setParentId(proResourceId);
            appResource.setResourceUrl(newUrl);
            if (adminResourceDao.addResource(appResource) == 1) {
                //查询应用管理下的子资源
                List<AdminResource> childResources = adminResourceDao.getListByParentId(templateAppResourceId);
                for (AdminResource resource : childResources) {
                    resource.setResourceId(null);
                    resource.setParentId(appResource.getResourceId());
                }

                adminResourceDao.batchAddResources(childResources);
            }
            //更新项目，添加对应的项目资源ID
            OpsBasePro opsBasePro = new OpsBasePro();
            opsBasePro.setProId(proId);
            opsBasePro.setResourceId(proResourceId);
            opsBaseProDao.updateByPrimaryKeySelective(opsBasePro);
        }
    }

    /**
     * 环境绑定项目时，在该项目资源下新增对应的环境资源及其子资源
     *
     * @param env 环境对象
     * @param pro 项目对象
     */
    @Async
    public void addEnvResources(OpsBaseEnv env, OpsBasePro pro) {
        AdminResource envResource = getTemplateByResourceName(TEMPLATE_ENV_MANAGER_NAME);
        if (envResource == null) {
            log.warn("No template resource for the env " + env.getEnvName());
            return;
        }
        long templateEnvResId = envResource.getResourceId();
        //重置模板环境资源 属性
        Long proResourceId = pro.getResourceId();
        String newEnvResName = generateNewResourceName(env.getEnvName(), "环境");
        String newParentResName = generateNewResourceName(pro.getProName(), "项目管理");
        envResource.setResourceId(null);
        envResource.setResourceName(newEnvResName);
        envResource.setParentId(proResourceId);
//        envResource.setParentName(newParentResName);

        //保存新的环境资源并获取resourceId
        if (addResource(envResource) == 1) {
            List<VueTree> vueTrees = getTree(templateEnvResId);
            if (vueTrees != null && !vueTrees.isEmpty()) {
                addEnvChildResource(vueTrees, templateEnvResId, envResource.getResourceId(), envResource.getResourceName(), pro.getProId(), env.getEnvId());
            }
        }
    }

    /**
     * 添加环境资源下的子资源
     *
     * @param vueTrees         子资源树
     * @param oldParentResId   旧的父资源Id
     * @param newParentResId   新的父资源Id
     * @param newParentResName 新的父资源名称
     * @param proId            项目Id
     * @param envId            环境Id
     */
    private void addEnvChildResource(List<VueTree> vueTrees, long oldParentResId, long newParentResId, String newParentResName, long proId, long envId) {
        List<AdminResource> childrenResources = new ArrayList<>();
        for (VueTree tree : vueTrees) {
            AdminResource resource = new AdminResource();
            resource.setResourceName(tree.getResourceName());
            resource.setResourceType(tree.getResourceType());
            resource.setIsEnable(tree.getIsEnable());
            resource.setResourceOder(tree.getResourceOrder());
            resource.setResourceIcon(tree.getResourceIcon());

            //type=2时表示页面，更新url
            if (tree.getResourceType() == 2) {
                String newUrl = generateEnvNewUrl(tree.getResourceUrl(), proId, envId);
                resource.setResourceUrl(newUrl);
            }

            if (tree.getParentId() == oldParentResId) {
                resource.setParentId(newParentResId);
            }
            //含有子资源时，先增加该资源以获取资源Id，递归子资源树
            List<VueTree> childTree = tree.getChildren();
            if (childTree != null && !childTree.isEmpty()) {
                if (addResource(resource) == 1) {
                    addEnvChildResource(childTree, tree.getResourceId(), resource.getResourceId(), resource.getResourceName(), proId, envId);
                }
            } else {
                childrenResources.add(resource);
            }
        }
        //批量插入子资源(不再包含其他子资源)
        if (!childrenResources.isEmpty()) {
            adminResourceDao.batchAddResources(childrenResources);
        }
    }

    /**
     * 根据模板资源名称获取对应资源模板
     *
     * @param templateResourceName 模板资源名称
     * @return 模板资源对象
     */
    private AdminResource getTemplateByResourceName(String templateResourceName) {
        return adminResourceDao.getTemplateByResourceName(templateResourceName);
    }

    private String generateProNewUrl(String oldUrl, long proId) {
    	String str = "[\\d]";
        Matcher matcher = Pattern.compile(str).matcher(oldUrl);
        return matcher.replaceAll("") + proId;
    }

    private String generateEnvNewUrl(String oldUrl, long proId, long envId) {
        int proIndex = StringUtils.indexOf(oldUrl, "pro_id");
        return oldUrl.substring(0, proIndex) + "pro_id=" + proId + "&env_id=" + envId;
    }

    private String generateNewResourceName(String srcName, String suffix) {
        return srcName.endsWith(suffix) ? srcName : srcName + suffix;
    }

    /**
     * 添加资源和接口的关联
     *
     * @param resourceId   资源id
     * @param interfaceIds 接口id集合
     */
    @Transactional
    public void relWithUri(Long resourceId, List<Long> interfaceIds) {
        adminResourceDao.deleteResourceUri(resourceId);
        if (!interfaceIds.isEmpty()) {
            adminResourceDao.insertResourceUri(resourceId, interfaceIds);
        }
    }
}
