package com.fico.testCaseGenerator.batch;

import com.fico.testCaseGenerator.facade.TestCaseGeneratorFacade;
import com.fico.testCaseGenerator.project.Project;
import com.fico.testCaseGenerator.testCase.RIDOInstance;
import com.fico.testCaseGenerator.testCase.TestCaseInstance;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public class TestCaseGeneratorItemReader implements ItemReader<RIDOInstance>, InitializingBean {

    private ExecutionConfiguration executionConfiguration;

    /**
     *
     * @return
     */
    public TestCaseGeneratorFacade getTestCaseGeneratorFacade(){
        return executionConfiguration.getTestCaseGeneratorFacade();
    }


    public RIDOInstance read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        while(executionConfiguration.getCurrentGeneratedNumCases() < executionConfiguration.getTargetNumCases()){

            return generateTestCaseInstance();
        }

        return null;
    }


    private RIDOInstance generateTestCaseInstance(){
        RIDOInstance rtnInstance = new RIDOInstance();

        Project cafsProject = getTestCaseGeneratorFacade().getProject("cafs");
        cafsProject.setProjectID(4L);
        Object testCase = cafsProject.generateTestCase();

        getTestCaseGeneratorFacade().manageProjectLevelMasterAndSlave();

        TestCaseInstance cafsTestCaseInstance = new TestCaseInstance();
        cafsTestCaseInstance.setSrcInput( testCase );
        cafsTestCaseInstance.setProjectID(4L);
        cafsTestCaseInstance.setIsInput("1");

        rtnInstance.getRidoResultList().add(cafsTestCaseInstance);

        return rtnInstance;
    }

    public ExecutionConfiguration getExecutionConfiguration() {
        return executionConfiguration;
    }

    public void setExecutionConfiguration(ExecutionConfiguration executionConfiguration) {
        this.executionConfiguration = executionConfiguration;
    }

    public void afterPropertiesSet() throws Exception {
        getTestCaseGeneratorFacade();
    }
}
