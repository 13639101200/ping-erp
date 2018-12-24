package com.ping.erp.personnel.department.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.personnel.department.dao.BaseDepartmentDao;
import com.ping.erp.personnel.department.domain.BaseDepartment;
import com.ping.erp.personnel.department.repository.BaseDepartmentRepository;

/**
 * 基础部门数据访问接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-25 11:54:23
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class BaseDepartmentDaoImpl implements BaseDepartmentDao {

	@Autowired
	private BaseDepartmentRepository repository;

	public PageResult<BaseDepartment> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseDepartment> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseDepartment>(springPage.getTotalElements(), springPage.getContent());
	}

	public BaseDepartment findOne(String id) {
		return repository.findOne(id);
	}

	public void save(BaseDepartment entity) {
		repository.save(entity);
	}

	public void delete(List<BaseDepartment> entities) {
		repository.delete(entities);
	}

	public PageResult<BaseDepartment> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseDepartment> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseDepartment>(springPage.getTotalElements(), springPage.getContent());
	}

}