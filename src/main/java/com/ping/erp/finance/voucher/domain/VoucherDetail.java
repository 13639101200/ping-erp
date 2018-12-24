package com.ping.erp.finance.voucher.domain;

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
import com.ping.erp.finance.subject.domain.ArchivedSubject;

/**
 * 凭证明细数据实体类
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:07:49
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
public class VoucherDetail {
	/**
	 * 明细ID
	 */
	@Id
	private String detailId;
	/**
	 * 记账凭证
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voucher_id")
	private FinanceVoucher voucher;
	/**
	 * 结账科目
	 */
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private ArchivedSubject subject;
	/**
	 * 摘要
	 */
	private String detailAbstract;
	/**
	 * 借方金额
	 */
	private Double borrowerMoney;
	/**
	 * 贷方金额
	 */
	private Double lenderMoney;
	/**
	 * 辅计数量
	 */
	private Double assistAmount;
	/**
	 * 辅助核算
	 */
	private String assistAccount;
	/**
	 * 辅助对象
	 */
	private String assistObject;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	public String getDetailId() {
		return this.detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public FinanceVoucher getVoucher() {
		return voucher;
	}

	public void setVoucher(FinanceVoucher voucher) {
		this.voucher = voucher;
	}

	public ArchivedSubject getSubject() {
		return subject;
	}

	public void setSubject(ArchivedSubject subject) {
		this.subject = subject;
	}

	public String getDetailAbstract() {
		return this.detailAbstract;
	}

	public void setDetailAbstract(String detailAbstract) {
		this.detailAbstract = detailAbstract;
	}

	public Double getBorrowerMoney() {
		return borrowerMoney;
	}

	public void setBorrowerMoney(Double borrowerMoney) {
		this.borrowerMoney = borrowerMoney;
	}

	public Double getLenderMoney() {
		return lenderMoney;
	}

	public void setLenderMoney(Double lenderMoney) {
		this.lenderMoney = lenderMoney;
	}

	public Double getAssistAmount() {
		return assistAmount;
	}

	public void setAssistAmount(Double assistAmount) {
		this.assistAmount = assistAmount;
	}

	public String getAssistAccount() {
		return this.assistAccount;
	}

	public void setAssistAccount(String assistAccount) {
		this.assistAccount = assistAccount;
	}

	public String getAssistObject() {
		return this.assistObject;
	}

	public void setAssistObject(String assistObject) {
		this.assistObject = assistObject;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}