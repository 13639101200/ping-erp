package com.ping.erp.common.config.json;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.ping.erp.common.util.StringUtil;

/**
 * 时间戳反序列化器
 *
 * @version 1.1.7-RELEASE
 * @time 2018-12-10
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@JsonComponent
public class TimestampDeserializer extends JsonDeserializer<Timestamp> {

	@Override
	public Timestamp deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		try {
			String source = parser.getText();
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
