import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmitriybrosalin on 01.08.17.
 */
public class Main {

    public static void main(String[] args){

        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(
                        "application-context.xml"
                );


        JobLauncher jobProdLauncher = (JobLauncher) applicationContext.getBean("jobLauncher");
        Job prodJobParallel = (Job) applicationContext.getBean("flowJob");

        try {

            JobExecution jobExecutionParallel = jobProdLauncher.run(prodJobParallel, new JobParameters());
            System.out.println("Exit status of Parallel : " + jobExecutionParallel.getStatus());
        }catch (Exception ex){

        }

    }

}
