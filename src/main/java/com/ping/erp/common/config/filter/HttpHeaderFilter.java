package com.ping.erp.common.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpHeader配置过滤器
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
public class HttpHeaderFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// 域名使用https请求；max-age：一定时间(秒)内使用https请求；includeSubDomains：适用于子域名
		httpResponse.setHeader("Strict-Transport-Security", "max-age=31536000;includeSubDomains");
		// 资源白名单；default-src：全局资源；self：来自本域名；unsafe-eval：允许eval机制；unsafe-inline：允许内联脚本；
		httpResponse.setHeader("Content-Security-Policy", "default-src 'self' 'unsafe-eval' 'unsafe-inline';");
		// 遵循Content-Type对MIME的设定
		httpResponse.setHeader("X-Content-Type-Options", "nosniff");
		// 禁止加载跨站脚本
		httpResponse.setHeader("X-XSS-Protection", "1");
		// 禁止页面缓存
		httpResponse.setHeader("Cache-control", "no-cache");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

}
