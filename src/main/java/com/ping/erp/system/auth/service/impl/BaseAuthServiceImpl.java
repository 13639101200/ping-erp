package com.ping.erp.system.auth.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.auth.dao.BaseAuthDao;
import com.ping.erp.system.auth.domain.BaseAuth;
import com.ping.erp.system.auth.service.BaseAuthService;

/**
 * 基础权限业务服务接口实现
 *
 * @version 1.2.1-RELEASE
 * @time 2018-12-14 04:20:32
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class BaseAuthServiceImpl implements BaseAuthService {

	@Autowired
	private BaseAuthDao dao;

	public PageResult<BaseAuth> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public BaseAuth findOne(String id) {
		return dao.findOne(id);
	}

	public void save(BaseAuth entity) {
		if (entity.getAuthId() == null || "".equals(entity.getAuthId())) {
			entity.setAuthId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<BaseAuth> entities) {
		dao.delete(entities);
	}

	public PageResult<BaseAuth> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

}