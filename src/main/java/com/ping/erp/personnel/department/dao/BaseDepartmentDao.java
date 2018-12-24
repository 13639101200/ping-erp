package com.ping.erp.personnel.department.dao;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.personnel.department.domain.BaseDepartment;

/**
 * 基础部门数据访问接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-25 11:54:23
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseDepartmentDao {

	PageResult<BaseDepartment> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	BaseDepartment findOne(String id);

	void save(BaseDepartment entity);

	void delete(List<BaseDepartment> entities);

	PageResult<BaseDepartment> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

}