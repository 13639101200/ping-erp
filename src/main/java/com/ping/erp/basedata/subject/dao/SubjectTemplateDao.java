package com.ping.erp.basedata.subject.dao;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.basedata.subject.domain.SubjectTemplate;

/**
 * 科目模板数据访问接口
 *
 * @version 1.1.2-RELEASE
 * @time 2018-11-23 12:32:22
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface SubjectTemplateDao {

	PageResult<SubjectTemplate> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	SubjectTemplate findOne(String id);

	void save(SubjectTemplate entity);

	void delete(List<SubjectTemplate> entities);

	PageResult<SubjectTemplate> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

	List<SubjectTemplate> findAll();

}