package com.ping.erp.finance.subject.domain;

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
import com.ping.erp.basedata.subject.domain.TemplateSubject;
import com.ping.erp.common.config.json.JacksonConfig;
import com.ping.erp.common.util.StringUtil;
import com.ping.erp.finance.assist.domain.AssistUnit;
import com.ping.erp.finance.period.domain.FinancePeriod;
import com.ping.erp.system.code.domain.BaseCode;

/**
 * 结账科目数据实体类
 *
 * @version 1.1.4-RELEASE
 * @time 2018-11-30 06:05:35
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
public class ArchivedSubject {
	/**
	 * 科目ID
	 */
	@Id
	private String subjectId;
	/**
	 * 会计期间
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "period_id")
	private FinancePeriod period;
	/**
	 * 科目代码
	 */
	private String subjectCode;
	/**
	 * 科目名称
	 */
	private String subjectName;
	/**
	 * 科目类别
	 */
	@ManyToOne
	@JoinColumn(name = "subject_type")
	private BaseCode subjectType;
	/**
	 * 科目方向
	 */
	@ManyToOne
	@JoinColumn(name = "subject_direction")
	private BaseCode subjectDirection;
	/**
	 * 科目金额
	 */
	private Double subjectMoney;
	/**
	 * 辅计单位
	 */
	@ManyToOne
	@JoinColumn(name = "assist_unit")
	private AssistUnit assistUnit;
	/**
	 * 辅计数量
	 */
	private Double assistAmount;
	/**
	 * 辅助核算
	 */
	private String assistAccount;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	public ArchivedSubject() {
		super();
	}

	public ArchivedSubject(TemplateSubject subject, FinancePeriod period) {
		super();
		this.subjectId = StringUtil.getUUID();
		this.period = period;
		this.subjectCode = subject.getSubjectCode();
		this.subjectName = subject.getSubjectName();
		this.subjectType = subject.getSubjectType();
		this.subjectDirection = subject.getSubjectDirection();
	}

	public ArchivedSubject(ArchivedSubject archivedSubject, String subjectId, FinancePeriod period) {
		super();
		this.subjectId = subjectId;
		this.period = period;
		this.subjectCode = archivedSubject.getSubjectCode();
		this.subjectName = archivedSubject.getSubjectName();
		this.subjectType = archivedSubject.getSubjectType();
		this.subjectDirection = archivedSubject.getSubjectDirection();
		this.subjectMoney = archivedSubject.getSubjectMoney();
		this.assistUnit = archivedSubject.getAssistUnit();
		this.assistAmount = archivedSubject.getAssistAmount();
		this.assistAccount = archivedSubject.getAssistAccount();
	}

	public String getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public FinancePeriod getPeriod() {
		return period;
	}

	public void setPeriod(FinancePeriod period) {
		this.period = period;
	}

	public String getSubjectCode() {
		return this.subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public BaseCode getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(BaseCode subjectType) {
		this.subjectType = subjectType;
	}

	public BaseCode getSubjectDirection() {
		return subjectDirection;
	}

	public void setSubjectDirection(BaseCode subjectDirection) {
		this.subjectDirection = subjectDirection;
	}

	public Double getSubjectMoney() {
		return subjectMoney;
	}

	public void setSubjectMoney(Double subjectMoney) {
		this.subjectMoney = subjectMoney;
	}

	public AssistUnit getAssistUnit() {
		return assistUnit;
	}

	public void setAssistUnit(AssistUnit assistUnit) {
		this.assistUnit = assistUnit;
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

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}