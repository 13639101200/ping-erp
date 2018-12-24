package com.ping.erp.basedata.subject.service;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.basedata.subject.domain.SubjectTemplate;
import com.ping.erp.basedata.subject.domain.TemplateSubject;

/**
 * 模板科目业务服务接口
 *
 * @version 1.1.2-RELEASE
 * @time 2018-11-23 12:33:48
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface TemplateSubjectService {

	PageResult<TemplateSubject> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	TemplateSubject findOne(String id);

	void save(TemplateSubject entity);

	void delete(List<TemplateSubject> entities);

	PageResult<TemplateSubject> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

	PageResult<TemplateSubject> findByTemplate(SubjectTemplate template, List<String> fields, String keyword, String field, String order, int size, int page);

}