package com.ping.erp.system.user.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.dao.BaseUserDao;
import com.ping.erp.system.user.domain.BaseUser;
import com.ping.erp.system.user.service.BaseUserService;

/**
 * 基础用户业务服务接口实现
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 10:01:33
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class BaseUserServiceImpl implements BaseUserService {

	@Autowired
	private BaseUserDao dao;

	public PageResult<BaseUser> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public BaseUser findOne(String id) {
		return dao.findOne(id);
	}

	public void save(BaseUser entity) {
		if (entity.getUserId() == null || "".equals(entity.getUserId())) {
			entity.setUserId(StringUtil.getUUID());
		} else {
			BaseUser user = dao.findOne(entity.getUserId());
			if (user != null) {
				entity.setRoles(user.getRoles());
			}
		}
		dao.save(entity);
	}

	public void delete(List<BaseUser> entities) {
		dao.delete(entities);
	}

	@Override
	public PageResult<BaseUser> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

	@Override
	public void authSave(BaseUser entity) {
		BaseUser user = dao.findOne(entity.getUserId());
		user.setRoles(entity.getRoles());
		dao.save(user);
	}

	@Override
	public void baseSave(BaseUser entity) {
		BaseUser user = dao.findOne(entity.getUserId());
		user.setUserName(entity.getUserName());
		user.setUserCard(entity.getUserCard());
		user.setUserPhone(entity.getUserPhone());
		user.setUserEmail(entity.getUserEmail());
		dao.save(user);
	}

}