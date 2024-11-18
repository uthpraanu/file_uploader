package com.uthkarsh.fileAPI.services.serviceFactory.FileUploadPackage.TypeFileUploaderBean;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.uthkarsh.fileAPI.configuration.S3StorageService;
import com.uthkarsh.fileAPI.services.serviceFactory.FileUploadPackage.TypeFileUploaderBean.FileUploaderInterface.FileUploader;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class AwsFileUpload implements FileUploader {

    private static final Logger logger = LoggerFactory.getLogger(AwsFileUpload.class);

    @Autowired
    private S3StorageService s3StorageService;

    @Override
    public String upload(MultipartFile file) throws IOException {
        logger.info("Starting AWS file upload for file: {}", file.getOriginalFilename());

        // Generate a unique file name
        String uniqueFileName = UUID.randomUUID()+"_"+file.getOriginalFilename();
        logger.debug("Generated unique file name: {}", uniqueFileName);

        try{
            // Set metadata for the file
            ObjectMetadata metadata = new ObjectMetadata();

            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            logger.debug("Metadata set for the file: contentType={}, contentLength={}",
                    file.getContentType(),
                    file.getSize()
            );

            // Upload the file to S3
            s3StorageService.getS3Client().putObject(
                    new PutObjectRequest(
                            s3StorageService.getBucketName(),
                            uniqueFileName,
                            file.getInputStream(),
                            metadata
                    )
            );

            logger.info("File uploaded successfully to bucket: {}",s3StorageService.getBucketName());

            // Retrieve the file URL
            String fileUrl = s3StorageService.getS3Client()
                    .getUrl(s3StorageService.getBucketName(), uniqueFileName)
                    .toString();

            logger.info("File URL generated: {}", fileUrl);

            return fileUrl;
        }
        catch (Exception e){
            logger.error("Failed to upload file to AWS S3: {}", file.getOriginalFilename());

            throw new FileUploadException("Error occurred while uploading file to AWS S3", e);
        }
    }
}
