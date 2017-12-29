package com.cams.blaze.response;

import javax.persistence.*;

@Entity
public class Limit {
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
	/**行动值域*/
	@Column
	private String limitItemID;
	@Column
	private Double limitItemValue;
	public Double getLimitItemValue() {
		return limitItemValue;
	}
	public void setLimitItemValue(Double limitItemValue) {
		this.limitItemValue = limitItemValue;
	}
	public String getLimitItemID() {
		return limitItemID;
	}
	public void setLimitItemID(String limitItemID) {
		this.limitItemID = limitItemID;
	}
}
