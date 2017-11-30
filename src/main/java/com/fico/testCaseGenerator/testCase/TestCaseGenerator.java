package com.fico.testCaseGenerator.testCase;

import java.text.SimpleDateFormat;
import java.util.*;

import com.fico.testCaseGenerator.data.AbstractTestData;
import com.fico.testCaseGenerator.CustomFunctionFactory.CustomFunctionFactory;
import com.fico.testCaseGenerator.data.SimpleField;
import com.fico.testCaseGenerator.data.TestData;
import com.fico.testCaseGenerator.BOM.BOMGenerator;
import com.fico.testCaseGenerator.data.configuration.Item;
import com.fico.testCaseGenerator.expression.TestCaseExpression;
import com.fico.testCaseGenerator.util.TestCaseUtils;

public abstract class TestCaseGenerator {
	
	protected BOMGenerator bomGenerator = null;

	protected Map<String, Integer> simpleFieldDependencyLocalPathCounter = null;

	protected CustomFunctionFactory customFunctionFactory = null;

	private TestCaseExpression testCaseExpression = null;

	protected Set<TestData> unGeneratedSlaveTestDataList = new HashSet<TestData>();

	protected Set<SimpleField> unGeneratedSlaveSimpleFieldList = new HashSet<SimpleField>();

	/**
	 * 构造函数
	 * @param bomGenerator
	 */
	public TestCaseGenerator(BOMGenerator bomGenerator){

		this.bomGenerator = bomGenerator;

		this.customFunctionFactory = new CustomFunctionFactory();

		this.testCaseExpression = new TestCaseExpression(this.bomGenerator);
	}

	/**
	 * 主程序对外主入口
	 * @return
	 */
	public Object generateTestCase(){

		clearSimpleFieldDependencyPathCounter();

		TestData rootTestData = generateRootTestData();

		if(rootTestData.getCustomFieldList() != null){
			for(TestData testData : rootTestData.getCustomFieldList()){
				generateAllTestCaseListForOneTestData(testData);
			}
		}

		handleLocalDependency();

		return rootTestData.getTestCase().get(0);
	}

	private TestData generateRootTestData(){

		List<Object> instanceElementList = new ArrayList<Object>();

		TestData rootTestData = getRootTestData();

		Object rootTestCase = this.generateRootTestCaseData( rootTestData );

		instanceElementList.add( rootTestCase );

		rootTestData.setTestCase(instanceElementList);

		rootTestData.setGenerateTestCaseFinish(true);
		for(SimpleField simpleField : rootTestData.getTmpGeneratedSimpleFieldSet()){
			simpleField.setGenerateTestCaseFinish(true);
		}


		return rootTestData;
	}

	/**
	 * @param parentTestCaseElement 父亲已经生成的测试案例的实例
	 * @param testData 要生成的testData
	 * @return
	 */
	private void getInstanceNumberAndGenerateAttr(Object parentTestCaseElement, TestData testData){

		Object intVal = this.testCaseExpression.parse( testData.getExtendtion().getRestriction() );

		Integer instanceSize = null;

		if(intVal == null){
			instanceSize = 0;
		}
		else{
			instanceSize = new Double( intVal.toString() ).intValue();
		}

		generateAttr(parentTestCaseElement, instanceSize, testData);
	}

	/**
	 * 生成简单属性
	 * @param parentTestCaseElement
	 * @param instanceSize
	 * @param testData
	 * @return
	 */
	protected void generateAttr(Object parentTestCaseElement, int instanceSize, TestData testData){

		for(int i=0; i<instanceSize; i++){

			Object newIns = this.createEmptyTestCaseInstance(testData);

			appendChildTestCaseToParentTestCase( parentTestCaseElement, newIns, testData );

			testData.setGeneratingTestData(newIns);
			testData.getTestCase().add(newIns);

			if(i == 0){
				testData.setGeneratingTestDataFirstChild(true);
			}
			else{
				testData.setGeneratingTestDataFirstChild(false);
			}

			generateAllSimpleFeildTestCaseValueForOneTestCaseInstance(newIns, testData);
		}
	}

	public Object generateAllSimpleFeildTestCaseValueForOneTestCaseInstance(Object newIns, TestData testData){
		for( SimpleField simpleField : testData.getSimpleFieldList() ){

			if(simpleField.getExtendtion() != null){

				generateSingleTestCaseAttributeValue(newIns, simpleField);
			}
		}
		return newIns;
	}

	public void generateSingleTestCaseAttributeValue( Object newInstance,  SimpleField simpleField){

		if(simpleField.getExtendtion() != null && simpleField.getExtendtion().getRestriction() != null){

			if(this.isAllRelativeElementReady(simpleField)){


				if(simpleField.getName().contains("amortizationPri")){
					String a = "";
				}


				Object expValue = this.testCaseExpression.parse(simpleField.getExtendtion().getRestriction());

				this.setTestCaseElementValue(simpleField, newInstance, simpleField.getName(), expValue);

			}
			else{
				recordAbstractTestDataPosition( simpleField );
				this.unGeneratedSlaveSimpleFieldList.add(simpleField);
			}
		}
	}

