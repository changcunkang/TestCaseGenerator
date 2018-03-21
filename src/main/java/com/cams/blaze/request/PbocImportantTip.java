package com.cams.blaze.request;

import javax.persistence.*;
import java.util.Date;
@Entity
public class PbocImportantTip {
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
	private Date tipDate;
	@Column
	private String tipText;
	public Date getTipDate() {
		return tipDate;
	}
	public void setTipDate(Date tipDate) {
		this.tipDate = tipDate;
	}
	public String getTipText() {
		return tipText;
	}
	public void setTipText(String tipText) {
		this.tipText = tipText;
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
