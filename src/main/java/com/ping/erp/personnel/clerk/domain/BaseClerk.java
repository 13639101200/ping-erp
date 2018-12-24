package com.ping.erp.personnel.clerk.domain;

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
import com.ping.erp.system.company.domain.BaseCompany;

/**
 * 基础职员数据实体类
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-26 12:27:38
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
public class BaseClerk {
	/**
	 * 职员ID
	 */
	@Id
	private String clerkId;
	/**
	 * 公司
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private BaseCompany company;
	/**
	 * 职员名称
	 */
	private String clerkName;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	public String getClerkId() {
		return this.clerkId;
	}

	public void setClerkId(String clerkId) {
		this.clerkId = clerkId;
	}

	public BaseCompany getCompany() {
		return company;
	}

	public void setCompany(BaseCompany company) {
		this.company = company;
	}

	public String getClerkName() {
		return this.clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}