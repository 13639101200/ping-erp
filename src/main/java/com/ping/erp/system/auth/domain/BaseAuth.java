package com.ping.erp.system.auth.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ping.erp.common.config.json.JacksonConfig;
import com.ping.erp.system.menu.domain.BaseMenu;

/**
 * 基础权限数据实体类
 *
 * @version 1.2.1-RELEASE
 * @time 2018-12-14 04:20:32
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
public class BaseAuth {
	/**
	 * 权限ID
	 */
	@Id
	private String authId;
	/**
	 * 菜单
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private BaseMenu menu;
	/**
	 * 权限链接
	 */
	private String authHref;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	public String getAuthId() {
		return this.authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public BaseMenu getMenu() {
		return menu;
	}

	public void setMenu(BaseMenu menu) {
		this.menu = menu;
	}

	public String getAuthHref() {
		return this.authHref;
	}

	public void setAuthHref(String authHref) {
		this.authHref = authHref;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}