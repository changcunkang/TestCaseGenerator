package com.fico.testCaseGenerator.testCase;

import java.text.SimpleDateFormat;
import java.util.*;

import com.fico.testCaseGenerator.data.AbstractTestData;
import com.fico.testCaseGenerator.CustomFunctionFactory.CustomFunctionFactory;
import com.fico.testCaseGenerator.data.SimpleField;
import com.fico.testCaseGenerator.data.TestData;
import com.fico.testCaseGenerator.BOM.BOMGenerator;
import com.fico.testCaseGenerator.data.configuration.Item;
import com.fico.testCaseGenerator.data.configuration.Restriction;
import com.fico.testCaseGenerator.expression.TestCaseExpression;
import com.fico.testCaseGenerator.util.RandomFactory;
import com.fico.testCaseGenerator.util.TestCaseUtils;

public abstract class TestCaseGenerator {
	
	protected BOMGenerator bomGenerator;
	
	protected Map<String, Integer> simpleFieldDependencyLocalPathCounter = null;

	protected CustomFunctionFactory customFunctionFactory = null;

	public Map<String, TestData> getPathTestDataMap(){
		return this.bomGenerator.getPathTestDataMap();
	}

	private TestCaseExpression testCaseExpression = null;

	protected List<TestData> unGeneratedSlaveTestDataList = new ArrayList<TestData>();

	protected List<SimpleField> unGeneratedSlaveSimpleFieldList = new ArrayList<SimpleField>();

	public TestCaseGenerator(BOMGenerator bomGenerator){

		this.bomGenerator = bomGenerator;

		this.customFunctionFactory = new CustomFunctionFactory();

		this.testCaseExpression = new TestCaseExpression(this.bomGenerator);
	}

	/**
	 * 入口主程序
	 * @return
	 */
	public Object generateTestCase(){

		clearSimpleFieldDependencyPathCounter();

		List<Object> instanceElementList = new ArrayList<Object>();

		TestData rootTestData = getRootTestData();

		Object rootTestCase = this.generateRootTestCaseData( rootTestData );

		instanceElementList.add( rootTestCase );

		rootTestData.setTestCase(instanceElementList);

		rootTestData.setGenerateTestCaseFinish(true);

		if(rootTestData.getCustomFieldList() != null){
			for(TestData testData : rootTestData.getCustomFieldList()){
				generateAllTestCaseListForOneTestData(testData);
			}
		}

		generateNotCreatedSlaveTestCase();

		generateLocalDependencySimpleField();

		return rootTestCase;
	}



	/**
	 * @param parentTestCaseElement 父亲已经生成的测试案例的实例
	 * @param testData 要生成的testData
	 * @return
	 */
	private List getInstanceNumberAndGenerateAttr(Object parentTestCaseElement, TestData testData){

		Integer instanceSize = new Double( this.testCaseExpression.parse( testData.getExtendtion().getRestriction() ).toString() ).intValue();

		return generateAttr(parentTestCaseElement, instanceSize, testData);
	}


