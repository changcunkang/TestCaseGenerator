package com.fico.testCaseGenerator.expression;

import com.fico.testCaseGenerator.BOM.BOMGenerator;
import com.fico.testCaseGenerator.CustomFunctionFactory.CustomFunctionFactory;
import com.fico.testCaseGenerator.data.AbstractTestData;
import com.fico.testCaseGenerator.data.SimpleField;
import com.fico.testCaseGenerator.data.TestCaseUnit;
import com.fico.testCaseGenerator.data.TestData;
import com.fico.testCaseGenerator.data.configuration.Restriction;
import com.fico.testCaseGenerator.testCase.TestCaseGenerator;
import com.fico.testCaseGenerator.util.RandomFactory;
import com.fico.testCaseGenerator.util.TestCaseUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 11/15/2017
 *
 *
 * 以后考虑Partern的现场安全问题可以提高效率
 *
 */
public class TestCaseExpression {

    private TestCaseGenerator testCaseGenerator = null;

    private BOMGenerator bomGenerator;

    private static final int TYPE_CURRENT = 0;

    private static final int TYPE_SIZE = 1;

    private static final int TYPE_LIST = 0;

    private static final int STATE_PROJECT_SIMPLEFIELD_PATH = 0;

    private static final int STATE_CALCULATE = 0;

    private static final int STATE_LOGIC = 0;

    private static final int STATE_FUNCTION = 0;

    //public static final String FIND_ALL_PATH_EXP = "(\\/)?Application(\\/@?[_a-zA-Z]{1,}[a-zA-Z0-9]{1,})*\\/";

    public static final String FIND_ALL_PATH_EXP = "Application(\\/@?[_a-zA-Z]{1,}[a-zA-Z0-9]{1,})*\\/";

    public static final String FIND_PURE_NUMBERIC_PATH_EXP = "^(-?\\d+)(\\.+\\d+)?$";

    private static final String FIND_PURE_INTEGER_PATH_EXP = "^\\d?$";

    private static final String FIND_CALCULATION_PATH_EXP = "(^\\+|-|\\*|\\/).*";

    private static final String FUNCTION_SIMBOL_BEGIN = "$";

    private static final String FUNCTION_LEFT_BRACKETS = "(";

    private static final String FUNCTION_RIGHT_BRACKETS = ")";

    private static final String IT_RECURSIVE_FUNCTION_NAME = "IT";

    private static final String STRING_NULL = "null";

    public static final String MERGE_FUNCTION_NAME = "merge";

    public static final String MERGE_FILTER_FUNCTION_NAME = "mergeFilter";

    public static final String TESTDATA_SIZE_FUNCTION_NAME = "testDataSize";

    public static final String TESTDATASIMPLEFIELDSETSIZE = "testDataSimpleFieldSetSize";

    public static final String TEST_DATA_SIMPLEFIELDSET_TYPE = "testDataSimpleFieldSetType";

    public static final String MAX_SIZE_FUNCTION_NAME = "maxSimpleField";

    public static final String MIN_SIZE_FUNCTION_NAME = "minSimpleField";

    public static final String SUM_SIZE_FUNCTION_NAME = "sum";

    public static final String SETSUM_SIZE_FUNCTION_NAME = "setSum";

    private static List<String> ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST = new ArrayList<String>();

    static {

        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add(TESTDATA_SIZE_FUNCTION_NAME);
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add(MERGE_FUNCTION_NAME);
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add(MAX_SIZE_FUNCTION_NAME);
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add(MIN_SIZE_FUNCTION_NAME);
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add(SUM_SIZE_FUNCTION_NAME);
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add(SETSUM_SIZE_FUNCTION_NAME);
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("maxFilter");
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("minFilter");
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("avgFilter");
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("enumuationFill");
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("getLastMonthlyRecordInfoNonEndInstalmentNum");
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("getLastMonthlyRecordInfoNonEndInstalmentInstalmentID");
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("getLastMonthlyRecordInfoNonEndInstalmentInstalmentType");
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add(TESTDATASIMPLEFIELDSETSIZE);
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("testDataSimpleFieldSetType");
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("sumFilter");
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("getNewInstalmentTotalAmt");
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("sumFilterCurInstalmentInfo");
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("getNewInstalmentMaxPeriod");
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("getCurInstalmentMaxPeriod");
        ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.add("getLastTransDate");

    }

