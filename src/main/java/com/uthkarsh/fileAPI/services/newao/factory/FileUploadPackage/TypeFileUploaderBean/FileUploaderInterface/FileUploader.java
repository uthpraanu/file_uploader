package com.uthkarsh.fileAPI.services.newao.factory.FileUploadPackage.TypeFileUploaderBean.FileUploaderInterface;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploader {
    public String upload(MultipartFile file) throws IOException;
}
