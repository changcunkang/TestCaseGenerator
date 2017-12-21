package com.cams.blaze.request;

import javax.persistence.*;
import java.util.*;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private	 List<Account> account = new ArrayList<Account>();
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private	 List<EnabledDecisionArea> enabledDecisionArea = new ArrayList<EnabledDecisionArea>();
	@Transient
    private	 List<UserDefinedField> userDefinedField = new ArrayList<UserDefinedField>();
	@Column
    private	 String productNumber;
	@Column
    private	 String productCode;
	@Column
    private	 Double bScore;
	@Column
    private	 Double ptfqScore;
	@Column
    private	 Double defqScore;
	@Column
    private	 Double xhCuScore;
	@Column
	private Double gykScore;
	@Column
    private	 Double score01;
	@Column
    private	 Double score02;
	@Column
    private	 Double score03;
	@Column
    private	 Double score04;
	@Column
    private	 Double score05;
	@Column
    private	 Double score06;
	@Column
    private	 Double score07;
	@Column
    private	 Double score08;
	@Column
    private	 Double score09;
	@Column
    private	 Double score10;
	@Column
    private	 String channelType;
	@Column
    private	 String customerSegmentation;
	@Column
	private String customerSegmentation1;
	@Column
	private String customerSegmentation2;
	@Column
    private	 String adjustLimitType;
	@Column
	private String adjustLimitReason;
	@Column
    private	 Double applyALAmt;
	@Column
    private	 Double applyInstalAmt;
	@Column
    private	 Integer applyInstalTerms;
	@Column
    private	 Double preALAmt1;
	@Column
    private	 Date preALVaildDate1;
	@Column
    private	 Double preALAmt2;
	@Column
    private	 Date preALVaildDate2;
	@Column
    private	 Double preALAmt3;
	@Column
    private	 Date preALVaildDate3;
	@Column
    private	 Double preALAmt4;
	@Column
    private	 Date preALVaildDate4;
	@Column
    private	 Double preALAmt5;
	@Column
    private	 Date preALVaildDate5;

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
	

	public Double getPtfqScore() {
		return ptfqScore;
	}
	public void setPtfqScore(Double ptfqScore) {
		this.ptfqScore = ptfqScore;
	}
	public Double getDefqScore() {
		return defqScore;
	}
	public void setDefqScore(Double defqScore) {
		this.defqScore = defqScore;
	}
	public Double getPreALAmt1() {
		return preALAmt1;
	}
	public void setPreALAmt1(Double preALAmt1) {
		this.preALAmt1 = preALAmt1;
	}
	public Date getPreALVaildDate1() {
		return preALVaildDate1;
	}
	public void setPreALVaildDate1(Date preALVaildDate1) {
		this.preALVaildDate1 = preALVaildDate1;
	}
	public Double getPreALAmt2() {
		return preALAmt2;
	}
	public void setPreALAmt2(Double preALAmt2) {
		this.preALAmt2 = preALAmt2;
	}
	public Date getPreALVaildDate2() {
		return preALVaildDate2;
	}
	public void setPreALVaildDate2(Date preALVaildDate2) {
		this.preALVaildDate2 = preALVaildDate2;
	}
	public Double getPreALAmt3() {
		return preALAmt3;
	}
	public void setPreALAmt3(Double preALAmt3) {
		this.preALAmt3 = preALAmt3;
	}
	public Date getPreALVaildDate3() {
		return preALVaildDate3;
	}
	public void setPreALVaildDate3(Date preALVaildDate3) {
		this.preALVaildDate3 = preALVaildDate3;
	}
	public Double getPreALAmt4() {
		return preALAmt4;
	}
	public void setPreALAmt4(Double preALAmt4) {
		this.preALAmt4 = preALAmt4;
	}
	public Date getPreALVaildDate4() {
		return preALVaildDate4;
	}
	public void setPreALVaildDate4(Date preALVaildDate4) {
		this.preALVaildDate4 = preALVaildDate4;
	}
	public Double getPreALAmt5() {
		return preALAmt5;
	}
	public void setPreALAmt5(Double preALAmt5) {
		this.preALAmt5 = preALAmt5;
	}
	public Date getPreALVaildDate5() {
		return preALVaildDate5;
	}
	public void setPreALVaildDate5(Date preALVaildDate5) {
		this.preALVaildDate5 = preALVaildDate5;
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
	public String getReservedString13() {
		return reservedString13;
	}
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
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Double getbScore() {
		return bScore;
	}
	public void setbScore(Double bScore) {
		this.bScore = bScore;
	}
	public Double getXhCuScore() {
		return xhCuScore;
	}
	public void setXhCuScore(Double xhCuScore) {
		this.xhCuScore = xhCuScore;
	}
	public Double getScore01() {
		return score01;
	}
	public void setScore01(Double score01) {
		this.score01 = score01;
	}
	public Double getScore02() {
		return score02;
	}
	public void setScore02(Double score02) {
		this.score02 = score02;
	}
	public Double getScore03() {
		return score03;
	}
	public void setScore03(Double score03) {
		this.score03 = score03;
	}
	public Double getScore04() {
		return score04;
	}
	public void setScore04(Double score04) {
		this.score04 = score04;
	}
	public Double getScore05() {
		return score05;
	}
	public void setScore05(Double score05) {
		this.score05 = score05;
	}
	public Double getScore06() {
		return score06;
	}
	public void setScore06(Double score06) {
		this.score06 = score06;
	}
	public Double getScore07() {
		return score07;
	}
	public void setScore07(Double score07) {
		this.score07 = score07;
	}
	public Double getScore08() {
		return score08;
	}
	public void setScore08(Double score08) {
		this.score08 = score08;
	}
	public Double getScore09() {
		return score09;
	}
	public void setScore09(Double score09) {
		this.score09 = score09;
	}
	public Double getScore10() {
		return score10;
	}
	public void setScore10(Double score10) {
		this.score10 = score10;
	}
	public String getCustomerSegmentation() {
		return customerSegmentation;
	}
	public void setCustomerSegmentation(String customerSegmentation) {
		this.customerSegmentation = customerSegmentation;
	}
	public String getAdjustLimitType() {
		return adjustLimitType;
	}
	public void setAdjustLimitType(String adjustLimitType) {
		this.adjustLimitType = adjustLimitType;
	}
	public Double getApplyALAmt() {
		return applyALAmt;
	}
	public void setApplyALAmt(Double applyALAmt) {
		this.applyALAmt = applyALAmt;
	}
	public Double getApplyInstalAmt() {
		return applyInstalAmt;
	}
	public void setApplyInstalAmt(Double applyInstalAmt) {
		this.applyInstalAmt = applyInstalAmt;
	}
	public List<Account> getAccount() {
		return account;
	}
	public void setAccount(List<Account> account) {
		this.account = account;
	}
	public List<EnabledDecisionArea> getEnabledDecisionArea() {
		return enabledDecisionArea;
	}
	public void setEnabledDecisionArea(List<EnabledDecisionArea> enabledDecisionArea) {
		this.enabledDecisionArea = enabledDecisionArea;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public Integer getApplyInstalTerms() {
		return applyInstalTerms;
	}
	public void setApplyInstalTerms(Integer applyInstalTerms) {
		this.applyInstalTerms = applyInstalTerms;
	}
	public List<UserDefinedField> getUserDefinedField() {
		return userDefinedField;
	}
	public void setUserDefinedField(List<UserDefinedField> userDefinedField) {
		this.userDefinedField = userDefinedField;
	}

	public Double getGykScore() {
		return gykScore;
	}

	public void setGykScore(Double gykScore) {
		this.gykScore = gykScore;
	}

	public String getCustomerSegmentation1() {
		return customerSegmentation1;
	}

	public void setCustomerSegmentation1(String customerSegmentation1) {
		this.customerSegmentation1 = customerSegmentation1;
	}

	public String getCustomerSegmentation2() {
		return customerSegmentation2;
	}

	public void setCustomerSegmentation2(String customerSegmentation2) {
		this.customerSegmentation2 = customerSegmentation2;
	}

	public String getAdjustLimitReason() {
		return adjustLimitReason;
	}

	public void setAdjustLimitReason(String adjustLimitReason) {
		this.adjustLimitReason = adjustLimitReason;
	}
}
