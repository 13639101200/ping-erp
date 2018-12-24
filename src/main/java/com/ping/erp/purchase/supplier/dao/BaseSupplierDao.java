package com.ping.erp.purchase.supplier.dao;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.purchase.supplier.domain.BaseSupplier;

/**
 * 基础供应商数据访问接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-25 11:59:33
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseSupplierDao {

	PageResult<BaseSupplier> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	BaseSupplier findOne(String id);

	void save(BaseSupplier entity);

	void delete(List<BaseSupplier> entities);

	PageResult<BaseSupplier> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

}