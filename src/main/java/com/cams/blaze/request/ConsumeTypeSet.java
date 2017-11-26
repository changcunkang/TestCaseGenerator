package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class ConsumeTypeSet {

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
	private String consumeType;
	@Column
	private Double consumeAmt;

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
