package com.ping.erp.common.result.impl;

import com.ping.erp.common.result.SimpleResult;

/**
 * 简单结果接口实现
 *
 * @version 1.0.3-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public class SimpleResultImpl implements SimpleResult {

	/**
	 * 结果代码
	 */
	private int code;
	/**
	 * 结果信息
	 */
	private String message;
	/**
	 * 链接
	 */
	private String url;
	/**
	 * 日期
	 */
	private long date;

	public SimpleResultImpl() {
		super();
	}

	public SimpleResultImpl(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public SimpleResultImpl setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public SimpleResultImpl setMessage(String message) {
		this.message = message;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public SimpleResultImpl setUrl(String url) {
		this.url = url;
		return this;
	}

	public long getDate() {
		return date;
	}

	public SimpleResultImpl setDate(long date) {
		this.date = date;
		return this;
	}

}
