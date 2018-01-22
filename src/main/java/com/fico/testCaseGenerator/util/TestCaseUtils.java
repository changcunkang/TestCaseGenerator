package com.fico.testCaseGenerator.util;

import com.fico.testCaseGenerator.expression.TestCaseExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 11/18/2017
 */
public class TestCaseUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * 从字符串中找到所有的path
     * @param exp
     * @return
     */
    public List<String> getAllAbsTestData(String exp){

        List<String> rtnPathList = new ArrayList<String>();

        Pattern p = Pattern.compile(TestCaseExpression.FIND_ALL_PATH_EXP);

        Matcher m = p.matcher( exp );

        while (m.find() ){
            rtnPathList.add( m.group() );
        }
        return rtnPathList;
    }

}
