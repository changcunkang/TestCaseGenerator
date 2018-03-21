package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class CurInstalmentInfo {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Column
	private String instalsType;
	@Column
	private Double amortizationPri;
	@Column
	private Double totAmortizationPri;
	@Column
	private Double unAmortizationPri;
	@Column
	private Double amortizationFee;
	@Column
	private Double totAmortizationFee;
	@Column
	private Integer unAmortizationTerms;
	@Column
	private Integer AmortizationTerms;
	@Column
	private Integer totAppTerms;
	@Column
	private Double totAppAmt;
	@Column
	private Integer maxInstalTerms;
	public String getInstalsType() {
		return instalsType;
	}
	public void setInstalsType(String instalsType) {
		this.instalsType = instalsType;
	}
	public Double getAmortizationPri() {
		return amortizationPri;
	}
	public void setAmortizationPri(Double amortizationPri) {
		this.amortizationPri = amortizationPri;
	}
	public Double getTotAmortizationPri() {
		return totAmortizationPri;
	}
	public void setTotAmortizationPri(Double totAmortizationPri) {
		this.totAmortizationPri = totAmortizationPri;
	}
	public Double getUnAmortizationPri() {
		return unAmortizationPri;
	}
	public void setUnAmortizationPri(Double unAmortizationPri) {
		this.unAmortizationPri = unAmortizationPri;
	}
	public Double getAmortizationFee() {
		return amortizationFee;
	}
	public void setAmortizationFee(Double amortizationFee) {
		this.amortizationFee = amortizationFee;
	}
	public Double getTotAmortizationFee() {
		return totAmortizationFee;
	}
	public void setTotAmortizationFee(Double totAmortizationFee) {
		this.totAmortizationFee = totAmortizationFee;
	}
	public Integer getUnAmortizationTerms() {
		return unAmortizationTerms;
	}
	public void setUnAmortizationTerms(Integer unAmortizationTerms) {
		this.unAmortizationTerms = unAmortizationTerms;
	}
	public Integer getAmortizationTerms() {
		return AmortizationTerms;
	}
	public void setAmortizationTerms(Integer amortizationTerms) {
		AmortizationTerms = amortizationTerms;
	}
	public Integer getTotAppTerms() {
		return totAppTerms;
	}
	public void setTotAppTerms(Integer totAppTerms) {
		this.totAppTerms = totAppTerms;
	}
	public Double getTotAppAmt() {
		return totAppAmt;
	}
	public void setTotAppAmt(Double totAppAmt) {
		this.totAppAmt = totAppAmt;
	}
	public Integer getMaxInstalTerms() {
		return maxInstalTerms;
	}
	public void setMaxInstalTerms(Integer maxInstalTerms) {
		this.maxInstalTerms = maxInstalTerms;
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
