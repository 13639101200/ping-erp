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

import com.ping.erp.common.action.SessionAction;
import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.SimpleResult;
import com.ping.erp.common.result.impl.SimpleResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.company.service.BaseCompanyService;
import com.ping.erp.system.menu.domain.BaseMenu;
import com.ping.erp.system.menu.service.BaseMenuService;
import com.ping.erp.system.role.domain.BaseRole;
import com.ping.erp.system.role.service.BaseRoleService;

/**
 * 基础角色控制器
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 09:41:19
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Controller
@RequestMapping("system/baseRole")
public class BaseRoleController {

	/**
	 * 基础菜单-根菜单-ID
	 */
	@Value("${define.system.menu.basemenu.rootmenuid}")
	private String rootMenuId;

	@Autowired
	private SessionAction sessionAction;
	@Autowired
	private BaseRoleService service;
	@Autowired
	private BaseCompanyService companyService;
	@Autowired
	private BaseMenuService menuService;

	@RequestMapping("index")
	public String index() {
		try {
			return "system/baseRole/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("page")
	@ResponseBody
	public PageResult<BaseRole> page(String keyword, String field, String order, int size, int page) {
		try {
			List<String> fields = new ArrayList<String>();
			fields.add("roleName");
			PageResult<BaseRole> pageResult = service.findAll(fields, keyword, field, order, size, page);
			for (BaseRole role : pageResult.getData()) {
				role.getCompany().toString();
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
				BaseRole baseRole = service.findOne(id);
				model.addAttribute("baseRole", baseRole);
			}
			List<BaseCompany> companyList = companyService.findAll();
			model.addAttribute("companyList", companyList);
			return "system/baseRole/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("save")
	@ResponseBody
	public SimpleResult save(BaseRole entity) {
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
	public SimpleResult delete(@RequestBody List<BaseRole> entities) {
		try {
			service.delete(entities);
			return new SimpleResultImpl(1, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "删除失败：数据存在关联");
		}
	}

	@RequestMapping("companyIndex")
	public String companyIndex() {
		try {
			return "system/baseRole/companyIndex";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("companyPage")
	@ResponseBody
	public PageResult<BaseRole> companyPage(String keyword, String field, String order, int size, int page) {
		try {
			BaseCompany company = sessionAction.getCompany();
			List<String> fields = new ArrayList<String>();
			fields.add("roleName");
			return service.findByCompany(company, fields, keyword, field, order, size, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("companyEdit")
	public String companyEdit(Model model, String id) {
		try {
			if (id != null && !"".equals(id)) {
				BaseRole baseRole = service.findOne(id);
				model.addAttribute("baseRole", baseRole);
			}
			return "system/baseRole/companyEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("companySave")
	@ResponseBody
	public SimpleResult companySave(BaseRole entity) {
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

	@RequestMapping("companyDelete")
	@ResponseBody
	public SimpleResult companyDelete(@RequestBody List<BaseRole> entities) {
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
			return "system/baseRole/authEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("authFind")
	@ResponseBody
	public Set<BaseMenu> authFind(BaseRole role) {
		try {
			return menuService.findTreeByCompanyAndPidRelRole(rootMenuId, role);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("authSave")
	@ResponseBody
	public SimpleResult authSave(@RequestBody BaseRole entity) {
		try {
			service.authSave(entity);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("companyAuthEdit")
	public String companyAuthEdit() {
		try {
			return "system/baseRole/companyAuthEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("companyAuthFind")
	@ResponseBody
	public Set<BaseMenu> companyAuthFind(BaseRole role) {
		try {
			return menuService.findTreeByCompanyAndPidRelRole(rootMenuId, role);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("companyAuthSave")
	@ResponseBody
	public SimpleResult companyAuthSave(@RequestBody BaseRole entity) {
		try {
			service.authSave(entity);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

}