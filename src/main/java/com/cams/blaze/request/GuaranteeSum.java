package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class GuaranteeSum {
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
	private Integer count;
	@Column
	private Double amount;
	@Column
	private Double balance;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
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
