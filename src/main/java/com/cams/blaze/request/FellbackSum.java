package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class FellbackSum {

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
	private String fellbackCD;
	@Column
	private Integer count;
	@Column
	private Double balance;
	public String getFellbackCD() {
		return fellbackCD;
	}
	public void setFellbackCD(String fellbackCD) {
		this.fellbackCD = fellbackCD;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}


}
