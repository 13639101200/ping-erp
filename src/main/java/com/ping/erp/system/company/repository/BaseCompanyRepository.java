package com.ping.erp.system.company.repository;

import com.ping.erp.common.config.repository.CoreRepository;
import com.ping.erp.system.company.domain.BaseCompany;

/**
 * 基础公司数据存储接口
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 08:59:21
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseCompanyRepository extends CoreRepository<BaseCompany, String> {

	BaseCompany findByCompanyUrl(String url);

}