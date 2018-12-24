package com.ping.erp.runtime.access.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.domain.BaseUser;
import com.ping.erp.runtime.access.dao.BaseAccessDao;
import com.ping.erp.runtime.access.domain.BaseAccess;
import com.ping.erp.runtime.access.repository.BaseAccessRepository;

/**
 * 访问统计数据访问接口实现
 *
 * @version 1.0.9-RELEASE
 * @time 2018-11-20 05:33:27
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class BaseAccessDaoImpl implements BaseAccessDao {

	@Autowired
	private BaseAccessRepository repository;

	public PageResult<BaseAccess> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseAccess> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseAccess>(springPage.getTotalElements(), springPage.getContent());
	}

	public BaseAccess findOne(String id) {
		return repository.findOne(id);
	}

	public void save(BaseAccess entity) {
		repository.save(entity);
	}

	public void delete(List<BaseAccess> entities) {
		repository.delete(entities);
	}

	public PageResult<BaseAccess> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseAccess> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseAccess>(springPage.getTotalElements(), springPage.getContent());
	}

	@Override
	public BaseAccess findByUserAndClientIpAndServerUrl(BaseUser user, String clientIp, String serverUrl) {
		return repository.findByUserAndClientIpAndServerUrl(user, clientIp, serverUrl);
	}

}