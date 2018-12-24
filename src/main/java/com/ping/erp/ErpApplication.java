package com.ping.erp;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ping.erp.common.config.repository.factory.CoreRepositoryFactoryBean;

/**
 * ERP应用程序启动类
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@SpringBootApplication
@ServletComponentScan
@EnableJpaRepositories(repositoryFactoryBeanClass = CoreRepositoryFactoryBean.class)
public class ErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}

	@PostConstruct
	void start() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
	}

}
