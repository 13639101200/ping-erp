package com.ping.erp.common.config.repository.factory;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * 核心存储库工厂Bean
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public class CoreRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
		extends JpaRepositoryFactoryBean<T, S, ID> {

	public CoreRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
		super(repositoryInterface);
	}

	/**
	 * 创建核心存储库工厂
	 */
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new CoreRepositoryFactory(entityManager);
	}

}
