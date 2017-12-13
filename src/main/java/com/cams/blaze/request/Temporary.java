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
