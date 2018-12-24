package com.ping.erp.web.runtime;

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
import com.ping.erp.runtime.access.domain.BaseAccess;
import com.ping.erp.runtime.access.service.BaseAccessService;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 访问统计控制器
 *
 * @version 1.0.9-RELEASE
 * @time 2018-11-20 05:33:27
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Controller
@RequestMapping("runtime/baseAccess")
public class BaseAccessController {

	@Autowired
	private BaseAccessService service;

	@RequestMapping("index")
	public String index() {
		try {
			return "runtime/baseAccess/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("page")
	@ResponseBody
	public PageResult<BaseAccess> page(String keyword, String field, String order, int size, int page) {
		try {
			List<String> fields = new ArrayList<String>();
			fields.add("user.userName");
			fields.add("clientIp");
			fields.add("serverUrl");
			PageResult<BaseAccess> pageResult = service.findAll(fields, keyword, field, order, size, page);
			for (BaseAccess access : pageResult.getData()) {
				BaseUser user = access.getUser();
				if (user != null) {
					user.toString();
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
				BaseAccess baseAccess = service.findOne(id);
				model.addAttribute("baseAccess", baseAccess);
			}
			return "runtime/baseAccess/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("save")
	@ResponseBody
	public SimpleResult save(BaseAccess entity) {
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
	public SimpleResult delete(@RequestBody List<BaseAccess> entities) {
		try {
			service.delete(entities);
			return new SimpleResultImpl(1, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

}