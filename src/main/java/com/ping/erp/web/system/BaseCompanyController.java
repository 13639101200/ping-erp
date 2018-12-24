package com.ping.erp.web.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.SimpleResult;
import com.ping.erp.common.result.impl.SimpleResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.company.service.BaseCompanyService;
import com.ping.erp.system.menu.domain.BaseMenu;
import com.ping.erp.system.menu.service.BaseMenuService;

/**
 * 基础公司控制器
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 08:59:21
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Controller
@RequestMapping("system/baseCompany")
public class BaseCompanyController {

	/**
	 * 基础公司-系统链接
	 */
	@Value("#{'${define.system.company.basecompany.systemurl}'.split(',')}")
	private List<String> systemurl;

	/**
	 * 基础菜单-根菜单-ID
	 */
	@Value("${define.system.menu.basemenu.rootmenuid}")
	private String rootMenuId;

	@Autowired
	private BaseCompanyService service;
	@Autowired
	private BaseMenuService menuService;

	@RequestMapping("index")
	public String index() {
		try {
			return "system/baseCompany/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("page")
	@ResponseBody
	public PageResult<BaseCompany> page(String keyword, String field, String order, int size, int page) {
		try {
			List<String> fields = new ArrayList<String>();
			fields.add("companyName");
			fields.add("companyUrl");
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
				BaseCompany baseCompany = service.findOne(id);
				model.addAttribute("baseCompany", baseCompany);
			}
			return "system/baseCompany/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("save")
	@ResponseBody
	public SimpleResult save(BaseCompany entity) {
		try {
			for (String url : systemurl) {
				if (url.equals(entity.getCompanyUrl())) {
					return new SimpleResultImpl(-1, "增加失败：公司链接为系统链接");
				}
			}
			BaseCompany company = service.findByCompanyUrl(entity.getCompanyUrl());
			if (company != null) {
				return new SimpleResultImpl(-2, "增加失败：公司链接已经存在");
			}
			service.save(entity);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("delete")
	@ResponseBody
	public SimpleResult delete(@RequestBody List<BaseCompany> entities) {
		try {
			service.delete(entities);
			return new SimpleResultImpl(1, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "删除失败：数据存在关联");
		}
	}

	@RequestMapping("authEdit")
	public String authEdit() {
		try {
			return "system/baseCompany/authEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("authFind")
	@ResponseBody
	public Set<BaseMenu> authFind(BaseCompany company) {
		try {
			return menuService.findTreeByPidRelCompany(rootMenuId, company);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("authSave")
	@ResponseBody
	public SimpleResult authSave(@RequestBody BaseCompany entity) {
		try {
			service.authSave(entity);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

}