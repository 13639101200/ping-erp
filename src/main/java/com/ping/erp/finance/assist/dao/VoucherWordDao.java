package com.ping.erp.finance.assist.dao;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.assist.domain.VoucherWord;

/**
 * 凭证字数据访问接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:04:36
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface VoucherWordDao {

	PageResult<VoucherWord> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	VoucherWord findOne(String id);

	void save(VoucherWord entity);

	void delete(List<VoucherWord> entities);

	PageResult<VoucherWord> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

	List<VoucherWord> findByCompany(BaseCompany company);

}