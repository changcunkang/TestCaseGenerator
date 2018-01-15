package com.cams.blaze.request;

import javax.persistence.*;
import java.util.*;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private	 Long id;

	@Column
    private	 String customerNumber;
	@Column
    private	 Integer strategyRandomDigit01;
	@Column
    private	 Integer strategyRandomDigit02;

	@Column
    private	 Integer strategyRandomDigit03;
	@Column
    private	 Integer strategyRandomDigit04;
	@Column
    private	 Integer strategyRandomDigit05;
	@Column
    private	 Integer strategyRandomDigit06;
	@Column
    private	 Integer strategyRandomDigit07;
	@Column
    private	 Integer strategyRandomDigit08;
	@Column
    private	 Integer strategyRandomDigit09;
	@Column
    private	 Integer strategyRandomDigit10;
	@Column
    private	 String companyName;
	@Column
    private	 Double fstPbocAppScore;
	@Column
    private	 Double lstPbocAppScore;
	@Column
    private	 Double annualIncome;
	@Column
	private Double innerFundRadix;
	@Column
    private	 String educDes;
	@Column
    private	 String gender;
	@Column
    private	 Date birthday;
	@Column
    private	 String homeDes;
	@Column
    private	 Integer yearInComp;
	@Column
    private	 Integer cycleDay;
	@Column
    private	 Integer cnsmrMobileValidFlag;
	@Column
    private	 String riskLevel;
	@Column
	private String riskLevel1;
	@Column
	private String riskLevel2;
	@Column
    private	 String txFlag;
	@Column
	private String txFlag1;
	@Column
	private String layerCoder2Acct;

	@Column
	private String layerCode2Acct;
	@Column
	private String layerCoder2Custr;
	@Column
    private	 Integer fraudCusterFlag;
	@Column
    private	 Double capitalCostRatio;

	@Column
    private	 String reservedString01;
	@Column
    private	 String reservedString02;
	@Column
    private	 String reservedString03;
	@Column
    private	 String reservedString04;
	@Column
    private	 String reservedString05;
	@Column
    private	 String reservedString06;
	@Column
    private	 String reservedString07;
	@Column
    private	 String reservedString08;
	@Column
    private	 String reservedString09;
	@Column
    private	 String reservedString10;
	@Column
    private	 String reservedString11;
	@Column
    private	 String reservedString12;
	@Column
    private	 String reservedString13;
	@Column
    private	 String reservedString14;
	@Column
    private	 String reservedString15;
	@Column
    private	 String reservedString16;
	@Column
    private	 String reservedString17;
	@Column
    private	 String reservedString18;
	@Column
    private	 String reservedString19;
	@Column
    private	 String reservedString20;

	@Column
    private	 Double reservedDouble01;
	@Column
    private	 Double reservedDouble02;
	@Column
    private	 Double reservedDouble03;
	@Column
    private	 Double reservedDouble04;
	@Column
    private	 Double reservedDouble05;
	@Column
    private	 Double reservedDouble06;
	@Column
    private	 Double reservedDouble07;
	@Column
    private	 Double reservedDouble08;
	@Column
    private	 Double reservedDouble09;
	@Column
    private	 Double reservedDouble10;
	@Column
    private	 Double reservedDouble11;
	@Column
    private	 Double reservedDouble12;
	@Column
    private	 Double reservedDouble13;
	@Column
    private	 Double reservedDouble14;
	@Column
    private	 Double reservedDouble15;
	@Column
    private	 Double reservedDouble16;
	@Column
    private	 Double reservedDouble17;
	@Column
    private	 Double reservedDouble18;
	@Column
    private	 Double reservedDouble19;
	@Column
    private	 Double reservedDouble20;

	@Column
    private	 Integer reservedInteger01;
	@Column
    private	 Integer reservedInteger02;
	@Column
    private	 Integer reservedInteger03;
	@Column
    private	 Integer reservedInteger04;
	@Column
    private	 Integer reservedInteger05;
	@Column
    private	 Integer reservedInteger06;
	@Column
    private	 Integer reservedInteger07;
	@Column
    private	 Integer reservedInteger08;
	@Column
    private	 Integer reservedInteger09;
	@Column
    private	 Integer reservedInteger10;
	@Column
    private	 Integer reservedInteger11;
	@Column
    private	 Integer reservedInteger12;
	@Column
    private	 Integer reservedInteger13;
	@Column
    private	 Integer reservedInteger14;
	@Column
    private	 Integer reservedInteger15;
	@Column
    private	 Integer reservedInteger16;
	@Column
    private	 Integer reservedInteger17;
	@Column
    private	 Integer reservedInteger18;
	@Column
    private	 Integer reservedInteger19;
	@Column
    private	 Integer reservedInteger20;

	@Column
    private	 Date reservedDate01;
	@Column
    private	 Date reservedDate02;
	@Column
    private	 Date reservedDate03;
	@Column
    private	 Date reservedDate04;
	@Column
    private	 Date reservedDate05;
	@Column
    private	 Date reservedDate06;
	@Column
    private	 Date reservedDate07;
	@Column
    private	 Date reservedDate08;
	@Column
    private	 Date reservedDate09;
	@Column
    private	 Date reservedDate10;
	@Column
    private	 Date reservedDate11;
	@Column
    private	 Date reservedDate12;
	@Column
    private	 Date reservedDate13;
	@Column
    private	 Date reservedDate14;
	@Column
    private	 Date reservedDate15;
	@Column
    private	 Date reservedDate16;
	@Column
    private	 Date reservedDate17;
	@Column
    private	 Date reservedDate18;
	@Column
    private	 Date reservedDate19;
	@Column
    private	 Date reservedDate20;
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private	 List<EnabledDecisionArea> enabledDecisionArea = new ArrayList<EnabledDecisionArea>();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private	 List<PbocImportantTip> pbocImportantTip = new ArrayList<PbocImportantTip>();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private	 List<PbocCreditScore> pbocCreditScore = new ArrayList<PbocCreditScore>();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private	 List<PCustRef> pCustRef = new ArrayList<PCustRef>();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private	 List<TXScore> TXScore = new ArrayList<TXScore>();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private	 List<Pnote> pnote = new ArrayList<Pnote>();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private	 List<Product> product = new ArrayList<Product>();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private	 List<PbocReport> pbocReport = new ArrayList<PbocReport>();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private	 List<UserDefinedField> userDefinedField = new ArrayList<UserDefinedField>();


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getCapitalCostRatio() {
		return capitalCostRatio;
	}
	public void setCapitalCostRatio(Double capitalCostRatio) {
		this.capitalCostRatio = capitalCostRatio;
	}
	public String getReservedString01() {
		return reservedString01;
	}
	public void setReservedString01(String reservedString01) {
		this.reservedString01 = reservedString01;
	}
	public String getReservedString02() {
		return reservedString02;
	}
	public void setReservedString02(String reservedString02) {
		this.reservedString02 = reservedString02;
	}
	public String getReservedString03() {
		return reservedString03;
	}
	public void setReservedString03(String reservedString03) {
		this.reservedString03 = reservedString03;
	}
	public String getReservedString04() {
		return reservedString04;
	}
	public void setReservedString04(String reservedString04) {
		this.reservedString04 = reservedString04;
	}
	public String getReservedString05() {
		return reservedString05;
	}
	public void setReservedString05(String reservedString05) {
		this.reservedString05 = reservedString05;
	}
	public String getReservedString06() {
		return reservedString06;
	}
	public void setReservedString06(String reservedString06) {
		this.reservedString06 = reservedString06;
	}
	public String getReservedString07() {
		return reservedString07;
	}
	public void setReservedString07(String reservedString07) {
		this.reservedString07 = reservedString07;
	}
	public String getReservedString08() {
		return reservedString08;
	}
	public void setReservedString08(String reservedString08) {
		this.reservedString08 = reservedString08;
	}
	public String getReservedString09() {
		return reservedString09;
	}
	public void setReservedString09(String reservedString09) {
		this.reservedString09 = reservedString09;
	}
	public String getReservedString10() {
		return reservedString10;
	}
	public void setReservedString10(String reservedString10) {
		this.reservedString10 = reservedString10;
	}
	public String getReservedString11() {
		return reservedString11;
	}
	public void setReservedString11(String reservedString11) {
		this.reservedString11 = reservedString11;
	}
	public String getReservedString12() {
		return reservedString12;
	}
	public void setReservedString12(String reservedString12) {
		this.reservedString12 = reservedString12;
	}
	public String getReservedString13() { return reservedString13; }
	public void setReservedString13(String reservedString13) {
		this.reservedString13 = reservedString13;
	}
	public String getReservedString14() {
		return reservedString14;
	}
	public void setReservedString14(String reservedString14) {
		this.reservedString14 = reservedString14;
	}
	public String getReservedString15() {
		return reservedString15;
	}
	public void setReservedString15(String reservedString15) {
		this.reservedString15 = reservedString15;
	}
	public String getReservedString16() {
		return reservedString16;
	}
	public void setReservedString16(String reservedString16) {
		this.reservedString16 = reservedString16;
	}
	public String getReservedString17() {
		return reservedString17;
	}
	public void setReservedString17(String reservedString17) {
		this.reservedString17 = reservedString17;
	}
	public String getReservedString18() {
		return reservedString18;
	}
	public void setReservedString18(String reservedString18) {
		this.reservedString18 = reservedString18;
	}
	public String getReservedString19() {
		return reservedString19;
	}
	public void setReservedString19(String reservedString19) {
		this.reservedString19 = reservedString19;
	}
	public String getReservedString20() {
		return reservedString20;
	}
	public void setReservedString20(String reservedString20) {
		this.reservedString20 = reservedString20;
	}
	public Double getReservedDouble01() {
		return reservedDouble01;
	}
	public void setReservedDouble01(Double reservedDouble01) {
		this.reservedDouble01 = reservedDouble01;
	}
	public Double getReservedDouble02() {
		return reservedDouble02;
	}
	public void setReservedDouble02(Double reservedDouble02) {
		this.reservedDouble02 = reservedDouble02;
	}
	public Double getReservedDouble03() {
		return reservedDouble03;
	}
	public void setReservedDouble03(Double reservedDouble03) {
		this.reservedDouble03 = reservedDouble03;
	}
	public Double getReservedDouble04() {
		return reservedDouble04;
	}
	public void setReservedDouble04(Double reservedDouble04) {
		this.reservedDouble04 = reservedDouble04;
	}
	public Double getReservedDouble05() {
		return reservedDouble05;
	}
	public void setReservedDouble05(Double reservedDouble05) {
		this.reservedDouble05 = reservedDouble05;
	}
	public Double getReservedDouble06() {
		return reservedDouble06;
	}
	public void setReservedDouble06(Double reservedDouble06) {
		this.reservedDouble06 = reservedDouble06;
	}
	public Double getReservedDouble07() {
		return reservedDouble07;
	}
	public void setReservedDouble07(Double reservedDouble07) {
		this.reservedDouble07 = reservedDouble07;
	}
	public Double getReservedDouble08() {
		return reservedDouble08;
	}
	public void setReservedDouble08(Double reservedDouble08) {
		this.reservedDouble08 = reservedDouble08;
	}
	public Double getReservedDouble09() {
		return reservedDouble09;
	}
	public void setReservedDouble09(Double reservedDouble09) {
		this.reservedDouble09 = reservedDouble09;
	}
	public Double getReservedDouble10() {
		return reservedDouble10;
	}
	public void setReservedDouble10(Double reservedDouble10) {
		this.reservedDouble10 = reservedDouble10;
	}
	public Double getReservedDouble11() {
		return reservedDouble11;
	}
	public void setReservedDouble11(Double reservedDouble11) {
		this.reservedDouble11 = reservedDouble11;
	}
	public Double getReservedDouble12() {
		return reservedDouble12;
	}
	public void setReservedDouble12(Double reservedDouble12) {
		this.reservedDouble12 = reservedDouble12;
	}
	public Double getReservedDouble13() {
		return reservedDouble13;
	}
	public void setReservedDouble13(Double reservedDouble13) {
		this.reservedDouble13 = reservedDouble13;
	}
	public Double getReservedDouble14() {
		return reservedDouble14;
	}
	public void setReservedDouble14(Double reservedDouble14) {
		this.reservedDouble14 = reservedDouble14;
	}
	public Double getReservedDouble15() {
		return reservedDouble15;
	}
	public void setReservedDouble15(Double reservedDouble15) {
		this.reservedDouble15 = reservedDouble15;
	}
	public Double getReservedDouble16() {
		return reservedDouble16;
	}
	public void setReservedDouble16(Double reservedDouble16) {
		this.reservedDouble16 = reservedDouble16;
	}
	public Double getReservedDouble17() {
		return reservedDouble17;
	}
	public void setReservedDouble17(Double reservedDouble17) {
		this.reservedDouble17 = reservedDouble17;
	}
	public Double getReservedDouble18() {
		return reservedDouble18;
	}
	public void setReservedDouble18(Double reservedDouble18) {
		this.reservedDouble18 = reservedDouble18;
	}
	public Double getReservedDouble19() {
		return reservedDouble19;
	}
	public void setReservedDouble19(Double reservedDouble19) {
		this.reservedDouble19 = reservedDouble19;
	}
	public Double getReservedDouble20() {
		return reservedDouble20;
	}
	public void setReservedDouble20(Double reservedDouble20) {
		this.reservedDouble20 = reservedDouble20;
	}
	public Integer getReservedInteger01() {
		return reservedInteger01;
	}
	public void setReservedInteger01(Integer reservedInteger01) {
		this.reservedInteger01 = reservedInteger01;
	}
	public Integer getReservedInteger02() {
		return reservedInteger02;
	}
	public void setReservedInteger02(Integer reservedInteger02) {
		this.reservedInteger02 = reservedInteger02;
	}
	public Integer getReservedInteger03() {
		return reservedInteger03;
	}
	public void setReservedInteger03(Integer reservedInteger03) {
		this.reservedInteger03 = reservedInteger03;
	}
	public Integer getReservedInteger04() {
		return reservedInteger04;
	}
	public void setReservedInteger04(Integer reservedInteger04) {
		this.reservedInteger04 = reservedInteger04;
	}
	public Integer getReservedInteger05() {
		return reservedInteger05;
	}
	public void setReservedInteger05(Integer reservedInteger05) {
		this.reservedInteger05 = reservedInteger05;
	}
	public Integer getReservedInteger06() {
		return reservedInteger06;
	}
	public void setReservedInteger06(Integer reservedInteger06) {
		this.reservedInteger06 = reservedInteger06;
	}
	public Integer getReservedInteger07() {
		return reservedInteger07;
	}
	public void setReservedInteger07(Integer reservedInteger07) {
		this.reservedInteger07 = reservedInteger07;
	}
	public Integer getReservedInteger08() {
		return reservedInteger08;
	}
	public void setReservedInteger08(Integer reservedInteger08) {
		this.reservedInteger08 = reservedInteger08;
	}
	public Integer getReservedInteger09() {
		return reservedInteger09;
	}
	public void setReservedInteger09(Integer reservedInteger09) {
		this.reservedInteger09 = reservedInteger09;
	}
	public Integer getReservedInteger10() {
		return reservedInteger10;
	}
	public void setReservedInteger10(Integer reservedInteger10) {
		this.reservedInteger10 = reservedInteger10;
	}
	public Integer getReservedInteger11() {
		return reservedInteger11;
	}
	public void setReservedInteger11(Integer reservedInteger11) {
		this.reservedInteger11 = reservedInteger11;
	}
	public Integer getReservedInteger12() {
		return reservedInteger12;
	}
	public void setReservedInteger12(Integer reservedInteger12) {
		this.reservedInteger12 = reservedInteger12;
	}
	public Integer getReservedInteger13() {
		return reservedInteger13;
	}
	public void setReservedInteger13(Integer reservedInteger13) {
		this.reservedInteger13 = reservedInteger13;
	}
	public Integer getReservedInteger14() {
		return reservedInteger14;
	}
	public void setReservedInteger14(Integer reservedInteger14) {
		this.reservedInteger14 = reservedInteger14;
	}
	public Integer getReservedInteger15() {
		return reservedInteger15;
	}
	public void setReservedInteger15(Integer reservedInteger15) {
		this.reservedInteger15 = reservedInteger15;
	}
	public Integer getReservedInteger16() {
		return reservedInteger16;
	}
	public void setReservedInteger16(Integer reservedInteger16) {
		this.reservedInteger16 = reservedInteger16;
	}
	public Integer getReservedInteger17() {
		return reservedInteger17;
	}
	public void setReservedInteger17(Integer reservedInteger17) {
		this.reservedInteger17 = reservedInteger17;
	}
	public Integer getReservedInteger18() {
		return reservedInteger18;
	}
	public void setReservedInteger18(Integer reservedInteger18) {
		this.reservedInteger18 = reservedInteger18;
	}
	public Integer getReservedInteger19() {
		return reservedInteger19;
	}
	public void setReservedInteger19(Integer reservedInteger19) {
		this.reservedInteger19 = reservedInteger19;
	}
	public Integer getReservedInteger20() {
		return reservedInteger20;
	}
	public void setReservedInteger20(Integer reservedInteger20) {
		this.reservedInteger20 = reservedInteger20;
	}
	public Date getReservedDate01() {
		return reservedDate01;
	}
	public void setReservedDate01(Date reservedDate01) {
		this.reservedDate01 = reservedDate01;
	}
	public Date getReservedDate02() {
		return reservedDate02;
	}
	public void setReservedDate02(Date reservedDate02) {
		this.reservedDate02 = reservedDate02;
	}
	public Date getReservedDate03() {
		return reservedDate03;
	}
	public void setReservedDate03(Date reservedDate03) {
		this.reservedDate03 = reservedDate03;
	}
	public Date getReservedDate04() {
		return reservedDate04;
	}
	public void setReservedDate04(Date reservedDate04) {
		this.reservedDate04 = reservedDate04;
	}
	public Date getReservedDate05() {
		return reservedDate05;
	}
	public void setReservedDate05(Date reservedDate05) {
		this.reservedDate05 = reservedDate05;
	}
	public Date getReservedDate06() {
		return reservedDate06;
	}
	public void setReservedDate06(Date reservedDate06) {
		this.reservedDate06 = reservedDate06;
	}
	public Date getReservedDate07() {
		return reservedDate07;
	}
	public void setReservedDate07(Date reservedDate07) {
		this.reservedDate07 = reservedDate07;
	}
	public Date getReservedDate08() {
		return reservedDate08;
	}
	public void setReservedDate08(Date reservedDate08) {
		this.reservedDate08 = reservedDate08;
	}
	public Date getReservedDate09() {
		return reservedDate09;
	}
	public void setReservedDate09(Date reservedDate09) {
		this.reservedDate09 = reservedDate09;
	}
	public Date getReservedDate10() {
		return reservedDate10;
	}
	public void setReservedDate10(Date reservedDate10) {
		this.reservedDate10 = reservedDate10;
	}
	public Date getReservedDate11() {
		return reservedDate11;
	}
	public void setReservedDate11(Date reservedDate11) {
		this.reservedDate11 = reservedDate11;
	}
	public Date getReservedDate12() {
		return reservedDate12;
	}
	public void setReservedDate12(Date reservedDate12) {
		this.reservedDate12 = reservedDate12;
	}
	public Date getReservedDate13() {
		return reservedDate13;
	}
	public void setReservedDate13(Date reservedDate13) {
		this.reservedDate13 = reservedDate13;
	}
	public Date getReservedDate14() {
		return reservedDate14;
	}
	public void setReservedDate14(Date reservedDate14) {
		this.reservedDate14 = reservedDate14;
	}
	public Date getReservedDate15() {
		return reservedDate15;
	}
	public void setReservedDate15(Date reservedDate15) {
		this.reservedDate15 = reservedDate15;
	}
	public Date getReservedDate16() {
		return reservedDate16;
	}
	public void setReservedDate16(Date reservedDate16) {
		this.reservedDate16 = reservedDate16;
	}
	public Date getReservedDate17() {
		return reservedDate17;
	}
	public void setReservedDate17(Date reservedDate17) {
		this.reservedDate17 = reservedDate17;
	}
	public Date getReservedDate18() {
		return reservedDate18;
	}
	public void setReservedDate18(Date reservedDate18) {
		this.reservedDate18 = reservedDate18;
	}
	public Date getReservedDate19() {
		return reservedDate19;
	}
	public void setReservedDate19(Date reservedDate19) {
		this.reservedDate19 = reservedDate19;
	}
	public Date getReservedDate20() {
		return reservedDate20;
	}
	public void setReservedDate20(Date reservedDate20) {
		this.reservedDate20 = reservedDate20;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}	
	public List<TXScore> getTXScore() {
		return TXScore;
	}
	public void setTXScore(List<TXScore> tXScore) {
		TXScore = tXScore;
	}
	public Double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(Double annualIncome) {
		this.annualIncome = annualIncome;
	}
	public String getEducDes() {
		return educDes;
	}
	public void setEducDes(String educDes) {
		this.educDes = educDes;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getHomeDes() {
		return homeDes;
	}
	public void setHomeDes(String homeDes) {
		this.homeDes = homeDes;
	}
	public Integer getYearInComp() {
		return yearInComp;
	}
	public void setYearInComp(Integer yearInComp) {
		this.yearInComp = yearInComp;
	}
	public Integer getCnsmrMobileValidFlag() {
		return cnsmrMobileValidFlag;
	}
	public void setCnsmrMobileValidFlag(Integer cnsmrMobileValidFlag) {
		this.cnsmrMobileValidFlag = cnsmrMobileValidFlag;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	public Integer getStrategyRandomDigit01() {
		return strategyRandomDigit01;
	}
	public void setStrategyRandomDigit01(Integer strategyRandomDigit01) {
		this.strategyRandomDigit01 = strategyRandomDigit01;
	}
	public Integer getStrategyRandomDigit02() {
		return strategyRandomDigit02;
	}
	public void setStrategyRandomDigit02(Integer strategyRandomDigit02) {
		this.strategyRandomDigit02 = strategyRandomDigit02;
	}
	public Integer getStrategyRandomDigit03() {
		return strategyRandomDigit03;
	}
	public void setStrategyRandomDigit03(Integer strategyRandomDigit03) {
		this.strategyRandomDigit03 = strategyRandomDigit03;
	}
	public Integer getStrategyRandomDigit04() {
		return strategyRandomDigit04;
	}
	public void setStrategyRandomDigit04(Integer strategyRandomDigit04) {
		this.strategyRandomDigit04 = strategyRandomDigit04;
	}
	public Integer getStrategyRandomDigit05() {
		return strategyRandomDigit05;
	}
	public void setStrategyRandomDigit05(Integer strategyRandomDigit05) {
		this.strategyRandomDigit05 = strategyRandomDigit05;
	}
	public Integer getStrategyRandomDigit06() {
		return strategyRandomDigit06;
	}
	public void setStrategyRandomDigit06(Integer strategyRandomDigit06) {
		this.strategyRandomDigit06 = strategyRandomDigit06;
	}
	public Integer getStrategyRandomDigit07() {
		return strategyRandomDigit07;
	}
	public void setStrategyRandomDigit07(Integer strategyRandomDigit07) {
		this.strategyRandomDigit07 = strategyRandomDigit07;
	}
	public Integer getStrategyRandomDigit08() {
		return strategyRandomDigit08;
	}
	public void setStrategyRandomDigit08(Integer strategyRandomDigit08) {
		this.strategyRandomDigit08 = strategyRandomDigit08;
	}
	public Integer getStrategyRandomDigit09() {
		return strategyRandomDigit09;
	}
	public void setStrategyRandomDigit09(Integer strategyRandomDigit09) {
		this.strategyRandomDigit09 = strategyRandomDigit09;
	}
	public Integer getStrategyRandomDigit10() {
		return strategyRandomDigit10;
	}
	public void setStrategyRandomDigit10(Integer strategyRandomDigit10) {
		this.strategyRandomDigit10 = strategyRandomDigit10;
	}
	public List<EnabledDecisionArea> getEnabledDecisionArea() {
		return enabledDecisionArea;
	}
	public void setEnabledDecisionArea(List<EnabledDecisionArea> enabledDecisionArea) {
		this.enabledDecisionArea = enabledDecisionArea;
	}
	public Integer getCycleDay() {
		return cycleDay;
	}
	public void setCycleDay(Integer cycleDay) {
		this.cycleDay = cycleDay;
	}
	public List<PbocImportantTip> getPbocImportantTip() {
		return pbocImportantTip;
	}
	public void setPbocImportantTip(List<PbocImportantTip> pbocImportantTip) {
		this.pbocImportantTip = pbocImportantTip;
	}
	public List<PbocCreditScore> getPbocCreditScore() {
		return pbocCreditScore;
	}
	public void setPbocCreditScore(List<PbocCreditScore> pbocCreditScore) {
		this.pbocCreditScore = pbocCreditScore;
	}
	public List<Pnote> getPnote() {
		return pnote;
	}
	public void setPnote(List<Pnote> pnote) {
		this.pnote = pnote;
	}
	public List<PbocReport> getPbocReport() {
		return pbocReport;
	}
	public void setPbocReport(List<PbocReport> pbocReport) {
		this.pbocReport = pbocReport;
	}
	public List<PCustRef> getpCustRef() {
		return pCustRef;
	}
	public void setpCustRef(List<PCustRef> pCustRef) {
		this.pCustRef = pCustRef;
	}
	public Double getLstPbocAppScore() {
		return lstPbocAppScore;
	}
	public void setLstPbocAppScore(Double lstPbocAppScore) {
		this.lstPbocAppScore = lstPbocAppScore;
	}
	public Double getFstPbocAppScore() {
		return fstPbocAppScore;
	}
	public void setFstPbocAppScore(Double fstPbocAppScore) {
		this.fstPbocAppScore = fstPbocAppScore;
	}
	public String getTxFlag() {
		return txFlag;
	}
	public void setTxFlag(String txFlag) {
		this.txFlag = txFlag;
	}
	public Integer getFraudCusterFlag() {
		return fraudCusterFlag;
	}
	public void setFraudCusterFlag(Integer fraudCusterFlag) {
		this.fraudCusterFlag = fraudCusterFlag;
	}
	public List<UserDefinedField> getUserDefinedField() {
		return userDefinedField;
	}
	public void setUserDefinedField(List<UserDefinedField> userDefinedField) {
		this.userDefinedField = userDefinedField;
	}

	public String getRiskLevel2() {
		return riskLevel2;
	}

	public void setRiskLevel2(String riskLevel2) {
		this.riskLevel2 = riskLevel2;
	}

	public String getRiskLevel1() {
		return riskLevel1;
	}

	public void setRiskLevel1(String riskLevel1) {
		this.riskLevel1 = riskLevel1;
	}

	public String getTxFlag1() {
		return txFlag1;
	}

	public void setTxFlag1(String txFlag1) {
		this.txFlag1 = txFlag1;
	}

	public String getLayerCoder2Custr() {
		return layerCoder2Custr;
	}

	public void setLayerCoder2Custr(String layerCoder2Custr) {
		this.layerCoder2Custr = layerCoder2Custr;
	}

	public String getLayerCode2Acct() {
		return layerCode2Acct;
	}

	public void setLayerCode2Acct(String layerCode2Acct) {
		this.layerCode2Acct = layerCode2Acct;
	}

	public Double getInnerFundRadix() {
		return innerFundRadix;
	}

	public void setInnerFundRadix(Double innerFundRadix) {
		this.innerFundRadix = innerFundRadix;
	}

	public String getLayerCoder2Acct() {
		return layerCoder2Acct;
	}

	public void setLayerCoder2Acct(String layerCoder2Acct) {
		this.layerCoder2Acct = layerCoder2Acct;
	}
}
