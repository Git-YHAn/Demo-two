package com.bee.devops.admin.component.git;

import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import com.bee.devops.admin.component.git.exceptions.CancelledAuthorizationException;
import com.bee.devops.admin.component.git.exceptions.MissingRepoException;
import com.bee.devops.admin.component.git.exceptions.PushToAheadRemoteError;
import com.bee.devops.admin.component.git.exceptions.TagNameExistsException;
import com.bee.devops.admin.component.git.helper.*;
import com.bee.devops.admin.component.git.model.BranchModel;
import com.bee.devops.admin.component.git.model.BranchModel.BranchType;
import com.bee.devops.admin.component.git.model.TagModel;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.DiffCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.MergeResult.MergeStatus;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.ResetCommand.ResetType;
import org.eclipse.jgit.api.errors.CannotDeleteCurrentBranchException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidTagNameException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.eclipse.jgit.transport.RemoteRefUpdate.Status;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.springframework.context.ApplicationContextException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;

/**
 * git仓库实体
 *
 * @author TurnerXi
 * @description Repo
 * @date 2018年7月10日
 */
public class Repo {

    public final static String remote = "origin";

    private RepoHelper repoHelper;
    private BranchHelper mainBranchHelper;

    public Repo(RepoHelper repo) throws IOException, GitAPIException, MissingRepoException {
        super();
        repoHelper = repo;
        LocalBranchHelper localBranchHelper = new LocalBranchHelper(OpsSysParameterUtil.getGitBranch(), repoHelper);
        localBranchHelper.checkoutBranch();
        repoHelper.fetch(true);
        BranchModel branchModel = repoHelper.getBranchModel();
        this.mainBranchHelper = branchModel.getBranchByName(BranchModel.BranchType.LOCAL, OpsSysParameterUtil.getGitBranch());
    }

    /**
     * 查询本地仓库文件列表
     *
     * @param branch
     * @return
     * @throws GitAPIException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws CancelledAuthorizationException
     * @throws BackingStoreException
     * @throws MissingRepoException
     */
    public List<Path> queryRepoFiles(String branch)
            throws GitAPIException, IOException, ClassNotFoundException, CancelledAuthorizationException, BackingStoreException, MissingRepoException {
        branch = StringUtils.isNotBlank(branch) ? branch : OpsSysParameterUtil.getGitBranch();
        BranchModel branchModel = repoHelper.getBranchModel();
        BranchHelper branchHelper = branchModel.getBranchByName(BranchModel.BranchType.LOCAL, branch);
        if (branchHelper == null) {
            throw new RuntimeException("本地分支[" + branch + "]不存在");
        }
        repoHelper.updateModel();
        branchHelper.checkoutBranch();
        return repoHelper.getAllRepoFiles();
    }

    /**
     * 检出标签
     *
     * @param tagName
     * @throws GitAPIException
     * @throws IOException
     * @throws CancelledAuthorizationException
     * @throws ClassNotFoundException
     * @throws BackingStoreException
     * @throws MissingRepoException
     */
    public void checkoutTag(String tagName)
            throws GitAPIException, IOException, CancelledAuthorizationException, ClassNotFoundException, BackingStoreException, MissingRepoException {
        repoHelper.updateModel();
        TagHelper tag = repoHelper.getTagModel().getTag(tagName);
        if (tag == null) {
            throw new RuntimeException("标签[" + tagName + "]不存在");
        }
        LocalBranchHelper localBranchHelper = new LocalBranchHelper(tag.getRefName(), repoHelper);
        localBranchHelper.checkoutBranch();
    }

