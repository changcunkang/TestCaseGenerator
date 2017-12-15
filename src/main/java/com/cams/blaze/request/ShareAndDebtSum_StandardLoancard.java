package com.cams.blaze.request;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 12/14/2017
 */
public class ShareAndDebtSum_StandardLoancard {

    private String shareAndDebtCD;
    private Integer financeCorpCount;
    private Integer financeOrgCount;
    private Integer accountCount;
    private Double creditLimit;
    private Double maxCreditLimitPerOrg;
    private Double minCreditLimitPerOrg;
    private Double balance;
    private Double usedCreditLimit;
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

}
