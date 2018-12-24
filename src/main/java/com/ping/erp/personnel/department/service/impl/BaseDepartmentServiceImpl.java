package com.ping.erp.personnel.department.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.personnel.department.dao.BaseDepartmentDao;
import com.ping.erp.personnel.department.domain.BaseDepartment;
import com.ping.erp.personnel.department.service.BaseDepartmentService;

/**
 * 基础部门业务服务接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-25 11:54:23
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class BaseDepartmentServiceImpl implements BaseDepartmentService {

	@Autowired
	private BaseDepartmentDao dao;

	public PageResult<BaseDepartment> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public BaseDepartment findOne(String id) {
		return dao.findOne(id);
	}

	public void save(BaseDepartment entity) {
		if (entity.getDepartmentId() == null || "".equals(entity.getDepartmentId())) {
			entity.setDepartmentId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<BaseDepartment> entities) {
		dao.delete(entities);
	}

	public PageResult<BaseDepartment> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

}