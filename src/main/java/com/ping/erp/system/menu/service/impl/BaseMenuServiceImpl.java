package com.ping.erp.system.menu.service.impl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.erp.common.result.PageResult;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.menu.dao.BaseMenuDao;
import com.ping.erp.system.menu.domain.BaseMenu;
import com.ping.erp.system.menu.service.BaseMenuService;
import com.ping.erp.system.role.dao.BaseRoleDao;
import com.ping.erp.system.role.domain.BaseRole;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 基础菜单业务服务接口实现
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 09:32:11
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Service
@Transactional
public class BaseMenuServiceImpl implements BaseMenuService {

	@Autowired
	private BaseMenuDao dao;
	@Autowired
	private BaseRoleDao roleDao;

	public PageResult<BaseMenu> findAll(List<String> fields, String keyword, String field, String order, int size, int page) {
		return dao.findAll(fields, keyword, field, order, size, page);
	}

	public BaseMenu findOne(String id) {
		return dao.findOne(id);
	}

	public void save(BaseMenu entity) {
		if (entity.getMenuId() == null || "".equals(entity.getMenuId())) {
			entity.setMenuId(StringUtil.getUUID());
		}
		dao.save(entity);
	}

	public void delete(List<BaseMenu> entities) {
		dao.delete(entities);
	}

	@Override
	public Set<BaseMenu> findTreeByUserAndPidAndTypeId(BaseUser user, String pid, String typeId) {
		Set<BaseMenu> menuList = dao.findByUserAndPidAndTypeId(user, pid, typeId);
		buildMenuTreeForBack(menuList, user, typeId);
		return menuList;
	}

	private void buildMenuTreeForBack(Set<BaseMenu> menuList, BaseUser user, String typeId) {
		if (menuList != null && menuList.size() > 0) {
			for (BaseMenu menu : menuList) {
				Set<BaseMenu> menus = dao.findByUserAndPidAndTypeId(user, menu.getMenuId(), typeId);
				menu.setChildren(menus);
				buildMenuTreeForBack(menus, user, typeId);
			}
		}
	}

	@Override
	public Set<BaseMenu> findTreeByPidRelCompany(String pid, BaseCompany company) {
		Set<BaseMenu> menuList = dao.findByPidRelCompany(pid, company);
		buildMenuTreeForCompany(menuList, company);
		return menuList;
	}

	private void buildMenuTreeForCompany(Set<BaseMenu> menuList, BaseCompany company) {
		if (menuList != null && menuList.size() > 0) {
			for (BaseMenu menu : menuList) {
				Set<BaseMenu> menus = dao.findByPidRelCompany(menu.getMenuId(), company);
				menu.setChildren(menus);
				buildMenuTreeForCompany(menus, company);
			}
		}
	}

	@Override
	public Set<BaseMenu> findTreeByCompanyAndPidRelRole(String pid, BaseRole role) {
		BaseCompany company = roleDao.findOne(role.getRoleId()).getCompany();
		Set<BaseMenu> menuList = dao.findByCompanyAndPidRelRole(company, pid, role);
		buildMenuTreeForRole(menuList, company, role);
		return menuList;
	}

	private void buildMenuTreeForRole(Set<BaseMenu> menuList, BaseCompany company, BaseRole role) {
		if (menuList != null && menuList.size() > 0) {
			for (BaseMenu menu : menuList) {
				Set<BaseMenu> menus = dao.findByCompanyAndPidRelRole(company, menu.getMenuId(), role);
				menu.setChildren(menus);
				buildMenuTreeForRole(menus, company, role);
			}
		}
	}

	@Override
	public List<BaseMenu> findByMenuPidOrderByOrderNumber(String pid) {
		return dao.findByMenuPidOrderByOrderNumber(pid);
	}

	@Override
	public List<BaseMenu> findByMenuPid(String pid) {
		return dao.findByMenuPid(pid);
	}

	@Override
	public List<BaseMenu> findByTypeId(String typeId) {
		List<BaseMenu> menus = dao.findByTypeId(typeId);
		for (BaseMenu menu : menus) {
			menu.getAuths().size();
		}
		return menus;
	}

	@Override
	public List<BaseMenu> findByUserAndTypeId(BaseUser user, String typeId) {
		return dao.findByUserAndTypeId(user, typeId);
	}

}