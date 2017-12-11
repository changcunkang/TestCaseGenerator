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
