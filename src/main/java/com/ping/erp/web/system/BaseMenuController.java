package com.ping.erp.web.system;

import java.util.ArrayList;
import java.util.List;

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
import com.ping.erp.system.code.domain.BaseCode;
import com.ping.erp.system.code.service.BaseCodeService;
import com.ping.erp.system.menu.domain.BaseMenu;
import com.ping.erp.system.menu.service.BaseMenuService;

/**
 * 基础菜单控制器
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 09:32:11
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Controller
@RequestMapping("system/baseMenu")
public class BaseMenuController {

	/**
	 * 基础菜单-根菜单-ID
	 */
	@Value("${define.system.menu.basemenu.rootmenuid}")
	private String rootMenuId;

	@Autowired
	private BaseMenuService service;
	@Autowired
	private BaseCodeService codeService;

	@RequestMapping("index")
	public String index() {
		try {
			return "system/baseMenu/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("page")
	@ResponseBody
	public PageResult<BaseMenu> page(String keyword, String field, String order, int size, int page) {
		try {
			List<String> fields = new ArrayList<String>();
			fields.add("menuPid");
			fields.add("menuName");
			fields.add("menuHref");
			fields.add("menuType.codeValue");
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
				BaseMenu baseMenu = service.findOne(id);
				model.addAttribute("baseMenu", baseMenu);
			}
			List<BaseCode> typeList = codeService.findByTypeCode("base_menu_menu_type");
			List<BaseCode> targetList = codeService.findByTypeCode("base_menu_menu_target");
			model.addAttribute("typeList", typeList);
			model.addAttribute("targetList", targetList);
			return "system/baseMenu/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("save")
	@ResponseBody
	public SimpleResult save(BaseMenu entity) {
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
	public SimpleResult delete(@RequestBody List<BaseMenu> entities) {
		try {
			for (BaseMenu menu : entities) {
				List<BaseMenu> menus = service.findByMenuPid(menu.getMenuId());
				if (menus != null && menus.size() > 0) {
					return new SimpleResultImpl(-1, "删除失败：菜单“" + menu.getMenuName() + "”存在子菜单");
				}
			}
			service.delete(entities);
			return new SimpleResultImpl(1, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "删除失败：数据存在关联");
		}
	}

	@RequestMapping("verifyExist")
	@ResponseBody
	public SimpleResult verifyExist(String id) {
		try {
			if (id == null || "".equals(id)) {
				return new SimpleResultImpl(-1, "菜单不存在");
			}
			if (id.equals(rootMenuId)) {
				return new SimpleResultImpl(1, "菜单存在");
			}
			BaseMenu menu = service.findOne(id);
			if (menu == null) {
				return new SimpleResultImpl(-1, "菜单不存在");
			} else {
				return new SimpleResultImpl(1, "菜单存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("treeIndex")
	public String treeIndex() {
		try {
			return "system/baseMenu/treeIndex";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("children")
	@ResponseBody
	public List<BaseMenu> children(String menuId) {
		if (menuId == null || "".equals(menuId)) {
			menuId = rootMenuId;
		}
		return service.findByMenuPidOrderByOrderNumber(menuId);
	}

	@RequestMapping("treeEdit")
	public String treeEdit(Model model, String pid, String id) {
		try {
			if (pid != null && !"".equals(pid)) {
				BaseMenu baseMenu = new BaseMenu();
				baseMenu.setMenuPid(pid);
				model.addAttribute("baseMenu", baseMenu);
			}
			if (id != null && !"".equals(id)) {
				BaseMenu baseMenu = service.findOne(id);
				model.addAttribute("baseMenu", baseMenu);
			}
			List<BaseCode> typeList = codeService.findByTypeCode("base_menu_menu_type");
			List<BaseCode> targetList = codeService.findByTypeCode("base_menu_menu_target");
			model.addAttribute("typeList", typeList);
			model.addAttribute("targetList", targetList);
			return "system/baseMenu/treeEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

}