package com.ping.erp.finance.voucher.domain;

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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ping.erp.common.config.json.JacksonConfig;
import com.ping.erp.finance.assist.domain.VoucherWord;
import com.ping.erp.finance.period.domain.FinancePeriod;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 记账凭证数据实体类
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:06:44
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
public class FinanceVoucher {
	/**
	 * 凭证ID
	 */
	@Id
	private String voucherId;
	/**
	 * 会计期间
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "period_id")
	private FinancePeriod period;
	/**
	 * 凭证字
	 */
	@ManyToOne
	@JoinColumn(name = "voucher_word")
	private VoucherWord voucherWord;
	/**
	 * 凭证号
	 */
	private String voucherNumber;
	/**
	 * 制单日期
	 */
	private Timestamp voucherTime;
	/**
	 * 附件数
	 */
	private Integer accessoryNumber;
	/**
	 * 制单人
	 */
	@ManyToOne
	@JoinColumn(name = "fill_user")
	private BaseUser fillUser;
	/**
	 * 签字人
	 */
	@ManyToOne
	@JoinColumn(name = "signature_user")
	private BaseUser signatureUser;
	/**
	 * 审核人
	 */
	@ManyToOne
	@JoinColumn(name = "audit_user")
	private BaseUser auditUser;
	/**
	 * 记账人
	 */
	@ManyToOne
	@JoinColumn(name = "account_user")
	private BaseUser accountUser;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	/**
	 * 凭证明细
	 */
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE }, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "voucher_id")
	private Set<VoucherDetail> details = new HashSet<VoucherDetail>();

	public String getVoucherId() {
		return this.voucherId;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}

	public FinancePeriod getPeriod() {
		return period;
	}

	public void setPeriod(FinancePeriod period) {
		this.period = period;
	}

	public VoucherWord getVoucherWord() {
		return voucherWord;
	}

	public void setVoucherWord(VoucherWord voucherWord) {
		this.voucherWord = voucherWord;
	}

	public String getVoucherNumber() {
		return this.voucherNumber;
	}

	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}

	public Timestamp getVoucherTime() {
		return this.voucherTime;
	}

	public void setVoucherTime(Timestamp voucherTime) {
		this.voucherTime = voucherTime;
	}

	public Integer getAccessoryNumber() {
		return this.accessoryNumber;
	}

	public void setAccessoryNumber(Integer accessoryNumber) {
		this.accessoryNumber = accessoryNumber;
	}

	public BaseUser getFillUser() {
		return fillUser;
	}

	public void setFillUser(BaseUser fillUser) {
		this.fillUser = fillUser;
	}

	public BaseUser getSignatureUser() {
		return signatureUser;
	}

	public void setSignatureUser(BaseUser signatureUser) {
		this.signatureUser = signatureUser;
	}

	public BaseUser getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(BaseUser auditUser) {
		this.auditUser = auditUser;
	}

	public BaseUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(BaseUser accountUser) {
		this.accountUser = accountUser;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Set<VoucherDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<VoucherDetail> details) {
		this.details = details;
	}

}