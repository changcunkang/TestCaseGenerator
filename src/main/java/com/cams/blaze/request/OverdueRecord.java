package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class OverdueRecord {
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
	private String month;
	@Column
	private Integer lastMonths;
	@Column
	private Double amount;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getLastMonths() {
		return lastMonths;
	}
	public void setLastMonths(Integer lastMonths) {
		this.lastMonths = lastMonths;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
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
