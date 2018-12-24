package com.ping.erp.finance.voucher.service;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.SimpleResult;
import com.ping.erp.finance.voucher.domain.FinanceVoucher;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 记账凭证业务服务接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:06:44
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface FinanceVoucherService {

	PageResult<FinanceVoucher> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	FinanceVoucher findOne(String id);

	void save(FinanceVoucher entity);

	void delete(List<FinanceVoucher> entities);

	PageResult<FinanceVoucher> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

	SimpleResult saveToCurrentPeriod(BaseCompany company, BaseUser user, FinanceVoucher entity);

	PageResult<FinanceVoucher> findByCompanyAndCurrentPeriod(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

	SimpleResult signature(BaseUser user, String id);

	SimpleResult audit(BaseUser user, String id);

	SimpleResult account(BaseCompany company, BaseUser user, String id);

}