    private CustomFunctionFactory customFunctionFactory = null;

    public TestCaseExpression(TestCaseGenerator testCaseGenerator){
        this.bomGenerator = testCaseGenerator.getBomGenerator();
        this.customFunctionFactory = new CustomFunctionFactory(testCaseGenerator);
    }

    /**
     * 如果配置中有主从依赖关系，需要先判断所有的主都已经建立好以后，再调用获得返回值
     * @param restriction
     * @return
     */
    public Object parse(Restriction restriction){

        double nullPercent = restriction.getNullPercentage();

        double nullPercentHit = RandomFactory.random();

        if( nullPercentHit >= nullPercent ){

            double notNullPercentHit = RandomFactory.random();

            double minPercent = 0;

            double maxPercent = 0;

            for(int i=0; i<restriction.getItem().size(); i++) {

                minPercent = maxPercent;

                maxPercent += restriction.getItem().get(i).getPercentage()/100;

                //区间的边界值界定
                if(notNullPercentHit> minPercent && notNullPercentHit<= maxPercent){

                    String minExp = restriction.getItem().get(i).getMinExpression().replace(" ", "").trim();

                    String maxExp = restriction.getItem().get(i).getMaxExpression().replace(" ", "").trim();

                    if(minExp.equals(maxExp) || maxExp == null || "~".equals(maxExp)){

                        if("$getNewInstalmentMaxPeriod(Application/Customer/Product/Account/MonthlyRecordInfo/InstalmentInfo/@maxInstalTerms/,Application/Customer/Product/Account/MonthlyRecordInfo/InstalmentDetail/@currentInstalmentPeriod/, instalsType, instalmentType)$".equals(maxExp)){
                            String a = "";
                        }

                        Object rtnVal = this.recursiveParse(null, minExp, restriction );

                        return rtnVal;
                    }
                    else{

                        if("[Application/Customer/PbocReport/Loan/@currOverdueCyc/,>,0,Application/Customer/PbocReport/Loan/@scheduledPaymentAmount/]".equalsIgnoreCase(minExp)){
                            String a = "";
                        }

                        Object minExpRtnVal = this.recursiveParse(null, minExp, restriction );

                        Object maxExpRtnVal = this.recursiveParse(null, maxExp, restriction );

                        return generateRandomBetweetnMaxValueAndMinValue(minExpRtnVal, maxExpRtnVal, restriction);
                    }
                }
            }
        }
        return null;
    }

