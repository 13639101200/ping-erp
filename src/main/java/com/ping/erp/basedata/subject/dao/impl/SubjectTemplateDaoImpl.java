package com.ping.erp.basedata.subject.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.basedata.subject.dao.SubjectTemplateDao;
import com.ping.erp.basedata.subject.domain.SubjectTemplate;
import com.ping.erp.basedata.subject.repository.SubjectTemplateRepository;

/**
 * 科目模板数据访问接口实现
 *
 * @version 1.1.2-RELEASE
 * @time 2018-11-23 12:32:22
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class SubjectTemplateDaoImpl implements SubjectTemplateDao {

	@Autowired
	private SubjectTemplateRepository repository;

	public PageResult<SubjectTemplate> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<SubjectTemplate> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<SubjectTemplate>(springPage.getTotalElements(), springPage.getContent());
	}

	public SubjectTemplate findOne(String id) {
		return repository.findOne(id);
	}

	public void save(SubjectTemplate entity) {
		repository.save(entity);
	}

	public void delete(List<SubjectTemplate> entities) {
		repository.delete(entities);
	}

	public PageResult<SubjectTemplate> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<SubjectTemplate> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<SubjectTemplate>(springPage.getTotalElements(), springPage.getContent());
	}

	@Override
	public List<SubjectTemplate> findAll() {
		return repository.findAll();
	}

}