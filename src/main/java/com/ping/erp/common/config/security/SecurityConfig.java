package com.ping.erp.common.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * SpringSecurity配置
 *
 * @version 1.2.1-RELEASE
 * @time 2018-12-15
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityUserDetailsService securityUserDetailsService;
	@Autowired
	private SecurityPasswordEncoder securityPasswordEncoder;
	@Autowired
	private SecurityAuthenticationHandler securityAuthenticationHandler;

	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		// 配置用户信息服务与密码编码器
		builder.userDetailsService(securityUserDetailsService).passwordEncoder(securityPasswordEncoder);
	}

	public void configure(WebSecurity web) throws Exception {
		// 忽略静态资源
		String antPatterns = "/css/**,/js/**,/ico/**,/images/**,/jquery-1.12.4/**,/uuid-1.4/**,/layui-2.4.5/**,/jquery-easyui-1.6.11/**,/zTree-3.5.33/**,/select2-4.0.5/**";
		web.ignoring().antMatchers(antPatterns.split(","));
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/").permitAll(); // 登录地址
		http.formLogin().loginProcessingUrl("/login"); // 登录处理地址
		http.formLogin().usernameParameter("account"); // 账号字段
		http.formLogin().passwordParameter("password"); // 密码字段
		http.formLogin().successHandler(securityAuthenticationHandler); // 登录成功处理
		http.formLogin().failureHandler(securityAuthenticationHandler); // 登录失败处理
		http.authorizeRequests().anyRequest().authenticated(); // 所有地址必须通过授权验证
		http.authorizeRequests().withObjectPostProcessor(new SecurityObjectPostProcessor<FilterSecurityInterceptor>()); // 授权验证处理程序
		http.logout().logoutUrl("/logout").logoutSuccessHandler(securityAuthenticationHandler); // 注销地址与注销成功处理
		http.logout().invalidateHttpSession(false); // 注销成功后不销毁会话
		http.csrf().disable(); // 禁用CSRF
	}

}