	private void recordAbstractTestDataPosition(AbstractTestData absTestData){
		String[] allRelativedPath = absTestData.getRelativePathArr();
		Integer[] tmpPathPosition = new Integer[allRelativedPath.length];

		for( int i=0; i<allRelativedPath.length; i++ ){
			String path = allRelativedPath[i];

			if(this.bomGenerator.pathIsSimpleField(path)){
				SimpleField masterSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(path);
				TestData testData = masterSimpleField.getTestData();
				tmpPathPosition[i] = testData.getTestCase().size()-1;
			}
		}

		absTestData.getPositionRecord().add( tmpPathPosition );

	}

	private void setTestCaseElementValue(SimpleField simpleField , Object newInstantceArrElement, String attributeName, Object attributeValue){

		setTestCaseInstanceSimpleFieldValue(newInstantceArrElement,attributeName, attributeValue);

		simpleField.getTestData().getTmpGeneratedSimpleFieldSet().add(simpleField);

		addSimpleListTestCaseValue(simpleField, attributeValue);
	}

	private void addSimpleListTestCaseValue(SimpleField simpleField, Object eleValue){
		simpleField.getTestCase().add(eleValue);
	}

	/**
	 * 生成一个TestData下所有案例数据，包括主从
	 * @param testData
	 * @return
	 */

	public void generateAllTestCaseListForOneTestData(TestData testData){

		generateTestDataInstance( testData );

		if(testData.getCustomFieldList() != null){
			for(TestData tmpTestData : testData.getCustomFieldList()){
				generateAllTestCaseListForOneTestData(tmpTestData);
			}
		}
	}

	/**
	 * 生成一个TestData下所有案例数据，不包括主从
	 * @param testData
	 * @return
	 */

	public void generateTestDataInstance( TestData testData) {

		List paretnTestCaseElementList = testData.getParentTestData().getTestCase();

		if(testData.getExtendtion() != null && testData.getExtendtion().getRestriction() != null){

			if( isAllRelativeElementReady( testData ) ){

				for( Object parentTestCaseElement :  paretnTestCaseElementList){

					getInstanceNumberAndGenerateAttr(parentTestCaseElement, testData);

				}

				testData.setGenerateTestCaseFinish(true);
				for(SimpleField simpleField : testData.getTmpGeneratedSimpleFieldSet()){
					simpleField.setGenerateTestCaseFinish( true );
				}
				testData.setTmpGeneratedSimpleFieldSet(new HashSet<SimpleField>());

			}else{
				this.unGeneratedSlaveTestDataList.add( testData );
				this.recordAbstractTestDataPosition( testData );
			}
		}
	}

	private boolean isAllRelativeElementReady(AbstractTestData abstractTestData){
		boolean isAllRelativeElementReady = true;

		if(abstractTestData.getExtendtion() == null || abstractTestData.getExtendtion().getRestriction() == null){
			return isAllRelativeElementReady;
		}

		for(Item item : abstractTestData.getExtendtion().getRestriction().getItem()){
			if(  !this.testCaseExpression.isAllElementReady( item.getMinExpression(), abstractTestData.getPath())
					|| !this.testCaseExpression.isAllElementReady( item.getMaxExpression(), abstractTestData.getPath())){
				isAllRelativeElementReady = false;
			}
		}
		return isAllRelativeElementReady;
	}

