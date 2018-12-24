package com.ping.erp.system.code.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.code.dao.BaseCodeDao;
import com.ping.erp.system.code.domain.BaseCode;
import com.ping.erp.system.code.service.BaseCodeService;

/**
 * 基础编码业务服务接口实现
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14 04:37:41
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class BaseCodeServiceImpl implements BaseCodeService {

	@Autowired
	private BaseCodeDao dao;

	public PageResult<BaseCode> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public BaseCode findOne(String id) {
		return dao.findOne(id);
	}

	public void save(BaseCode entity) {
		if (entity.getCodeId() == null || "".equals(entity.getCodeId())) {
			entity.setCodeId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<BaseCode> entities) {
		dao.delete(entities);
	}

	@Override
	public List<BaseCode> findByTypeCode(String typeCode) {
		return dao.findByTypeCode(typeCode);
	}

}