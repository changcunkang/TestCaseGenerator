package com.cams.blaze.request;

import javax.persistence.*;
import java.util.Date;
@Entity
public class TXScore {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
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
