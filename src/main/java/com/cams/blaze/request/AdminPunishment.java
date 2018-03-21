package com.cams.blaze.request;

import javax.persistence.*;
import java.util.*;

@Entity
public class AdminPunishment {

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
	private String content;
	@Column
	private String money;
	@Column
	private Date beginDate;
	@Column
	private Date endDate;
	@Column
	private String result;
	public String getOrganname() {
		return organname;
	}
	public void setOrganname(String organname) {
		this.organname = organname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}


}
