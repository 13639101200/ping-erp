package com.ping.erp.finance.subject.service;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.finance.subject.domain.InitialSubject;

/**
 * 初始科目业务服务接口
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:05:16
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface InitialSubjectService {

	PageResult<InitialSubject> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	InitialSubject findOne(String id);

	void save(InitialSubject entity);

	void delete(List<InitialSubject> entities);

	PageResult<InitialSubject> findByCompany(BaseCompany company, List<String> fields, String keyword, String field, String order, int size, int page);

}