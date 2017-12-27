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
