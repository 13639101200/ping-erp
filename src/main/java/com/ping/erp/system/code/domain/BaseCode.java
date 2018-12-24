package com.ping.erp.system.code.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ping.erp.common.config.json.JacksonConfig;

/**
 * 基础编码数据实体类
 *
 * @version 1.0.0-RELEASE
 * @time 2018-11-14 04:37:41
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
public class BaseCode {
	/**
	 * 编码ID
	 */
	@Id
	private String codeId;
	/**
	 * 类型代码
	 */
	private String typeCode;
	/**
	 * 编码类型
	 */
	private String codeType;
	/**
	 * 编码码值
	 */
	private String codeValue;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	public BaseCode() {
		super();
	}

	public BaseCode(String codeId) {
		super();
		this.codeId = codeId;
	}

	public String getCodeId() {
		return this.codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getCodeType() {
		return this.codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeValue() {
		return this.codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}