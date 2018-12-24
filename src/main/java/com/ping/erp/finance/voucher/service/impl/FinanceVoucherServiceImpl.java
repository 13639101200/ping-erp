package com.ping.erp.finance.voucher.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.SimpleResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.common.result.impl.SimpleResultImpl;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.finance.period.dao.FinancePeriodDao;
import com.ping.erp.finance.period.domain.FinancePeriod;
import com.ping.erp.finance.subject.domain.ArchivedSubject;
import com.ping.erp.finance.voucher.dao.FinanceVoucherDao;
import com.ping.erp.finance.voucher.domain.FinanceVoucher;
import com.ping.erp.finance.voucher.domain.VoucherDetail;
import com.ping.erp.finance.voucher.service.FinanceVoucherService;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 记账凭证业务服务接口实现
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:06:44
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class FinanceVoucherServiceImpl implements FinanceVoucherService {

	@Autowired
	private FinanceVoucherDao dao;
	@Autowired
	private FinancePeriodDao financePeriodDao;

	public PageResult<FinanceVoucher> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public FinanceVoucher findOne(String id) {
		return dao.findOne(id);
	}

	public void save(FinanceVoucher entity) {
		if (entity.getVoucherId() == null || "".equals(entity.getVoucherId())) {
			entity.setVoucherId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<FinanceVoucher> entities) {
		dao.delete(entities);
	}

	public PageResult<FinanceVoucher> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findByCompany(company, fields, keyword, field, order, size, page);
	}

	@Override
	public SimpleResult saveToCurrentPeriod(BaseCompany company, BaseUser user, FinanceVoucher entity) {
		double borrowTotal = 0;
		double lenderTotal = 0;
		for (VoucherDetail detail : entity.getDetails()) {
			borrowTotal += detail.getBorrowerMoney();
			lenderTotal += detail.getLenderMoney();
		}
		if (borrowTotal != lenderTotal) {
			return new SimpleResultImpl(-1, "保存失败：借贷不平衡");
		}

		FinancePeriod period = financePeriodDao.findByCompanyAndNoArchived(company);
		entity.setPeriod(period);
		entity.setFillUser(user);
		if (entity.getVoucherId() == null || "".equals(entity.getVoucherId())) {
			entity.setVoucherId(StringUtil.getUUID());
		}
		dao.save(entity);
		return new SimpleResultImpl(1, "保存成功");
	}

	@Override
	public PageResult<FinanceVoucher> findByCompanyAndCurrentPeriod(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page) {
		FinancePeriod period = financePeriodDao.findByCompanyAndNoArchived(company);
		if (period == null) {
			return new PageResultImpl<FinanceVoucher>(0, new ArrayList<FinanceVoucher>());
		} else {
			return dao.findByPeriod(period, fields, keyword, field, order, size, page);
		}
	}

	@Override
	public SimpleResult signature(BaseUser user, String id) {
		FinanceVoucher voucher = dao.findOne(id);
		if (voucher.getSignatureUser() != null) {
			return new SimpleResultImpl(-1, "签字失败：凭证已经签字");
		}
		voucher.setSignatureUser(user);
		dao.save(voucher);
		return new SimpleResultImpl(1, "签字成功");
	}

	@Override
	public SimpleResult audit(BaseUser user, String id) {
		FinanceVoucher voucher = dao.findOne(id);
		if (voucher.getAuditUser() != null) {
			return new SimpleResultImpl(-1, "审核失败：凭证已经审核");
		}
		voucher.setAuditUser(user);
		dao.save(voucher);
		return new SimpleResultImpl(1, "审核成功");
	}

	@Override
	public SimpleResult account(BaseCompany company, BaseUser user, String id) {
		FinanceVoucher voucher = dao.findOne(id);
		if (voucher.getAccountUser() != null) {
			return new SimpleResultImpl(-1, "记账失败：凭证已经记账");
		}
		voucher.setAccountUser(user);
		dao.save(voucher);

		for (VoucherDetail detail : voucher.getDetails()) {
			ArchivedSubject subject = detail.getSubject();
			// 借方
			if (detail.getBorrowerMoney() != 0) {
				subject.setSubjectMoney(subject.getSubjectMoney() + detail.getBorrowerMoney());
				subject.setAssistAmount(subject.getAssistAmount() + detail.getAssistAmount());
			}
			// 贷方
			if (detail.getLenderMoney() != 0) {
				subject.setSubjectMoney(subject.getSubjectMoney() - detail.getLenderMoney());
				subject.setAssistAmount(subject.getAssistAmount() - detail.getAssistAmount());
			}
		}

		return new SimpleResultImpl(1, "记账成功");
	}

}