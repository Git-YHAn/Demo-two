package com.bee.devops.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

public class AppLogsDownloadTest {
	public static class FileTree {
		private String resourceName;
		private String url;
		private int type;
		private List<FileTree> children;

		public FileTree(String resourceName, String url, int type, List<FileTree> children) {
			super();
			this.resourceName = resourceName;
			this.url = url;
			this.type = type;
			this.children = children;
		}

		public FileTree() {
			super();
		}

		public String getResourceName() {
			return resourceName;
		}

		public void setResourceName(String resourceName) {
			this.resourceName = resourceName;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public List<FileTree> getChildren() {
			return children;
		}

		public void setChildren(List<FileTree> children) {
			this.children = children;
		}

		@Override
		public String toString() {
			return "FileTree [resourceName=" + resourceName + ", url=" + url + ", type=" + type + ", children=" + children + "]";
		}

	}

	public static void main(String[] args) {
		File directory = FileUtils.getFile("E:\\demo");
		List<FileTree> fileTree = new ArrayList<>();
		List<FileTree> recursiveQueryFiles = recursiveQueryFiles(fileTree, directory);

		System.out.println(recursiveQueryFiles);
		// queryFiles(directory);
	}

	public static void queryFiles(File directory) {
		Collection<File> listFiles = FileUtils.listFilesAndDirs(directory, FileFilterUtils.trueFileFilter(), FileFilterUtils.trueFileFilter());
		for (File file : listFiles) {
			System.out.println(file);
		}
	}

	public static List<FileTree> recursiveQueryFiles(List<FileTree> fileTree, File directory) {
		File[] listFiles = directory.listFiles();
		for (File file2 : listFiles) {
			List<FileTree> children = new ArrayList<>();
			fileTree.add(new AppLogsDownloadTest.FileTree(file2.getName(), file2.getAbsolutePath(), (file2.isDirectory() ? 0 : 1), children));
			if (file2.isDirectory()) {
				recursiveQueryFiles(children, file2);
			}
		}
		return fileTree;
	}
}
