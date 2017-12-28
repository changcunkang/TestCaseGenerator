package com.fico.testCaseGenerator.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fico.testCaseGenerator.data.configuration.Extendtion;

public abstract class TestData extends AbstractTestData {

	private TestData parentTestData;

	protected List<SimpleField> simpleFieldList;
	
	protected List<TestData> customFieldList;
	
	private Object implementElement;
	
	private Extendtion extendtion;
	
	protected String path;

	public String getParentFieldName() {
		return parentFieldName;
	}

	public void setParentFieldName(String parentFieldName) {
		this.parentFieldName = parentFieldName;
	}

	private String parentFieldName;

	private TestCaseUnit generatingTestCaseUnit;

	private TestCaseUnit generatingChildrenTestCaseUnit;

	private TestCaseUnit[] tempGeneratingArr = null;

	public TestCaseUnit[] getTempGeneratingTestCaseUnitArr() {
		return tempGeneratingArr;
	}

	public void setTempGeneratingArr(TestCaseUnit[] tempGeneratingArr) {
		this.tempGeneratingArr = tempGeneratingArr;
	}

	public boolean isGeneratingTestDataLastChild() {
		return this.getGeneratingTestCaseUnit().isLastChild();
	}

	public TestCaseUnit getGeneratingChildrenTestCaseUnit() {
		return generatingChildrenTestCaseUnit;
	}

	public void setGeneratingChildrenTestCaseUnit(TestCaseUnit generatingChildrenTestCaseUnit) {
		this.generatingChildrenTestCaseUnit = generatingChildrenTestCaseUnit;
	}


	public boolean isGeneratingTestDataFirstChild() {

		return this.getGeneratingTestCaseUnit().isFirstChild();
	}

	public Set<SimpleField> getTmpGeneratedSimpleFieldSet() {

		if( tmpGeneratedSimpleFieldSet == null ){
			tmpGeneratedSimpleFieldSet = new HashSet<SimpleField>();
		}

		return tmpGeneratedSimpleFieldSet;
	}

	public void setTmpGeneratedSimpleFieldSet(Set<SimpleField> tmpGeneratedSimpleFieldSet) {
		this.tmpGeneratedSimpleFieldSet = tmpGeneratedSimpleFieldSet;
	}

	private Set<SimpleField> tmpGeneratedSimpleFieldSet = null;



	public TestCaseUnit getGeneratingTestCaseUnit() {
		return generatingTestCaseUnit;
	}

	public void setGeneratingTestCaseUnit(TestCaseUnit generatingTestData) {
		this.generatingTestCaseUnit = generatingTestData;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Extendtion getExtendtion() {
		return extendtion;
	}

	public void setExtendtion(Extendtion extendtion) {
		this.extendtion = extendtion;
	}

	public Object getDataStructureElement() {
		return implementElement;
	}

	public void setDataStructureElement(Object implementElement) {
		this.implementElement = implementElement;
	}

	public List<SimpleField> getSimpleFieldList() {
		
		if(simpleFieldList == null){
			simpleFieldList = new ArrayList<SimpleField>();
		}
		
		return simpleFieldList;
	}

	public void setSimpleFieldList(List<SimpleField> simpleFieldList) {
		this.simpleFieldList = simpleFieldList;
	}

	public List<TestData> getCustomFieldList() {
		
		if(customFieldList == null){
			customFieldList = new ArrayList<TestData>();
		}
		
		return customFieldList;
	}



	public void setCustomFieldList(List<TestData> customFieldList) {
		this.customFieldList = customFieldList;
	}

	public TestData getParentTestData() {
		return parentTestData;
	}

	public void setParentTestData(TestData parentTestData) {
		this.parentTestData = parentTestData;
	}

	public String getPath() {

		if(this.path != null){
			return this.path;
		}

		if( this.getParentTestData() != null ){
			this.path = this.getParentTestData().getPath() + this.getName() + "/";
		}
		else{
			this.path = this.getName() + "/";
		}

		return this.path;
	}

	@Override
	public String toString(){
		return this.getName();
	}
}
