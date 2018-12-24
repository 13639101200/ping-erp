package com.ping.erp.runtime.access.service;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.domain.BaseUser;
import com.ping.erp.runtime.access.domain.BaseAccess;

/**
 * 访问统计业务服务接口
 *
 * @version 1.0.9-RELEASE
 * @time 2018-11-20 05:33:27
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseAccessService {

	PageResult<BaseAccess> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	BaseAccess findOne(String id);

	void save(BaseAccess entity);

	void delete(List<BaseAccess> entities);

	PageResult<BaseAccess> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

	BaseAccess findByUserAndClientIpAndServerUrl(BaseUser user, String clientIp, String serverUrl);

}