package com.uthkarsh.fileAPI.services.CompanyService;

import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.entity.orders.OrderQuotation;
import com.uthkarsh.fileAPI.entity.orders.PurchaseOrder;
import com.uthkarsh.fileAPI.entity.organization.Company;
import com.uthkarsh.fileAPI.exception.FileNotFoundException;
import com.uthkarsh.fileAPI.exception.ServiceFailedException;
import com.uthkarsh.fileAPI.repository.orders.PurchaseOrderRepository;
import com.uthkarsh.fileAPI.repository.organization.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UploadPurchaseOrderService {
    @Value("${file.upload.path}")
    private String path;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    private String uploadToFileSystem(MultipartFile file){
        if(file.isEmpty()){
            throw new FileNotFoundException("file not found");
        }

        Path filePath = Paths.get(path+"\\"+file.getOriginalFilename());

        try(BufferedInputStream inputStream = new BufferedInputStream(file.getInputStream());
            FileOutputStream fileOutputStream = new FileOutputStream(filePath.toFile())){

            byte[] buffer = new byte[8192];
            int bytesRead = 0;

            while((bytesRead = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            return filePath.toString();

        }
        catch (Exception e){
            throw new ServiceFailedException("Some error in service");
        }
    }

    public ResponseEntity<?> upload(LongDTO id, MultipartFile file){

        PurchaseOrder purchaseOrder = new PurchaseOrder();

        purchaseOrder.setOrderName(file.getName());
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
        purchaseOrder.setFileUrl(uploadToFileSystem(file));

        purchaseOrderRepository.save(purchaseOrder);

        return ResponseEntity.ok("something");
    }
}