	/**
	 * 处理主从关系
	 * @param masterSimpleField
	 * @param slaveSimpleField
	 * @param newInstantceArrElement
	 */
//	public void handlSimpleFieldDependency(SimpleField masterSimpleField, SimpleField slaveSimpleField, Object newInstantceArrElement){
//
//		List masterSimpleFieldTestDataList = masterSimpleField.getValueList();
//
//		int slaveSimpleFieldPosition = slaveSimpleField.getValueList().size();
//
//		int dependencyType = slaveSimpleField.getExtendtion().getDependency().getType();
//
//		String attributeName = slaveSimpleField.getName();
//
//		if( slaveSimpleFieldPosition <= masterSimpleFieldTestDataList.size()-1 && masterSimpleFieldTestDataList.size()>0 ){
//
//			int masterTestCasePos = slaveSimpleFieldPosition == 0 ? 0 : slaveSimpleFieldPosition -1;
//
//			Object targetValue = masterSimpleFieldTestDataList.get( slaveSimpleFieldPosition );
//
//			if( dependencyType == Dependency.TYPE_NULL && targetValue != null){
//				createSimpleField(newInstantceArrElement, slaveSimpleField);
//			}
//			else if(dependencyType == Dependency.TYPE_EQUICALENCE){
//				setTestCaseElementValue(slaveSimpleField, newInstantceArrElement, attributeName, targetValue);
//			}
//		}
//		if(dependencyType == Dependency.TYPE_FUNCTIONNAME){
//
//			String functionName = slaveSimpleField.getExtendtion().getDependency().getFunctionName();
//
//			if( masterSimpleField != null ){
//
//				try {
//					String targetValue = customFunctionFactory.invokeCustomFunction(functionName, masterSimpleField, slaveSimpleField);
//
//					setTestCaseElementValue(slaveSimpleField, newInstantceArrElement, attributeName, targetValue);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	/**
	 * 生成简单属性
	 * @param parentTestCaseElement
	 * @param instanceSize
	 * @param testData
	 * @return
	 */
	protected List generateAttr(Object parentTestCaseElement, int instanceSize, TestData testData){

		List newInstantceArr = new ArrayList();

		for(int i=0; i<instanceSize; i++){

			Object newIns = this.createEmptyTestCaseInstance(testData);

			appendChildTestCaseToParentTestCase( parentTestCaseElement, newIns, testData );

			newInstantceArr.add( generateAllSimpleFeildTestCaseValueForOneTestCaseInstance(newIns, testData) );
		}

		return newInstantceArr;
	}

	protected Object generateAllSimpleFeildTestCaseValueForOneTestCaseInstance(Object newIns, TestData testData){

		for( SimpleField simpleField : testData.getSimpleFieldList() ){

			if(simpleField.getExtendtion() != null){
				generateSingleTestCaseAttributeValue(newIns, simpleField);
			}
		}

		return newIns;
	}


