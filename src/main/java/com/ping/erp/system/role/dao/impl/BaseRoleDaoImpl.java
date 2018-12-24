package com.ping.erp.system.role.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.role.dao.BaseRoleDao;
import com.ping.erp.system.role.domain.BaseRole;
import com.ping.erp.system.role.repository.BaseRoleRepository;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 基础角色数据访问接口实现
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 09:41:19
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class BaseRoleDaoImpl implements BaseRoleDao {

	@Autowired
	private BaseRoleRepository repository;

	public PageResult<BaseRole> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseRole> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseRole>(springPage.getTotalElements(), springPage.getContent());
	}

	public BaseRole findOne(String id) {
		return repository.findOne(id);
	}

	public void save(BaseRole entity) {
		repository.save(entity);
	}

	public void delete(List<BaseRole> entities) {
		repository.delete(entities);
	}

	@Override
	public PageResult<BaseRole> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseRole> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseRole>(springPage.getTotalElements(), springPage.getContent());
	}

	@Override
	public List<BaseRole> findByCompanyRelUser(BaseCompany company, BaseUser user) {
		return repository.findByCompanyRelUser(company, user);
	}

}