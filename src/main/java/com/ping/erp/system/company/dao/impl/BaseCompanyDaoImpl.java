package com.ping.erp.system.company.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.dao.BaseCompanyDao;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.company.repository.BaseCompanyRepository;

/**
 * 基础公司数据访问接口实现
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 08:59:21
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class BaseCompanyDaoImpl implements BaseCompanyDao {

	@Autowired
	private BaseCompanyRepository repository;

	public PageResult<BaseCompany> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseCompany> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseCompany>(springPage.getTotalElements(), springPage.getContent());
	}

	public BaseCompany findOne(String id) {
		return repository.findOne(id);
	}

	public void save(BaseCompany entity) {
		repository.save(entity);
	}

	public void delete(List<BaseCompany> entities) {
		repository.delete(entities);
	}

	@Override
	public BaseCompany findByCompanyUrl(String url) {
		return repository.findByCompanyUrl(url);
	}

	@Override
	public List<BaseCompany> findAll() {
		return repository.findAll();
	}

}