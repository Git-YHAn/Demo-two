package com.bee.devops.admin.common.utils;

import ch.ethz.ssh2.*;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ssh工具类
 *
 * @author wanghuajie
 * @date 2018/7/17 17:37
 */
public class SshUtils {
    private final static Logger logger = Logger.getLogger(SshUtils.class);
    private static final int EXEC_RESULT_MAX_LENGTH = 1024 * 4;
    /**
     * 获取连接对象
     *
     * @param address  服务器地址
     * @param username 账号
     * @param password 密码
     * @param port     端口
     * @return
     * @throws IOException
     */
    public static Connection getConnection(String address, String username, String password, int port) throws IOException {
        Connection connection = new Connection(address, port);
        connection.connect();
        boolean isAuthenticated = connection.authenticateWithPassword(username, password);
        if (!isAuthenticated) {
            throw new IOException("Authentication failed.");
        }
        return connection;
    }

    /**
     * 使用ethz方式执行Linux命令
     * @param connection  连接对象
     * @param cmd         执行命令
     * @param execResult  执行结果输出
     * @param readTimeout 读取标准输出或错误输出最大超时时间，0 为无超时限制. 单位:毫秒
     * @return 退出状态码， 0 表示执行成功，其它值表示失败
     */
    public static int ethzExecute(Connection connection, String cmd, StringBuilder execResult, long readTimeout) {
        Session session = null;
        Integer exitStatus = 1;
        try {
            session = connection.openSession();
            session.execCommand(cmd);
            //获取标准输出
            getExecResult(cmd, execResult, readTimeout, session, ChannelCondition.STDOUT_DATA);
            session.waitForCondition(ChannelCondition.EXIT_STATUS, 500);
            exitStatus = session.getExitStatus();
            if (exitStatus != null && exitStatus != 0) {
                //获取错误输出
                getExecResult(cmd, execResult, readTimeout, session, ChannelCondition.STDERR_DATA);
            }
        } catch (IOException e) {
            String errStr = "Shell exec IOException: " + e.getMessage();
            execResult.append(errStr);
            logger.error(errStr);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        if (execResult.length() >= EXEC_RESULT_MAX_LENGTH) {
            String substring = execResult.toString().substring(0, execResult.indexOf("\n", 128) + 1);
            execResult.replace(0, execResult.length(), substring).append("too more...\r\n");
        }
        return exitStatus != null ? exitStatus : 1;
    }

    private static void getExecResult(String cmd, StringBuilder execResult, long readTimeout, Session session, int channelCondition) throws IOException {
        session.waitForCondition(channelCondition, 500);
        InputStream inputStream;
        if (channelCondition == ChannelCondition.STDERR_DATA) {
            inputStream = session.getStderr();
        } else {
            inputStream = session.getStdout();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(new StreamGobbler(inputStream)));
        String line;
        long endTime = System.currentTimeMillis() + readTimeout;
        while ((line = br.readLine()) != null) {
            execResult.append(line).append("\r\n");
            if (readTimeout > 0 && System.currentTimeMillis() > endTime) {
                String warn = "Read inputStream timeout and cmd: " + cmd;
                logger.warn(warn);
                execResult.append(warn);
                break;
            }
        }
    }

    public static String ethzScp(Connection connection, ClassPathResource resource, String remoteFileName, String remotePath) {
        StringBuilder result = new StringBuilder();
        SFTPv3Client sftPv3Client = null;
        try {
            sftPv3Client = new SFTPv3Client(connection);
            sftPv3Client.lstat(remotePath + "/" + remoteFileName);
        } catch (IOException ignored) {
            try {
                String mkdirCmd = "mkdir -p " + remotePath + ";chmod 755 " + remotePath;
                int exitStatus = ethzExecute(connection, mkdirCmd, result, 1000);
                if (exitStatus == 0) {
                    byte[] content = IOUtils.toByteArray(resource.getInputStream());
                    SCPClient scpClient = connection.createSCPClient();
                    scpClient.put(content, remoteFileName, remotePath, "0755");
                } else {
                    logger.error("mkdir " + remotePath + " error: " + result);
                }
            } catch (IOException e) {
                String errStr = "Shell Scp IOException: " + e.getMessage();
                result.append(errStr);
                logger.error(errStr);
            }
        } finally {
            if (sftPv3Client != null) {
                sftPv3Client.close();
            }
        }
        return result.toString();
    }

    /**
     * 获取执行结果,设置到全局map中
     *
     * @param key     唯一主键
     * @param session session
     * @throws IOException
     */
    public static void getResult(String key, Session session) throws Exception {
        InputStream stdout = new StreamGobbler(session.getStdout());
        BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
        StringBuilder sb = new StringBuilder();
        String line;
        GlobalContainer.SESSION_MAP.put(key, session);
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\r\n");
            GlobalContainer.MAP.put(key, sb.toString());
        }
        GlobalContainer.SESSION_MAP.remove(key);

        Integer exitStatus = session.getExitStatus();
        if (exitStatus != null && exitStatus != 0) {
            logger.warn("Shell execute error with error code: " + exitStatus);
            GlobalContainer.MAP.put(key, "exception: " + ExitStatusEnum.getExitDesc(exitStatus));
            return;
        }

        session.close();
    }

    enum ExitStatusEnum {
        /* 0 表示执行成功，非0 表示失败*/
        EX_OK(0, "Success exit status"),
        EX_FAILURE(1, "Failed unknown error"),
        EX_INCORRECT_USAGE(2, "Incorrect Usage"),
        EX_USAGE(64, "command line usage error"),
        EX_DATA_FORMAT_ERROR(65, "data format error"),
        EX_CANNOT_OPEN_INPUT(66, "cannot open input"),
        EX_NO_USER(67, "addressee unknown"),
        EX_NO_HOST(68, "host name unknown"),
        EX_UNAVAILABLE(69, "service unavailable"),
        EX_SOFTWARE(70, "internal software error"),
        EX_SYSTEM_ERROR(71, "system error (e.g., can't fork)"),
        EX_MISSING_OS_FILE(72, "critical OS file missing"),
        EX_CANT_CREATE(73, "can't create (user) output file"),
        EX_IO_ERROR(74, "input/output error"),
        EX_TEMP_FAILURE(75, "temp failure; user is invited to retry"),
        EX_PROTOCOL_ERROR(76, "remote error in protocol"),
        EX_NO_PERMISSION(77, "permission denied"),
        EX_CONFIG(78, "configuration error"),
        EX_NOT_AN_EXECUTABLE(126, "Not an executable"),
        EX_COMMAND_NOT_FOUND(127, "Command Not Found");

        private int exitCode;
        private String exitDesc;

        ExitStatusEnum(int exitCode, String exitDesc) {
            this.exitCode = exitCode;
            this.exitDesc = exitDesc;
        }

        public static String getExitDesc(int exitCode) {
            ExitStatusEnum[] enumConstants = ExitStatusEnum.class.getEnumConstants();
            for (ExitStatusEnum constant : enumConstants) {
                if (constant.exitCode == exitCode) {
                    return constant.exitDesc;
                }
            }
            return EX_FAILURE.exitDesc;
        }
    }
}
