package com.fico.testCaseGenerator.testCase;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fico.testCaseGenerator.data.*;
import com.fico.testCaseGenerator.BOM.BOMGenerator;
import com.fico.testCaseGenerator.data.configuration.Item;
import com.fico.testCaseGenerator.data.configuration.Restriction;
import com.fico.testCaseGenerator.expression.TestCaseExpression;
import com.fico.testCaseGenerator.util.TestCaseUtils;
import org.apache.commons.beanutils.PropertyUtils;

public abstract class TestCaseGenerator {

	protected BOMGenerator bomGenerator = null;

	protected Map<String, Integer> simpleFieldDependencyLocalPathCounter = null;

	private TestCaseExpression testCaseExpression = null;

	protected Set<TestData> unGeneratedSlaveTestDataList = new HashSet<TestData>();

	protected Set<SimpleField> unGeneratedSlaveSimpleFieldList = new HashSet<SimpleField>();

	private static final int SIMPLE_FIELD_GENERATING_TYPE_NORMAL = 1;

	private static final int SIMPLE_FIELD_GENERATING_TYPE_MERGE = 2;


	/**
	 * 构造函数
	 * @param bomGenerator
	 */
	public TestCaseGenerator(BOMGenerator bomGenerator){

		this.bomGenerator = bomGenerator;

		this.testCaseExpression = new TestCaseExpression(this);
	}

	/**
	 * 主程序对外主入口
	 * @return
	 */
	public Object generateTestCase(){

		//重置状态
		clearSimpleFieldDependencyPathCounter();

		//生成根App
		TestData rootTestData = generateRootTestData();

		if(rootTestData.getCustomFieldList() != null){
			for(TestData testData : rootTestData.getCustomFieldList()){
				generateAllTestCaseListForOneTestData(testData);
			}
		}

		//集中处理依赖关系
		handleLocalDependency();

		return rootTestData.getTestCaseUnitList().get(0).getTestCaseInstance();
	}

	private TestData generateRootTestData(){

		TestData rootTestData = getRootTestData();

		Object rootTestCase = this.generateRootTestCaseData( rootTestData );

		TestCaseUnit testCaseUnit = new TestCaseUnit();
		testCaseUnit.setTestData(rootTestData);
		testCaseUnit.setTestCaseInstance(rootTestCase);
		testCaseUnit.setFirstChild(true);
		testCaseUnit.setLastChild(true);
		TestCaseUnit[] testCaseList = new TestCaseUnit[1];
		testCaseList[0] = testCaseUnit;
		testCaseUnit.setBrotherListForOneParent( testCaseList );

		rootTestData.getTestCaseUnitList().add(testCaseUnit);

		rootTestData.setGenerateTestCaseFinish(true);


		generateAllSimpleFeildTestCaseValueForOneTestCaseInstance(testCaseUnit, rootTestData);


		for(SimpleField simpleField : rootTestData.getTmpGeneratedSimpleFieldSet()){
			simpleField.setGenerateTestCaseFinish(true);
		}

		return rootTestData;
	}

	/**
	 * @param parentTestCaseUnit 父亲已经生成的测试案例的实例
	 * @param testData 要生成的testData
	 * @return
	 */
	private void getInstanceNumberAndGenerateAttr(TestCaseUnit parentTestCaseUnit, TestData testData){

		if(isTestDataMergeMode(testData)){
			tmpMergeParentTestCaseUnitElement = parentTestCaseUnit;
			this.testCaseExpression.parse(testData.getExtendtion().getRestriction());
			return ;
		}

		parentTestCaseUnit.getTestData().setGeneratingChildrenTestCaseUnit(parentTestCaseUnit);

		Integer instanceSize = generateTestCaseNumberFromRestriction(testData.getExtendtion().getRestriction());

		if(isTempoaryTestData(testData)){
			//临时节点
			generateTempoaryTestDataTestCase(instanceSize, testData);
		}else{
			//不是临时节点，正常生成
			generateAttr(parentTestCaseUnit, instanceSize, testData, null, SIMPLE_FIELD_GENERATING_TYPE_NORMAL);
		}
	}

