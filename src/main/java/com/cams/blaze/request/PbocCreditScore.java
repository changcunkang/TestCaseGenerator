package com.cams.blaze.request;

import java.util.Date;

public class PbocCreditScore {
	private Date scoreDate;
	private Double creditScore;
	private String resultType;
	public Date getScoreDate() {
		return scoreDate;
	}
	public void setScoreDate(Date scoreDate) {
		this.scoreDate = scoreDate;
	}
	public Double getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(Double creditScore) {
		this.creditScore = creditScore;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

}
