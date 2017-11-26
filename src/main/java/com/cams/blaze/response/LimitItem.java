package com.cams.blaze.response;

import javax.persistence.*;

@Entity
public class LimitItem {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
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
}
