package com.uthkarsh.fileAPI.services.newao.Company;

import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.enums.OrganizationEnum;
import com.uthkarsh.fileAPI.services.newao.factory.DocumentService.DocumentServiceBean.DocumentServiceInterface.OrganizationDocumentService;
import com.uthkarsh.fileAPI.services.newao.factory.DocumentService.DocumentServiceFactory.DocumentFactory;
import com.uthkarsh.fileAPI.services.newao.factory.utility.organizationParameters.CompanyUploadMethodParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CompanyService {
    @Autowired
    private DocumentFactory documentFactory;

    public String uploadPurchaseOrder(LongDTO id, FileUploaderEnum type, MultipartFile file){
        OrganizationDocumentService companyOrganization = documentFactory.getOrganizationService(OrganizationEnum.COMPANY);

        return companyOrganization.uploadMethodCall(new CompanyUploadMethodParameter(id.getId(),type,file));

    }
}
