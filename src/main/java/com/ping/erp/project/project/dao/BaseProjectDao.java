package com.ping.erp.project.project.dao;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.project.project.domain.BaseProject;

/**
 * 基础项目数据访问接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-26 12:05:23
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseProjectDao {

	PageResult<BaseProject> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	BaseProject findOne(String id);

	void save(BaseProject entity);

	void delete(List<BaseProject> entities);

	PageResult<BaseProject> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

}