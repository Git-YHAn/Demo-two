package com.bee.devops.admin.core.common.service.common;

import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import com.bee.devops.admin.component.git.Repo;
import com.bee.devops.admin.component.git.RepoFactory;
import com.bee.devops.admin.component.git.exceptions.CancelledAuthorizationException;
import com.bee.devops.admin.component.git.exceptions.MissingRepoException;
import com.bee.devops.admin.component.git.exceptions.PushToAheadRemoteError;
import com.bee.devops.admin.component.git.exceptions.TagNameExistsException;
import com.bee.devops.admin.core.common.entity.ops.OpsBaseApp;
import com.bee.devops.admin.core.vo.response.AppEnvProCodeVo;
import com.bee.devops.admin.core.vo.response.CodeVo;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;

@Component
public class AppVersionControlService {
    /**
     * 校验集成应用
     */
    private void checkAssembleApp(OpsBaseApp app) {
        if (app == null) {
            throw new ApplicationContextException("集成应用不存在");
        }
        String remoteUrl = app.getAppGitUrl();
        if (StringUtils.isBlank(remoteUrl)) {
            throw new ApplicationContextException("集成应用[" + app.getAppName() + "]远程仓库地址不存在");
        }
    }

    /**
     * 从主分支新建本地分支
     */
    public String createLocalBranchFromMaster(OpsBaseApp app, AppEnvProCodeVo codeVo, String branchName) {
        checkAssembleApp(app);
        String remoteUrl = app.getAppGitUrl();
        String repoPath = getAppRepoPath(codeVo.getProCode(), codeVo.getEnvCode(), codeVo.getAppCode());
        try {
            Repo repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
            // 拉取分支
            repo.pull();
            return repo.createNewLocalBranch(branchName);
        } catch (Exception e) {
            throw new ApplicationContextException("应用[" + app.getAppName() + "]新建本地分支错误：" + e.getMessage());
        }
    }

    /**
     * 提交本地分支,返回当前分支的最新提交id
     */
    public String commitLocalBranch(Repo repo, String username,String comment) {
        try {
            repo.getRepoHelper().commit(username + " " + comment);
            repo.getRepoHelper().updateModel();
            return repo.getCurrentRepoHeadCommitId();
        } catch (Exception e) {
            throw new ApplicationContextException("提交从zip压缩包解压的文件错误：" + e.getMessage());
        }
    }

    /**
     * 当前分支暂存  add
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

    /**
     * 推送本地分支
     */
    public void pushLocalBranch(Repo repo, String branchName) {
        try {
            repo.pushLocalBranch(branchName);
        } catch (Exception e) {
            throw new ApplicationContextException("推送[" + branchName + "]本地分支错误：" + e.getMessage());
        }
    }

    /**
     * 重载：推送本地分支
     */
    public void pushLocalBranch(OpsBaseApp app, AppEnvProCodeVo codeVo, String branchName) {
        checkAssembleApp(app);
        String remoteUrl = app.getAppGitUrl();
        String repoPath = getAppRepoPath(codeVo.getProCode(), codeVo.getEnvCode(), codeVo.getAppCode());
        try {
            Repo repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
            repo.pushLocalBranch(branchName);
        } catch (Exception e) {
            throw new ApplicationContextException("推送[" + branchName + "]本地分支错误：" + e.getMessage());
        }
    }

    /**
     * 删除本地分支
     */
    public void dropLocalBranch(Repo repo, String branchName) {
        try {
            repo.dropLocalBranch(branchName);
        } catch (Exception e) {
            throw new ApplicationContextException("删除[" + branchName + "]本地分支错误：" + e.getMessage());
        }
    }

    /**
     * 重载：删除本地分支
     */
    public void dropLocalBranch(OpsBaseApp app, AppEnvProCodeVo codeVo, String branchName) {
        checkAssembleApp(app);
        String remoteUrl = app.getAppGitUrl();
        String repoPath = getAppRepoPath(codeVo.getProCode(), codeVo.getEnvCode(), codeVo.getAppCode());
        try {
            Repo repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
            repo.dropLocalBranch(branchName);
        } catch (Exception e) {
            throw new ApplicationContextException("删除[" + branchName + "]本地分支错误：" + e.getMessage());
        }
    }

    /**
     * 合并分支到主分支
     */
    public boolean mergeBranchToMain(OpsBaseApp app, AppEnvProCodeVo codeVo, String branchName) {
        Repo repo = checkoutLocalBranch(app, codeVo, branchName);
        try {
            MergeResult.MergeStatus mergeStatus = repo.mergeBranch(branchName, OpsSysParameterUtil.getGitBranch());
            if (!mergeStatus.isSuccessful()) {
                throw new ApplicationContextException(mergeStatus.toString());
            }
        } catch (IOException | GitAPIException | MissingRepoException e) {
            throw new ApplicationContextException("应用[" + app.getAppName() + "]合并分支[" + branchName + "]失败：" + e.getMessage());
        }
        return true;
    }

