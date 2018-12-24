package com.ping.erp.common.cache.impl;

import java.io.InputStream;

import com.ping.erp.common.cache.FileCache;

/**
 * 文件缓存接口实现
 *
 * @version 1.0.3-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public class FileCacheImpl implements FileCache {

	/**
	 * 文件名称
	 */
	private String name;
	/**
	 * 原始文件名称
	 */
	private String originalFilename;
	/**
	 * 文件类型
	 */
	private String contentType;
	/**
	 * 文件大小
	 */
	private long size;
	/**
	 * 文件字节
	 */
	private byte[] bytes;
	/**
	 * 文件输入流
	 */
	private InputStream inputStream;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
