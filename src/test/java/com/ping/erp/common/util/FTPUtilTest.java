package com.ping.erp.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.junit.Before;
import org.junit.Test;

public class FTPUtilTest {

	@Before
	public void before() {
		FTPUtil ftp = new FTPUtil();
		ftp.setHostname("127.0.0.1");
		ftp.setPort(21);
		ftp.setUsername("ftp_ping_erp");
		ftp.setPassword("ftp_ping_erp");
	}

	@Test
	public void testDoUpload() throws SocketException, IOException {
		String fileDir = "/";
		String fileName = "测试文件.ico";
		InputStream fileInput = new FileInputStream("E:" + File.separator + "Test Files" + File.separator + "测试文件.ico");
		FTPUtil.doUpload(fileDir, fileName, fileInput);
	}

	@Test
	public void testDoDownload() throws SocketException, IOException {
		String fileDir = "/";
		String fileName = "测试文件.ico";
		OutputStream fileOutput = new FileOutputStream("E:" + File.separator + "Test Files" + File.separator + "测试文件1.ico");
		FTPUtil.doDownload(fileDir, fileName, fileOutput);
	}

	@Test
	public void testDoDownload1() throws SocketException, IOException {
		String fileDir = "/";
		String fileName = "测试文件.ico";
		FTPUtil.doDownload(fileDir, fileName);
	}

	@Test
	public void testDoDelete() throws SocketException, IOException {
		String fileDir = "/";
		String fileName = "测试文件.ico";
		FTPUtil.doDelete(fileDir, fileName);
	}

}
