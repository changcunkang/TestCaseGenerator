package com.cams.blaze.request;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 12/28/2017
 */
public class InstalmentDetail {

    //id
    private String instalmentID;

    public String getInstalmentID() {
        return instalmentID;
    }

    public void setInstalmentID(String instalmentID) {
        this.instalmentID = instalmentID;
    }


    //类型
    private String instalmentType;

    //id
    private String id;

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

    //未摊销本金
    private Double unAmortizationPri;

    //已摊销手续费
    private Double totAmortizationFee;

    //未摊销总期数
    private Integer unAmortizationTerms;

    //已摊销总期数
    private Integer amortizationTerms;

    //已摊销总期数
    private Integer totAppTerms;

    public Double getUnAmortizationPri() {
        return unAmortizationPri;
    }

    public void setUnAmortizationPri(Double unAmortizationPri) {
        this.unAmortizationPri = unAmortizationPri;
    }

    public Double getTotAmortizationFee() {
        return totAmortizationFee;
    }

    public void setTotAmortizationFee(Double totAmortizationFee) {
        this.totAmortizationFee = totAmortizationFee;
    }

    public Integer getUnAmortizationTerms() {
        return unAmortizationTerms;
    }

    public void setUnAmortizationTerms(Integer unAmortizationTerms) {
        this.unAmortizationTerms = unAmortizationTerms;
    }

    public Integer getAmortizationTerms() {
        return amortizationTerms;
    }

    public void setAmortizationTerms(Integer amortizationTerms) {
        this.amortizationTerms = amortizationTerms;
    }

    public Integer getTotAppTerms() {
        return totAppTerms;
    }

    public void setTotAppTerms(Integer totAppTerms) {
        this.totAppTerms = totAppTerms;
    }

    public String getInstalmentType() {
        return instalmentType;
    }

    public void setInstalmentType(String instalmentType) {
        this.instalmentType = instalmentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
