package com.ping.erp.sale.customer.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.sale.customer.dao.BaseCustomerDao;
import com.ping.erp.sale.customer.domain.BaseCustomer;
import com.ping.erp.sale.customer.service.BaseCustomerService;

/**
 * 基础客户业务服务接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-25 11:58:01
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class BaseCustomerServiceImpl implements BaseCustomerService {

	@Autowired
	private BaseCustomerDao dao;

	public PageResult<BaseCustomer> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public BaseCustomer findOne(String id) {
		return dao.findOne(id);
	}

	public void save(BaseCustomer entity) {
		if (entity.getCustomerId() == null || "".equals(entity.getCustomerId())) {
			entity.setCustomerId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<BaseCustomer> entities) {
		dao.delete(entities);
	}

	public PageResult<BaseCustomer> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

}