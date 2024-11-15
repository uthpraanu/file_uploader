package com.uthkarsh.fileAPI.services.newV2.Vendor;

import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.dto.vendor.VendorDTO;
import com.uthkarsh.fileAPI.entity.organization.Vendor;
import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.enums.OrganizationEnum;
import com.uthkarsh.fileAPI.exception.RepositoryException;
import com.uthkarsh.fileAPI.exception.ServiceFailedException;
import com.uthkarsh.fileAPI.mapper.vendor.VendorMapper;
import com.uthkarsh.fileAPI.repository.organization.VendorRepository;
import com.uthkarsh.fileAPI.services.newV2.factory.DocumentService.DocumentServiceBean.DocumentServiceInterface.OrganizationDocumentService;
import com.uthkarsh.fileAPI.services.newV2.factory.DocumentService.DocumentServiceFactory.DocumentFactory;
import com.uthkarsh.fileAPI.services.newV2.factory.utility.organizationParameters.VendorUploadMethodParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VendorService {
    @Autowired
    private DocumentFactory documentFactory;

    @Autowired
    private VendorRepository vendorRepository;

    public String uploadOrderQuotation(LongDTO vendorID, LongDTO orderID, FileUploaderEnum type, MultipartFile file){

        OrganizationDocumentService vendorOrganization = documentFactory.getOrganizationService(OrganizationEnum.VENDOR);

        return vendorOrganization.uploadMethodCall(new VendorUploadMethodParameter(vendorID.getId(), orderID.getId(), type, file));
    }

    public String addNewVendor(VendorDTO vendorDTO){
        try{
            Vendor vendor = VendorMapper.mapIt(vendorDTO);

            vendorRepository.save(vendor);

            return "Vendor saved successfully";
        }
        catch (DataIntegrityViolationException ex){
            throw new RepositoryException("Duplicate entity");
        }
        catch (Exception e){
            throw new ServiceFailedException("Internal error occured");
        }
    }
}
