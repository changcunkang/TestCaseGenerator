package com.fico.testCaseGenerator.blazeServer;

import com.blazesoft.server.base.NdServiceException;
import com.blazesoft.server.deploy.NdStatelessServer;
import com.blazesoft.server.local.NdLocalServerException;
import com.cams.blaze.request.Application;

public class BlazeServers  {

    private static final String FRD_SERVER_FILE_PATH = "C:\\FICO\\CAMS\\bom\\blaze.server";

    private static final String RSK_SERVER_FILE_PATH = "C:\\projects\\testCaseGenerator\\eclipse_work_space\\TestCaseGenerator\\adb\\RSK\\RSK_ser.server";

    private static final String OPR_SERVER_FILE_PATH = "C:\\projects\\testCaseGenerator\\eclipse_work_space\\TestCaseGenerator\\adb\\OPR\\OPR_ser.server";

    private static Integer blazeServerNum = 0;

    private static Integer frdNumBlazeServer = 1;

    private static Integer rskNumBlazeServer = 1;

    private static Integer oprNumBlazeServer = 1;

    private static BlazeServer[] frdServers = null;

    private static NdStatelessServer[] rskServers = null;

    private static NdStatelessServer[] oprServers = null;

    public static Integer getBlazeServerNum() {
        return blazeServerNum;
    }

    private static Integer getFrdNumBlazeServer() {
        return frdNumBlazeServer;
    }

    private static Integer getRskNumBlazeServer() {
        return rskNumBlazeServer;
    }

    private static Integer getOprNumBlazeServer() {
        return oprNumBlazeServer;
    }

    public static void createServers(){
        getFrdServers();
        getRskServers();
        getOprServers();
    }

    private static NdStatelessServer[] getFrdServers() {

        if(frdServers == null){
            frdServers = new BlazeServer[frdNumBlazeServer];

            for(int i=0; i<frdNumBlazeServer; i++){
                try {
                    frdServers[i] = (BlazeServer) BlazeServer.createServer(FRD_SERVER_FILE_PATH);
                } catch (NdLocalServerException e) {
                    e.printStackTrace();
                }
            }
        }

        return frdServers;
    }

    private static NdStatelessServer[] getRskServers() {

        if(rskServers == null){
            rskServers = new NdStatelessServer[rskNumBlazeServer];

            for(int i=0; i<rskNumBlazeServer; i++){
                try {
                    rskServers[i] = NdStatelessServer.createStatelessServer(RSK_SERVER_FILE_PATH);
                } catch (NdLocalServerException e) {
                    e.printStackTrace();
                }
            }

        }

        return rskServers;
    }

    private static NdStatelessServer[] getOprServers() {

        if(oprServers == null){
            oprServers = new NdStatelessServer[oprNumBlazeServer];

            for(int i=0; i<oprNumBlazeServer; i++){
                try {
                    oprServers[i] = NdStatelessServer.createStatelessServer(OPR_SERVER_FILE_PATH);
                } catch (NdLocalServerException e) {
                    e.printStackTrace();
                }
            }

        }

        return oprServers;
    }


    private static int frdExecutionPointer = 0;
    public static Object invokeFrdBlazeServer(Object applicationObj) {

        Object[] applicationArgs = new Object[1];
        applicationArgs[0] = applicationObj;

        Object rtn = null;

        try {
            //rtn = (String) getFrdServers()[frdExecutionPointer>=frdNumBlazeServer?frdNumBlazeServer-1:frdExecutionPointer].invokeService("FraudRuleService", "invokeExternalMain", null, applicationArgs);
            rtn = getFrdServers()[0].invokeService("StrategyRuleService", "invokeExternalMain", null, applicationArgs);

        } catch (NdLocalServerException e) {
            e.printStackTrace();
        } catch (NdServiceException e) {
            e.printStackTrace();
        }

        frdExecutionPointer++;
        frdExecutionPointer = frdExecutionPointer % frdNumBlazeServer;

        return rtn;
    }

    private static int rskExecutionPointer = 0;
    public static String invokeRskBlazeServer(String blazeInput){
        Object[] applicationArgs = new Object[1];
        applicationArgs[0] = blazeInput;

        String rtn = null;

        try {
            //rtn = (String)getRskServers()[rskExecutionPointer>=rskNumBlazeServer?rskNumBlazeServer-1:rskExecutionPointer].invokeService("RiskRuleService","invokeExternalMain",null, applicationArgs);
            rtn = (String)getRskServers()[0].invokeService("RiskRuleService","invokeExternalMain",null, applicationArgs);

        } catch (NdLocalServerException e) {
            e.printStackTrace();
        } catch (NdServiceException e) {
            e.printStackTrace();
        }

        rskExecutionPointer++;

        rskExecutionPointer = rskExecutionPointer%rskNumBlazeServer;

        return rtn;
    }

    private static int oprExecutionPointer = 0;
    public static String invokeOprBlazeServer(String blazeInput){
        Object[] applicationArgs = new Object[1];
        applicationArgs[0] = blazeInput;

        String rtn = null;

        try {
            //rtn = (String)getOprServers()[oprExecutionPointer>=oprNumBlazeServer?oprNumBlazeServer-1:oprExecutionPointer].invokeService("OPRRuleService","invokeExternalMain",null, applicationArgs);

            rtn = (String)getOprServers()[0].invokeService("OPRRuleService","invokeExternalMain",null, applicationArgs);

        } catch (NdLocalServerException e) {
            e.printStackTrace();
        } catch (NdServiceException e) {
            e.printStackTrace();
        }

        oprExecutionPointer++;

        oprExecutionPointer = oprExecutionPointer%oprNumBlazeServer;

        return rtn;
    }


}
