package com.ping.erp.system.auth.dao;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.auth.domain.BaseAuth;

/**
 * 基础权限数据访问接口
 *
 * @version 1.2.1-RELEASE
 * @time 2018-12-14 04:20:32
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseAuthDao {

	PageResult<BaseAuth> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	BaseAuth findOne(String id);

	void save(BaseAuth entity);

	void delete(List<BaseAuth> entities);

	PageResult<BaseAuth> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

}