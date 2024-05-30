package mrs.s3test.controller;

import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import mrs.s3test.service.S3Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Controller
@RequestMapping("/s3")
@Log4j2
public class S3Controller {

  private final S3Service s3Service;

  public S3Controller(S3Service s3Service) {
    this.s3Service = s3Service;
  }

  @GetMapping("/index")
  public String index() {
    return "index";
  }

  @PostMapping("/upload")
  public String upload(@RequestParam("file") MultipartFile file) throws IOException {
    PutObjectResponse result = s3Service.uploadFile(file.getOriginalFilename(), file.getBytes());
    log.info("ファイルアップロードしました。 eTag : {}, ファイル名 : {}", result.eTag(), result.toString());
    return "redirect:/s3/index";
  }

  @GetMapping("/image")
  public String getImage(Model model) {
    return "image";
  }
}
