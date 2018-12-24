package com.ping.erp.common.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ping.erp.common.util.SpringUtil;
import com.ping.erp.system.auth.domain.BaseAuth;
import com.ping.erp.system.menu.domain.BaseMenu;
import com.ping.erp.system.menu.service.BaseMenuService;

/**
 * 授权验证处理程序
 *
 * @version 1.2.1-RELEASE
 * @time 2018-12-15
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public class SecurityObjectPostProcessor<T extends FilterSecurityInterceptor> implements ObjectPostProcessor<T> {

	@Override
	public <O extends T> O postProcess(O object) {
		object.setSecurityMetadataSource(new SecurityMetadataSource());
		object.setAccessDecisionManager(new SecurityAccessDecisionManager());
		return object;
	}

}

/**
 * 安全元数据源管理类
 *
 * @version 1.2.1-RELEASE
 * @time 2018-12-15
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	/**
	 * 基础菜单-权限菜单-ID
	 */
	private String authtypeid = SpringUtil.getPropertyValue("define.system.menu.basemenu.menutype.authtypeid");

	private BaseMenuService menuService = SpringUtil.getBean(BaseMenuService.class);

	/**
	 * 权限表
	 */
	private Map<ConfigAttribute, List<String>> authorityTable;

	/**
	 * 加载权限表
	 */
	private void loadAuthorityTable() {
		authorityTable = new HashMap<ConfigAttribute, List<String>>();
		List<BaseMenu> menuList = menuService.findByTypeId(authtypeid);
		for (BaseMenu menu : menuList) {
			List<String> hrefList = new ArrayList<String>();
			for (BaseAuth auth : menu.getAuths()) {
				hrefList.add(auth.getAuthHref());
			}
			authorityTable.put(new SecurityConfig(menu.getMenuId()), hrefList);
		}
	}

	/**
	 * 获取可以访问地址的所有权限
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (authorityTable == null || authorityTable.size() <= 0) {
			loadAuthorityTable();
		}

		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		List<ConfigAttribute> authorityList = new ArrayList<ConfigAttribute>();
		for (Entry<ConfigAttribute, List<String>> entry : authorityTable.entrySet()) {
			for (String href : entry.getValue()) {
				if (new AntPathRequestMatcher(href).matches(request)) {
					authorityList.add(entry.getKey());
					break;
				}
			}
		}
		return authorityList;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}
}

/**
 * 访问决策管理器
 *
 * @version 1.2.1-RELEASE
 * @time 2018-12-15
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
class SecurityAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 权限对比并做决策
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		// 权限对比
		for (ConfigAttribute hrefAuth : configAttributes) {
			for (GrantedAuthority userAuth : authentication.getAuthorities()) {
				if (hrefAuth.getAttribute().equals(userAuth.getAuthority())) {
					return;
				}
			}
		}

		// 没有权限
		throw new InsufficientAuthenticationException("");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return false;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

}