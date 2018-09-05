package com.bee.devops.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class ReadServerLogTest {

	// 从配置文件中加载用户名和密码
	static String userName = "root";
	static String password = "1q2w3e4r..";
	// 从配置文件中加载服务器信息
	static Map<Object, Object> serversProp = new HashMap<>();

	public static void main(String[] args) throws FileNotFoundException, IOException, JSchException, SftpException {
		serversProp.put("192.168.0.208", "192.168.0.208");
		readLogCurrently();
	}

	// 连接服务器
	private static void iteraterServer() throws FileNotFoundException, IOException, JSchException {
		// TODO Auto-generated method stub
		// 读取服务器磁盘空间信息命令，读取使用率大于 90%的
		String cmd = "df -h ";
		JSch sshSingleton = new JSch();
		for (Map.Entry<Object, Object> serverProp : serversProp.entrySet()) {
			String name = (String) serverProp.getKey();
			String server = (String) serverProp.getValue();

			System.out.println("Start working on: " + name);
			Session session = sshSingleton.getSession(userName, server);
			session.setPassword(password);
			Properties config = new Properties();
			// 设置 SSH 连接时不进行公钥确认
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			// 打开命令执行管道
			ChannelExec channel = (ChannelExec) session.openChannel("exec");
			BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
			channel.setCommand(cmd);
			channel.connect();
			// 读取命令输出信息
			String msg;
			while ((msg = in.readLine()) != null) {
				System.out.println(msg);
			}

			channel.disconnect();
			session.disconnect();
		}
	}

	// 读取服务器日志
	private static void readLog() throws JSchException, IOException {
		// TODO Auto-generated method stub
		// 日志 1 中特殊字符信息
		String uniqeStr = "micro-tj-api";
		// 日志 1 文件位置
		String serverlogFilePath = "/home/deploy/E00/P00/E00P00M00/G0/LOG/micro-tj-api/micro-tj-api/lg_info.log";
		// 日志 2 中特殊字符信息
		String jobIdExpr = "id=[0-9]+";
		// 日志 2 文件位置
		String joblogFilePath = "/opt/server/log/job.log";

		JSch sshSingleton = new JSch();

		Pattern p = Pattern.compile(jobIdExpr);
		for (Map.Entry<Object, Object> serverProp : serversProp.entrySet()) {
			String name = (String) serverProp.getKey();
			String server = (String) serverProp.getValue();

			Session session = sshSingleton.getSession(userName, server);
			session.setPassword(password);

			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			// 打开执行管道
			ChannelExec channel = (ChannelExec) session.openChannel("exec");
			BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
			// 设置命令，从日志 1 中找出关键字符信息
			channel.setCommand("cat " + serverlogFilePath + " |grep -w " + uniqeStr);
			channel.connect();
			String msg;
			String jobId = null;
			while ((msg = in.readLine()) != null) {
				Matcher m = p.matcher(msg);
				if (m.find()) {
					jobId = m.group();
					break;
				}
			}
			// 关闭第一个执行管道
			channel.disconnect();

			if (jobId != null) {
				// 日志 1 中发现关键字符信息后，在同一服务器中日志文件 2 中继续查找
				System.out.println("found log in jobServer: " + msg);

				// 另外再打开一个新的执行管道
				// channel = (ChannelExec) session.openChannel("exec");
				// in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
				// String cmd = "cat " + joblogFilePath + " |grep -A 10 " + jobId;
				//
				// channel.setCommand(cmd);
				// channel.connect();
				// // 输出需要查找的日志信息
				// while ((msg = in.readLine()) != null) {
				// System.out.println(msg);
				// }
				//
				// channel.disconnect();
			}

			session.disconnect();

			if (jobId != null) {
				// 已经在这台服务器中找到日志，不需要继续去其他服务器中查找了
				break;
			}
		}
	}

	// 上传下载
	private static void upAndDown() throws JSchException, SftpException {
		String serverFile = "/home/deploy/E00/P00/E00P00M00/G0/LOG/micro-tj-api/micro-tj-api/lg_info.log";
		String localFolder = "E:/tmp/";

		String localFile = "C:/tmp/user.xml";
		String serverFolder = "/tmp/";

		JSch sshSingleton = new JSch();

		String server = "192.168.0.208";
		// 设置端口
		int port = 22;
		Session session = sshSingleton.getSession(userName, server, port);
		session.setPassword(password);

		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		// 打开 ftp 管道
		Channel channel = session.openChannel("sftp");
		channel.connect();
		ChannelSftp c = (ChannelSftp) channel;
		// 转到根目录，便于使用绝对路径来进行文件传输
		c.cd("/");

		File file = new File(localFolder);
		if (file.mkdirs()) {
			// 从服务器上下载日志文件 1.log 到本地目录 C:/tmp
			c.get(serverFile, localFolder);

		}

		// 上传配置文件 user.xml 到服务器上，如果服务器上已经存在该文件，则覆盖它
		// c.put(localFile, serverFolder, ChannelSftp.OVERWRITE);

		session.disconnect();
	}

	// 实时读取服务器日志
	private static void readLogCurrently() throws JSchException, IOException {
		// 日志 1 文件位置
		String serverlogFilePath = "/home/logs/lg_info.log";

		JSch sshSingleton = new JSch();

		String server = "192.168.2.226";

		Session session = sshSingleton.getSession(userName, server);
		session.setPassword(password);

		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();

		// 打开执行管道
		ChannelExec channel = (ChannelExec) session.openChannel("exec");
		BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
		// 设置命令，从日志 1 中找出关键字符信息
		channel.setCommand("tail -f " + serverlogFilePath);
		channel.connect();
		String msg;
		while ((msg = in.readLine()) != null) {
			System.out.println(msg);
		}
		// 关闭第一个执行管道
		channel.disconnect();

		session.disconnect();
	}
}
