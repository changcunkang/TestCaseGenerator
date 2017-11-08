package com.fico.testCaseGenerator.BOM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fico.testCaseGenerator.SimpleField;
import com.fico.testCaseGenerator.TestData;
import com.fico.testCaseGenerator.configuration.Extendtion;
import com.fico.testCaseGenerator.factory.LoadConfiguration;

public abstract class BOMGenerator {

	protected TestData rootTestData;

	protected LoadConfiguration loadconf;

	protected Map<String,Extendtion> configurationMap;

	public BOMGenerator(){
		List<Extendtion> list = LoadConfiguration.readExcelData("C:\\projects\\testCaseGenerator\\eclipse_work_space\\TestCaseGeneratorWeb\\BOM\\cafs\\test.xls");
		configurationMap = new HashMap<String, Extendtion>();
		for(int i=0;i<list.size();i++){

			if( list.get(i)!=null && list.get(i).getRealPath()!=null ){
				configurationMap.put(list.get(i).getRealPath(), list.get(i));
			}
		}
	}

	private Map<String, TestData> pathTestDataMap;
	
	private Map<String, SimpleField> pathSimpleFieldMap = new HashMap<String, SimpleField>();
	
	private Map<String, Boolean> testDataDependencyMap = new HashMap<String, Boolean>();
	
	private Map<String, Boolean> simpleFieldDependencyMap = new HashMap<String, Boolean>();
	
	protected List<SimpleField> dependencySimpleFieldList = new ArrayList<SimpleField>();
	
	protected List<TestData> dependencyTestDataList = new ArrayList<TestData>();
	
	protected  List<SimpleField> outputSimpleFieldList = new ArrayList<SimpleField>();
	
	public  List<SimpleField> getOutputSimpleFieldList() {
		return outputSimpleFieldList;
	}


	public List<TestData> getDependencyTestDataList() {
		return dependencyTestDataList;
	}

	public void setDependencyTestDataList(List<TestData> dependencyTestDataList) {
		this.dependencyTestDataList = dependencyTestDataList;
	}

	public Map<String, Boolean> getTestDataDependencyMap() {
		return testDataDependencyMap;
	}

	public void setTestDataDependencyMap(Map<String, Boolean> testDataDependencyMap) {
		this.testDataDependencyMap = testDataDependencyMap;
	}

	public Map<String, Boolean> getSimpleFieldDependencyMap() {
		return simpleFieldDependencyMap;
	}

	public void setSimpleFieldDependencyMap(
			Map<String, Boolean> simpleFieldDependencyMap) {
		this.simpleFieldDependencyMap = simpleFieldDependencyMap;
	}

	public Map<String, SimpleField> getPathSimpleFieldMap() {
		return pathSimpleFieldMap;
	}

	public void setPathSimpleFieldMap(Map<String, SimpleField> pathSimpleFieldMap) {
		this.pathSimpleFieldMap = pathSimpleFieldMap;
	}

	public List<SimpleField> getDependencySimpleFieldList() {
		return dependencySimpleFieldList;
	}

	public void setDependencySimpleFieldList(List<SimpleField> dependencySimpleFieldList) {
		this.dependencySimpleFieldList = dependencySimpleFieldList;
	}

	public Map<String, TestData> getPathTestDataMap() {
		
		if(pathTestDataMap == null){
			pathTestDataMap = new HashMap<String, TestData>();
		}
		
		return pathTestDataMap;
	}

	public void setPathTestDataMap(Map<String, TestData> pathTestDataMap) {
		this.pathTestDataMap = pathTestDataMap;
	}
	
	public TestData buildTestDataStructor(){
		this.rootTestData = this.getRootTestData();

		this.recusiveBuildTestData(this.rootTestData);

		return this.rootTestData;
	}

	public abstract TestData getRootTestData();

	public TestData recusiveBuildTestData(TestData testData){

		constructDependency( testData );

		List<TestData> customFieldList = testData.getCustomFieldList();

		for(TestData childTestData : customFieldList){
			recusiveBuildTestData( childTestData );
		}

		return testData;
	}

	public void constructDependency(TestData testData){

		Extendtion testDataExtendtion = this.configurationMap.get(testData.getPath());

		if( testDataExtendtion != null){
			testData.setExtendtion(testDataExtendtion);

			this.getPathTestDataMap().put(testData.getPath(), testData);

			if( testData.getExtendtion() != null && testData.getExtendtion().getDependency() != null && testData.getExtendtion().getDependency().getParentPath() != null ){
				this.getTestDataDependencyMap().put(testData.getExtendtion().getDependency().getParentPath(), false);

				this.getDependencyTestDataList().add(testData);
			}
		}

		for(SimpleField simpleField : testData.getSimpleFieldList()){
			Extendtion simpleFieldExtendtion = this.configurationMap.get( simpleField.getPath() );

			if(simpleFieldExtendtion != null){
				simpleField.setExtendtion(simpleFieldExtendtion);
			}

			this.getPathSimpleFieldMap().put(simpleField.getPath(), simpleField);

			if(simpleField.getExtendtion()!=null && simpleField.getExtendtion().getRestriction() != null && simpleField.getExtendtion().getRestriction().getTransfersType() == 2){
				this.getOutputSimpleFieldList().add(simpleField);
			}

			if( simpleField.getExtendtion() != null && simpleField.getExtendtion().getDependency() != null && simpleField.getExtendtion().getDependency().getParentPath() != null ){
				this.getSimpleFieldDependencyMap().put(simpleField.getExtendtion().getDependency().getParentPath(), false);

				this.getDependencySimpleFieldList().add(simpleField);
			}
		}
	}
}
