import com.cams.blaze.request.Application;
import com.cams.blaze.request.Customer;
import com.cams.blaze.request.Product;
import com.fico.testCaseGenerator.XSTream.XSTreamHelper;
import com.fico.testCaseGenerator.blazeServer.BlazeServer;
import com.fico.testCaseGenerator.project.Project;
import com.fico.testCaseGenerator.repository.ApplicationDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestMainThread {

    public static void main(String[] args) throws Exception{

        int targetCases = 300000;

        int threadNum = 8;

        BlazeServer blazeServer = (BlazeServer)BlazeServer.createServer("C:\\FICO\\CAMS\\adb\\CAMS_RDS\\adb\\CAMS_RDS.server");

        Thread[] threadArr = new Thread[ threadNum ];

        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext-batch.xml");
        ApplicationDao appDao = (ApplicationDao) context.getBean(ApplicationDao.class);

//        for(int i=0; i<threadNum; i++){
//            Thread tmpThread = new Thread(new TestCaseInstanceExecutor(targetCases/threadNum, i, appDao, blazeServer));
//            threadArr[i] = tmpThread;
//        }

        Project project = new Project("cafs",Project.PROJECT_TYPE_XMLBOM);

        Application testCase =  (Application)project.generateTestCase();

        appDao.save(testCase);

//        for(int i=0; i<threadNum; i++){
//             threadArr[i].start();
//        }
    }
}
