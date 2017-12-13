package com.fico.testCaseGenerator.CustomFunctionFactory;

import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.fico.testCaseGenerator.BOM.BOMGenerator;
import com.fico.testCaseGenerator.data.AbstractTestData;
import com.fico.testCaseGenerator.data.SimpleField;
import com.fico.testCaseGenerator.data.TestData;
import com.fico.testCaseGenerator.testCase.TestCaseGenerator;
import com.fico.testCaseGenerator.util.RandomFactory;
import com.fico.testCaseGenerator.util.TestCaseUtils;

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

	public Object testDataSize( String absTestDataPath ){
		AbstractTestData targetAbsTestData = null;
		if(this.bomGenerator.pathIsSimpleField(absTestDataPath) ){
			targetAbsTestData = this.bomGenerator.getPathSimpleFieldMap().get(absTestDataPath);
		}else {
			targetAbsTestData = this.bomGenerator.getPathTestDataMap().get(absTestDataPath);
		}

		return targetAbsTestData.getTestCase().size();
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

		Double minValDouble = new Double(minVal.toString());

		Double maxValDouble = new Double(maxVal.toString());

		return Math.min(minValDouble, maxValDouble);
	}

	public Double max(Object minVal, Object maxVal){

		Double minValDouble = new Double(minVal.toString());

		Double maxValDouble = new Double(maxVal.toString());

		return Math.max(minValDouble, maxValDouble);
	}


	public String generateApplicationID() {
		return java.util.UUID.randomUUID().toString();
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

	/**
	 * 中间节点合并函数
	 * @param testData
	 * @param testDataPath
	 * @return
	 */
	public Object merge(Object parentTestDataIns, TestData testData, String[] testDataPath){

		this.testCaseGenerator.merge(parentTestDataIns, testData, testDataPath);

		return null;
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

	public Object right(String srcStr, Double doubleLen){
		int intLen = doubleLen.intValue();

		return srcStr.substring(srcStr.length() - intLen);
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

		List loanIns = loanTestData.getTestCase();

		if (loanIns != null) {

		}
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
	//end by kcc at 20170719
	
}
