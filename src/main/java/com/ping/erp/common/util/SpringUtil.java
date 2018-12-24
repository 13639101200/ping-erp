package com.ping.erp.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * Spring工具类
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
public class SpringUtil implements ApplicationContextAware, EmbeddedValueResolverAware {

	private static ApplicationContext context;

	private static StringValueResolver resolver;

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	@SuppressWarnings("static-access")
	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		this.resolver = resolver;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) context.getBean(name);
	}

	public static <T> T getBean(Class<T> _class) {
		return context.getBean(_class);
	}

	public static String getPropertyValue(String name) {
		return resolver.resolveStringValue("${" + name + "}");
	}

}
