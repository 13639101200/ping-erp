package com.ping.erp.system.user.service;

import java.util.List;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.domain.UserLogin;

/**
 * 用户登录业务服务接口
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 10:26:27
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface UserLoginService {

	PageResult<UserLogin> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	UserLogin findOne(String id);

	void save(UserLogin entity);

	void delete(List<UserLogin> entities);

	UserLogin findByCompanyAndAccount(BaseCompany company, String account);

	void accountsave(UserLogin entity);

	void disableSwitch(String userId);

}