package com.bee.devops.admin.core.common.service.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.prefs.BackingStoreException;

import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.MergeResult.MergeStatus;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Component;

import com.bee.devops.admin.component.git.Repo;
import com.bee.devops.admin.component.git.RepoFactory;
import com.bee.devops.admin.component.git.exceptions.CancelledAuthorizationException;
import com.bee.devops.admin.component.git.exceptions.MissingRepoException;
import com.bee.devops.admin.component.git.exceptions.PushToAheadRemoteError;
import com.bee.devops.admin.component.git.exceptions.TagNameExistsException;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.vo.response.CodeVo;

@Component
public class ConfigVersionControlService {

	/**
	 * 查询本地仓库文件列表
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param branch
	 * @return
	 */
	public List<Path> queryRepoFiles(OpsAssembleApp opsAssembleApp, CodeVo code, String branch) {
		checkAssembleApp(opsAssembleApp);
		String remoteUrl = opsAssembleApp.getConfigGitUrl();
		String repoPath = getConfigFilePath(code.getProCode(), code.getEnvCode(), code.getAppCode());
		try {
			Repo repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
			repo.pull();
			return repo.queryRepoFiles(branch);
		} catch (Exception e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]查询配置文件错误：" + e.getMessage());
		}
	}

	/**
	 * 查询所有本地分支
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @return
	 */
	public List<String> queryLocalBranches(OpsAssembleApp opsAssembleApp, CodeVo code) {
		checkAssembleApp(opsAssembleApp);
		String remoteUrl = opsAssembleApp.getConfigGitUrl();
		String repoPath = getConfigFilePath(code.getProCode(), code.getEnvCode(), code.getAppCode());
		try {
			Repo repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
			return repo.listLocalBranches();
		} catch (Exception e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]查询本地分支错误：" + e.getMessage());
		}
	}

	/**
	 * 从主分支新建本地分支
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param branchName
	 * @return
	 */
	public String createLocalBranchFromMaster(OpsAssembleApp opsAssembleApp, CodeVo code, String branchName) {
		checkAssembleApp(opsAssembleApp);
		String remoteUrl = opsAssembleApp.getConfigGitUrl();
		String repoPath = getConfigFilePath(code.getProCode(), code.getEnvCode(), code.getAppCode());
		try {
			Repo repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
			// 拉取分支
			repo.pull();
			return repo.createNewLocalBranch(branchName);
		} catch (Exception e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]新建本地分支错误：" + e.getMessage());
		}
	}

	/**
	 * 删除本地分支
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param branchName
	 */
	public void dropLocalBranch(OpsAssembleApp opsAssembleApp, CodeVo code, String branchName) {
		checkAssembleApp(opsAssembleApp);
		String remoteUrl = opsAssembleApp.getConfigGitUrl();
		String repoPath = getConfigFilePath(code.getProCode(), code.getEnvCode(), code.getAppCode());
		try {
			Repo repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
			repo.dropLocalBranch(branchName);
		} catch (Exception e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]删除本地分支错误：" + e.getMessage());
		}
	}

	/**
	 * 删除远程分支
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param branchName
	 * @return
	 */
	public boolean dropRemoteBranch(OpsAssembleApp opsAssembleApp, CodeVo code, String branchName) {
		checkAssembleApp(opsAssembleApp);
		String remoteUrl = opsAssembleApp.getConfigGitUrl();
		String repoPath = getConfigFilePath(code.getProCode(), code.getEnvCode(), code.getAppCode());
		try {
			Repo repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
			return repo.dropRemoteBranch(branchName);
		} catch (Exception e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]删除远程分支错误：" + e.getMessage());
		}
	}

	/**
	 * 推送本地分支
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param branchName
	 */
	public void pushLocalBranch(OpsAssembleApp opsAssembleApp, CodeVo code, String branchName) {
		checkAssembleApp(opsAssembleApp);
		String remoteUrl = opsAssembleApp.getConfigGitUrl();
		String repoPath = getConfigFilePath(code.getProCode(), code.getEnvCode(), code.getAppCode());
		try {
			Repo repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
			repo.pushLocalBranch(branchName);
		} catch (Exception e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]查询推送本地分支错误：" + e.getMessage());
		}
	}

	/**
	 * 获取文件内容
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param path
	 * @param branchName
	 * @return
	 * @throws IOException
	 */
	public String getRepoFileContent(OpsAssembleApp opsAssembleApp, CodeVo code, String path, String branchName) throws IOException {
		checkAssembleApp(opsAssembleApp);
		String remoteUrl = opsAssembleApp.getConfigGitUrl();
		String repoPath = getConfigFilePath(code.getProCode(), code.getEnvCode(), code.getAppCode());
		try {
			Repo repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
			if (!StringUtils.isBlank(branchName)) {
				repo.checkoutBranch(branchName);
			}
		} catch (Exception e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]检出本地分支[" + branchName + "]错误：" + e.getMessage());
		}
		File file = new File(path);
		return Base64.encodeBase64String(Files.readAllBytes(file.toPath()));
	}

	/**
	 * 本地库新增文件
	 * 
	 * @param opsAssembleApp
	 * @param codeVo
	 * @param filePath
	 * @param fileContent
	 * @return
	 */
	public String addRepoFile(OpsAssembleApp opsAssembleApp, CodeVo code, String adminUserName, String branchName, String fileName, String fileContent) {
		checkAssembleApp(opsAssembleApp);
		Repo repo = checkoutLocalBranch(opsAssembleApp, code, branchName);

		Path filePath = repo.getRepoHelper().getLocalPath().resolve(fileName);
		if (filePath.toFile().exists()) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]新增配置文件[" + fileName + "]错误：已存在配置文件！");
		}
		try {
			Files.write(filePath, new String(Base64.decodeBase64(fileContent)).getBytes(), StandardOpenOption.CREATE_NEW);
			repo.getRepoHelper().addFilePathTest(filePath);
			String diffString = getDiffString(repo);
			if (StringUtils.isNotBlank(diffString)) {
				repo.getRepoHelper().commit(adminUserName + " " + diffString);
			} else {
				throw new ApplicationContextException("没有变更的文件！");
			}
			return repo.getCurrentRepoHeadCommitId();
		} catch (IOException | GitAPIException | MissingRepoException e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]新增配置文件[" + fileName + "]错误：" + e.getMessage());
		}
	}

	/**
	 * 本地仓库删除文件
	 * 
	 * @param opsAssembleApp
	 * @param codeVo
	 * @param username
	 * @param branchName
	 * @param
	 * @return
	 */
	public String deleteRepoFile(OpsAssembleApp opsAssembleApp, CodeVo code, String username, String branchName, String fileName) {
		checkAssembleApp(opsAssembleApp);
		Repo repo = checkoutLocalBranch(opsAssembleApp, code, branchName);
		Path filePath = Paths.get(fileName);
		if (!filePath.toFile().exists()) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]删除配置文件[" + fileName + "]错误：配置文件不存在！");
		}
		try {
			repo.getRepoHelper().removeFilePath(filePath);
			String diffString = getDiffString(repo);
			if (StringUtils.isNotBlank(diffString)) {
				repo.getRepoHelper().commit(username + " " + diffString);
			} else {
				throw new ApplicationContextException("没有变更的文件！");
			}
			return repo.getCurrentRepoHeadCommitId();
		} catch (IOException | GitAPIException | MissingRepoException e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]删除配置文件[" + fileName + "]错误：" + e.getMessage());
		}
	}

	/**
	 * 本地仓库修改配置文件
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param adminUserName
	 * @param branchName
	 * @param fileName
	 * @param fileContent
	 * @return
	 */
	public String modifyRepoFile(OpsAssembleApp opsAssembleApp, CodeVo code, String adminUserName, String branchName, String fileName, String fileContent) {
		checkAssembleApp(opsAssembleApp);
		Repo repo = checkoutLocalBranch(opsAssembleApp, code, branchName);
		Path filePath = repo.getRepoHelper().getLocalPath().resolve(fileName);
		if (!filePath.toFile().exists()) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]修改配置文件[" + fileName + "]错误：配置文件不存在！");
		}
		try {
			Files.write(filePath, new String(Base64.decodeBase64(fileContent)).getBytes("utf-8"), StandardOpenOption.TRUNCATE_EXISTING);
			repo.getRepoHelper().addFilePathTest(filePath);
			String diffString = getDiffString(repo);
			if (StringUtils.isNotBlank(diffString)) {
				repo.getRepoHelper().commit(adminUserName + " " + diffString);
			} else {
				throw new ApplicationContextException("没有变更的文件！");
			}
			return repo.getCurrentRepoHeadCommitId();
		} catch (Exception e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]新增配置文件[" + fileName + "]错误：" + e.getMessage());
		}
	}

	/**
	 * 查询分支间文件变动
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param branchName
	 * @return
	 * @throws IOException
	 * @throws GitAPIException
	 */
	public List<DiffEntry> showDiffsWithMainBranch(OpsAssembleApp opsAssembleApp, CodeVo code, String branchName) {
		Repo repo = checkoutLocalBranch(opsAssembleApp, code, null);
		try {
			return repo.showBranchDiffs(branchName, null);
		} catch (IOException | GitAPIException e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]对比分支[" + branchName + "]差异错误：" + e.getMessage());
		}
	}

	/**
	 * 查询分支间文件变动
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param branchName
	 * @param beginCommitId
	 * @param endCommitId
	 * @return
	 * @throws IOException
	 * @throws GitAPIException
	 */
	public List<DiffEntry> showDiffsWithCommits(OpsAssembleApp opsAssembleApp, CodeVo code, String beginCommitId, String endCommitId) {
		Repo repo = checkoutLocalBranch(opsAssembleApp, code, null);
		try {
			return repo.showRepoFileDiffs(beginCommitId, endCommitId, null);
		} catch (IOException | GitAPIException e) {
			throw new ApplicationContextException(
			        "应用[" + opsAssembleApp.getAppName() + "]对比差异[" + beginCommitId + ".." + endCommitId + "]错误：" + e.getMessage());
		}
	}

	/**
	 * 查看差异内容
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param entry
	 * @return
	 * @throws IOException
	 */
	public String showDiffContent(OpsAssembleApp opsAssembleApp, CodeVo code, DiffEntry entry) {
		Repo repo = checkoutLocalBranch(opsAssembleApp, code, null);
		try {
			return repo.getDiffContent(entry);
		} catch (IOException e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]对比差异内容错误：" + e.getMessage());
		}
	}

	/**
	 * 合并分支到主干
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param branchName
	 * @return
	 */
	public boolean mergeBranchToMain(OpsAssembleApp opsAssembleApp, CodeVo code, String branchName) {
		Repo repo = checkoutLocalBranch(opsAssembleApp, code, branchName);
		try {
			MergeStatus mergeStatus = repo.mergeBranch(branchName, OpsSysParameterUtil.getGitBranch());
			if (!mergeStatus.isSuccessful()) {
				throw new ApplicationContextException(mergeStatus.toString());
			}
		} catch (IOException | GitAPIException | MissingRepoException e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]合并分支[" + branchName + "]失败：" + e.getMessage());
		}
		return true;
	}

	/**
	 * 从主分支创建标签
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param tagName
	 * @return
	 */
	public boolean createTagFromMainBranch(OpsAssembleApp opsAssembleApp, CodeVo code, String tagName) {
		Repo repo = checkoutLocalBranch(opsAssembleApp, code, null);
		try {
			return repo.createTageFromMainBranch(tagName);
		} catch (IOException | GitAPIException | MissingRepoException | TagNameExistsException | PushToAheadRemoteError e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]创建标签[" + tagName + "]失败：" + e.getMessage());
		}
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
		String remoteUrl = opsAssembleApp.getConfigGitUrl();
		String repoPath = getConfigFilePath(code.getProCode(), code.getEnvCode(), code.getAppCode());
		Repo repo;
		try {
			repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
			repo.pull();
			if (!StringUtils.isBlank(branchName)) {
				repo.checkoutBranch(branchName);
			}
		} catch (Exception e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]检出本地分支[" + branchName + "]错误：" + e.getMessage());
		}
		return repo;
	}

	/**
	 * 检出标签
	 * 
	 * @param opsBaseApp
	 * @param appEnvProCodeVo
	 * @param tagName
	 * @return
	 */
	public Repo checkoutTag(OpsAssembleApp opsAssembleApp, CodeVo code, String tagName) {
		Repo repo = this.checkoutLocalBranch(opsAssembleApp, code, null);
		try {
			repo.pull();
			repo.checkoutTag(tagName);
		} catch (ClassNotFoundException | GitAPIException | IOException | CancelledAuthorizationException | BackingStoreException | MissingRepoException e) {
			throw new ApplicationContextException("应用[" + opsAssembleApp.getAppName() + "]检出标签[" + tagName + "]错误：" + e.getMessage());
		}
		return repo;
	}

	private String getDiffString(Repo repo) throws GitAPIException, IOException {
		StringBuilder builder = new StringBuilder();
		List<DiffEntry> showStagedDiff = repo.showCurrentRepoStagedFileDiffs();
		for (DiffEntry diffEntry : showStagedDiff) {
			builder.append("[" + diffEntry.getChangeType() + "]");
			if (diffEntry.getChangeType() == ChangeType.DELETE) {
				builder.append(diffEntry.getOldPath());
			} else {
				builder.append(diffEntry.getNewPath());
			}
			builder.append(", ");
		}
		int idx = builder.lastIndexOf(",");
		if (idx > -1) {
			builder.deleteCharAt(idx);
		}
		return builder.toString();
	}

	/**
	 * 获取本地仓库路径
	 * 
	 * @param opsAssembleApp
	 * @param code
	 * @param branch
	 * @return
	 */
	public Path getRepoPath(CodeVo code) {
		String repoPath = getConfigFilePath(code.getProCode(), code.getEnvCode(), code.getAppCode());
		return Paths.get(repoPath);
	}

	/**
	 * 获取配置文件路径
	 * 
	 * @param proCode
	 * @param envCode
	 * @param appCode
	 * @return
	 */
	public String getConfigFilePath(String proCode, String envCode, String appCode) {
		String configVersionHomePath = OpsSysParameterUtil.getStringProperty(OpsSysParameterUtil.CONFIG_VERSION_HOME_PATH);
		return configVersionHomePath + File.separator + proCode + File.separator + envCode + File.separator + appCode;
	}

	/**
	 * 校验集成应用
	 * 
	 * @param opsAssembleApp
	 */
	private void checkAssembleApp(OpsAssembleApp opsAssembleApp) {
		if (opsAssembleApp == null) {
			throw new ApplicationContextException("集成应用不存在");
		}
		String remoteUrl = opsAssembleApp.getConfigGitUrl();
		if (StringUtils.isBlank(remoteUrl)) {
			throw new ApplicationContextException("集成应用[" + opsAssembleApp.getEnvName() + "-" + opsAssembleApp.getAppName() + "]远程仓库地址不存在");
		}
	}

}
