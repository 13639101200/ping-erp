package com.ping.erp.basedata.subject.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.basedata.subject.dao.SubjectTemplateDao;
import com.ping.erp.basedata.subject.domain.SubjectTemplate;
import com.ping.erp.basedata.subject.service.SubjectTemplateService;

/**
 * 科目模板业务服务接口实现
 *
 * @version 1.1.2-RELEASE
 * @time 2018-11-23 12:32:22
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class SubjectTemplateServiceImpl implements SubjectTemplateService {

	@Autowired
	private SubjectTemplateDao dao;

	public PageResult<SubjectTemplate> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public SubjectTemplate findOne(String id) {
		return dao.findOne(id);
	}

	public void save(SubjectTemplate entity) {
		if (entity.getTemplateId() == null || "".equals(entity.getTemplateId())) {
			entity.setTemplateId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<SubjectTemplate> entities) {
		dao.delete(entities);
	}

	public PageResult<SubjectTemplate> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

	@Override
	public List<SubjectTemplate> findAll() {
		return dao.findAll();
	}

}