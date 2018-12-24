package com.ping.erp.system.user.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.dao.UserLoginDao;
import com.ping.erp.system.user.domain.UserLogin;
import com.ping.erp.system.user.repository.UserLoginRepository;

/**
 * 用户登录数据访问接口实现
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 10:26:27
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class UserLoginDaoImpl implements UserLoginDao {

	@Autowired
	private UserLoginRepository repository;

	public PageResult<UserLogin> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<UserLogin> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<UserLogin>(springPage.getTotalElements(), springPage.getContent());
	}

	public UserLogin findOne(String id) {
		return repository.findOne(id);
	}

	public void save(UserLogin entity) {
		repository.save(entity);
	}

	public void delete(List<UserLogin> entities) {
		repository.delete(entities);
	}

	@Override
	public UserLogin findByCompanyAndAccount(BaseCompany company, String account) {
		return repository.findByCompanyAndAccount(company, account);
	}

}