package com.uthkarsh.fileAPI.services.serviceFactory.FileDownloadPackage.TypeFileDownloadFactory;

import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.services.serviceFactory.FileDownloadPackage.TypeFileDownloadBean.AwsFileDownload;
import com.uthkarsh.fileAPI.services.serviceFactory.FileDownloadPackage.TypeFileDownloadBean.FileDownloadInterface.FileDownload;
import com.uthkarsh.fileAPI.services.serviceFactory.FileDownloadPackage.TypeFileDownloadBean.LocalFileDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DownloadFactory {

    private final ApplicationContext applicationContext;

    @Autowired
    public DownloadFactory(ApplicationContext context){
        this.applicationContext = context;
    }

    public FileDownload getTypeFileDownloader(FileUploaderEnum type){
        if(type == FileUploaderEnum.AWS){
            return applicationContext.getBean(AwsFileDownload.class);
        }
        else if(type == FileUploaderEnum.LOCAL){
            return applicationContext.getBean(LocalFileDownload.class);
        }
        else{
            throw new IllegalArgumentException("Unsupported type provided to download from");
        }
    }

}
