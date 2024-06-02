package mrs.s3test.controller;

import java.io.IOException;
import java.util.List;
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
import software.amazon.awssdk.services.s3.model.S3Object;

@Controller
@RequestMapping("/s3")
@Log4j2
public class S3Controller {

  private final S3Service s3Service;

  public S3Controller(S3Service s3Service) {
    this.s3Service = s3Service;
  }

  @GetMapping("/index")
  public String index(Model model) {
    List<S3Object> result = s3Service.getObjectList();
    for (S3Object s3Object : result) {
      log.info(s3Object.key());
    }
    model.addAttribute("objectList", result);
    return "s3/images";
  }

  @PostMapping("/upload")
  public String upload(@RequestParam("file") MultipartFile file) throws IOException {
    PutObjectResponse result = s3Service.uploadFile(file.getOriginalFilename(), file.getBytes());
    log.info("ファイルアップロードしました。 eTag : {}, ファイル名 : {}", result.eTag(), result.toString());
    return "redirect:/s3/index";
  }

  @GetMapping("/image")
  public String getImage(Model model) {
    String url = s3Service.generatePresignedUrl("年賀状_2024.jpg");
    log.info(url);
    model.addAttribute("url", url);
    return "s3/image";
  }
}
