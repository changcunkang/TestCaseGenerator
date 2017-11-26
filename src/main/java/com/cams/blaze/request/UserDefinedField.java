package com.cams.blaze.request;

import javax.persistence.*;

/**
 * 自定义字段
 * @author YuHuaPeng
 *
 */
@Entity
public class UserDefinedField {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	/**名称*/
	@Column
	private String name;
	/**数据类型*/
	@Column
	private String dataType;
	/**值ֵ*/
	@Column
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
