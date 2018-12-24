package com.ping.erp.web.finance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.SimpleResult;
import com.ping.erp.common.result.impl.SimpleResultImpl;
import com.ping.erp.finance.period.domain.FinancePeriod;
import com.ping.erp.finance.period.service.FinancePeriodService;

/**
 * 会计期间控制器
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:06:21
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Controller
@RequestMapping("finance/financePeriod")
public class FinancePeriodController {

	@Autowired
	private FinancePeriodService service;

	@RequestMapping("index")
	public String index() {
		try {
			return "finance/financePeriod/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("page")
	@ResponseBody
	public PageResult<FinancePeriod> page(String keyword, String field, String order, int size, int page) {
		try {
			List<String> fields = new ArrayList<String>();
			return service.findAll(fields, keyword, field, order, size, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("edit")
	public String edit(Model model, String id) {
		try {
			if (id != null && !"".equals(id)) {
				FinancePeriod financePeriod = service.findOne(id);
				model.addAttribute("financePeriod", financePeriod);
			}
			return "finance/financePeriod/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("save")
	@ResponseBody
	public SimpleResult save(FinancePeriod entity) {
		try {
			service.save(entity);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("delete")
	@ResponseBody
	public SimpleResult delete(@RequestBody List<FinancePeriod> entities) {
		try {
			service.delete(entities);
			return new SimpleResultImpl(1, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

}