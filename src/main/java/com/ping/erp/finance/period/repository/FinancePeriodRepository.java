package com.ping.erp.finance.period.repository;

import java.util.List;

import com.ping.erp.common.config.repository.CoreRepository;
import com.ping.erp.finance.period.domain.FinancePeriod;
import com.ping.erp.system.company.domain.BaseCompany;

/**
 * 会计期间数据存储接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:06:21
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface FinancePeriodRepository extends CoreRepository<FinancePeriod, String> {

	List<FinancePeriod> findByCompanyAndIsArchived(BaseCompany company, Boolean isArchived);

}