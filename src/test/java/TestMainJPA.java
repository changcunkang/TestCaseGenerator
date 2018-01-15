import com.cams.blaze.request.Application;
import com.fico.testCaseGenerator.XSTream.XSTreamHelper;
import com.fico.testCaseGenerator.blazeServer.BlazeServer;
import com.fico.testCaseGenerator.facade.TestCaseGeneratorFacade;
import com.fico.testCaseGenerator.project.Project;
import com.fico.testCaseGenerator.repository.ApplicationDao;
import com.fico.testCaseGenerator.testCase.RIDOInstance;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMainJPA {

    public static void main(String[] args) throws Exception{

        int loopCnt = 10;

        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext-batch.xml");
        ApplicationDao appDao = (ApplicationDao) context.getBean(ApplicationDao.class);

        RIDOInstance rtnInstance = new RIDOInstance();

        TestCaseGeneratorFacade testCaseGeneratorFacade = new TestCaseGeneratorFacade();
        testCaseGeneratorFacade.listProjects();
        testCaseGeneratorFacade.loadAllProjects();

        Project cafsProject = testCaseGeneratorFacade.getProject("cafs");
        cafsProject.setProjectID(4L);

        System.out.println("Creating Blaze Server.");
        BlazeServer blazeServer = (BlazeServer)BlazeServer.createServer("C:\\FICO\\CAMS\\bom\\blaze.server");
        System.out.println("Creating Blaze Server Complete.");


        for (int i=0; i<loopCnt; i++ ){

            System.out.println("Generating " + i + " TestCase.");

            Application testCase = (Application) cafsProject.generateTestCase();



            Application blazeResponse = blazeServer.invokeExternalMain( testCase );

            String responseStr = XSTreamHelper.getXStream().toXML(blazeResponse);

            blazeResponse.setResponseStr(responseStr);

            appDao.save(blazeResponse);

            System.out.println( + i + " TestCase Generating Complete");

        }

        System.out.println("");

    }
}
