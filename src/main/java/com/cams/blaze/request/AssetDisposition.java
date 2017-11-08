package com.cams.blaze.request;

import java.util.*;

public class AssetDisposition {
	private String organname;
	private Date getTime;
	private Double money;
	private Date latestRepayDate;
	private Double balance;
	public String getOrganname() {
		return organname;
	}
	public void setOrganname(String organname) {
		this.organname = organname;
	}
	public Date getGetTime() {
		return getTime;
	}
	public void setGetTime(Date getTime) {
		this.getTime = getTime;
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
