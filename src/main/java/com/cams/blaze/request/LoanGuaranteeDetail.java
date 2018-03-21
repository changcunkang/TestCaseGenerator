package com.cams.blaze.request;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LoanGuaranteeDetail {

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
	private String organization;
	@Column
	private Double contractAmount;
	@Column
	private Date issueDate;
	@Column
	private Date endDate;
	@Column
	private Double bondsAmount;
	@Column
	private Double basicAmount;
	@Column
	private String fiveStyle;
	@Column
	private Date needRepayDate;
	
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public Double getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getBondsAmount() {
		return bondsAmount;
	}
	public void setBondsAmount(Double bondsAmount) {
		this.bondsAmount = bondsAmount;
	}
	public Double getBasicAmount() {
		return basicAmount;
	}
	public void setBasicAmount(Double basicAmount) {
		this.basicAmount = basicAmount;
	}
	public String getFiveStyle() {
		return fiveStyle;
	}
	public void setFiveStyle(String fiveStyle) {
		this.fiveStyle = fiveStyle;
	}
	public Date getNeedRepayDate() {
		return needRepayDate;
	}
	public void setNeedRepayDate(Date needRepayDate) {
		this.needRepayDate = needRepayDate;
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
