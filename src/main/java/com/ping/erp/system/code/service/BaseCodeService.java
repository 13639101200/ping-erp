package com.ping.erp.system.code.service;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.code.domain.BaseCode;

/**
 * 基础编码业务服务接口
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14 04:37:41
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseCodeService {

	PageResult<BaseCode> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	BaseCode findOne(String id);

	void save(BaseCode entity);

	void delete(List<BaseCode> entities);

	List<BaseCode> findByTypeCode(String typeCode);

}