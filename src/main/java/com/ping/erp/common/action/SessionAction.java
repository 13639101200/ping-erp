package com.ping.erp.common.action;

import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 会话操作接口
 *
 * @version 1.0.3-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface SessionAction {

	BaseUser getUser();

	void setUser(BaseUser user);

	BaseCompany getCompany();

	void setCompany(BaseCompany company);

	void destroy();

}
