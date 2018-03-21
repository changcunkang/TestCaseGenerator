package com.cams.blaze.request;

import javax.persistence.*;
import java.util.*;
@Entity
public class PbocReport {
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

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<CreditSummaryCue> creditSummaryCue = new ArrayList<CreditSummaryCue>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<FellbackSum> fellbackSum = new ArrayList<FellbackSum>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<OverdueSum> overdueSum = new ArrayList<OverdueSum>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<ShareAndDebtSum> shareAndDebtSum = new ArrayList<ShareAndDebtSum>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<GuaranteeSum> guaranteeSum = new ArrayList<GuaranteeSum>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<Loancard> loancard = new ArrayList<Loancard>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<StandardLoancard> standardLoancard = new ArrayList<StandardLoancard>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<Loan> loan = new ArrayList<Loan>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<AccFund> accFund = new ArrayList<AccFund>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<AssetDisposition> assetDisposition = new ArrayList<AssetDisposition>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<AssurerRepay> assurerRepay = new ArrayList<AssurerRepay>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<ForceExecution> forceExecution = new ArrayList<ForceExecution>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<AdminPunishment> adminPunishment = new ArrayList<AdminPunishment>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<RecordDetail> recordDetail = new ArrayList<RecordDetail>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<LoanGuaranteeDetail> loanGuaranteeDetail = new ArrayList<LoanGuaranteeDetail>();

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	private List<CreditGuaranteeDetail> creditGuaranteeDetail = new ArrayList<CreditGuaranteeDetail>();

	@Column
	private String reportSN;
	@Column
	private Integer pbocQueriedFlag;
	@Column
	private Date queryTime;
	@Column
	private Date reportCreateTime;
	@Column
	private String gender;
	@Column
	private Date birthday;
	@Column
	private String maritalState;
	@Column
	private String mobile;
	@Column
	private String eduLevel;
	@Column
	private Double creditScore;
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public List<FellbackSum> getFellbackSum() {
		return fellbackSum;
	}
	public void setFellbackSum(List<FellbackSum> fellbackSum) {
		this.fellbackSum = fellbackSum;
	}
	public List<OverdueSum> getOverdueSum() {
		return overdueSum;
	}
	public void setOverdueSum(List<OverdueSum> overdueSum) {
		this.overdueSum = overdueSum;
	}
	public List<ShareAndDebtSum> getShareAndDebtSum() {
		return shareAndDebtSum;
	}
	public void setShareAndDebtSum(List<ShareAndDebtSum> shareAndDebtSum) {
		this.shareAndDebtSum = shareAndDebtSum;
	}
	public List<GuaranteeSum> getGuaranteeSum() {
		return guaranteeSum;
	}
	public void setGuaranteeSum(List<GuaranteeSum> guaranteeSum) {
		this.guaranteeSum = guaranteeSum;
	}
	public List<Loancard> getLoancard() {
		return loancard;
	}
	public void setLoancard(List<Loancard> loancard) {
		this.loancard = loancard;
	}
	public List<StandardLoancard> getStandardLoancard() {
		return standardLoancard;
	}
	public void setStandardLoancard(List<StandardLoancard> standardLoancard) {
		this.standardLoancard = standardLoancard;
	}
	public List<Loan> getLoan() {
		return loan;
	}
	public void setLoan(List<Loan> loan) {
		this.loan = loan;
	}
	public List<AccFund> getAccFund() {
		return accFund;
	}
	public void setAccFund(List<AccFund> accFund) {
		this.accFund = accFund;
	}
	public List<AssetDisposition> getAssetDisposition() {
		return assetDisposition;
	}
	public void setAssetDisposition(List<AssetDisposition> assetDisposition) {
		this.assetDisposition = assetDisposition;
	}
	public List<AssurerRepay> getAssurerRepay() {
		return assurerRepay;
	}
	public void setAssurerRepay(List<AssurerRepay> assurerRepay) {
		this.assurerRepay = assurerRepay;
	}
	public List<ForceExecution> getForceExecution() {
		return forceExecution;
	}
	public void setForceExecution(List<ForceExecution> forceExecution) {
		this.forceExecution = forceExecution;
	}
	public List<AdminPunishment> getAdminPunishment() {
		return adminPunishment;
	}
	public void setAdminPunishment(List<AdminPunishment> adminPunishment) {
		this.adminPunishment = adminPunishment;
	}
	public List<RecordDetail> getRecordDetail() {
		return recordDetail;
	}
	public void setRecordDetail(List<RecordDetail> recordDetail) {
		this.recordDetail = recordDetail;
	}
	public String getReportSN() {
		return reportSN;
	}
	public void setReportSN(String reportSN) {
		this.reportSN = reportSN;
	}
	public Integer getPbocQueriedFlag() {
		return pbocQueriedFlag;
	}
	public void setPbocQueriedFlag(Integer pbocQueriedFlag) {
		this.pbocQueriedFlag = pbocQueriedFlag;
	}
	public Date getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(Date queryTime) {
		this.queryTime = queryTime;
	}
	public Date getReportCreateTime() {
		return reportCreateTime;
	}
	public void setReportCreateTime(Date reportCreateTime) {
		this.reportCreateTime = reportCreateTime;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getMaritalState() {
		return maritalState;
	}
	public void setMaritalState(String maritalState) {
		this.maritalState = maritalState;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEduLevel() {
		return eduLevel;
	}
	public void setEduLevel(String eduLevel) {
		this.eduLevel = eduLevel;
	}
	public Double getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(Double creditScore) {
		this.creditScore = creditScore;
	}
	public List<CreditSummaryCue> getCreditSummaryCue() {
		return creditSummaryCue;
	}
	public void setCreditSummaryCue(List<CreditSummaryCue> creditSummaryCue) {
		this.creditSummaryCue = creditSummaryCue;
	}
	public List<LoanGuaranteeDetail> getLoanGuaranteeDetail() {
		return loanGuaranteeDetail;
	}

	public void setLoanGuaranteeDetail(List<LoanGuaranteeDetail> loanGuaranteeDetail) {
		this.loanGuaranteeDetail = loanGuaranteeDetail;
	}

	public List<CreditGuaranteeDetail> getCreditGuaranteeDetail() {
		return creditGuaranteeDetail;
	}

	public void setCreditGuaranteeDetail(List<CreditGuaranteeDetail> creditGuaranteeDetail) {
		this.creditGuaranteeDetail = creditGuaranteeDetail;
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
