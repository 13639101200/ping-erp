package com.ping.erp.project.project.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.project.project.dao.BaseProjectDao;
import com.ping.erp.project.project.domain.BaseProject;
import com.ping.erp.project.project.service.BaseProjectService;

/**
 * 基础项目业务服务接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-26 12:05:23
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class BaseProjectServiceImpl implements BaseProjectService {

	@Autowired
	private BaseProjectDao dao;

	public PageResult<BaseProject> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public BaseProject findOne(String id) {
		return dao.findOne(id);
	}

	public void save(BaseProject entity) {
		if (entity.getProjectId() == null || "".equals(entity.getProjectId())) {
			entity.setProjectId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<BaseProject> entities) {
		dao.delete(entities);
	}

	public PageResult<BaseProject> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

}