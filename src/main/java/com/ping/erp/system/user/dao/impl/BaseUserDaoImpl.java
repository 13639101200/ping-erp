package com.ping.erp.system.user.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.dao.BaseUserDao;
import com.ping.erp.system.user.domain.BaseUser;
import com.ping.erp.system.user.repository.BaseUserRepository;

/**
 * 基础用户数据访问接口实现
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 10:01:33
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class BaseUserDaoImpl implements BaseUserDao {

	@Autowired
	private BaseUserRepository repository;

	public PageResult<BaseUser> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseUser> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseUser>(springPage.getTotalElements(), springPage.getContent());
	}

	public BaseUser findOne(String id) {
		return repository.findOne(id);
	}

	public void save(BaseUser entity) {
		repository.save(entity);
	}

	public void delete(List<BaseUser> entities) {
		repository.delete(entities);
	}

	@Override
	public PageResult<BaseUser> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseUser> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseUser>(springPage.getTotalElements(), springPage.getContent());
	}

}