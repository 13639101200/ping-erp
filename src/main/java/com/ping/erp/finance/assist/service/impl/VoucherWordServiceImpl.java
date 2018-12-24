package com.ping.erp.finance.assist.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.assist.dao.VoucherWordDao;
import com.ping.erp.finance.assist.domain.VoucherWord;
import com.ping.erp.finance.assist.service.VoucherWordService;

/**
 * 凭证字业务服务接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:04:36
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class VoucherWordServiceImpl implements VoucherWordService {

	@Autowired
	private VoucherWordDao dao;

	public PageResult<VoucherWord> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public VoucherWord findOne(String id) {
		return dao.findOne(id);
	}

	public void save(VoucherWord entity) {
		if (entity.getWordId() == null || "".equals(entity.getWordId())) {
			entity.setWordId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<VoucherWord> entities) {
		dao.delete(entities);
	}

	public PageResult<VoucherWord> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

	@Override
	public List<VoucherWord> findByCompany(BaseCompany company) {
		return dao.findByCompany(company);
	}

}