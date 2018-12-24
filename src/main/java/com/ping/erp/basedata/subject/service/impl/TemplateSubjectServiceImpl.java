package com.ping.erp.basedata.subject.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.basedata.subject.dao.TemplateSubjectDao;
import com.ping.erp.basedata.subject.domain.SubjectTemplate;
import com.ping.erp.basedata.subject.domain.TemplateSubject;
import com.ping.erp.basedata.subject.service.TemplateSubjectService;

/**
 * 模板科目业务服务接口实现
 *
 * @version 1.1.2-RELEASE
 * @time 2018-11-23 12:33:48
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class TemplateSubjectServiceImpl implements TemplateSubjectService {

	@Autowired
	private TemplateSubjectDao dao;

	public PageResult<TemplateSubject> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public TemplateSubject findOne(String id) {
		return dao.findOne(id);
	}

	public void save(TemplateSubject entity) {
		if (entity.getSubjectId() == null || "".equals(entity.getSubjectId())) {
			entity.setSubjectId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<TemplateSubject> entities) {
		dao.delete(entities);
	}

	public PageResult<TemplateSubject> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

	@Override
	public PageResult<TemplateSubject> findByTemplate(SubjectTemplate template, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByTemplate(template, fields, keyword, field, order, size, page);
	}

}