package com.cams.blaze.request;
/**
 * 自定义字段
 * @author YuHuaPeng
 *
 */
public class UserDefinedField {
	/**名称*/
	private String name;
	/**数据类型*/
	private String dataType;
	/**值ֵ*/
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


}
