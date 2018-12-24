package com.ping.erp.finance.period.dao;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.period.domain.FinancePeriod;

/**
 * 会计期间数据访问接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:06:21
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface FinancePeriodDao {

	PageResult<FinancePeriod> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	FinancePeriod findOne(String id);

	void save(FinancePeriod entity);

	void delete(List<FinancePeriod> entities);

	PageResult<FinancePeriod> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

	List<FinancePeriod> findByCompanyAndIsArchived(BaseCompany company, Boolean isArchived);
	
	FinancePeriod findByCompanyAndNoArchived(BaseCompany company);

}