package com.ping.erp.runtime.access.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.domain.BaseUser;
import com.ping.erp.runtime.access.dao.BaseAccessDao;
import com.ping.erp.runtime.access.domain.BaseAccess;
import com.ping.erp.runtime.access.service.BaseAccessService;

/**
 * 访问统计业务服务接口实现
 *
 * @version 1.0.9-RELEASE
 * @time 2018-11-20 05:33:27
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class BaseAccessServiceImpl implements BaseAccessService {

	@Autowired
	private BaseAccessDao dao;

	public PageResult<BaseAccess> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public BaseAccess findOne(String id) {
		return dao.findOne(id);
	}

	public void save(BaseAccess entity) {
		if (entity.getAccessId() == null || "".equals(entity.getAccessId())) {
			entity.setAccessId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<BaseAccess> entities) {
		dao.delete(entities);
	}

	public PageResult<BaseAccess> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

	@Override
	public BaseAccess findByUserAndClientIpAndServerUrl(BaseUser user, String clientIp, String serverUrl) {
		return dao.findByUserAndClientIpAndServerUrl(user, clientIp, serverUrl);
	}

}