package com.uthkarsh.fileAPI.services.serviceFactory.FileUploadPackage.TypeFileUploaderBean;

import com.uthkarsh.fileAPI.exception.ServiceFailedException;
import com.uthkarsh.fileAPI.services.serviceFactory.FileUploadPackage.TypeFileUploaderBean.FileUploaderInterface.FileUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class LocalFileUpload implements FileUploader {
    private static final Logger logger = LoggerFactory.getLogger(LocalFileUpload.class);

    @Value("${file.upload.path}")
    private String path;

    @Override
    public String upload(MultipartFile file) throws IOException {
        logger.info("Starting local file upload for file: {}", file.getOriginalFilename());

        // Validate if the file is empty
        if(file.isEmpty()){
            logger.error("Upload failed: File is empty");

            throw new IOException("file is empty");
        }

        //Construct the file pat
        String uniqueFileName = UUID.randomUUID()+"_"+file.getOriginalFilename();
        Path filePath = Paths.get(path+"\\"+uniqueFileName);

        logger.debug("File will be saved to path: {}",filePath);

        try(BufferedInputStream inputStream = new BufferedInputStream(file.getInputStream());
            FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile())){

            // Write file contents
            byte[] buffer = new byte[8192];
            int bytesRead = 0;

            while((bytesRead = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            logger.info("File successfully saved to local path: {}", filePath);

            return filePath.toString();

        }
        catch (Exception e){
            logger.error("Failed to save file to local path: {}",filePath, e);

            throw new ServiceFailedException("failed to save to the local file path", e);
        }
    }
}
