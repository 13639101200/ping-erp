package ${package}.${parent_module}.${child_module}.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ping.erp.common.config.json.JacksonConfig;

/**
 * ${tabInfo.tabComment}数据实体类
 *
 * @version ${version}
 * @time ${time}
 *
 * @author ${author}
 * @phone ${phone}
 * @email ${email}
 *
 */
@Entity
@DynamicInsert
@DynamicUpdate
@JsonSerialize(using = JacksonConfig.class)
public class ${tabInfo.upperTabName} {
	<#-- 遍历字段 -->
	<#list tabInfo.columns as column>
	/**
	 * ${column.colComent}
	 */
	<#if tabInfo.tabKey = column.colName>
	@Id
	</#if>
	<#if column.lowerColName = 'createTime'>
	@Column(updatable=false)
	</#if>
	<#switch column.colType>
	<#case "DATETIME">
	private Timestamp ${column.lowerColName};
	<#break>
	<#case "INT">
	private Integer ${column.lowerColName};
	<#break>
	<#default>
	private String ${column.lowerColName};
	</#switch>
	</#list>
	<#-- get和set方法 -->
	<#list tabInfo.columns as column>
	<#switch column.colType>
	<#case "DATETIME">

	public Timestamp get${column.upperColName}() {
		return this.${column.lowerColName};
	}

	public void set${column.upperColName}(Timestamp ${column.lowerColName}) {
		this.${column.lowerColName} = ${column.lowerColName};
	}
	<#break>
	<#case "INT">

	public Integer get${column.upperColName}() {
		return this.${column.lowerColName};
	}

	public void set${column.upperColName}(Integer ${column.lowerColName}) {
		this.${column.lowerColName} = ${column.lowerColName};
	}
	<#break>
	<#default>

	public String get${column.upperColName}() {
		return this.${column.lowerColName};
	}

	public void set${column.upperColName}(String ${column.lowerColName}) {
		this.${column.lowerColName} = ${column.lowerColName};
	}
	</#switch>
	</#list>
}