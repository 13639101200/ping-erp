package com.ping.erp.common.result;

import com.ping.erp.common.result.impl.SimpleResultImpl;

/**
 * 简单结果接口
 *
 * @version 1.0.3-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface SimpleResult {

	int getCode();

	SimpleResultImpl setCode(int code);

	String getMessage();

	SimpleResultImpl setMessage(String message);

	String getUrl();

	SimpleResultImpl setUrl(String url);

	long getDate();

	SimpleResultImpl setDate(long date);

}
