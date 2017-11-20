package com.fico.testCaseGenerator.batch;

import com.fico.testCaseGenerator.facade.TestCaseGeneratorFacade;

/**
 * Reader 和 Writer通信的中间类
 */

public class ExecutionConfiguration {

    private Long targetNumCases;

    private Integer currentGeneratedNumCases = 0;

    private Long totalNumCasesGenerated = 0L;

    public Long getTargetNumCases() {
        return targetNumCases;
    }

    public void setTargetNumCases(Long targetNumCases) {
        this.targetNumCases = targetNumCases;
    }

    public Integer getCurrentGeneratedNumCases() {
        return currentGeneratedNumCases;
    }

    public void setCurrentGeneratedNumCases(Integer currentGeneratedNumCases) {
        this.currentGeneratedNumCases = currentGeneratedNumCases;
    }

    public Long getTotalNumCasesGenerated() {
        return totalNumCasesGenerated;
    }

    public void setTotalNumCasesGenerated(Long totalNumCasesGenerated) {
        this.totalNumCasesGenerated = totalNumCasesGenerated;
    }

    private static TestCaseGeneratorFacade testCaseGeneratorFacade;

    public TestCaseGeneratorFacade getTestCaseGeneratorFacade(){
        if( testCaseGeneratorFacade == null ){
            testCaseGeneratorFacade = new TestCaseGeneratorFacade();
            testCaseGeneratorFacade.listProjects();
            testCaseGeneratorFacade.loadAllProjects();
            testCaseGeneratorFacade.setOutputPool(targetNumCases);
        }
        return testCaseGeneratorFacade;
    }
}