	public void generateSingleTestCaseAttributeValue( Object newInstance,  SimpleField simpleField){

		if(simpleField.getName().contains("curBalance")){
			String a = "";
		}

		if(simpleField.getExtendtion() != null && simpleField.getExtendtion().getRestriction() != null){

			if(this.isAllRelativeElementReady(simpleField)){
				Object expValue = this.testCaseExpression.parse(simpleField.getExtendtion().getRestriction());

				this.setTestCaseElementValue(simpleField, newInstance, simpleField.getName(), expValue);
			}
			else{
				this.unGeneratedSlaveSimpleFieldList.add(simpleField);
			}

//			//主已经建立好
//			if( simpleField.getExtendtion().getDependency() != null &&  this.isMasterAvailable(simpleField) ){
//
//				SimpleField masterField = this.bomGenerator.getPathSimpleFieldMap().get( simpleField.getExtendtion().getDependency().getParentPath() );
//
//				handlSimpleFieldDependency(masterField, simpleField, newInstanceArr);
//
//			}
//			else if( simpleField.getExtendtion().getDependency() == null && simpleField.getExtendtion().getRestriction() != null){
//				createSimpleField(newInstanceArr, simpleField);
//			}
		}
	}
//
//	private Object generateRestrictionExpressionValue(Restriction restriction){
//
//		double nullPercent = restriction.getNullPercentage();
//
//		double nullPercentHit = RandomFactory.random();
//
//		if( nullPercentHit >= nullPercent ){
//
//			double notNullPercentHit = RandomFactory.random();
//
//			double minPercent = 0;
//
//			double maxPercent = 0;
//
//			for(int i=0; i<restriction.getItem().size(); i++) {
//
//				minPercent = maxPercent;
//
//				maxPercent += restriction.getItem().get(i).getPercentage();
//
//				//区间的边界值界定
//				if(notNullPercentHit> minPercent && notNullPercentHit<= maxPercent){
//
//					String minExp = restriction.getItem().get(i).getMinExpression().replace(" ", "").trim();
//
//					String maxExp = restriction.getItem().get(i).getMaxExpression().replace(" ", "").trim();
//
//					if(minExp.equals(maxExp)){
//
//						Object expReturnVal = this.testCaseExpression.parse( minExp );
//					}
//					else{
//						Object minExpRtnVal = this.testCaseExpression.parse( minExp );
//
//						Object maxExpRtnVal = this.testCaseExpression.parse( maxExp );
//
//					}
//
//				}
//			}
//		}
//		else{
//			return null;
//		}
//
//		return null;
//	}
//
//	private void createSimpleField( Object newInstantceArrElement, SimpleField simpleField ){
//
//		Restriction restriction = simpleField.getExtendtion().getRestriction();
//
//		String restrictionType = restriction.getType();
//
//		double nullPercent = restriction.getNullPercentage();
//
//		double nullPercentHit = RandomFactory.random();
//
//		if( nullPercentHit > nullPercent ){
//
//			double notNullPercentHit = RandomFactory.random();
//
//			double minPercent = 0;
//
//			double maxPercent = 0;
//
//			for(int i=0; i<restriction.getItem().size(); i++){
//
//				minPercent = maxPercent;
//
//				maxPercent += restriction.getItem().get(i).getPercentage();
//
//				String attributeName = simpleField.getName();
//
//				if(notNullPercentHit> minPercent && notNullPercentHit<= maxPercent){
//					//generateValue
//					if( Restriction.TYPE_ENMURATION.equals(restrictionType) ){
//
//						setTestCaseElementValue(simpleField,newInstantceArrElement, attributeName, restriction.getItem().get(i).getValue());
//					}
//					else{
//
//						String minRandomRangeStr = null;
//
//						if( i==0 ){
//							minRandomRangeStr = restriction.getMinStr();
//						}else{
//
//							minRandomRangeStr = restriction.getItem().get(i -1).getValue();
//						}
//
//						String maxRandomRangeStr = restriction.getItem().get(i).getValue();
//
//						String eleValue = null;
//
//						if( Restriction.TYPE_SECTION.equals(restrictionType) ){
//
//							try {
//								eleValue = generateSimpleAttrRandomValue( simpleField, minRandomRangeStr, maxRandomRangeStr );
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								System.out.println( "Exception occur : " + simpleField.getPath() );
//
//								e.printStackTrace();
//							}
//
//							setTestCaseElementValue(simpleField, newInstantceArrElement, attributeName, eleValue);
//
//						}
//
//						else if( Restriction.TYPE_FUNCTION.equals(restrictionType) ){
//							try {
//								String functionRtn = customFunctionFactory.invokeCustomFunction(maxRandomRangeStr, null, simpleField);
//
//								setTestCaseElementValue(simpleField, newInstantceArrElement, attributeName, functionRtn);
//
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//					}
//				}
//			}
//		}
//		else{
//			addSimpleListTestCaseValue(simpleField, null );
//		}
//	}

	private void setTestCaseElementValue(SimpleField simpleField , Object newInstantceArrElement, String attributeName, Object attributeValue){

		setTestCaseInstanceSimpleFieldValue(newInstantceArrElement,attributeName, attributeValue);

		simpleField.setGenerateTestCaseFinish(true);

		addSimpleListTestCaseValue(simpleField, attributeValue);
	}

	private void addSimpleListTestCaseValue(SimpleField simpleField, Object eleValue){

		simpleField.getTestCase().add(eleValue);

//		if(  this.bomGenerator.getSimpleFieldDependencyMap().get(simpleField.getPath()) != null ){
//			this.bomGenerator.getSimpleFieldDependencyMap().put( simpleField.getPath(), true );
//		}

		simpleField.setGenerateTestCaseFinish(true);
	}

	private  SimpleDateFormat	dateTypeDataFormat = new SimpleDateFormat(TestCaseUtils.DATE_FORMAT);
	
	private  SimpleDateFormat	dateTimeTypeDataFormat = new SimpleDateFormat(TestCaseUtils.DATE_TIME_FORMAT);
	
	public  SimpleDateFormat getDateTypeDataFormat(){
		return dateTypeDataFormat;
	}
	
