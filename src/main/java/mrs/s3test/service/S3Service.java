package mrs.s3test.service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.BucketAlreadyExistsException;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
@Log4j2
public class S3Service {

  @Value("${cloud.aws.endpoint}")
  private String endpoint;

  @Value("${cloud.aws.region}")
  private String region;

  @Value("${cloud.aws.credentials.accessKey}")
  private String accessKey;

  @Value("${cloud.aws.credentials.secretKey}")
  private String secretKey;

  @Value("${cloud.aws.bucket.name}")
  private String bucketName;

  private S3Client s3Client;

  @PostConstruct
  private void initializeS3Client() {
    s3Client = S3Client.builder()
        .endpointOverride(URI.create(endpoint))
        .region(Region.of(region))
        .credentialsProvider(
            StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
        .build();
  }

  /**
   * Amazon S3のバケットが存在しない場合は作成する。<br> すでに存在している場合は、ログを出力して終了する。
   */
  public void createBucket() {
    try {
      log.info("バケットを作成します。 バケット名 : {}", bucketName);
      s3Client.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
      log.info("バケットが作成されました。 バケット名 : {}", bucketName);
    } catch (BucketAlreadyExistsException e) {
      log.info("バケットはすでに存在しています。 バケット名 : {}", bucketName);
    }
  }

  /**
   * Amazon S3にファイルをアップロードする
   *
   * @param key      ファイル名
   * @param fileDate ファイルのバイト配列
   * @return PutObjectResponse レスポンス
   */
  public PutObjectResponse uploadFile(String key, byte[] fileDate) {
    return s3Client.putObject(PutObjectRequest.builder().bucket(bucketName).key(key).build(),
        RequestBody.fromBytes(fileDate));
  }
}
