package com.cams.blaze.request;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 12/28/2017
 */
public class InstalmentDetail_Old {

    //类型
    private String instalmentType;

    //id
    private String instalmentID;

    public String getInstalmentID() {
        return instalmentID;
    }

    public void setInstalmentID(String instalmentID) {
        this.instalmentID = instalmentID;
    }

    //手续费
    private Double poundage;

    //期数
    private Integer period;

    //总摊销额度
    private Double totalInstalmentAmount;

    //当前期数
    private Integer currentInstalmentPeriod;

    //当期摊销金额
    private Double currentInstalmentAmount;

    //当前摊销总金额
    private Double currentTotalInstalmentAmount;

    public String getInstalmentType() {
        return instalmentType;
    }

    public void setInstalmentType(String instalmentType) {
        this.instalmentType = instalmentType;
    }

    public Double getPoundage() {
        return poundage;
    }

    public void setPoundage(Double poundage) {
        this.poundage = poundage;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getTotalInstalmentAmount() {
        return totalInstalmentAmount;
    }

    public void setTotalInstalmentAmount(Double totalInstalmentAmount) {
        this.totalInstalmentAmount = totalInstalmentAmount;
    }

    public Integer getCurrentInstalmentPeriod() {
        return currentInstalmentPeriod;
    }

    public void setCurrentInstalmentPeriod(Integer currentInstalmentPeriod) {
        this.currentInstalmentPeriod = currentInstalmentPeriod;
    }

    public Double getCurrentInstalmentAmount() {
        return currentInstalmentAmount;
    }

    public void setCurrentInstalmentAmount(Double currentInstalmentAmount) {
        this.currentInstalmentAmount = currentInstalmentAmount;
    }

    public Double getCurrentTotalInstalmentAmount() {
        return currentTotalInstalmentAmount;
    }

    public void setCurrentTotalInstalmentAmount(Double currentTotalInstalmentAmount) {
        this.currentTotalInstalmentAmount = currentTotalInstalmentAmount;
    }
}
