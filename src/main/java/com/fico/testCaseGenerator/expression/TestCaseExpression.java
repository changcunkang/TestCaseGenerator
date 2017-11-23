package com.fico.testCaseGenerator.expression;

import com.fico.testCaseGenerator.BOM.BOMGenerator;
import com.fico.testCaseGenerator.CustomFunctionFactory.CustomFunctionFactory;
import com.fico.testCaseGenerator.data.AbstractTestData;
import com.fico.testCaseGenerator.data.SimpleField;
import com.fico.testCaseGenerator.data.TestData;
import com.fico.testCaseGenerator.data.configuration.Restriction;
import com.fico.testCaseGenerator.util.RandomFactory;
import com.fico.testCaseGenerator.util.TestCaseUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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

    private BOMGenerator bomGenerator;

    private static final int TYPE_CURRENT = 0;

    private static final int TYPE_SIZE = 1;

    private static final int TYPE_LIST = 0;

    private static final int STATE_PROJECT_SIMPLEFIELD_PATH = 0;

    private static final int STATE_CALCULATE = 0;

    private static final int STATE_LOGIC = 0;

    private static final int STATE_FUNCTION = 0;

    private static final String FIND_ALL_PATH_EXP = "(\\/)?Application(\\/@?[_a-zA-Z]{1,}[a-zA-Z0-9]{1,})*\\/";

    private static final String FIND_PURE_NUMBERIC_PATH_EXP = "^(-?\\d+)(\\.+\\d+)?$";

    private static final String FIND_PURE_INTEGER_PATH_EXP = "^\\d?$";

    private static final String FIND_CALCULATION_PATH_EXP = "(^\\+|-|\\*|\\/).*";

    private static final String FUNCTION_SIMBOL_BEGIN = "$";

    private static final String FUNCTION_LEFT_BRACKETS = "(";

    private static final String FUNCTION_RIGHT_BRACKETS = ")";

    private CustomFunctionFactory customFunctionFactory = null;

    public TestCaseExpression(BOMGenerator bomGenerator){
        this.bomGenerator = bomGenerator;
        this.customFunctionFactory = new CustomFunctionFactory();
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

                        Object rtnVal = this.recursiveParse(null, minExp, restriction );
                        return rtnVal;
                    }
                    else{

                        Object minExpRtnVal = this.recursiveParse(null, minExp, restriction );

                        Object maxExpRtnVal = this.recursiveParse(null, maxExp, restriction );

                        return generateRandomBetweetnMaxValueAndMinValue(minExpRtnVal, maxExpRtnVal, restriction);
                    }
                }
            }
        }

        return null;
    }

    private Object transFerType(Object val, Restriction restriction){

       return null;
    }

    private Object generateRandomBetweetnMaxValueAndMinValue(Object minValue, Object maxValue, Restriction restriction){
        AbstractTestData abstractTestData = restriction.getExtendtion().getParentTestData();

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
            return new Integer ( new Double(val.toString()).intValue() );
        }else if(val instanceof Double){
            return new Integer ( ((Double)val).intValue() );
        }else if( val instanceof BigDecimal){
            return ((BigDecimal)val).intValue();
        }
        return (Integer)val;
    }

    private Object recursiveParse(Object tempValue, String path, Restriction restriction){

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
            String operator = path.substring(0,1);

            String subPath = path.substring(1);

            Object leftValue = tempValue;

            Object rightValue = this.recursiveParse(null, subPath, restriction);;

            if("+".equals(operator)){
                //日期加减
                //现只支持左边日期右边数字，左边数字右边日期暂时没考虑
                if(this.isSimpleFieldDateType(restriction)){
                    GregorianCalendar gc=new GregorianCalendar();
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
                if(this.isSimpleFieldDateType(restriction)){
                    GregorianCalendar gc=new GregorianCalendar();
                    gc.setTime( (Date)tempValue );
                    gc.add(GregorianCalendar.DATE, new Double( rightValue.toString() ).intValue() * -1);
                    return gc.getTime();
                }
                BigDecimal leftBigDec = new BigDecimal(leftValue.toString());
                return leftBigDec.subtract( new BigDecimal( recursiveParse(null, subPath, restriction).toString() ) );

            }else if ("*".equals(operator)){
                BigDecimal leftBigDec = new BigDecimal(leftValue.toString());
                return leftBigDec.multiply( new BigDecimal( recursiveParse(null, subPath, restriction).toString() ) );

            }else if ("/".equals(operator)){
                BigDecimal leftBigDec = new BigDecimal(leftValue.toString());
                return leftBigDec.divide( new BigDecimal( recursiveParse(null, subPath, restriction).toString() ) );
            }
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
        else if(true){
            return path;
        }
        return null;
    }

    private boolean isSimpleFieldDateType(Restriction restriction){
        if(restriction.getExtendtion().getParentTestData() instanceof SimpleField ) {
            SimpleField simpleField = (SimpleField) restriction.getExtendtion().getParentTestData();

            if (simpleField.getFieldType() == SimpleField.TYPE_DATE || simpleField.getFieldType() == SimpleField.TYPE_DATETIME) {
                return true;

            }
        }
        return false;
    }

    public boolean isAllElementReady(String exp){
        List<String> expIncludeAbsTestDataPath = getAllAbsTestData( exp );

        for(String path : expIncludeAbsTestDataPath){
            if ( ! this.bomGenerator.isAbsTestDataGenerateFinish(path) ){
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

        Double leftNumbericVal = null;
        Double rightNumberVal = null;

        if(leftVal instanceof String){
            compareRes = leftVal.toString().compareTo(rightVal.toString());
        }else {
            if( leftVal instanceof Date ){
                leftNumbericVal = new Double(((Date)leftVal).getTime());
                rightNumberVal = new Double(((Date)rightVal).getTime());
            }else if(leftVal instanceof Double || leftVal instanceof Integer) {
                leftNumbericVal = new Double(((Date)leftVal).getTime());
                rightNumberVal = new Double(((Date)rightVal).getTime());
            }else if(leftVal instanceof Boolean ){
                leftNumbericVal = (Boolean)leftVal?1d:0d;
                rightNumberVal = (Boolean)rightVal?1d:0d;
            }
            if(leftVal instanceof Double || leftVal instanceof Integer ){
                compareRes = new BigDecimal( leftNumbericVal ).compareTo( new BigDecimal(rightNumberVal) );
            }
        }

        if(operationExp.equals(">")){
            return compareRes>1;
        }else if(operationExp.equals(">=")){
            return compareRes>=1;
        }else if(operationExp.equals("<")){
            return compareRes<1;
        }else if(operationExp.equals("<=")){
            return compareRes<=1;
        }else if(operationExp.equals("==")){
            return compareRes==1;
        }else if(operationExp.equals("!=")){
            return compareRes!=1;
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
        return this.customFunctionFactory.invokeCustomFunction(functionName, args);
    }

    private Object parseFunctionParameter( String path, Restriction restriction){
        String functionName = parseFunctionNameFromPath(path);

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

        if(expRtnValueArr != null && expRtnValueArr.length>0){
            for(int j=0; j<expRtnValueArr.length; j++){
                expRtnValueArr[j] = this.recursiveParse(null,rtn[j], restriction);
            }
        }

        Object thisFunctionResultValue = this.functionInvocation(targetFunctionName, expRtnValueArr);

        //做完本次函数后剩下的东西
        //2 表达占位)$的位置
        int afterFunctionExpLevaeStrPos = path.indexOf(functionName) + functionName.length() + parsePos + 2 + 1;

        String afterFunctionExpLevaeStr = path.substring( afterFunctionExpLevaeStrPos );

        if("".equals( afterFunctionExpLevaeStr ) ){
            return thisFunctionResultValue;
        }else{
            return this.recursiveParse( thisFunctionResultValue, afterFunctionExpLevaeStr, restriction );
        }

    }

    /**
     * 通过xpath 和 restriction 获取主的TestData或者SimpleField的具体属性
     * @return
     */
    private Object getAbsTestDataValueFromPath(String path, Restriction restriction){

        AbstractTestData abstractSlaveTestData = restriction.getExtendtion().getParentTestData();

        AbstractTestData absMasterTestData = null;

        List slaveTestCaseList = (List)abstractSlaveTestData.getTestCase();

        int slaveTestCasePos = slaveTestCaseList.size();

        if(abstractSlaveTestData instanceof SimpleField){

            List masterTestCaseList = this.bomGenerator.getAbsTestDataFromPath(path);

            int targetMasterPos = slaveTestCasePos == 0 ? 0 : slaveTestCasePos -1;

            targetMasterPos = Math.min(targetMasterPos, masterTestCaseList.size() -1);

            return masterTestCaseList.get(targetMasterPos);
        }
        else if(abstractSlaveTestData instanceof TestData){
            absMasterTestData = this.bomGenerator.getPathTestDataMap().get(path);

            return absMasterTestData;
        }
        return null;
    }

    /**
     * 从字符串中找到所有的path
     * @param exp
     * @return
     */
    private List<String> getAllAbsTestData(String exp){

        List<String> rtnPathList = new ArrayList<String>();

        Pattern p = Pattern.compile(FIND_ALL_PATH_EXP);

        Matcher m = p.matcher( exp );

        while (m.find() ){
            rtnPathList.add( m.group() );
        }
        return rtnPathList;
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
