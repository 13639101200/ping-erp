package com.ping.erp.system.menu.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ping.erp.common.config.json.JacksonConfig;
import com.ping.erp.system.auth.domain.BaseAuth;
import com.ping.erp.system.code.domain.BaseCode;

/**
 * 基础菜单数据实体类
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 09:32:11
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
public class BaseMenu {
	/**
	 * 菜单ID
	 */
	@Id
	private String menuId;
	/**
	 * 菜单PID
	 */
	private String menuPid;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 菜单链接
	 */
	private String menuHref;
	/**
	 * 菜单类型
	 */
	@ManyToOne
	@JoinColumn(name = "menu_type")
	private BaseCode menuType;
	/**
	 * 打开方式
	 */
	@ManyToOne
	@JoinColumn(name = "menu_target")
	private BaseCode menuTarget;
	/**
	 * 排序编号
	 */
	private Integer orderNumber;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	/**
	 * 权限
	 */
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Set<BaseAuth> auths = new HashSet<BaseAuth>();

	/**
	 * 子级菜单
	 */
	@Transient
	private Set<BaseMenu> children;
	/**
	 * 用于zTree
	 */
	@Transient
	private Boolean checked;

	public BaseMenu() {
		super();
	}

	public BaseMenu(String menuId, String menuName, String menuHref, BaseCode menuTarget) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuHref = menuHref;
		this.menuTarget = menuTarget;
	}

	public BaseMenu(String menuId, String menuPid, String menuName, Boolean checked) {
		super();
		this.menuId = menuId;
		this.menuPid = menuPid;
		this.menuName = menuName;
		this.checked = checked;
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuPid() {
		return this.menuPid;
	}

	public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuHref() {
		return this.menuHref;
	}

	public void setMenuHref(String menuHref) {
		this.menuHref = menuHref;
	}

	public BaseCode getMenuType() {
		return this.menuType;
	}

	public void setMenuType(BaseCode menuType) {
		this.menuType = menuType;
	}

	public BaseCode getMenuTarget() {
		return this.menuTarget;
	}

	public void setMenuTarget(BaseCode menuTarget) {
		this.menuTarget = menuTarget;
	}

	public Integer getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Set<BaseAuth> getAuths() {
		return auths;
	}

	public void setAuths(Set<BaseAuth> auths) {
		this.auths = auths;
	}

	public Set<BaseMenu> getChildren() {
		return children;
	}

	public void setChildren(Set<BaseMenu> children) {
		this.children = children;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}