package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class HighRiskTrans {


	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)

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

	@Column
	private Long parent_id;

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
}
