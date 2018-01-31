import com.cams.blaze.request.Application;
import com.fico.testCaseGenerator.XSTream.XSTreamHelper;
import com.fico.testCaseGenerator.blazeServer.BlazeServer;
import com.fico.testCaseGenerator.blazeServer.BlazeServers;
import com.fico.testCaseGenerator.facade.TestCaseGeneratorFacade;
import com.fico.testCaseGenerator.project.Project;
import com.fico.testCaseGenerator.testCase.RIDOInstance;

public class TestMain {

    public static void main(String[] args) throws Exception{

        RIDOInstance rtnInstance = new RIDOInstance();

        TestCaseGeneratorFacade testCaseGeneratorFacade = new TestCaseGeneratorFacade();
        testCaseGeneratorFacade.listProjects();
        testCaseGeneratorFacade.loadAllProjects();

        Project cafsProject = testCaseGeneratorFacade.getProject("cafs");
        cafsProject.setProjectID(4L);
        Application testCase = (Application)cafsProject.generateTestCase();

        System.out.println("TestCase Generating Complete.");

        System.out.println("Creating Blaze Server.");
        //BlazeServer blazeServer = (BlazeServer)BlazeServer.createServer("C:\\FICO\\CAMS\\adb\\CAMS_RDS\\adb\\CAMS_RDS.server");
        System.out.println("Creating Blaze Server Complete.");

        System.out.println(new XSTreamHelper().getXStream().toXML(testCase) );

        //Application blazeResponse = blazeServer.invokeExternalMain( testCase );

        //System.out.println(new XSTreamHelper().getXStream().toXML(blazeResponse) );

        System.out.println("111");
    }
}
