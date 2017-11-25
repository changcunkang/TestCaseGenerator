import com.fico.testCaseGenerator.facade.TestCaseGeneratorFacade;
import com.fico.testCaseGenerator.project.Project;
import com.fico.testCaseGenerator.testCase.RIDOInstance;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

    public static void main(String[] args){
//        ApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContext-batch.xml");
//        JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
//        Job job = (Job) context.getBean("helloWorldJob");
//
//        try {
//            /* 运行Job */
//            JobExecution result = launcher.run(job, new JobParameters());
//            /* 处理结束，控制台打印处理结果 */
//            System.out.println(result.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        RIDOInstance rtnInstance = new RIDOInstance();

        TestCaseGeneratorFacade testCaseGeneratorFacade = new TestCaseGeneratorFacade();
        testCaseGeneratorFacade.listProjects();
        testCaseGeneratorFacade.loadAllProjects();

        Project cafsProject = testCaseGeneratorFacade.getProject("cafs");
        cafsProject.setProjectID(4L);
        Object testCase = cafsProject.generateTestCase();

        System.out.println("111");
    }

}