	private Integer generateTestCaseNumberFromRestriction(Restriction restriction){
		//Object intVal = this.testCaseExpression.parseInitialValue( restriction );

		Object intVal = this.testCaseExpression.parse( restriction );

		Integer instanceSize = 0;

		if(intVal != null && !"".equalsIgnoreCase(intVal.toString())){
			instanceSize = new Double( intVal.toString() ).intValue();
		}

		return instanceSize;
	}

	private boolean isTempoaryTestData(AbstractTestData abstractTestData){
		return false;
	}

	/**
	 *
	 * @param parentTestCaseUnit 父元素
	 * @param normalInstanceSize 正常生成的testData节点的个数
	 * @param testData 目标生成的testData
	 * @param mergeSrcTestCaseUnitList merge的时候的源案例节点列表
	 * @param simpleFieldGeneratingType 生成类型标识，正常生成还是merge生成
	 */
	protected void generateAttr(TestCaseUnit parentTestCaseUnit, Integer normalInstanceSize, TestData testData, List mergeSrcTestCaseUnitList, int simpleFieldGeneratingType){

		int instanceSize = -1;

		if(simpleFieldGeneratingType == SIMPLE_FIELD_GENERATING_TYPE_NORMAL){
			instanceSize = normalInstanceSize;
		}else if(simpleFieldGeneratingType == SIMPLE_FIELD_GENERATING_TYPE_MERGE){
			instanceSize = mergeSrcTestCaseUnitList.size();
		}

		if(instanceSize > 0){

			TestCaseUnit[] tmpGeneratingArr = new TestCaseUnit[instanceSize];

			testData.setTempGeneratingArr(tmpGeneratingArr);

			Object parentTestCaseElement = parentTestCaseUnit.getTestCaseInstance();

			for(int i=0; i<instanceSize; i++){

				Object newIns = this.createEmptyTestCaseInstance(testData);

				appendChildTestCaseToParentTestCase( parentTestCaseElement, newIns, testData );

				TestCaseUnit testCaseUnit = new TestCaseUnit();
				testCaseUnit.setTestCaseInstance(newIns);
				testCaseUnit.setTestData(testData);
				testCaseUnit.setBrotherListForOneParent(tmpGeneratingArr);
				testCaseUnit.setParentTestCaseUnit(parentTestCaseUnit);
				testCaseUnit.setPositionInParent(i);

				if(i == 0){
					testCaseUnit.setFirstChild(true);
				}
				if(i == instanceSize-1){
					testCaseUnit.setLastChild(true);
				}

				testData.setGeneratingTestCaseUnit(testCaseUnit);

				tmpGeneratingArr[i] = testCaseUnit;

				testData.getTestCaseUnitList().add(testCaseUnit);

				if(simpleFieldGeneratingType == SIMPLE_FIELD_GENERATING_TYPE_NORMAL){
					generateAllSimpleFeildTestCaseValueForOneTestCaseInstance(testCaseUnit, testData);
				}else if(simpleFieldGeneratingType == SIMPLE_FIELD_GENERATING_TYPE_MERGE){
					//
					generateAllSimpleFeildTestCaseForMergeMode(testCaseUnit, testData, mergeSrcTestCaseUnitList);
				}
			}
			testData.setTempGeneratingArr(null);
			testData.setGeneratingTestCaseUnit(null);
		}
	}

