package com.cams.blaze.request;

import javax.persistence.*;
import java.util.*;

@Entity
public class PCustRef {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
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
	private String custRef;
	public Date getpDate() {
		return pDate;
	}
	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}
	public String getCustRef() {
		return custRef;
	}
	public void setCustRef(String custRef) {
		this.custRef = custRef;
	}


}
