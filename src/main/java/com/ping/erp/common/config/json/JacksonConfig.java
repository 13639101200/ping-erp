package com.ping.erp.common.config.json;

import java.io.IOException;
import java.lang.reflect.Field;

import org.hibernate.collection.internal.AbstractPersistentCollection;
import org.hibernate.proxy.HibernateProxy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ping.erp.common.util.StringUtil;

/**
 * Jackson配置
 *
 * @version 1.0.4-RELEASE
 * @time 2018-11-17
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public class JacksonConfig extends JsonSerializer<Object> {

	@Override
	public void serialize(Object object, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
		try {
			generator.writeStartObject();

			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object fieldObject = field.get(object);
				// 属性为空
				if (fieldObject == null) {
					generator.writeNullField(field.getName());
					continue;
				}
				// 属性为"一对一"或"多对一"
				if (fieldObject instanceof HibernateProxy && ((HibernateProxy) fieldObject).getHibernateLazyInitializer().isUninitialized()) {
					// 未加载
					generator.writeNullField(field.getName());
					continue;
				} else if (fieldObject instanceof HibernateProxy) {
					// 已加载
					generator.writeObjectField(field.getName(), ((HibernateProxy) fieldObject).getHibernateLazyInitializer().getImplementation());
					continue;
				}
				// 属性为"一对多"或"多对多"
				if (fieldObject instanceof AbstractPersistentCollection && !((AbstractPersistentCollection) fieldObject).wasInitialized()) {
					// 未加载
					generator.writeNullField(field.getName());
					continue;
				} else if (fieldObject instanceof AbstractPersistentCollection) {
					// 已加载
					generator.writeObjectField(field.getName(), fieldObject);
					continue;
				}

				// 属性的get方法标记JsonIgnore注解
				JsonIgnore jsonIgnore = object.getClass().getMethod(StringUtil.humpConverLower("get_" + field.getName())).getAnnotation(JsonIgnore.class);
				if (jsonIgnore != null) {
					generator.writeNullField(field.getName());
					continue;
				}

				// 属性为普通属性
				generator.writeObjectField(field.getName(), fieldObject);
			}

			generator.writeEndObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
