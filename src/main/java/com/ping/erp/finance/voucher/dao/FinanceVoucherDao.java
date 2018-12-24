package com.ping.erp.finance.voucher.dao;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.period.domain.FinancePeriod;
import com.ping.erp.finance.voucher.domain.FinanceVoucher;

/**
 * 记账凭证数据访问接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:06:44
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface FinanceVoucherDao {

	PageResult<FinanceVoucher> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	FinanceVoucher findOne(String id);

	void save(FinanceVoucher entity);

	void delete(List<FinanceVoucher> entities);

	PageResult<FinanceVoucher> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

	PageResult<FinanceVoucher> findByPeriod(FinancePeriod period, List<String> fields, String keyword, String field, String order, int size, int page);

}