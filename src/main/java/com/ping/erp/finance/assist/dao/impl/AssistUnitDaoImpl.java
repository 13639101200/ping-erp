package com.ping.erp.finance.assist.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.assist.dao.AssistUnitDao;
import com.ping.erp.finance.assist.domain.AssistUnit;
import com.ping.erp.finance.assist.repository.AssistUnitRepository;

/**
 * 辅计单位数据访问接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:03:47
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class AssistUnitDaoImpl implements AssistUnitDao {

	@Autowired
	private AssistUnitRepository repository;

	public PageResult<AssistUnit> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<AssistUnit> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<AssistUnit>(springPage.getTotalElements(), springPage.getContent());
	}

	public AssistUnit findOne(String id) {
		return repository.findOne(id);
	}

	public void save(AssistUnit entity) {
		repository.save(entity);
	}

	public void delete(List<AssistUnit> entities) {
		repository.delete(entities);
	}

	public PageResult<AssistUnit> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<AssistUnit> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<AssistUnit>(springPage.getTotalElements(), springPage.getContent());
	}

	@Override
	public List<AssistUnit> findByCompany(BaseCompany company) {
		return repository.findByCompany(company);
	}

}