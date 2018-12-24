package com.ping.erp.purchase.supplier.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.purchase.supplier.dao.BaseSupplierDao;
import com.ping.erp.purchase.supplier.domain.BaseSupplier;
import com.ping.erp.purchase.supplier.repository.BaseSupplierRepository;

/**
 * 基础供应商数据访问接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-25 11:59:33
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class BaseSupplierDaoImpl implements BaseSupplierDao {

	@Autowired
	private BaseSupplierRepository repository;

	public PageResult<BaseSupplier> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseSupplier> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseSupplier>(springPage.getTotalElements(), springPage.getContent());
	}

	public BaseSupplier findOne(String id) {
		return repository.findOne(id);
	}

	public void save(BaseSupplier entity) {
		repository.save(entity);
	}

	public void delete(List<BaseSupplier> entities) {
		repository.delete(entities);
	}

	public PageResult<BaseSupplier> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseSupplier> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseSupplier>(springPage.getTotalElements(), springPage.getContent());
	}

}