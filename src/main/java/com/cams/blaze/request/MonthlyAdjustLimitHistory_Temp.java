package com.cams.blaze.request;

import java.util.Date;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 12/13/2017
 */
public class MonthlyAdjustLimitHistory_Temp {

    private Date adjustLimitDate;

    private Double adjustLimitAmtBefore;

    private Double adjustLimitAmtAfter;

    private String adjustLimitType;

    private Date tempALunValidDate;
    private String adjustLimitDirection;

    public Date getAdjustLimitDate() {
        return adjustLimitDate;
    }

    public void setAdjustLimitDate(Date adjustLimitDate) {
        this.adjustLimitDate = adjustLimitDate;
    }

    public Double getAdjustLimitAmtBefore() {
        return adjustLimitAmtBefore;
    }

    public void setAdjustLimitAmtBefore(Double adjustLimitAmtBefore) {
        this.adjustLimitAmtBefore = adjustLimitAmtBefore;
    }

    public Double getAdjustLimitAmtAfter() {
        return adjustLimitAmtAfter;
    }

    public void setAdjustLimitAmtAfter(Double adjustLimitAmtAfter) {
        this.adjustLimitAmtAfter = adjustLimitAmtAfter;
    }

    public String getAdjustLimitType() {
        return adjustLimitType;
    }

    public void setAdjustLimitType(String adjustLimitType) {
        this.adjustLimitType = adjustLimitType;
    }

    public Date getTempALunValidDate() {
        return tempALunValidDate;
    }

    public void setTempALunValidDate(Date tempALunValidDate) {
        this.tempALunValidDate = tempALunValidDate;
    }

    public String getAdjustLimitDirection() {
        return adjustLimitDirection;
    }

    public void setAdjustLimitDirection(String adjustLimitDirection) {
        this.adjustLimitDirection = adjustLimitDirection;
    }
}
