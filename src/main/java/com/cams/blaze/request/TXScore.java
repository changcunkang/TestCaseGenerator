package com.cams.blaze.request;

import javax.persistence.*;
import java.util.Date;
@Entity
public class TXScore {


	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column(name="customer_id")
	private	 Long customer_id;
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

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
	private Double score;
	public Date getScoreDate() {
		return scoreDate;
	}
	public void setScoreDate(Date scoreDate) {
		this.scoreDate = scoreDate;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}

}
