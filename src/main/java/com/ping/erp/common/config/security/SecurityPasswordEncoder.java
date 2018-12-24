package com.ping.erp.common.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ping.erp.common.util.StringUtil;

/**
 * 密码编码器
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
public class SecurityPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return null;
	}

	/**
	 * 密码匹配
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return StringUtil.getMD5(rawPassword.toString()).equals(encodedPassword);
	}

}
