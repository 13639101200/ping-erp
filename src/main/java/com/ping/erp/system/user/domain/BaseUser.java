package com.ping.erp.system.user.domain;

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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ping.erp.common.config.json.JacksonConfig;
import com.ping.erp.system.company.domain.BaseCompany;
import com.ping.erp.system.role.domain.BaseRole;

/**
 * 基础用户数据实体类
 *
 * @version 1.0.1-RELEASE
 * @time 2018-11-14 10:01:33
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
public class BaseUser {
	/**
	 * 用户ID
	 */
	@Id
	private String userId;
	/**
	 * 公司
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private BaseCompany company;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 身份证号
	 */
	private String userCard;
	/**
	 * 手机号码
	 */
	private String userPhone;
	/**
	 * 电子邮箱
	 */
	private String userEmail;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	/**
	 * 拥有角色
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<BaseRole> roles = new HashSet<BaseRole>();

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BaseCompany getCompany() {
		return company;
	}

	public void setCompany(BaseCompany company) {
		this.company = company;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCard() {
		return this.userCard;
	}

	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Set<BaseRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<BaseRole> roles) {
		this.roles = roles;
	}

}