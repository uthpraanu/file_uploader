package com.uthkarsh.fileAPI.services.serviceFactory.FileDownloadPackage.TypeFileDownloadBean;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.uthkarsh.fileAPI.configuration.S3StorageService;
import com.uthkarsh.fileAPI.services.serviceFactory.FileDownloadPackage.TypeFileDownloadBean.FileDownloadInterface.FileDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class AwsFileDownload implements FileDownload {
    @Autowired
    private S3StorageService s3StorageService;

    @Override
    public Resource download(String name) {
        S3Object s3Object = s3StorageService.getS3Client().getObject(new GetObjectRequest(s3StorageService.getBucketName(), name));

        S3ObjectInputStream inputStream = s3Object.getObjectContent();

        return new InputStreamResource(inputStream);
    }
}
