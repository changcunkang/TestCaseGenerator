package com.cams.blaze.request;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 12/8/2017
 */

public class Temporary {

    private List<Loancard_local> loancard_local = new ArrayList<Loancard_local>();

    private List<Loancard_other> loancard_other = new ArrayList<Loancard_other>();

    private List<CurrentMonthAdjustLimitHistory_Fix> currentMonthAdjustLimitHistory_Fix = new ArrayList<CurrentMonthAdjustLimitHistory_Fix>();

    private List<CurrentMonthAdjustLimitHistory_Temp> currentMonthAdjustLimitHistory_Temp = new ArrayList<CurrentMonthAdjustLimitHistory_Temp>();

    private List<MonthlyAdjustLimitHistory_Fix> monthlyAdjustLimitHistory_Fix = new ArrayList<MonthlyAdjustLimitHistory_Fix>();

    private List<MonthlyAdjustLimitHistory_Temp> monthlyAdjustLimitHistory_Temp = new ArrayList<MonthlyAdjustLimitHistory_Temp>();

    private List<OverdueSum_Loan> overdueSum_Loan = new ArrayList<OverdueSum_Loan>();

    private List<OverdueSum_Loancard> overdueSum_Loancard = new ArrayList<OverdueSum_Loancard>();

    private List<ShareAndDebtSum_Loan> shareAndDebtSum_loan = new ArrayList<ShareAndDebtSum_Loan>();

    private List<ShareAndDebtSum_Loancard> shareAndDebtSum_loancard = new ArrayList<ShareAndDebtSum_Loancard>();

    private List<ShareAndDebtSum_StandardLoancard> shareAndDebtSum_standardLoancard = new ArrayList<ShareAndDebtSum_StandardLoancard>();

    public List<ShareAndDebtSum_Loan> getShareAndDebtSum_loan() {
        return shareAndDebtSum_loan;
    }

    public void setShareAndDebtSum_loan(List<ShareAndDebtSum_Loan> shareAndDebtSum_loan) {
        this.shareAndDebtSum_loan = shareAndDebtSum_loan;
    }

    public List<ShareAndDebtSum_Loancard> getShareAndDebtSum_loancard() {
        return shareAndDebtSum_loancard;
    }

    public void setShareAndDebtSum_loancard(List<ShareAndDebtSum_Loancard> shareAndDebtSum_loancard) {
        this.shareAndDebtSum_loancard = shareAndDebtSum_loancard;
    }

    public List<ShareAndDebtSum_StandardLoancard> getShareAndDebtSum_standardLoancard() {
        return shareAndDebtSum_standardLoancard;
    }

    public void setShareAndDebtSum_standardLoancard(List<ShareAndDebtSum_StandardLoancard> shareAndDebtSum_standardLoancard) {
        this.shareAndDebtSum_standardLoancard = shareAndDebtSum_standardLoancard;
    }

    public List<OverdueSum_Loan> getOverdueSum_Loan() {
        return overdueSum_Loan;
    }

    public void setOverdueSum_Loan(List<OverdueSum_Loan> overdueSum_Loan) {
        this.overdueSum_Loan = overdueSum_Loan;
    }

    public List<OverdueSum_Loancard> getOverdueSum_Loancard() {
        return overdueSum_Loancard;
    }

    public void setOverdueSum_Loancard(List<OverdueSum_Loancard> overdueSum_Loancard) {
        this.overdueSum_Loancard = overdueSum_Loancard;
    }

    public List<CurrentMonthAdjustLimitHistory_Fix> getCurrentMonthAdjustLimitHistory_Fix() {
        return currentMonthAdjustLimitHistory_Fix;
    }

    public void setCurrentMonthAdjustLimitHistory_Fix(List<CurrentMonthAdjustLimitHistory_Fix> currentMonthAdjustLimitHistory_Fix) {
        this.currentMonthAdjustLimitHistory_Fix = currentMonthAdjustLimitHistory_Fix;
    }

    public List<CurrentMonthAdjustLimitHistory_Temp> getCurrentMonthAdjustLimitHistory_Temp() {
        return currentMonthAdjustLimitHistory_Temp;
    }

    public void setCurrentMonthAdjustLimitHistory_Temp(List<CurrentMonthAdjustLimitHistory_Temp> currentMonthAdjustLimitHistory_Temp) {
        this.currentMonthAdjustLimitHistory_Temp = currentMonthAdjustLimitHistory_Temp;
    }

    public List<MonthlyAdjustLimitHistory_Fix> getMonthlyAdjustLimitHistory_Fix() {
        return monthlyAdjustLimitHistory_Fix;
    }

    public void setMonthlyAdjustLimitHistory_Fix(List<MonthlyAdjustLimitHistory_Fix> monthlyAdjustLimitHistory_Fix) {
        this.monthlyAdjustLimitHistory_Fix = monthlyAdjustLimitHistory_Fix;
    }

    public List<MonthlyAdjustLimitHistory_Temp> getMonthlyAdjustLimitHistory_Temp() {
        return monthlyAdjustLimitHistory_Temp;
    }

    public void setMonthlyAdjustLimitHistory_Temp(List<MonthlyAdjustLimitHistory_Temp> monthlyAdjustLimitHistory_Temp) {
        this.monthlyAdjustLimitHistory_Temp = monthlyAdjustLimitHistory_Temp;
    }

    public List<Loancard_local> getLoancard_local() {
        return loancard_local;
    }

    public void setLoancard_local(List<Loancard_local> loancard_local) {
        this.loancard_local = loancard_local;
    }

    public List<Loancard_other> getLoancard_other() {
        return loancard_other;
    }

    public void setLoancard_other(List<Loancard_other> loancard_other) {
        this.loancard_other = loancard_other;
    }
}
