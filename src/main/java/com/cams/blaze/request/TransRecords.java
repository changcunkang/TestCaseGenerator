package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class TransRecords {


	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column(name="monthlyRecordInfo_id")
	private	 Long monthlyRecordInfo_id;
	public void setMonthlyRecordInfo_id(Long monthlyRecordInfo_id) {
		this.monthlyRecordInfo_id = monthlyRecordInfo_id;
	}

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
