package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class ShareAndDebtSum {
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
	private String shareAndDebtCD;
	@Column
	private Integer financeCorpCount;
	@Column
	private Integer financeOrgCount;
	@Column
	private Integer accountCount;
	@Column
	private Double creditLimit;
	@Column
	private Double maxCreditLimitPerOrg;
	@Column
	private Double minCreditLimitPerOrg;
	@Column
	private Double balance;
	@Column
	private Double usedCreditLimit;
	@Column
	private Double latest6MonthUsedAvgAmount;
	public String getShareAndDebtCD() {
		return shareAndDebtCD;
	}
	public void setShareAndDebtCD(String shareAndDebtCD) {
		this.shareAndDebtCD = shareAndDebtCD;
	}
	public Integer getFinanceCorpCount() {
		return financeCorpCount;
	}
	public void setFinanceCorpCount(Integer financeCorpCount) {
		this.financeCorpCount = financeCorpCount;
	}
	public Integer getFinanceOrgCount() {
		return financeOrgCount;
	}
	public void setFinanceOrgCount(Integer financeOrgCount) {
		this.financeOrgCount = financeOrgCount;
	}
	public Integer getAccountCount() {
		return accountCount;
	}
	public void setAccountCount(Integer accountCount) {
		this.accountCount = accountCount;
	}
	public Double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}
	public Double getMaxCreditLimitPerOrg() {
		return maxCreditLimitPerOrg;
	}
	public void setMaxCreditLimitPerOrg(Double maxCreditLimitPerOrg) {
		this.maxCreditLimitPerOrg = maxCreditLimitPerOrg;
	}
	public Double getMinCreditLimitPerOrg() {
		return minCreditLimitPerOrg;
	}
	public void setMinCreditLimitPerOrg(Double minCreditLimitPerOrg) {
		this.minCreditLimitPerOrg = minCreditLimitPerOrg;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getUsedCreditLimit() {
		return usedCreditLimit;
	}
	public void setUsedCreditLimit(Double usedCreditLimit) {
		this.usedCreditLimit = usedCreditLimit;
	}
	public Double getLatest6MonthUsedAvgAmount() {
		return latest6MonthUsedAvgAmount;
	}
	public void setLatest6MonthUsedAvgAmount(Double latest6MonthUsedAvgAmount) {
		this.latest6MonthUsedAvgAmount = latest6MonthUsedAvgAmount;
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
