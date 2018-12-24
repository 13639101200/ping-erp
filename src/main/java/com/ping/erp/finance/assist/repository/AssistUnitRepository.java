package com.ping.erp.finance.assist.repository;

import java.util.List;

import com.ping.erp.common.config.repository.CoreRepository;
import com.ping.erp.finance.assist.domain.AssistUnit;
import com.ping.erp.system.company.domain.BaseCompany;

/**
 * 辅计单位数据存储接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:03:47
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface AssistUnitRepository extends CoreRepository<AssistUnit, String> {

	List<AssistUnit> findByCompany(BaseCompany company);

}