package com.ping.erp.finance.period.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.period.dao.FinancePeriodDao;
import com.ping.erp.finance.period.domain.FinancePeriod;
import com.ping.erp.finance.period.service.FinancePeriodService;

/**
 * 会计期间业务服务接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:06:21
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class FinancePeriodServiceImpl implements FinancePeriodService {

	@Autowired
	private FinancePeriodDao dao;

	public PageResult<FinancePeriod> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public FinancePeriod findOne(String id) {
		return dao.findOne(id);
	}

	public void save(FinancePeriod entity) {
		if (entity.getPeriodId() == null || "".equals(entity.getPeriodId())) {
			entity.setPeriodId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<FinancePeriod> entities) {
		dao.delete(entities);
	}

	public PageResult<FinancePeriod> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

	@Override
	public List<FinancePeriod> findByCompanyAndIsArchived(BaseCompany company, Boolean isArchived) {
		return dao.findByCompanyAndIsArchived(company, isArchived);
	}

	@Override
	public FinancePeriod findByCompanyAndNoArchived(BaseCompany company) {
		return dao.findByCompanyAndNoArchived(company);
	}

}