package com.cams.blaze.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 12/8/2017
 */

public class Loancard_other {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private List<SpecialTrade> specialTrade = new ArrayList<SpecialTrade>();
    private List<OverdueRecord> overdueRecord = new ArrayList<OverdueRecord>();
    private String state;
    private String cue;
    private String financeOrg;
    private String account;
    private String currency;
    private Date openDate;
    
    private Double creditLimitAmount;
    
    private String guaranteeType;
    
    private Date stateEndDate;
    

    private Double shareCreditLimitAmount;
    
    private Double usedCreditLimitAmount;
    
    private Double latest6MonthUsedAvgAmount;
    
    private Double usedHighestAmount;
    
    private Integer scheduledPaymentDate;
    
    private Double scheduledPaymentAmount;
    
    private Double actualPaymentAmount;
    
    private Date recentPayDate;
    
    private Integer currOverdueCyc;
    
    private Double currOverdueAmount;
    
    private String beginMonth;
    
    private String endMonth;
    
    private String latest24State;
    
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
    public Date getStateEndDate() {
        return stateEndDate;
    }
    public void setStateEndDate(Date stateEndDate) {
        this.stateEndDate = stateEndDate;
    }
    public Double getShareCreditLimitAmount() {
        return shareCreditLimitAmount;
    }
    public void setShareCreditLimitAmount(Double shareCreditLimitAmount) {
        this.shareCreditLimitAmount = shareCreditLimitAmount;
    }
    public Double getUsedCreditLimitAmount() {
        return usedCreditLimitAmount;
    }
    public void setUsedCreditLimitAmount(Double usedCreditLimitAmount) {
        this.usedCreditLimitAmount = usedCreditLimitAmount;
    }
    public Double getLatest6MonthUsedAvgAmount() {
        return latest6MonthUsedAvgAmount;
    }
    public void setLatest6MonthUsedAvgAmount(Double latest6MonthUsedAvgAmount) {
        this.latest6MonthUsedAvgAmount = latest6MonthUsedAvgAmount;
    }
    public Double getUsedHighestAmount() {
        return usedHighestAmount;
    }
    public void setUsedHighestAmount(Double usedHighestAmount) {
        this.usedHighestAmount = usedHighestAmount;
    }
    public Integer getScheduledPaymentDate() {
        return scheduledPaymentDate;
    }
    public void setScheduledPaymentDate(Integer scheduledPaymentDate) {
        this.scheduledPaymentDate = scheduledPaymentDate;
    }
    public Double getScheduledPaymentAmount() {
        return scheduledPaymentAmount;
    }
    public void setScheduledPaymentAmount(Double scheduledPaymentAmount) {
        this.scheduledPaymentAmount = scheduledPaymentAmount;
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
    public List<OverdueRecord> getOverdueRecord() {
        return overdueRecord;
    }
    public void setOverdueRecord(List<OverdueRecord> overdueRecord) {
        this.overdueRecord = overdueRecord;
    }
    public List<SpecialTrade> getSpecialTrade() {
        return specialTrade;
    }
    public void setSpecialTrade(List<SpecialTrade> specialTrade) {
        this.specialTrade = specialTrade;
    }


}