    /**
     * 检出本地分支或远程分支
     *
     * @param branch
     * @return
     * @throws IOException
     * @throws GitAPIException
     */
    public BranchHelper checkoutBranch(String branch) throws GitAPIException, IOException {
        BranchModel branchModel = repoHelper.getBranchModel();
        BranchHelper currentBranch = branchModel.getBranchByName(BranchModel.BranchType.LOCAL, branch);
        if (currentBranch == null) {
            currentBranch = branchModel.getBranchByName(BranchModel.BranchType.REMOTE, remote + "/" + branch);
            if (currentBranch == null) {
                throw new RuntimeException("分支[" + branch + "]不存在");
            }
        }
        currentBranch.checkoutBranch();
        repoHelper.updateModel();
        return currentBranch;
    }

    /**
     * 检出远程分支为本地分支
     *
     * @param branch
     * @throws GitAPIException
     * @throws IOException
     * @throws CancelledAuthorizationException
     * @throws ClassNotFoundException
     * @throws BackingStoreException
     * @throws MissingRepoException
     */
    public void checkoutRemoteBranch(String branch)
            throws GitAPIException, IOException, CancelledAuthorizationException, ClassNotFoundException, BackingStoreException, MissingRepoException {
        BranchModel branchModel = repoHelper.getBranchModel();
        RemoteBranchHelper branchHelper = (RemoteBranchHelper) branchModel.getBranchByName(BranchModel.BranchType.REMOTE, "origin/" + branch);
        if (branchHelper == null) {
            throw new RuntimeException("远程分支[" + branch + "]不存在");
        }
        branchHelper.checkoutBranch();
        repoHelper.updateModel();
    }

    /**
     * 检出本地分支
     *
     * @param branch
     * @throws GitAPIException
     * @throws IOException
     * @throws CancelledAuthorizationException
     * @throws ClassNotFoundException
     * @throws BackingStoreException
     * @throws MissingRepoException
     */
    public void checkoutLocalBranch(String branch)
            throws GitAPIException, IOException, CancelledAuthorizationException, ClassNotFoundException, BackingStoreException, MissingRepoException {
        BranchModel branchModel = repoHelper.getBranchModel();
        BranchHelper branchHelper = branchModel.getBranchByName(BranchModel.BranchType.LOCAL, branch);
        if (branchHelper == null) {
            throw new RuntimeException("本地分支[" + branch + "]不存在");
        }
        branchHelper.checkoutBranch();
        repoHelper.updateModel();
    }

    /**
     * 查询所有本地分支
     *
     * @return
     */
    public List<String> listLocalBranches() {
        BranchModel branchModel = repoHelper.getBranchModel();
        List<String> list = new ArrayList<>();
        List<LocalBranchHelper> branchHelpers = branchModel.getLocalBranchesTyped();
        for (LocalBranchHelper branchHelper : branchHelpers) {
            list.add(branchHelper.getAbbrevName());
        }
        return list;
    }

    /**
     * 新建本地分支
     *
     * @param branchName
     * @return
     * @throws GitAPIException
     * @throws IOException
     */
    public String createNewLocalBranch(String branchName) throws GitAPIException, IOException {
        BranchModel branchModel = repoHelper.getBranchModel();
        LocalBranchHelper branchHelper = branchModel.createNewLocalBranch(branchName);
        return branchHelper.getAbbrevName();
    }

    /**
     * 删除本地分支
     *
     * @param branchName
     * @throws CannotDeleteCurrentBranchException
     * @throws GitAPIException
     */
    public void dropLocalBranch(String branchName) throws CannotDeleteCurrentBranchException, GitAPIException {
        BranchModel branchModel = repoHelper.getBranchModel();
        LocalBranchHelper branchHelper = (LocalBranchHelper) branchModel.getBranchByName(BranchModel.BranchType.LOCAL, branchName);
        branchModel.forceDeleteLocalBranch(branchHelper);
    }

    /**
     * 删除远程分支
     *
     * @param branchName
     * @return
     * @throws CannotDeleteCurrentBranchException
     * @throws GitAPIException
     * @throws IOException
     */
    public boolean dropRemoteBranch(String branchName) throws CannotDeleteCurrentBranchException, GitAPIException, IOException {
        BranchModel branchModel = repoHelper.getBranchModel();
        RemoteBranchHelper branchHelper = (RemoteBranchHelper) branchModel.getBranchByName(BranchModel.BranchType.REMOTE, remote + "/" + branchName);
        Status status = branchModel.deleteRemoteBranch(branchHelper);
        return status == Status.OK;
    }

