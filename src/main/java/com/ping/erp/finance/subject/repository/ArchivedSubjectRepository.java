package com.ping.erp.finance.subject.repository;

import java.util.List;

import com.ping.erp.common.config.repository.CoreRepository;
import com.ping.erp.finance.period.domain.FinancePeriod;
import com.ping.erp.finance.subject.domain.ArchivedSubject;

/**
 * 结账科目数据存储接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:05:35
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface ArchivedSubjectRepository extends CoreRepository<ArchivedSubject, String> {

	List<ArchivedSubject> findByPeriod(FinancePeriod period);

}