    private Object generateRandomBetweetnMaxValueAndMinValue(Object minValue, Object maxValue, Restriction restriction){
        AbstractTestData abstractTestData = restriction.getExtendtion().getParentTestData();

        if(minValue == null || maxValue == null){
            String a = "";
            return null;
        }

        if(abstractTestData instanceof SimpleField){
            SimpleField simpleField = (SimpleField)abstractTestData;
            int simpleFieldType = simpleField.getFieldType();
            switch (simpleFieldType){
                case SimpleField.TYPE_INT :
                    return new Integer( RandomFactory.randomIntBetween( convertObjectValToInteger(minValue), convertObjectValToInteger(maxValue) ) );

                case SimpleField.TYPE_REAL:
                    return new Double( RandomFactory.randomDoubleBetween( convertObjectValToInteger(minValue), convertObjectValToInteger(maxValue) ) );

                case SimpleField.TYPE_DATE:

                    try {

                        Long randomMinDateInt = null;
                        Long randomMaxDateInt = null;
                        if(minValue instanceof String && maxValue instanceof String){
                            randomMinDateInt = new SimpleDateFormat(TestCaseUtils.DATE_FORMAT).parse(minValue.toString()).getTime();
                            randomMaxDateInt = new SimpleDateFormat(TestCaseUtils.DATE_FORMAT).parse(maxValue.toString()).getTime();
                        }
                        else if(minValue instanceof Date && maxValue instanceof Date){
                            randomMinDateInt = ((Date)minValue).getTime();
                            randomMaxDateInt = ((Date)maxValue).getTime();
                        }

                        if(randomMinDateInt==null || randomMaxDateInt==null){
                            String a = "";
                            return null;
                        }

                        long randomBetweenInt = RandomFactory.randomLongBetween(randomMinDateInt, randomMaxDateInt);
                        return new Date(randomBetweenInt);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
            }
        }else if(abstractTestData instanceof TestData){
            return new Integer( RandomFactory.randomIntBetween( new Double(minValue.toString()).intValue(), new Double(maxValue.toString()).intValue() ) ) ;
        }
        return null;
    }

    private Integer convertObjectValToInteger(Object val){
        if(val instanceof String){

            if(val.toString().equalsIgnoreCase("")){
                String a = "";
            }


            return new Integer ( new Double(val.toString()).intValue() );
        }else if(val instanceof Double){
            return new Integer ( ((Double)val).intValue() );
        }else if( val instanceof BigDecimal){
            return ((BigDecimal)val).intValue();
        }
        return (Integer)val;
    }

    private Object recursiveParse(Object tempValue, String path, Restriction restriction){

        if(path.equals("Application/Customer/Product/Account/MonthlyRecordInfo/InstalmentDetail_New/@period/")){
            String a = "";
        }

        Object val = null;

        //纯数字
        if( (val = isExpPureNumberic(path, restriction) ) != null && tempValue == null){
            return val;
        }
        //表达式开始为主从关系依赖路径
        else if( this.isStateLocalTestData( path ) ){
            String firstPath = getExpFirstPath( path );
            Object elementVal = getAbsTestDataValueFromPath( firstPath, restriction );
            if( path.equals(firstPath) ){
                return elementVal;
            }else{
                return recursiveParse(elementVal, path.substring( firstPath.length() ), restriction);
            }
        }
        //计算公式开头
        else if( this.isCalculationOperationOperator(path) ){
            return parseCalculationOperationOperator(tempValue, path, restriction);
        }
        //函数
        else if( isFunctionInvocation(path) ){
            return parseFunctionParameter(path, restriction);
        }
        //Boolean表达式
        else if( isBooleanExp(path) ){
            return parseBooleanExp(path, restriction);
        }
        //纯文本
        else if( !STRING_NULL.equalsIgnoreCase(path) ){
            return path;
        }
        return null;
    }

    private Object parseCalculationOperationOperator(Object tempValue, String path, Restriction restriction){
        String operator = path.substring(0,1);

        String subPath = path.substring(1);

        Object leftValue = tempValue;

        if(tempValue == null){
            return null;
        }

        Object rightValue = this.recursiveParse(null, subPath, restriction);;

        if("+".equals(operator)){
            //日期加减
            //现只支持左边日期右边数字，左边数字右边日期暂时没考虑
            if(this.isSimpleFieldDateType(restriction, tempValue)){
                GregorianCalendar gc=new GregorianCalendar();
                if(tempValue == null){
                    String a = "";
                }
                gc.setTime( (Date)tempValue );
                gc.add(GregorianCalendar.DATE, new Double( rightValue.toString() ).intValue());
                return gc.getTime();
            }
            //正常数学运算
            BigDecimal leftBigDec = new BigDecimal(leftValue.toString());
            return leftBigDec.add( new BigDecimal( recursiveParse(null, subPath, restriction).toString() ) );

        }else if("-".equals(operator)) {
            //日期加减
            //现只支持左边日期右边数字，左边数字右边日期暂时没考虑
            if(this.isSimpleFieldDateType(restriction, tempValue)){
                GregorianCalendar gc=new GregorianCalendar();
                gc.setTime( (Date)tempValue );

                gc.add(GregorianCalendar.DATE, new Double( this.recursiveParse(null, rightValue.toString(), restriction).toString() ).intValue() * -1);
                return gc.getTime();
            }
            BigDecimal leftBigDec = new BigDecimal(leftValue.toString());
            if(recursiveParse(null, subPath, restriction)==null){
                String a = "";
            }

            return leftBigDec.subtract( new BigDecimal( recursiveParse(null, subPath, restriction).toString() ) );

        }else if ("*".equals(operator)){

            BigDecimal leftBigDec = new BigDecimal(leftValue.toString());
            return leftBigDec.multiply( new BigDecimal( recursiveParse(null, subPath, restriction).toString() ) );

        }else if ("/".equals(operator)){
            BigDecimal leftBigDec = new BigDecimal(leftValue.toString());
            return leftBigDec.divide( new BigDecimal( recursiveParse(null, subPath, restriction).toString() ), 5, BigDecimal.ROUND_DOWN );
        }

        return null;
    }

    private boolean isSimpleFieldDateType(Restriction restriction, Object leftValue){
        if(restriction.getExtendtion().getParentTestData() instanceof SimpleField ) {
            SimpleField simpleField = (SimpleField) restriction.getExtendtion().getParentTestData();

            if (simpleField.getFieldType() == SimpleField.TYPE_DATE || simpleField.getFieldType() == SimpleField.TYPE_DATETIME) {
                return true;

            }
        }
        if(leftValue == null){
            String a = "";
        }
        if(leftValue.getClass() == Date.class){
            return true;
        }
        return false;
    }

    /**
     *
     * @param exp
     * @param selfPath 排除path，自己依赖自己的情况去掉
     * @return
     */
    public boolean isAllElementReady(String exp, String selfPath){
        List<String> expIncludeAbsTestDataPath = getAllAbsTestData( exp );

        for(String path : expIncludeAbsTestDataPath){
            if ( !selfPath.equals(path) && ! this.bomGenerator.isAbsTestDataGenerateFinish(path) ){
                return false;
            }
        }
        return true;
    }

    //调用函数，判断应该在纯字符串之前
    private boolean isFunctionInvocation(String exp){

       if(exp.startsWith(FUNCTION_SIMBOL_BEGIN)){
           return true;
       }
        return false;
    }

    //判断是否为boolean表达式
    private boolean isBooleanExp(String path){
        return path.startsWith("[");
    }

    private Object parseBooleanExp(String path, Restriction restriction){
        int analyse = 0;
        int pos = 0;

        List<Integer> commaList = new ArrayList<Integer>();
        int i=0;

        for(i=0; i<path.length(); i++){
            char tmpChar = path.charAt(i);

            if(tmpChar == '[' || tmpChar == '('){
                analyse ++;
            }
            else if( tmpChar == ',' && analyse ==1 ){
                commaList.add(i);
            }
            else if( tmpChar == ']' || tmpChar == ')'){
                analyse -- ;
                if(analyse == 0){
                    break;
                }
            }
        }

        String[] splitArr = new String[ commaList.size()+1 ];
        String argStrInPath = path.substring(1,i);
        if (commaList.size() != 0){
            int tmpPosStart = 0;
            int j=0;
            for(j=0; j<commaList.size(); j++){
                int tmpCommaPos = commaList.get(j) -1 ;
                splitArr[j] = argStrInPath.substring(tmpPosStart, tmpCommaPos);
                tmpPosStart = tmpCommaPos + 1;
            }
            splitArr[j] = argStrInPath.substring( tmpPosStart );
        }

        Object booleanLeftVal = this.recursiveParse(null, splitArr[0], restriction);

        String operator = splitArr[1];

        Object booelanRightVal = this.recursiveParse(null, splitArr[2], restriction);

        boolean booleanOperatorResult = booleanOperatorResult(booleanLeftVal, booelanRightVal, operator );

        if(booleanOperatorResult){
            return this.recursiveParse(null, splitArr[3], restriction);
        }
        else if(splitArr .length ==5) {
            return this.recursiveParse(null, splitArr[4], restriction);
        }
        return "";
    }

    private boolean booleanOperatorResult(Object leftVal, Object rightVal, String operationExp){
        int compareRes = 0;

        Double leftNumberVal = null;
        Double rightNumberVal = null;

        if(leftVal == null || rightVal==null){

            if( !(leftVal == null && rightVal==null)){
                compareRes = 1;
            }

        }else{
            if(leftVal instanceof String){
                compareRes = leftVal.toString().compareTo(rightVal.toString());
            }else {
                if( leftVal instanceof Date ){
                    if(rightVal instanceof String){
                        try {
                            rightVal = new SimpleDateFormat(TestCaseUtils.DATE_FORMAT).parse(rightVal.toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    leftNumberVal = new Double(((Date)leftVal).getTime());
                    rightNumberVal = new Double(((Date)rightVal).getTime());
                }else if(leftVal instanceof Double || leftVal instanceof Integer || leftVal instanceof BigDecimal) {
                    leftNumberVal = new Double(leftVal.toString());
                    rightNumberVal = new Double(rightVal.toString());
                }else if(leftVal instanceof Boolean ){
                    leftNumberVal = new Boolean(leftVal.toString())?1d:0d;
                    rightNumberVal = new Boolean(rightVal.toString())?1d:0d;
                }
                //if(leftVal instanceof Double || leftVal instanceof Integer || leftVal instanceof BigDecimal ){
                compareRes = new BigDecimal( leftNumberVal ).compareTo( new BigDecimal(rightNumberVal) );
                //}
            }
        }

        if(operationExp.equals(">")){
            return compareRes>0;
        }else if(operationExp.equals(">=")){
            return compareRes>=0;
        }else if(operationExp.equals("<")){
            return compareRes<0;
        }else if(operationExp.equals("<=")){
            return compareRes<=0;
        }else if(operationExp.equals("=")){
            return compareRes==0;
        }else if(operationExp.equals("<>")){
            return compareRes!=0;
        }else if(operationExp.equals("&&")){
            return (Boolean)leftVal && (Boolean)rightVal;
        }else if(operationExp.equals("||")){
            return (Boolean)leftVal || (Boolean)rightVal;
        }

        return false;
    }

    private String parseFunctionNameFromPath(String path){
        int indexOfFunctionPos = path.indexOf(FUNCTION_LEFT_BRACKETS);

        return path.substring(1, indexOfFunctionPos);
    }

    private Object functionInvocation(String functionName, Object[] args){
        if(functionName.equals("testDataSimpleFieldSetType")){
            String a = "";
        }
        return this.customFunctionFactory.invokeCustomFunction(functionName, args);
    }

    private Object recursiveFunctionInvocation(Restriction restriction, String path, String[] rtn){

        assert rtn.length == 2 : "number of arguments in recursiveFunctionInvocation is not 2";

        Integer recursiveVar = new Integer( new Double(rtn[0].toString()).intValue() );

        SimpleField targetMasterSimpleField = this.bomGenerator.getPathSimpleFieldMap().get( rtn[1] );

        SimpleField slaveSimpleField = (SimpleField)restriction.getExtendtion().getParentTestData();

        int targeMasterSimpleFieldPos = Math.max(0, slaveSimpleField.getTestCase().size() + recursiveVar );

        int recursiveOffset = new Integer(rtn[0].toString());

        if( (recursiveOffset<0 && slaveSimpleField.getTestData().isGeneratingTestDataFirstChild() ) ||
                (recursiveOffset>0 && slaveSimpleField.getTestData().isGeneratingTestDataLastChild() )  ){
            //minStr 就是初始值
            if(rtn.length<3){
                String a = "";
            }
            return this.recursiveParse(null, rtn[2], restriction);
        }
        else {

            if(targetMasterSimpleField == null || targetMasterSimpleField.getTestCase() == null){
                String a = "";
            }

            if(targeMasterSimpleFieldPos >= targetMasterSimpleField.getTestCase().size()){
                String a = "";
                return null;
            }


            return targetMasterSimpleField.getTestCase().get( targeMasterSimpleFieldPos );
        }
    }


    private Object parseFunctionParameter( String path, Restriction restriction){

        String targetFunctionName = this.parseFunctionNameFromPath(path);

        int afterFunctionStrGeginPos = path.indexOf(targetFunctionName) + targetFunctionName.length() + 1;

        String afterFunctionStr = path.substring( afterFunctionStrGeginPos );

        int analyse = 1;

        int parsePos = 0;

        List<Integer> commaPosList = new ArrayList<Integer>();

        do{
            char tmpChar = afterFunctionStr.charAt( parsePos );

            if(tmpChar == '(' || tmpChar == '['){
                analyse ++;
            }else if(tmpChar == ')' || tmpChar == ']'){
                analyse --;
                if(analyse == 0){
                    break;
                }
            }else if(tmpChar == ',' && analyse == 1){
                commaPosList.add( parsePos );
            }
            parsePos ++;
        }
        while( parsePos < afterFunctionStr.length()-1 || analyse > 0 ) ;

        int splitPosStart = 0;

        int splitPosEnd = parsePos;

        String[] rtn = new String[commaPosList.size() +1];

        Object[] expRtnValueArr = null;

        if(commaPosList.size() != 0){
            int i=0;
            for(i=0; i<commaPosList.size(); i++){
                splitPosEnd = commaPosList.get(i);
                rtn[i] = afterFunctionStr.substring(splitPosStart, splitPosEnd);
                splitPosStart = splitPosEnd + 1;
            }
            rtn[i] = afterFunctionStr.substring(splitPosStart, parsePos);

            expRtnValueArr = new Object[rtn.length];

        }else{
            if(splitPosEnd>0){
                expRtnValueArr = new Object[1];
                rtn[0] = afterFunctionStr.substring(splitPosStart, splitPosEnd);
            }
        }

        Object thisFunctionResultValue = null;

        /**
         * 以后单独做个函数
         */
        if( IT_RECURSIVE_FUNCTION_NAME.equals( targetFunctionName ) ){
            thisFunctionResultValue = this.recursiveFunctionInvocation(restriction, path, rtn);

            //这里有些冗余问题，以后继续优化
            SimpleField slaveSimpleField = (SimpleField)restriction.getExtendtion().getParentTestData();

            int recursiveOffset = new Integer(rtn[0].toString());
            if( (recursiveOffset<0 && slaveSimpleField.getTestData().isGeneratingTestDataFirstChild() ) ||
                    (recursiveOffset>0 && slaveSimpleField.getTestData().isGeneratingTestDataLastChild() ) ){
                //minStr 就是初始值
                return thisFunctionResultValue;
            }

        }else if( isAbstractTestDataOperationFunction(targetFunctionName) ){
            if(targetFunctionName.equalsIgnoreCase(MERGE_FUNCTION_NAME) || targetFunctionName.equalsIgnoreCase(MERGE_FILTER_FUNCTION_NAME)){
                Object[] functionArgs = new Object[2];
                //这块以后要改
                functionArgs[0] = restriction.getExtendtion().getParentTestData();
                functionArgs[1] = rtn;
                return this.functionInvocation(targetFunctionName, functionArgs);
            }else if(targetFunctionName.equalsIgnoreCase(TESTDATA_SIZE_FUNCTION_NAME) || targetFunctionName.equalsIgnoreCase(TESTDATASIMPLEFIELDSETSIZE)){
                AbstractTestData abstractTestData = restriction.getExtendtion().getParentTestData();
                TestData testData = null;
                if( abstractTestData instanceof TestData ){
                    testData = (TestData)abstractTestData;
                }else{
                    testData = ((SimpleField)abstractTestData).getTestData();
                }

                Object[] functionArgs = null;

                functionArgs = new Object[rtn.length + 1];

                functionArgs[0] = testData;

                for(int i=1; i<=rtn.length; i++){
                    functionArgs[i] = rtn[i-1];
                }

                return this.functionInvocation(targetFunctionName, functionArgs);
            }
            else{
                return this.functionInvocation(targetFunctionName, rtn);
            }
        }
        else{
            if(expRtnValueArr != null && expRtnValueArr.length>0){
                for(int j=0; j<expRtnValueArr.length; j++){
                    if(rtn[j].equalsIgnoreCase("Application/Customer/Product/Account/@firstConsDate/")){
                        String a = "";
                    }
                    expRtnValueArr[j] = this.recursiveParse(null,rtn[j], restriction);
                }
            }
            thisFunctionResultValue = this.functionInvocation(targetFunctionName, expRtnValueArr);
        }
        /**
          * end of 以后单独做个函数
         **/

        //做完本次函数后剩下的东西
        //2 表达占位)$的位置
        int afterFunctionExpLevaeStrPos = path.indexOf(targetFunctionName) + targetFunctionName.length() + parsePos + 2 + 1;

        String afterFunctionExpLevaeStr = path.substring( afterFunctionExpLevaeStrPos );

        if("".equals( afterFunctionExpLevaeStr ) ){
            return thisFunctionResultValue;
        }else{
            return this.recursiveParse( thisFunctionResultValue, afterFunctionExpLevaeStr, restriction );
        }
    }

    /**
     * 获取TestData本身的属性的函数，如TestData的TestCase列表的长度
     * @param functionName
     * @return
     */
    private boolean isAbstractTestDataOperationFunction(String functionName){
        boolean rtn = false;

        if(ABSTRACT_TESTDATA_OPERATION_FUNCTION_NAME_LIST.contains(functionName)){
            rtn = true;
        }

        return rtn;
    }

    /**
     * 通过xpath 和 restriction 获取主的TestData或者SimpleField的具体属性
     * @return
     */

    private Object getAbsTestDataValueFromPath(String path, Restriction restriction){

        AbstractTestData abstractTestData = restriction.getExtendtion().getParentTestData();

        if(abstractTestData.getPath().equalsIgnoreCase("Application/Customer/Product/Account/CurInstalmentInfo/@AmortizationTerms/")){
            String a = "";
        }

        List<TestCaseUnit> targetTestCaseUnit = this.customFunctionFactory.findCrorrespondingTestCaseUnit(restriction.getExtendtion().getParentTestData(), path);

        assert targetTestCaseUnit==null || targetTestCaseUnit.size()==0 : "getAbsTestDataValueFromPath : testCaseUnitList is null or blank.";

        TestData currentTestData = this.customFunctionFactory.getBelongingTestData( restriction.getExtendtion().getParentTestData() ) ;

        int pos = -1;



        //这块逻辑有点不好理解，以后可能有问题
        if(abstractTestData instanceof TestData){
            pos = currentTestData.getParentTestData().getGeneratingChildrenTestCaseUnit().getPositionInParent();
        }else if(abstractTestData instanceof SimpleField){
            pos = currentTestData.getTestCaseUnitList().size()-1;
        }

        pos = Math.min( pos, targetTestCaseUnit.size()-1 );
        // end of 这块逻辑有点不好理解，以后可能有问题


        TestCaseUnit testCaseUnit = targetTestCaseUnit.get( pos );

        if(testCaseUnit == null){
            String a = "";
        }

        return testCaseUnit.getFieldValue(getFieldNameFromPath(path));
    }


    public String getFieldNameFromPath(String path){
        String temp1 = path.substring(0, path.length()-1);

        String fieldName = temp1.substring(temp1.lastIndexOf("/")+2, temp1.length());
        return fieldName;
    }


    /**
     * 从字符串中找到所有的path
     * @param exp
     * @return
     */
    public List<String> getAllAbsTestData(String exp){
        return TestCaseUtils.getAllAbsTestData(exp);
    }

    /**
     * 获取exp中的第一个path
     * @return
     */
    private String getExpFirstPath (String exp){
        Pattern p = Pattern.compile(FIND_ALL_PATH_EXP);

        Matcher m = p.matcher( exp );

        if (m.find() ){
            return m.group() ;
        }

        return null;
    }

    private Object isExpPureNumberic(String exp, Restriction restriction){
        Pattern p = Pattern.compile( FIND_PURE_NUMBERIC_PATH_EXP );

        Matcher m = p.matcher(exp);

        if ( m.find() ){
            return Double.valueOf( m.group() );
        }

        return null;
    }

    private boolean isStateLocalTestData(String exp){
        if(exp.startsWith(AbstractTestData.APPLICATION )){
            return true;
        }
        return false;
    }

    private boolean isCalculationOperationOperator(String exp){
        Pattern p = Pattern.compile(FIND_CALCULATION_PATH_EXP);

        Matcher m = p.matcher( exp );

        if(m.matches()){
            return true;
        }
        return false;
    }

    private boolean isStateProjectTestData(String exp){
        if(exp.startsWith(AbstractTestData.GLOABLE_PATH_PREFIX )){
            return true;
        }
        return false;
    }

    private boolean isStateLocalSimpleField(String exp){
        if(exp.startsWith(AbstractTestData.PATH_SEPARATOR )){
            return true;
        }
        return false;
    }
}
