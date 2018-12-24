package com.ping.erp.common.config.filter;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ping.erp.common.action.SessionAction;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.runtime.access.domain.BaseAccess;
import com.ping.erp.runtime.access.service.BaseAccessService;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 访问统计过滤器
 *
 * @version 1.0.9-RELEASE
 * @time 2018-11-20
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@WebFilter(urlPatterns = "/*")
public class AccessFilter implements Filter {

	@Autowired
	private SessionAction sessionAction;
	@Autowired
	private BaseAccessService accessService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		BaseUser user = sessionAction.getUser();
		String clientIp = getClientIp(request);
		String serverUrl = getServerUrl(request);

		int index = serverUrl.indexOf(".");
		if (index < 0) {
			BaseAccess access = accessService.findByUserAndClientIpAndServerUrl(user, clientIp, serverUrl);
			if (access == null) {
				access = new BaseAccess();
				access.setAccessId(StringUtil.getUUID());
				access.setUser(user);
				access.setClientIp(clientIp);
				access.setServerUrl(serverUrl);
			} else {
				access.setAccessTotal(access.getAccessTotal() + 1);
				access.setLastTime(new Timestamp(new Date().getTime()));
			}
			accessService.save(access);
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

	private String getClientIp(ServletRequest request) {
		String ip = ((HttpServletRequest) request).getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = ((HttpServletRequest) request).getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	private String getServerUrl(ServletRequest request) {
		return ((HttpServletRequest) request).getServletPath();
	}

}