	public  SimpleDateFormat getDateTimeTypeDataFormat(){
		return dateTimeTypeDataFormat;
	}
	
//	public boolean isMasterAvailable(AbstractTestData absTestData){
//
//		if(absTestData instanceof TestData){
//			TestData testData = (TestData)absTestData;
//
//			if(testData.getExtendtion()!= null && testData.getExtendtion().getDependency() != null){
//				String depPath = testData.getExtendtion().getDependency().getParentPath();
//
//				if(this.bomGenerator.getTestDataDependencyMap().get(depPath) != null && this.bomGenerator.getTestDataDependencyMap().get(depPath)){
//					return true;
//				}
//			}
//		}else if(absTestData instanceof SimpleField){
//			SimpleField simpleField = (SimpleField)absTestData;
//
//			if(simpleField.getExtendtion()!= null && simpleField.getExtendtion().getDependency() != null){
//				String depPath = simpleField.getExtendtion().getDependency().getParentPath();
//
//				if(this.bomGenerator.getSimpleFieldDependencyMap().get(depPath) != null && this.bomGenerator.getSimpleFieldDependencyMap().get(depPath)){
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
	public void clearTestCaseFinishFlag(){
		if( this.bomGenerator.getRootTestData() != null ){
			recursiveClearTestCaseFinishFlag( this.bomGenerator.getRootTestData(), false );
		}
	}
	
	private void recursiveClearTestCaseFinishFlag(TestData testData, boolean flag){
		testData.setGenerateTestCaseFinish(flag);
		
		for(SimpleField sf : testData.getSimpleFieldList()){
			sf.setGenerateTestCaseFinish(flag);
		}
		
		for(TestData childTestData : testData.getCustomFieldList()){
			recursiveClearTestCaseFinishFlag( childTestData,  flag);
		}
	}


	/**
	 * 生成一个TestData下所有案例数据，包括主从
	 * @param testData
	 * @return
	 */

	public List generateAllTestCaseListForOneTestData(TestData testData){

		List instanceElementList = generateTestDataInstance( testData );

		testData.setTestCase(instanceElementList);

		if(testData.getCustomFieldList() != null){
			for(TestData tmpTestData : testData.getCustomFieldList()){
				generateAllTestCaseListForOneTestData(tmpTestData);
			}
		}

		return instanceElementList;
	}

	/**
	 * 生成一个TestData下所有案例数据，不包括主从
	 * @param testData
	 * @return
	 */

	public List generateTestDataInstance( TestData testData) {

		List paretnTestCaseElementList = testData.getParentTestData().getTestCase();

		List rtnList = new ArrayList();

		if(testData.getExtendtion() != null && testData.getExtendtion().getRestriction() != null){

			if( isAllRelativeElementReady( testData ) ){

				Object expressionVal = null;

				for( Object parentTestCaseElement :  paretnTestCaseElementList){

					List newInstantceArr = getInstanceNumberAndGenerateAttr(parentTestCaseElement, testData);

					rtnList.addAll( newInstantceArr );
				}

				testData.setGenerateTestCaseFinish(true);

			}else{
				this.unGeneratedSlaveTestDataList.add( testData );
			}

		}

		return rtnList;
	}

	private boolean isAllRelativeElementReady(AbstractTestData abstractTestData){
		boolean isAllRelativeElementReady = true;

		if(abstractTestData.getExtendtion() == null || abstractTestData.getExtendtion().getRestriction() == null){
			return isAllRelativeElementReady;
		}

		for(Item item : abstractTestData.getExtendtion().getRestriction().getItem()){
			if( !this.testCaseExpression.isAllElementReady( item.getMinExpression())
					|| !this.testCaseExpression.isAllElementReady( item.getMaxExpression())){
				isAllRelativeElementReady = false;
			}
		}
		return isAllRelativeElementReady;
	}

	
	public void generateNotCreatedSlaveTestCase(){
		
		if(this.unGeneratedSlaveTestDataList != null){
			boolean loopFlag = true;
			
			while( loopFlag ){
				
				int unConstructedTestCaseListSize = unGeneratedSlaveTestDataList.size();
				
				for(TestData unConsTestCaseData : unGeneratedSlaveTestDataList){
					if(this.isAllRelativeElementReady( unConsTestCaseData )){
						generateAllTestCaseListForOneTestData(unConsTestCaseData);
						unGeneratedSlaveTestDataList.remove(unConsTestCaseData);
					}
				}
				if(unGeneratedSlaveTestDataList.size() == 0){
					loopFlag = false;
					break;
				}else if(unConstructedTestCaseListSize >= unGeneratedSlaveTestDataList.size()){
					System.out.println("local dead loop in generateNotCreatedSlaveTestCase");
					break;
				}
			}
		}
	}
	
