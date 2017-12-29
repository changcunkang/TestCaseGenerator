package com.fico.testCaseGenerator.blazeServer;

import com.blazesoft.server.base.NdServerException;
import com.blazesoft.server.base.NdServiceException;
import com.blazesoft.server.base.NdServiceSessionException;
import com.blazesoft.server.config.NdServerConfig;
import com.blazesoft.server.deploy.NdStatelessServer;
import com.blazesoft.server.local.NdLocalServerException;
import com.cams.blaze.request.Application;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 12/28/2017
 */
public class BlazeServer extends NdStatelessServer {

    public BlazeServer(NdServerConfig serverConfig)
            throws NdLocalServerException
    {
        super(serverConfig);
    }

    public com.cams.blaze.request.Application invokeExternalMain(com.cams.blaze.request.Application arg0)
            throws NdServerException, NdServiceException, NdServiceSessionException
    {
        // Build the argument list
        Object[] applicationArgs = new Object[1];
        applicationArgs[0] = arg0;


        // Invoke the service and returns its result, if any.
        com.cams.blaze.request.Application retVal = (com.cams.blaze.request.Application)invokeService("StrategyRuleService", "invokeExternalMain", null, applicationArgs);
        return retVal;
    }

    public static void main(String[] args) throws Exception{
        BlazeServer blazeServer = (BlazeServer)BlazeServer.createServer("C:\\FICO\\CAMS\\bom\\blaze.server");

        Application blazeResponse = blazeServer.invokeExternalMain(new Application());

        System.out.println("");
    }

}
