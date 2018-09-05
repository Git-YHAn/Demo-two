package com.bee.devops.admin.common.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

/**
 * 执行shell语句封装类
 * 
 * @author Administrator
 *
 */
public class Shell {

	private final static Logger log = Logger.getLogger(Shell.class);

	// 远程主机的ip地址
	private String ip;
	// 远程主机登录用户名
	private String username;
	// 远程主机的登录密码
	private String password;
	// 设置ssh连接的远程端口
	public Integer port;
	// 保存输出内容的容器
	private Vector<String> stdout;
	// 保存需要的信息的容器
	private Map<String, String> map;

	private Session session;

	private final static Integer DEFAULT_SSH_PORT = 22;// ssh默认端口

	private final static int sessionTimeout = 1000 * 60 * 5;
	// 获取服务器信息shell代码
	final public String getServiceMessage = "kernel_version=$(uname -r) \n echo -e \"Kernel_version:\\n${kernel_version}\\n${Line}\" \n OS_version=$(uname -m) \n echo -e \"OS_version:\\n${OS_version}\\n${Line}\" \n if [[ -f /usr/bin/lsb_release ]]; then \n OS=$(/usr/bin/lsb_release -a |grep Description |awk -F : \'{print $2}\' |sed \'s/^[ \\t]*//g\') \n else \n OS=$(cat /etc/issue |sed -n \'1p\') \n fi \n  echo -e \"${Line}\\nOS:\\n${OS}\\n${Line}\" \n Disk=$(fdisk -l |grep \'Disk\' |awk -F , \'{print $1}\' | sed \'s/Disk identifier.*//g\' | sed \'/^$/d\') \n echo -e \"Amount Of Disks:\\n${Disk}\\n${Line}\" \n Total=$(cat /proc/meminfo |grep \'MemTotal\' |awk -F : \'{print $2}\' |sed \'s/^[ \\t]*//g\') \n echo -e \"Total Memory:\\n${Total}\\n${Line}\" \n CPU=$(grep \'model name\' /proc/cpuinfo |uniq |awk -F : \'{print $2}\' |sed \'s/^[ \\t]*//g\' |sed \'s/ \\+/ /g\')  \n echo -e \"CPU model:\\n${CPU}\\n${Line}\"";

	/**
	 * 初始化登录信息
	 * 
	 * @param ip
	 * @param username
	 * @param password
	 * @param port
	 */
	public Shell(final String ip, final String username, final String password, final Integer port) {
		this.ip = ip;
		this.username = username;
		this.password = password;
		this.port = port == null ? DEFAULT_SSH_PORT : port;
	}

	public Session initSession() {
		JSch jsch = new JSch();
		try {
			session = jsch.getSession(username, ip, port);
			session.setPassword(password);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect(sessionTimeout);
			return session;
		} catch (JSchException e) {
			return null;
		}
	}

	public Channel exec(String cmd) throws Exception {
		// 打开通道，设置通道类型，和执行的命令
		ChannelExec channel = (ChannelExec) session.openChannel("exec");
		channel.setInputStream(null);
		channel.setCommand(cmd);
		// 链接.等同于执行
		channel.connect();
		return channel;
	}

	public boolean checkResult(Channel ch) throws Exception {
		ChannelExec channel = (ChannelExec) ch;
		// 打开通道，设置通道类型，和执行的命令
		channel.setInputStream(null);
		channel.setCommand("echo $? ");
		// 链接.等同于执行
		channel.connect();
		int resultCode = 0;
		try (InputStream inputStream = channel.getInputStream();) {
			resultCode = Integer.parseInt(IOUtils.toString(inputStream));
		}
		return resultCode == 0;
	}

