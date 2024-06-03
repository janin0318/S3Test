package mrs.s3test;

import mrs.s3test.service.S3Service;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S3TestApplication {

  public static void main(String[] args) {
    SpringApplication.run(S3TestApplication.class, args);
  }

}
