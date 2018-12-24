package com.ping.erp.system.company.service;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;

/**
 * 基础公司业务服务接口
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 08:59:21
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseCompanyService {

	PageResult<BaseCompany> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	BaseCompany findOne(String id);

	void save(BaseCompany entity);

	void delete(List<BaseCompany> entities);

	BaseCompany findByCompanyUrl(String url);

	List<BaseCompany> findAll();

	void authSave(BaseCompany entity);

}