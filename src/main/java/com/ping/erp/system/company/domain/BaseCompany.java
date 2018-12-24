package com.ping.erp.system.company.domain;

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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ping.erp.common.config.json.JacksonConfig;
import com.ping.erp.system.menu.domain.BaseMenu;

/**
 * 基础公司数据实体类
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 08:59:21
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
public class BaseCompany {
	/**
	 * 公司ID
	 */
	@Id
	private String companyId;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 公司链接
	 */
	private String companyUrl;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "company_menu", joinColumns = { @JoinColumn(name = "company_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
	private Set<BaseMenu> menus = new HashSet<BaseMenu>();

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyUrl() {
		return this.companyUrl;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Set<BaseMenu> getMenus() {
		return menus;
	}

	public void setMenus(Set<BaseMenu> menus) {
		this.menus = menus;
	}
}