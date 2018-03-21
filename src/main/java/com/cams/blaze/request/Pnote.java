package com.cams.blaze.request;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Pnote {

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
