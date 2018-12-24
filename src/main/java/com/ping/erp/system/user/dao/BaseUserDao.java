package com.ping.erp.system.user.dao;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 基础用户数据访问接口
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 10:01:33
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseUserDao {

	PageResult<BaseUser> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	BaseUser findOne(String id);

	void save(BaseUser entity);

	void delete(List<BaseUser> entities);

	PageResult<BaseUser> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

}