package com.ping.erp.personnel.clerk.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.personnel.clerk.dao.BaseClerkDao;
import com.ping.erp.personnel.clerk.domain.BaseClerk;
import com.ping.erp.personnel.clerk.repository.BaseClerkRepository;

/**
 * 基础职员数据访问接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-26 12:27:38
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class BaseClerkDaoImpl implements BaseClerkDao {

	@Autowired
	private BaseClerkRepository repository;

	public PageResult<BaseClerk> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseClerk> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseClerk>(springPage.getTotalElements(), springPage.getContent());
	}

	public BaseClerk findOne(String id) {
		return repository.findOne(id);
	}

	public void save(BaseClerk entity) {
		repository.save(entity);
	}

	public void delete(List<BaseClerk> entities) {
		repository.delete(entities);
	}

	public PageResult<BaseClerk> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseClerk> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseClerk>(springPage.getTotalElements(), springPage.getContent());
	}

}