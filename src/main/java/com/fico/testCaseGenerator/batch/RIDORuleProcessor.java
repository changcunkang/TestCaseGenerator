package com.fico.testCaseGenerator.batch;

import com.blazesoft.server.base.NdServerException;
import com.blazesoft.server.local.NdLocalServerException;
import com.cams.blaze.request.Application;
import com.fico.testCaseGenerator.XSTream.XSTreamHelper;
import com.fico.testCaseGenerator.blazeServer.BlazeServer;
import com.fico.testCaseGenerator.blazeServer.BlazeServers;
import com.fico.testCaseGenerator.facade.TestCaseGeneratorFacade;
import com.fico.testCaseGenerator.project.Project;
import com.fico.testCaseGenerator.testCase.RIDOInstance;
import com.fico.testCaseGenerator.testCase.TestCaseInstance;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class RIDORuleProcessor implements ItemProcessor<Object, Object>, InitializingBean, DisposableBean {

    private ExecutionConfiguration executionConfiguration;

    private static BlazeServer blazeServer = null;

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

    private BlazeServer getBlazeServer(){
        if(blazeServer == null){
            System.out.println("Creating Blaze Servers");
            try {
                blazeServer = (BlazeServer)BlazeServer.createServer("C:\\FICO\\CAMS\\adb\\CAMS_RDS\\adb\\CAMS_RDS_ser.server");
            } catch (NdLocalServerException e) {
                e.printStackTrace();
            }
            System.out.println("Creating Blaze Servers Successful");
        }
        return blazeServer;
    }


    public Object process(Object ridoInstance) throws Exception {

       Object blazeInput = this.getProject().generateTestCase();;

       return invokingBlaze(blazeInput);
    }

    public Object invokingBlaze(Object blazeInput){
        Application blazeResponse = null;
        try {
            blazeResponse = getBlazeServer().invokeExternalMain((Application)blazeInput);

            String responseStr = new XSTreamHelper().getXStream().toXML(blazeResponse);

            blazeResponse.setResponseStr(responseStr);

        } catch (NdServerException e) {
            e.printStackTrace();
        }

        return blazeResponse;
    }

    public void destroy() throws Exception {

    }

    public void afterPropertiesSet() throws Exception {
        //getBlazeServer();
    }

    public ExecutionConfiguration getExecutionConfiguration() {
        return executionConfiguration;
    }

    public void setExecutionConfiguration(ExecutionConfiguration executionConfiguration) {
        this.executionConfiguration = executionConfiguration;
    }

    public RIDOInstance doRIDOProecssingTestCase(RIDOInstance ridoInstance) throws Exception {

        clearResultReportMap();

        List<TestCaseInstance> rtnList = new Vector<TestCaseInstance>();

        //this.manageProjectLevelMasterAndSlave();

        String frdBlazeOutput = null;

        String rskBlazeOutput = null;

        String oprBlazeOutput = null;

        TestCaseInstance frdTestCaseInstance = ridoInstance.getRidoResultList().get(0);
        Element frdEle = (Element) frdTestCaseInstance.getSrcInput();
        frdTestCaseInstance.setAppID(getApplicationID(frdEle));


        Element fraudDecisionGlobalEle = null;

        TestCaseInstance rskTestCaseInstance = ridoInstance.getRidoResultList().get(1);
        Element rskEle = (Element) rskTestCaseInstance.getSrcInput();
        rskTestCaseInstance.setAppID(getApplicationID(rskEle));


        Element rskDecisionGlobalEle = null;

        TestCaseInstance oprTestCaseInstance = ridoInstance.getRidoResultList().get(2);
        Element oprEle = (Element) oprTestCaseInstance.getSrcInput();
        oprTestCaseInstance.setAppID(getApplicationID(oprEle));


        StringBuffer frdBF = new StringBuffer();

        StringBuffer rskBF = new StringBuffer();

        StringBuffer oprBF = new StringBuffer();

        boolean proecssingRIDOFlag = false;

        int loopFlag = 0;

        do {

            loopFlag ++;

            //frdBlazeOutput = BlazeServers.invokeFrdBlazeServer(frdEle.asXML());



            bufferAppend(frdBF, frdEle, frdBlazeOutput);

            frdEle = DocumentHelper.parseText(frdBlazeOutput).getRootElement();

            fraudDecisionGlobalEle = getFraudDecisionGlobal(frdEle);

            // if( rskCaseList != null && rskCaseList.size()>0 ){

            if (fraudDecisionGlobalEle != null) {

                Element oldFrandDecisionGlobalEle = (Element) rskEle
                        .selectSingleNode("/Application/FraudDecisionGlobal");

                if (oldFrandDecisionGlobalEle != null) {
                    rskEle.remove(oldFrandDecisionGlobalEle);
                }

                rskEle.add(fraudDecisionGlobalEle);
            } else {
                rskEle.addElement("FraudDecisionGlobal");
            }

            rskBlazeOutput = BlazeServers.invokeRskBlazeServer(rskEle.asXML());


            bufferAppend(rskBF, rskEle, rskBlazeOutput);

            rskEle = DocumentHelper.parseText(rskBlazeOutput).getRootElement();

            // }

            rskDecisionGlobalEle = getRiskDecisionGlobal(rskEle);


            if (fraudDecisionGlobalEle != null) {

                Element oldFrandDecisionGlobalEle = (Element) oprEle
                        .selectSingleNode("/Application/FraudDecisionGlobal");

                if (oldFrandDecisionGlobalEle != null) {
                    oprEle.remove(oldFrandDecisionGlobalEle);
                }

                oprEle.add(fraudDecisionGlobalEle.createCopy());
            } else {
                oprEle.addElement("FraudDecisionGlobal");
            }

            if (rskDecisionGlobalEle != null) {

                Element oldFrandDecisionGlobalEle = (Element) oprEle
                        .selectSingleNode("/Application/RiskDecisionGlobal");

                if (oldFrandDecisionGlobalEle != null) {
                    oprEle.remove(oldFrandDecisionGlobalEle);
                }

                oprEle.add(rskDecisionGlobalEle);
            } else {
                oprEle.addElement("RiskDecisionGlobal");
            }

            oprBlazeOutput = BlazeServers.invokeOprBlazeServer(oprEle.asXML());

            bufferAppend(oprBF, oprEle, oprBlazeOutput);

            oprEle = DocumentHelper.parseText(oprBlazeOutput).getRootElement();

            proecssingRIDOFlag = IsOPRBlazeOutputFinalDecision(oprEle, loopFlag);

            RIDOPrcessing(frdEle, rskEle, oprEle, oprBF, ridoInstance);

            operationBlazeoutput(frdEle);

            operationBlazeoutput(rskEle);

            operationBlazeoutput(oprEle);

            if (proecssingRIDOFlag) {
                frdTestCaseInstance.setFinalOutput(frdBlazeOutput);

                rskTestCaseInstance.setFinalOutput(rskBlazeOutput);

                oprTestCaseInstance.setFinalOutput(oprBlazeOutput);

                Element oprBlazeOutputFinalEle = DocumentHelper.parseText(
                        oprBlazeOutput).getRootElement();

                recordScore(oprBlazeOutputFinalEle);


                Attribute finalDecisionResultAttr = (Attribute) oprBlazeOutputFinalEle
                        .selectSingleNode("/Application/OprDecisionGlobal/@FinalDecisionResult");

                String finalResult = finalDecisionResultAttr.getValue();

                oprTestCaseInstance.setFinalResult(finalResult);
            }
            // }
        } while (!proecssingRIDOFlag);

        rskTestCaseInstance.setContent(rskBF.toString());

        frdTestCaseInstance.setContent(frdBF.toString());

        oprTestCaseInstance.setContent(oprBF.toString());

        rtnList.add(frdTestCaseInstance);

        rtnList.add(rskTestCaseInstance);

        rtnList.add(oprTestCaseInstance);



        ridoInstance.setNodeProcessingMap(noteInfoProcessingMap);

        ridoInstance.setScoreMap(scoreMap);

        ridoInstance.setRidoResultList(rtnList);

        ridoInstance.setAppID(oprTestCaseInstance.getAppID());

        frdTestCaseInstance.getAppID();

        if(loopFlag >= 50){
            ridoInstance.setFinalResult("Drop");
        }

        return ridoInstance;
    }


    private Map<String, Integer> noteInfoProcessingMap = new HashMap<String, Integer>();

    private Map<String, Double> scoreMap = new HashMap<String, Double>();

    private void clearResultReportMap() {

        noteInfoProcessingMap.put("N000000", 0);
        noteInfoProcessingMap.put("N010000", 0);
        noteInfoProcessingMap.put("N020000", 0);
        noteInfoProcessingMap.put("N030000", 0);
        noteInfoProcessingMap.put("N040000", 0);
        noteInfoProcessingMap.put("N050000", 0);
        noteInfoProcessingMap.put("N060000", 0);
        noteInfoProcessingMap.put("N060100", 0);
        noteInfoProcessingMap.put("N070000", 0);
        noteInfoProcessingMap.put("N080000", 0);
        noteInfoProcessingMap.put("N090000", 0);
        noteInfoProcessingMap.put("N100000", 0);
        noteInfoProcessingMap.put("N110000", 0);
        noteInfoProcessingMap.put("N120000", 0);
        noteInfoProcessingMap.put("N123000", 0);
        noteInfoProcessingMap.put("N127000", 0);
        noteInfoProcessingMap.put("N130000", 0);
        noteInfoProcessingMap.put("N140000", 0);
        noteInfoProcessingMap.put("N140001", 0);
        noteInfoProcessingMap.put("N150000", 0);
        noteInfoProcessingMap.put("N160000", 0);
        noteInfoProcessingMap.put("N170000", 0);
        noteInfoProcessingMap.put("N170001", 0);
        noteInfoProcessingMap.put("N180000", 0);
        noteInfoProcessingMap.put("N500000", 0);
        noteInfoProcessingMap.put("N550000", 0);
        noteInfoProcessingMap.put("N600000", 0);
        noteInfoProcessingMap.put("N700000", 0);
        noteInfoProcessingMap.put("N800000", 0);
        noteInfoProcessingMap.put("N980000", 0);
        noteInfoProcessingMap.put("N990000", 0);

        scoreMap.put("Score01", 0d);
        scoreMap.put("Score03", 0d);
        scoreMap.put("Score04", 0d);
        scoreMap.put("Score05", 0d);
    }


    private boolean IsOPRBlazeOutputFinalDecision(Element oprElement, int loopFlag) {

        Attribute finalDecisionResultAttr = (Attribute) oprElement
                .selectSingleNode("/Application/OprDecisionGlobal/@FinalDecisionResult");

        String curNodeID = oprElement.attributeValue("CurNodeID");

        if(curNodeID != null){

            if( loopFlag >= 50 ){
                writeXML(oprElement, oprElement.asXML(), "C:/temp/xml");
                return true;
            }

            this.noteInfoProcessingMap.put(curNodeID, this.noteInfoProcessingMap.get(curNodeID) + 1);

            if ("N990000".equals( curNodeID ) || "N980000".equals( curNodeID )) {
                return true;
            }
        }

        return false;
    }

    public String getApplicationID(Element blazeOutput) {
        Attribute attr = (Attribute) blazeOutput
                .selectSingleNode("/Application/ApplicationForm[1]/@ApplicationID");
        return attr.getValue();
    }


    private void operationBlazeoutput(Element outputEle) {
        Node responseEle = outputEle
                .selectSingleNode("//Application/DecisionResponse");

        Node processingHistoryEle = outputEle
                .selectSingleNode("//Application/ProcessingHistory");

        Node messageListEle = outputEle
                .selectSingleNode("//Application/MessageList");

        Node fraudDecisionGlobalEle = outputEle
                .selectSingleNode("//Application/FraudDecisionGlobal");

        Node riskDecisionGloballEle = outputEle
                .selectSingleNode("//Application/RiskDecisionGlobal");

        Node oprDecisionGloballEle = outputEle
                .selectSingleNode("//Application/OprDecisionGlobal");

        if (responseEle != null) {
            outputEle.remove(responseEle);
        }

        if (processingHistoryEle != null) {
            outputEle.remove(processingHistoryEle);
        }

        if (messageListEle != null) {
            outputEle.remove(messageListEle);
        }

        // if(fraudDecisionGlobalEle != null){
        // outputEle.remove(fraudDecisionGlobalEle);
        // }
        //
        // if(riskDecisionGloballEle != null){
        // outputEle.remove(riskDecisionGloballEle);
        // }
        //
        // if(oprDecisionGloballEle != null){
        // outputEle.remove(oprDecisionGloballEle);
        // }
        //
    }

    private void bufferAppend(StringBuffer bf, Element ele, String output)
            throws Exception {
        String curNode = ele.attributeValue("CurNodeID");

        // 设置文件编码
        OutputFormat xmlFormat = new OutputFormat();
        xmlFormat.setEncoding("UTF-8");
        // 设置换行
        xmlFormat.setNewlines(true);
        // 生成缩进
        xmlFormat.setIndent(true);
        // 使用4个空格进行缩进, 可以兼容文本编辑器
        xmlFormat.setIndent("    ");

        StringWriter writer = new StringWriter();
        // 格式化输出流
        XMLWriter xmlWriter = new XMLWriter(writer, xmlFormat);
        // 将document写入到输出流
        xmlWriter.write(ele.getDocument());
        xmlWriter.close();

        bf.append("blaze input in node : ").append(curNode).append("\n");
        bf.append(writer.toString());

        bf.append("blaze output in node : ").append(curNode).append("\n");
        bf.append(output);

        bf.append("------------------------------------------------------------------------------------------------");
    }



    private void RIDOPrcessing(Element frdEle, Element rskEle, Element oprEle,
                               StringBuffer oprBF, RIDOInstance rtnInstance) throws Exception {

        String currentNode = oprEle.attributeValue("CurNodeID");

        Element targetNextNodeElement = (Element) oprEle
                .selectSingleNode("/Application/NodeInfo[@NodeID='"
                        + currentNode + "']");

        String nextNodeId = targetNextNodeElement.attributeValue("NextNodeID");

        if("N600000".equals(nextNodeId)){
            recordNode60000(oprEle, currentNode, rtnInstance);
        }

        frdEle.setAttributeValue("CurNodeID", nextNodeId);

        rskEle.setAttributeValue("CurNodeID", nextNodeId);

        oprEle.setAttributeValue("CurNodeID", nextNodeId);
    }

    public static Map<String,Map> node60000RecordMap = new HashMap<String, Map>();

    private synchronized void recordNode60000(Element ele, String priNodeID, RIDOInstance rtnInstance){
        if(node60000RecordMap.get(priNodeID) == null ){
            Map<String, Integer> nodeDetailMap = new HashMap<String, Integer>();
            node60000RecordMap.put(priNodeID, nodeDetailMap);
        }
        Map<String, Integer> preNodeRecordMap = node60000RecordMap.get(priNodeID);

        Element ruleDecisionElement = (Element) ele
                .selectSingleNode("/Application/DecisionResponse/RuleDecision");

        String ruleCode = ruleDecisionElement.attributeValue("RuleCode");

        String ruleName = ruleDecisionElement.attributeValue("RuleName");

        if(preNodeRecordMap.get(ruleCode) == null){
            preNodeRecordMap.put(ruleCode, 0);
        }

        preNodeRecordMap.put(ruleCode, preNodeRecordMap.get(ruleCode) + 1);

        rtnInstance.setN60000RuleName(ruleName);
        rtnInstance.setPreN60000Node(priNodeID);
    }

    private Element getFraudDecisionGlobal(Element frdBlazeOutput) {

        return getElementCreateCopy(frdBlazeOutput, "FraudDecisionGlobal");
    }

    private Element getRiskDecisionGlobal(Element frdBlazeOutput) {

        return getElementCreateCopy(frdBlazeOutput, "RiskDecisionGlobal");
    }

    private Element getElementCreateCopy(Element appEle,
                                         String applicationFirstChildEleName) {

        Element fraudDecisionGlobalEle = (Element) appEle
                .selectSingleNode("//Application/"
                        + applicationFirstChildEleName);

        if (fraudDecisionGlobalEle != null) {
            return fraudDecisionGlobalEle.createCopy();
        }

        return null;
    }


    private void writeXML(Element ele, String blazeOutput, String filePath) {
        //创建字符串缓冲区
        StringWriter stringWriter = new StringWriter();
        //设置文件编码
        OutputFormat xmlFormat = new OutputFormat();
        xmlFormat.setEncoding("UTF-8");
        // 设置换行
        xmlFormat.setNewlines(true);
        // 生成缩进
        xmlFormat.setIndent(true);
        // 使用4个空格进行缩进, 可以兼容文本编辑器
        xmlFormat.setIndent("    ");

        Document doc = ele.getDocument();

        filePath = filePath + Math.random() + ".xml";

        try{
            Writer fileWriter = new FileWriter( filePath, true );

            //创建写文件方法
            XMLWriter xmlWriter = new XMLWriter(fileWriter,xmlFormat);

            fileWriter.write("blaze input in node : " +
                    ele.attributeValue("CurNodeID") + "\n");

            //写入文件
            xmlWriter.write(doc);

            //fileWriter.write("blaze output in node : " +
            //        ele.attributeValue("CurNodeID") + "\n");

            fileWriter.write(blazeOutput);

            fileWriter.write("-----------------------------------------------------------------------------------------------------------------------------------------\n");

            //关闭
            xmlWriter.close();

            fileWriter.close();

            // 输出xml
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }



    private void recordScore(Element rskDecisionGlobalEle){

        Element scoreModelRtnInfo = (Element)rskDecisionGlobalEle.selectSingleNode("/Application/RiskDecisionGlobal");

        if(scoreModelRtnInfo == null){
            return;
        }

        if( scoreModelRtnInfo.attributeValue("Score01") != null ){
            scoreMap.put("Score01", Double.parseDouble( scoreModelRtnInfo.attributeValue("Score01") ) );
        }

        if( scoreModelRtnInfo.attributeValue("Score03") != null ){
            scoreMap.put("Score03", Double.parseDouble( scoreModelRtnInfo.attributeValue("Score03") ) );
        }

        if( scoreModelRtnInfo.attributeValue("Score04") != null ){
            scoreMap.put("Score04", Double.parseDouble( scoreModelRtnInfo.attributeValue("Score04") ) );
        }

        if( scoreModelRtnInfo.attributeValue("Score05") != null ){
            scoreMap.put("Score05", Double.parseDouble( scoreModelRtnInfo.attributeValue("Score05") ) );
        }
    }
}
