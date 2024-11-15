package com.uthkarsh.fileAPI.services.newao.Vendor;

import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.enums.OrganizationEnum;
import com.uthkarsh.fileAPI.services.newao.factory.DocumentService.DocumentServiceBean.DocumentServiceInterface.OrganizationDocumentService;
import com.uthkarsh.fileAPI.services.newao.factory.DocumentService.DocumentServiceFactory.DocumentFactory;
import com.uthkarsh.fileAPI.services.newao.factory.utility.organizationParameters.VendorUploadMethodParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VendorService {
    @Autowired
    private DocumentFactory documentFactory;

    public String uploadOrderQuotation(LongDTO vendorID, LongDTO orderID, FileUploaderEnum type, MultipartFile file){

        OrganizationDocumentService vendorOrganization = documentFactory.getOrganizationService(OrganizationEnum.VENDOR);

        return vendorOrganization.uploadMethodCall(new VendorUploadMethodParameter(vendorID.getId(), orderID.getId(), type, file));

    }
}
