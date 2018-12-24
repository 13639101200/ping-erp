package com.ping.erp.system.code.dao;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.code.domain.BaseCode;

/**
 * 基础编码数据访问接口
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14 04:37:41
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseCodeDao {

	PageResult<BaseCode> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	BaseCode findOne(String id);

	void save(BaseCode entity);

	void delete(List<BaseCode> entities);

	List<BaseCode> findByTypeCode(String typeCode);

}