	private void handleLocalDependency(){
		boolean testDataLoopFlag = false;
		boolean simpleFieldLoopFlag = false;

		boolean isTestDataListReduced = true;
		boolean isSimpleFieldReduced = true;

		do{
			//testData
			if(this.unGeneratedSlaveTestDataList != null && this.unGeneratedSlaveTestDataList.size() > 0){

				testDataLoopFlag = true;

				while( testDataLoopFlag ){

					int unConstructedTestDataListSize = unGeneratedSlaveTestDataList.size();

					for(TestData unConsTestCaseData : unGeneratedSlaveTestDataList){
						if(this.isAllRelativeElementReady( unConsTestCaseData )){
							generateAllTestCaseListForOneTestData(unConsTestCaseData);
							unGeneratedSlaveTestDataList.remove(unConsTestCaseData);
						}
					}
					if(unGeneratedSlaveTestDataList.size() == 0){
						testDataLoopFlag = false;
						break;
					}else if(unConstructedTestDataListSize == unGeneratedSlaveTestDataList.size()){
						isTestDataListReduced = false;
					}
				}
			}

			//simpleField
			if(this.unGeneratedSlaveSimpleFieldList != null && this.unGeneratedSlaveSimpleFieldList.size() > 0 ){

				simpleFieldLoopFlag = true;

				List<SimpleField> tmpList = new ArrayList<SimpleField>();

				tmpList.addAll( unGeneratedSlaveSimpleFieldList );

				while( simpleFieldLoopFlag ){

					int unConstructedSimpleListSize = unGeneratedSlaveSimpleFieldList.size();

					for(int i=0; i<tmpList.size(); i ++){

						SimpleField unConsTestCaseData = tmpList.get(i);

						if( this.isAllRelativeElementReady(unConsTestCaseData) ){

							for(Object testCaseIns : unConsTestCaseData.getTestData().getTestCase() ){
								generateSingleTestCaseAttributeValue(testCaseIns,unConsTestCaseData);
							}
							unConsTestCaseData.setGenerateTestCaseFinish(true);
							unGeneratedSlaveSimpleFieldList.remove(unConsTestCaseData);
							tmpList.remove(unConsTestCaseData);
						}
					}
					if(unGeneratedSlaveSimpleFieldList.size() == 0){
						simpleFieldLoopFlag = false;
						break;
					}else if(unConstructedSimpleListSize == unGeneratedSlaveSimpleFieldList.size()){
						isSimpleFieldReduced = false;
					}
				}
			}

			//异常
			if( !isTestDataListReduced && !isSimpleFieldReduced ){
				System.out.println("None TestData nor SimpleField is reduced");
				break;
			}

		}while (!testDataLoopFlag && simpleFieldLoopFlag );
	}

	private void clearSimpleFieldDependencyPathCounter(){

		//重置状态
		this.clearTestCaseFinishFlag();
		
		if( simpleFieldDependencyLocalPathCounter == null ){
			simpleFieldDependencyLocalPathCounter = new HashMap<String, Integer>();
		}
		
		for( SimpleField simpleField : this.bomGenerator.getDependencySimpleFieldList() ){
			simpleFieldDependencyLocalPathCounter.put(simpleField.getPath(), 0);
		}

		this.unGeneratedSlaveTestDataList = new HashSet<TestData>();

		this.unGeneratedSlaveSimpleFieldList = new HashSet<SimpleField>();
	}

	public void clearTestCaseFinishFlag(){
		if( this.bomGenerator.getRootTestData() != null ){
			recursiveClearTestCaseFinishFlag( this.bomGenerator.getRootTestData(), false );
		}
	}

	private void recursiveClearTestCaseFinishFlag(TestData testData, boolean flag){

		testData.setGenerateTestCaseFinish(flag);
		testData.setGeneratingTestDataFirstChild(flag);
		testData.setGeneratingTestData(null);

		for(SimpleField sf : testData.getSimpleFieldList()){
			sf.setGenerateTestCaseFinish(flag);
			sf.setDependencySimpleFieldPosition(new ArrayList<Integer>());
		}

		for(TestData childTestData : testData.getCustomFieldList()){
			recursiveClearTestCaseFinishFlag( childTestData,  flag);
		}
	}


	public TestData getRootTestData(){
		return this.bomGenerator.getRootTestData();
	}

	protected abstract Object TestCaseInstanceCopy(Object testCaseInstance);

	public abstract Object generateRootTestCaseData( TestData testData );

	protected abstract void appendChildTestCaseToParentTestCase(Object parentIns, Object childIns, TestData childTestData);

	protected abstract void setTestCaseInstanceSimpleFieldValue(Object newTestCaseInstance, String attributeName, Object attributeValue);

	protected abstract Object createEmptyTestCaseInstance(TestData testData);

	public Set<TestData> getUnGeneratedSlaveTestDataList() {
		return unGeneratedSlaveTestDataList;
	}

	public void setUnGeneratedSlaveTestDataList(Set<TestData> unGeneratedSlaveTestDataList) {
		this.unGeneratedSlaveTestDataList = unGeneratedSlaveTestDataList;
	}

	public Set<SimpleField> getUnGeneratedSlaveSimpleFieldList() {
		return unGeneratedSlaveSimpleFieldList;
	}

	public void setUnGeneratedSlaveSimpleFieldList(HashSet<SimpleField> unGeneratedSlaveSimpleFieldList) {
		this.unGeneratedSlaveSimpleFieldList = unGeneratedSlaveSimpleFieldList;
	}

	public Map<String, TestData> getPathTestDataMap(){
		return this.bomGenerator.getPathTestDataMap();
	}

	private  SimpleDateFormat	dateTypeDataFormat = new SimpleDateFormat(TestCaseUtils.DATE_FORMAT);

	private  SimpleDateFormat	dateTimeTypeDataFormat = new SimpleDateFormat(TestCaseUtils.DATE_TIME_FORMAT);

	public  SimpleDateFormat getDateTypeDataFormat(){
		return dateTypeDataFormat;
	}

	public  SimpleDateFormat getDateTimeTypeDataFormat(){
		return dateTimeTypeDataFormat;
	}

}
