package com.ping.erp.finance.subject.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.subject.dao.InitialSubjectDao;
import com.ping.erp.finance.subject.domain.InitialSubject;
import com.ping.erp.finance.subject.service.InitialSubjectService;

/**
 * 初始科目业务服务接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:05:16
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class InitialSubjectServiceImpl implements InitialSubjectService {

	@Autowired
	private InitialSubjectDao dao;

	public PageResult<InitialSubject> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public InitialSubject findOne(String id) {
		return dao.findOne(id);
	}

	public void save(InitialSubject entity) {
		if (entity.getSubjectId() == null || "".equals(entity.getSubjectId())) {
			entity.setSubjectId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<InitialSubject> entities) {
		dao.delete(entities);
	}

	public PageResult<InitialSubject> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

}