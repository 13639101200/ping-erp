package com.ping.erp.finance.assist.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.assist.dao.AssistUnitDao;
import com.ping.erp.finance.assist.domain.AssistUnit;
import com.ping.erp.finance.assist.service.AssistUnitService;

/**
 * 辅计单位业务服务接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:03:47
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class AssistUnitServiceImpl implements AssistUnitService {

	@Autowired
	private AssistUnitDao dao;

	public PageResult<AssistUnit> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public AssistUnit findOne(String id) {
		return dao.findOne(id);
	}

	public void save(AssistUnit entity) {
		if (entity.getUnitId() == null || "".equals(entity.getUnitId())) {
			entity.setUnitId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<AssistUnit> entities) {
		dao.delete(entities);
	}

	public PageResult<AssistUnit> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

	@Override
	public List<AssistUnit> findByCompany(BaseCompany company) {
		return dao.findByCompany(company);
	}

}