	public void generateLocalDependencySimpleField(){
		if(this.unGeneratedSlaveSimpleFieldList != null){
			boolean loopFlag = true;
			
			while( loopFlag ){
				
				int unConstructedTestCaseListSize = unGeneratedSlaveSimpleFieldList.size();
				
				for(int i=0; i<unGeneratedSlaveSimpleFieldList.size(); i ++){
					
					SimpleField unConsTestCaseData = unGeneratedSlaveSimpleFieldList.get(i);
					
					if( this.isAllRelativeElementReady(unConsTestCaseData) ){
						
						for(Object testCaseIns : unConsTestCaseData.getTestData().getTestCase() ){
							generateSingleTestCaseAttributeValue(testCaseIns,unConsTestCaseData);
						}
						unGeneratedSlaveSimpleFieldList.remove(unConsTestCaseData);
					}
				}
				if(unGeneratedSlaveSimpleFieldList.size() == 0){
					loopFlag = false;
					break;
				}else if(unConstructedTestCaseListSize == unGeneratedSlaveSimpleFieldList.size()){
					System.out.println("local dead loop in generateLocalDependencySimpleField");
					break;
				}
			}
		}
	}
	
	private void clearSimpleFieldDependencyPathCounter(){
		
		clearTestCaseFinishFlag();
		
		if( simpleFieldDependencyLocalPathCounter == null ){
			simpleFieldDependencyLocalPathCounter = new HashMap<String, Integer>();
		}
		
		for( SimpleField simpleField : this.bomGenerator.getDependencySimpleFieldList() ){
			simpleFieldDependencyLocalPathCounter.put(simpleField.getPath(), 0);
		}

		this.unGeneratedSlaveTestDataList = new ArrayList<TestData>();

		this.unGeneratedSlaveSimpleFieldList = new ArrayList<SimpleField>();
	}

//	protected void manageMasterSlave(Object absTestData){
//
//		manageLocalMasterSlave( absTestData );
//	}
	
//	protected void manageLocalMasterSlave( Object absTestData ){
//
//		if(absTestData instanceof TestData){
//			TestData testData = (TestData)absTestData;
//
//			this.bomGenerator.getTestDataDependencyMap().put(testData.getPath(), true);
//
//			List<SimpleField> simpleFiledList = testData.getSimpleFieldList();
//
//			if( simpleFiledList != null){
//				for(SimpleField simpleField : simpleFiledList){
//
//					if(simpleField.isGenerateTestCaseFinish() &&  this.bomGenerator.getSimpleFieldDependencyMap().get(simpleField.getPath()) != null){
//						this.bomGenerator.getSimpleFieldDependencyMap().put( simpleField.getPath(), true );
//					}
//				}
//			}
//		}
//	}

	public TestData getRootTestData(){
		return this.bomGenerator.getRootTestData();
	}

	protected abstract Object TestCaseInstanceCopy(Object testCaseInstance);

	public abstract Object generateRootTestCaseData( TestData testData );

	protected abstract void appendChildTestCaseToParentTestCase(Object parentIns, Object childIns, TestData childTestData);

	protected abstract void setTestCaseInstanceSimpleFieldValue(Object newTestCaseInstance, String attributeName, Object attributeValue);

	protected abstract Object createEmptyTestCaseInstance(TestData testData);

	public List<TestData> getUnGeneratedSlaveTestDataList() {
		return unGeneratedSlaveTestDataList;
	}

	public void setUnGeneratedSlaveTestDataList(List<TestData> unGeneratedSlaveTestDataList) {
		this.unGeneratedSlaveTestDataList = unGeneratedSlaveTestDataList;
	}

	public List<SimpleField> getUnGeneratedSlaveSimpleFieldList() {
		return unGeneratedSlaveSimpleFieldList;
	}

	public void setUnGeneratedSlaveSimpleFieldList(List<SimpleField> unGeneratedSlaveSimpleFieldList) {
		this.unGeneratedSlaveSimpleFieldList = unGeneratedSlaveSimpleFieldList;
	}

}
