package com.ping.erp.newcode;

/**
 * 表信息-列信息
 * 
 * @author Ping
 *
 */
public class ColInfo {

	/**
	 * 列名称
	 */
	private String colName;
	/**
	 * 大驼峰转换列名称
	 */
	private String upperColName;
	/**
	 * 小驼峰转换列名称
	 */
	private String lowerColName;
	/**
	 * 列注释
	 */
	private String colComent;
	/**
	 * 列类型
	 */
	private String colType;

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getUpperColName() {
		return upperColName;
	}

	public void setUpperColName(String upperColName) {
		this.upperColName = upperColName;
	}

	public String getLowerColName() {
		return lowerColName;
	}

	public void setLowerColName(String lowerColName) {
		this.lowerColName = lowerColName;
	}

	public String getColComent() {
		return colComent;
	}

	public void setColComent(String colComent) {
		this.colComent = colComent;
	}

	public String getColType() {
		return colType;
	}

	public void setColType(String colType) {
		this.colType = colType;
	}

}
