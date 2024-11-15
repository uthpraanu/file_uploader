package com.uthkarsh.fileAPI.controller.old.compnay;

import com.uthkarsh.fileAPI.dto.general.LongDTO;
import com.uthkarsh.fileAPI.enums.FileUploaderEnum;
import com.uthkarsh.fileAPI.services.old.CompanyService.UploadPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/v1")
public class AddPurchaseOrderController {
    @Autowired
    private UploadPurchaseOrderService uploadPurchaseOrderService;

    @PostMapping(value = "/addOrder", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addOrder(@RequestPart("id") LongDTO id, @RequestPart("file")MultipartFile order){
        return uploadPurchaseOrderService.upload(id, FileUploaderEnum.AWS, order);
    }

}
