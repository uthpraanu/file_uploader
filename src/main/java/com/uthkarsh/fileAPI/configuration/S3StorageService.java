package com.uthkarsh.fileAPI.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class S3StorageService {
    private final AmazonS3 s3Client;

    private final String bucketName;

    public S3StorageService(@Value("${aws.access.key.id}") String accessKeyId,
                            @Value("${aws.access.key.secret}") String secretAccessKey,
                            @Value("${aws.s3.region}") String region,
                            @Value("${aws.s3.bucket-name}") String bucketNameEnv){
        this.bucketName = bucketNameEnv;

        BasicAWSCredentials awsCred = new BasicAWSCredentials(accessKeyId, secretAccessKey);

        System.out.println("bucket name = "+bucketNameEnv);
        System.out.println("id = "+accessKeyId);
        System.out.println("region = "+region);
        System.out.println("secret = "+secretAccessKey);

        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCred))
                .build();
    }
}
