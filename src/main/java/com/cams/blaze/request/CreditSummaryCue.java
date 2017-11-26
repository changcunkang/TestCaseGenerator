package com.cams.blaze.request;

import javax.persistence.*;
@Entity
public class CreditSummaryCue {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private Integer houseLoanCount;
	@Column
	private Integer otherLoanCount;
	@Column
	private String firstLoanOpenMonth;
	@Column
	private Integer loancardCount;
	@Column
	private String firstLoancardOpenMonth;
	@Column
	private Integer standardLoancardCount;
	@Column
	private String firstStandardLoancardOpenMonth;
	public Integer getHouseLoanCount() {
		return houseLoanCount;
	}
	public void setHouseLoanCount(Integer houseLoanCount) {
		this.houseLoanCount = houseLoanCount;
	}
	public Integer getOtherLoanCount() {
		return otherLoanCount;
	}
	public void setOtherLoanCount(Integer otherLoanCount) {
		this.otherLoanCount = otherLoanCount;
	}
	public String getFirstLoanOpenMonth() {
		return firstLoanOpenMonth;
	}
	public void setFirstLoanOpenMonth(String firstLoanOpenMonth) {
		this.firstLoanOpenMonth = firstLoanOpenMonth;
	}
	public Integer getLoancardCount() {
		return loancardCount;
	}
	public void setLoancardCount(Integer loancardCount) {
		this.loancardCount = loancardCount;
	}
	public String getFirstLoancardOpenMonth() {
		return firstLoancardOpenMonth;
	}
	public void setFirstLoancardOpenMonth(String firstLoancardOpenMonth) {
		this.firstLoancardOpenMonth = firstLoancardOpenMonth;
	}
	public Integer getStandardLoancardCount() {
		return standardLoancardCount;
	}
	public void setStandardLoancardCount(Integer standardLoancardCount) {
		this.standardLoancardCount = standardLoancardCount;
	}
	public String getFirstStandardLoancardOpenMonth() {
		return firstStandardLoancardOpenMonth;
	}
	public void setFirstStandardLoancardOpenMonth(
			String firstStandardLoancardOpenMonth) {
		this.firstStandardLoancardOpenMonth = firstStandardLoancardOpenMonth;
	}
}
