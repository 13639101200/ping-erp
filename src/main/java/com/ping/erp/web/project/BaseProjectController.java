package com.ping.erp.web.project;

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
import com.ping.erp.project.project.domain.BaseProject;
import com.ping.erp.project.project.service.BaseProjectService;
import com.ping.erp.system.company.domain.BaseCompany;

/**
 * 基础项目控制器
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-26 12:05:23
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Controller
@RequestMapping("project/baseProject")
public class BaseProjectController {

	@Autowired
	private SessionAction sessionAction;
	@Autowired
	private BaseProjectService service;

	@RequestMapping("index")
	public String index() {
		try {
			return "project/baseProject/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("page")
	@ResponseBody
	public PageResult<BaseProject> page(String keyword, String field, String order, int size, int page) {
		try {
			BaseCompany company = sessionAction.getCompany();
			List<String> fields = new ArrayList<String>();
			fields.add("projectName");
			return service.findByCompany(company, fields, keyword, field, order, size, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("edit")
	public String edit(Model model, String id) {
		try {
			if (id != null && !"".equals(id)) {
				BaseProject baseProject = service.findOne(id);
				model.addAttribute("baseProject", baseProject);
			}
			return "project/baseProject/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("save")
	@ResponseBody
	public SimpleResult save(BaseProject entity) {
		try {
			BaseCompany company = sessionAction.getCompany();
			entity.setCompany(company);
			service.save(entity);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("delete")
	@ResponseBody
	public SimpleResult delete(@RequestBody List<BaseProject> entities) {
		try {
			service.delete(entities);
			return new SimpleResultImpl(1, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

}