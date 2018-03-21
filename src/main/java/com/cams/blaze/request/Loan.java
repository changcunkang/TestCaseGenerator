package com.cams.blaze.request;

import javax.persistence.*;
import java.util.*;

@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column
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
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<SpecialTrade> specialTrade = new ArrayList<SpecialTrade>();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<OverdueRecord> overdueRecord = new ArrayList<OverdueRecord>();
	@Column
	private String state;
	@Column
	private String cue;
	@Column
	private String financeOrg;
	@Column
	private String account;
	@Column
	private String type;
	@Column
	private String currency;
	@Column
	private Date openDate;
	@Column
	private Date endDate;
	@Column
	private Double creditLimitAmount;
	@Column
	private String guaranteeType;
	@Column
	private String paymentRating;
	@Column
	private String paymentCyc;
	@Column
	private Date stateEndDate;
	@Column
	private String class5State;
	@Column
	private Double balance;
	@Column
	private Integer remainPaymentCyc;
	@Column
	private Double scheduledPaymentAmount;
	@Column
	private Date scheduledPaymentDate;
	@Column
	private Double actualPaymentAmount;
	@Column
	private Date recentPayDate;
	@Column
	private Integer currOverdueCyc;
	@Column
	private Double currOverdueAmount;
	@Column
	private Double overdue31To60Amount;
	@Column
	private Double overdue61To90Amount;
	@Column
	private Double overdue91To180Amount;
	@Column
	private Double overdueOver180Amount;
	@Column
	private String beginMonth;
	@Column
	private String endMonth;
	@Column
	private String latest24State;
	@Column
	private String latest24StateNum;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCue() {
		return cue;
	}
	public void setCue(String cue) {
		this.cue = cue;
	}
	public String getFinanceOrg() {
		return financeOrg;
	}
	public void setFinanceOrg(String financeOrg) {
		this.financeOrg = financeOrg;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getCreditLimitAmount() {
		return creditLimitAmount;
	}
	public void setCreditLimitAmount(Double creditLimitAmount) {
		this.creditLimitAmount = creditLimitAmount;
	}
	public String getGuaranteeType() {
		return guaranteeType;
	}
	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}
	public String getPaymentRating() {
		return paymentRating;
	}
	public void setPaymentRating(String paymentRating) {
		this.paymentRating = paymentRating;
	}
	public String getPaymentCyc() {
		return paymentCyc;
	}
	public void setPaymentCyc(String paymentCyc) {
		this.paymentCyc = paymentCyc;
	}
	public Date getStateEndDate() {
		return stateEndDate;
	}
	public void setStateEndDate(Date stateEndDate) {
		this.stateEndDate = stateEndDate;
	}
	public String getClass5State() {
		return class5State;
	}
	public void setClass5State(String class5State) {
		this.class5State = class5State;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getRemainPaymentCyc() {
		return remainPaymentCyc;
	}
	public void setRemainPaymentCyc(Integer remainPaymentCyc) {
		this.remainPaymentCyc = remainPaymentCyc;
	}
	public Double getScheduledPaymentAmount() {
		return scheduledPaymentAmount;
	}
	public void setScheduledPaymentAmount(Double scheduledPaymentAmount) {
		this.scheduledPaymentAmount = scheduledPaymentAmount;
	}
	public Date getScheduledPaymentDate() {
		return scheduledPaymentDate;
	}
	public void setScheduledPaymentDate(Date scheduledPaymentDate) {
		this.scheduledPaymentDate = scheduledPaymentDate;
	}
	public Double getActualPaymentAmount() {
		return actualPaymentAmount;
	}
	public void setActualPaymentAmount(Double actualPaymentAmount) {
		this.actualPaymentAmount = actualPaymentAmount;
	}
	public Date getRecentPayDate() {
		return recentPayDate;
	}
	public void setRecentPayDate(Date recentPayDate) {
		this.recentPayDate = recentPayDate;
	}
	public Integer getCurrOverdueCyc() {
		return currOverdueCyc;
	}
	public void setCurrOverdueCyc(Integer currOverdueCyc) {
		this.currOverdueCyc = currOverdueCyc;
	}
	public Double getCurrOverdueAmount() {
		return currOverdueAmount;
	}
	public void setCurrOverdueAmount(Double currOverdueAmount) {
		this.currOverdueAmount = currOverdueAmount;
	}
	public Double getOverdue31To60Amount() {
		return overdue31To60Amount;
	}
	public void setOverdue31To60Amount(Double overdue31To60Amount) {
		this.overdue31To60Amount = overdue31To60Amount;
	}
	public Double getOverdue61To90Amount() {
		return overdue61To90Amount;
	}
	public void setOverdue61To90Amount(Double overdue61To90Amount) {
		this.overdue61To90Amount = overdue61To90Amount;
	}
	public Double getOverdue91To180Amount() {
		return overdue91To180Amount;
	}
	public void setOverdue91To180Amount(Double overdue91To180Amount) {
		this.overdue91To180Amount = overdue91To180Amount;
	}
	public Double getOverdueOver180Amount() {
		return overdueOver180Amount;
	}
	public void setOverdueOver180Amount(Double overdueOver180Amount) {
		this.overdueOver180Amount = overdueOver180Amount;
	}
	public String getBeginMonth() {
		return beginMonth;
	}
	public void setBeginMonth(String beginMonth) {
		this.beginMonth = beginMonth;
	}
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	public String getLatest24State() {
		return latest24State;
	}
	public void setLatest24State(String latest24State) {
		this.latest24State = latest24State;
	}
	public String getLatest24StateNum() {
		return latest24StateNum;
	}
	public void setLatest24StateNum(String latest24StateNum) {
		this.latest24StateNum = latest24StateNum;
	}
	public List<SpecialTrade> getSpecialTrade() {
		return specialTrade;
	}
	public void setSpecialTrade(List<SpecialTrade> specialTrade) {
		this.specialTrade = specialTrade;
	}
	public List<OverdueRecord> getOverdueRecord() {
		return overdueRecord;
	}
	public void setOverdueRecord(List<OverdueRecord> overdueRecord) {
		this.overdueRecord = overdueRecord;
	}


}
