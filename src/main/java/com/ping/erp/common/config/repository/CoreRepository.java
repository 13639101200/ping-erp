package com.ping.erp.common.config.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.ping.erp.system.company.domain.BaseCompany;

/**
 * 核心存储库接口
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@NoRepositoryBean
public interface CoreRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

	Page<T> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	Page<T> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

}
