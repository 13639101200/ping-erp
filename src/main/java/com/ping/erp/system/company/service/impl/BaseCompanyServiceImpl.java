package com.ping.erp.system.company.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.dao.BaseCompanyDao;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.company.service.BaseCompanyService;

/**
 * 基础公司业务服务接口实现
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 08:59:21
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class BaseCompanyServiceImpl implements BaseCompanyService {

	@Autowired
	private BaseCompanyDao dao;

	public PageResult<BaseCompany> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public BaseCompany findOne(String id) {
		return dao.findOne(id);
	}

	public void save(BaseCompany entity) {
		if (entity.getCompanyId() == null || "".equals(entity.getCompanyId())) {
			entity.setCompanyId(StringUtil.getUUID());
		} else {
			BaseCompany company = dao.findOne(entity.getCompanyId());
			if (company != null) {
				entity.setMenus(company.getMenus());
			}
		}
		dao.save(entity);
	}

	public void delete(List<BaseCompany> entities) {
		dao.delete(entities);
	}

	@Override
	public BaseCompany findByCompanyUrl(String url) {
		return dao.findByCompanyUrl(url);
	}

	@Override
	public List<BaseCompany> findAll() {
		return dao.findAll();
	}

	@Override
	public void authSave(BaseCompany entity) {
		BaseCompany company = dao.findOne(entity.getCompanyId());
		company.setMenus(entity.getMenus());
		dao.save(company);
	}

}