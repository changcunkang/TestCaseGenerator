package com.fico.testCaseGenerator.testCase;

import java.text.SimpleDateFormat;
import java.util.*;

import com.fico.testCaseGenerator.data.AbstractTestData;
import com.fico.testCaseGenerator.CustomFunctionFactory.CustomFunctionFactory;
import com.fico.testCaseGenerator.data.SimpleField;
import com.fico.testCaseGenerator.data.TestData;
import com.fico.testCaseGenerator.BOM.BOMGenerator;
import com.fico.testCaseGenerator.configuration.Dependency;
import com.fico.testCaseGenerator.configuration.Item;
import com.fico.testCaseGenerator.configuration.Restriction;
import com.fico.testCaseGenerator.util.RandomFactory;



public abstract class TestCaseGenerator {

	protected int testCaseNumber;
	
	protected List<TestData> testCaseList;
	
	protected BOMGenerator bomGenerator;
	
	protected Map<String, Integer> simpleFieldDependencyLocalPathCounter = null;

	protected CustomFunctionFactory customFunctionFactory = null;

	public Map<String, TestData> getPathTestDataMap(){
		return this.bomGenerator.getPathTestDataMap();
	}


	public TestCaseGenerator(BOMGenerator bomGenerator){
		this.bomGenerator = bomGenerator;
		customFunctionFactory = new CustomFunctionFactory();
	}
	
	public TestData getRootTestData(){
		return this.bomGenerator.getRootTestData();
	}

	public List generateTestDataInstance( TestData testData) {

		List paretnTestCaseElementList = testData.getParentTestData().getTestCase();

		List rtnList = new ArrayList();

		if(testData.getExtendtion() != null){
			//无主从关系
			if(testData.getExtendtion().getDependency() == null ){

				if(testData.getExtendtion().getRestriction() != null && testData.getExtendtion().getRestriction().getTransfersType() != 2){

					for( Object parentTestCaseElement :  paretnTestCaseElementList){

						List newInstantceArr = new ArrayList();

						newInstantceArr = getInstanceNumberAndGenerateAttr(parentTestCaseElement, testData);

						rtnList.addAll( newInstantceArr );
					}

					testData.setGenerateTestCaseFinish(true);

					//Element的主从map
				}
			}
			else{
				if( this.isMasterAvailable(testData) ){
					String dependencyPath = testData.getExtendtion().getDependency().getParentPath();

					TestData dependencyTestData = this.getPathTestDataMap().get(dependencyPath);

					for( Object parentTestCaseElement :  paretnTestCaseElementList){

						List newInstantceArr = new ArrayList();

						newInstantceArr = handleTestCaseDependency(dependencyTestData, testData, parentTestCaseElement);

						rtnList.addAll( newInstantceArr );
					}

					testData.setGenerateTestCaseFinish(true);
				}
				else{

				}
			}
		}

		return rtnList;
	}

	private List getInstanceNumberAndGenerateAttr(Object parentTestCaseElement, TestData testData){

		int instanceSize = getTestCaseInstanceNumber( testData );

		return generateAttr(parentTestCaseElement, instanceSize, testData);
	}


