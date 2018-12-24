package com.ping.erp.sale.customer.service;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.sale.customer.domain.BaseCustomer;

/**
 * 基础客户业务服务接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-25 11:58:01
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseCustomerService {

	PageResult<BaseCustomer> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	BaseCustomer findOne(String id);

	void save(BaseCustomer entity);

	void delete(List<BaseCustomer> entities);

	PageResult<BaseCustomer> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

}