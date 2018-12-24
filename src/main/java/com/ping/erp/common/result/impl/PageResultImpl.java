package com.ping.erp.common.result.impl;

import java.util.List;

import com.ping.erp.common.result.PageResult;

/**
 * 分页结果接口实现
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public class PageResultImpl<T> implements PageResult<T> {

	/**
	 * 用于layui
	 */
	private int code = 0;
	/**
	 * 数据总数
	 */
	private long count;
	/**
	 * 数据信息
	 */
	private List<T> data;

	public PageResultImpl() {
		super();
	}

	public PageResultImpl(long count, List<T> data) {
		super();
		this.count = count;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
