package com.cams.blaze.request;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CreditGuaranteeDetail {
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
	private Double bondsAmount;
	@Column
	private Double usedAmount;
	@Column
	private Date billDate;

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
	public Double getBondsAmount() {
		return bondsAmount;
	}
	public void setBondsAmount(Double bondsAmount) {
		this.bondsAmount = bondsAmount;
	}
	public Double getUsedAmount() {
		return usedAmount;
	}
	public void setUsedAmount(Double usedAmount) {
		this.usedAmount = usedAmount;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
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
