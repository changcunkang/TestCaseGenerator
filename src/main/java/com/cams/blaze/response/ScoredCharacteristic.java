package com.cams.blaze.response;

import javax.persistence.*;

/**
 * 
 * @author YuHuaPeng
 *
 */
@Entity
public class ScoredCharacteristic{
  @Id
  @GeneratedValue(strategy= GenerationType.SEQUENCE)
  @Column
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  @Column
	private String characteristic;
  @Column
	private String mappedFieldName;
  @Column
	private String characteristicName;
  @Column
	private String characteristicValue;
  @Column
	private String binLabel;
  @Column
	private Double baselineScore;
  @Column
	private Double partialScore;
  @Column
	private Double maxBinScore;

  @Column
	private Boolean unexpectedValueFlag;

  @Column
  private Double weight;
 
  public String getCharacteristicName()
  {
    return this.characteristicName;
  }
  public void setCharacteristicName(String characteristicName) {
    this.characteristicName = characteristicName;
  }
  public String getCharacteristicValue() {
    return this.characteristicValue;
  }
  public void setCharacteristicValue(String characteristicValue) {
    this.characteristicValue = characteristicValue;
  }
  public String getBinLabel() {
    return this.binLabel;
  }
  public void setBinLabel(String binLabel) {
    this.binLabel = binLabel;
  }
  public Double getBaselineScore() {
    return this.baselineScore;
  }
  public void setBaselineScore(Double baselineScore) {
    this.baselineScore = baselineScore;
  }
  public Double getPartialScore() {
    return this.partialScore;
  }
  public void setPartialScore(Double partialScore) {
    this.partialScore = partialScore;
  }
  public Double getMaxBinScore() {
    return this.maxBinScore;
  }
  public void setMaxBinScore(Double maxBinScore) {
    this.maxBinScore = maxBinScore;
  }
  public Boolean getUnexpectedValueFlag() {
    return this.unexpectedValueFlag;
  }
  public void setUnexpectedValueFlag(Boolean unexpectedValueFlag) {
    this.unexpectedValueFlag = unexpectedValueFlag;
  }
public String getCharacteristic() {
	return characteristic;
}
public void setCharacteristic(String characteristic) {
	this.characteristic = characteristic;
}
public String getMappedFieldName() {
	return mappedFieldName;
}
public void setMappedFieldName(String mappedFieldName) {
	this.mappedFieldName = mappedFieldName;
}
  public Double getWeight() {
    return weight;
  }
  public void setWeight(Double weight) {
    this.weight = weight;
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
