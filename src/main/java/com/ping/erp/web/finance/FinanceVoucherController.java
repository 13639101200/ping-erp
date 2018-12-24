package com.ping.erp.web.finance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.erp.common.action.SessionAction;
import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.SimpleResult;
import com.ping.erp.common.result.impl.SimpleResultImpl;
import com.ping.erp.finance.assist.domain.VoucherWord;
import com.ping.erp.finance.assist.service.VoucherWordService;
import com.ping.erp.finance.voucher.domain.FinanceVoucher;
import com.ping.erp.finance.voucher.domain.VoucherDetail;
import com.ping.erp.finance.voucher.service.FinanceVoucherService;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 记账凭证控制器
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:06:44
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Controller
@RequestMapping("finance/financeVoucher")
public class FinanceVoucherController {

	@Autowired
	private SessionAction sessionAction;
	@Autowired
	private FinanceVoucherService service;
	@Autowired
	private VoucherWordService voucherWordService;

	@RequestMapping("index")
	public String index() {
		try {
			return "finance/financeVoucher/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("page")
	@ResponseBody
	public PageResult<FinanceVoucher> page(String keyword, String field, String order, int size, int page) {
		try {
			BaseCompany company = sessionAction.getCompany();
			List<String> fields = new ArrayList<String>();
			fields.add("voucherNumber");
			if (field == null || "".equals(field)) {
				field = "voucherNumber";
				order = "asc";
			}

			PageResult<FinanceVoucher> pageResult = service.findByCompanyAndCurrentPeriod(company, fields, keyword, field, order, size, page);
			for (FinanceVoucher voucher : pageResult.getData()) {
				for (VoucherDetail detail : voucher.getDetails()) {
					detail.setVoucher(null);
				}
			}
			return pageResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("edit")
	public String edit(Model model, String id) {
		try {
			if (id != null && !"".equals(id)) {
				FinanceVoucher financeVoucher = service.findOne(id);
				model.addAttribute("financeVoucher", financeVoucher);
			}
			BaseCompany company = sessionAction.getCompany();
			List<VoucherWord> wordList = voucherWordService.findByCompany(company);
			model.addAttribute("wordList", wordList);
			return "finance/financeVoucher/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("save")
	@ResponseBody
	public SimpleResult save(@RequestBody FinanceVoucher entity) {
		try {
			BaseCompany company = sessionAction.getCompany();
			BaseUser user = sessionAction.getUser();
			return service.saveToCurrentPeriod(company, user, entity);
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("delete")
	@ResponseBody
	public SimpleResult delete(@RequestBody List<FinanceVoucher> entities) {
		try {
			service.delete(entities);
			return new SimpleResultImpl(1, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("detail")
	public String detail(Model model, String id) {
		try {
			FinanceVoucher voucher = service.findOne(id);
			double voucherMoney = 0;
			for (VoucherDetail detail : voucher.getDetails()) {
				detail.setVoucher(null);
				voucherMoney += detail.getBorrowerMoney();
			}
			model.addAttribute("voucher", voucher);
			model.addAttribute("voucherMoney", voucherMoney);
			return "finance/financeVoucher/detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("signature")
	@ResponseBody
	public SimpleResult signature(String id) {
		try {
			BaseUser user = sessionAction.getUser();
			return service.signature(user, id);
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("audit")
	@ResponseBody
	public SimpleResult audit(String id) {
		try {
			BaseUser user = sessionAction.getUser();
			return service.audit(user, id);
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("account")
	@ResponseBody
	public SimpleResult account(String id) {
		try {
			BaseCompany company = sessionAction.getCompany();
			BaseUser user = sessionAction.getUser();
			return service.account(company, user, id);
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

}