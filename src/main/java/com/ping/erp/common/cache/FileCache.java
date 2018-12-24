package com.ping.erp.common.cache;

import java.io.InputStream;

/**
 * 文件缓存接口
 *
 * @version 1.0.3-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface FileCache {

	public String getName();

	public void setName(String name);

	public String getOriginalFilename();

	public void setOriginalFilename(String originalFilename);

	public String getContentType();

	public void setContentType(String contentType);

	public long getSize();

	public void setSize(long size);

	public byte[] getBytes();

	public void setBytes(byte[] bytes);

	public InputStream getInputStream();

	public void setInputStream(InputStream inputStream);

}
