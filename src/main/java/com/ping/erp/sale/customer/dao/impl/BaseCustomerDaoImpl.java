package com.ping.erp.sale.customer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.sale.customer.dao.BaseCustomerDao;
import com.ping.erp.sale.customer.domain.BaseCustomer;
import com.ping.erp.sale.customer.repository.BaseCustomerRepository;

/**
 * 基础客户数据访问接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-25 11:58:01
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class BaseCustomerDaoImpl implements BaseCustomerDao {

	@Autowired
	private BaseCustomerRepository repository;

	public PageResult<BaseCustomer> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseCustomer> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseCustomer>(springPage.getTotalElements(), springPage.getContent());
	}

	public BaseCustomer findOne(String id) {
		return repository.findOne(id);
	}

	public void save(BaseCustomer entity) {
		repository.save(entity);
	}

	public void delete(List<BaseCustomer> entities) {
		repository.delete(entities);
	}

	public PageResult<BaseCustomer> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseCustomer> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseCustomer>(springPage.getTotalElements(), springPage.getContent());
	}

}