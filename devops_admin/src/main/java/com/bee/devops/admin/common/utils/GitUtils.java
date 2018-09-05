//package com.bee.devops.admin.common.utils;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.nio.channels.FileChannel;
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
//import org.eclipse.jgit.api.CloneCommand;
//import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
//import org.eclipse.jgit.api.DiffCommand;
//import org.eclipse.jgit.api.Git;
//import org.eclipse.jgit.api.ListBranchCommand.ListMode;
//import org.eclipse.jgit.api.MergeCommand.FastForwardMode;
//import org.eclipse.jgit.api.RebaseCommand;
//import org.eclipse.jgit.api.RebaseCommand.InteractiveHandler;
//import org.eclipse.jgit.api.RebaseResult;
//import org.eclipse.jgit.api.StatusCommand;
//import org.eclipse.jgit.api.errors.CheckoutConflictException;
//import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
//import org.eclipse.jgit.api.errors.GitAPIException;
//import org.eclipse.jgit.api.errors.InvalidRefNameException;
//import org.eclipse.jgit.api.errors.InvalidRemoteException;
//import org.eclipse.jgit.api.errors.InvalidTagNameException;
//import org.eclipse.jgit.api.errors.NoHeadException;
//import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
//import org.eclipse.jgit.api.errors.RefNotFoundException;
//import org.eclipse.jgit.api.errors.TransportException;
//import org.eclipse.jgit.diff.DiffEntry;
//import org.eclipse.jgit.diff.DiffFormatter;
//import org.eclipse.jgit.errors.IllegalTodoFileModification;
//import org.eclipse.jgit.internal.storage.file.FileRepository;
//import org.eclipse.jgit.lib.ObjectId;
//import org.eclipse.jgit.lib.ObjectReader;
//import org.eclipse.jgit.lib.RebaseTodoLine;
//import org.eclipse.jgit.lib.RebaseTodoLine.Action;
//import org.eclipse.jgit.lib.Ref;
//import org.eclipse.jgit.lib.Repository;
//import org.eclipse.jgit.revwalk.RevCommit;
//import org.eclipse.jgit.revwalk.RevTree;
//import org.eclipse.jgit.revwalk.RevWalk;
//import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
//import org.eclipse.jgit.treewalk.AbstractTreeIterator;
//import org.eclipse.jgit.treewalk.CanonicalTreeParser;
//import org.eclipse.jgit.treewalk.filter.PathFilter;
//import org.springframework.context.ApplicationContextException;
//
///**
// * Created by yangliang on 2018/03/19.
// */
//public class GitUtils {
//
//	final static Logger log = Logger.getLogger(GitUtils.class);
//
//	
//	
//	public static void main(String[] args) throws IOException, GitAPIException {
//		String userName = "chunhai.yang@lcbeta.com";
//		String password = "yh201314";
//		String localpath = "E:\\home\\config\\E00P00\\E001\\P032";
//		String branchName = "admin20180609090300";
//		UsernamePasswordCredentialsProvider credential = new UsernamePasswordCredentialsProvider("chunhai.yang@lcbeta.com", "yh201314");
//		Repository repository = new FileRepository(localpath + "/.git");
//		Git git = new Git(repository);
//		Ref trackingBranchRef = git.branchCreate().
//                setName(branchName).
//                setUpstreamMode(SetupUpstreamMode.TRACK).
//                setStartPoint(repository.resolve("origin/"+branchName).name()).
//                call();
//	}
//
//	/**
//	 * Show diff of changes to a file between two revs
//	 * 
//	 * @param localpath
//	 * @return
//	 * @throws IOException
//	 * @throws GitAPIException
//	 */
//	public static List<DiffEntry> showDiff(String localpath, String beginSha, String endSha, String filePath) throws IOException, GitAPIException {
//		try (Repository repository = new FileRepository(localpath + "/.git")) {
//			// all refs
//			AbstractTreeIterator oldTreeParser = prepareCommitTreeParser(repository, repository.resolve(beginSha+"^").name());
//			AbstractTreeIterator newTreeParser = prepareCommitTreeParser(repository, endSha);
//
//			try (Git git = new Git(repository)) {
//				DiffCommand diffCmd = git.diff().setOldTree(oldTreeParser).setNewTree(newTreeParser);
//				if (!StringUtils.isBlank(filePath)) {
//					// to filter on Suffix use the following instead
//					// setPathFilter(PathSuffixFilter.create(".java")).
//					diffCmd.setPathFilter(PathFilter.create(filePath));
//				}
//				return diffCmd.call();
//			}
//		}
//	}
//	
//	public static String showDiffContent(String localpath, DiffEntry entry) throws IOException {
//		StringBuffer content = new StringBuffer();
//		try (Repository repository = new FileRepository(localpath + "/.git")) {
//			try (ByteArrayOutputStream bos=new ByteArrayOutputStream();) {
//				try (DiffFormatter formatter = new DiffFormatter(bos)) {
//					formatter.setRepository(repository);
//					formatter.format(entry);
//					content.append(new String(bos.toByteArray()));
//				}
//			}
//		}
//		return content.toString();
//	}
//
//    private static AbstractTreeIterator prepareCommitTreeParser(Repository repository, String objectId) throws IOException {
//        // from the commit we can build the tree which allows us to construct the TreeParser
//        //noinspection Duplicates
//        try (RevWalk walk = new RevWalk(repository)) {
//            RevCommit commit = walk.parseCommit(ObjectId.fromString(objectId));
//            RevTree tree = walk.parseTree(commit.getTree().getId());
//
//            CanonicalTreeParser treeParser = new CanonicalTreeParser();
//            try (ObjectReader reader = repository.newObjectReader()) {
//                treeParser.reset(reader, tree.getId());
//            }
//
//            walk.dispose();
//
//            return treeParser;
//        }
//    }
//    
////	public static void main(String[] args) throws IOException {
//////		showDiff("E:\\home\\config\\E00P00\\E001\\P032", "master", "admin20180608100611");
////		
////		try (Repository repository = new FileRepository("E:\\home\\config\\E00P00\\E001\\P032" + "/.git")) {
////            // find the HEAD
////            ObjectId lastCommitId = repository.resolve(Constants.HEAD);
////
////            // a RevWalk allows to walk over commits based on some filtering that is defined
////            try (RevWalk revWalk = new RevWalk(repository)) {
////                RevCommit commit = revWalk.parseCommit(lastCommitId);
////                // and using commit's tree find the path
////                RevTree tree = commit.getTree();
////                System.out.println("Having tree: " + tree);
////
////                // now try to find a specific file
////                try (TreeWalk treeWalk = new TreeWalk(repository)) {
////                    treeWalk.addTree(tree);
////                    treeWalk.setRecursive(true);
////                    treeWalk.setFilter(PathFilter.create("WEB-INF/web.xml"));
////                    if (!treeWalk.next()) {
////                        throw new IllegalStateException("Did not find expected file 'README.md'");
////                    }
////
////                    ObjectId objectId = treeWalk.getObjectId(0);
////                    ObjectLoader loader = repository.open(objectId);
////
////                    // and then one can the loader to read the file
////                    loader.copyTo(System.out);
////                }
////
////                revWalk.dispose();
////            }
////        }
////	}
//	
//	/**
//	 * 查询已暂存文件变动
//	 * @param localpath
//	 * @return
//	 */
//	public static List<DiffEntry> showDiff(String localpath) {
//		try (Git git = new Git(new FileRepository(localpath + "/.git"));) {
//			return git.diff().setCached(true).call();
//		} catch (Exception e) {
//			throw new ApplicationContextException(e.getMessage());
//		}
//	}
//
//	/**
//	 * 对比分支文件差异
//	 * @param localpath
//	 * @return
//	 * @throws IOException 
//	 */
//	public static List<DiffEntry> showDiff(String localpath, String masterBranch, String diffBranch) throws IOException {
//		FileRepository repository = new FileRepository(localpath + "/.git");
//		try (Git git = new Git(repository);) {
//			 // the diff works on TreeIterators, we prepare two for the two branches
//            AbstractTreeIterator oldTreeParser = prepareTreeParser(repository, "refs/heads/"+diffBranch);
//            AbstractTreeIterator newTreeParser = prepareTreeParser(repository, "refs/heads/"+masterBranch);
//
//            // then the procelain diff-command returns a list of diff entries
//            return git.diff().setOldTree(oldTreeParser).setNewTree(newTreeParser).call();
//		} catch (Exception e) {
//			throw new ApplicationContextException(e.getMessage());
//		}
//	}
//    
//	/**
//	 * 合并分支
//	 */
//	public static String rebaseBranch(String frombranch, String tobranch, String localpath) throws IOException {
//		Repository repository = new FileRepository(localpath + "/.git");
//		Git git = new Git(repository);
//		InteractiveHandler handler = new InteractiveHandler() {
//			@Override
//			public void prepareSteps(List<RebaseTodoLine> steps) {
//				for (RebaseTodoLine step : steps) {
//					try {
//						step.setAction(Action.EDIT);
//					} catch (IllegalTodoFileModification e) {
//						throw new IllegalStateException(e);
//					}
//				}
//			}
//
//			@Override
//			public String modifyCommitMessage(String oldMessage) {
//				return oldMessage;
//			}
//		};
//
//		RebaseResult result = null;
//		try {
//			result = git.rebase().setUpstream(frombranch).runInteractively(handler).call();
//			log.info("Rebase had state: " + result.getStatus() + ": " + result.getConflicts());
//			if (!result.getStatus().isSuccessful()) {
//				result = git.rebase().setUpstream(frombranch).runInteractively(handler).setOperation(RebaseCommand.Operation.ABORT).call();
//				log.info("Aborted reabse with state: " + result.getStatus() + ": " + result.getConflicts());
//			}
//		} catch (GitAPIException e) {
//			e.printStackTrace();
//		}
//		git.close();
//		return "success";
//	}
//
//	/**
//	 * 创建分支名
//	 */
//	public static String createBranchName(String userId) {
//		String newBranchName = String.valueOf(userId) + DateUtils.dateToStr(new Date(), "yyyyMMddHHmmss");
//		log.info(newBranchName);
//		return newBranchName;
//	}
//
//	/**
//	 * 创建分支
//	 */
//	public static String createBranch(String localpath, String newBranchName) {
//		Repository repository = null;
//		try {
//			repository = new FileRepository(localpath + "/.git");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Git git = new Git(repository);
//		try {
//			git.branchCreate().setName(newBranchName).call();
//		} catch (GitAPIException e) {
//			git.close();
//			return "创建分支失败";
//		}
//		git.close();
//		return "success";
//	}
//
//	/**
//	 * 文件迭代查看路径
//	 */
//	public List<String> filePathName(String path) {
//
//		return checkFilePathName(path);
//	}
//
//	List<String> res = new LinkedList<>();
//
//	public List<String> checkFilePathName(String path) {
//		File file = new File(path);
//		File files[] = file.listFiles();
//		for (File tempFile : files) {
//			if (!".git".equals(tempFile.getName())) {
//				if (tempFile.isDirectory()) {
//					checkFilePathName(tempFile.getAbsolutePath());
//				} else {
//					res.add(tempFile.getAbsolutePath());
//				}
//			}
//		}
//		return res;
//	}
//
////	/**
////	 * 获取分支文件
////	 * 
////	 * @throws GitAPIException
////	 */
////	public static List<Ref> showBranches(String localpath, ListMode mode) throws GitAPIException {
////		Repository repository = null;
////		try {
////			repository = new FileRepository(localpath + "/.git");
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		Git git = new Git(repository);
////		List<Ref> call = git.branchList().setListMode(mode).call();
////		git.close();
////		return call;
////	}
//
//	/**
//	 * 克隆远程库
//	 * 
//	 * @throws IOException
//	 * @throws GitAPIException
//	 */
//	public static String cloneRepository(String username, String password, String giturl, String branch, String localpath) {
//		UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new UsernamePasswordCredentialsProvider(username, password);
//		// 克隆代码库命令
//		CloneCommand cloneCommand = Git.cloneRepository();
//		try {
//			cloneCommand.setURI(giturl) // 设置远程URI
//			        .setCloneAllBranches(true).setDirectory(new File(localpath)) // 设置下载存放路径
//			        .setCredentialsProvider(usernamePasswordCredentialsProvider) // 设置权限验证
//			        .call();
//		} catch (InvalidRemoteException e) {
//			log.error(e);
//			return "远程仓库地址错误或者用户没有访问此仓库的权限！仓库地址：" + giturl;
//		} catch (TransportException e) {
//			log.error(e);
//			return "克隆失败!";
//		} catch (GitAPIException e) {
//			log.error(e);
//			return "克隆失败!";
//		}
//		return "success";
//	}
//
//	/**
//	 * 创建TAG
//	 */
//	public static String CreateRepositoryTag(String localpath, String tagname) throws IOException {
//		Repository repository = new FileRepository(localpath + "/.git");
//		try (Git git = new Git(repository)) {
//			Ref tag = git.tag().setName(tagname).call();
//			log.info("Created " + tag + " to repository at " + repository.getDirectory());
//		} catch (ConcurrentRefUpdateException e) {
//			log.error(e);
//			return "创建失败！";
//		} catch (InvalidTagNameException e) {
//			log.error(e);
//			return "标签名字不可用！";
//		} catch (NoHeadException e) {
//			log.error(e);
//			return "创建失败！";
//		} catch (GitAPIException e) {
//			log.error(e);
//			return "创建失败，标签已经存在！";
//		}
//		return "success";
//	}
//
//	/**
//	 * 删除TAG
//	 */
//	public static String DeleteRepositoryTag(String localpath, String tagname) throws IOException {
//		Repository repository = new FileRepository(localpath + "/.git");
//		try (Git git = new Git(repository)) {
//			git.tagDelete().setTags(tagname).call();
//			log.info("Created " + tagname + " to repository at " + repository.getDirectory());
//		} catch (ConcurrentRefUpdateException e) {
//			log.error(e);
//			return "删除失败！";
//		} catch (InvalidTagNameException e) {
//			log.error(e);
//			return "标签名字不可用！";
//		} catch (NoHeadException e) {
//			log.error(e);
//			return "删除失败！";
//		} catch (GitAPIException e) {
//			log.error(e);
//			return "删除失败！";
//		}
//		return "success";
//	}
//
//	/**
//	 * 本地仓库文件修改提交
//	 */
//	public static List<Ref> getTags(String localpath) throws IOException {
//		Repository repository = new FileRepository(localpath + "/.git");
//		List<Ref> call = null;
//		try (Git git = new Git(repository);) {
//			call = git.tagList().call();
//		} catch (GitAPIException e) {
//			e.printStackTrace();
//		}
//		return call;
//	}
//
//	/**
//	 * 本地仓库文件修改提交
//	 */
//	public static RevCommit localRepositoryCommitFile(String localpath, String message) throws IOException {
//		Repository repository = new FileRepository(localpath + "/.git");
//		RevCommit commit = null;
//		try (Git git = new Git(repository);) {
//			git.add().addFilepattern(".").call();
//			commit = git.commit().setAll(true).setMessage(message).call();
//		} catch (GitAPIException e) {
//			log.error(e);
//		}
//		return commit;
//	}
//
//	/**
//	 * 本地仓库文件暂存
//	 */
//	public static void stageFile(String localpath) throws IOException {
//		Repository repository = new FileRepository(localpath + "/.git");
//		try (Git git = new Git(repository);) {
//            git.add().setUpdate(true).addFilepattern(".").call();
//			git.add().addFilepattern(".").call();
//		} catch (GitAPIException e) {
//			log.error("文件暂存失败："+e.getMessage());
//			throw new ApplicationContextException(e.getMessage());
//		}
//	}
//
//	/**
//	 * 获取状态
//	 * 
//	 * @author Yang Chunhai
//	 * @param localpath
//	 * @return
//	 * @throws IOException
//	 */
//	public static StatusCommand localFileStatus(String localpath) throws IOException {
//		StatusCommand status = null;
//		Repository repository = new FileRepository(localpath + "/.git");
//		try (Git git = new Git(repository);) {
//			status = git.status();
//		} catch (Exception e) {
//			return null;
//		}
//		return status;
//	}
//
//	/**
//	 * 拉取远程仓库内容到本地
//	 */
//	public static void projectAppPull(String username, String password, String localpath, String branch) throws IOException, GitAPIException {
//		UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new UsernamePasswordCredentialsProvider(username, password);
//		Git git = new Git(new FileRepository(localpath + "/.git"));
//		git.pull().setRemoteBranchName(branch).setCredentialsProvider(usernamePasswordCredentialsProvider).call();
//		git.close();
//	}
//
//	/**
//	 * push本地代码到远程仓库地址
//	 */
//	public static String pushRepository(String username, String password, String localpath) throws IOException {
//		UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new UsernamePasswordCredentialsProvider(username, password);
//		Repository repository = new FileRepository(localpath + "/.git");
//		try (Git git = new Git(repository);) {
//			git.push().setRemote("origin").setCredentialsProvider(usernamePasswordCredentialsProvider).setPushTags().setPushAll().call();
//		} catch (InvalidRemoteException e) {
//			log.error(e);
//			return "推送到远程仓库失败！";
//		} catch (TransportException e) {
//			log.error(e);
//			return "推送到远程仓库失败！";
//		} catch (GitAPIException e) {
//			log.error(e);
//			return "推送到远程仓库失败！";
//		}
//		return "success";
//	}
//
//	/**
//	 * 审核重置合并状态
//	 */
//	public static String reset(String localpath) throws IOException {
//		Repository repository = new FileRepository(localpath + "/.git");
//		try (Git git = new Git(repository);) {
//			git.reset().call();
//		} catch (GitAPIException e) {
//			return "重置分支状态失败";
//		}
//		return "success";
//	}
//
//	/**
//	 * 检出远程分支到本地
//	 * @param localpath
//	 * @return
//	 * @return
//	 * @throws IOException
//	 * @throws GitAPIException 
//	 */
//	public static boolean checkoutRemoteRepository(String localpath, String branchName)
//	        throws IOException, GitAPIException {
//		Repository repository = new FileRepository(localpath + "/.git");
//		try (Git git = new Git(repository)) {
//			git.branchCreate().setName(branchName).setUpstreamMode(SetupUpstreamMode.TRACK).setStartPoint(repository.resolve("origin/" + branchName).name())
//			        .call();
//		} catch (RefAlreadyExistsException e) {
//		}
//		return true;
//	}
//	/**
//	 * checkout切换分支
//	 */
//	public static String checkoutRepository(String localpath, String tagname) throws IOException {
//		Repository repository = new FileRepository(localpath + "/.git");
//		try (Git git = new Git(repository);) {
//			String branch = repository.getBranch();
//			if(branch == null || !branch.equals(tagname)) {
//				git.checkout().setName(tagname).call();
//			}
////			if(branch != null && branch.equals(tagname)) {
////				git.pull().call();
////			}else {
////				git.checkout().setName(tagname).call();
////			}
//		} catch (RefNotFoundException e) {
//			log.error(e);
//			return "检出标签" + tagname + "失败";
//		} catch (InvalidRefNameException e) {
//			log.error(e);
//			return "检出标签" + tagname + "失败";
//		} catch (CheckoutConflictException e) {
//			log.error(e);
//			return "检出标签" + tagname + "失败";
//		} catch (GitAPIException e) {
//			log.error(e);
//			return "检出标签" + tagname + "失败";
//		}
//
//		return "success";
//	}
//
//	/**
//	 * 清理仓库
//	 * 
//	 * @param localpath
//	 *            要删除的目录的文件路径
//	 * @return 目录删除成功返回true，否则返回false
//	 */
//	public static boolean deleteDirectory(String localpath) {
//
//		// 如果localpath不以文件分隔符结尾，自动添加文件分隔符
//		if (!localpath.endsWith(File.separator)) {
//			localpath = localpath + File.separator;
//		}
//		File dirFile = new File(localpath);
//		// 如果dir对应的文件不存在，或者不是一个目录，则退出
//		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
//			log.info("目录：" + localpath + "不存在！");
//			return true;
//		}
//		boolean flag = true;
//		// 删除文件夹中的所有文件包括子目录
//		File[] files = dirFile.listFiles();
//		for (int i = 0; i < files.length; i++) {
//			// 删除子文件
//			if (files[i].isFile()) {
//				flag = GitUtils.deleteFile(files[i].getAbsolutePath());
//				if (!flag) {
//					break;
//				}
//
//			}
//			// 删除子目录
//			else if (files[i].isDirectory()) {
//				if (".git".equals(files[i].getName())) {
//					continue;
//				}
//				flag = GitUtils.deleteDirectory(files[i].getAbsolutePath());
//				if (!flag) {
//					break;
//				}
//			}
//		}
//		if (!flag) {
//			log.info("删除目录失败！");
//			return false;
//		}
//		if (dirFile.delete()) {
//			log.info("删除目录：" + localpath + "成功! ");
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * 删除单个文件
//	 * 
//	 * @param fileName
//	 *            要删除的文件的文件名
//	 * @return 单个文件删除成功返回true，否则返回false
//	 */
//	public static boolean deleteFile(String fileName) {
//		File file = new File(fileName);
//		if (file.exists() && file.isFile()) {
//			if (file.delete()) {
//				log.info("删除单个文件" + fileName + "成功！");
//				return true;
//			} else {
//				log.info("删除单个文件" + fileName + "失败！");
//				return false;
//			}
//		} else {
//			log.info("删除单个文件失败：" + fileName + "不存在！");
//			return false;
//		}
//	}
//
//	/**
//	 * 拷贝文件
//	 * 
//	 * @param sourceFile
//	 *            原文件
//	 * @param targetFile
//	 *            目标文件
//	 * @throws IOException
//	 */
//	public static void copyFile(File sourceFile, File targetFile) throws IOException {
//		if (!sourceFile.canRead()) {
//			log.info("源文件" + sourceFile.getAbsolutePath() + "不可读，无法复制！");
//			return;
//		} else {
//			// 使用FileInputStream打开一个文件输入流
//			FileInputStream fis = new FileInputStream(sourceFile);
//			// 使用FileOutputStream打开一个文件输出流
//			FileOutputStream fos = new FileOutputStream(targetFile);
//			// 得到文件输入流的通道
//			FileChannel ifc = fis.getChannel();
//			// 得到文件输出流的通道
//			FileChannel ofc = fos.getChannel();
//			// 分配一个字节缓冲区，大小为1024
//			ByteBuffer buffer = ByteBuffer.allocate(1024);
//			while (true) {
//				// 清空缓冲区，使其处于可接受字节状态
//				buffer.clear();
//				// 从文件输入流通道里读取数据，大小取决于缓冲区大小，以及文件剩余字节大小
//				int i = ifc.read(buffer);
//				// 如果返回值为-1表示已读取完毕
//				if (i == -1) {
//					break;
//				}
//				// 反转缓冲区，使其处于可写入通道状态
//				buffer.flip();
//				// 把缓冲区数据写入文件输出流通道
//				ofc.write(buffer);
//			}
//			fis.close();
//			fos.close();
//		}
//	}
//
//	/**
//	 * 相对较快的拷贝方法
//	 * 
//	 * @author Yang Chunhai
//	 * @param sourceFile
//	 * @param targetFile
//	 */
//	public static void fileChannelCopy(File sourceFile, File targetFile) {
//		FileInputStream fi = null;
//		FileOutputStream fo = null;
//		FileChannel in = null;
//		FileChannel out = null;
//		try {
//			fi = new FileInputStream(sourceFile);
//			fo = new FileOutputStream(targetFile);
//			in = fi.getChannel();// 得到对应的文件通道
//			out = fo.getChannel();// 得到对应的文件通道
//			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				fi.close();
//				in.close();
//				fo.close();
//				out.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * @author Yang Chunhai
//	 * @param sourcePathString
//	 *            原文件夹
//	 * @param targetPathString
//	 *            目标文件夹
//	 * @throws IOException
//	 */
//	public static void copyDirectory(String sourcePathString, String targetPathString) {
//		if (!new File(sourcePathString).canRead()) {
//			log.info("源文件夹" + sourcePathString + "不可读，无法复制！");
//			return;
//		} else {
//			(new File(targetPathString)).mkdirs();
//			log.info("开始复制文件夹" + sourcePathString + "到" + targetPathString);
//			File[] files = new File(sourcePathString).listFiles();
//			for (int i = 0; i < files.length; i++) {
//				if (files[i].isFile()) {
//					fileChannelCopy(new File(sourcePathString + File.separator + files[i].getName()),
//					        new File(targetPathString + File.separator + files[i].getName()));
//				} else if (files[i].isDirectory()) {
//					if (".git".equals(files[i].getName())) {
//						continue;
//					}
//					copyDirectory(sourcePathString + File.separator + files[i].getName(), targetPathString + File.separator + files[i].getName());
//				}
//			}
//			log.info("复制文件夹" + sourcePathString + "到" + targetPathString + "结束");
//		}
//	}
//
//	/**
//	 * 返回两个分支之间
//	 * 
//	 * @author Yang Chunhai
//	 * @param repository
//	 * @param ref
//	 * @return
//	 * @throws IOException
//	 */
//	public static AbstractTreeIterator prepareTreeParser(Repository repository, String ref) throws IOException {
//		// from the commit we can build the tree which allows us to construct the
//		// TreeParser
//		Ref head = repository.exactRef(ref);
//		try (RevWalk walk = new RevWalk(repository)) {
//			RevCommit commit = walk.parseCommit(head.getObjectId());
//			RevTree tree = walk.parseTree(commit.getTree().getId());
//
//			CanonicalTreeParser treeParser = new CanonicalTreeParser();
//			try (ObjectReader reader = repository.newObjectReader()) {
//				treeParser.reset(reader, tree.getId());
//			}
//			walk.dispose();
//			return treeParser;
//		}
//	}
//
//	/**
//	 * 获取最近一次提交Id
//	 * 
//	 * @author Yang Chunhai
//	 * @param localpath
//	 * @return
//	 * @throws Exception
//	 */
//	public static String getCommitId(String localpath) throws Exception {
//		Repository repository = new FileRepository(localpath + "/.git");
//		String commitId = "";
//		try (Git git = new Git(repository);) {
//			commitId = git.revert().call().getName();
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//		return commitId;
//	}
//
//	public static void dropBranch(String localpath, String branchName) {
//		try (Git git = new Git(new FileRepository(localpath + "/.git"));) {
//			git.branchDelete().setForce(true).setBranchNames(branchName).call();
//		} catch (Exception e) {
//			log.error("删除本地分支【" + branchName + "】失败: " + e.getMessage());
//			throw new ApplicationContextException(e.getMessage());
//		}
//	}
//	
//	public static boolean dropRemoteBranch(String localpath, String branchName, String username, String password) {
//		UsernamePasswordCredentialsProvider credential = new UsernamePasswordCredentialsProvider(username, password);
//		try (Git git = new Git(new FileRepository(localpath + "/.git"));) {
//			git.push().setCredentialsProvider(credential).setRemote("origin").add(":refs/heads/"+branchName).call();
//		} catch (Exception e) {
//			log.error("删除远程分支【" + branchName + "】失败: " + e.getMessage());
//			throw new ApplicationContextException(e.getMessage());
//		}
//		return true;
//	}
//	
//	public static boolean mergeBranch(String localpath, String fromBranch, String toBranch, String userName,String password) throws IOException {
//		UsernamePasswordCredentialsProvider credential = new UsernamePasswordCredentialsProvider(userName, password);
//		FileRepository repo = new FileRepository(localpath + "/.git");
//		try (Git git = new Git(repo);) {
//			git.checkout().setName(toBranch).call();
//			git.merge().setFastForward(FastForwardMode.FF).include(repo.resolve("origin/"+fromBranch)).call();
//			git.push().setCredentialsProvider(credential).call();
//		} catch (Exception e) {
//			log.error("合并分支【" + fromBranch + "】失败: " + e.getMessage());
//			throw new ApplicationContextException(e.getMessage());
//		}
//		return true;
//	}
//	
//}
