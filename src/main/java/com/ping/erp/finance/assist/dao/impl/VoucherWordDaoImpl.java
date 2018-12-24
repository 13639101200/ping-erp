package com.ping.erp.finance.assist.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.assist.dao.VoucherWordDao;
import com.ping.erp.finance.assist.domain.VoucherWord;
import com.ping.erp.finance.assist.repository.VoucherWordRepository;

/**
 * 凭证字数据访问接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:04:36
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class VoucherWordDaoImpl implements VoucherWordDao {

	@Autowired
	private VoucherWordRepository repository;

	public PageResult<VoucherWord> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<VoucherWord> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<VoucherWord>(springPage.getTotalElements(), springPage.getContent());
	}

	public VoucherWord findOne(String id) {
		return repository.findOne(id);
	}

	public void save(VoucherWord entity) {
		repository.save(entity);
	}

	public void delete(List<VoucherWord> entities) {
		repository.delete(entities);
	}

	public PageResult<VoucherWord> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<VoucherWord> springPage = repository.findByCompany(company, fields, keyword, field, order, size, page);
		return new PageResultImpl<VoucherWord>(springPage.getTotalElements(), springPage.getContent());
	}

	@Override
	public List<VoucherWord> findByCompany(BaseCompany company) {
		return repository.findByCompany(company);
	}

}