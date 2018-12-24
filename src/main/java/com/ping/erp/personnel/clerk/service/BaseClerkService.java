package com.ping.erp.personnel.clerk.service;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.personnel.clerk.domain.BaseClerk;

/**
 * 基础职员业务服务接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-26 12:27:38
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseClerkService {

	PageResult<BaseClerk> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	BaseClerk findOne(String id);

	void save(BaseClerk entity);

	void delete(List<BaseClerk> entities);

	PageResult<BaseClerk> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

}