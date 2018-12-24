package com.ping.erp.personnel.clerk.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.personnel.clerk.dao.BaseClerkDao;
import com.ping.erp.personnel.clerk.domain.BaseClerk;
import com.ping.erp.personnel.clerk.service.BaseClerkService;

/**
 * 基础职员业务服务接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-26 12:27:38
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class BaseClerkServiceImpl implements BaseClerkService {

	@Autowired
	private BaseClerkDao dao;

	public PageResult<BaseClerk> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public BaseClerk findOne(String id) {
		return dao.findOne(id);
	}

	public void save(BaseClerk entity) {
		if (entity.getClerkId() == null || "".equals(entity.getClerkId())) {
			entity.setClerkId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<BaseClerk> entities) {
		dao.delete(entities);
	}

	public PageResult<BaseClerk> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

}