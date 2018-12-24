package com.ping.erp.finance.voucher.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.voucher.dao.VoucherDetailDao;
import com.ping.erp.finance.voucher.domain.VoucherDetail;
import com.ping.erp.finance.voucher.repository.VoucherDetailRepository;

/**
 * 凭证明细数据访问接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:07:49
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class VoucherDetailDaoImpl implements VoucherDetailDao {

	@Autowired
	private VoucherDetailRepository repository;

	public PageResult<VoucherDetail> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<VoucherDetail> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<VoucherDetail>(springPage.getTotalElements(), springPage.getContent());
	}

	public VoucherDetail findOne(String id) {
		return repository.findOne(id);
	}

	public void save(VoucherDetail entity) {
		repository.save(entity);
	}

	public void delete(List<VoucherDetail> entities) {
		repository.delete(entities);
	}

	public PageResult<VoucherDetail> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<VoucherDetail> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<VoucherDetail>(springPage.getTotalElements(), springPage.getContent());
	}

}