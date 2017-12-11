package com.fico.testCaseGenerator.data;

import java.util.List;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 12/1/2017
 */
public class TempoaryTestData extends TestData {

    public static final String TEMPOARY_TESTDATA_NODE_NAME = "Tempoary";

    public static final String TEMPOARY_TESTDATA_PREFIX = AbstractTestData.APPLICATION + AbstractTestData.PATH_SEPARATOR + TEMPOARY_TESTDATA_NODE_NAME;

    private List<TempoaryTestCase> tempoaryTestCaseList = null;

    @Override
    public List getTestCase() {
        return tempoaryTestCaseList;
    }

    @Override
    public void setTestCase(List testCase) {
        this.tempoaryTestCaseList = testCase;
    }
}
