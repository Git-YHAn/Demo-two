package com.bee.devops.admin.common.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextException;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 从classpath路径下载文件工具类
 *
 * @author wanghuajie
 * @date 2018/7/13 15:20
 */
public class DownloadUtils {
    private final static Logger logger = Logger.getLogger(DownloadUtils.class);

    /**
     * 下载文件
     *
     * @param path     下载文件的路径
     * @param fileName 指定下载之后的文件名称,可不传,不传的时候直接获取被下载的文件名
     * @param response 响应
     */
    public static void download(String path, String fileName, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            ClassPathResource resource = new ClassPathResource(path);
            inputStream = resource.getInputStream();
            if (StringUtils.isEmpty(fileName)) {
                fileName = resource.getFilename();
            }

            String contentType;
            if (fileName.endsWith(".doc")) {
                contentType = "application/msword";
            } else if (fileName.endsWith(".xlsx")) {
                contentType = "application/vnd.ms-excel";
            } else {
                contentType = "application/octet-stream";
            }
            contentType += ";charset=utf-8";
            fileName = new String(fileName.getBytes("gbk"), "iso-8859-1");
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            logger.error("文件下载异常", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }
	public static void download(File file, HttpServletResponse response) {
		try (FileInputStream in = new FileInputStream(file); OutputStream outputStream = response.getOutputStream();) {
			if (!file.exists()) {
				throw new ApplicationContextException("您要下载的资源已被删除！！");
			}
			String fileName = file.getName();

			fileName = new String(fileName.getBytes("gbk"), "iso-8859-1");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

			IOUtils.copy(in, outputStream);
		} catch (IOException e) {
			logger.error("文件下载异常", e);
		}
	}
}
