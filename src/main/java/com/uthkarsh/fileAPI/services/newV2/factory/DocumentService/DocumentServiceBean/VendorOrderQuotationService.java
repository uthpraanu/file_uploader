package com.uthkarsh.fileAPI.services.newV2.factory.DocumentService.DocumentServiceBean;

import com.uthkarsh.fileAPI.entity.orders.OrderQuotation;
import com.uthkarsh.fileAPI.entity.orders.PurchaseOrder;
import com.uthkarsh.fileAPI.entity.organization.Vendor;
import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.exception.FileNotFoundException;
import com.uthkarsh.fileAPI.exception.RepositoryException;
import com.uthkarsh.fileAPI.services.newV2.factory.DocumentService.DocumentServiceBean.DocumentServiceInterface.OrganizationDocumentService;
import com.uthkarsh.fileAPI.repository.orders.OrderQuotationRepository;
import com.uthkarsh.fileAPI.repository.orders.PurchaseOrderRepository;
import com.uthkarsh.fileAPI.repository.organization.VendorRepository;
import com.uthkarsh.fileAPI.services.newV2.factory.FileUploadPackage.TypeFileUploaderBean.FileUploaderInterface.FileUploader;
import com.uthkarsh.fileAPI.services.newV2.factory.FileUploadPackage.TypeFileUploaderFactory.FileUploaderFactory;
import com.uthkarsh.fileAPI.services.newV2.factory.utility.organizationParameters.OrganizationMethoParameter.OrganizationUploadMethodParameter;
import com.uthkarsh.fileAPI.services.newV2.factory.utility.organizationParameters.VendorUploadMethodParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class VendorOrderQuotationService implements OrganizationDocumentService {

    private static final Logger logger = LoggerFactory.getLogger(VendorOrderQuotationService.class);

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private OrderQuotationRepository orderQuotationRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private FileUploaderFactory fileUploaderFactory;

    private String upload(Long vendorId, Long purchaseOrderId, FileUploaderEnum type, MultipartFile file){
        logger.info("Starting upload process for vendorId : {}",vendorId);

        // Check if the file is empty
        if(file.isEmpty()){
            logger.error("Upload Failed: File is empty");
            throw new FileNotFoundException("File not found");
        }

        // Fetch vendor details
        Optional<Vendor> vendorOptional = vendorRepository.findById(vendorId);

        if(vendorOptional.isEmpty()){
            logger.error("Upload failed: Invalid vendorId {}", vendorId);
            throw new RepositoryException("Invalid company ID provided");
        }

        Vendor vendor = vendorOptional.get();

        // Fetch order details
        Optional<PurchaseOrder> purchaseOrderderOptional = purchaseOrderRepository.findById(purchaseOrderId);

        if(purchaseOrderderOptional.isEmpty()){
            logger.error("Upload failed: Invalid purchaseOrderID {}", purchaseOrderId);
            throw new RepositoryException("Invalid Purchase Order ID provided");
        }

        PurchaseOrder purchaseOrder = purchaseOrderderOptional.get();

        // Create an Order Quotation object
        OrderQuotation orderQuotation = new OrderQuotation();

        // Get the bean for uploading it to the file storage
        FileUploader fileUploader = fileUploaderFactory.getFileUploader(type);

        try{
            logger.info("Uploading file: {}", file.getOriginalFilename());
            String fileUrl = fileUploader.upload(file);

            orderQuotation.setName(file.getOriginalFilename());
            orderQuotation.setFileSize(file.getSize());
            orderQuotation.setFileType(file.getContentType());

            orderQuotation.setUploadDate(LocalDate.now());
            orderQuotation.setUploadBy("system");
            orderQuotation.setStatus("Active");
            orderQuotation.setDescription("order quotation uploaded");
            orderQuotation.setFileHash("dummy-hash");

            orderQuotation.setPath(fileUrl);

            orderQuotation.setVendor(vendor);
            orderQuotation.setOrder(purchaseOrder);

            // Save Order Quotation to the repository
            orderQuotationRepository.save(orderQuotation);

            logger.info("File uploaded in {} and Order Quotation saved successfully for vendorId: {}",type, vendorId);
            return "File uploaded successfully";
        }
        catch (Exception e){
            logger.error("Upload failed due to an error : {}",e.getMessage(), e);
            throw new RepositoryException("Failed to upload and save the purchase order to the database");
        }
    }

    public String download(){
        return "Order Quotation downloaded";
    }

    @Override
    public String uploadMethodCall(OrganizationUploadMethodParameter parameters) {

        VendorUploadMethodParameter data = (VendorUploadMethodParameter) parameters;

         return upload(data.getVendorID(), data.getPurchaseOrderID(), data.getType(), data.getFile());
    }
}
