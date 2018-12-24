package com.ping.erp.system.code.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.code.dao.BaseCodeDao;
import com.ping.erp.system.code.domain.BaseCode;
import com.ping.erp.system.code.repository.BaseCodeRepository;

/**
 * 基础编码数据访问接口实现
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14 04:37:41
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class BaseCodeDaoImpl implements BaseCodeDao {

	@Autowired
	private BaseCodeRepository repository;

	public PageResult<BaseCode> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseCode> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseCode>(springPage.getTotalElements(), springPage.getContent());
	}

	public BaseCode findOne(String id) {
		return repository.findOne(id);
	}

	public void save(BaseCode entity) {
		repository.save(entity);
	}

	public void delete(List<BaseCode> entities) {
		repository.delete(entities);
	}

	@Override
	public List<BaseCode> findByTypeCode(String typeCode) {
		return repository.findByTypeCode(typeCode);
	}

}