package com.ping.erp.system.user.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.dao.UserLoginDao;
import com.ping.erp.system.user.domain.UserLogin;
import com.ping.erp.system.user.service.UserLoginService;

/**
 * 用户登录业务服务接口实现
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 10:26:27
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserLoginDao dao;

	public PageResult<UserLogin> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public UserLogin findOne(String id) {
		return dao.findOne(id);
	}

	public void save(UserLogin entity) {
		if (entity.getUserId() == null || "".equals(entity.getUserId())) {
			entity.setUserId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<UserLogin> entities) {
		dao.delete(entities);
	}

	@Override
	public UserLogin findByCompanyAndAccount(BaseCompany company, String account) {
		return dao.findByCompanyAndAccount(company, account);
	}

	@Override
	public void accountsave(UserLogin entity) {
		UserLogin login = dao.findOne(entity.getUserId());
		if (login == null) {
			entity.setPassword(StringUtil.getMD5(entity.getPassword()));
			dao.save(entity);
		} else {
			login.setAccount(entity.getAccount());
			login.setPassword(StringUtil.getMD5(entity.getPassword()));
			dao.save(login);
		}
	}

	@Override
	public void disableSwitch(String userId) {
		UserLogin login = dao.findOne(userId);
		login.setEnable(!login.getEnable());
		dao.save(login);
	}

}