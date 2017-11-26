package com.cams.blaze.request;

import javax.persistence.*;

@Entity
public class CardsBlkInfo {
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
	private String maflag;
	@Column
	private String cardNum;
	@Column
	private String cardstat;
	@Column
	private String product;
	public String getMaflag() {
		return maflag;
	}
	public void setMaflag(String maflag) {
		this.maflag = maflag;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getCardstat() {
		return cardstat;
	}
	public void setCardstat(String cardstat) {
		this.cardstat = cardstat;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	
	

}
