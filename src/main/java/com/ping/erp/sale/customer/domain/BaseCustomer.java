package com.ping.erp.sale.customer.domain;

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
 * 基础客户数据实体类
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-25 11:58:01
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
public class BaseCustomer {
	/**
	 * 客户ID
	 */
	@Id
	private String customerId;
	/**
	 * 公司
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private BaseCompany company;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BaseCompany getCompany() {
		return company;
	}

	public void setCompany(BaseCompany company) {
		this.company = company;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}