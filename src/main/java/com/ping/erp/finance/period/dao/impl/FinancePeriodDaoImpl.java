package com.ping.erp.finance.period.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.period.dao.FinancePeriodDao;
import com.ping.erp.finance.period.domain.FinancePeriod;
import com.ping.erp.finance.period.repository.FinancePeriodRepository;

/**
 * 会计期间数据访问接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:06:21
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class FinancePeriodDaoImpl implements FinancePeriodDao {

	@Autowired
	private FinancePeriodRepository repository;

	public PageResult<FinancePeriod> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<FinancePeriod> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<FinancePeriod>(springPage.getTotalElements(), springPage.getContent());
	}

	public FinancePeriod findOne(String id) {
		return repository.findOne(id);
	}

	public void save(FinancePeriod entity) {
		repository.save(entity);
	}

	public void delete(List<FinancePeriod> entities) {
		repository.delete(entities);
	}

	public PageResult<FinancePeriod> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<FinancePeriod> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<FinancePeriod>(springPage.getTotalElements(), springPage.getContent());
	}

	@Override
	public List<FinancePeriod> findByCompanyAndIsArchived(BaseCompany company, Boolean isArchived) {
		return repository.findByCompanyAndIsArchived(company, isArchived);
	}

	@Override
	public FinancePeriod findByCompanyAndNoArchived(BaseCompany company) {
		List<FinancePeriod> periodList = repository.findByCompanyAndIsArchived(company, false);
		if (periodList != null && periodList.size() > 0) {
			return periodList.get(0);
		} else {
			return null;
		}

	}

}