package com.cams.blaze.request;

import java.util.*;

public class AssurerRepay {
	private String organname;
	private Date latestAssurerRepayDate;
	private Double money;
	private Date latestRepayDate;
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



}
