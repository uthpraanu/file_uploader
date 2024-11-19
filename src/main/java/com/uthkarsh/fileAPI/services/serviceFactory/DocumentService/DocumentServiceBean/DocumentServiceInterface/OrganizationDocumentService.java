package com.uthkarsh.fileAPI.services.serviceFactory.DocumentService.DocumentServiceBean.DocumentServiceInterface;

import com.uthkarsh.fileAPI.services.serviceFactory.utility.organizationParameters.OrganizationMethoParameter.OrganizationUploadMethodParameter;

public interface OrganizationDocumentService {
    public String uploadMethodCall(OrganizationUploadMethodParameter parameters);
}
