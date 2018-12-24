package com.ping.erp.web.system;

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
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.company.service.BaseCompanyService;
import com.ping.erp.system.role.domain.BaseRole;
import com.ping.erp.system.role.service.BaseRoleService;
import com.ping.erp.system.user.domain.BaseUser;
import com.ping.erp.system.user.domain.UserLogin;
import com.ping.erp.system.user.service.BaseUserService;
import com.ping.erp.system.user.service.UserLoginService;

/**
 * 基础用户控制器
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 10:01:33
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Controller
@RequestMapping("system/baseUser")
public class BaseUserController {

	@Autowired
	private SessionAction sessionAction;
	@Autowired
	private BaseUserService service;
	@Autowired
	private BaseCompanyService companyService;
	@Autowired
	private BaseRoleService roleService;
	@Autowired
	private UserLoginService loginService;

	@RequestMapping("index")
	public String index() {
		try {
			return "system/baseUser/index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("page")
	@ResponseBody
	public PageResult<BaseUser> page(String keyword, String field, String order, int size, int page) {
		try {
			List<String> fields = new ArrayList<String>();
			fields.add("userName");
			fields.add("userCard");
			fields.add("userPhone");
			fields.add("userEmail");
			PageResult<BaseUser> pageResult = service.findAll(fields, keyword, field, order, size, page);
			for (BaseUser user : pageResult.getData()) {
				user.getCompany().toString();
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
				BaseUser baseUser = service.findOne(id);
				model.addAttribute("baseUser", baseUser);
			}
			List<BaseCompany> companyList = companyService.findAll();
			model.addAttribute("companyList", companyList);
			return "system/baseUser/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("save")
	@ResponseBody
	public SimpleResult save(BaseUser entity) {
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
	public SimpleResult delete(@RequestBody List<BaseUser> entities) {
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
			return "system/baseUser/companyIndex";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("companyPage")
	@ResponseBody
	public PageResult<BaseUser> companyPage(String keyword, String field, String order, int size, int page) {
		try {
			BaseCompany company = sessionAction.getCompany();
			List<String> fields = new ArrayList<String>();
			fields.add("userName");
			fields.add("userCard");
			fields.add("userPhone");
			fields.add("userEmail");
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
				BaseUser baseUser = service.findOne(id);
				model.addAttribute("baseUser", baseUser);
			}
			return "system/baseUser/companyEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("companySave")
	@ResponseBody
	public SimpleResult companySave(BaseUser entity) {
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
	public SimpleResult companyDelete(@RequestBody List<BaseUser> entities) {
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
			return "system/baseUser/authEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("authFind")
	@ResponseBody
	public List<BaseRole> authFind(BaseUser user) {
		try {
			return roleService.findByCompanyRelUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("authSave")
	@ResponseBody
	public SimpleResult authSave(@RequestBody BaseUser entity) {
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
			return "system/baseUser/companyAuthEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("companyAuthFind")
	@ResponseBody
	public List<BaseRole> companyAuthFind(BaseUser user) {
		try {
			return roleService.findByCompanyRelUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("companyAuthSave")
	@ResponseBody
	public SimpleResult companyAuthSave(@RequestBody BaseUser entity) {
		try {
			service.authSave(entity);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("accountEdit")
	public String accountEdit(Model model, String id) {
		try {
			if (id != null && !"".equals(id)) {
				UserLogin userLogin = loginService.findOne(id);
				if (userLogin == null) {
					userLogin = new UserLogin();
					userLogin.setUserId(id);
				}
				model.addAttribute("userLogin", userLogin);
			}
			return "system/baseUser/accountEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("accountSave")
	@ResponseBody
	public SimpleResult accountSave(UserLogin entity) {
		try {
			BaseUser user = service.findOne(entity.getUserId());
			UserLogin login = loginService.findByCompanyAndAccount(user.getCompany(), entity.getAccount());
			if (login != null && !login.getUserId().equals(entity.getUserId())) {
				return new SimpleResultImpl(-1, "设置失败：账号已经存在");
			}
			loginService.accountsave(entity);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("companyAccountEdit")
	public String companyAccountEdit(Model model, String id) {
		try {
			if (id != null && !"".equals(id)) {
				UserLogin userLogin = loginService.findOne(id);
				if (userLogin == null) {
					userLogin = new UserLogin();
					userLogin.setUserId(id);
				}
				model.addAttribute("userLogin", userLogin);
			}
			return "system/baseUser/companyAccountEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("companyAccountSave")
	@ResponseBody
	public SimpleResult companyAccountSave(UserLogin entity) {
		try {
			BaseUser user = service.findOne(entity.getUserId());
			UserLogin login = loginService.findByCompanyAndAccount(user.getCompany(), entity.getAccount());
			if (login != null && !login.getUserId().equals(entity.getUserId())) {
				return new SimpleResultImpl(-1, "设置失败：账号已经存在");
			}
			loginService.accountsave(entity);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("disableEdit")
	public String disableEdit(Model model, String id) {
		try {
			if (id != null && !"".equals(id)) {
				UserLogin userLogin = loginService.findOne(id);
				model.addAttribute("userLogin", userLogin);
			}
			return "system/baseUser/disableEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("disableSave")
	@ResponseBody
	public SimpleResult disableSave(String userId) {
		try {
			loginService.disableSwitch(userId);
			return new SimpleResultImpl(1, "设置成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("companyDisableEdit")
	public String companyDisableEdit(Model model, String id) {
		try {
			if (id != null && !"".equals(id)) {
				UserLogin userLogin = loginService.findOne(id);
				model.addAttribute("userLogin", userLogin);
			}
			return "system/baseUser/companyDisableEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("companyDisableSave")
	@ResponseBody
	public SimpleResult companyDisableSave(String userId) {
		try {
			loginService.disableSwitch(userId);
			return new SimpleResultImpl(1, "设置成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("baseEdit")
	public String baseEdit(Model model) {
		try {
			BaseUser baseUser = sessionAction.getUser();
			model.addAttribute("baseUser", baseUser);
			return "system/baseUser/baseEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("passwordEdit")
	public String passwordEdit() {
		try {
			return "system/baseUser/passwordEdit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500";
		}
	}

	@RequestMapping("baseSave")
	@ResponseBody
	public SimpleResult baseSave(BaseUser entity) {
		try {
			service.baseSave(entity);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

	@RequestMapping("passwordSave")
	@ResponseBody
	public SimpleResult passwordSave(String password, String passwordNew) {
		try {
			BaseUser user = sessionAction.getUser();
			UserLogin login = loginService.findOne(user.getUserId());
			if (!login.getPassword().equals(StringUtil.getMD5(password))) {
				return new SimpleResultImpl(-1, "旧密码错误");
			}
			login.setPassword(StringUtil.getMD5(passwordNew));
			loginService.save(login);
			return new SimpleResultImpl(1, "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new SimpleResultImpl(0, "未知错误");
		}
	}

}