    /**
     * 暂存所有修改
     *
     * @throws NoFilepatternException
     * @throws GitAPIException
     */
    public void stageAll() throws NoFilepatternException, GitAPIException {
        Git git = new Git(this.repoHelper.repo);
        git.add().addFilepattern(".").call();
        git.close();
    }

    /**
     * 推送所有分支及标签
     *
     * @throws GitAPIException
     * @throws PushToAheadRemoteError
     * @throws IOException
     */
    public void pushAll() throws GitAPIException, PushToAheadRemoteError, IOException {
        Git git = new Git(this.repoHelper.repo);
        PushCommand push = git.push().setRemote(remote).setPushAll();
        repoHelper.pushCurrentBranch(push);
        git.close();
    }

    /**
     * 拉取远程内容
     *
     * @throws GitAPIException
     * @throws CancelledAuthorizationException
     */
    public void pull() throws GitAPIException, CancelledAuthorizationException {
        Git git = new Git(this.repoHelper.repo);
        UsernamePasswordCredentialsProvider provider = this.repoHelper.getOwnerAuthCredentials();
        git.pull().setRemote(remote).setCredentialsProvider(provider).call();
        git.close();
    }

    /**
     * 推送本地分支
     *
     * @param branchName
     * @throws IOException
     * @throws PushToAheadRemoteError
     * @throws GitAPIException
     */
    public void pushLocalBranch(String branchName) throws IOException, GitAPIException, PushToAheadRemoteError {
        BranchModel branchModel = repoHelper.getBranchModel();
        BranchHelper localBranch = branchModel.getBranchByName(BranchModel.BranchType.LOCAL, branchName);
        if (localBranch == null) {
            throw new RuntimeException("本地分支" + branchName + "不存在, 本地仓库路径：" + this.repoHelper.localPath);
        }
        BranchHelper remoteBranch = branchModel.getBranchByName(BranchModel.BranchType.REMOTE, remote + "/" + branchName);
        if (remoteBranch == null) {
            repoHelper.setUpstreamBranch(localBranch, remote);
        }
        Git git = new Git(this.repoHelper.repo);
        PushCommand push = git.push().setRemote(remote).add(localBranch.getRefPathString());
        repoHelper.pushCurrentBranch(push);
        git.close();
    }

    /**
     * 查询两次提交间文件变动
     *
     * @param branchName
     * @param beginCommitId
     * @param endCommitId
     * @return
     * @throws IOException
     * @throws GitAPIException
     */
    public List<DiffEntry> showRepoFileDiffs(String beginCommitId, String endCommitId, String branchName) throws IOException, GitAPIException {
        if (StringUtils.isNotBlank(branchName)) {
            checkoutBranch(branchName);
        }
        Repository repository = this.repoHelper.repo;
        // all refs
        AbstractTreeIterator oldTreeParser = prepareCommitTreeParser(repository, repository.resolve(beginCommitId + "^").name());
        AbstractTreeIterator newTreeParser = prepareCommitTreeParser(repository, endCommitId);

        try (Git git = new Git(repository)) {
            DiffCommand diffCmd = git.diff().setOldTree(oldTreeParser).setNewTree(newTreeParser);
            return diffCmd.call();
        }
    }

