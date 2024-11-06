package com.uthkarsh.fileAPI.controller.vendor;

import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.services.VendorService.UploadQuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api/v1")
public class AddOrderQuotationController {
    @Autowired
    private UploadQuotationService uploadQuotationService;

    @PostMapping(value = "/uploadQuotation", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadQuotation(@RequestPart("id") LongDTO id, @RequestPart("OrderId") LongDTO orderId, @RequestPart("file")MultipartFile file){
        return uploadQuotationService.upload(id, orderId, file);
    }
}
