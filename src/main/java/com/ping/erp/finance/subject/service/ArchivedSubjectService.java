package com.ping.erp.finance.subject.service;

import java.util.List;

import com.ping.erp.basedata.subject.domain.SubjectTemplate;
import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.SimpleResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.subject.domain.ArchivedSubject;

/**
 * 结账科目业务服务接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:05:35
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface ArchivedSubjectService {

	PageResult<ArchivedSubject> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	ArchivedSubject findOne(String id);

	void save(ArchivedSubject entity);

	void delete(List<ArchivedSubject> entities);

	PageResult<ArchivedSubject> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

	PageResult<ArchivedSubject> findByCompanyAndCurrentPeriod(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

	void saveToCurrentPeriod(BaseCompany company, ArchivedSubject entity);

	void importSaveToCurrentPeriod(BaseCompany company, SubjectTemplate template);

	SimpleResult startSubject(BaseCompany company);

	SimpleResult isStart(BaseCompany company);

	SimpleResult archivedSubject(BaseCompany company);

}