    /**
     * 删除远程分支
     */
    public boolean dropRemoteBranch(OpsBaseApp app, AppEnvProCodeVo codeVo, String branchName) {
        checkAssembleApp(app);
        String remoteUrl = app.getAppGitUrl();
        String repoPath = getAppRepoPath(codeVo.getProCode(), codeVo.getEnvCode(), codeVo.getAppCode());
        try {
            Repo repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
            return repo.dropRemoteBranch(branchName);
        } catch (Exception e) {
            throw new ApplicationContextException("应用[" + app.getAppName() + "]删除远程分支错误：" + e.getMessage());
        }
    }

    public List<DiffEntry> showAppVersionFileDiffs(OpsBaseApp app, AppEnvProCodeVo codeVo, String beginCommitId, String endCommitId) {
        Repo repo = checkoutLocalBranch(app, codeVo, null);
        try {
            return repo.showAppVersionFileDiffs(beginCommitId, endCommitId, null);
        } catch (IOException | GitAPIException e) {
            throw new ApplicationContextException("应用[" + app.getAppName() + "]对比差异[" + beginCommitId + ".." + endCommitId + "]错误：" + e.getMessage());
        }
    }

    /**
     * 从主分支创建标签
     *
     * @param opsBaseApp
     * @param appEnvProCodeVo
     * @param tagName
     * @return
     */
    public boolean createTagFromMainBranch(OpsBaseApp opsBaseApp, AppEnvProCodeVo appEnvProCodeVo, String tagName) {
        Repo repo = checkoutLocalBranch(opsBaseApp, appEnvProCodeVo, null);
        try {
            return repo.createTageFromMainBranch(tagName);
        } catch (IOException | GitAPIException | MissingRepoException | TagNameExistsException | PushToAheadRemoteError e) {
            throw new ApplicationContextException("应用[" + opsBaseApp.getAppName() + "]创建标签[" + tagName + "]失败：" + e.getMessage());
        }
    }

    /**
     * 获取最近一次提交信息
     *
     * @param opsBaseApp
     * @param appEnvProCodeVo
     * @return
     */
    public String getHeadCommitMsg(OpsBaseApp opsBaseApp, AppEnvProCodeVo appEnvProCodeVo) {
        Repo repo = checkoutLocalBranch(opsBaseApp, appEnvProCodeVo, null);
        try {
            return repo.getCurrentHeadCommitDesc();
        } catch (Exception e) {
            throw new ApplicationContextException("应用[" + opsBaseApp.getAppName() + "]查询最近提交记录失败：" + e.getMessage());
        }
    }

    /**
     * 查询最新提交ID
     *
     * @param opsBaseApp
     * @param appEnvProCodeVo
     * @return
     */
    public String getHeadCommitId(OpsBaseApp opsBaseApp, AppEnvProCodeVo appEnvProCodeVo) {
        Repo repo = checkoutLocalBranch(opsBaseApp, appEnvProCodeVo, null);
        try {
            // 执行拉取远程操作,然后才获取最新提交id
            repo.pull();
            return repo.getCurrentRepoHeadCommitId();
        } catch (Exception e) {
            throw new ApplicationContextException("应用[" + opsBaseApp.getAppName() + "]查询最近提交ID失败：" + e.getMessage());
        }
    }

    /**
     * 获取本地仓库路径
     *
     * @param code
     * @return
     */
    public Path getRepoPath(CodeVo code) {
        String repoPath = getAppRepoPath(code.getProCode(), code.getEnvCode(), code.getAppCode());
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
    public String getAppRepoPath(String proCode, String envCode, String appCode) {
        String applicationHomePath = OpsSysParameterUtil.getStringProperty(OpsSysParameterUtil.APP_VERSION_HOME_PATH);
        return applicationHomePath + File.separator + proCode + File.separator + envCode + File.separator + appCode;
    }

    /**
     * 检出本地分支
     *
     * @param opsBaseApp
     * @param appEnvProCodeVo
     * @param branchName
     * @return
     */
    public Repo checkoutLocalBranch(OpsBaseApp opsBaseApp, AppEnvProCodeVo appEnvProCodeVo, String branchName) {
        checkAssembleApp(opsBaseApp);
        String remoteUrl = opsBaseApp.getAppGitUrl();
        String repoPath = getAppRepoPath(appEnvProCodeVo.getProCode(), appEnvProCodeVo.getEnvCode(), appEnvProCodeVo.getAppCode());
        Repo repo;
        try {
            repo = RepoFactory.getInstance().get(repoPath, remoteUrl);
            repo.pull();
            if (!StringUtils.isBlank(branchName)) {
                repo.checkoutBranch(branchName);
            }
        } catch (Exception e) {
            throw new ApplicationContextException("应用[" + opsBaseApp.getAppName() + "]检出本地分支[" + branchName + "]错误：" + e.getMessage());
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
    public Repo checkoutTag(OpsBaseApp opsBaseApp, AppEnvProCodeVo appEnvProCodeVo, String tagName) {
        Repo repo = this.checkoutLocalBranch(opsBaseApp, appEnvProCodeVo, null);
        try {
            repo.checkoutTag(tagName);
        } catch (ClassNotFoundException | GitAPIException | IOException | CancelledAuthorizationException | BackingStoreException | MissingRepoException e) {
            throw new ApplicationContextException("应用[" + opsBaseApp.getAppName() + "]检出标签[" + tagName + "]错误：" + e.getMessage());
        }
        return repo;
    }
}
