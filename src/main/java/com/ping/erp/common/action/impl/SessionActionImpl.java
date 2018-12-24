package com.ping.erp.common.action.impl;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ping.erp.common.action.SessionAction;
import com.ping.erp.system.company.dao.BaseCompanyDao;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.dao.BaseUserDao;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 会话操作接口实现
 *
 * @version 1.0.3-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Component
@Transactional
public class SessionActionImpl implements SessionAction {

	@Autowired
	private HttpSession session;
	@Autowired
	private BaseUserDao userDao;
	@Autowired
	private BaseCompanyDao companyDao;

	@Override
	public BaseUser getUser() {
		BaseUser user = (BaseUser) session.getAttribute("user");
		if (user == null) {
			return null;
		}
		return userDao.findOne(user.getUserId());
	}

	@Override
	public void setUser(BaseUser user) {
		session.setAttribute("user", user);
	}

	@Override
	public BaseCompany getCompany() {
		BaseCompany company = (BaseCompany) session.getAttribute("company");
		if (company == null) {
			return null;
		}
		return companyDao.findOne(company.getCompanyId());
	}

	@Override
	public void setCompany(BaseCompany company) {
		session.setAttribute("company", company);
	}

	@Override
	public void destroy() {
		session.invalidate();
	}

}
