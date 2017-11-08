import com.cams.blaze.request.Application;
import com.fico.testCaseGenerator.repository.ApplicationDao;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMainJPA {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext-batch.xml");
        ApplicationDao appDao = (ApplicationDao) context.getBean(ApplicationDao.class);

        Application app = new Application();

        app.setCallType("1111");

        appDao.save(app);

    }
}
