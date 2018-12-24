package com.ping.erp.common.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ping.erp.common.action.SessionAction;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.company.service.BaseCompanyService;
import com.ping.erp.system.menu.domain.BaseMenu;
import com.ping.erp.system.menu.service.BaseMenuService;
import com.ping.erp.system.user.domain.UserLogin;
import com.ping.erp.system.user.service.UserLoginService;

/**
 * 用户信息服务
 *
 * @version 1.2.1-RELEASE
 * @time 2018-12-15
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Component
public class SecurityUserDetailsService implements UserDetailsService {

	/**
	 * 基础菜单-权限菜单-ID
	 */
	@Value("${define.system.menu.basemenu.menutype.authtypeid}")
	private String authtypeid;

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private SessionAction sessionAction;
	@Autowired
	private BaseCompanyService companyService;
	@Autowired
	private UserLoginService loginService;
	@Autowired
	private BaseMenuService menuService;

	/**
	 * 加载用户
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 验证公司链接
		String companyUrl = request.getParameter("companyUrl");
		if (companyUrl == null || "".equals(companyUrl)) {
			companyUrl = sessionAction.getCompany().getCompanyUrl();
		}
		if (companyUrl == null || "".equals(companyUrl)) {
			return null;
		}

		// 验证账号
		if (username == null || "".equals(username)) {
			return null;
		}

		// 验证密码
		String password = request.getParameter("password");
		if (password == null || "".equals(password)) {
			return null;
		}

		// 验证公司
		BaseCompany company = companyService.findByCompanyUrl(companyUrl);
		if (company == null) {
			return null;
		}

		// 验证用户
		UserLogin user = loginService.findByCompanyAndAccount(company, username);
		if (user == null) {
			return null;
		}

		// 获取权限
		List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		List<BaseMenu> menuList = menuService.findByUserAndTypeId(user.getUser(), authtypeid);
		for (BaseMenu menu : menuList) {
			authorityList.add(new SimpleGrantedAuthority(menu.getMenuId()));
		}

		// 预存公司信息和用户信息
		request.setAttribute("company", company);
		request.setAttribute("user", user.getUser());

		return new SecurityUserDetails(user.getAccount(), user.getPassword(), authorityList, user.getEnable());
	}

}

/**
 * 用户信息类
 *
 * @version 1.2.1-RELEASE
 * @time 2018-12-15
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
class SecurityUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	/**
	 * 账号
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 权限
	 */
	private Collection<? extends GrantedAuthority> authorities;
	/**
	 * 账号未过期
	 */
	private boolean accountNonExpired = true;
	/**
	 * 账号未锁定
	 */
	private boolean accountNonLocked = true;
	/**
	 * 凭证未过期
	 */
	private boolean credentialsNonExpired = true;
	/**
	 * 是否可用
	 */
	private boolean enabled;

	public SecurityUserDetails() {
		super();
	}

	public SecurityUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
