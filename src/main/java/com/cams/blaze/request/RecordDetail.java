package com.cams.blaze.request;

import javax.persistence.*;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class RecordDetail {
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	@Column
	private Long id;

	@Column(name="pbocReport_id")
	private	 Long pbocReport_id;

	public void setPbocReport_id(Long pbocReport_id) {
		this.pbocReport_id = pbocReport_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column
	private Date queryDate;
	@Column
	private String querier;
	@Column
	private String queryReason;
	
	@Column
	private String reserveString01;
	@Column
	private String reserveString02;
	@Column
	private String reserveString03;
	@Column
	private String reserveString04;
	@Column
	private String reserveString05;
	
	public Date getQueryDate() {
		return queryDate;
	}
	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}
	public String getQuerier() {
		return querier;
	}
	public void setQuerier(String querier) {
		this.querier = querier;
	}
	public String getQueryReason() {
		return queryReason;
	}
	public void setQueryReason(String queryReason) {
		this.queryReason = queryReason;
	}
	public String getReserveString01() {
		return reserveString01;
	}
	public void setReserveString01(String reserveString01) {
		this.reserveString01 = reserveString01;
	}
	public String getReserveString02() {
		return reserveString02;
	}
	public void setReserveString02(String reserveString02) {
		this.reserveString02 = reserveString02;
	}
	public String getReserveString03() {
		return reserveString03;
	}
	public void setReserveString03(String reserveString03) {
		this.reserveString03 = reserveString03;
	}
	public String getReserveString04() {
		return reserveString04;
	}
	public void setReserveString04(String reserveString04) {
		this.reserveString04 = reserveString04;
	}
	public String getReserveString05() {
		return reserveString05;
	}
	public void setReserveString05(String reserveString05) {
		this.reserveString05 = reserveString05;
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
