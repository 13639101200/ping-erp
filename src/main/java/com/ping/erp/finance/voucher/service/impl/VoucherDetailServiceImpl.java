package com.ping.erp.finance.voucher.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.voucher.dao.VoucherDetailDao;
import com.ping.erp.finance.voucher.domain.VoucherDetail;
import com.ping.erp.finance.voucher.service.VoucherDetailService;

/**
 * 凭证明细业务服务接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:07:49
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class VoucherDetailServiceImpl implements VoucherDetailService {

	@Autowired
	private VoucherDetailDao dao;

	public PageResult<VoucherDetail> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public VoucherDetail findOne(String id) {
		return dao.findOne(id);
	}

	public void save(VoucherDetail entity) {
		if (entity.getDetailId() == null || "".equals(entity.getDetailId())) {
			entity.setDetailId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<VoucherDetail> entities) {
		dao.delete(entities);
	}

	public PageResult<VoucherDetail> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

}