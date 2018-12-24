package com.ping.erp.system.role.dao;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.role.domain.BaseRole;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 基础角色数据访问接口
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 09:41:19
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseRoleDao {

	PageResult<BaseRole> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	BaseRole findOne(String id);

	void save(BaseRole entity);

	void delete(List<BaseRole> entities);

	PageResult<BaseRole> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

	List<BaseRole> findByCompanyRelUser(BaseCompany company, BaseUser user);

}