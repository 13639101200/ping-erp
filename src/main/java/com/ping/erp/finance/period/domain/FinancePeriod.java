package com.ping.erp.finance.period.domain;

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
 * 会计期间数据实体类
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:06:21
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
public class FinancePeriod {
	/**
	 * 期间ID
	 */
	@Id
	private String periodId;
	/**
	 * 公司
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private BaseCompany company;
	/**
	 * 初始时间
	 */
	private Timestamp initialTime;
	/**
	 * 结账时间
	 */
	private Timestamp archivedTime;
	/**
	 * 是否结账
	 */
	private Boolean isArchived;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	public String getPeriodId() {
		return this.periodId;
	}

	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}

	public BaseCompany getCompany() {
		return company;
	}

	public void setCompany(BaseCompany company) {
		this.company = company;
	}

	public Timestamp getInitialTime() {
		return this.initialTime;
	}

	public void setInitialTime(Timestamp initialTime) {
		this.initialTime = initialTime;
	}

	public Timestamp getArchivedTime() {
		return this.archivedTime;
	}

	public void setArchivedTime(Timestamp archivedTime) {
		this.archivedTime = archivedTime;
	}

	public Boolean getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}