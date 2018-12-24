package com.ping.erp.newcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库-表信息
 * 
 * @author Ping
 *
 */
public class TabInfo {

	/**
	 * 表名称
	 */
	private String tabName;
	/**
	 * 大驼峰转换表名称
	 */
	private String upperTabName;
	/**
	 * 小驼峰转换表名称
	 */
	private String lowerTabName;
	/**
	 * 表注释
	 */
	private String tabComment;
	/**
	 * 表主键
	 */
	private String tabKey;
	/**
	 * 大驼峰转换表主键
	 */
	private String upperTabKey;
	/**
	 * 小驼峰转换表主键
	 */
	private String lowerTabKey;
	/**
	 * 列信息
	 */
	private List<ColInfo> columns = new ArrayList<ColInfo>();

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String getUpperTabName() {
		return upperTabName;
	}

	public void setUpperTabName(String upperTabName) {
		this.upperTabName = upperTabName;
	}

	public String getLowerTabName() {
		return lowerTabName;
	}

	public void setLowerTabName(String lowerTabName) {
		this.lowerTabName = lowerTabName;
	}

	public String getTabComment() {
		return tabComment;
	}

	public void setTabComment(String tabComment) {
		this.tabComment = tabComment;
	}

	public String getTabKey() {
		return tabKey;
	}

	public void setTabKey(String tabKey) {
		this.tabKey = tabKey;
	}

	public String getUpperTabKey() {
		return upperTabKey;
	}

	public void setUpperTabKey(String upperTabKey) {
		this.upperTabKey = upperTabKey;
	}

	public String getLowerTabKey() {
		return lowerTabKey;
	}

	public void setLowerTabKey(String lowerTabKey) {
		this.lowerTabKey = lowerTabKey;
	}

	public List<ColInfo> getColumns() {
		return columns;
	}

	public void setColumns(List<ColInfo> columns) {
		this.columns = columns;
	}

}
