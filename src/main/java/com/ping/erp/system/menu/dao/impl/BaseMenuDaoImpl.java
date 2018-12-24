package com.ping.erp.system.menu.dao.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.result.impl.PageResultImpl;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.menu.dao.BaseMenuDao;
import com.ping.erp.system.menu.domain.BaseMenu;
import com.ping.erp.system.menu.repository.BaseMenuRepository;
import com.ping.erp.system.role.domain.BaseRole;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 基础菜单数据访问接口实现
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 09:32:11
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Repository
public class BaseMenuDaoImpl implements BaseMenuDao {

	@Autowired
	private BaseMenuRepository repository;

	public PageResult<BaseMenu> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		Page<BaseMenu> springPage = repository.findAll(fields, keyword, field, order, size, page);
		return new PageResultImpl<BaseMenu>(springPage.getTotalElements(), springPage.getContent());
	}

	public BaseMenu findOne(String id) {
		return repository.findOne(id);
	}

	public void save(BaseMenu entity) {
		repository.save(entity);
	}

	public void delete(List<BaseMenu> entities) {
		repository.delete(entities);
	}

	@Override
	public Set<BaseMenu> findByUserAndPidAndTypeId(BaseUser user, String pid, String typeId) {
		return repository.findByUserAndPidAndTypeId(user, pid, typeId);
	}

	@Override
	public Set<BaseMenu> findByPidRelCompany(String pid, BaseCompany company) {
		return repository.findByPidRelCompany(pid, company);
	}

	@Override
	public Set<BaseMenu> findByCompanyAndPidRelRole(BaseCompany company, String pid, BaseRole role) {
		return repository.findByCompanyAndPidRelRole(company, pid, role);
	}

	@Override
	public List<BaseMenu> findByMenuPidOrderByOrderNumber(String pid) {
		return repository.findByMenuPidOrderByOrderNumber(pid);
	}

	@Override
	public List<BaseMenu> findByMenuPid(String pid) {
		return repository.findByMenuPid(pid);
	}

	@Override
	public List<BaseMenu> findByTypeId(String typeId) {
		return repository.findByTypeId(typeId);
	}

	@Override
	public List<BaseMenu> findByUserAndTypeId(BaseUser user, String typeId) {
		return repository.findByUserAndTypeId(user, typeId);
	}

}