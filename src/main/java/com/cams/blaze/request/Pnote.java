package com.cams.blaze.request;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Pnote {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private Date pDate;
	@Column
	private String pText;
	public Date getpDate() {
		return pDate;
	}
	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}
	public String getpText() {
		return pText;
	}
	public void setpText(String pText) {
		this.pText = pText;
	}

}
