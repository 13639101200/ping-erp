package com.ping.erp.system.auth.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.auth.dao.BaseAuthDao;
import com.ping.erp.system.auth.domain.BaseAuth;
import com.ping.erp.system.auth.repository.BaseAuthRepository;

/**
 * 基础权限数据访问接口实现
 *
 * @version 1.2.1-RELEASE
 * @time 2018-12-14 04:20:32
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class BaseAuthDaoImpl implements BaseAuthDao {

	@Autowired
	private BaseAuthRepository repository;

	public PageResult<BaseAuth> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseAuth> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseAuth>(springPage.getTotalElements(), springPage.getContent());
	}

	public BaseAuth findOne(String id) {
		return repository.findOne(id);
	}

	public void save(BaseAuth entity) {
		repository.save(entity);
	}

	public void delete(List<BaseAuth> entities) {
		repository.delete(entities);
	}

	public PageResult<BaseAuth> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseAuth> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseAuth>(springPage.getTotalElements(), springPage.getContent());
	}

}