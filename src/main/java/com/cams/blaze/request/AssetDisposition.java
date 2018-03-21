package com.cams.blaze.request;

import javax.persistence.*;
import java.util.*;

@Entity
public class AssetDisposition {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;

	@Column(name="pbocReport_id")
	private	 Long pbocReport_id;

	public void setPbocReport_id(Long pbocReport_id) {
		this.pbocReport_id = pbocReport_id;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column
	private String organname;
	@Column
	private Date getTime;
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
