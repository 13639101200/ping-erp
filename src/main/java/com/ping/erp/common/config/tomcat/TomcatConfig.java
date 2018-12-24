package com.ping.erp.common.config.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.Ssl;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Tomcat配置
 *
 * @version 1.1.1-RELEASE
 * @time 2018-11-22
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Configuration
public class TomcatConfig {
	/**
	 * 服务启动端口
	 */
	@Value("${server.port}")
	private int serverPort;
	/**
	 * 服务监听端口
	 */
	@Value("${define.server.port}")
	private int definePort;

	@Value("#{'${define.server.tomcat.ssl.ciphers}'.split(',')}")
	private String[] ciphers;

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			protected void postProcessContext(Context context) {

				SecurityCollection collection = new SecurityCollection();
				// 添加请求路径与请求方法
				collection.addPattern("/*");
				collection.addMethod("GET");
				collection.addMethod("POST");
				SecurityConstraint constraint = new SecurityConstraint();
				// 支持SSL配置
				constraint.setUserConstraint("CONFIDENTIAL");
				constraint.addCollection(collection);
				context.addConstraint(constraint);
			}
		};
		// 添加监听端口
		tomcat.addAdditionalTomcatConnectors(getConnector());

		// 配置SSL密码套件
		Ssl ssl = new Ssl();
		ssl.setCiphers(ciphers);
		tomcat.setSsl(ssl);
		return tomcat;
	}

	private Connector getConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(definePort);
		connector.setSecure(false);
		connector.setRedirectPort(serverPort);
		return connector;
	}
}
