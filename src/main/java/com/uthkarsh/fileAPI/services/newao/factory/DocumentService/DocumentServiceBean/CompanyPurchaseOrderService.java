package com.uthkarsh.fileAPI.services.newao.factory.DocumentService.DocumentServiceBean;

import com.uthkarsh.fileAPI.entity.orders.PurchaseOrder;
import com.uthkarsh.fileAPI.entity.organization.Company;
import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.exception.FileNotFoundException;
import com.uthkarsh.fileAPI.exception.RepositoryException;
import com.uthkarsh.fileAPI.services.newao.factory.DocumentService.DocumentServiceBean.DocumentServiceInterface.OrganizationDocumentService;
import com.uthkarsh.fileAPI.repository.orders.PurchaseOrderRepository;
import com.uthkarsh.fileAPI.repository.organization.CompanyRepository;
import com.uthkarsh.fileAPI.services.newao.factory.FileUploadPackage.TypeFileUploaderBean.FileUploaderInterface.FileUploader;
import com.uthkarsh.fileAPI.services.newao.factory.FileUploadPackage.TypeFileUploaderFactory.FileUploaderFactory;
import com.uthkarsh.fileAPI.services.newao.factory.utility.organizationParameters.CompanyUploadMethodParameter;
import com.uthkarsh.fileAPI.services.newao.factory.utility.organizationParameters.OrganizationMethoParameter.OrganizationUploadMethodParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CompanyPurchaseOrderService implements OrganizationDocumentService {
    private static final Logger logger = LoggerFactory.getLogger(CompanyPurchaseOrderService.class);

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private FileUploaderFactory fileUploaderFactory;

    private String upload(long companyId, FileUploaderEnum type, MultipartFile file){

        logger.info("Starting upload process for companyId : {}", companyId);

        // Check if the file is empty
        if(file.isEmpty()){
            logger.error("Upload failed: File is empty");
            throw new FileNotFoundException("File not found");
        }

        // Fetch company details
        Optional<Company> companyOptional = companyRepository.findById(companyId);

        if(companyOptional.isEmpty()){
            logger.error("Upload failed: Invalid companyId {}", companyId);
            throw new RepositoryException("Invalid company ID provided");
        }

        Company company = companyOptional.get();

        // Create a Purchase Order object
        PurchaseOrder purchaseOrder = new PurchaseOrder();

        // Get the bean for uploading it to the file storage
        FileUploader fileUploader = fileUploaderFactory.getFileUploader(type);

        try{
            logger.info("Uploading file: {}", file.getOriginalFilename());
            String fileUrl = fileUploader.upload(file);

            purchaseOrder.setOrderName(file.getOriginalFilename());
            purchaseOrder.setFileSize(file.getSize());
            purchaseOrder.setFileType(file.getContentType());

            purchaseOrder.setUploadDate(LocalDate.now());
            purchaseOrder.setExpiryDate(LocalDate.now().plusDays(30));

            purchaseOrder.setUploadBy("Testing System");
            purchaseOrder.setStatus("Active");
            purchaseOrder.setDescription("purchase order uploaded");
            purchaseOrder.setFileHash("dummy-hash");
            purchaseOrder.setCompany(company);
            purchaseOrder.setFileUrl(fileUrl);

            // Save PurchaseOrder to the repository
            purchaseOrderRepository.save(purchaseOrder);

            logger.info("File uploaded in {} and Purchase order saved successfully for companyId : {}", type, companyId);
            return "File uploaded successfully";
        }catch (Exception e){
            logger.error("Upload failed due to an error : {}", e.getMessage(), e);
            throw new RepositoryException("Failed to upload and save the purchase order to the database.");
        }
    }

    public String download(){
        return "Purchase order downloaded";
    }

    @Override
    public String uploadMethodCall(OrganizationUploadMethodParameter parameters) {
        CompanyUploadMethodParameter data = (CompanyUploadMethodParameter) parameters;
        return upload(data.getCompanyID(), data.getType(), data.getFile());
    }
}