	public Channel scp(String localFile, String remoteFile) throws Exception {
		// 打开通道，设置通道类型，和执行的命令
		ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
		channel.connect();
		// 列出服务器指定的文件列表
		channel.cd("/");
		try {
			createDir(remoteFile, channel);
		} catch (Exception e) {
		}
		channel.put(new FileInputStream(localFile), remoteFile);
		channel.disconnect();
		try {
			ChannelExec chmod = chmod(remoteFile);
			chmod.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channel;
	}

	/**
	 * 重载一个远程复制函数
	 */
	public Channel scp(InputStream inputStream, String remoteFile) throws Exception {
		// 打开通道，设置通道类型，和执行的命令
		ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
		channel.connect();
		// 列出服务器指定的文件列表
		channel.cd("/");
		try {
			createDir(remoteFile, channel);
		} catch (Exception e) {
		}
		channel.put(inputStream, remoteFile);
		channel.disconnect();
		try {
			ChannelExec chmod = chmod(remoteFile);
			chmod.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channel;
	}

	public ChannelExec chmod(String filePath) throws JSchException {
		ChannelExec channel = (ChannelExec) session.openChannel("exec");
		channel.setInputStream(null);
		channel.setCommand("chmod 755 " + filePath);
		channel.connect();
		return channel;
	}

	/**
	 * 创建一个文件目录
	 * 
	 * @throws SftpException
	 */
	public void createDir(String createpath, ChannelSftp sftp) throws SftpException {
		if (isDirExist(createpath, sftp)) {
			sftp.cd(createpath);
			return;
		}
		String pathArry[] = createpath.split("/");
		StringBuffer filePath = new StringBuffer("/");
		for (String path : pathArry) {
			if (path.equals("") || path.contains(".")) {
				continue;
			}
			filePath.append(path + "/");
			if (isDirExist(filePath.toString(), sftp)) {
				sftp.cd(filePath.toString());
			} else {
				// 建立目录
				sftp.mkdir(filePath.toString());
				// 进入并设置为当前目录
				sftp.cd(filePath.toString());
			}
		}
		sftp.cd(createpath);
	}

	/**
	 * 判断目录是否存在
	 * 
	 * @param sftp
	 */
	public boolean isDirExist(String directory, ChannelSftp sftp) {
		boolean isDirExistFlag = false;
		try {
			SftpATTRS sftpATTRS = sftp.lstat(directory);
			isDirExistFlag = true;
			return sftpATTRS.isDir();
		} catch (Exception e) {
			if (e.getMessage().toLowerCase().equals("no such file")) {
				isDirExistFlag = false;
			}
		}
		return isDirExistFlag;
	}

	public List<String> getExecResult(Channel channel) throws IOException {
		List<String> result = new LinkedList<>();
		if (channel != null && channel.isConnected()) {
			BufferedReader input = new BufferedReader(new InputStreamReader(channel.getInputStream()));
			// 接收远程服务器执行命令的结果
			String line;
			while ((line = input.readLine()) != null) {
				result.add(line);
			}
			input.close();
			// 关闭通道
			channel.disconnect();
		}
		return result;
	}

	public void closeChannel(Channel channel) {
		if (channel != null) {
			channel.disconnect();
		}
	}

	public void closeSession() {
		if (session != null) {
			session.disconnect();
		}
	}

	/**
	 * 执行单条shell命令后退出
	 * 
	 * @param command
	 *            shell 语句
	 * @return
	 * @throws JSchException
	 * @throws IOException
	 */
	public int execute(final String command) throws com.jcraft.jsch.JSchException, IOException {
		// 初始化保存输出内容的容器
		stdout = new Vector<>();
		int returnCode = 0;
		JSch jsch = new JSch();
		// 创建session并且打开连接，因为创建session之后要主动打开连接
		Session session = jsch.getSession(username, ip, port);
		session.setPassword(password);
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();

		// 打开通道，设置通道类型，和执行的命令
		Channel channel = session.openChannel("exec");
		ChannelExec channelExec = (ChannelExec) channel;
		channelExec.setCommand(command);

		channelExec.setInputStream(null);
		BufferedReader input = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));

		// 链接.等同于执行
		channelExec.connect();

		// 接收远程服务器执行命令的结果
		String line;
		while ((line = input.readLine()) != null) {
			stdout.add(line);
		}
		input.close();

		// 得到returnCode
		if (channelExec.isClosed()) {
			returnCode = channelExec.getExitStatus();
		}

		// 关闭通道
		channelExec.disconnect();
		// 关闭session
		session.disconnect();
		return returnCode;
	}

	/**
	 * 返回执行结果
	 * 
	 * @return
	 */
	public List<String> getResult() {
		return stdout;
	}

	/**
	 * 获取服务器参数
	 * 
	 * @return
	 */
	public Map<String, String> getStandardOutput() {
		map = new HashMap<>();
		map.put("操作系统", stdout.get(stdout.indexOf("Kernel_version:") + 1));
		map.put("内存信息", stdout.get(stdout.indexOf("Amount Of Disks:") + 1));
		map.put("磁盘大小", stdout.get(stdout.indexOf("Total Memory:") + 1));
		map.put("CPU信息", stdout.get(stdout.indexOf("CPU model:") + 1));
		return map;
	}

	public Session getSession() {
		return session;
	}

	public int getExitStatus(Channel chs) throws IOException {
		while (true) {
			if (chs.isClosed()) {
				return chs.getExitStatus();
			}
			try {
				Thread.sleep(200);
			} catch (Exception ee) {
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Shell shell = new Shell("192.168.100.80", "root", "1q2w3e4r..", 22);
		Session session = shell.initSession();
		String cmd =" kubectl delete pod -l app=lbl-e00p00g0-10004-api --namespace=e00p00g0";
		Channel channel = shell.exec(cmd);
		int result = shell.getExitStatus(channel);
		session.disconnect();
		System.out.println(result);
	}
}
