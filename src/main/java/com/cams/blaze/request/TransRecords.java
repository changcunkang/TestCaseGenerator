package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class TransRecords {

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
	private String transType;
	@Column
	private Integer transTimes;
	@Column
	private Double transAmt;
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public Integer getTransTimes() {
		return transTimes;
	}
	public void setTransTimes(Integer transTimes) {
		this.transTimes = transTimes;
	}
	public Double getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(Double transAmt) {
		this.transAmt = transAmt;
	}
	
	

}
