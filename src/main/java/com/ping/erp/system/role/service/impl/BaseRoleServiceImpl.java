package com.ping.erp.system.role.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.role.dao.BaseRoleDao;
import com.ping.erp.system.role.domain.BaseRole;
import com.ping.erp.system.role.service.BaseRoleService;
import com.ping.erp.system.user.dao.BaseUserDao;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 基础角色业务服务接口实现
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 09:41:19
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class BaseRoleServiceImpl implements BaseRoleService {

	@Autowired
	private BaseRoleDao dao;
	@Autowired
	private BaseUserDao userDao;

	public PageResult<BaseRole> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public BaseRole findOne(String id) {
		return dao.findOne(id);
	}

	public void save(BaseRole entity) {
		if (entity.getRoleId() == null || "".equals(entity.getRoleId())) {
			entity.setRoleId(StringUtil.getUUID());
		} else {
			BaseRole role = dao.findOne(entity.getRoleId());
			if (role != null) {
				entity.setMenus(role.getMenus());
			}
		}
		dao.save(entity);
	}

	public void delete(List<BaseRole> entities) {
		dao.delete(entities);
	}

	@Override
	public PageResult<BaseRole> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

	@Override
	public void authSave(BaseRole entity) {
		BaseRole role = dao.findOne(entity.getRoleId());
		role.setMenus(entity.getMenus());
		dao.save(role);
	}

	@Override
	public List<BaseRole> findByCompanyRelUser(BaseUser user) {
		BaseCompany company = userDao.findOne(user.getUserId()).getCompany();
		return dao.findByCompanyRelUser(company, user);
	}

}