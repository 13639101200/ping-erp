package com.ping.erp.common.config.json;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 双精度反序列化器
 *
 * @version 1.1.9-RELEASE
 * @time 2018-12-12
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@JsonComponent
public class DoubleDeserializer extends JsonDeserializer<Double> {

	@Override
	public Double deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		String source = parser.getText();
		if (source == null || "".equals(source)) {
			return null;
		}
		source = source.replace(",", "");
		return Double.valueOf(source);
	}

}
