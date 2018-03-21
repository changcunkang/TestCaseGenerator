package com.cams.blaze.request;

import javax.persistence.*;
import java.util.*;
@Entity
public class AssurerRepay {

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
	private String organname;
	@Column
	private Date latestAssurerRepayDate;
	@Column
	private Double money;
	@Column
	private Date latestRepayDate;
	@Column
	private Double balance;
	public String getOrganname() {
		return organname;
	}
	public void setOrganname(String organname) {
		this.organname = organname;
	}
	public Date getLatestAssurerRepayDate() {
		return latestAssurerRepayDate;
	}
	public void setLatestAssurerRepayDate(Date latestAssurerRepayDate) {
		this.latestAssurerRepayDate = latestAssurerRepayDate;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getLatestRepayDate() {
		return latestRepayDate;
	}
	public void setLatestRepayDate(Date latestRepayDate) {
		this.latestRepayDate = latestRepayDate;
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