    // 直接比较两个commitId,不进行上一版本的比较 (应用版本)
    public List<DiffEntry> showAppVersionFileDiffs(String beginCommitId, String endCommitId, String branchName) throws IOException, GitAPIException {
        if (StringUtils.isNotBlank(branchName)) {
            checkoutBranch(branchName);
        }
        Repository repository = this.repoHelper.repo;
        // all refs
        AbstractTreeIterator oldTreeParser = prepareCommitTreeParser(repository, beginCommitId);
        AbstractTreeIterator newTreeParser = prepareCommitTreeParser(repository, endCommitId);

        try (Git git = new Git(repository)) {
            DiffCommand diffCmd = git.diff().setOldTree(oldTreeParser).setNewTree(newTreeParser);
            return diffCmd.call();
        }
    }

    /**
     * 对比分支文件差异
     *
     * @return
     * @throws IOException
     * @throws GitAPIException
     */
    public List<DiffEntry> showBranchDiffs(String diffBranch, String mainBranch) throws IOException, GitAPIException {
        mainBranch = StringUtils.isNotBlank(mainBranch) ? mainBranch : OpsSysParameterUtil.getGitBranch();
        Repository repository = this.repoHelper.repo;
        try (Git git = new Git(repository);) {
            // the diff works on TreeIterators, we prepare two for the two branches
            AbstractTreeIterator oldTreeParser = prepareTreeParser(repository, "refs/heads/" + mainBranch);
            AbstractTreeIterator newTreeParser = prepareTreeParser(repository, "refs/heads/" + diffBranch);

            // then the procelain diff-command returns a list of diff entries
            return git.diff().setOldTree(oldTreeParser).setNewTree(newTreeParser).call();
        }
    }

    /**
     * 查询两个分支基点
     *
     * @param commitIdA
     * @param commitIdB
     * @return divergence point between the two branches (even if seemingly unrelated all must come back to master)
     */
    public String getMergeBase(String branchNameA, String branchNameB) {
        BranchModel branchModel = repoHelper.getBranchModel();
        BranchHelper branchA = branchModel.getBranchByName(BranchType.REMOTE, "origin/" + branchNameA);
        if (branchA == null) {
            branchA = branchModel.getBranchByName(BranchType.LOCAL, branchNameA);
        }
        BranchHelper branchB = branchModel.getBranchByName(BranchType.REMOTE, "origin/" + branchNameB);
        if (branchB == null) {
            branchB = branchModel.getBranchByName(BranchType.LOCAL, branchNameB);
        }
        try (RevWalk walk = new RevWalk(repoHelper.repo);) {

            RevCommit revA = walk.lookupCommit(branchA.getHeadId());
            RevCommit revB = walk.lookupCommit(branchB.getHeadId());

            walk.setRevFilter(RevFilter.MERGE_BASE);

            walk.markStart(revA);
            walk.markStart(revB);
            RevCommit mergeBase = walk.next();
            return mergeBase == null ? null : mergeBase.getName();
        } catch (Exception e) {
            throw new ApplicationContextException("GetMergeBase Failed: " + branchNameA + ", " + branchNameB + " because " + e.getMessage());
        }
    }

    /**
     * 查询已暂存文件变动
     *
     * @return
     * @throws IOException
     * @throws GitAPIException
     */
    public List<DiffEntry> showCurrentRepoStagedFileDiffs() throws GitAPIException, IOException {
        Repository repository = this.repoHelper.repo;
        try (Git git = new Git(repository)) {
            return git.diff().setCached(true).call();
        }
    }

    /**
     * 获取最新提交ID
     *
     * @return
     * @throws IOException
     * @throws GitAPIException
     */
    public String getCurrentRepoHeadCommitId() throws IOException, GitAPIException {
        repoHelper.updateModel();
        BranchModel branchModel = repoHelper.getBranchModel();
        BranchHelper currentBranch = branchModel.getCurrentBranch();
        return currentBranch.getHeadId().getName();
    }

    /**
     * 对比分支之间差异
     */
    public static AbstractTreeIterator prepareTreeParser(Repository repository, String ref) throws IOException {
        Ref head = repository.exactRef(ref);
        try (RevWalk walk = new RevWalk(repository)) {
            RevCommit commit = walk.parseCommit(head.getObjectId());
            RevTree tree = walk.parseTree(commit.getTree().getId());

            CanonicalTreeParser treeParser = new CanonicalTreeParser();
            try (ObjectReader reader = repository.newObjectReader()) {
                treeParser.reset(reader, tree.getId());
            }
            walk.dispose();
            return treeParser;
        }
    }

