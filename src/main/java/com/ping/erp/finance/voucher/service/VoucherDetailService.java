package com.ping.erp.finance.voucher.service;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.voucher.domain.VoucherDetail;

/**
 * 凭证明细业务服务接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:07:49
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface VoucherDetailService {

	PageResult<VoucherDetail> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	VoucherDetail findOne(String id);

	void save(VoucherDetail entity);

	void delete(List<VoucherDetail> entities);

	PageResult<VoucherDetail> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

}