package com.ping.erp.runtime.access.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ping.erp.common.config.json.JacksonConfig;
import com.ping.erp.system.user.domain.BaseUser;

/**
 * 访问统计数据实体类
 *
 * @version 1.0.9-RELEASE
 * @time 2018-11-20 05:33:27
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
public class BaseAccess {
	/**
	 * 访问ID
	 */
	@Id
	private String accessId;
	/**
	 * 访问用户
	 */
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private BaseUser user;
	/**
	 * 客户端地址
	 */
	private String clientIp;
	/**
	 * 服务器资源
	 */
	private String serverUrl;
	/**
	 * 累计访问次数
	 */
	private Integer accessTotal;
	/**
	 * 最后访问时间
	 */
	private Timestamp lastTime;
	/**
	 * 创建时间
	 */
	@Column(updatable = false)
	private Timestamp createTime;

	public String getAccessId() {
		return this.accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	public BaseUser getUser() {
		return user;
	}

	public void setUser(BaseUser user) {
		this.user = user;
	}

	public String getClientIp() {
		return this.clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getServerUrl() {
		return this.serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public Integer getAccessTotal() {
		return this.accessTotal;
	}

	public void setAccessTotal(Integer accessTotal) {
		this.accessTotal = accessTotal;
	}

	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}