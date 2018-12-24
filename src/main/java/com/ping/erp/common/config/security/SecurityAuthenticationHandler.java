package com.ping.erp.common.config.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ping.erp.common.action.SessionAction;
import com.ping.erp.common.result.SimpleResult;
import com.ping.erp.common.result.impl.SimpleResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 身份验证处理器
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
public class SecurityAuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler, LogoutSuccessHandler {

	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private SessionAction sessionAction;

	/**
	 * 登录成功处理
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		BaseCompany company = (BaseCompany) request.getAttribute("company");
		BaseUser user = (BaseUser) request.getAttribute("user");
		sessionAction.setCompany(company);
		sessionAction.setUser(user);

		SimpleResult result = new SimpleResultImpl(1, "登录成功").setUrl("/home");
		String json = mapper.writeValueAsString(result);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");

		PrintWriter writer = response.getWriter();
		writer.append(json);
		writer.flush();
		writer.close();
	}

	/**
	 * 登录失败处理
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		SimpleResult result = new SimpleResultImpl(-1, "登录失败：信息有误");
		String json = mapper.writeValueAsString(result);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");

		PrintWriter writer = response.getWriter();
		writer.append(json);
		writer.flush();
		writer.close();
	}

	/**
	 * 注销成功处理
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		BaseCompany company = sessionAction.getCompany();
		SimpleResult result = new SimpleResultImpl(1, "注销成功").setUrl("/" + company.getCompanyUrl());
		String json = mapper.writeValueAsString(result);

		sessionAction.destroy();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");

		PrintWriter writer = response.getWriter();
		writer.append(json);
		writer.flush();
		writer.close();
	}

}
