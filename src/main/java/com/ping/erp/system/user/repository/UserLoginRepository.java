package com.ping.erp.system.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ping.erp.common.config.repository.CoreRepository;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.user.domain.UserLogin;

/**
 * 用户登录数据存储接口
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 10:26:27
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface UserLoginRepository extends CoreRepository<UserLogin, String> {

	@Query("select a from UserLogin a where a.user.company = :company and a.account = :account")
	UserLogin findByCompanyAndAccount(@Param("company") BaseCompany company, @Param("account") String account);

}