package com.ping.erp.basedata.subject.domain;

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
import com.ping.erp.system.code.domain.BaseCode;

/**
 * 模板科目数据实体类
 *
 * @version 1.1.2-RELEASE
 * @time 2018-11-23 12:33:48
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
public class TemplateSubject {
	/**
	 * 科目ID
	 */
	@Id
	private String subjectId;
	/**
	 * 科目模板
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id")
	private SubjectTemplate template;
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
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	public String getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public SubjectTemplate getTemplate() {
		return template;
	}

	public void setTemplate(SubjectTemplate template) {
		this.template = template;
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

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}