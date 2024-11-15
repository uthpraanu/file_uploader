package com.uthkarsh.fileAPI.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.uthkarsh.fileAPI.exception.ConfigurationException;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class S3StorageService {
    private static final Logger logger = LoggerFactory.getLogger(S3StorageService.class);

    private final AmazonS3 s3Client;

    private final String bucketName;

    public S3StorageService(@Value("${aws.access.key.id}") String accessKeyId,
                            @Value("${aws.access.key.secret}") String secretAccessKey,
                            @Value("${aws.s3.region}") String region,
                            @Value("${aws.s3.bucket-name}") String bucketNameEnv){
        this.bucketName = bucketNameEnv;

        logger.info("Initializing S3Storage with bucket: {}",bucketNameEnv);

        try{
            BasicAWSCredentials awsCred = new BasicAWSCredentials(accessKeyId, secretAccessKey);

            this.s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(region)
                    .withCredentials(new AWSStaticCredentialsProvider(awsCred))
                    .build();

            logger.info("Amazon S3 client successfully initialized for bucket: {}", bucketNameEnv);
        }
        catch (Exception e){
            logger.error("Failed to initialize Amazon S3 client. Error: {}",e.getMessage(),e);
            throw new ConfigurationException("Failed to initialize Amazon S3 client",e);
        }
    }
}
