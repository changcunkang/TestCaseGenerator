package com.cams.blaze.response;

import javax.persistence.*;

@Entity
public class LimitItem {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column
	private Long id;



	@Column(name="limitModelReturnInfo_id")
	private	 Long limitModelReturnInfo_id;
	public void setLimitModelReturnInfo_id(Long limitModelReturnInfo_id) {
		this.limitModelReturnInfo_id = limitModelReturnInfo_id;
	}

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
