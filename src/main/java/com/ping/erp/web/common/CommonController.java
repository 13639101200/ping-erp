package com.ping.erp.web.common;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ping.erp.common.action.SessionAction;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.company.service.BaseCompanyService;
import com.ping.erp.system.menu.domain.BaseMenu;
import com.ping.erp.system.menu.service.BaseMenuService;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 公共控制器
 *
 * @version 1.0.3-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Controller
public class CommonController {

	/**
	 * 基础菜单-根菜单-ID
	 */
	@Value("${define.system.menu.basemenu.rootmenuid}")
	private String rootMenuId;
	/**
	 * 菜单类型-后台菜单-ID
	 */
	@Value("${define.system.menu.basemenu.menutype.backtypeid}")
	private String backTypeId;

	@Autowired
	private SessionAction sessionAction;
	@Autowired
	private BaseCompanyService companyService;
	@Autowired
	private BaseMenuService menuService;

	@RequestMapping("test")
	public String test() {
		try {
			return "common/common/test";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("/")
	public String _default() {
		try {
			return "common/common/default";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("/{url}")
	public String index(Model model, @PathVariable String url) {
		try {
			BaseCompany company = companyService.findByCompanyUrl(url);
			if (company != null) {
				sessionAction.setCompany(company);
				model.addAttribute("company", company);
				return "common/common/index";
			}
			return "error/404";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("home")
	public String home(Model model) {
		try {
			BaseCompany company = sessionAction.getCompany();
			BaseUser user = sessionAction.getUser();
			Set<BaseMenu> menuTree = menuService.findTreeByUserAndPidAndTypeId(user, rootMenuId, backTypeId);
			model.addAttribute("company", company);
			model.addAttribute("user", user);
			model.addAttribute("menuTree", menuTree);
			return "common/common/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("page")
	public String page(Model model, String url) {
		try {
			model.addAttribute("url", url);
			return "common/common/page";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

}