    /**
     * 对比提交记录间差异
     *
     * @param repository
     * @param objectId
     * @return
     * @throws IOException
     */
    private static AbstractTreeIterator prepareCommitTreeParser(Repository repository, String objectId) throws IOException {
        try (RevWalk walk = new RevWalk(repository)) {
            RevCommit commit = walk.parseCommit(ObjectId.fromString(objectId));
            RevTree tree = walk.parseTree(commit.getTree().getId());

            CanonicalTreeParser treeParser = new CanonicalTreeParser();
            try (ObjectReader reader = repository.newObjectReader()) {
                treeParser.reset(reader, tree.getId());
            }

            walk.dispose();

            return treeParser;
        }
    }

    /**
     * 合并分支
     *
     * @param fromBranch
     * @param toBranch
     * @return
     * @throws GitAPIException
     * @throws IOException
     * @throws MissingRepoException
     */
    public MergeStatus mergeBranch(String fromBranch, String toBranch) throws GitAPIException, IOException, MissingRepoException {
        BranchModel branchModel = repoHelper.getBranchModel();
        BranchHelper fromBranchHelper = this.checkoutBranch(fromBranch);
        BranchHelper toBranchHelper = this.checkoutBranch(toBranch);
        MergeResult mergeWithBranch = branchModel.mergeWithBranch(fromBranchHelper);
        MergeStatus mergeStatus = mergeWithBranch.getMergeStatus();
        if (!mergeStatus.isSuccessful()) {
            repoHelper.reset(toBranchHelper.getRefName(), ResetType.HARD);
        }
        return mergeStatus;
    }

    /**
     * 创建标签
     *
     * @param tagName
     * @return
     * @throws InvalidTagNameException
     * @throws GitAPIException
     * @throws MissingRepoException
     * @throws IOException
     * @throws TagNameExistsException
     * @throws PushToAheadRemoteError
     */
    public boolean createTageFromMainBranch(String tagName)
            throws InvalidTagNameException, GitAPIException, MissingRepoException, IOException, TagNameExistsException, PushToAheadRemoteError {
        TagModel tagModel = repoHelper.getTagModel();
        BranchModel branchModel = repoHelper.getBranchModel();
        if (!branchModel.isBranchCurrent(this.mainBranchHelper)) {
            this.mainBranchHelper.checkoutBranch();
        }
        TagHelper tag = tagModel.getTag(tagName);
        if (tag != null) {
            tagModel.deleteTag(tagName);
        }
        String commitId = this.getCurrentRepoHeadCommitId();
        tagModel.tag(tagName, commitId);
        repoHelper.pushTags();
        return true;
    }

    /**
     * 获取当前分支最近一次提交
     *
     * @return
     * @throws IOException
     * @throws GitAPIException
     */
    public String getCurrentHeadCommitDesc() throws GitAPIException, IOException {
        BranchModel branchModel = repoHelper.getBranchModel();
        BranchHelper currentBranch = branchModel.getCurrentBranch();
        return repoHelper.getCommitDescriptorString(currentBranch.getHeadId().name(), true);
    }

    public RepoHelper getRepoHelper() {
        return repoHelper;
    }

    public BranchHelper getMainBranchHelper() {
        return mainBranchHelper;
    }

    /**
     * 获取文件差异
     *
     * @param entry
     * @return
     * @throws IOException
     */
    public String getDiffContent(DiffEntry entry) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
            try (DiffFormatter formatter = new DiffFormatter(bos)) {
                formatter.setRepository(repoHelper.repo);
                formatter.format(entry);
                return new String(bos.toByteArray());
            }
        }

    }

}
