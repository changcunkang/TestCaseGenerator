package com.fico.testCaseGenerator.batch;

import com.cams.blaze.request.Application;
import com.fico.testCaseGenerator.facade.TestCaseGeneratorFacade;
import com.fico.testCaseGenerator.project.Project;
import com.fico.testCaseGenerator.testCase.RIDOInstance;
import com.fico.testCaseGenerator.testCase.TestCaseInstance;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;
import java.util.List;

public class TestCaseGeneratorItemReader implements ItemReader<Object>, InitializingBean {

    private ExecutionConfiguration executionConfiguration;

    private Project project;

    public Project getProject() {
        if(this.project == null){
            TestCaseGeneratorFacade testCaseGeneratorFacade = new TestCaseGeneratorFacade();
            testCaseGeneratorFacade.listProjects();
            testCaseGeneratorFacade.loadAllProjects();

            Project cafsProject = testCaseGeneratorFacade.getProject("cafs");
            cafsProject.setProjectID(4L);
            this.project = cafsProject;
        }
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    /**
     *
     * @return
     */
    public TestCaseGeneratorFacade getTestCaseGeneratorFacade(){
        return executionConfiguration.getTestCaseGeneratorFacade();
    }


    private Date startDate;

    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if(executionConfiguration.getCurrentGeneratedNumCases() == 0){
            startDate = new Date();
            System.out.println("batch start at " + startDate);
        }

        while(executionConfiguration.getCurrentGeneratedNumCases() < executionConfiguration.getTargetNumCases()){

            return generateTestCaseInstance();
        }

        System.out.println("all " + executionConfiguration.getTargetNumCases() + " cases generated complete, batch start at " + startDate);

        System.out.println("batch end at " + new Date());

        return null;
    }


    private Object generateTestCaseInstance(){
        return this.getProject().generateTestCase();
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
