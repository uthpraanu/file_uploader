package com.uthkarsh.fileAPI.services.VendorService;

import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.entity.orders.OrderQuotation;
import com.uthkarsh.fileAPI.entity.orders.PurchaseOrder;
import com.uthkarsh.fileAPI.entity.organization.Vendor;
import com.uthkarsh.fileAPI.exception.FileNotFoundException;
import com.uthkarsh.fileAPI.exception.RepositoryException;
import com.uthkarsh.fileAPI.exception.ServiceFailedException;
import com.uthkarsh.fileAPI.repository.orders.OrderQuotationRepository;
import com.uthkarsh.fileAPI.repository.orders.PurchaseOrderRepository;
import com.uthkarsh.fileAPI.repository.organization.VendorRepository;
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
public class UploadQuotationService {

    @Value("${file.upload.path}")
    private String path;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private OrderQuotationRepository orderQuotationRepository;

    @Autowired
    private VendorRepository vendorRepository;

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

    public ResponseEntity<?> upload(LongDTO id, LongDTO orderId, MultipartFile file){
        Optional<Vendor> vendor = vendorRepository.findById(id.getId());

        Optional<PurchaseOrder> order = purchaseOrderRepository.findById(orderId.getId());

        if(vendor.isEmpty() || order.isEmpty()){
            throw new RepositoryException("The vendor or order id is invalid");
        }

        OrderQuotation quotation = new OrderQuotation();

        if(file.isEmpty()){
            throw new FileNotFoundException("file not found");
        }

        quotation.setName(file.getOriginalFilename());
        quotation.setFileSize(file.getSize());
        quotation.setFileType(file.getContentType());

        quotation.setUploadDate(LocalDate.now());
        quotation.setUploadBy("someone");
        quotation.setStatus("1");
        quotation.setDescription("hhi ou");
        quotation.setFileHash("some hash");

        try{
            quotation.setPath(uploadToFileSystem(file));
        }
        catch (Exception e){
            throw new FileNotFoundException("trouble saving the file");
        }

        quotation.setVendor(vendor.get());
        quotation.setOrder(order.get());

        try{
            orderQuotationRepository.save(quotation);
        }
        catch (Exception e){
            throw new RepositoryException("Some issue in saving to the repository");
        }

        return ResponseEntity.ok("success");
    }
}
