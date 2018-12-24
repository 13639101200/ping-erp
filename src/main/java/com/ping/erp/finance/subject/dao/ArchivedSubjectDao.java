package com.ping.erp.finance.subject.dao;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.period.domain.FinancePeriod;
import com.ping.erp.finance.subject.domain.ArchivedSubject;

/**
 * 结账科目数据访问接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:05:35
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface ArchivedSubjectDao {

	PageResult<ArchivedSubject> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	ArchivedSubject findOne(String id);

	void save(ArchivedSubject entity);

	void delete(List<ArchivedSubject> entities);

	PageResult<ArchivedSubject> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

	PageResult<ArchivedSubject> findByPeriod(FinancePeriod period, List<String> fields, String keyword, String field, String order, int size, int page);

	void save(List<ArchivedSubject> entities);

	List<ArchivedSubject> findByPeriod(FinancePeriod period);

}