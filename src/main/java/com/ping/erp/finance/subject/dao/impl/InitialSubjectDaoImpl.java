package com.ping.erp.finance.subject.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.subject.dao.InitialSubjectDao;
import com.ping.erp.finance.subject.domain.InitialSubject;
import com.ping.erp.finance.subject.repository.InitialSubjectRepository;

/**
 * 初始科目数据访问接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:05:16
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class InitialSubjectDaoImpl implements InitialSubjectDao {

	@Autowired
	private InitialSubjectRepository repository;

	public PageResult<InitialSubject> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<InitialSubject> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<InitialSubject>(springPage.getTotalElements(), springPage.getContent());
	}

	public InitialSubject findOne(String id) {
		return repository.findOne(id);
	}

	public void save(InitialSubject entity) {
		repository.save(entity);
	}

	public void delete(List<InitialSubject> entities) {
		repository.delete(entities);
	}

	public PageResult<InitialSubject> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<InitialSubject> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<InitialSubject>(springPage.getTotalElements(), springPage.getContent());
	}

	@Override
	public void save(List<InitialSubject> entities) {
		repository.save(entities);
	}

}