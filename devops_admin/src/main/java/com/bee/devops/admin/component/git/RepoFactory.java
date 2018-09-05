package com.bee.devops.admin.component.git;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.prefs.BackingStoreException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import com.bee.devops.admin.component.git.exceptions.CancelledAuthorizationException;
import com.bee.devops.admin.component.git.exceptions.MissingRepoException;
import com.bee.devops.admin.component.git.exceptions.PushToAheadRemoteError;
import com.bee.devops.admin.component.git.exceptions.TagNameExistsException;
import com.bee.devops.admin.component.git.helper.ClonedRepoHelper;
import com.bee.devops.admin.component.git.helper.ExistingRepoHelper;
import com.bee.devops.admin.component.git.helper.RepoHelper;
import com.bee.devops.admin.component.git.model.SessionModel;

/**
 * git仓库工厂类
 * 
 * @description RepoFactory
 * @author TurnerXi
 * @date 2018年7月10日
 */
public class RepoFactory {

	private static RepoFactory factory;
	private SessionModel sessionModel;
	private UsernamePasswordCredentialsProvider credential;

	public static void main(String[] args)
	        throws ClassNotFoundException, GitAPIException, IOException, CancelledAuthorizationException, BackingStoreException, MissingRepoException, PushToAheadRemoteError, TagNameExistsException {
		RepoFactory instance = RepoFactory.getInstance();
		Repo repo = instance.get("E:\\home\\config\\E00P00\\E001\\P032", "http://192.168.100.110:31000/E09/P00/DEVELOP/E00P00/mshop/config.git");
//		List<String> queryRepoFiles = repo.queryRepoFiles("admin20180706171009");
//		List<String> result = repo.listLocalBranches();
//		repo.pushLocalBranch("temp123");
//		File file = new File("E:\\home\\config\\E00P00\\E001\\P032\\WEB-INF\\web.xml");
//		System.out.println(new String(Files.readAllBytes(file.toPath()),"utf-8")); 
//		System.out.println(result);
//		repo.checkoutBranch("master");
//		System.out.println(commitOneRepoFile);
//		List<DiffEntry> showCurrentRepoFileDiffs = repo.showCurrentRepoStagedFileDiffs();
//		System.out.println(showCurrentRepoFileDiffs);
//		repo.createTageFromMainBranch("tag_test");
//		String currentHeadCommit = repo.getCurrentHeadCommitDesc();
//		System.out.println(currentHeadCommit);
//		File[] listFiles = repo.getRepoHelper().localPath.toFile().listFiles();
//		for (File file : listFiles) {
//			if(!".git".equals(file.getName())) {
//				NioFileUtil.deleteIfExists(file.toPath());
//			}
//		}
//		Path path = Paths.get("E:\\home\\config\\E00P00\\E001\\P006");
//		Path taget = Paths.get("E:\\home\\config\\E00P00\\E001\\P032");
//		File[] appFiles = path.toFile().listFiles();
//		for (File file : appFiles) {
//			try {
//				if(!".git".equals(file.getName()))
//				NioFileUtil.copyFolder(file.getAbsolutePath(), taget.toString());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		NioFileUtil.copyFolder(path.toString(), taget.toString(), false);
//		System.out.println(Paths.get("."));
		String mergeBase = repo.getMergeBase("master", "admin20180706171009");
		System.out.println(mergeBase);
	}

	private RepoFactory(String username, String password) {
		super();
		this.credential = new UsernamePasswordCredentialsProvider(username, password);
		this.sessionModel = SessionModel.getSessionModel();
	}

	public static RepoFactory getInstance() {
		if (RepoFactory.factory == null) {
			String username = OpsSysParameterUtil.getStringProperty(OpsSysParameterUtil.GITLAB_USERNAME);
			String password = OpsSysParameterUtil.getStringProperty(OpsSysParameterUtil.GITLAB_PASSWORD);
			RepoFactory.factory = new RepoFactory(username, password);
		}
		return RepoFactory.factory;
	}

	public Repo get(String repoPath, String remoteUrl)
	        throws GitAPIException, IOException, CancelledAuthorizationException, ClassNotFoundException, BackingStoreException, MissingRepoException {
		RepoHelper repo = sessionModel.matchRepoWithAlreadyLoadedRepo(repoPath);
		if (repo != null && !repo.exists()) {
			RepoHelper[] arr = { repo };
			sessionModel.removeRepoHelpers(Arrays.asList(arr));
			repo = null;
		}

		if (repo == null) {
			File file = new File(repoPath);
			if (file != null && file.exists()) {
				RepoHelper repoHelper = new RepoHelper(file.toPath(), this.credential);
				if (repoHelper.exists()) {
					repo = new ExistingRepoHelper(file.toPath(), this.credential);
				}
			}
			if (repo == null || !repo.exists()) {
				ClonedRepoHelper clonedRepo = new ClonedRepoHelper(file.toPath(), remoteUrl, this.credential);
				clonedRepo.obtainRepository(remoteUrl);
				sessionModel.openRepoFromHelper(clonedRepo);
				repo = clonedRepo;
			}
		}
		return new Repo(repo);
	}

}
