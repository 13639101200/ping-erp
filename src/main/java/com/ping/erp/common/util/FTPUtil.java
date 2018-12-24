package com.ping.erp.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * FTP工具类
 *
 * @version 1.1.5-RELEASE
 * @time 2018-12-04
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Component
public class FTPUtil {

	/**
	 * 服务地址
	 */
	private static String hostname;
	/**
	 * 服务端口
	 */
	private static int port;
	/**
	 * 用户账号
	 */
	private static String username;
	/**
	 * 用户密码
	 */
	private static String password;
	/**
	 * 客户端
	 */
	private static FTPClient client;

	/**
	 * 初始化客户端
	 * 
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
	private static boolean initClient() throws SocketException, IOException {
		if (client == null) {
			client = new FTPClient();
		}
		if (!client.isConnected()) {
			client.setControlEncoding("UTF-8");
			client.connect(hostname, port);
			client.login(username, password);
		}
		if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
			client.disconnect();
			client = null;
			return false;
		}
		return true;
	}

	/**
	 * 创建目录
	 * 
	 * @param directory
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
	private static boolean createDirectory(String directory) throws SocketException, IOException {
		if (!initClient()) {
			return false;
		}
		if (!client.changeWorkingDirectory(directory)) {
			if (!directory.startsWith("/")) {
				directory = "/" + directory;
			}
			if (!directory.endsWith("/")) {
				directory = directory + "/";
			}
			int endIndex = 0;
			while (true) {
				endIndex = directory.indexOf("/", endIndex) + 1;
				String path = directory.substring(0, endIndex);
				if (!client.changeWorkingDirectory(path)) {
					client.makeDirectory(path);
				}

				if (endIndex >= directory.length()) {
					break;
				}
			}
		}
		return true;
	}

	/**
	 * 上传文件
	 * 
	 * @param fileDir
	 * @param fileName
	 * @param fileInput
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
	public static boolean doUpload(String fileDir, String fileName, InputStream fileInput) throws SocketException, IOException {
		if (!initClient()) {
			return false;
		}
		client.setFileType(FTPClient.BINARY_FILE_TYPE);
		createDirectory(fileDir);
		client.changeWorkingDirectory(fileDir);
		client.storeFile(fileName, fileInput);
		fileInput.close();
		return true;
	}

	/**
	 * 下载文件
	 * 
	 * @param fileDir
	 * @param fileName
	 * @param fileOutput
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
	public static boolean doDownload(String fileDir, String fileName, OutputStream fileOutput) throws SocketException, IOException {
		if (!initClient()) {
			return false;
		}
		client.changeWorkingDirectory(fileDir);
		client.retrieveFile(fileName, fileOutput);
		return true;
	}

	/**
	 * 下载文件
	 * 
	 * @param fileDir
	 * @param fileName
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
	public static InputStream doDownload(String fileDir, String fileName) throws SocketException, IOException {
		initClient();
		client.changeWorkingDirectory(fileDir);
		return client.retrieveFileStream(fileName);
	}

	/**
	 * 删除文件
	 * 
	 * @param fileDir
	 * @param fileName
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
	public static boolean doDelete(String fileDir, String fileName) throws SocketException, IOException {
		if (!initClient()) {
			return false;
		}
		client.changeWorkingDirectory(fileDir);
		client.dele(fileName);
		return true;
	}

	@Value("${define.ftp.server.hostname}")
	public void setHostname(String hostname) {
		FTPUtil.hostname = hostname;
	}

	@Value("${define.ftp.server.port}")
	public void setPort(int port) {
		FTPUtil.port = port;
	}

	@Value("${define.ftp.server.username}")
	public void setUsername(String username) {
		FTPUtil.username = username;
	}

	@Value("${define.ftp.server.password}")
	public void setPassword(String password) {
		FTPUtil.password = password;
	}

}
