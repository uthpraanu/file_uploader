package com.uthkarsh.fileAPI.services.newV2.Company;

import com.uthkarsh.fileAPI.dto.compnay.CompanyDTO;
import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.entity.organization.Company;
import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.enums.OrganizationEnum;
import com.uthkarsh.fileAPI.exception.RepositoryException;
import com.uthkarsh.fileAPI.exception.ServiceFailedException;
import com.uthkarsh.fileAPI.mapper.company.CompanyMapper;
import com.uthkarsh.fileAPI.repository.organization.CompanyRepository;
import com.uthkarsh.fileAPI.services.newV2.serviceFactory.DocumentService.DocumentServiceBean.DocumentServiceInterface.OrganizationDocumentService;
import com.uthkarsh.fileAPI.services.newV2.serviceFactory.DocumentService.DocumentServiceFactory.DocumentFactory;
import com.uthkarsh.fileAPI.services.newV2.serviceFactory.FileDownloadPackage.FileDownloadFactory.DownloadFactory;
import com.uthkarsh.fileAPI.services.newV2.serviceFactory.FileDownloadPackage.TypeFileDownloadBean.FileDownloadInterface.FileDownload;
import com.uthkarsh.fileAPI.services.newV2.serviceFactory.utility.organizationParameters.CompanyUploadMethodParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CompanyService {
    @Autowired
    private DocumentFactory documentFactory;

    @Autowired
    private DownloadFactory downloadFactory;

    @Autowired
    private CompanyRepository companyRepository;

    public String uploadPurchaseOrder(LongDTO companyID, FileUploaderEnum type, MultipartFile file){
        OrganizationDocumentService companyOrganization = documentFactory.getOrganizationService(OrganizationEnum.COMPANY);

        return companyOrganization.uploadMethodCall(new CompanyUploadMethodParameter(companyID.getId(),type,file));
    }

    public Resource downloadPurchaseOrder(String name, FileUploaderEnum type){
        FileDownload typeFileDownload = downloadFactory.getTypeFileDownloader(type);

        return typeFileDownload.download(name);
    }

    public String addNewCompany(CompanyDTO companyDTO){
        try{
            Company c = CompanyMapper.mapIt(companyDTO);

            companyRepository.save(c);

            return "Saved successfully to the database";
        }
        catch (DataIntegrityViolationException ex){
            throw new RepositoryException("Duplicate entity");
        }
        catch (Exception e){
            throw new ServiceFailedException("Internal error occured");
        }
    }
}
