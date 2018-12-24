package com.ping.erp.system.user.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ping.erp.common.config.json.JacksonConfig;

/**
 * 用户登录数据实体类
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 10:26:27
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
public class UserLogin {
	/**
	 * 用户ID
	 */
	@Id
	private String userId;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 是否可用
	 */
	private Boolean enable;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	/**
	 * 基础用户
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private BaseUser user;

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public BaseUser getUser() {
		return user;
	}

	public void setUser(BaseUser user) {
		this.user = user;
	}

}