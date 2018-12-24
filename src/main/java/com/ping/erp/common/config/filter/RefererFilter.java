package com.ping.erp.common.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

/**
 * 跨站点请求过滤器
 *
 * @version 1.1.1-RELEASE
 * @time 2018-11-22
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@WebFilter(urlPatterns = "/*")
public class RefererFilter implements Filter {
	/**
	 * 服务启动地址
	 */
	@Value("${define.server.addr}")
	private String serverAddr;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String referer = ((HttpServletRequest) request).getHeader("Referer");
		if (referer == null || referer.indexOf(serverAddr) >= 0) {
			chain.doFilter(request, response);
		} else {
			request.getRequestDispatcher("/").forward(request, response);
		}

	}

	@Override
	public void destroy() {

	}

}
