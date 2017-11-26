package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class HighRiskTrans {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column
	private String highRiskTransType;
	@Column
	private Double highRiskTransAmt;
	
	public String getHighRiskTransType() {
		return highRiskTransType;
	}
	public void setHighRiskTransType(String highRiskTransType) {
		this.highRiskTransType = highRiskTransType;
	}
	public Double getHighRiskTransAmt() {
		return highRiskTransAmt;
	}
	public void setHighRiskTransAmt(Double highRiskTransAmt) {
		this.highRiskTransAmt = highRiskTransAmt;
	}

}
