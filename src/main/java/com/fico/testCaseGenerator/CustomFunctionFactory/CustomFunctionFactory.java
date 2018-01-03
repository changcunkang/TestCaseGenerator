package com.fico.testCaseGenerator.CustomFunctionFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fico.testCaseGenerator.BOM.BOMGenerator;
import com.fico.testCaseGenerator.data.AbstractTestData;
import com.fico.testCaseGenerator.data.SimpleField;
import com.fico.testCaseGenerator.data.TestCaseUnit;
import com.fico.testCaseGenerator.data.TestData;
import com.fico.testCaseGenerator.expression.TestCaseExpression;
import com.fico.testCaseGenerator.testCase.TestCaseGenerator;
import com.fico.testCaseGenerator.util.ClassUtil;
import com.fico.testCaseGenerator.util.RandomFactory;
import com.fico.testCaseGenerator.util.TestCaseUtils;
import org.apache.commons.beanutils.PropertyUtils;
import sun.java2d.pipe.SpanShapeRenderer;

public class CustomFunctionFactory {

	private IdCardGenerator g = new IdCardGenerator();

	private BOMGenerator bomGenerator;

	private TestCaseGenerator testCaseGenerator;

	public CustomFunctionFactory(TestCaseGenerator testCaseGenerator){
		this.bomGenerator = testCaseGenerator.getBomGenerator();
		this.testCaseGenerator = testCaseGenerator;
	}
	
	public Object invokeCustomFunction(String functionName, Object... args){
		Method[] mArr = CustomFunctionFactory.class.getMethods();

		if(functionName.equals("getDayNumber") || functionName.equals("getStmtOdue120")){
			String a = "";
		}

		for (Method method : mArr) {
			if (method.getName().equals(functionName)) {
				Object rtn = null;
				try {

					if(args == null){
						rtn = method.invoke(this);
					}else{

						rtn = method.invoke(this, args);
					}

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}catch (Exception e){
					e.printStackTrace();
				}
				return rtn;
			}
		}
		return null;
	}

