package com.ping.erp.common.result;

import java.util.List;

/**
 * 分页结果接口
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface PageResult<T> {

	int getCode();

	void setCode(int code);

	long getCount();

	void setCount(long count);

	List<T> getData();

	void setData(List<T> data);

}
