package com.ping.erp.finance.assist.dao;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.assist.domain.AssistUnit;

/**
 * 辅计单位数据访问接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:03:47
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface AssistUnitDao {

	PageResult<AssistUnit> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	AssistUnit findOne(String id);

	void save(AssistUnit entity);

	void delete(List<AssistUnit> entities);

	PageResult<AssistUnit> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

	List<AssistUnit> findByCompany(BaseCompany company);

}