package com.uthkarsh.fileAPI.services.old.CompanyService;

import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.entity.orders.PurchaseOrder;
import com.uthkarsh.fileAPI.entity.organization.Company;
import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.exception.FileNotFoundException;
import com.uthkarsh.fileAPI.exception.RepositoryException;
import com.uthkarsh.fileAPI.repository.orders.PurchaseOrderRepository;
import com.uthkarsh.fileAPI.repository.organization.CompanyRepository;
import com.uthkarsh.fileAPI.services.newV2.factory.FileUploadPackage.TypeFileUploaderFactory.FileUploaderFactory;
import com.uthkarsh.fileAPI.services.newV2.factory.FileUploadPackage.TypeFileUploaderBean.FileUploaderInterface.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UploadPurchaseOrderService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private FileUploaderFactory fileUploaderFactory;

    public ResponseEntity<?> upload(LongDTO id, FileUploaderEnum type, MultipartFile file){
        FileUploader fileUploader = fileUploaderFactory.getFileUploader(type);

        PurchaseOrder purchaseOrder = new PurchaseOrder();

        if(file.isEmpty()){
            throw new FileNotFoundException("file not found");
        }

        purchaseOrder.setOrderName(file.getOriginalFilename());
        purchaseOrder.setFileSize(file.getSize());
        purchaseOrder.setFileType(file.getContentType());

        purchaseOrder.setUploadDate(LocalDate.now());
        purchaseOrder.setExpiryDate(LocalDate.now()); // add further date here

        purchaseOrder.setUploadBy("someone");
        purchaseOrder.setStatus("1");
        purchaseOrder.setDescription("some description");
        purchaseOrder.setFileHash("some hash");

        Optional<Company> c = companyRepository.findById(id.getId());

        if(c.isEmpty()){
            ResponseEntity.badRequest().body("id mentioned is wrong");
        }

        purchaseOrder.setCompany(c.get());

        try {
            purchaseOrder.setFileUrl(fileUploader.upload(file));
        }
        catch (Exception e){
            e.printStackTrace();
            throw new FileNotFoundException("cannot add file url to the database");
        }

        try{
            purchaseOrderRepository.save(purchaseOrder);
        }
        catch (Exception e){
            throw new RepositoryException("failed to save the data");
        }

        return ResponseEntity.ok("successfully saved");
    }
}
