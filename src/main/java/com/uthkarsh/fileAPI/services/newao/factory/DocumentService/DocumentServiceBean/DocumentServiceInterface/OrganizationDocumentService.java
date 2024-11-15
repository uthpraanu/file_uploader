package com.uthkarsh.fileAPI.services.newao.factory.DocumentService.DocumentServiceBean.DocumentServiceInterface;

import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.services.newao.factory.utility.organizationParameters.OrganizationMethoParameter.OrganizationUploadMethodParameter;
import org.springframework.web.multipart.MultipartFile;

public interface OrganizationDocumentService {
    public String uploadMethodCall(OrganizationUploadMethodParameter parameters);
}
