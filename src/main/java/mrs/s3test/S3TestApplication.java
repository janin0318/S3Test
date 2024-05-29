package mrs.s3test;

import mrs.s3test.service.S3Service;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S3TestApplication implements ApplicationRunner {

  private final S3Service s3Service;

  S3TestApplication(S3Service s3Service) {
    this.s3Service = s3Service;
  }

  public static void main(String[] args) {
    SpringApplication.run(S3TestApplication.class, args);
  }

  /**
   * Executes the run method with the given arguments.
   *
   * @param args the arguments passed to the application
   * @throws Exception if any error occurs during the execution
   */
  public void run(ApplicationArguments args) throws Exception {
    s3Service.createBucket();
  }

}
