package com.ping.erp.system.menu.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ping.erp.common.config.repository.CoreRepository;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.menu.domain.BaseMenu;
import com.ping.erp.system.role.domain.BaseRole;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 基础菜单数据存储接口
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 09:32:11
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseMenuRepository extends CoreRepository<BaseMenu, String> {

	@Query("select new BaseMenu(c.menuId, c.menuName, c.menuHref, c.menuTarget) from BaseUser a join a.roles b join b.menus c where a = :user and c.menuPid = :pid and c.menuType.codeId = :typeId order by c.orderNumber asc")
	Set<BaseMenu> findByUserAndPidAndTypeId(@Param("user") BaseUser user, @Param("pid") String pid, @Param("typeId") String typeId);

	@Query("select new BaseMenu(a.menuId, a.menuPid, a.menuName, case when a.menuId in (select c.menuId from BaseCompany b join b.menus c where b = :company) then true else false end) from BaseMenu a where a.menuPid = :pid order by a.orderNumber asc")
	Set<BaseMenu> findByPidRelCompany(@Param("pid") String pid, @Param("company") BaseCompany company);

	@Query("select new BaseMenu(b.menuId, b.menuPid, b.menuName, case when b.menuId in (select d.menuId from BaseRole c join c.menus d where c = :role) then true else false end) from BaseCompany a join a.menus b where a = :company and b.menuPid = :pid order by b.orderNumber asc")
	Set<BaseMenu> findByCompanyAndPidRelRole(@Param("company") BaseCompany company, @Param("pid") String pid, @Param("role") BaseRole role);

	List<BaseMenu> findByMenuPidOrderByOrderNumber(String pid);

	List<BaseMenu> findByMenuPid(String pid);

	@Query("select a from BaseMenu a join a.menuType b where b.codeId = :typeId")
	List<BaseMenu> findByTypeId(@Param("typeId") String typeId);

	@Query("select c from BaseUser a join a.roles b join b.menus c where a = :user and c.menuType.codeId = :typeId")
	List<BaseMenu> findByUserAndTypeId(@Param("user") BaseUser user, @Param("typeId") String typeId);

}