	public Object enumuationFill(Object pathObj, Object joinEnumOjb){

		List joinedEnumList = new ArrayList();

		for(String tmp : joinEnumOjb.toString().split("_") ){
			joinedEnumList.add(tmp);
		}

		String path = pathObj.toString();

		SimpleField simpleField = this.bomGenerator.getPathSimpleFieldMap().get(path);

		TestData simpleFieldParentTestData = simpleField.getTestData();

		TestCaseUnit[] tmpGeneratingArr = simpleFieldParentTestData.getTempGeneratingTestCaseUnitArr();

		if(tmpGeneratingArr == null){
			String a = "";
			tmpGeneratingArr = null;
		}

		List tmpPickedList = new ArrayList();

		for(TestCaseUnit tmpTestCaseUnit : tmpGeneratingArr){
			try {

				if(tmpTestCaseUnit != null){
					Object tmpNewTestCase = tmpTestCaseUnit.getTestCaseInstance();

					if(tmpNewTestCase != null){
						Object alreadyGeneratedSimpleFieldValue = PropertyUtils.getSimpleProperty(tmpNewTestCase, simpleField.getName());
						if(alreadyGeneratedSimpleFieldValue != null){
							if(joinedEnumList.contains(alreadyGeneratedSimpleFieldValue)){
								joinedEnumList.remove(alreadyGeneratedSimpleFieldValue);
							}
						}
					}
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return joinedEnumList.get( RandomFactory.randomIntBetween(0, joinedEnumList.size()-1) );
	}

	public Integer testDataSimpleFieldSetSize(TestData testData, String targetSimpleFieldPath, String isCurrent){

		Set set = setTestDataSimpleFieldSet(testData, targetSimpleFieldPath, isCurrent);

		return set.size();
	}

	public Object testDataSimpleFieldSetType( String simpleFieldPath, String targetPath, String isCurrent ){

		SimpleField simpleField = this.bomGenerator.getPathSimpleFieldMap().get(simpleFieldPath);

		TestData testData = simpleField.getTestData();

		Set set = setTestDataSimpleFieldSet(testData, targetPath, isCurrent);

		int i=0;

		StringBuffer sb = new StringBuffer();

		TestCaseUnit[] tmpGeneratingArr = testData.getTempGeneratingTestCaseUnitArr();

		for(TestCaseUnit tmpTestCaseUnit : tmpGeneratingArr){
			try {

				if(tmpTestCaseUnit != null){
					Object tmpNewTestCase = tmpTestCaseUnit.getTestCaseInstance();

					if(tmpNewTestCase != null){
						Object alreadyGeneratedSimpleFieldValue = PropertyUtils.getSimpleProperty(tmpNewTestCase, simpleField.getName());
						if(alreadyGeneratedSimpleFieldValue != null){
							if(set.contains(alreadyGeneratedSimpleFieldValue)){
								set.remove(alreadyGeneratedSimpleFieldValue);
							}
						}
					}
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}

		return set.toArray()[0];
	}

	public Set setTestDataSimpleFieldSet(TestData testData, String targetSimpleFieldPath, String isCurrent){

		// Todo 目前只有分期用，其它地方用可能有问题

		TestCaseUnit callingParentMonthlyRecordInfo = testData.getParentTestData().getGeneratingChildrenTestCaseUnit();

		SimpleField tmpSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(targetSimpleFieldPath);

		TestData targetSimpleFieldTestData = tmpSimpleField.getTestData();

		List<TestCaseUnit> testCaseUnitList = this.testCaseGenerator.findAllChildrenForOneParentTestCaseUnit( targetSimpleFieldTestData, callingParentMonthlyRecordInfo );

		if(isCurrent != null && "1".equals(isCurrent)){
			for(int i=0; i<testCaseUnitList.size(); i++){
				TestCaseUnit tmpTestCaseUnit = testCaseUnitList.get(i);
				if(tmpTestCaseUnit.getParentTestCaseUnit().getPositionInParent() != 0){
					testCaseUnitList.remove( tmpTestCaseUnit );
				}
			}
		}

		Set rtnSet = new HashSet();

		for(TestCaseUnit tmpTestCaseUnit : testCaseUnitList){
			Object tmpFieldVal = tmpTestCaseUnit.getFieldValue(tmpSimpleField.getName());
			if(tmpFieldVal != null){
				rtnSet.add(tmpFieldVal);
			}
		}
		return rtnSet;
	}


	public Object randomString(Object... enumObj){
		int len = enumObj.length;

		return enumObj[ RandomFactory.randomIntBetween(0,len-1 ) ];
	}

	/**
	 * 后续还要改造这个方法，目前先这么用，后续要传两个参数，path和筛选条件，path找到目标TestCase，筛选条件进行过滤
	 * @param testData
	 * @param absTestDataPath
	 * @return
	 */
	public Object testDataSize( AbstractTestData testData, String absTestDataPath ){

		if(absTestDataPath == null){
			String a = "";
		}

		if(absTestDataPath.contains("[")){

			List rtnList = ClassUtil.search(this.bomGenerator.getRootTestData().getTestCaseUnitList().get(0).getTestCaseInstance(), absTestDataPath);

			return rtnList.size();

		}else{

			if("Application/Customer/Product/Account/".equalsIgnoreCase(absTestDataPath)){
				String a = "";
			}

			List targetSharedTestCase = findCrorrespondingTestCaseUnit(testData, absTestDataPath);

			return targetSharedTestCase.size();
		}
	}

	public Integer getLastMonthlyRecordInfoNonEndInstalmentNum(String selfPath, String instalmentNewPath, String newPeriod){

		SimpleField sf = this.bomGenerator.getPathSimpleFieldMap().get(newPeriod);

		TestData testData = this.bomGenerator.getPathTestDataMap().get( selfPath );

		TestData instalmentNewTestData = this.bomGenerator.getPathTestDataMap().get( instalmentNewPath );

		TestCaseUnit callingParentMonthlyRecordInfo = testData.getParentTestData().getGeneratingChildrenTestCaseUnit();

		if(callingParentMonthlyRecordInfo.isFirstChild()){
			return 0;
		}

		TestCaseUnit[] callingParentMonthlyUnitForOneParentArr = callingParentMonthlyRecordInfo.getBrotherListForOneParent();

		TestCaseUnit callingParentMonthlyRecordInfoPreviousSibling =callingParentMonthlyUnitForOneParentArr[callingParentMonthlyRecordInfo.getPositionInParent() - 1];

		return getLastMonthSumInstalmentList(callingParentMonthlyRecordInfoPreviousSibling, testData, instalmentNewTestData).size();

	}

	public String getLastMonthlyRecordInfoNonEndInstalmentInstalmentID(String selfInstalmentSimpleFieldPath, String instalmentNewPath){

		SimpleField instalmentSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(selfInstalmentSimpleFieldPath);

		TestData testData = instalmentSimpleField.getTestData();

		TestData instalmentNewTestData = this.bomGenerator.getPathTestDataMap().get( instalmentNewPath );

		TestCaseUnit callingParentMonthlyRecordInfo = testData.getParentTestData().getGeneratingChildrenTestCaseUnit();

		TestCaseUnit[] callingParentMonthlyUnitForOneParentArr = callingParentMonthlyRecordInfo.getBrotherListForOneParent();

		TestCaseUnit callingParentMonthlyRecordInfoPreviousSibling =callingParentMonthlyUnitForOneParentArr[callingParentMonthlyRecordInfo.getPositionInParent() - 1];

		List<TestCaseUnit> lastMonthSumInstalmentList = getLastMonthSumInstalmentList(callingParentMonthlyRecordInfoPreviousSibling, testData, instalmentNewTestData);

		List instalmentIDWholeList = new ArrayList();

		for(TestCaseUnit tmpPreviouosTestCaseUnit : lastMonthSumInstalmentList){
			if(tmpPreviouosTestCaseUnit != null){
				Object tmpFieldValue = tmpPreviouosTestCaseUnit.getFieldValue(instalmentSimpleField.getName());
				if(tmpFieldValue != null){
					instalmentIDWholeList.add( tmpPreviouosTestCaseUnit.getFieldValue(instalmentSimpleField.getName()) );
				}
			}
		}

		TestCaseUnit[] tmpGeneratingArr = testData.getTempGeneratingTestCaseUnitArr();

		for(TestCaseUnit tmpGeneratingTestCaseUnit : tmpGeneratingArr){
			if(tmpGeneratingTestCaseUnit!=null){
				Object tmpInstalmentID = tmpGeneratingTestCaseUnit.getFieldValue(instalmentSimpleField.getName());
				if(tmpInstalmentID != null && instalmentIDWholeList.contains(tmpInstalmentID)){
					instalmentIDWholeList.remove(tmpInstalmentID);
				}
			}
		}
		return (String)instalmentIDWholeList.get(0);
	}

	public Object getLastMonthlyRecordInfoNonEndInstalmentInstalmentCurrentPeriod(){
		SimpleField instalmentSimpleField = this.bomGenerator.getPathSimpleFieldMap().get("Application/Customer/Product/Account/MonthlyRecordInfo/InstalmentDetail_Old/@currentInstalmentPeriod/");

		TestData testData = instalmentSimpleField.getTestData();

		TestData instalmentNewTestData = this.bomGenerator.getPathTestDataMap().get( "Application/Customer/Product/Account/MonthlyRecordInfo/InstalmentDetail_New/" );

		//TestCaseUnit callingParentMonthlyRecordInfo = testData.getGeneratingTestCaseUnit().getParentTestCaseUnit();

		//if(callingParentMonthlyRecordInfo == null){
		TestCaseUnit callingParentMonthlyRecordInfo = testData.getParentTestData().getGeneratingChildrenTestCaseUnit();
		//}

		TestCaseUnit[] callingParentMonthlyUnitForOneParentArr = callingParentMonthlyRecordInfo.getBrotherListForOneParent();

		TestCaseUnit callingParentMonthlyRecordInfoPreviousSibling =callingParentMonthlyUnitForOneParentArr[callingParentMonthlyRecordInfo.getPositionInParent() - 1];

		List<TestCaseUnit> lastMonthSumInstalmentList = getLastMonthSumInstalmentList(callingParentMonthlyRecordInfoPreviousSibling, testData, instalmentNewTestData);

		List instalmentIDWholeList = new ArrayList();

		SimpleField idSimpleField = this.bomGenerator.getPathSimpleFieldMap().get( "Application/Customer/Product/Account/MonthlyRecordInfo/InstalmentDetail_Old/@instalmentID/");

		SimpleField targetSimpleField = this.bomGenerator.getPathSimpleFieldMap().get( "Application/Customer/Product/Account/MonthlyRecordInfo/InstalmentDetail_Old/@currentInstalmentPeriod/");

		for(TestCaseUnit tmpPreviouosTestCaseUnit : lastMonthSumInstalmentList){
			String idFieldName = idSimpleField.getName();
			if(tmpPreviouosTestCaseUnit.getFieldValue(idFieldName).equals(testData.getGeneratingTestCaseUnit().getFieldValue(idFieldName))){
				Object tmpPreviousVal = tmpPreviouosTestCaseUnit.getFieldValue(targetSimpleField.getName());
				if(targetSimpleField.getName().equals("currentInstalmentPeriod")){

					if(tmpPreviousVal == null){
						String a = "";
					}

					return new Integer(new Integer(tmpPreviousVal.toString()) - 1);
//				}else if(targetSimpleField.getName().equals("currentInstalmentPeriod")){
//					return new BigDecimal();
				}

				return tmpPreviousVal ;

			}
		}

		return null;
	}

	public Object getLastMonthlyRecordInfoNonEndInstalmentInstalmentType(String selfInstalmentSimpleFieldPath, String instalmentNewPath, String instalmentIDpath, String targetSimpleFieldpath){

		SimpleField instalmentSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(selfInstalmentSimpleFieldPath);

		TestData testData = instalmentSimpleField.getTestData();

		TestData instalmentNewTestData = this.bomGenerator.getPathTestDataMap().get( instalmentNewPath );

		//TestCaseUnit callingParentMonthlyRecordInfo = testData.getGeneratingTestCaseUnit().getParentTestCaseUnit();

		//if(callingParentMonthlyRecordInfo == null){
		TestCaseUnit callingParentMonthlyRecordInfo = testData.getParentTestData().getGeneratingChildrenTestCaseUnit();
		//}

		TestCaseUnit[] callingParentMonthlyUnitForOneParentArr = callingParentMonthlyRecordInfo.getBrotherListForOneParent();

		TestCaseUnit callingParentMonthlyRecordInfoPreviousSibling =callingParentMonthlyUnitForOneParentArr[callingParentMonthlyRecordInfo.getPositionInParent() - 1];

		List<TestCaseUnit> lastMonthSumInstalmentList = getLastMonthSumInstalmentList(callingParentMonthlyRecordInfoPreviousSibling, testData, instalmentNewTestData);

		List instalmentIDWholeList = new ArrayList();

		SimpleField idSimpleField = this.bomGenerator.getPathSimpleFieldMap().get( instalmentIDpath);

		SimpleField targetSimpleField = this.bomGenerator.getPathSimpleFieldMap().get( targetSimpleFieldpath);


		for(TestCaseUnit tmpPreviouosTestCaseUnit : lastMonthSumInstalmentList){
			String idFieldName = idSimpleField.getName();
			if(tmpPreviouosTestCaseUnit.getFieldValue(idFieldName).equals(testData.getGeneratingTestCaseUnit().getFieldValue(idFieldName))){
				Object tmpPreviousVal = tmpPreviouosTestCaseUnit.getFieldValue(targetSimpleField.getName());
				if(targetSimpleField.getName().equals("currentInstalmentPeriod")){

					if(tmpPreviousVal == null){
						String a = "";
					}

					return new Integer(new Integer(tmpPreviousVal.toString()) - 1);
//				}else if(targetSimpleField.getName().equals("currentInstalmentPeriod")){
//					return new BigDecimal();
				}

				return tmpPreviousVal ;

			}
		}

		return null;
	}


	private List<TestCaseUnit> getLastMonthSumInstalmentList(TestCaseUnit callingParentMonthlyRecordInfoPreviousSibling, TestData oldInstalmentTestData, TestData newInstalmentTestData){

		List<TestCaseUnit> previousOldTestDataList = this.testCaseGenerator.findAllChildrenForOneParentTestCaseUnit(oldInstalmentTestData ,callingParentMonthlyRecordInfoPreviousSibling );

		List<TestCaseUnit> previdousNewTestDataList = this.testCaseGenerator.findAllChildrenForOneParentTestCaseUnit(newInstalmentTestData ,callingParentMonthlyRecordInfoPreviousSibling );

		for(int i=0; i<previousOldTestDataList.size(); i++){
			TestCaseUnit tmpTestCaseUnit = previousOldTestDataList.get(i);

			if(tmpTestCaseUnit.getFieldValue("currentInstalmentPeriod") == null){
				String a = "";
			}

			if(tmpTestCaseUnit.getFieldValue("currentInstalmentPeriod").toString().equals("1")){
				previousOldTestDataList.remove(tmpTestCaseUnit);
			}
		}

		List<TestCaseUnit> rtnList = new ArrayList<TestCaseUnit>();

		if(previousOldTestDataList != null && previousOldTestDataList.size()>0){
			rtnList.addAll(previousOldTestDataList);
		}
		if(previdousNewTestDataList != null && previdousNewTestDataList.size()>0){
			rtnList.addAll(previdousNewTestDataList);
		}

		return rtnList;
	}


	/**
	 * 中间节点合并函数
	 * @param testData
	 * @param testDataPath
	 * @return
	 */
	public Object merge(TestData testData, String[] testDataPath){

		if(testData.getName().equalsIgnoreCase("LoanCard")){
			String a = "";
		}

		List<List> srcMergeList = new ArrayList<List>();

		for(String tmpTestDataPath : testDataPath){

			List targetList = findCrorrespondingTestCaseUnit(testData, tmpTestDataPath);

			srcMergeList.add( targetList );
		}

		this.testCaseGenerator.merge(testData, srcMergeList);

		return null;
	}

	public Object mergeFilter(TestData testData, String[] testDataPathFilterPairArr){

		int pairLen = testDataPathFilterPairArr.length / 2;

		List srcMergeList = new ArrayList();

		for(int i=0; i<pairLen; i++){
			List targetList = findCrorrespondingTestCaseUnit(testData, testDataPathFilterPairArr[i*2]);
			for(Object obj : targetList){
				Object tmpTargetMergeObj = ClassUtil.search(obj, testDataPathFilterPairArr[ i*2+1 ]);
				if(tmpTargetMergeObj != null){
					srcMergeList.add(tmpTargetMergeObj);
				}
			}
		}

		this.testCaseGenerator.merge(testData, srcMergeList);

		return null;
	}

	public List<TestCaseUnit> findCrorrespondingTestCaseUnit(AbstractTestData testData, String absTestDataPath){

		List<TestCaseUnit> rtn = new ArrayList<TestCaseUnit>();

		TestData callIngTestData = getBelongingTestData(testData);

		TestData targetTestData = getBelongingTestData( this.bomGenerator.getAbsTestDataFromPath(absTestDataPath) );

		if(callIngTestData.equals(targetTestData)){
			assert callIngTestData.getGeneratingTestCaseUnit() == null : "in findCrorrespondingTestCaseUnit, GeneratingTestCaseUnit is null";

			if(callIngTestData.getGeneratingTestCaseUnit() == null){
				String a = "";
			}

			rtn.add(callIngTestData.getGeneratingTestCaseUnit());
			return rtn;
		}

		TestCaseUnit callingTestCaseUnit = callIngTestData.getGeneratingTestCaseUnit();

		//自己一个案例都没有，找父亲
		if(callingTestCaseUnit == null){
			callIngTestData = callIngTestData.getParentTestData();
			callingTestCaseUnit = callIngTestData.getGeneratingChildrenTestCaseUnit();
		}

		String sharedPath = this.testCaseGenerator.getSharedPath(testData.getPath(), absTestDataPath);

		TestData sharedTestData = this.bomGenerator.getPathTestDataMap().get(sharedPath);

		TestCaseUnit targetSharedTestCaseUnit = this.testCaseGenerator.getParentTestCaseUnitBaseOnChildTestCase(callingTestCaseUnit, sharedTestData);

		for(TestCaseUnit tmpTargetDataTestCaseUnit : targetTestData.getTestCaseUnitList()){

			TestCaseUnit tmpSharedTestCaseUnit = this.testCaseGenerator.getParentTestCaseUnitBaseOnChildTestCase(tmpTargetDataTestCaseUnit, sharedTestData);

			if(tmpSharedTestCaseUnit == targetSharedTestCaseUnit){
				if(tmpTargetDataTestCaseUnit == null){
					String a = "";
				}
				rtn.add(tmpTargetDataTestCaseUnit);
			}
		}
		return rtn;
	}



	public TestData getBelongingTestData(AbstractTestData abstractTestData){
		if(abstractTestData instanceof TestData){
			return (TestData)abstractTestData;
		}else if(abstractTestData instanceof SimpleField){
			return  ((SimpleField)abstractTestData).getTestData();
		}
		return null;
	}

	public Boolean inStr(Object src, Object strArrObj){
		String srcStr = (String)src;
		String strArr = (String)strArrObj;

		if(strArr.indexOf(srcStr) != -1){
			return true;
		}else{
			return false;
		}
	}

	public Object minSimpleField(String path){
		SimpleField simpleField = this.bomGenerator.getPathSimpleFieldMap().get(path);

		List testCaseList = simpleField.getTestCase();

		Double sumD = new Double(0);

		for(Object doubleObj : testCaseList){

			if(doubleObj != null){
				if(doubleObj instanceof Date){
					Long dateMilsec = ((Date)doubleObj).getTime();
					sumD = Math.min(new Double(dateMilsec), sumD );
				}
				else{
					sumD = Math.min(new Double(doubleObj.toString()), sumD );
				}

			}
		}

		return sumD;
	}

	public Object maxFilter(String query, String fieldName){

		List queryedList = ClassUtil.search(this.bomGenerator.getRootTestData().getTestCaseUnitList().get(0).getTestCaseInstance(), query);

		Double sumD = new Double(0);

		for(Object queryObj : queryedList){

			Class queryObjCls = queryObj.getClass();

			try {
				Field field = queryObjCls.getDeclaredField(fieldName);

				field.setAccessible(true);

				Object doubleObj = field.get(queryObj);

				if(doubleObj != null){
					if(doubleObj instanceof Date){
						Long dateMilsec = ((Date)doubleObj).getTime();
						sumD = Math.min(new Double(dateMilsec), sumD );
					}
					else{
						sumD = Math.min(new Double(doubleObj.toString()), sumD );
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return sumD;
	}


	public Object minFilter(String query, String fieldName){

		List queryedList = ClassUtil.search(this.bomGenerator.getRootTestData().getTestCaseUnitList().get(0).getTestCaseInstance(), query);

		Double sumD = new Double(0);

		for(Object queryObj : queryedList){

			Class queryObjCls = queryObj.getClass();

			try {
				Field field = queryObjCls.getDeclaredField(fieldName);

				field.setAccessible(true);

				Object doubleObj = field.get(queryObj);

				if(doubleObj != null){
					if(doubleObj instanceof Date){
						Long dateMilsec = ((Date)doubleObj).getTime();
						sumD = Math.min(new Double(dateMilsec), sumD );
					}
					else{
						sumD = Math.min(new Double(doubleObj.toString()), sumD );
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return sumD;
	}


	public Object avgFilter(String query, String fieldName){

		List queryedList = ClassUtil.search(this.bomGenerator.getRootTestData().getTestCaseUnitList().get(0).getTestCaseInstance(), query);

		Double sumD = new Double(0);

		for(Object queryObj : queryedList){

			Class queryObjCls = queryObj.getClass();

			try {
				Field field = queryObjCls.getDeclaredField(fieldName);

				field.setAccessible(true);

				Object doubleObj = field.get(queryObj);

				if(doubleObj != null){
					if(doubleObj instanceof Date){
						Long dateMilsec = ((Date)doubleObj).getTime();
						sumD += new Double(dateMilsec);
					}
					else{
						sumD += new Double(doubleObj.toString());
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if(queryedList.size() == 0){
			return 0;
		}

		return sumD/queryedList.size();
	}


    public Double sumFilter(String simpleFieldPath, String targetSumSimpleFieldPath, String queryBy, String targetQyeryFieldName){

	    SimpleField sumedSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(simpleFieldPath);

	    SimpleField targetSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(targetSumSimpleFieldPath);

	    List<TestCaseUnit> targetSumedTestDataList = this.findCrorrespondingTestCaseUnit(sumedSimpleField, targetSumSimpleFieldPath);

	    TestCaseUnit testCaseUnit = sumedSimpleField.getTestData().getGeneratingTestCaseUnit();

	    Object queryedValue = testCaseUnit.getFieldValue(queryBy);

	    BigDecimal rtnBigDecimal = new BigDecimal(0);

	    String queryStr = "/" + targetSimpleField.getTestData().getName() + "[" + targetQyeryFieldName + "=" + "'" + queryedValue.toString() + "']";

	    List<TestCaseUnit> rtnTestCaseUnit = new ArrayList<TestCaseUnit>();

	    for(TestCaseUnit tmpTestCaseUnit : targetSumedTestDataList){
	        List tmpQueryResult = ClassUtil.search(tmpTestCaseUnit.getTestCaseInstance(), queryStr);

	        if(tmpQueryResult != null && tmpQueryResult.size()>0){

				BigDecimal tmpBig = new BigDecimal( tmpTestCaseUnit.getFieldValue(targetSimpleField.getName()).toString());
				rtnBigDecimal = rtnBigDecimal.add(tmpBig);
            }
	    }

	    return rtnBigDecimal.doubleValue();
	}

	public Double sumFilterCurInstalmentInfo(String simpleFieldPath, String targetSumSimpleFieldPath, String queryBy, String targetQyeryFieldName){

		SimpleField sumedSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(simpleFieldPath);

		SimpleField targetSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(targetSumSimpleFieldPath);

		List<TestCaseUnit> targetSumedTestDataList = this.findCrorrespondingTestCaseUnit(sumedSimpleField, targetSumSimpleFieldPath);

		TestCaseUnit testCaseUnit = sumedSimpleField.getTestData().getGeneratingTestCaseUnit();

		Object queryedValue = testCaseUnit.getFieldValue(queryBy);

		BigDecimal rtnBigDecimal = new BigDecimal(0);

		String queryStr = "/" + targetSimpleField.getTestData().getName() + "[" + targetQyeryFieldName + "=" + "'" + queryedValue.toString() + "']";

		List<TestCaseUnit> rtnTestCaseUnit = new ArrayList<TestCaseUnit>();

		for(TestCaseUnit tmpTestCaseUnit : targetSumedTestDataList){

			if(tmpTestCaseUnit.getParentTestCaseUnit().getPositionInParent() != 0){
				List tmpQueryResult = ClassUtil.search(tmpTestCaseUnit.getTestCaseInstance(), queryStr);

				if(tmpQueryResult != null && tmpQueryResult.size()>0){
					BigDecimal tmpBig = new BigDecimal( tmpTestCaseUnit.getFieldValue(targetSimpleField.getName()).toString());
					rtnBigDecimal = rtnBigDecimal.add(tmpBig);
				}

			}
		}

		return rtnBigDecimal.doubleValue();
	}

	public Double getNewInstalmentTotalAmt(String simpleFieldPath, String targetSumSimpleFieldPath, String queryBy, String targetQyeryFieldName){

		SimpleField sumedSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(simpleFieldPath);

		SimpleField targetSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(targetSumSimpleFieldPath);

		List<TestCaseUnit> targetSumedTestDataList = this.findCrorrespondingTestCaseUnit(sumedSimpleField, targetSumSimpleFieldPath);

		TestCaseUnit testCaseUnit = sumedSimpleField.getTestData().getGeneratingTestCaseUnit();

		Object queryedValue = testCaseUnit.getFieldValue(queryBy);

		BigDecimal rtnBigDecimal = new BigDecimal(0);

		String queryStr = "/" + targetSimpleField.getTestData().getName() + "[" + targetQyeryFieldName + "=" + "'" + queryedValue.toString() + "' and currentInstalmentPeriod=1]";

		List<TestCaseUnit> rtnTestCaseUnit = new ArrayList<TestCaseUnit>();

		for(TestCaseUnit tmpTestCaseUnit : targetSumedTestDataList){
			List tmpQueryResult = ClassUtil.search(tmpTestCaseUnit.getTestCaseInstance(), queryStr);

			if(tmpQueryResult != null && tmpQueryResult.size()>0){

				BigDecimal tmpBig = new BigDecimal( tmpTestCaseUnit.getFieldValue(targetSimpleField.getName()).toString());
				rtnBigDecimal = rtnBigDecimal.add(tmpBig);
			}
		}

		return rtnBigDecimal.doubleValue();
	}


	public Double getNewInstalmentMaxPeriod(String simpleFieldPath, String targetSumSimpleFieldPath, String queryBy, String targetQyeryFieldName){

		SimpleField sumedSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(simpleFieldPath);

		SimpleField targetSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(targetSumSimpleFieldPath);

		List<TestCaseUnit> targetSumedTestDataList = this.findCrorrespondingTestCaseUnit(sumedSimpleField, targetSumSimpleFieldPath);

		TestCaseUnit testCaseUnit = sumedSimpleField.getTestData().getGeneratingTestCaseUnit();

		Object queryedValue = testCaseUnit.getFieldValue(queryBy);

		Double rtnDouble = new Double(0);

		String queryStr = "/" + targetSimpleField.getTestData().getName() + "[" + targetQyeryFieldName + "=" + "'" + queryedValue.toString() + "']";

		List<TestCaseUnit> rtnTestCaseUnit = new ArrayList<TestCaseUnit>();

		for(TestCaseUnit tmpTestCaseUnit : targetSumedTestDataList){
			List tmpQueryResult = ClassUtil.search(tmpTestCaseUnit.getTestCaseInstance(), queryStr);

			if(tmpQueryResult != null && tmpQueryResult.size()>0){

				Double tmpDouble = new Double( tmpTestCaseUnit.getFieldValue(targetSimpleField.getName()).toString());

				if(rtnDouble < tmpDouble){
					rtnDouble = tmpDouble;
				}
			}
		}

		return rtnDouble.doubleValue();
	}

	public Double getCurInstalmentMaxPeriod(String simpleFieldPath, String targetSumSimpleFieldPath, String queryBy, String targetQyeryFieldName){

		SimpleField sumedSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(simpleFieldPath);

		SimpleField targetSimpleField = this.bomGenerator.getPathSimpleFieldMap().get(targetSumSimpleFieldPath);

		List<TestCaseUnit> targetSumedTestDataList = this.findCrorrespondingTestCaseUnit(sumedSimpleField, targetSumSimpleFieldPath);

		TestCaseUnit testCaseUnit = sumedSimpleField.getTestData().getGeneratingTestCaseUnit();

		Object queryedValue = testCaseUnit.getFieldValue(queryBy);

		Double rtnDouble = new Double(0);

		String queryStr = "/" + targetSimpleField.getTestData().getName() + "[" + targetQyeryFieldName + "=" + "'" + queryedValue.toString() + "']";

		List<TestCaseUnit> rtnTestCaseUnit = new ArrayList<TestCaseUnit>();

		for(TestCaseUnit tmpTestCaseUnit : targetSumedTestDataList){

			if(tmpTestCaseUnit.getParentTestCaseUnit().getPositionInParent() == 0){
				List tmpQueryResult = ClassUtil.search(tmpTestCaseUnit.getTestCaseInstance(), queryStr);

				if(tmpQueryResult != null && tmpQueryResult.size()>0){

					Double tmpDouble = new Double( tmpTestCaseUnit.getFieldValue(targetSimpleField.getName()).toString());

					if(rtnDouble < tmpDouble){
						rtnDouble = tmpDouble;
					}
				}
			}
		}

		return rtnDouble.doubleValue();
	}



	public Object sum(String query, String fieldName){
		List queryedList = ClassUtil.search(this.bomGenerator.getRootTestData().getTestCaseUnitList().get(0).getTestCaseInstance(), query);

		Double sumD = new Double(0);

		for(Object queryObj : queryedList){

			Class queryObjCls = queryObj.getClass();

			try {
				Field field = queryObjCls.getDeclaredField(fieldName);

				field.setAccessible(true);

				Object doubleObj = field.get(queryObj);

				if(doubleObj != null){
					if(doubleObj instanceof Date){
						Long dateMilsec = ((Date)doubleObj).getTime();
						sumD += new Double(doubleObj.toString());
					}
					else{
						sumD += new Double(doubleObj.toString());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return sumD;
	}

	public Object setSum(String query, String fieldName){
		List queryedList = ClassUtil.search(this.bomGenerator.getRootTestData().getTestCaseUnitList().get(0).getTestCaseInstance(), query);

		Double sumD = new Double(0);

		Set<String> tmpSet = new HashSet<String>();

		for(Object queryObj : queryedList){

			Class queryObjCls = queryObj.getClass();

			try {
				Field field = queryObjCls.getDeclaredField(fieldName);

				field.setAccessible(true);

				Object doubleObj = field.get(queryObj);

				if(doubleObj != null){

					tmpSet.add(doubleObj.toString());

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return tmpSet.size();
	}

	//added by kangchangcun at 2017/12/02
	public Object left(String srcStr, Double doubleLen){
		int intLen = doubleLen.intValue();
		String Str = srcStr.substring(0,intLen);
		return Str.replace('N','0');
	}
//end added by kangchangcun at 2017/12/02

	public Object getstateEndMonth(Object stateEndDate){

		Date stateEndDateD = (Date)stateEndDate;

		return new SimpleDateFormat("yyyy.MM").format(stateEndDateD);

	}

	public Object NowDate(){
		return new Date();
	}

	public Object getBeginMonth(Object stateEndDate){
		return getstateEndMonth( stateEndDate);
	}

	public Object getLatest24State(){

		StringBuffer tmp = new StringBuffer("0");

		boolean isOverDue = false;

		int i = 0;

		for(; i<24; i++){

			int randomInt = RandomFactory.randomIntBetween(1,100);

			if( ! isOverDue ){
				if(randomInt <= percentMoreThan1){
					tmp.append("1");
					isOverDue = true;
				}
				else if(randomInt < percentZero + percentMoreThan1){
					tmp.append("0");
				}else{
					tmp.append("N");
				}
			}else{
				int newDueNum = new Integer( tmp.substring(tmp.length()-1) ) + 1;

				if(randomInt < overDueUpLevel){
					tmp.append( newDueNum );
				}else{
					int nextDueNum = RandomFactory.randomIntBetween(0,newDueNum);
					tmp.append( nextDueNum );
					if(newDueNum == 0){
						isOverDue = false;
					}
				}
			}
		}
		return tmp.toString();
	}

	/**
	 *
	 * @param targetInteger
	 * @param numberLen 整数，取整位数；负数：小数
	 * @param upDownFlag 0:向上取整，1：向下取整；2：四舍五入
	 * @return
	 */
	public Object floor(Double targetInteger, Double numberLen, Double upDownFlag){
		if(numberLen >0){
			Double tmpTargetInteger = targetInteger / Math.pow(10,numberLen) ;

			if(upDownFlag == 0){
				return Math.ceil( tmpTargetInteger ) * Math.pow(10,numberLen);
			}
			else if(upDownFlag == 1){
				return Math.floor( Math.ceil( tmpTargetInteger ) * Math.pow(10,numberLen) );
			}
			else if( upDownFlag == 2 ){
				return Math.rint( Math.ceil( tmpTargetInteger ) * Math.pow(10,numberLen) );
			}
		}
		return null;
	}

	public Double min(Object minVal, Object maxVal){

		if(minVal == null || maxVal == null){
			return null;
		}

		Double minValDouble = null;

		if(minVal instanceof Date){
			minValDouble = new Double( ((Date)minVal).getTime() );
		}else{
			minValDouble = new Double(minVal.toString());
		}

		Double maxValDouble = null;

		if(minVal instanceof Date){
			maxValDouble = new Double( ((Date)maxVal).getTime() );
		}else{
			maxValDouble = new Double(maxVal.toString());
		}

		return Math.min(minValDouble, maxValDouble);
	}


	public Double max(Object minVal, Object maxVal){

		Double minValDouble = new Double(minVal.toString());

		Double maxValDouble = new Double(maxVal.toString());

		return Math.max(minValDouble, maxValDouble);
	}

	private int guid = 100;

	public String generateApplicationID() {

		guid += 1;

		long now = System.currentTimeMillis();
		//获取4位年份数字
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy");
		//获取时间戳
		String time=dateFormat.format(now);
		String info=now+"";
		//获取三位随机数
		//int ran=(int) ((Math.random()*9+1)*100);
		//要是一段时间内的数据连过大会有重复的情况，所以做以下修改
		int ran=0;
		if(guid>999){
			guid=100;
		}
		ran=guid;

		return time+info+ran;

	}

	/**
	 * 广州银行120期逾期记录生成，目前只能用函数搞
	 * @param mob
	 * @return
	 */
	//滚动率
	private static final double overDueUpLevel = 15;
	//N的比率
	private static final double percentN = 50;
	//0的比率
	private static final double percentZero = 45;
	//大于等于1的比率
	private static final double percentMoreThan1 = 5;

	public Object getEStmtOdue120(Integer mob){

		StringBuffer tmp = new StringBuffer("0");

		boolean isOverDue = false;

		int i = 0;

		for(; i<mob-1; i++){

			int randomInt = RandomFactory.randomIntBetween(1,100);

			if( ! isOverDue ){
				if(randomInt <= percentMoreThan1){
					tmp.append("1");
					isOverDue = true;
				}
				else if(randomInt < percentZero + percentMoreThan1){
					tmp.append("0");
				}else{
					tmp.append("N");
				}
			}else{
				int newDueNum = new Integer( tmp.substring(tmp.length()-1) ) + 1;

				if(randomInt < overDueUpLevel){
					tmp.append( newDueNum );
				}else{
					int nextDueNum = RandomFactory.randomIntBetween(0,newDueNum);
					tmp.append( nextDueNum );
					if(newDueNum == 0){
						isOverDue = false;
					}
				}
			}
		}
		return tmp.reverse().toString();
	}

	private Object getLastRepaymentDate(){
		return "1";
	}

	public Object getDelinquentCycle(String due123, Object relativeCycleNum){
		Integer relativeCycleNumInt = new Integer( new Double(relativeCycleNum.toString()).intValue() );

		String convertedDue123Str = due123.replace('N','0');

		if(convertedDue123Str.length() < relativeCycleNumInt){
			return "";
		}

//modified by kangchangcun at 20171202
		return Character.toString(convertedDue123Str.charAt(relativeCycleNumInt-1));
//end  modified by kangchangcun at 20171202
	}

	public Object getDayNumber(String due123, Object cycleNumber, Object businessDateObj){

		Date businessDate = (Date)businessDateObj;

		Calendar businessCal=Calendar.getInstance();
		businessCal.setTime(businessDate);

		int dayOfBusinessDateMonth = businessCal.getActualMaximum( Calendar.DATE );

		Integer cycleNumberDouble = new Integer( new Double( cycleNumber.toString() ).intValue() );

		Integer rtn = null;
//modified by kangchangcun at 20171202

		if(due123.charAt( cycleNumberDouble-1 ) == 'N' ){
			rtn = 0;
		}else if( due123.charAt( cycleNumberDouble-1 ) != '0'){
			rtn = 30;
		}
		else {
			rtn = RandomFactory.randomIntBetween( 1, dayOfBusinessDateMonth );
		}
		//end  modified by kangchangcun at 20171202
		return rtn;
	}



	public Object getLastCycleDay(Object businessDateObj, Object cycleDayObj){

		Date businessDate = (Date)businessDateObj;

		Integer cycleDay = (Integer)cycleDayObj;

		Calendar calendar=Calendar.getInstance();

		calendar.setTime( businessDate );

		Integer calDay = calendar.get(Calendar.DAY_OF_MONTH);

		Long rtnTime = -1l;

		if(cycleDay > calDay){
			Calendar lastMonthCalendar=Calendar.getInstance();

			lastMonthCalendar.setTime( businessDate );

			lastMonthCalendar.add(Calendar.MONTH, -1);

			lastMonthCalendar.add(Calendar.DAY_OF_YEAR,  cycleDay - calDay);

			rtnTime = RandomFactory.randomLongBetween(lastMonthCalendar.getTimeInMillis(), businessDate.getTime());
		}else{
			Calendar cycleCalendar=Calendar.getInstance();

			cycleCalendar.setTime(businessDate);

			cycleCalendar.add(Calendar.DAY_OF_YEAR, cycleDay - calDay);

			rtnTime = RandomFactory.randomLongBetween(cycleCalendar.getTimeInMillis(), businessDate.getTime());

		}

		return new Date(rtnTime);
	}

	public Object getLastHistoryCycleDay(Object businessDateObj, Object cycleObj, Object cycleNumberObj){
		Date businessDate = (Date)businessDateObj;

		if(cycleNumberObj == null){
			return null;
		}

		Integer cycleNumber = new BigDecimal(cycleNumberObj.toString()).intValue();

		Calendar calendar=Calendar.getInstance();

		calendar.setTime( businessDate );

		Integer calDay = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.add(Calendar.MONTH, cycleNumber * -1);

		calendar.set(Calendar.DAY_OF_MONTH, (Integer)cycleObj);

		Long thisMonthMilSec = calendar.getTimeInMillis();

		calendar.add(Calendar.MONTH,  -1);

		Long lastMonthMilSec = calendar.getTimeInMillis();

		return new Date( RandomFactory.randomLongBetween(lastMonthMilSec, thisMonthMilSec));
	}

	public Object getStmtOdue120(String eStmtOdue120){

		char[] tmpChar = new char[eStmtOdue120.length()] ;

		for(int i=0; i<tmpChar.length; i++) {

			double randomDouble = RandomFactory.randomDoubleBetween(0, 1);
//modified by kangchangcun at 20171202
//			if (i == 0) {
//				if (randomDouble >= 0 || randomDouble < 0.9) {
//					tmpChar[i] = '0';
//				} else if (randomDouble >= 0.9 || randomDouble < 0.99) {
//					tmpChar[i] = 'A';
//				} else if (randomDouble >= 0.992 || randomDouble < 0.994) {
//					tmpChar[i] = 'B';
//				} else if (randomDouble >= 0.994 || randomDouble < 0.996) {
//					tmpChar[i] = 'C';
//				} else if (randomDouble >= 0.996 || randomDouble < 1) {
//					tmpChar[i] = 'D';
//				}
//				continue;
//			}

			if (eStmtOdue120.charAt(i) == 'N') {
			tmpChar[i] = eStmtOdue120.charAt(i);
		} else {
			if (randomDouble >= 0 || randomDouble < 0.25) {
				tmpChar[i] = 'A';
			} else if (randomDouble >= 0.25 || randomDouble < 0.5) {
				tmpChar[i] = 'B';
			} else if (randomDouble >= 0.5 || randomDouble < 0.75) {
				tmpChar[i] = 'C';
			} else if (randomDouble >= 0.75 || randomDouble < 1) {
				tmpChar[i] = 'D';
			}
		}
	}
//end modified by kangchangcun at 20171202
			return new String(tmpChar);
	}


	public Object getMthsOdueAmt(String due123, Object relativeCycleNum,  Object balance){

		Double balanceDouble = new Double(balance.toString());

		Integer relativeCycleNumInt = new Integer( new Double(relativeCycleNum.toString()).intValue() );

		String convertedDue123Str = due123.replace('N','0');
//modified by kangchangcun at 20171202
		char currentMonthChr = convertedDue123Str.charAt(relativeCycleNumInt-1);
//end modified by kangchangcun at 20171202
		if(currentMonthChr == '0'){
			return new Double(0);
		}
		else {
			return new Double( RandomFactory.randomDoubleBetween(0d, balanceDouble) );
		}
	}

	public String generateInternalInfoApplicationType(
			AbstractTestData parentTest, AbstractTestData currentTetsData) {

		SimpleField sf = (SimpleField) currentTetsData;

		String rtn = null;

		if (sf.getTestCase().size() == 0) {
			rtn = "1";
		} else {
			rtn = "2";
		}

		return rtn;
	}

	public Object random(Object minDouble, Object maxDouble){

		Double minD = new Double(minDouble.toString());

		Double maxD = new Double(maxDouble.toString());

		return RandomFactory.randomDoubleBetween(minD, maxD);
	}

	public Object rightLast24States(String srcStr){
		String lastChar = srcStr.substring(srcStr.length() - 1);

		Pattern p = Pattern.compile(TestCaseExpression.FIND_PURE_NUMBERIC_PATH_EXP);

		Matcher m = p.matcher( lastChar );

		if(!m.matches()){
			return "0";
		}else{
			return lastChar;
		}
	}
//
//	public String getLatest24State(AbstractTestData parentTest,
//			AbstractTestData currentTetsData) {
//		String rtn = "";
//
//		for (int i = 0; i < 24; i++) {
//			if (RandomFactory.randomDoubleBetween(1, 100) < 0.5) {
//				rtn += "1";
//			} else {
//				rtn += "0";
//			}
//		}
//		return rtn;
//	}
//


	private Map<Integer, String> nodeInforNodeIDPosIDMap = new ConcurrentHashMap<Integer, String>();

	{
		nodeInforNodeIDPosIDMap.put(0, "N000000");
		nodeInforNodeIDPosIDMap.put(1, "N010000");
		nodeInforNodeIDPosIDMap.put(2, "N020000");
		nodeInforNodeIDPosIDMap.put(3, "N030000");
		nodeInforNodeIDPosIDMap.put(4, "N040000");
		nodeInforNodeIDPosIDMap.put(5, "N050000");
		nodeInforNodeIDPosIDMap.put(6, "N060000");
		nodeInforNodeIDPosIDMap.put(7, "N060100");
		nodeInforNodeIDPosIDMap.put(8, "N070000");
		nodeInforNodeIDPosIDMap.put(9, "N080000");
		nodeInforNodeIDPosIDMap.put(10, "N090000");
		nodeInforNodeIDPosIDMap.put(11, "N100000");
		nodeInforNodeIDPosIDMap.put(12, "N110000");
		nodeInforNodeIDPosIDMap.put(13, "N120000");
		nodeInforNodeIDPosIDMap.put(14, "N123000");
		nodeInforNodeIDPosIDMap.put(15, "N127000");
		nodeInforNodeIDPosIDMap.put(16, "N130000");
		nodeInforNodeIDPosIDMap.put(17, "N140000");
		nodeInforNodeIDPosIDMap.put(18, "N140001");
		nodeInforNodeIDPosIDMap.put(19, "N150000");
		nodeInforNodeIDPosIDMap.put(20, "N160000");
		nodeInforNodeIDPosIDMap.put(21, "N170000");
		nodeInforNodeIDPosIDMap.put(22, "N170001");
		nodeInforNodeIDPosIDMap.put(23, "N180000");
		nodeInforNodeIDPosIDMap.put(24, "N500000");
		nodeInforNodeIDPosIDMap.put(25, "N550000");
		nodeInforNodeIDPosIDMap.put(26, "N600000");
		nodeInforNodeIDPosIDMap.put(27, "N700000");
		nodeInforNodeIDPosIDMap.put(28, "N800000");
		nodeInforNodeIDPosIDMap.put(29, "N980000");
		nodeInforNodeIDPosIDMap.put(30, "N990000");
	}

	private Map<Integer, String> nodeInforNodeTypeMap = new ConcurrentHashMap<Integer, String>();

	{
		nodeInforNodeTypeMap.put(17, "1");
		nodeInforNodeTypeMap.put(18, "1");
		nodeInforNodeTypeMap.put(21, "1");
		nodeInforNodeTypeMap.put(22, "1");
		nodeInforNodeTypeMap.put(28, "1");
		nodeInforNodeTypeMap.put(29, "1");
	}

	private int nodeInfoNodeIDCnt = 0;

	private int nodeInfoNodeTypeCnt = 0;

	public String generateNodeInfoNodeIDElement(AbstractTestData parentTest,
			AbstractTestData currentTetsData) {

		String rtn = nodeInforNodeIDPosIDMap.get(nodeInfoNodeIDCnt % 31);

		nodeInfoNodeIDCnt++;

		return rtn;

	}

	public Integer getAge(Object applicationDateObj, Object birthDateObj){
		Date applicationDate = (Date)applicationDateObj;
		Date birthDate = (Date)birthDateObj;

		return new Integer( applicationDate.getYear() - birthDate.getYear() );
	}

	private static String randomCharAll = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public Object randomChar(Object lenObj){
		Integer len = new Double(lenObj.toString()).intValue();

		StringBuffer sb = new StringBuffer();

		for(int i=0; i<len; i++){
			int random = RandomFactory.randomIntBetween(0, randomCharAll.length()-1);
			sb.append( randomCharAll.charAt(random) );
		}

		return sb.toString();
	}

	public String generateNodeInfoNodeTypeElement(AbstractTestData parentTest,
			AbstractTestData currentTetsData) {

		String rtn = nodeInforNodeTypeMap.get(nodeInfoNodeTypeCnt % 31);

		nodeInfoNodeTypeCnt++;

		if (rtn == null) {
			return "0";
		}

		return rtn;
	}

	public boolean findCusteomFunctionByName(String functionName) {
		Method[] mArr = CustomFunctionFactory.class.getMethods();

		for (Method method : mArr) {
			if (method.getName().equals(functionName)) {
				return true;
			}
		}
		return false;
	}

	public void manageCreditSummaryCueHouseLoanCount(
			AbstractTestData parentTest, AbstractTestData currentTetsData) {

	}

	public void handleStateEndDate(AbstractTestData parentTest,
			AbstractTestData currentTetsData) {
		SimpleField stateEndDate = (SimpleField) currentTetsData;

		TestData loanTestData = stateEndDate.getTestData();
	}

	private int getMonthItemMonthCnt = 0;
	// add by kcc at 20170717
	public String getMonthItemMonth(AbstractTestData parentTest,
			AbstractTestData currentTetsData) throws Exception {

		SimpleField simpleField = (SimpleField)parentTest;
		
		String dateStr = (String) simpleField.getTestCase().get(0);
		
		SimpleDateFormat dateTimeTypeDataFormat = new SimpleDateFormat(TestCaseUtils.DATE_TIME_FORMAT);

		Date monthItemBaseDate = dateTimeTypeDataFormat.parse(dateStr);
		
		Calendar calendar=Calendar.getInstance();  
		
		calendar.setTime( monthItemBaseDate ); 
		
		calendar.add(calendar.MONTH, getMonthItemMonthCnt*-1);
		
		getMonthItemMonthCnt ++;
		
		getMonthItemMonthCnt = getMonthItemMonthCnt%6;
		
		SimpleDateFormat rtnDateTimeTypeDataFormat = new SimpleDateFormat("yyyyMM");
		
		return rtnDateTimeTypeDataFormat.format( calendar.getTime() );
	}
	// end by kcc at 20170717
	//added by kcc at 20170719
	
	public String getIDNumber(AbstractTestData parentTest, AbstractTestData currentTetsData) {

		String rtn = null;
		
		rtn = g.generate();
		return rtn;
	}
}
