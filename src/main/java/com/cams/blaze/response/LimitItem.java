package com.cams.blaze.response;

import javax.persistence.*;

@Entity
public class LimitItem {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column
	private String limitItemID;
	@Column
	private Double limitItemValue;
	
	public String getLimitItemID() {
		return limitItemID;
	}
	public void setLimitItemID(String limitItemID) {
		this.limitItemID = limitItemID;
	}
	public Double getLimitItemValue() {
		return limitItemValue;
	}
	public void setLimitItemValue(Double limitItemValue) {
		this.limitItemValue = limitItemValue;
	}
	@Column
	private Long parent_id;

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
}
