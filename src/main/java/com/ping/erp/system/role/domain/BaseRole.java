package com.ping.erp.system.role.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ping.erp.common.config.json.JacksonConfig;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.menu.domain.BaseMenu;

/**
 * 基础角色数据实体类
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 09:41:19
 *
 * @author Ping
 * @phone 13639101200
 * @email 13639101200@163.com
 *
 */
@Entity
@DynamicInsert
@DynamicUpdate
@JsonSerialize(using = JacksonConfig.class)
public class BaseRole {
	/**
	 * 角色ID
	 */
	@Id
	private String roleId;
	/**
	 * 公司
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private BaseCompany company;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	/**
	 * 用于zTree
	 */
	@Transient
	private Boolean checked;

	/**
	 * 拥有菜单
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_menu", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
	private Set<BaseMenu> menus = new HashSet<BaseMenu>();

	public BaseRole() {
		super();
	}

	public BaseRole(String roleId, String roleName, Boolean checked) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.checked = checked;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public BaseCompany getCompany() {
		return company;
	}

	public void setCompany(BaseCompany company) {
		this.company = company;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Set<BaseMenu> getMenus() {
		return menus;
	}

	public void setMenus(Set<BaseMenu> menus) {
		this.menus = menus;
	}
}