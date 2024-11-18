package com.uthkarsh.fileAPI.services.serviceFactory.FileUploadPackage.TypeFileUploaderFactory;

import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.exception.PersonalizedFactoryException;
import com.uthkarsh.fileAPI.services.serviceFactory.FileUploadPackage.TypeFileUploaderBean.AwsFileUpload;
import com.uthkarsh.fileAPI.services.serviceFactory.FileUploadPackage.TypeFileUploaderBean.FileUploaderInterface.FileUploader;
import com.uthkarsh.fileAPI.services.serviceFactory.FileUploadPackage.TypeFileUploaderBean.LocalFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FileUploaderFactory {

    private final static Logger logger = LoggerFactory.getLogger(FileUploaderFactory.class);

    private final ApplicationContext applicationContext;

    @Autowired
    public FileUploaderFactory(ApplicationContext context){
        this.applicationContext = context;
    }

    public FileUploader getFileUploader(FileUploaderEnum type){
        logger.info("Retrieving FileUploader type: {}", type);

        try{
            if(type == FileUploaderEnum.AWS){
                logger.debug("Returning AwsFileUploader bean");

                return applicationContext.getBean(AwsFileUpload.class);
            }
            else if(type == FileUploaderEnum.LOCAL){
                logger.debug("Returning LocalFileUploader bean");

                return applicationContext.getBean(LocalFileUpload.class);
            }
            else{
                logger.error("Unsupported File Uploader type: {}",type);

                throw new IllegalArgumentException("Please send the getFileUploader method correct parameter");
            }
        }
        catch (Exception e){
            logger.error("Failed to retrieve FileUploader type: {}", type, e);

            throw new PersonalizedFactoryException("Failed to create the File Uploader of type: "+ type);
        }
    }
}
