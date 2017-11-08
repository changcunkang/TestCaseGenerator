package com.cams.blaze.response;
/**
 * 
 * @author YuHuaPeng
 *
 */
public class ScoredCharacteristic{
  private String characteristic;
  private String mappedFieldName;
  private String characteristicName;
  private String characteristicValue;
  private String binLabel;
  private Double baselineScore;
  private Double partialScore;
  private Double maxBinScore;

  private Boolean unexpectedValueFlag;
 
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
}
