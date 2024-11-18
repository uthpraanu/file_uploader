package com.uthkarsh.fileAPI.services.serviceFactory.FileDownloadPackage.TypeFileDownloadBean;

import com.uthkarsh.fileAPI.exception.ServiceFailedException;
import com.uthkarsh.fileAPI.services.serviceFactory.FileDownloadPackage.TypeFileDownloadBean.FileDownloadInterface.FileDownload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LocalFileDownload implements FileDownload {

    @Value("${file.upload.path}")
    private String path;

    @Override
    public Resource download(String name) {
        try{

            if (name.contains("..") || name.contains("/") || name.contains("\\")) {
                throw new ServiceFailedException("There seems to be some bad request sent from the client");
            }

            Path filePath = Paths.get(path).resolve(name).normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists() && resource.isReadable()){
                return resource;
            }
            else{
                throw new ServiceFailedException("The file you mentioned could not be found");
            }
        }
        catch (IOException e){
            throw new ServiceFailedException("Some issue in the service code of the Local File Download");
        }
    }
}
