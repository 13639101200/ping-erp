package com.ping.erp.project.project.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.project.project.dao.BaseProjectDao;
import com.ping.erp.project.project.domain.BaseProject;
import com.ping.erp.project.project.repository.BaseProjectRepository;

/**
 * 基础项目数据访问接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-26 12:05:23
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class BaseProjectDaoImpl implements BaseProjectDao {

	@Autowired
	private BaseProjectRepository repository;

	public PageResult<BaseProject> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseProject> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseProject>(springPage.getTotalElements(), springPage.getContent());
	}

	public BaseProject findOne(String id) {
		return repository.findOne(id);
	}

	public void save(BaseProject entity) {
		repository.save(entity);
	}

	public void delete(List<BaseProject> entities) {
		repository.delete(entities);
	}

	public PageResult<BaseProject> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseProject> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseProject>(springPage.getTotalElements(), springPage.getContent());
	}

}