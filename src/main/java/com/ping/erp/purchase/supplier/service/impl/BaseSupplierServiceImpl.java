package com.ping.erp.purchase.supplier.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.purchase.supplier.dao.BaseSupplierDao;
import com.ping.erp.purchase.supplier.domain.BaseSupplier;
import com.ping.erp.purchase.supplier.service.BaseSupplierService;

/**
 * 基础供应商业务服务接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-25 11:59:33
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class BaseSupplierServiceImpl implements BaseSupplierService {

	@Autowired
	private BaseSupplierDao dao;

	public PageResult<BaseSupplier> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public BaseSupplier findOne(String id) {
		return dao.findOne(id);
	}

	public void save(BaseSupplier entity) {
		if (entity.getSupplierId() == null || "".equals(entity.getSupplierId())) {
			entity.setSupplierId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<BaseSupplier> entities) {
		dao.delete(entities);
	}

	public PageResult<BaseSupplier> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

}