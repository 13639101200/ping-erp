package com.ping.erp.system.menu.dao;

import java.util.List;
import java.util.Set;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.menu.domain.BaseMenu;
import com.ping.erp.system.role.domain.BaseRole;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 基础菜单数据访问接口
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 09:32:11
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
public interface BaseMenuDao {

	PageResult<BaseMenu> findAll(List<String> fields, String keyword, String field, String order, int size, int page);

	BaseMenu findOne(String id);

	void save(BaseMenu entity);

	void delete(List<BaseMenu> entities);

	Set<BaseMenu> findByUserAndPidAndTypeId(BaseUser user, String pid, String typeId);

	Set<BaseMenu> findByPidRelCompany(String pid, BaseCompany company);

	Set<BaseMenu> findByCompanyAndPidRelRole(BaseCompany company, String pid, BaseRole role);

	List<BaseMenu> findByMenuPidOrderByOrderNumber(String pid);

	List<BaseMenu> findByMenuPid(String pid);

	List<BaseMenu> findByTypeId(String typeId);

	List<BaseMenu> findByUserAndTypeId(BaseUser user, String typeId);

}