package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class ConsumeTypeSet {

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
	private String consumeType;
	@Column
	private Double consumeAmt;
	@Column
	private Integer consumeTimes;


	@Column
	private Long parent_id;

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}


	public Integer getConsumeTimes() {
		return consumeTimes;
	}

	public void setConsumeTimes(Integer consumeTimes) {
		this.consumeTimes = consumeTimes;
	}

	public String getConsumeType() {
		return consumeType;
	}
	public void setConsumeType(String consumeType) {
		this.consumeType = consumeType;
	}
	public Double getConsumeAmt() {
		return consumeAmt;
	}
	public void setConsumeAmt(Double consumeAmt) {
		this.consumeAmt = consumeAmt;
	}

}
