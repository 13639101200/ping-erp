package com.ping.erp.web.system;

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
import com.ping.erp.system.auth.domain.BaseAuth;
import com.ping.erp.system.auth.service.BaseAuthService;

/**
 * 基础权限控制器
 *
 * @version 1.2.1-RELEASE
 * @time 2018-12-14 04:20:32
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Controller
@RequestMapping("system/baseAuth")
public class BaseAuthController {

	@Autowired
	private BaseAuthService service;

	@RequestMapping("index")
	public String index() {
		try {
			return "system/baseAuth/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("page")
	@ResponseBody
	public PageResult<BaseAuth> page(String keyword, String field, String order, int size, int page) {
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
				BaseAuth baseAuth = service.findOne(id);
				model.addAttribute("baseAuth", baseAuth);
			}
			return "system/baseAuth/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("save")
	@ResponseBody
	public SimpleResult save(BaseAuth entity) {
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
	public SimpleResult delete(@RequestBody List<BaseAuth> entities) {
		try {
			service.delete(entities);
			return new SimpleResultImpl(1, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

}