	public void generateAllSimpleFeildTestCaseForMergeMode(TestCaseUnit parentTestCaseUnit, TestData testData, List<TestCaseUnit> srcTempInstanceList){

		Object targetObj = parentTestCaseUnit.getTestCaseInstance();

		Class targetObjCls = targetObj.getClass();

		for (TestCaseUnit tmpTestCaseUnit : srcTempInstanceList){

			Object tmpObj = tmpTestCaseUnit.getTestCaseInstance();

			Class tmpCls = tmpObj.getClass();

			for(SimpleField simpleField : testData.getSimpleFieldList()){

				String simpleFieldName = simpleField.getName();

				for(Field tmpField : tmpCls.getDeclaredFields()){
					if(simpleFieldName.equals(tmpField.getName())){
						try {
							tmpField.setAccessible(true);
							Object tmpObjTargetFieldValue = tmpField.get(tmpObj);

							setTestCaseElementValue(simpleField, parentTestCaseUnit, simpleFieldName, tmpObjTargetFieldValue);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	private TestCaseUnit tmpMergeParentTestCaseUnitElement = null;

	/**
	 * 这里以后要考虑类似多个人行的情况，目前只考虑一个
	 * @param testData
	 * @param testCaseListArr
	 */
	public void merge(TestData testData, List<List> testCaseListArr){
		for(List tmpTestCaseList : testCaseListArr){
			appendTempoaryToTargetTestData(tmpMergeParentTestCaseUnitElement, testData, tmpTestCaseList);
		}
	}

	private void appendTempoaryToTargetTestData(TestCaseUnit parentTestCaseUnit, TestData testData, List objList){

		generateAttr(parentTestCaseUnit,null, testData, objList, SIMPLE_FIELD_GENERATING_TYPE_MERGE);

//		for(Object obj : objList) {
//			Object newIns = this.createEmptyTestCaseInstance(testData);
//
//			appendChildTestCaseToParentTestCase(parentTestCaseUnit.getTestCaseInstance(), newIns, testData);
//
//			Class objCls = obj.getClass();
//			for (Field field : objCls.getDeclaredFields()) {
//
//				String fieldName = field.getName();
//
//				for(SimpleField simpleField : testData.getSimpleFieldList()){
//
//					if(simpleField.getName().equalsIgnoreCase(fieldName)) {
//						try {
//							field.setAccessible(true);
//							setTestCaseElementValue(simpleField, newIns, fieldName, field.get(obj));
//						} catch (IllegalAccessException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//			testData.getTestCase().add(newIns);
//		}
	}

	private boolean isTestDataMergeMode(AbstractTestData testData){
		if(testData.getExtendtion().getRestriction()!=null){
			Restriction restriction = testData.getExtendtion().getRestriction();
			if(restriction.getMinStr().contains(TestCaseExpression.MERGE_FUNCTION_NAME)){
				return true;
			}
			for(Item item:restriction.getItem()){
				if(item.getMinExpression().contains(TestCaseExpression.MERGE_FUNCTION_NAME) ||
						item.getMaxExpression().contains(TestCaseExpression.MERGE_FUNCTION_NAME)){
					return true;
				}
			}
		}
		return false;
	}

	protected void generateTempoaryTestDataTestCase(int instanceSize, TestData testData){
		TempoaryTestData tempoaryTestData = (TempoaryTestData)testData;

		for(int i=0; i<instanceSize; i++){

			TempoaryTestCase tempoaryTestCase = new TempoaryTestCase();

			for( SimpleField simpleField : tempoaryTestData.getSimpleFieldList() ){
					tempoaryTestCase.getKeyValuePairList().add(
						new TempoaryKeyValuePair(simpleField.getName(), this.testCaseExpression.parse(simpleField.getExtendtion().getRestriction()))
				);
			}
		}
	}

	public void generateAllSimpleFeildTestCaseValueForOneTestCaseInstance(TestCaseUnit parentTestCaseUnit, TestData testData){
		for( SimpleField simpleField : testData.getSimpleFieldList() ){
			if(simpleField.getExtendtion() != null){
				generateSingleTestCaseAttributeValue(parentTestCaseUnit, simpleField);
			}
		}
	}

	public void generateSingleTestCaseAttributeValue( TestCaseUnit parentTestCaseUnit,  SimpleField simpleField){

		if(simpleField.getExtendtion() != null && simpleField.getExtendtion().getRestriction() != null){

			if(this.isAllRelativeElementReady(simpleField)){

				Object expValue = this.testCaseExpression.parse(simpleField.getExtendtion().getRestriction());

				this.setTestCaseElementValue(simpleField, parentTestCaseUnit, simpleField.getName(), expValue);

			}
			else{
				recordAbstractTestDataPosition( simpleField );
				this.unGeneratedSlaveSimpleFieldList.add(simpleField);
			}
		}
	}

	public String getSharedPath(String path1, String path2){
		String rtn = null;
		int lastOprPosition = -1;
		int minLen = Math.min(path1.length(), path2.length());
		char[] tmpCharArr = new char[ minLen ];
		int i = 0;
		for(; i<path1.length()&&i<path2.length(); i++){

			if(path1.charAt(i) != path2.charAt(i)){
				break;
			}
			if(path1.charAt(i) == '@'){
				break;
			}
			if(path1.charAt(i) == '/'){
				lastOprPosition = i;
			}

		}
		return path1.substring(0, Math.min(i, lastOprPosition)+1 );
	}

	public TestCaseUnit getParentTestCaseUnitBaseOnChildTestCase(TestCaseUnit childTestCaseUnit, TestData parentTestData){

		TestData childTestData = childTestCaseUnit.getTestData();

		if(childTestData.equals(parentTestData)){
			return childTestCaseUnit;
		}

		boolean loopFlag = true;

		TestCaseUnit tmpChildTestDataIns = childTestCaseUnit;

		while(loopFlag){

			TestCaseUnit tmpParentTestDataUnit = tmpChildTestDataIns.getParentTestCaseUnit();

			TestData tmpParentTestData = tmpParentTestDataUnit.getTestData();

			if(tmpParentTestData.equals(parentTestData)){
				return tmpParentTestDataUnit;
			}else{
				tmpChildTestDataIns = tmpParentTestDataUnit;
			}
		}
		return null;
	}

	private void setTestCaseElementValue(SimpleField simpleField , TestCaseUnit parentTestCaseUnit, String attributeName, Object attributeValue){

		Object newInstantceArrElement = parentTestCaseUnit.getTestCaseInstance();

		setTestCaseInstanceSimpleFieldValue(newInstantceArrElement,attributeName, attributeValue);

		simpleField.getTestData().getTmpGeneratedSimpleFieldSet().add(simpleField);

		addSimpleListTestCaseValue(simpleField, attributeValue);
	}

	private void addSimpleListTestCaseValue(SimpleField simpleField, Object eleValue){
		simpleField.getTestCase().add(eleValue);
	}

	/**
	 * 递归生成一个TestData下所有案例数据，包括主从
	 * @param testData
	 * @return
	 */
	public void generateAllTestCaseListForOneTestData(TestData testData){

		//生成自己
		generateTestDataInstance( testData );

		//递归自己下面的所有子节点
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

		List<TestCaseUnit> parentTestCaseUnitList = testData.getParentTestData().getTestCaseUnitList();

		if(testData.getExtendtion() != null && testData.getExtendtion().getRestriction() != null){

			if( isAllRelativeElementReady( testData ) ){

				//先冗余写，以后这个if可能不需要
				if(parentTestCaseUnitList == null || parentTestCaseUnitList.size() == 0){
					recursiveClearTestCaseFinishFlag(testData, true);
				}
				else{
					for( TestCaseUnit parentTemptestCaseUnit  :  parentTestCaseUnitList){
						testData.getParentTestData().setGeneratingChildrenTestCaseUnit(parentTemptestCaseUnit);
						getInstanceNumberAndGenerateAttr(parentTemptestCaseUnit, testData);
					}
					testData.getParentTestData().setGeneratingChildrenTestCaseUnit(null);
					//根据概率，一个案例没生成的情况
					if(testData.getTestCaseUnitList().size() == 0){
						recursiveClearTestCaseFinishFlag(testData, true);
					}
					else{
						testData.setGenerateTestCaseFinish(true);

						for(SimpleField simpleField : testData.getTmpGeneratedSimpleFieldSet()){
							simpleField.setGenerateTestCaseFinish( true );
						}
						testData.setTmpGeneratedSimpleFieldSet(null);
					}
				}
			}else{
				this.unGeneratedSlaveTestDataList.add( testData );
				if( testData.getParentTestData().isGenerateTestCaseFinish() ){
					this.recordAbstractTestDataPosition( testData );
				}
			}
		}
	}

	private boolean isAllRelativeElementReady(AbstractTestData abstractTestData){

		if(abstractTestData.getName().equalsIgnoreCase("OverdueRecord")){
			String a = "";
		}

		boolean isAllRelativeElementReady = true;

		if(abstractTestData.getExtendtion() == null || abstractTestData.getExtendtion().getRestriction() == null){
			return isAllRelativeElementReady;
		}

		if(abstractTestData instanceof TestData){
			TestData testData = (TestData)abstractTestData;
			if(!testData.getParentTestData().isGenerateTestCaseFinish()){
				return false;
			}
			else if(testData.getParentTestData().getTestCaseUnitList().size()==0){
				return true;
			}
		}

		for(Item item : abstractTestData.getExtendtion().getRestriction().getItem()){
			if(  !this.testCaseExpression.isAllElementReady( item.getMinExpression(), abstractTestData.getPath())
					|| !this.testCaseExpression.isAllElementReady( item.getMaxExpression(), abstractTestData.getPath())){
				isAllRelativeElementReady = false;
			}
		}

		if(isAllRelativeElementReady && this.isTestDataMergeMode(abstractTestData)){

			for(Item item : abstractTestData.getExtendtion().getRestriction().getItem()){
				List<String> pathList = this.testCaseExpression.getAllAbsTestData(item.getMinExpression());

				for(String path : pathList){
					for(SimpleField simpleField : this.unGeneratedSlaveSimpleFieldList){
						if(simpleField.getPath().startsWith(abstractTestData.getPath()) ||
								simpleField.getPath().startsWith(path)
								){
							return false;
						}
					}
				}
			}
		}

		return isAllRelativeElementReady;
	}

	private void handleLocalDependency(){
		boolean testDataLoopFlag = false;
		boolean simpleFieldLoopFlag = false;

		boolean isTestDataListReduced = true;
		boolean isSimpleFieldReduced = true;

		int bothReduced = 0;

		do{
			//testData
			if(this.unGeneratedSlaveTestDataList != null && this.unGeneratedSlaveTestDataList.size() > 0){

				testDataLoopFlag = true;

				List<TestData> tmpList = new ArrayList<TestData>();

				tmpList.addAll( unGeneratedSlaveTestDataList );

				while( testDataLoopFlag ){

					int unConstructedTestDataListSize = unGeneratedSlaveTestDataList.size();

					for( int i=0; i<tmpList.size(); i++){
						TestData unConsTestCaseData = tmpList.get(i);
						if(this.isAllRelativeElementReady( unConsTestCaseData )){
							generateAllTestCaseListForOneTestData(unConsTestCaseData);
							unGeneratedSlaveTestDataList.remove(unConsTestCaseData);
							tmpList.remove(unConsTestCaseData);
						}
					}
					if(unGeneratedSlaveTestDataList.size() == 0){
						testDataLoopFlag = false;
						break;
					}else if(unConstructedTestDataListSize == unGeneratedSlaveTestDataList.size()){
						isTestDataListReduced = false;
						break;
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

						SimpleField unConsSimple = tmpList.get(i);

						if( this.isAllRelativeElementReady(unConsSimple) ){

							for(TestCaseUnit testCaseUnit : unConsSimple.getTestData().getTestCaseUnitList() ){
								unConsSimple.getTestData().setGeneratingTestCaseUnit(testCaseUnit);
								generateSingleTestCaseAttributeValue(testCaseUnit,unConsSimple);
							}
							unConsSimple.getTestData().setGeneratingTestCaseUnit(null);

							unConsSimple.setGenerateTestCaseFinish(true);
							unGeneratedSlaveSimpleFieldList.remove(unConsSimple);
							tmpList.remove(unConsSimple);
						}
					}
					if(unGeneratedSlaveSimpleFieldList.size() == 0){
						simpleFieldLoopFlag = false;
						break;
					}else if(unConstructedSimpleListSize == unGeneratedSlaveSimpleFieldList.size()){
						isSimpleFieldReduced = false;
						break;
					}
				}
			}

			//异常
			if( !isTestDataListReduced && !isSimpleFieldReduced ){

				bothReduced ++;

				if(bothReduced >= 1){
					System.out.println("None TestData nor SimpleField is reduced");
				}

				//break;
			}else{
				bothReduced = 0 ;
			}

		}while (testDataLoopFlag || simpleFieldLoopFlag );
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
		testData.setTempGeneratingArr(null);
		testData.setGeneratingTestCaseUnit(null);
		testData.setGeneratingChildrenTestCaseUnit(null);

		for(TestCaseUnit testCaseUnit : testData.getTestCaseUnitList()){
			testCaseUnit.setParentTestCaseUnit(null);
			testCaseUnit.setTestData(null);
			testCaseUnit.setBrotherListForOneParent(null);
		}
		testData.setTestCaseUnitList(null);
		testData.setTmpGeneratedSimpleFieldSet(null);
		testData.setGeneratingTestCaseUnit(null);

		for(SimpleField sf : testData.getSimpleFieldList()){
			sf.setGenerateTestCaseFinish(flag);
			sf.setDependencySimpleFieldPosition(new ArrayList<Integer>());
		}

		for(TestData childTestData : testData.getCustomFieldList()){
			recursiveClearTestCaseFinishFlag( childTestData,  flag);
		}
	}


	//找主从最近的共享节点
	private void recordAbstractTestDataPosition(AbstractTestData absTestData){

//		List<String> allRelativedPath = absTestData.getRelativeManyToOnePathSet();
//
//		Integer[] tmpPathPosition = new Integer[allRelativedPath.size()];
//
//		int i = 0;
//
//		for( Iterator<String> it = allRelativedPath.iterator(); it.hasNext(); i++ ){
//
//			String path = it.next();
//
//			if(this.bomGenerator.pathIsSimpleField(path)){
//
//				String minimumSharedPath = getSharedPath(absTestData.getPath(), path);
//
//				TestData minimumTestData = this.bomGenerator.getPathTestDataMap().get(minimumSharedPath);
//
//				SimpleField masterSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(path);
//
//				if(masterSimpleField == null){
//					String a = "";
//				}
//
//				masterSimpleField.getRelativeManyToOnePathSet().add(minimumSharedPath);
//
//				//已生成
//				if(minimumTestData.isGenerateTestCaseFinish()){
//
//					int tmpPos = -1;
//
//					if(absTestData.getPositionRecord().size() < minimumTestData.getTestCase().size()){
//						tmpPos = absTestData.getPositionRecord().size();
//					}else{
//						tmpPos = minimumTestData.getTestCase().size() -1;
//					}
//
//					if(tmpPos == -1){
//						String a = "";
//					}
//
//					tmpPos = Math.max(tmpPos, 0);
//					tmpPathPosition[i] = tmpPos;;
//				}
//				//正在生成
//				else if(minimumTestData.getTestCase().size()>0 && minimumTestData.getGeneratingTestCaseUnit() != null){
//					tmpPathPosition[i] = minimumTestData.getTestCase().size() - 1;
//				}
//			}
//		}
//
//		absTestData.getPositionRecord().add( tmpPathPosition );
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


	public BOMGenerator getBomGenerator() {
		return bomGenerator;
	}
}
