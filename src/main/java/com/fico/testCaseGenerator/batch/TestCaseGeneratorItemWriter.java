package com.fico.testCaseGenerator.batch;

import com.cams.blaze.request.Application;
import com.fico.testCaseGenerator.facade.TestCaseGeneratorFacade;
import com.fico.testCaseGenerator.project.Project;
import com.fico.testCaseGenerator.repository.ApplicationDao;
import com.fico.testCaseGenerator.testCase.RIDOInstance;
import com.fico.testCaseGenerator.testCase.TestCaseInstance;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

public class TestCaseGeneratorItemWriter implements ItemWriter<Application> {

    private ExecutionConfiguration executionConfiguration;

    private JdbcTemplate jdbcTemplate;

    private String insertSQL = "";

    private ApplicationDao applicationDao;

    public ApplicationDao getApplicationDao() {
        return applicationDao;
    }

    public void setApplicationDao(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    public void write(List<? extends Application> list) throws Exception {

        if(list != null && list.size()>0){
            for(Application application : list){
                saveApplication( application );
                this.executionConfiguration.setCurrentGeneratedNumCases( this.executionConfiguration.getCurrentGeneratedNumCases() + 1 );
                System.out.println(this.executionConfiguration.getCurrentGeneratedNumCases() + "th cases saved completed");
            }
        }
    }

    private void saveApplication(Application application){
        this.applicationDao.save(application);
    }

    public void processingWriteItem(RIDOInstance ridoInstance){
        TestCaseGeneratorFacade facade = executionConfiguration.getTestCaseGeneratorFacade();

        List<TestCaseInstance> rtnTestCaseInstance = ridoInstance.getRidoResultList();

        for( int i=0; i< facade.getProjectList().size(); i++){
            Project project = facade.getProjectList().get(i);

            boolean acceptFlag = true;

            for( TestCaseInstance testCaseInstance : rtnTestCaseInstance ){
                if( testCaseInstance.getProjectID() == project.getProjectID() ){
                    Element finalDecisionEle = null;
                    try {
                        finalDecisionEle = DocumentHelper.parseText( testCaseInstance.getFinalOutput() ).getRootElement();
                    } catch (DocumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if(finalDecisionEle == null){
                        System.out.println("finalDecisionEle is null");
                    }
                    if(project.addToResultPool( finalDecisionEle )){

                    }else{
                        acceptFlag = false;
                    }
                }
            }

            /**
             if(i == facade.getProjectList().size()-1 && acceptFlag){
             **/
            if( i == facade.getProjectList().size()-1 ){

                if(acceptFlag){

                    this.executionConfiguration.setCurrentGeneratedNumCases( this.executionConfiguration.getCurrentGeneratedNumCases() + 1 );

                    if(this.executionConfiguration.getCurrentGeneratedNumCases() % 100 == 0){
                        System.out.println("Persistence :" + this.executionConfiguration.getCurrentGeneratedNumCases() + " threadID is " + " Cases ");
                    }
                    writeRIDOInstance( ridoInstance );
                }
            }

            if( !acceptFlag ){
                break;
            }
        }
    }

    public void writeRIDOInstance(RIDOInstance ridoInstance){


        System.out.println("writeRIDOInstance");

        int i = 0;
        for(TestCaseInstance testCaseInstance : ridoInstance.getRidoResultList()){
            String content = testCaseInstance.getContent();

            jdbcTemplate.update(insertSQL, testCaseInstance.getProjectID(), testCaseInstance.getIsInput(),
                    testCaseInstance.getContent(), testCaseInstance.getAppID(), testCaseInstance.getFinalOutput(), testCaseInstance.getFinalResult());

//
//
//            String random = String.valueOf( Math.random() );
//
//            FileWriter fileWriter = null;
//
//            try {
//                fileWriter = new FileWriter("C:\\temp\\xml\\" + random + "_" + i++ + ".xml");
//
//                fileWriter.write( content );
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            finally {
//
//                i=i%3;
//
//                if(fileWriter != null){
//                    try {
//                        fileWriter.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
        }
    }

    public ExecutionConfiguration getExecutionConfiguration() {
        return executionConfiguration;
    }

    public void setExecutionConfiguration(ExecutionConfiguration executionConfiguration) {
        this.executionConfiguration = executionConfiguration;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setInsertSQL(String insertSQL) {
        this.insertSQL = insertSQL;
    }

}
