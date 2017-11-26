package com.cams.blaze.request;

import javax.persistence.*;
import java.util.Date;
@Entity
public class PbocCreditScore {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column
	private Date scoreDate;
	@Column
	private Double creditScore;
	@Column
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
