package com.ping.erp.finance.assist.domain;

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
 * 凭证字数据实体类
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:04:36
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
public class VoucherWord {
	/**
	 * 字ID
	 */
	@Id
	private String wordId;
	/**
	 * 公司
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private BaseCompany company;
	/**
	 * 字名称
	 */
	private String wordName;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	public String getWordId() {
		return this.wordId;
	}

	public void setWordId(String wordId) {
		this.wordId = wordId;
	}

	public BaseCompany getCompany() {
		return company;
	}

	public void setCompany(BaseCompany company) {
		this.company = company;
	}

	public String getWordName() {
		return this.wordName;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}