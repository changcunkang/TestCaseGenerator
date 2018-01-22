import com.blazesoft.server.base.NdServerException;
import com.blazesoft.server.local.NdLocalServerException;
import com.cams.blaze.request.Application;
import com.fico.testCaseGenerator.XSTream.XSTreamHelper;
import com.fico.testCaseGenerator.blazeServer.BlazeServer;
import com.fico.testCaseGenerator.project.Project;
import com.fico.testCaseGenerator.repository.ApplicationDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 1/18/2018
 */
public class TestCaseInstanceExecutor implements Runnable {

    private BlazeServer blazeServer = null;

    private ApplicationDao applicationDao;

    private int targetGenNum;

    private int thNum;

    private int generatingCases = 0;

    private Project project = null;

    private XSTreamHelper xsTreamHelper = new XSTreamHelper();

    private Date startDate = new Date();

    private static Date endDate = new Date();

    public TestCaseInstanceExecutor(int num, int thNum, ApplicationDao applicationDao, BlazeServer blazeServer){
        this.targetGenNum = num;

        this.thNum = thNum;

        this.applicationDao = applicationDao;

        this.project = new Project("cafs",Project.PROJECT_TYPE_XMLBOM);

        this.blazeServer = blazeServer;
    }

    public void run() {

        while (this.generatingCases<=this.targetGenNum){
            Application testCase =  (Application)this.project.generateTestCase();

            Application blazeResponse = null;

            try {
                blazeResponse = this.getBlazeServer().invokeExternalMain( testCase );

                String responseStr = this.xsTreamHelper.getXStream().toXML(blazeResponse);

                blazeResponse.setResponseStr(responseStr);

                this.applicationDao.save(blazeResponse);
            } catch (NdServerException e) {
                e.printStackTrace();
            }

            System.out.println( "Thread " + thNum + " Generating " + generatingCases + "th TestCase Generating Complete");

            if(this.generatingCases==this.targetGenNum){
                Date endDate = new Date();
                long usedSec = (endDate.getTime() - this.startDate.getTime())/1000/60;
                System.out.println( "Thread " + thNum + " Complete at " + endDate + " start at " + this.startDate + " total used minutes:" + usedSec );
            }

            this.generatingCases ++;
        }

    }

    public BlazeServer getBlazeServer(){
        return blazeServer;
    }
}
