package com.ping.erp.common.config.repository.factory;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import com.ping.erp.common.config.repository.impl.CoreRepositoryImpl;

/**
 * 核心存储库工厂
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public class CoreRepositoryFactory extends JpaRepositoryFactory {

	private EntityManager entityManager;

	public CoreRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
	}

	/**
	 * 获取核心存储库类型
	 */
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return CoreRepositoryImpl.class;
	}

	/**
	 * 获取核心存储库实例
	 */
	protected Object getTargetRepository(RepositoryInformation information) {
		return new CoreRepositoryImpl<>(information.getDomainType(), entityManager);
	}

}
