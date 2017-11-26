package com.cams.blaze.request;

import java.util.*;

import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
public class AccFund {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column
	private String area;
	@Column
	private Date registerDate;
	@Column
	private String firstMonth;
	@Column
	private String toMonth;
	@Column
	private String state;
	@Column
	private Double pay;
	@Column
	private Double ownPercent;
	@Column
	private Double comPercent;
	@Column
	private String organname;
	@Column
	private Date getTime;
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public String getFirstMonth() {
		return firstMonth;
	}
	public void setFirstMonth(String firstMonth) {
		this.firstMonth = firstMonth;
	}
	public String getToMonth() {
		return toMonth;
	}
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Double getPay() {
		return pay;
	}
	public void setPay(Double pay) {
		this.pay = pay;
	}
	public Double getOwnPercent() {
		return ownPercent;
	}
	public void setOwnPercent(Double ownPercent) {
		this.ownPercent = ownPercent;
	}
	public Double getComPercent() {
		return comPercent;
	}
	public void setComPercent(Double comPercent) {
		this.comPercent = comPercent;
	}
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


}
