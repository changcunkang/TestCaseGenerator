import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 1/16/2018
 */
public class RunSpringBatch {

    public static void main(String[] args) {

        Long startMill = System.currentTimeMillis();
        Date startDate = new Date();

        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext-batch.xml");
        JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("helloWorldJob");

        try {
            /* 运行Job */
            JobExecution result = launcher.run(job, new JobParameters());

        } catch (Exception e) {
            e.printStackTrace();
        }

//        Long endMill = System.currentTimeMillis();
//        System.out.println(" TestCase Generating Start at " + startDate);
//        System.out.println(" TestCase Generating End at " + new Date());
//        System.out.println(" Total used minutes : " + (endMill-startMill)/1000/60);
    }

}
