package com.cams.blaze.response;

import java.util.Date;

/**
 * 
 * @author YuHuaPeng
 *
 */
public class DerivedVariable {
	private Integer index;
	private String associationID;
	private String variableSetName;
	private String variableName;
	private String variableCode;
	private String variableValue;
	private String dataType;
	private String implementationType;
	private String implementationName;
	private String stringValue;
	private Double realValue;
	private Integer integerValue;
	private Date dateValue;

	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public Double getRealValue() {
		return realValue;
	}
	public void setRealValue(Double realValue) {
		this.realValue = realValue;
	}
	public Integer getIntegerValue() {
		return integerValue;
	}
	public void setIntegerValue(Integer integerValue) {
		this.integerValue = integerValue;
	}
	public Date getDateValue() {
		return dateValue;
	}
	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getImplementationType() {
		return implementationType;
	}
	public void setImplementationType(String implementationType) {
		this.implementationType = implementationType;
	}
	public String getImplementationName() {
		return implementationName;
	}
	public void setImplementationName(String implementationName) {
		this.implementationName = implementationName;
	}
	public String getAssociationID() {
		return associationID;
	}
	public void setAssociationID(String associationID) {
		this.associationID = associationID;
	}
	public String getVariableCode() {
		return variableCode;
	}
	public void setVariableCode(String variableCode) {
		this.variableCode = variableCode;
	}
	public String getVariableName() {
		return variableName;
	}
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getVariableSetName() {
		return variableSetName;
	}
	public void setVariableSetName(String variableSetName) {
		this.variableSetName = variableSetName;
	}
	public String getVariableValue() {
		return variableValue;
	}
	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

}
