package com.ping.erp.system.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ping.erp.common.config.repository.CoreRepository;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.role.domain.BaseRole;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 基础角色数据存储接口
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 09:41:19
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseRoleRepository extends CoreRepository<BaseRole, String> {

	@Query("select new BaseRole(a.roleId, a.roleName, case when a.roleId in (select c.roleId from BaseUser b join b.roles c where b = :user) then true else false end) from BaseRole a where a.company = :company order by a.createTime desc")
	List<BaseRole> findByCompanyRelUser(@Param("company") BaseCompany company, @Param("user") BaseUser user);

}