	public void handlSimpleFieldDependency(SimpleField masterSimpleField, SimpleField slaveSimpleField, Object newInstantceArrElement){

		List masterSimpleFieldTestDataList = masterSimpleField.getValueList();

		int slaveSimpleFieldPosition = slaveSimpleField.getValueList().size();

		int dependencyType = slaveSimpleField.getExtendtion().getDependency().getType();

		String attributeName = slaveSimpleField.getName();

		if( slaveSimpleFieldPosition <= masterSimpleFieldTestDataList.size()-1 && masterSimpleFieldTestDataList.size()>0 ){

			int masterTestCasePos = slaveSimpleFieldPosition == 0 ? 0 : slaveSimpleFieldPosition -1;

			Object targetValue = masterSimpleFieldTestDataList.get( slaveSimpleFieldPosition );

			if( dependencyType == Dependency.TYPE_NULL && targetValue != null){
				createSimpleField(newInstantceArrElement, slaveSimpleField);
			}
			else if(dependencyType == Dependency.TYPE_EQUICALENCE){
				setTestCaseElementValue(slaveSimpleField, newInstantceArrElement, attributeName, targetValue);
			}
		}
		if(dependencyType == Dependency.TYPE_FUNCTIONNAME){

			String functionName = slaveSimpleField.getExtendtion().getDependency().getFunctionName();

			if( masterSimpleField != null ){

				try {
					String targetValue = customFunctionFactory.invokeCustomFunction(functionName, masterSimpleField, slaveSimpleField);

					setTestCaseElementValue(slaveSimpleField, newInstantceArrElement, attributeName, targetValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected List generateAttr(Object parentTestCaseElement, int instanceSize, TestData testData){

		List newInstantceArr = new ArrayList();

		for(int i=0; i<instanceSize; i++){

			Object newIns = this.createEmptyTestCaseInstance(testData);

			appendChildTestCaseToParentTestCase( parentTestCaseElement, newIns, testData );

			newInstantceArr.add( generateSingleTestCaseInstance(newIns, testData) );
		}

		return newInstantceArr;
	}

	protected abstract void appendChildTestCaseToParentTestCase(Object parentIns, Object childIns, TestData childTestData);

	protected Object generateSingleTestCaseInstance(Object newIns,  TestData testData){

		for( SimpleField simpleField : testData.getSimpleFieldList() ){
			generateSingleTestCaseAttributeValue(newIns, simpleField);
		}

		return newIns;
	}


	public void generateSingleTestCaseAttributeValue( Object newInstantceArr,  SimpleField simpleField){

		if(simpleField.getExtendtion() != null){

			//主已经建立好
			if( simpleField.getExtendtion().getDependency() != null &&  this.isMasterAvailable(simpleField) ){
				//if( simpleField.getExtendtion().getDependency().getType() == Dependency.TYPE_NULL ){
				//----------------------------------------
				//}
				SimpleField masterField = this.bomGenerator.getPathSimpleFieldMap().get( simpleField.getExtendtion().getDependency().getParentPath() );

				handlSimpleFieldDependency(masterField, simpleField, newInstantceArr);

			}
			else if( simpleField.getExtendtion().getDependency() == null && simpleField.getExtendtion().getRestriction() != null){
				createSimpleField(newInstantceArr, simpleField);
			}
		}
	}

	private void createSimpleField( Object newInstantceArrElement, SimpleField simpleField ){

		Restriction restriction = simpleField.getExtendtion().getRestriction();

		String restrictionType = restriction.getType();

		double nullPercent = restriction.getNullPercentage();

		double nullPercentHit = RandomFactory.random();

		if( nullPercentHit > nullPercent ){

			double notNullPercentHit = RandomFactory.random();

			double minPercent = 0;

			double maxPercent = 0;

			for(int i=0; i<restriction.getItem().size(); i++){

				minPercent = maxPercent;

				maxPercent += restriction.getItem().get(i).getPercentage();

				String attributeName = simpleField.getName();

				if(notNullPercentHit> minPercent && notNullPercentHit<= maxPercent){
					//generateValue
					if( Restriction.TYPE_ENMURATION.equals(restrictionType) ){

						setTestCaseElementValue(simpleField,newInstantceArrElement, attributeName, restriction.getItem().get(i).getValue());
					}
					else{

						String minRandomRangeStr = null;

						if( i==0 ){
							minRandomRangeStr = restriction.getMinStr();
						}else{

							minRandomRangeStr = restriction.getItem().get(i -1).getValue();
						}

						String maxRandomRangeStr = restriction.getItem().get(i).getValue();

						String eleValue = null;

						if( Restriction.TYPE_SECTION.equals(restrictionType) ){

							try {
								eleValue = generateSimpleAttrRandomValue( simpleField, minRandomRangeStr, maxRandomRangeStr );
							} catch (Exception e) {
								// TODO Auto-generated catch block
								System.out.println( "Exception occur : " + simpleField.getPath() );

								e.printStackTrace();
							}

							setTestCaseElementValue(simpleField, newInstantceArrElement, attributeName, eleValue);

						}

						else if( Restriction.TYPE_FUNCTION.equals(restrictionType) ){
							try {
								String functionRtn = customFunctionFactory.invokeCustomFunction(maxRandomRangeStr, null, simpleField);

								setTestCaseElementValue(simpleField, newInstantceArrElement, attributeName, functionRtn);

							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		else{
			addSimpleListTestCaseValue(simpleField, null );
		}
	}

	protected abstract void setTestCaseInstanceSimpleFieldValue(Object newTestCaseInstance, String attributeName, Object attributeValue);

	private void setTestCaseElementValue(SimpleField simpleField , Object newInstantceArrElement, String attributeName, Object attributeValue){

		setTestCaseInstanceSimpleFieldValue(newInstantceArrElement,attributeName, attributeValue);

		addSimpleListTestCaseValue(simpleField, attributeValue);
	}

	private void addSimpleListTestCaseValue(SimpleField simpleField, Object eleValue){

		simpleField.getValueList().add(eleValue);

		if(  this.bomGenerator.getSimpleFieldDependencyMap().get(simpleField.getPath()) != null ){
			this.bomGenerator.getSimpleFieldDependencyMap().put( simpleField.getPath(), true );
		}

		simpleField.setGenerateTestCaseFinish(true);
	}

	private String generateSimpleAttrRandomValue( SimpleField simpleField, String min, String max ) throws Exception {

		String rtn  = null;

		if(simpleField.getFieldType() == SimpleField.TYPE_INT){

			int eleValueInt = RandomFactory.randomIntBetween(new Double(min).intValue(), new Double(max).intValue() );

			rtn = String.valueOf(eleValueInt);

		}else if( simpleField.getFieldType() == SimpleField.TYPE_REAL ){

			double eleValueDouble = RandomFactory.randomDoubleBetween(Double.parseDouble( min ), Double.parseDouble( max ) );


			rtn = String.valueOf(new Double(eleValueDouble));

		}else if( simpleField.getFieldType() == SimpleField.TYPE_DATE ){

			Date dateMin = this.getDateTypeDataFormat().parse(min);

			Date dateMax = this.getDateTypeDataFormat().parse(max);

			Date rntDate = RandomFactory.randomDateBetween(dateMin, dateMax);

			rtn = this.getDateTypeDataFormat().format(rntDate);

		}else if( simpleField.getFieldType() == SimpleField.TYPE_DATETIME ){

			Date dateMin = this.getDateTimeTypeDataFormat().parse(min);

			Date dateMax = this.getDateTimeTypeDataFormat().parse(max);

			Date rntDate = RandomFactory.randomDateBetween(dateMin, dateMax);

			rtn = this.getDateTimeTypeDataFormat().format(rntDate);
		}

		return rtn;
	}

	protected abstract Object createEmptyTestCaseInstance(TestData testData);

	protected int getTestCaseInstanceNumber(TestData xmlTestData){
		int instanceSize = 0;

		Restriction restriction = xmlTestData.getExtendtion().getRestriction();

		double nullPercent = restriction.getNullPercentage();

		double nullPercentHit = RandomFactory.random();

		if(nullPercentHit <= nullPercent){
			instanceSize = 0;
		}
		else{
			double sectionPercent = RandomFactory.random();

			if( restriction.getItem() != null && restriction.getItem().size()>0 ){

				//循环中概率区间下限
				double percentUnitMin = 0;

				double percentUnitMax = 0;

				for( int i=0; i< restriction.getItem().size(); i++){

					Item tmpItem = restriction.getItem().get(i);

					int minSize;

					if( i==0 ){
						minSize =  restriction.getTestCaseMinNum();
					}else{
//						if( i==restriction.getItem().size()-1 ){
//							minSize = restriction.getMaxNum().intValue();
//						}
//						else{
						minSize = new Double(restriction.getItem().get(i-1).getValue()).intValue();
//						}
					}

					int maxSize;

					if( i==0 ){

						String maxSizeStr = restriction.getItem().get(i).getValue();

						if( maxSizeStr == null  ){
							System.out.println(xmlTestData.getPath() + "is null");
						}

						maxSize = new Double( maxSizeStr ).intValue();
					}else{

						maxSize = new Double(restriction.getItem().get(i).getValue()).intValue();
					}

					percentUnitMin = percentUnitMax;
					percentUnitMax += tmpItem.getPercentage();

					if( sectionPercent > percentUnitMin && sectionPercent <= percentUnitMax ){
						instanceSize = RandomFactory.randomIntBetween(minSize, maxSize);
					}
					//end for
				}
			}
		}

		return instanceSize;
	}

	protected abstract Object TestCaseInstanceCopy(Object testCaseInstance);

	public abstract Object generateRootTestCaseData( TestData testData );

	public List handleTestCaseDependency(TestData dependencyTestData, TestData slaveTestData, Object parentTestCaseElement){

		List newInstantceArr = null;

		int dependencyType = slaveTestData.getExtendtion().getDependency().getType();

		if (dependencyType == Dependency.TYPE_NULL) {
			if (dependencyTestData.getTestCase() != null
					&& dependencyTestData.getTestCase().size() > 0) {
				newInstantceArr = getInstanceNumberAndGenerateAttr(
						parentTestCaseElement, slaveTestData);
			}
		} else if (dependencyType == Dependency.TYPE_EQUICALENCE) {
			List dependencyList = dependencyTestData.getTestCase();
			for(Object eqEl : dependencyList){

				Object newEqEle = TestCaseInstanceCopy( eqEl );

				newInstantceArr.add(newEqEle);
			}

			return dependencyList;
		} else if (dependencyType == Dependency.TYPE_NUMBER) {
			List dependencyList = dependencyTestData.getTestCase();

			int instanceNum = 0;

			if (dependencyList != null) {
				instanceNum = dependencyList.size();

				return generateAttr(parentTestCaseElement, instanceNum,
						slaveTestData);
			}
		} else if (dependencyType == Dependency.TYPE_FUNCTIONNAME) {
			newInstantceArr = new ArrayList();
		}

		return newInstantceArr;
	}

	private  SimpleDateFormat	dateTypeDataFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private  SimpleDateFormat	dateTimeTypeDataFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	public  SimpleDateFormat getDateTypeDataFormat(){
		return dateTypeDataFormat;
	}
	
	public  SimpleDateFormat getDateTimeTypeDataFormat(){
		return dateTimeTypeDataFormat;
	}
	
	public boolean isMasterAvailable(AbstractTestData absTestData){
		
		if(absTestData instanceof TestData){
			TestData testData = (TestData)absTestData;
			
			if(testData.getExtendtion()!= null && testData.getExtendtion().getDependency() != null){
				String depPath = testData.getExtendtion().getDependency().getParentPath();
				
				if(this.bomGenerator.getTestDataDependencyMap().get(depPath) != null && this.bomGenerator.getTestDataDependencyMap().get(depPath)){
					return true;
				}
			}
		}else if(absTestData instanceof SimpleField){
			SimpleField simpleField = (SimpleField)absTestData;
			
			if(simpleField.getExtendtion()!= null && simpleField.getExtendtion().getDependency() != null){
				String depPath = simpleField.getExtendtion().getDependency().getParentPath();
				
				if(this.bomGenerator.getSimpleFieldDependencyMap().get(depPath) != null && this.bomGenerator.getSimpleFieldDependencyMap().get(depPath)){
					return true;
				}
			}
		}
		return false;
	}
	
	public void clearTestCaseFinishFlag(){
		if( this.bomGenerator.getRootTestData() != null ){
			recursiveClearTestCaseFinishFlag( this.bomGenerator.getRootTestData(), true );
		}
	}
	
	private void recursiveClearTestCaseFinishFlag(TestData testData, boolean flag){
		testData.setGenerateTestCaseFinish(flag);
		
		for(SimpleField sf : testData.getSimpleFieldList()){
			sf.setGenerateTestCaseFinish(true);
		}
		
		for(TestData childTestData : testData.getCustomFieldList()){
			recursiveClearTestCaseFinishFlag( childTestData,  flag);
		}
	}

	public Object generateTestCase(){
		
		clearSimpleFieldDependencyPathCounter();

		List<Object> instanceElementList = new ArrayList<Object>();

		TestData rootTestData = getRootTestData();
		
		Object rootTestCase = this.generateRootTestCaseData( rootTestData );

		instanceElementList.add( rootTestCase );
		
		rootTestData.setTestCase(instanceElementList);
		
		if(rootTestData.getCustomFieldList() != null){
			for(TestData testData : rootTestData.getCustomFieldList()){
				generateTestCaseUnitData(testData);
			}
		}
		
		generateNotCreatedSlaveTestCase();

		generateLocalDependencySimpleField();
		
		return rootTestCase;
	}

	public List generateTestCaseUnitData(TestData testData){

		List instanceElementList = generateTestDataInstance( testData );

		testData.setTestCase(instanceElementList);

		manageMasterSlave( testData );

		if(testData.getCustomFieldList() != null){
			for(TestData tmpTestData : testData.getCustomFieldList()){
				generateTestCaseUnitData(tmpTestData);
			}
		}

		return instanceElementList;
	}
	
	public void generateNotCreatedSlaveTestCase(){
		
		List<TestData> unConstructedTestCaseList = new ArrayList<TestData>();
		
		for(TestData td : this.bomGenerator.getDependencyTestDataList() ){
			if( !td.isGloableDependency() && !td.isGenerateTestCaseFinish() ){
				unConstructedTestCaseList.add(td);
			}
		}
		
		if(unConstructedTestCaseList != null){
			boolean loopFlag = true;
			
			while( loopFlag ){
				
				int unConstructedTestCaseListSize = unConstructedTestCaseList.size();
				
				for(TestData unConsTestCaseData : unConstructedTestCaseList){
					if(this.isMasterAvailable(unConsTestCaseData) && !unConsTestCaseData.isGloableDependency()){
						generateTestCaseUnitData(unConsTestCaseData);
						unConstructedTestCaseList.remove(unConsTestCaseData);
					}
				}
				if(unConstructedTestCaseList.size() == 0){
					loopFlag = false;
					break;
				}else if(unConstructedTestCaseListSize >= unConstructedTestCaseList.size()){
					System.out.println("local dead loop in generateNotCreatedSlaveTestCase");
					break;
				}
			}
		}
	}
	
	public void generateLocalDependencySimpleField(){
		List<SimpleField> unConstructedSimpleFieldList = new ArrayList<SimpleField>();
		
		for( SimpleField sf : this.bomGenerator.getDependencySimpleFieldList() ){
			if(sf.getExtendtion() != null && sf.getExtendtion().getDependency() != null && !sf.isGenerateTestCaseFinish() && !sf.isGloableDependency()){
				unConstructedSimpleFieldList.add(sf);
			}
		}
		
		if(unConstructedSimpleFieldList != null){
			boolean loopFlag = true;
			
			while( loopFlag ){
				
				int unConstructedTestCaseListSize = unConstructedSimpleFieldList.size();
				
				for(int i=0; i<unConstructedSimpleFieldList.size(); i ++){
					
					SimpleField unConsTestCaseData = unConstructedSimpleFieldList.get(i);
					
					if( this.isMasterAvailable(unConsTestCaseData) ){
						
						for(Object testCaseIns : unConsTestCaseData.getTestData().getTestCase() ){
							generateSingleTestCaseAttributeValue(testCaseIns,unConsTestCaseData);
						}
						unConstructedSimpleFieldList.remove(unConsTestCaseData);
					}
				}
				if(unConstructedSimpleFieldList.size() == 0){
					loopFlag = false;
					break;
				}else if(unConstructedTestCaseListSize == unConstructedSimpleFieldList.size()){
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
	}
	

	
	
	protected void manageMasterSlave(Object absTestData){

		manageLocalMasterSlave( absTestData );
	}
	
	protected void manageLocalMasterSlave( Object absTestData ){
		
		if(absTestData instanceof TestData){
			TestData testData = (TestData)absTestData;
			
			this.bomGenerator.getTestDataDependencyMap().put(testData.getPath(), true);
			
			List<SimpleField> simpleFiledList = testData.getSimpleFieldList();
			
			if( simpleFiledList != null){
				for(SimpleField simpleField : simpleFiledList){
					
					if(simpleField.isGenerateTestCaseFinish() &&  this.bomGenerator.getSimpleFieldDependencyMap().get(simpleField.getPath()) != null){
						this.bomGenerator.getSimpleFieldDependencyMap().put( simpleField.getPath(), true );
					}
				}
			}
		}
	}
	
}
