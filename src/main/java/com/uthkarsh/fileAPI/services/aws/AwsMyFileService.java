package com.uthkarsh.fileAPI.services.aws;

import com.amazonaws.services.s3.model.*;
import com.uthkarsh.fileAPI.configuration.S3StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class AwsMyFileService {
    @Autowired
    private S3StorageService s3StorageService;

    public ResponseEntity<?> upload(MultipartFile file) throws IOException {

        String uniqueFileName = UUID.randomUUID()+"_"+file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();

        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        s3StorageService.getS3Client().putObject(
                new PutObjectRequest(
                        s3StorageService.getBucketName(),
                        uniqueFileName,
                        file.getInputStream(),
                        metadata
                ));

        String a = s3StorageService.getS3Client().getUrl(s3StorageService.getBucketName(), uniqueFileName).toString();

        return ResponseEntity.ok("success : "+a);
    }

    public ResponseEntity<?> download(String fileName){

        S3Object s3Object = s3StorageService.getS3Client().getObject(new GetObjectRequest(s3StorageService.getBucketName(), fileName));

        S3ObjectInputStream inputStream = s3Object.getObjectContent();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+fileName+"\"")
                .body(new InputStreamResource(inputStream));
    }
}
