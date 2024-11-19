package com.uthkarsh.fileAPI.services.Vendor;

import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.dto.vendor.VendorDTO;
import com.uthkarsh.fileAPI.entity.organization.Vendor;
import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.enums.OrganizationEnum;
import com.uthkarsh.fileAPI.exception.RepositoryException;
import com.uthkarsh.fileAPI.exception.ServiceFailedException;
import com.uthkarsh.fileAPI.mapper.vendor.VendorMapper;
import com.uthkarsh.fileAPI.repository.organization.VendorRepository;
import com.uthkarsh.fileAPI.services.serviceFactory.DocumentService.DocumentServiceBean.DocumentServiceInterface.OrganizationDocumentService;
import com.uthkarsh.fileAPI.services.serviceFactory.DocumentService.DocumentServiceFactory.DocumentFactory;
import com.uthkarsh.fileAPI.services.serviceFactory.FileDownloadPackage.TypeFileDownloadFactory.DownloadFactory;
import com.uthkarsh.fileAPI.services.serviceFactory.FileDownloadPackage.TypeFileDownloadBean.FileDownloadInterface.FileDownload;
import com.uthkarsh.fileAPI.services.serviceFactory.utility.organizationParameters.VendorUploadMethodParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VendorService {
    @Autowired
    private DocumentFactory documentFactory;

    @Autowired
    private DownloadFactory downloadFactory;

    @Autowired
    private VendorRepository vendorRepository;

    public String uploadOrderQuotation(LongDTO vendorID, LongDTO orderID, FileUploaderEnum type, MultipartFile file){

        OrganizationDocumentService vendorOrganization = documentFactory.getOrganizationService(OrganizationEnum.VENDOR);

        return vendorOrganization.uploadMethodCall(new VendorUploadMethodParameter(vendorID.getId(), orderID.getId(), type, file));
    }

    public Resource downloadOrderQuotation(String name, FileUploaderEnum type){
        FileDownload typeFileDownload = downloadFactory.getTypeFileDownloader(type);

        return typeFileDownload.download(name);
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
