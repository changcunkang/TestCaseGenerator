package com.cams.blaze.request;

import java.util.*;

public class PbocReport {
	private List<CreditSummaryCue> creditSummaryCue = new ArrayList<CreditSummaryCue>();
	private List<FellbackSum> fellbackSum = new ArrayList<FellbackSum>();
	private List<OverdueSum> overdueSum = new ArrayList<OverdueSum>();
	private List<ShareAndDebtSum> shareAndDebtSum = new ArrayList<ShareAndDebtSum>();
	private List<GuaranteeSum> guaranteeSum = new ArrayList<GuaranteeSum>();
	private List<Loancard> loancard = new ArrayList<Loancard>();
	private List<StandardLoancard> standardLoancard = new ArrayList<StandardLoancard>();
	private List<Loan> loan = new ArrayList<Loan>();
	private List<AccFund> accFund = new ArrayList<AccFund>();
	private List<AssetDisposition> assetDisposition = new ArrayList<AssetDisposition>();
	private List<AssurerRepay> assurerRepay = new ArrayList<AssurerRepay>();
	private List<ForceExecution> forceExecution = new ArrayList<ForceExecution>();
	private List<AdminPunishment> adminPunishment = new ArrayList<AdminPunishment>();
	private List<RecordDetail> recordDetail = new ArrayList<RecordDetail>();
	private String reportSN;
	private Integer pbocQueriedFlag;
	private Date queryTime;
	private Date reportCreateTime;
	private String gender;
	private Date birthday;
	private String maritalState;
	private String mobile;
	private String eduLevel;
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


}
