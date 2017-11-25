package com.fico.testCaseGenerator.data;

import java.util.ArrayList;
import java.util.List;

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

	private Object generatingTestData;

	private boolean isGeneratingTestDataFirstChild = false;

	public boolean isGeneratingTestDataFirstChild() {
		return isGeneratingTestDataFirstChild;
	}

	public void setGeneratingTestDataFirstChild(boolean generatingTestDataFirstChild) {
		isGeneratingTestDataFirstChild = generatingTestDataFirstChild;
	}

	public Object getGeneratingTestData() {
		return generatingTestData;
	}

	public void setGeneratingTestData(Object generatingTestData) {
		this.generatingTestData = generatingTestData;
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
}
