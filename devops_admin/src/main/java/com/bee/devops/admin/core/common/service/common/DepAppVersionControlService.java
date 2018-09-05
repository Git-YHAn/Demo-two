package com.bee.devops.admin.core.common.service.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Component;

import com.bee.devops.admin.common.utils.NioFileUtil;
import com.bee.devops.admin.component.git.Repo;
import com.bee.devops.admin.component.git.RepoFactory;
import com.bee.devops.admin.component.git.exceptions.MissingRepoException;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseApp;
import com.bee.devops.admin.core.vo.response.AppEnvProCodeVo;
import com.bee.devops.admin.core.vo.response.CodeVo;

@Component
public class DepAppVersionControlService {

	@Autowired
	AppVersionControlService appVersionControlService;
	@Autowired
	ConfigVersionControlService configVersionControlService;

	/**
	 * 提交本地修改
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param commitMsg
	 * @param repo
	 */
	public void commitCurrentRepoFiles(OpsAssembleApp opsAssembleApp, CodeVo code, String commitMsg, Repo repo) {
		try {
			repo.stageAll();
			if (StringUtils.isNotBlank(commitMsg)) {
				repo.getRepoHelper().commit(commitMsg);
			} else {
				throw new ApplicationContextException("没有提交信息！");
			}
		} catch (Exception e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]本地修改提交失败：" + e.getMessage());
		}
	}

	/**
	 * 重置本地仓库
	 * 
	 * @param opsAssembleApp
	 */
	public void resetCurrentRepo(OpsAssembleApp opsAssembleApp, CodeVo code, Repo repo) {
		try {
			repo.getRepoHelper().reset(repo.getCurrentRepoHeadCommitId(), ResetType.HARD);
		} catch (MissingRepoException | GitAPIException | IOException e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]重置本地仓库失败：" + e.getMessage());
		}
	}

	/**
	 * 获取本地仓库路径
	 * 
	 * @param code
	 * @return
	 */
	public Path getRepoPath(CodeVo code) {
		String repoPath = getDepAppRepoPath(code.getProCode(), code.getEnvCode(), code.getAppCode());
		return Paths.get(repoPath);
	}

	/**
	 * 获取本地应用仓库地址
	 * 
	 * @param proCode
	 * @param envCode
	 * @param appCode
	 * @return
	 */
	public String getDepAppRepoPath(String proCode, String envCode, String appCode) {
		String deployAppHomePath = OpsSysParameterUtil.getStringProperty(OpsSysParameterUtil.DEPLOY_VERSION_HOME_PATH);
		return deployAppHomePath + File.separator + proCode + File.separator + envCode + File.separator + appCode;
	}

	/**
	 * 检出本地分支
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param branchName
	 * @return
	 */
	public Repo checkoutLocalBranch(OpsAssembleApp opsAssembleApp, CodeVo code, String branchName) {
		String remoteUrl = opsAssembleApp.getDeployAppGitUrl();
		String repoPath = getDepAppRepoPath(code.getProCode(), code.getEnvCode(), code.getAppCode());
		Repo repo;
		try {
			repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
			if (!StringUtils.isBlank(branchName)) {
				repo.checkoutBranch(branchName);
			}
		} catch (Exception e) {
			throw new ApplicationContextException(
					"应用[" + opsAssembleApp.getAppName() + "]检出本地分支[" + branchName + "]错误：" + e.getMessage());
		}
		return repo;
	}

	/**
	 * 获取提交文件地址
	 * 
	 * @param directory
	 * @return
	 */
	private ArrayList<Path> getPaths(File directory) {
		ArrayList<Path> paths = new ArrayList<>();
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files != null) {
				for (File fi : files) {
					if (!".git".equals(fi.getName())) {
						paths.add(fi.toPath());
					}
				}
			}
		}
		return paths;
	}

	/**
	 * 当前分支暂存 add
	 */
	public void addLocalBranch(Repo repo, ArrayList<Path> list) {
		try {
			repo.getRepoHelper().addFilePathsTest(list);
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 本地仓库删除文件
     */
    public void deleteRepoFile(Repo repo, ArrayList<Path> list) {
        try {
            repo.getRepoHelper().removeFilePaths(list);
        } catch (Exception e) {
            throw new ApplicationContextException("删除从git拉取的文件错误：" + e.getMessage());
        }
    }

	public void createDepAppVersion(OpsAssembleApp opsAssembleApp, OpsBaseApp opsBaseApp, CodeVo codeVo,
			String appVersionCode, String configVersionCode, String depVersionCode, String commitMsg) throws Exception {
		AppEnvProCodeVo appEnvProCodeVo = new AppEnvProCodeVo(codeVo.getAppCode(), codeVo.getEnvCode(), codeVo.getProCode());
		Repo appRepo = appVersionControlService.checkoutTag(opsBaseApp, appEnvProCodeVo, appVersionCode);
		Repo repo = this.checkoutLocalBranch(opsAssembleApp, codeVo, null);
		Path depPath = repo.getRepoHelper().getLocalPath();
		Path appPath = appRepo.getRepoHelper().getLocalPath();
		Path configPath = null;
		if (configVersionCode != null) {
			Repo configRepo = configVersionControlService.checkoutTag(opsAssembleApp, codeVo, configVersionCode);
			configPath = configRepo.getRepoHelper().getLocalPath();
		}
		try {
			// 清空本地发布版本仓库
			ArrayList<Path> paths = this.getPaths(depPath.toFile());
			this.deleteRepoFile(repo,  paths);
			
			for (File file : appPath.toFile().listFiles()) {
				if (!".git".equals(file.getName())) {
					NioFileUtil.copyFolder(file.getAbsolutePath(), depPath.toString());
				}
			}
			if (configPath != null) {
				for (File file : configPath.toFile().listFiles()) {
					if (!".git".equals(file.getName())) {
						NioFileUtil.copyFolder(file.getAbsolutePath(), depPath.toString());
					}
				}
			}
//			File file = depPath.toFile();
//			ArrayList<Path> paths = this.getPaths(file);
//			addLocalBranch(repo, paths);
			this.commitCurrentRepoFiles(opsAssembleApp, codeVo, commitMsg, repo);
			repo.createTageFromMainBranch(depVersionCode);
			repo.pushAll();
		} catch (Exception e) {
			this.resetCurrentRepo(opsAssembleApp, codeVo, repo);
			throw new ApplicationContextException(
					"应用[" + opsAssembleApp.getAppName() + "]发布版本创建失败, 错误原因：" + e.getMessage());
		}
	}
}
