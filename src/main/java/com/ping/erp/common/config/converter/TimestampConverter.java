package com.ping.erp.common.config.converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import com.ping.erp.common.util.StringUtil;

/**
 * 时间戳转换器
 *
 * @version 1.1.7-RELEASE
 * @time 2018-12-10
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Configuration
public class TimestampConverter implements Converter<String, Timestamp> {

	@Override
	public Timestamp convert(String source) {
		try {
			if (source == null || "".equals(source)) {
				return null;
			}
			if (StringUtil.isInteger(source)) {
				return new Timestamp(Long.valueOf(source));
			}
			SimpleDateFormat format = new SimpleDateFormat(getPattern(source.length()));
			return new Timestamp(format.parse(source).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getPattern(int length) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		if (length == 10) {
			pattern = "yyyy-MM-dd";
		}
		return pattern;
	}

}
