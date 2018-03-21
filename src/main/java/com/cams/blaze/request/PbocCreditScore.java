package com.cams.blaze.request;

import javax.persistence.*;
import java.util.Date;
@Entity
public class PbocCreditScore {


	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column(name="customer_id")
	private	 Long customer_id;
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
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


	@Column
	private Long parent_id;

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
}
